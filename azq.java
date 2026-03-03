/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonParseException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azq
extends azp {
    private static final Logger c = LogManager.getLogger();
    private final bnk d;
    private final jy e;

    public azq(azo azo2) {
        super(azo2);
        blz blz2;
        this.d = this.a.R().a;
        try {
            blz2 = new blz(this.d.a());
        }
        catch (IOException iOException) {
            blz2 = bml.a;
        }
        this.e = this.a.P().a("texturepackicon", blz2);
    }

    @Override
    protected int a() {
        return 1;
    }

    @Override
    protected String b() {
        try {
            boj boj2 = (boj)this.d.a(this.a.R().b, "pack");
            if (boj2 != null) {
                return boj2.a().d();
            }
        }
        catch (JsonParseException jsonParseException) {
            c.error("Couldn't load metadata info", (Throwable)jsonParseException);
        }
        catch (IOException iOException) {
            c.error("Couldn't load metadata info", (Throwable)iOException);
        }
        return (Object)((Object)a.m) + "Missing " + "pack.mcmeta" + " :(";
    }

    @Override
    protected boolean f() {
        return false;
    }

    @Override
    protected boolean g() {
        return false;
    }

    @Override
    protected boolean h() {
        return false;
    }

    @Override
    protected boolean i() {
        return false;
    }

    @Override
    protected String c() {
        return "Default";
    }

    @Override
    protected void d() {
        this.a.P().a(this.e);
    }

    @Override
    protected boolean e() {
        return false;
    }
}

