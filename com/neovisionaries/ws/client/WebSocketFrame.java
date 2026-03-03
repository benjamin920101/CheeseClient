/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Misc;
import com.neovisionaries.ws.client.PerMessageCompressionExtension;
import com.neovisionaries.ws.client.WebSocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WebSocketFrame {
    private boolean mFin;
    private boolean mRsv1;
    private boolean mRsv2;
    private boolean mRsv3;
    private int mOpcode;
    private boolean mMask;
    private byte[] mPayload;

    public boolean getFin() {
        return this.mFin;
    }

    public WebSocketFrame setFin(boolean fin) {
        this.mFin = fin;
        return this;
    }

    public boolean getRsv1() {
        return this.mRsv1;
    }

    public WebSocketFrame setRsv1(boolean rsv1) {
        this.mRsv1 = rsv1;
        return this;
    }

    public boolean getRsv2() {
        return this.mRsv2;
    }

    public WebSocketFrame setRsv2(boolean rsv2) {
        this.mRsv2 = rsv2;
        return this;
    }

    public boolean getRsv3() {
        return this.mRsv3;
    }

    public WebSocketFrame setRsv3(boolean rsv3) {
        this.mRsv3 = rsv3;
        return this;
    }

    public int getOpcode() {
        return this.mOpcode;
    }

    public WebSocketFrame setOpcode(int opcode) {
        this.mOpcode = opcode;
        return this;
    }

    public boolean isContinuationFrame() {
        return this.mOpcode == 0;
    }

    public boolean isTextFrame() {
        return this.mOpcode == 1;
    }

    public boolean isBinaryFrame() {
        return this.mOpcode == 2;
    }

    public boolean isCloseFrame() {
        return this.mOpcode == 8;
    }

    public boolean isPingFrame() {
        return this.mOpcode == 9;
    }

    public boolean isPongFrame() {
        return this.mOpcode == 10;
    }

    public boolean isDataFrame() {
        return 1 <= this.mOpcode && this.mOpcode <= 7;
    }

    public boolean isControlFrame() {
        return 8 <= this.mOpcode && this.mOpcode <= 15;
    }

    boolean getMask() {
        return this.mMask;
    }

    WebSocketFrame setMask(boolean mask) {
        this.mMask = mask;
        return this;
    }

    public boolean hasPayload() {
        return this.mPayload != null;
    }

    public int getPayloadLength() {
        if (this.mPayload == null) {
            return 0;
        }
        return this.mPayload.length;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public String getPayloadText() {
        if (this.mPayload == null) {
            return null;
        }
        return Misc.toStringUTF8(this.mPayload);
    }

    public WebSocketFrame setPayload(byte[] payload) {
        if (payload != null && payload.length == 0) {
            payload = null;
        }
        this.mPayload = payload;
        return this;
    }

    public WebSocketFrame setPayload(String payload) {
        if (payload == null || payload.length() == 0) {
            return this.setPayload((byte[])null);
        }
        return this.setPayload(Misc.getBytesUTF8(payload));
    }

    public WebSocketFrame setCloseFramePayload(int closeCode, String reason) {
        byte[] encodedCloseCode = new byte[]{(byte)(closeCode >> 8 & 0xFF), (byte)(closeCode & 0xFF)};
        if (reason == null || reason.length() == 0) {
            return this.setPayload(encodedCloseCode);
        }
        byte[] encodedReason = Misc.getBytesUTF8(reason);
        byte[] payload = new byte[2 + encodedReason.length];
        System.arraycopy(encodedCloseCode, 0, payload, 0, 2);
        System.arraycopy(encodedReason, 0, payload, 2, encodedReason.length);
        return this.setPayload(payload);
    }

    public int getCloseCode() {
        if (this.mPayload == null || this.mPayload.length < 2) {
            return 1005;
        }
        int closeCode = (this.mPayload[0] & 0xFF) << 8 | this.mPayload[1] & 0xFF;
        return closeCode;
    }

    public String getCloseReason() {
        if (this.mPayload == null || this.mPayload.length < 3) {
            return null;
        }
        return Misc.toStringUTF8(this.mPayload, 2, this.mPayload.length - 2);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder().append("WebSocketFrame(FIN=").append(this.mFin ? "1" : "0").append(",RSV1=").append(this.mRsv1 ? "1" : "0").append(",RSV2=").append(this.mRsv2 ? "1" : "0").append(",RSV3=").append(this.mRsv3 ? "1" : "0").append(",Opcode=").append(Misc.toOpcodeName(this.mOpcode)).append(",Length=").append(this.getPayloadLength());
        switch (this.mOpcode) {
            case 1: {
                this.appendPayloadText(builder);
                break;
            }
            case 2: {
                this.appendPayloadBinary(builder);
                break;
            }
            case 8: {
                this.appendPayloadClose(builder);
            }
        }
        return builder.append(")").toString();
    }

    private boolean appendPayloadCommon(StringBuilder builder) {
        builder.append(",Payload=");
        if (this.mPayload == null) {
            builder.append("null");
            return true;
        }
        if (this.mRsv1) {
            builder.append("compressed");
            return true;
        }
        return false;
    }

    private void appendPayloadText(StringBuilder builder) {
        if (this.appendPayloadCommon(builder)) {
            return;
        }
        builder.append("\"");
        builder.append(this.getPayloadText());
        builder.append("\"");
    }

    private void appendPayloadClose(StringBuilder builder) {
        builder.append(",CloseCode=").append(this.getCloseCode()).append(",Reason=");
        String reason = this.getCloseReason();
        if (reason == null) {
            builder.append("null");
        } else {
            builder.append("\"").append(reason).append("\"");
        }
    }

    private void appendPayloadBinary(StringBuilder builder) {
        if (this.appendPayloadCommon(builder)) {
            return;
        }
        for (int i2 = 0; i2 < this.mPayload.length; ++i2) {
            builder.append(String.format("%02X ", 0xFF & this.mPayload[i2]));
        }
        if (this.mPayload.length != 0) {
            builder.setLength(builder.length() - 1);
        }
    }

    public static WebSocketFrame createContinuationFrame() {
        return new WebSocketFrame().setOpcode(0);
    }

    public static WebSocketFrame createContinuationFrame(byte[] payload) {
        return WebSocketFrame.createContinuationFrame().setPayload(payload);
    }

    public static WebSocketFrame createContinuationFrame(String payload) {
        return WebSocketFrame.createContinuationFrame().setPayload(payload);
    }

    public static WebSocketFrame createTextFrame(String payload) {
        return new WebSocketFrame().setFin(true).setOpcode(1).setPayload(payload);
    }

    public static WebSocketFrame createBinaryFrame(byte[] payload) {
        return new WebSocketFrame().setFin(true).setOpcode(2).setPayload(payload);
    }

    public static WebSocketFrame createCloseFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(8);
    }

    public static WebSocketFrame createCloseFrame(int closeCode) {
        return WebSocketFrame.createCloseFrame().setCloseFramePayload(closeCode, null);
    }

    public static WebSocketFrame createCloseFrame(int closeCode, String reason) {
        return WebSocketFrame.createCloseFrame().setCloseFramePayload(closeCode, reason);
    }

    public static WebSocketFrame createPingFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(9);
    }

    public static WebSocketFrame createPingFrame(byte[] payload) {
        return WebSocketFrame.createPingFrame().setPayload(payload);
    }

    public static WebSocketFrame createPingFrame(String payload) {
        return WebSocketFrame.createPingFrame().setPayload(payload);
    }

    public static WebSocketFrame createPongFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(10);
    }

    public static WebSocketFrame createPongFrame(byte[] payload) {
        return WebSocketFrame.createPongFrame().setPayload(payload);
    }

    public static WebSocketFrame createPongFrame(String payload) {
        return WebSocketFrame.createPongFrame().setPayload(payload);
    }

    static byte[] mask(byte[] maskingKey, byte[] payload) {
        if (maskingKey == null || maskingKey.length < 4 || payload == null) {
            return payload;
        }
        for (int i2 = 0; i2 < payload.length; ++i2) {
            int n2 = i2;
            payload[n2] = (byte)(payload[n2] ^ maskingKey[i2 % 4]);
        }
        return payload;
    }

    static WebSocketFrame compressFrame(WebSocketFrame frame, PerMessageCompressionExtension pmce) {
        if (pmce == null) {
            return frame;
        }
        if (!frame.isTextFrame() && !frame.isBinaryFrame()) {
            return frame;
        }
        if (!frame.getFin()) {
            return frame;
        }
        if (frame.getRsv1()) {
            return frame;
        }
        byte[] payload = frame.getPayload();
        if (payload == null || payload.length == 0) {
            return frame;
        }
        byte[] compressed = WebSocketFrame.compress(payload, pmce);
        if (payload.length <= compressed.length) {
            return frame;
        }
        frame.setPayload(compressed);
        frame.setRsv1(true);
        return frame;
    }

    private static byte[] compress(byte[] data, PerMessageCompressionExtension pmce) {
        try {
            return pmce.compress(data);
        }
        catch (WebSocketException e2) {
            return data;
        }
    }

    static List<WebSocketFrame> splitIfNecessary(WebSocketFrame frame, int maxPayloadSize, PerMessageCompressionExtension pmce) {
        if (maxPayloadSize == 0) {
            return null;
        }
        if (frame.getPayloadLength() <= maxPayloadSize) {
            return null;
        }
        if (frame.isBinaryFrame() || frame.isTextFrame() ? (frame = WebSocketFrame.compressFrame(frame, pmce)).getPayloadLength() <= maxPayloadSize : !frame.isContinuationFrame()) {
            return null;
        }
        return WebSocketFrame.split(frame, maxPayloadSize);
    }

    private static List<WebSocketFrame> split(WebSocketFrame frame, int maxPayloadSize) {
        byte[] originalPayload = frame.getPayload();
        boolean originalFin = frame.getFin();
        ArrayList<WebSocketFrame> frames = new ArrayList<WebSocketFrame>();
        byte[] payload = Arrays.copyOf(originalPayload, maxPayloadSize);
        frame.setFin(false).setPayload(payload);
        frames.add(frame);
        for (int from = maxPayloadSize; from < originalPayload.length; from += maxPayloadSize) {
            int to2 = Math.min(from + maxPayloadSize, originalPayload.length);
            payload = Arrays.copyOfRange(originalPayload, from, to2);
            WebSocketFrame cont = WebSocketFrame.createContinuationFrame(payload);
            frames.add(cont);
        }
        if (originalFin) {
            ((WebSocketFrame)frames.get(frames.size() - 1)).setFin(true);
        }
        return frames;
    }
}

