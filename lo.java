/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lo
implements jk,
km {
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Logger c = LogManager.getLogger();
    private static final Random d = new Random();
    private final byte[] e = new byte[4];
    private final MinecraftServer f;
    public final ek a;
    private a g = lo$a.a;
    private int h;
    private GameProfile i;
    private String j = "";
    private SecretKey k;
    private lf l;

    public lo(MinecraftServer minecraftServer, ek ek2) {
        this.f = minecraftServer;
        this.a = ek2;
        d.nextBytes(this.e);
    }

    @Override
    public void c() {
        lf lf2;
        if (this.g == lo$a.d) {
            this.b();
        } else if (this.g == lo$a.e && (lf2 = this.f.ap().a(this.i.getId())) == null) {
            this.g = lo$a.d;
            this.f.ap().a(this.a, this.l);
            this.l = null;
        }
        if (this.h++ == 600) {
            this.a("Took too long to log in");
        }
    }

    public void a(String string) {
        try {
            c.info("Disconnecting " + this.d() + ": " + string);
            fa fa2 = new fa(string);
            this.a.a(new jj(fa2));
            this.a.a(fa2);
        }
        catch (Exception exception) {
            c.error("Error whilst disconnecting player", (Throwable)exception);
        }
    }

    public void b() {
        String string;
        if (!this.i.isComplete()) {
            this.i = this.a(this.i);
        }
        if ((string = this.f.ap().a(this.a.b(), this.i)) != null) {
            this.a(string);
        } else {
            this.g = lo$a.f;
            if (this.f.aK() >= 0 && !this.a.c()) {
                this.a.a(new ji(this.f.aK()), new ChannelFutureListener(){

                    public void a(ChannelFuture channelFuture) throws Exception {
                        lo.this.a.a(lo.this.f.aK());
                    }

                    @Override
                    public /* synthetic */ void operationComplete(Future future) throws Exception {
                        this.a((ChannelFuture)future);
                    }
                }, new GenericFutureListener[0]);
            }
            this.a.a(new jg(this.i));
            lf lf2 = this.f.ap().a(this.i.getId());
            if (lf2 != null) {
                this.g = lo$a.e;
                this.l = this.f.ap().g(this.i);
            } else {
                this.f.ap().a(this.a, this.f.ap().g(this.i));
            }
        }
    }

    @Override
    public void a(eu eu2) {
        c.info(this.d() + " lost connection: " + eu2.c());
    }

    public String d() {
        if (this.i != null) {
            return this.i.toString() + " (" + this.a.b().toString() + ")";
        }
        return String.valueOf(this.a.b());
    }

    @Override
    public void a(jl jl2) {
        Validate.validState(this.g == lo$a.a, "Unexpected hello packet", new Object[0]);
        this.i = jl2.a();
        if (this.f.af() && !this.a.c()) {
            this.g = lo$a.b;
            this.a.a(new jh(this.j, this.f.Q().getPublic(), this.e));
        } else {
            this.g = lo$a.d;
        }
    }

    @Override
    public void a(jm jm2) {
        Validate.validState(this.g == lo$a.b, "Unexpected key packet", new Object[0]);
        PrivateKey privateKey = this.f.Q().getPrivate();
        if (!Arrays.equals(this.e, jm2.b(privateKey))) {
            throw new IllegalStateException("Invalid nonce!");
        }
        this.k = jm2.a(privateKey);
        this.g = lo$a.c;
        this.a.a(this.k);
        new Thread("User Authenticator #" + b.incrementAndGet()){

            @Override
            public void run() {
                GameProfile gameProfile = lo.this.i;
                try {
                    String string = new BigInteger(ng.a(lo.this.j, lo.this.f.Q().getPublic(), lo.this.k)).toString(16);
                    lo.this.i = lo.this.f.aD().hasJoinedServer(new GameProfile(null, gameProfile.getName()), string);
                    if (lo.this.i != null) {
                        c.info("UUID of player " + lo.this.i.getName() + " is " + lo.this.i.getId());
                        lo.this.g = lo$a.d;
                    } else if (lo.this.f.T()) {
                        c.warn("Failed to verify username but will let them in anyway!");
                        lo.this.i = lo.this.a(gameProfile);
                        lo.this.g = lo$a.d;
                    } else {
                        lo.this.a("Failed to verify username!");
                        c.error("Username '" + lo.this.i.getName() + "' tried to join with an invalid session");
                    }
                }
                catch (AuthenticationUnavailableException authenticationUnavailableException) {
                    if (lo.this.f.T()) {
                        c.warn("Authentication servers are down but will let them in anyway!");
                        lo.this.i = lo.this.a(gameProfile);
                        lo.this.g = lo$a.d;
                    }
                    lo.this.a("Authentication servers are down. Please try again later, sorry!");
                    c.error("Couldn't verify username because servers are unavailable");
                }
            }
        }.start();
    }

    protected GameProfile a(GameProfile gameProfile) {
        UUID uUID = UUID.nameUUIDFromBytes(("OfflinePlayer:" + gameProfile.getName()).getBytes(Charsets.UTF_8));
        return new GameProfile(uUID, gameProfile.getName());
    }

    static enum a {
        a,
        b,
        c,
        d,
        e,
        f;

    }
}

