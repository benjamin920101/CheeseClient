/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bdg {
    private static final Splitter a = Splitter.on('\u0000').limit(6);
    private static final Logger b = LogManager.getLogger();
    private final List<ek> c = Collections.synchronizedList(Lists.newArrayList());

    public void a(final bde bde2) throws UnknownHostException {
        bdd bdd2 = bdd.a(bde2.b);
        final ek \u26032 = ek.a(InetAddress.getByName(bdd2.a()), bdd2.b(), false);
        this.c.add(\u26032);
        bde2.d = "Pinging...";
        bde2.e = -1L;
        bde2.i = null;
        \u26032.a(new jp(){
            private boolean d = false;
            private boolean e = false;
            private long f = 0L;

            @Override
            public void a(jr jr2) {
                CharSequence charSequence;
                if (this.e) {
                    \u26032.a(new fa("Received unrequested status"));
                    return;
                }
                this.e = true;
                js js2 = jr2.a();
                bde2.d = js2.a() != null ? js2.a().d() : "";
                if (js2.c() != null) {
                    bde2.g = js2.c().a();
                    bde2.f = js2.c().b();
                } else {
                    bde2.g = "Old";
                    bde2.f = 0;
                }
                if (js2.b() != null) {
                    bde2.c = (Object)((Object)a.h) + "" + js2.b().b() + "" + (Object)((Object)a.i) + "/" + (Object)((Object)a.h) + js2.b().a();
                    if (ArrayUtils.isNotEmpty(js2.b().c())) {
                        charSequence = new StringBuilder();
                        for (GameProfile gameProfile : js2.b().c()) {
                            if (((StringBuilder)charSequence).length() > 0) {
                                ((StringBuilder)charSequence).append("\n");
                            }
                            ((StringBuilder)charSequence).append(gameProfile.getName());
                        }
                        if (js2.b().c().length < js2.b().b()) {
                            if (((StringBuilder)charSequence).length() > 0) {
                                ((StringBuilder)charSequence).append("\n");
                            }
                            ((StringBuilder)charSequence).append("... and ").append(js2.b().b() - js2.b().c().length).append(" more ...");
                        }
                        bde2.i = ((StringBuilder)charSequence).toString();
                    }
                } else {
                    bde2.c = (Object)((Object)a.i) + "???";
                }
                if (js2.d() != null) {
                    charSequence = js2.d();
                    if (((String)charSequence).startsWith("data:image/png;base64,")) {
                        bde2.a(((String)charSequence).substring("data:image/png;base64,".length()));
                    } else {
                        b.error("Invalid server icon (unknown format)");
                    }
                } else {
                    bde2.a((String)null);
                }
                this.f = ave.J();
                \u26032.a(new ju(this.f));
                this.d = true;
            }

            @Override
            public void a(jq jq2) {
                long l2 = this.f;
                \u2603 = ave.J();
                bde2.e = \u2603 - l2;
                \u26032.a(new fa("Finished"));
            }

            @Override
            public void a(eu eu2) {
                if (!this.d) {
                    b.error("Can't ping " + bde2.b + ": " + eu2.c());
                    bde2.d = (Object)((Object)a.e) + "Can't connect to server.";
                    bde2.c = "";
                    bdg.this.b(bde2);
                }
            }
        });
        try {
            \u26032.a(new jc(47, bdd2.a(), bdd2.b(), el.c));
            \u26032.a(new jv());
        }
        catch (Throwable \u26033) {
            b.error(\u26033);
        }
    }

    private void b(final bde bde2) {
        final bdd bdd2 = bdd.a(bde2.b);
        ((Bootstrap)((Bootstrap)((Bootstrap)new Bootstrap().group(ek.d.c())).handler(new ChannelInitializer<Channel>(){

            @Override
            protected void initChannel(Channel channel) throws Exception {
                try {
                    channel.config().setOption(ChannelOption.TCP_NODELAY, true);
                }
                catch (ChannelException channelException) {
                    // empty catch block
                }
                channel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>(){

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     */
                    @Override
                    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
                        super.channelActive(channelHandlerContext);
                        ByteBuf byteBuf = Unpooled.buffer();
                        try {
                            byteBuf.writeByte(254);
                            byteBuf.writeByte(1);
                            byteBuf.writeByte(250);
                            char[] \u26032 = "MC|PingHost".toCharArray();
                            byteBuf.writeShort(\u26032.length);
                            for (char c2 : \u26032) {
                                byteBuf.writeChar(c2);
                            }
                            byteBuf.writeShort(7 + 2 * bdd2.a().length());
                            byteBuf.writeByte(127);
                            \u26032 = bdd2.a().toCharArray();
                            byteBuf.writeShort(\u26032.length);
                            for (char c2 : \u26032) {
                                byteBuf.writeChar(c2);
                            }
                            byteBuf.writeInt(bdd2.b());
                            channelHandlerContext.channel().writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                        }
                        finally {
                            byteBuf.release();
                        }
                    }

                    protected void a(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        short s2 = byteBuf.readUnsignedByte();
                        if (s2 == 255) {
                            String string = new String(byteBuf.readBytes(byteBuf.readShort() * 2).array(), Charsets.UTF_16BE);
                            String[] \u26032 = Iterables.toArray(a.split(string), String.class);
                            if ("\u00a71".equals(\u26032[0])) {
                                int n2 = ns.a(\u26032[1], 0);
                                String \u26033 = \u26032[2];
                                String \u26034 = \u26032[3];
                                \u2603 = ns.a(\u26032[4], -1);
                                \u2603 = ns.a(\u26032[5], -1);
                                bde2.f = -1;
                                bde2.g = \u26033;
                                bde2.d = \u26034;
                                bde2.c = (Object)((Object)a.h) + "" + \u2603 + "" + (Object)((Object)a.i) + "/" + (Object)((Object)a.h) + \u2603;
                            }
                        }
                        channelHandlerContext.close();
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
                        channelHandlerContext.close();
                    }

                    @Override
                    protected /* synthetic */ void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
                        this.a(channelHandlerContext, (ByteBuf)object);
                    }
                });
            }
        })).channel(NioSocketChannel.class)).connect(bdd2.a(), bdd2.b());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() {
        List<ek> list = this.c;
        synchronized (list) {
            Iterator<ek> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                ek ek2 = iterator.next();
                if (ek2.g()) {
                    ek2.a();
                    continue;
                }
                iterator.remove();
                ek2.l();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b() {
        List<ek> list = this.c;
        synchronized (list) {
            Iterator<ek> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                ek ek2 = iterator.next();
                if (!ek2.g()) continue;
                iterator.remove();
                ek2.a(new fa("Cancelled"));
            }
        }
    }
}

