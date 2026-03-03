/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class ej
extends MessageToByteEncoder<ByteBuf> {
    private final byte[] a = new byte[8192];
    private final Deflater b;
    private int c;

    public ej(int n2) {
        this.c = n2;
        this.b = new Deflater();
    }

    protected void a(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        int n2 = byteBuf.readableBytes();
        em \u26032 = new em(byteBuf2);
        if (n2 < this.c) {
            \u26032.b(0);
            \u26032.writeBytes(byteBuf);
        } else {
            byte[] byArray = new byte[n2];
            byteBuf.readBytes(byArray);
            \u26032.b(byArray.length);
            this.b.setInput(byArray, 0, n2);
            this.b.finish();
            while (!this.b.finished()) {
                int n3 = this.b.deflate(this.a);
                \u26032.writeBytes(this.a, 0, n3);
            }
            this.b.reset();
        }
    }

    public void a(int n2) {
        this.c = n2;
    }

    @Override
    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.a(channelHandlerContext, (ByteBuf)object, byteBuf);
    }
}

