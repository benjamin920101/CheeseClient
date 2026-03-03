/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.InsufficientDataException;
import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.NoMoreFrameException;
import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class WebSocketInputStream
extends FilterInputStream {
    public WebSocketInputStream(InputStream in2) {
        super(in2);
    }

    public String readLine() throws IOException {
        return Misc.readLine(this, "UTF-8");
    }

    public WebSocketFrame readFrame() throws IOException, WebSocketException {
        byte[] buffer = new byte[8];
        try {
            this.readBytes(buffer, 2);
        }
        catch (InsufficientDataException e2) {
            if (e2.getReadByteCount() == 0) {
                throw new NoMoreFrameException();
            }
            throw e2;
        }
        boolean fin = (buffer[0] & 0x80) != 0;
        boolean rsv1 = (buffer[0] & 0x40) != 0;
        boolean rsv2 = (buffer[0] & 0x20) != 0;
        boolean rsv3 = (buffer[0] & 0x10) != 0;
        int opcode = buffer[0] & 0xF;
        boolean mask = (buffer[1] & 0x80) != 0;
        long payloadLength = buffer[1] & 0x7F;
        if (payloadLength == 126L) {
            this.readBytes(buffer, 2);
            payloadLength = (buffer[0] & 0xFF) << 8 | buffer[1] & 0xFF;
        } else if (payloadLength == 127L) {
            this.readBytes(buffer, 8);
            if ((buffer[0] & 0x80) != 0) {
                throw new WebSocketException(WebSocketError.INVALID_PAYLOAD_LENGTH, "The payload length of a frame is invalid.");
            }
            payloadLength = (buffer[0] & 0xFF) << 56 | (buffer[1] & 0xFF) << 48 | (buffer[2] & 0xFF) << 40 | (buffer[3] & 0xFF) << 32 | (buffer[4] & 0xFF) << 24 | (buffer[5] & 0xFF) << 16 | (buffer[6] & 0xFF) << 8 | buffer[7] & 0xFF;
        }
        byte[] maskingKey = null;
        if (mask) {
            maskingKey = new byte[4];
            this.readBytes(maskingKey, 4);
        }
        if (Integer.MAX_VALUE < payloadLength) {
            this.skipQuietly(payloadLength);
            throw new WebSocketException(WebSocketError.TOO_LONG_PAYLOAD, "The payload length of a frame exceeds the maximum array size in Java.");
        }
        byte[] payload = this.readPayload(payloadLength, mask, maskingKey);
        return new WebSocketFrame().setFin(fin).setRsv1(rsv1).setRsv2(rsv2).setRsv3(rsv3).setOpcode(opcode).setMask(mask).setPayload(payload);
    }

    void readBytes(byte[] buffer, int length) throws IOException, WebSocketException {
        int count;
        for (int total = 0; total < length; total += count) {
            count = this.read(buffer, total, length - total);
            if (count > 0) continue;
            throw new InsufficientDataException(length, total);
        }
    }

    private void skipQuietly(long length) {
        try {
            this.skip(length);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private byte[] readPayload(long payloadLength, boolean mask, byte[] maskingKey) throws IOException, WebSocketException {
        byte[] payload;
        if (payloadLength == 0L) {
            return null;
        }
        try {
            payload = new byte[(int)payloadLength];
        }
        catch (OutOfMemoryError e2) {
            this.skipQuietly(payloadLength);
            throw new WebSocketException(WebSocketError.INSUFFICIENT_MEMORY_FOR_PAYLOAD, "OutOfMemoryError occurred during a trial to allocate a memory area for a frame's payload: " + e2.getMessage(), e2);
        }
        this.readBytes(payload, payload.length);
        if (mask) {
            WebSocketFrame.mask(maskingKey, payload);
        }
        return payload;
    }
}

