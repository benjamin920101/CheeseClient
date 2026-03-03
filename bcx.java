/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bcx
implements jf {
    private static final Logger a = LogManager.getLogger();
    private final ave b;
    private final axu c;
    private final ek d;
    private GameProfile e;

    public bcx(ek ek2, ave ave2, axu axu2) {
        this.d = ek2;
        this.b = ave2;
        this.c = axu2;
    }

    @Override
    public void a(jh jh2) {
        final SecretKey secretKey = ng.a();
        String \u26032 = jh2.a();
        PublicKey \u26033 = jh2.b();
        String \u26034 = new BigInteger(ng.a(\u26032, \u26033, secretKey)).toString(16);
        if (this.b.D() != null && this.b.D().d()) {
            try {
                this.b().joinServer(this.b.L().e(), this.b.L().d(), \u26034);
            }
            catch (AuthenticationException authenticationException) {
                a.warn("Couldn't connect to auth servers but will continue to join LAN");
            }
        } else {
            try {
                this.b().joinServer(this.b.L().e(), this.b.L().d(), \u26034);
            }
            catch (AuthenticationUnavailableException authenticationUnavailableException) {
                this.d.a(new fb("disconnect.loginFailedInfo", new fb("disconnect.loginFailedInfo.serversUnavailable", new Object[0])));
                return;
            }
            catch (InvalidCredentialsException invalidCredentialsException) {
                this.d.a(new fb("disconnect.loginFailedInfo", new fb("disconnect.loginFailedInfo.invalidSession", new Object[0])));
                return;
            }
            catch (AuthenticationException authenticationException) {
                this.d.a(new fb("disconnect.loginFailedInfo", authenticationException.getMessage()));
                return;
            }
        }
        this.d.a(new jm(secretKey, \u26033, jh2.c()), (GenericFutureListener<? extends Future<? super Void>>)new GenericFutureListener<Future<? super Void>>(){

            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                bcx.this.d.a(secretKey);
            }
        }, new GenericFutureListener[0]);
    }

    private MinecraftSessionService b() {
        return this.b.aa();
    }

    @Override
    public void a(jg jg2) {
        this.e = jg2.a();
        this.d.a(el.b);
        this.d.a(new bcy(this.b, this.c, this.d, this.e));
    }

    @Override
    public void a(eu eu2) {
        this.b.a(new axh(this.c, "connect.failed", eu2));
    }

    @Override
    public void a(jj jj2) {
        this.d.a(jj2.a());
    }

    @Override
    public void a(ji ji2) {
        if (!this.d.c()) {
            this.d.a(ji2.a());
        }
    }
}

