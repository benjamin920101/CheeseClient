/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.audio;

import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.audio.AudioPacket;
import tomp2p.opuswrapper.Opus;

public class Decoder {
    protected int ssrc;
    protected char lastSeq;
    protected int lastTimestamp;
    protected PointerByReference opusDecoder;

    protected Decoder(int ssrc) {
        this.ssrc = ssrc;
        this.lastSeq = (char)65535;
        this.lastTimestamp = -1;
        IntBuffer error = IntBuffer.allocate(1);
        this.opusDecoder = Opus.INSTANCE.opus_decoder_create(48000, 2, error);
        if (error.get() != 0 && this.opusDecoder == null) {
            throw new IllegalStateException("Received error code from opus_decoder_create(...): " + error.get());
        }
    }

    public boolean isInOrder(char newSeq) {
        return this.lastSeq == '\uffff' || newSeq > this.lastSeq || this.lastSeq - newSeq > 10;
    }

    public boolean wasPacketLost(char newSeq) {
        return newSeq > this.lastSeq + '\u0001';
    }

    public short[] decodeFromOpus(AudioPacket decryptedPacket) {
        int result;
        ShortBuffer decoded = ShortBuffer.allocate(4096);
        if (decryptedPacket == null) {
            result = Opus.INSTANCE.opus_decode(this.opusDecoder, null, 0, decoded, 960, 0);
            this.lastSeq = (char)65535;
            this.lastTimestamp = -1;
        } else {
            this.lastSeq = decryptedPacket.getSequence();
            this.lastTimestamp = decryptedPacket.getTimestamp();
            ByteBuffer encodedAudio = decryptedPacket.getEncodedAudio();
            int length = encodedAudio.remaining();
            int offset = encodedAudio.arrayOffset() + encodedAudio.position();
            byte[] buf = new byte[length];
            byte[] data = encodedAudio.array();
            System.arraycopy(data, offset, buf, 0, length);
            result = Opus.INSTANCE.opus_decode(this.opusDecoder, buf, buf.length, decoded, 960, 0);
        }
        if (result < 0) {
            this.handleDecodeError(result);
            return null;
        }
        short[] audio = new short[result * 2];
        decoded.get(audio);
        return audio;
    }

    private void handleDecodeError(int result) {
        StringBuilder b2 = new StringBuilder("Decoder failed to decode audio from user with code ");
        switch (result) {
            case -1: {
                b2.append("OPUS_BAD_ARG");
                break;
            }
            case -2: {
                b2.append("OPUS_BUFFER_TOO_SMALL");
                break;
            }
            case -3: {
                b2.append("OPUS_INTERNAL_ERROR");
                break;
            }
            case -4: {
                b2.append("OPUS_INVALID_PACKET");
                break;
            }
            case -5: {
                b2.append("OPUS_UNIMPLEMENTED");
                break;
            }
            case -6: {
                b2.append("OPUS_INVALID_STATE");
                break;
            }
            case -7: {
                b2.append("OPUS_ALLOC_FAIL");
                break;
            }
            default: {
                b2.append(result);
            }
        }
        AudioConnection.LOG.debug("{}", (Object)b2);
    }

    protected synchronized void close() {
        if (this.opusDecoder != null) {
            Opus.INSTANCE.opus_decoder_destroy(this.opusDecoder);
            this.opusDecoder = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.close();
    }
}

