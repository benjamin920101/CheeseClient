/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class ex
extends es {
    private final String b;
    private final String c;
    private String d = "";

    public ex(String string, String string2) {
        this.b = string;
        this.c = string2;
    }

    public String g() {
        return this.b;
    }

    public String h() {
        return this.c;
    }

    public void b(String string) {
        this.d = string;
    }

    @Override
    public String e() {
        MinecraftServer minecraftServer = MinecraftServer.N();
        if (minecraftServer != null && minecraftServer.O() && nx.b(this.d)) {
            auo auo2 = minecraftServer.a(0).Z();
            if (auo2.b(this.b, \u2603 = auo2.b(this.c))) {
                aum aum2 = auo2.c(this.b, \u2603);
                this.b(String.format("%d", aum2.c()));
            } else {
                this.d = "";
            }
        }
        return this.d;
    }

    public ex i() {
        ex ex2 = new ex(this.b, this.c);
        ex2.b(this.d);
        ex2.a(this.b().m());
        for (eu eu2 : this.a()) {
            ex2.a(eu2.f());
        }
        return ex2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof ex) {
            ex ex2 = (ex)object;
            return this.b.equals(ex2.b) && this.c.equals(ex2.c) && super.equals(object);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ScoreComponent{name='" + this.b + '\'' + "objective='" + this.c + '\'' + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    @Override
    public /* synthetic */ eu f() {
        return this.i();
    }
}

