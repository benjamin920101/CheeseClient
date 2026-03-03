/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;

public class eq
extends ByteToMessageDecoder {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();
        byte[] byArray = new byte[3];
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            if (!byteBuf.isReadable()) {
                byteBuf.resetReaderIndex();
                return;
            }
            byArray[i2] = byteBuf.readByte();
            if (byArray[i2] < 0) continue;
            em em2 = new em(Unpooled.wrappedBuffer(byArray));
            try {
                int n2 = em2.e();
                if (byteBuf.readableBytes() < n2) {
                    byteBuf.resetReaderIndex();
                    return;
                }
                list.add(byteBuf.readBytes(n2));
                return;
            }
            finally {
                em2.release();
            }
        }
        throw new CorruptedFrameException("length wider than 21-bit");
    }
}

