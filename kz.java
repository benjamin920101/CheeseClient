/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class kz
extends le {
    private le a;

    public kz(MinecraftServer minecraftServer, atp atp2, int n2, le le2, nt nt2) {
        super(minecraftServer, atp2, new atl(le2.P()), n2, nt2);
        this.a = le2;
        le2.af().a(new amq(){

            @Override
            public void a(ams ams2, double d2) {
                kz.this.af().a(d2);
            }

            @Override
            public void a(ams ams2, double d2, double d3, long l2) {
                kz.this.af().a(d2, d3, l2);
            }

            @Override
            public void a(ams ams2, double d2, double d3) {
                kz.this.af().c(d2, d3);
            }

            @Override
            public void a(ams ams2, int n2) {
                kz.this.af().b(n2);
            }

            @Override
            public void b(ams ams2, int n2) {
                kz.this.af().c(n2);
            }

            @Override
            public void b(ams ams2, double d2) {
                kz.this.af().c(d2);
            }

            @Override
            public void c(ams ams2, double d2) {
                kz.this.af().b(d2);
            }
        });
    }

    @Override
    protected void a() {
    }

    @Override
    public adm b() {
        this.z = this.a.T();
        this.C = this.a.Z();
        String string = th.a(this.t);
        th \u26032 = (th)this.z.a(th.class, string);
        if (\u26032 == null) {
            this.A = new th(this);
            this.z.a(string, this.A);
        } else {
            this.A = \u26032;
            this.A.a(this);
        }
        return this;
    }
}

