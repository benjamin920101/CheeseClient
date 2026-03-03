/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class eo
extends MessageToByteEncoder<ff> {
    private static final Logger a = LogManager.getLogger();
    private static final Marker b = MarkerManager.getMarker("PACKET_SENT", ek.b);
    private final fg c;

    public eo(fg fg2) {
        this.c = fg2;
    }

    protected void a(ChannelHandlerContext channelHandlerContext, ff ff2, ByteBuf byteBuf) throws Exception {
        Integer n2 = channelHandlerContext.channel().attr(ek.c).get().a(this.c, ff2);
        if (a.isDebugEnabled()) {
            a.debug(b, "OUT: [{}:{}] {}", new Object[]{channelHandlerContext.channel().attr(ek.c).get(), n2, ff2.getClass().getName()});
        }
        if (n2 == null) {
            throw new IOException("Can't serialize unregistered packet");
        }
        em \u26032 = new em(byteBuf);
        \u26032.b(n2);
        try {
            if (ff2 instanceof fp) {
                // empty if block
            }
            ff2.b(\u26032);
        }
        catch (Throwable \u26033) {
            a.error(\u26033);
        }
    }

    @Override
    protected /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        this.a(channelHandlerContext, (ff)object, byteBuf);
    }
}

