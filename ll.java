/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ll {
    private static final Logger e = LogManager.getLogger();
    public static final no<NioEventLoopGroup> a = new no<NioEventLoopGroup>(){

        protected NioEventLoopGroup a() {
            return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Server IO #%d").setDaemon(true).build());
        }

        @Override
        protected /* synthetic */ Object b() {
            return this.a();
        }
    };
    public static final no<EpollEventLoopGroup> b = new no<EpollEventLoopGroup>(){

        protected EpollEventLoopGroup a() {
            return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
        }

        @Override
        protected /* synthetic */ Object b() {
            return this.a();
        }
    };
    public static final no<LocalEventLoopGroup> c = new no<LocalEventLoopGroup>(){

        protected LocalEventLoopGroup a() {
            return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
        }

        @Override
        protected /* synthetic */ Object b() {
            return this.a();
        }
    };
    private final MinecraftServer f;
    public volatile boolean d;
    private final List<ChannelFuture> g = Collections.synchronizedList(Lists.newArrayList());
    private final List<ek> h = Collections.synchronizedList(Lists.newArrayList());

    public ll(MinecraftServer minecraftServer) {
        this.f = minecraftServer;
        this.d = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(InetAddress inetAddress, int n2) throws IOException {
        List<ChannelFuture> list = this.g;
        synchronized (list) {
            no<MultithreadEventLoopGroup> \u26032;
            if (Epoll.isAvailable() && this.f.ai()) {
                Class clazz = EpollServerSocketChannel.class;
                \u26032 = b;
                e.info("Using epoll channel type");
            } else {
                clazz = NioServerSocketChannel.class;
                \u26032 = a;
                e.info("Using default channel type");
            }
            this.g.add(((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(clazz)).childHandler(new ChannelInitializer<Channel>(){

                @Override
                protected void initChannel(Channel channel) throws Exception {
                    try {
                        channel.config().setOption(ChannelOption.TCP_NODELAY, true);
                    }
                    catch (ChannelException channelException) {
                        // empty catch block
                    }
                    channel.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("legacy_query", (ChannelHandler)new lj(ll.this)).addLast("splitter", (ChannelHandler)new eq()).addLast("decoder", (ChannelHandler)new en(fg.a)).addLast("prepender", (ChannelHandler)new er()).addLast("encoder", (ChannelHandler)new eo(fg.b));
                    ek ek2 = new ek(fg.a);
                    ll.this.h.add(ek2);
                    channel.pipeline().addLast("packet_handler", (ChannelHandler)ek2);
                    ek2.a(new ln(ll.this.f, ek2));
                }
            }).group(\u26032.c()).localAddress(inetAddress, n2)).bind().syncUninterruptibly());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SocketAddress a() {
        ChannelFuture channelFuture;
        List<ChannelFuture> list = this.g;
        synchronized (list) {
            channelFuture = ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(LocalServerChannel.class)).childHandler(new ChannelInitializer<Channel>(){

                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ek ek2 = new ek(fg.a);
                    ek2.a(new lk(ll.this.f, ek2));
                    ll.this.h.add(ek2);
                    channel.pipeline().addLast("packet_handler", (ChannelHandler)ek2);
                }
            }).group(a.c()).localAddress(LocalAddress.ANY)).bind().syncUninterruptibly();
            this.g.add(channelFuture);
        }
        return channelFuture.channel().localAddress();
    }

    public void b() {
        this.d = false;
        for (ChannelFuture channelFuture : this.g) {
            try {
                channelFuture.channel().close().sync();
            }
            catch (InterruptedException interruptedException) {
                e.error("Interrupted whilst closing channel");
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void c() {
        List<ek> list = this.h;
        synchronized (list) {
            Iterator<ek> iterator = this.h.iterator();
            while (iterator.hasNext()) {
                final ek ek2 = iterator.next();
                if (ek2.h()) continue;
                if (!ek2.g()) {
                    iterator.remove();
                    ek2.l();
                    continue;
                }
                try {
                    ek2.a();
                }
                catch (Exception \u26032) {
                    if (ek2.c()) {
                        Object object = b.a(\u26032, "Ticking memory connection");
                        c \u26033 = ((b)object).a("Ticking connection");
                        \u26033.a("Connection", new Callable<String>(){

                            public String a() throws Exception {
                                return ek2.toString();
                            }

                            @Override
                            public /* synthetic */ Object call() throws Exception {
                                return this.a();
                            }
                        });
                        throw new e((b)object);
                    }
                    e.warn("Failed to handle packet for " + ek2.b(), (Throwable)\u26032);
                    object = new fa("Internal server error");
                    ek2.a(new gh((eu)object), (GenericFutureListener<? extends Future<? super Void>>)new GenericFutureListener<Future<? super Void>>((fa)object){
                        final /* synthetic */ fa b;
                        {
                            this.b = fa2;
                        }

                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            ek2.a(this.b);
                        }
                    }, new GenericFutureListener[0]);
                    ek2.k();
                }
            }
        }
    }

    public MinecraftServer d() {
        return this.f;
    }
}

