/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lj
extends ChannelInboundHandlerAdapter {
    private static final Logger a = LogManager.getLogger();
    private ll b;

    public lj(ll ll2) {
        this.b = ll2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
        ByteBuf byteBuf = (ByteBuf)object;
        byteBuf.markReaderIndex();
        boolean \u26032 = true;
        try {
            if (byteBuf.readUnsignedByte() != 254) {
                return;
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress)channelHandlerContext.channel().remoteAddress();
            MinecraftServer \u26033 = this.b.d();
            int \u26034 = byteBuf.readableBytes();
            switch (\u26034) {
                case 0: {
                    a.debug("Ping: (<1.3.x) from {}:{}", inetSocketAddress.getAddress(), inetSocketAddress.getPort());
                    String string = String.format("%s\u00a7%d\u00a7%d", \u26033.am(), \u26033.I(), \u26033.J());
                    this.a(channelHandlerContext, this.a(string));
                    break;
                }
                case 1: {
                    if (byteBuf.readUnsignedByte() != 1) {
                        return;
                    }
                    a.debug("Ping: (1.4-1.5.x) from {}:{}", inetSocketAddress.getAddress(), inetSocketAddress.getPort());
                    String \u26035 = String.format("\u00a71\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, \u26033.H(), \u26033.am(), \u26033.I(), \u26033.J());
                    this.a(channelHandlerContext, this.a(\u26035));
                    break;
                }
                default: {
                    boolean \u26036 = byteBuf.readUnsignedByte() == 1;
                    \u26036 &= byteBuf.readUnsignedByte() == 250;
                    \u26036 &= "MC|PingHost".equals(new String(byteBuf.readBytes(byteBuf.readShort() * 2).array(), Charsets.UTF_16BE));
                    int \u26037 = byteBuf.readUnsignedShort();
                    \u26036 &= byteBuf.readUnsignedByte() >= 73;
                    \u26036 &= 3 + byteBuf.readBytes(byteBuf.readShort() * 2).array().length + 4 == \u26037;
                    \u26036 &= byteBuf.readInt() <= 65535;
                    if (!(\u26036 &= byteBuf.readableBytes() == 0)) {
                        return;
                    }
                    a.debug("Ping: (1.6) from {}:{}", inetSocketAddress.getAddress(), inetSocketAddress.getPort());
                    String \u26038 = String.format("\u00a71\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, \u26033.H(), \u26033.am(), \u26033.I(), \u26033.J());
                    \u2603 = this.a(\u26038);
                    try {
                        this.a(channelHandlerContext, \u2603);
                        break;
                    }
                    finally {
                        \u2603.release();
                    }
                }
            }
            byteBuf.release();
            \u26032 = false;
        }
        catch (RuntimeException runtimeException) {
        }
        finally {
            if (\u26032) {
                byteBuf.resetReaderIndex();
                channelHandlerContext.channel().pipeline().remove("legacy_query");
                channelHandlerContext.fireChannelRead(object);
            }
        }
    }

    private void a(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        channelHandlerContext.pipeline().firstContext().writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE);
    }

    private ByteBuf a(String string) {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(255);
        char[] \u26032 = string.toCharArray();
        byteBuf.writeShort(\u26032.length);
        for (char c2 : \u26032) {
            byteBuf.writeChar(c2);
        }
        return byteBuf;
    }
}

