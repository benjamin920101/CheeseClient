/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

import com.iwebpp.crypto.TweetNaclFast;
import java.net.DatagramPacket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.audio.AudioEncryption;
import net.dv8tion.jda.internal.utils.IOUtil;

public class AudioPacket {
    public static final int RTP_HEADER_BYTE_LENGTH = 12;
    public static final byte RTP_VERSION_PAD_EXTEND = -128;
    public static final byte RTP_PAYLOAD_TYPE = 120;
    public static final short RTP_DISCORD_EXTENSION = -16674;
    public static final int PT_INDEX = 1;
    public static final int SEQ_INDEX = 2;
    public static final int TIMESTAMP_INDEX = 4;
    public static final int SSRC_INDEX = 8;
    private final byte type;
    private final char seq;
    private final int timestamp;
    private final int ssrc;
    private final byte[] rawPacket;
    private final ByteBuffer encodedAudio;

    public AudioPacket(DatagramPacket packet) {
        this(Arrays.copyOf(packet.getData(), packet.getLength()));
    }

    public AudioPacket(byte[] rawPacket) {
        this.rawPacket = rawPacket;
        ByteBuffer buffer = ByteBuffer.wrap(rawPacket);
        this.seq = buffer.getChar(2);
        this.timestamp = buffer.getInt(4);
        this.ssrc = buffer.getInt(8);
        this.type = buffer.get(1);
        byte profile = buffer.get(0);
        byte[] data = buffer.array();
        boolean hasExtension = (profile & 0x10) != 0;
        byte cc2 = (byte)(profile & 0xF);
        int csrcLength = cc2 * 4;
        short extension = hasExtension ? IOUtil.getShortBigEndian(data, 12 + csrcLength) : (short)0;
        int offset = 12 + csrcLength;
        if (hasExtension && extension == -16674) {
            offset = this.getPayloadOffset(data, csrcLength);
        }
        this.encodedAudio = ByteBuffer.allocate(data.length - offset);
        this.encodedAudio.put(data, offset, this.encodedAudio.capacity());
        ((Buffer)this.encodedAudio).flip();
    }

    public AudioPacket(ByteBuffer buffer, char seq, int timestamp, int ssrc, ByteBuffer encodedAudio) {
        this.seq = seq;
        this.ssrc = ssrc;
        this.timestamp = timestamp;
        this.encodedAudio = encodedAudio;
        this.type = (byte)120;
        this.rawPacket = AudioPacket.generateRawPacket(buffer, seq, timestamp, ssrc, encodedAudio);
    }

    private int getPayloadOffset(byte[] data, int csrcLength) {
        short headerLength = IOUtil.getShortBigEndian(data, 14 + csrcLength);
        int i2 = 16 + csrcLength + headerLength * 4;
        while (data[i2] == 0) {
            ++i2;
        }
        return i2;
    }

    public byte[] getHeader() {
        return Arrays.copyOf(this.rawPacket, 12);
    }

    public byte[] getNoncePadded() {
        byte[] nonce = new byte[24];
        System.arraycopy(this.rawPacket, 0, nonce, 0, 12);
        return nonce;
    }

    public byte[] getRawPacket() {
        return this.rawPacket;
    }

    public ByteBuffer getEncodedAudio() {
        return this.encodedAudio;
    }

    public char getSequence() {
        return this.seq;
    }

    public int getSSRC() {
        return this.ssrc;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    protected ByteBuffer asEncryptedPacket(TweetNaclFast.SecretBox boxer, ByteBuffer buffer, byte[] nonce, int nlen) {
        byte[] extendedNonce = nonce;
        if (nlen == 0) {
            extendedNonce = this.getNoncePadded();
        }
        byte[] array = this.encodedAudio.array();
        int offset = this.encodedAudio.arrayOffset() + this.encodedAudio.position();
        int length = this.encodedAudio.remaining();
        byte[] encryptedAudio = boxer.box(array, offset, length, extendedNonce);
        ((Buffer)buffer).clear();
        int capacity = 12 + encryptedAudio.length + nlen;
        if (capacity > buffer.remaining()) {
            buffer = ByteBuffer.allocate(capacity);
        }
        AudioPacket.populateBuffer(this.seq, this.timestamp, this.ssrc, ByteBuffer.wrap(encryptedAudio), buffer);
        if (nlen > 0) {
            buffer.put(nonce, 0, nlen);
        }
        ((Buffer)buffer).flip();
        return buffer;
    }

    protected static AudioPacket decryptAudioPacket(AudioEncryption encryption, DatagramPacket packet, byte[] secretKey) {
        byte[] extendedNonce;
        TweetNaclFast.SecretBox boxer = new TweetNaclFast.SecretBox(secretKey);
        AudioPacket encryptedPacket = new AudioPacket(packet);
        if (encryptedPacket.type != 120) {
            return null;
        }
        byte[] rawPacket = encryptedPacket.getRawPacket();
        switch (encryption) {
            case XSALSA20_POLY1305: {
                extendedNonce = encryptedPacket.getNoncePadded();
                break;
            }
            case XSALSA20_POLY1305_SUFFIX: {
                extendedNonce = new byte[24];
                System.arraycopy(rawPacket, rawPacket.length - extendedNonce.length, extendedNonce, 0, extendedNonce.length);
                break;
            }
            case XSALSA20_POLY1305_LITE: {
                extendedNonce = new byte[24];
                System.arraycopy(rawPacket, rawPacket.length - 4, extendedNonce, 0, 4);
                break;
            }
            default: {
                AudioConnection.LOG.debug("Failed to decrypt audio packet, unsupported encryption mode!");
                return null;
            }
        }
        ByteBuffer encodedAudio = encryptedPacket.encodedAudio;
        int length = encodedAudio.remaining();
        int offset = encodedAudio.arrayOffset() + encodedAudio.position();
        switch (encryption) {
            case XSALSA20_POLY1305: {
                break;
            }
            case XSALSA20_POLY1305_LITE: {
                length -= 4;
                break;
            }
            case XSALSA20_POLY1305_SUFFIX: {
                length -= 24;
                break;
            }
            default: {
                AudioConnection.LOG.debug("Failed to decrypt audio packet, unsupported encryption mode!");
                return null;
            }
        }
        byte[] decryptedAudio = boxer.open(encodedAudio.array(), offset, length, extendedNonce);
        if (decryptedAudio == null) {
            AudioConnection.LOG.trace("Failed to decrypt audio packet");
            return null;
        }
        byte[] decryptedRawPacket = new byte[12 + decryptedAudio.length];
        System.arraycopy(encryptedPacket.rawPacket, 0, decryptedRawPacket, 0, 12);
        System.arraycopy(decryptedAudio, 0, decryptedRawPacket, 12, decryptedAudio.length);
        return new AudioPacket(decryptedRawPacket);
    }

    private static byte[] generateRawPacket(ByteBuffer buffer, char seq, int timestamp, int ssrc, ByteBuffer data) {
        if (buffer == null) {
            buffer = ByteBuffer.allocate(12 + data.remaining());
        }
        AudioPacket.populateBuffer(seq, timestamp, ssrc, data, buffer);
        return buffer.array();
    }

    private static void populateBuffer(char seq, int timestamp, int ssrc, ByteBuffer data, ByteBuffer buffer) {
        buffer.put((byte)-128);
        buffer.put((byte)120);
        buffer.putChar(seq);
        buffer.putInt(timestamp);
        buffer.putInt(ssrc);
        buffer.put(data);
        ((Buffer)data).flip();
    }
}

