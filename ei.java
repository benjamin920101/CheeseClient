/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import java.util.List;
import java.util.zip.Inflater;

public class ei
extends ByteToMessageDecoder {
    private final Inflater a;
    private int b;

    public ei(int n2) {
        this.b = n2;
        this.a = new Inflater();
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() == 0) {
            return;
        }
        em em2 = new em(byteBuf);
        int \u26032 = em2.e();
        if (\u26032 == 0) {
            list.add(em2.readBytes(em2.readableBytes()));
        } else {
            if (\u26032 < this.b) {
                throw new DecoderException("Badly compressed packet - size of " + \u26032 + " is below server threshold of " + this.b);
            }
            if (\u26032 > 0x200000) {
                throw new DecoderException("Badly compressed packet - size of " + \u26032 + " is larger than protocol maximum of " + 0x200000);
            }
            byte[] byArray = new byte[em2.readableBytes()];
            em2.readBytes(byArray);
            this.a.setInput(byArray);
            \u2603 = new byte[\u26032];
            this.a.inflate(\u2603);
            list.add(Unpooled.wrappedBuffer(\u2603));
            this.a.reset();
        }
    }

    public void a(int n2) {
        this.b = n2;
    }
}

