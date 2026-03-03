/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class ef {
    private final Cipher a;
    private byte[] b = new byte[0];
    private byte[] c = new byte[0];

    protected ef(Cipher cipher) {
        this.a = cipher;
    }

    private byte[] a(ByteBuf byteBuf) {
        int n2 = byteBuf.readableBytes();
        if (this.b.length < n2) {
            this.b = new byte[n2];
        }
        byteBuf.readBytes(this.b, 0, n2);
        return this.b;
    }

    protected ByteBuf a(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws ShortBufferException {
        int n2 = byteBuf.readableBytes();
        byte[] \u26032 = this.a(byteBuf);
        ByteBuf \u26033 = channelHandlerContext.alloc().heapBuffer(this.a.getOutputSize(n2));
        \u26033.writerIndex(this.a.update(\u26032, 0, n2, \u26033.array(), \u26033.arrayOffset()));
        return \u26033;
    }

    protected void a(ByteBuf byteBuf, ByteBuf byteBuf2) throws ShortBufferException {
        int n2 = byteBuf.readableBytes();
        byte[] \u26032 = this.a(byteBuf);
        \u2603 = this.a.getOutputSize(n2);
        if (this.c.length < \u2603) {
            this.c = new byte[\u2603];
        }
        byteBuf2.writeBytes(this.c, 0, this.a.update(\u26032, 0, n2, this.c));
    }
}

