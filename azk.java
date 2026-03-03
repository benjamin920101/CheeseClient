/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azk
implements awd.a {
    private static final Logger a = LogManager.getLogger();
    private static final ThreadPoolExecutor b = new ScheduledThreadPoolExecutor(5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build());
    private static final jy c = new jy("textures/misc/unknown_server.png");
    private static final jy d = new jy("textures/gui/server_selection.png");
    private final azh e;
    private final ave f;
    private final bde g;
    private final jy h;
    private String i;
    private blz j;
    private long k;

    protected azk(azh azh2, bde bde2) {
        this.e = azh2;
        this.g = bde2;
        this.f = ave.A();
        this.h = new jy("servers/" + bde2.b + "/icon");
        this.j = (blz)this.f.P().b(this.h);
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
        String \u26036;
        boolean bl3;
        if (!this.g.h) {
            this.g.h = true;
            this.g.e = -2L;
            this.g.d = "";
            this.g.c = "";
            b.submit(new Runnable(){

                @Override
                public void run() {
                    try {
                        azk.this.e.g().a(azk.this.g);
                    }
                    catch (UnknownHostException unknownHostException) {
                        ((azk)azk.this).g.e = -1L;
                        ((azk)azk.this).g.d = (Object)((Object)a.e) + "Can't resolve hostname";
                    }
                    catch (Exception exception) {
                        ((azk)azk.this).g.e = -1L;
                        ((azk)azk.this).g.d = (Object)((Object)a.e) + "Can't connect to server.";
                    }
                }
            });
        }
        \u2603 = this.g.f > 47;
        \u2603 = this.g.f < 47;
        bl3 = \u2603 || \u2603;
        this.f.k.a(this.g.a, n3 + 32 + 3, n4 + 1, 0xFFFFFF);
        List<String> list = this.f.k.c(this.g.d, n5 - 32 - 2);
        for (int i2 = 0; i2 < Math.min(list.size(), 2); ++i2) {
            this.f.k.a(list.get(i2), n3 + 32 + 3, n4 + 12 + this.f.k.a * i2, 0x808080);
        }
        String \u26032 = bl3 ? (Object)((Object)a.e) + this.g.g : this.g.c;
        int \u26033 = this.f.k.a(\u26032);
        this.f.k.a(\u26032, n3 + n5 - \u26033 - 15 - 2, n4 + 1, 0x808080);
        int \u26034 = 0;
        String \u26035 = null;
        if (bl3) {
            int n9 = 5;
            \u26036 = \u2603 ? "Client out of date!" : "Server out of date!";
            \u26035 = this.g.i;
        } else if (this.g.h && this.g.e != -2L) {
            n9 = this.g.e < 0L ? 5 : (this.g.e < 150L ? 0 : (this.g.e < 300L ? 1 : (this.g.e < 600L ? 2 : (this.g.e < 1000L ? 3 : 4))));
            if (this.g.e < 0L) {
                \u26036 = "(no connection)";
            } else {
                \u26036 = this.g.e + "ms";
                \u26035 = this.g.i;
            }
        } else {
            \u26034 = 1;
            n9 = (int)(ave.J() / 100L + (long)(n2 * 2) & 7L);
            if (n9 > 4) {
                n9 = 8 - n9;
            }
            \u26036 = "Pinging...";
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.f.P().a(avp.d);
        avp.a(n3 + n5 - 15, n4, \u26034 * 10, 176 + n9 * 8, 10, 8, 256.0f, 256.0f);
        if (this.g.c() != null && !this.g.c().equals(this.i)) {
            this.i = this.g.c();
            this.c();
            this.e.h().b();
        }
        if (this.j != null) {
            this.a(n3, n4, this.h);
        } else {
            this.a(n3, n4, c);
        }
        \u2603 = n7 - n3;
        \u2603 = n8 - n4;
        if (\u2603 >= n5 - 15 && \u2603 <= n5 - 5 && \u2603 >= 0 && \u2603 <= 8) {
            this.e.a(\u26036);
        } else if (\u2603 >= n5 - \u26033 - 15 - 2 && \u2603 <= n5 - 15 - 2 && \u2603 >= 0 && \u2603 <= 8) {
            this.e.a(\u26035);
        }
        if (this.f.t.A || bl2) {
            this.f.P().a(d);
            avp.a(n3, n4, n3 + 32, n4 + 32, -1601138544);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            \u2603 = n7 - n3;
            \u2603 = n8 - n4;
            if (this.b()) {
                if (\u2603 < 32 && \u2603 > 16) {
                    avp.a(n3, n4, 0.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                } else {
                    avp.a(n3, n4, 0.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                }
            }
            if (this.e.a(this, n2)) {
                if (\u2603 < 16 && \u2603 < 16) {
                    avp.a(n3, n4, 96.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                } else {
                    avp.a(n3, n4, 96.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                }
            }
            if (this.e.b(this, n2)) {
                if (\u2603 < 16 && \u2603 > 16) {
                    avp.a(n3, n4, 64.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                } else {
                    avp.a(n3, n4, 64.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                }
            }
        }
    }

    protected void a(int n2, int n3, jy jy2) {
        this.f.P().a(jy2);
        bfl.l();
        avp.a(n2, n3, 0.0f, 0.0f, 32, 32, 32.0f, 32.0f);
        bfl.k();
    }

    private boolean b() {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void c() {
        if (this.g.c() == null) {
            this.f.P().c(this.h);
            this.j = null;
        } else {
            ByteBuf byteBuf = Unpooled.copiedBuffer(this.g.c(), Charsets.UTF_8);
            \u2603 = Base64.decode(byteBuf);
            try {
                BufferedImage bufferedImage = bml.a(new ByteBufInputStream(\u2603));
                Validate.validState(bufferedImage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
                Validate.validState(bufferedImage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
            }
            catch (Throwable throwable) {
                a.error("Invalid icon for server " + this.g.a + " (" + this.g.b + ")", throwable);
                this.g.a((String)null);
                return;
            }
            finally {
                byteBuf.release();
                \u2603.release();
            }
            if (this.j == null) {
                this.j = new blz(bufferedImage.getWidth(), bufferedImage.getHeight());
                this.f.P().a(this.h, (bmk)this.j);
            }
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.j.e(), 0, bufferedImage.getWidth());
            this.j.d();
        }
    }

    @Override
    public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        if (n6 <= 32) {
            if (n6 < 32 && n6 > 16 && this.b()) {
                this.e.b(n2);
                this.e.f();
                return true;
            }
            if (n6 < 16 && n7 < 16 && this.e.a(this, n2)) {
                this.e.a(this, n2, axu.r());
                return true;
            }
            if (n6 < 16 && n7 > 16 && this.e.b(this, n2)) {
                this.e.b(this, n2, axu.r());
                return true;
            }
        }
        this.e.b(n2);
        if (ave.J() - this.k < 250L) {
            this.e.f();
        }
        this.k = ave.J();
        return false;
    }

    @Override
    public void a(int n2, int n3, int n4) {
    }

    @Override
    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
    }

    public bde a() {
        return this.g;
    }
}

