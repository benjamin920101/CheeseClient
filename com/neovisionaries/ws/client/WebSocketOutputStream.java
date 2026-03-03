/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.WebSocketFrame;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class WebSocketOutputStream
extends BufferedOutputStream {
    public WebSocketOutputStream(OutputStream out) {
        super(out);
    }

    public void write(String string) throws IOException {
        byte[] bytes = Misc.getBytesUTF8(string);
        this.write(bytes);
    }

    public void write(WebSocketFrame frame) throws IOException {
        this.writeFrame0(frame);
        this.writeFrame1(frame);
        this.writeFrameExtendedPayloadLength(frame);
        byte[] maskingKey = Misc.nextBytes(4);
        this.write(maskingKey);
        this.writeFramePayload(frame, maskingKey);
    }

    private void writeFrame0(WebSocketFrame frame) throws IOException {
        int b2 = (frame.getFin() ? 128 : 0) | (frame.getRsv1() ? 64 : 0) | (frame.getRsv2() ? 32 : 0) | (frame.getRsv3() ? 16 : 0) | frame.getOpcode() & 0xF;
        this.write(b2);
    }

    private void writeFrame1(WebSocketFrame frame) throws IOException {
        int b2 = 128;
        int len = frame.getPayloadLength();
        b2 = len <= 125 ? (b2 |= len) : (len <= 65535 ? (b2 |= 0x7E) : (b2 |= 0x7F));
        this.write(b2);
    }

    private void writeFrameExtendedPayloadLength(WebSocketFrame frame) throws IOException {
        byte[] buf;
        int len = frame.getPayloadLength();
        if (len <= 125) {
            return;
        }
        if (len <= 65535) {
            buf = new byte[2];
            buf[1] = (byte)(len & 0xFF);
            buf[0] = (byte)(len >> 8 & 0xFF);
        } else {
            buf = new byte[8];
            for (int i2 = 7; i2 >= 0; --i2) {
                buf[i2] = (byte)(len & 0xFF);
                len >>>= 8;
            }
        }
        this.write(buf);
    }

    private void writeFramePayload(WebSocketFrame frame, byte[] maskingKey) throws IOException {
        byte[] payload = frame.getPayload();
        if (payload == null) {
            return;
        }
        byte[] masked = new byte[payload.length];
        for (int i2 = 0; i2 < payload.length; ++i2) {
            masked[i2] = (byte)((payload[i2] ^ maskingKey[i2 % 4]) & 0xFF);
        }
        this.write(masked);
    }
}

