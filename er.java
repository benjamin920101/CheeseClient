/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class er
extends MessageToByteEncoder<ByteBuf> {
    protected void a(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        int n2 = byteBuf.readableBytes();
        \u2603 = em.a(n2);
        if (\u2603 > 3) {
            throw new IllegalArgumentException("unable to fit " + n2 + " into " + 3);
        }
        em \u26032 = new em(byteBuf2);
        \u26032.ensureWritable(\u2603 + n2);
        \u26032.b(n2);
        \u26032.writeBytes(byteBuf, byteBuf.readerIndex(), n2);
    }

    @Override
    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.a(channelHandlerContext, (ByteBuf)object, byteBuf);
    }
}

