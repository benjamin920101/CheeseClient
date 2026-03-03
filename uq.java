/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class uq
extends un {
    public a c;

    public uq(adm adm2) {
        super(adm2);
    }

    public uq(adm adm2, cj cj2, cq cq2) {
        super(adm2, cj2);
        ArrayList<a> arrayList = Lists.newArrayList();
        a[] \u26032 = uq$a.values();
        int \u26033 = \u26032.length;
        for (int i2 = 0; i2 < \u26033; ++i2) {
            this.c = \u2603 = \u26032[i2];
            this.a(cq2);
            if (!this.j()) continue;
            arrayList.add(\u2603);
        }
        if (!arrayList.isEmpty()) {
            this.c = (a)((Object)arrayList.get(this.V.nextInt(arrayList.size())));
        }
        this.a(cq2);
    }

    public uq(adm adm2, cj cj2, cq cq22, String string) {
        this(adm2, cj2, cq22);
        cq cq22;
        for (a a2 : uq$a.values()) {
            if (!a2.B.equals(string)) continue;
            this.c = a2;
            break;
        }
        this.a(cq22);
    }

    @Override
    public void b(dn dn2) {
        dn2.a("Motive", this.c.B);
        super.b(dn2);
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        String string = dn22.j("Motive");
        for (a a2 : uq$a.values()) {
            if (!a2.B.equals(string)) continue;
            this.c = a2;
        }
        if (this.c == null) {
            this.c = uq$a.a;
        }
        super.a(dn22);
    }

    @Override
    public int l() {
        return this.c.C;
    }

    @Override
    public int m() {
        return this.c.D;
    }

    @Override
    public void b(pk pk2) {
        if (!this.o.Q().b("doEntityDrops")) {
            return;
        }
        if (pk2 instanceof wn) {
            wn wn2 = (wn)pk2;
            if (wn2.bA.d) {
                return;
            }
        }
        this.a(new zx(zy.an), 0.0f);
    }

    @Override
    public void b(double d2, double d3, double d4, float f2, float f3) {
        cj cj2 = this.a.a(d2 - this.s, d3 - this.t, d4 - this.u);
        this.b(cj2.n(), cj2.o(), cj2.p());
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        cj cj2 = this.a.a(d2 - this.s, d3 - this.t, d4 - this.u);
        this.b(cj2.n(), cj2.o(), cj2.p());
    }

    public static enum a {
        a("Kebab", 16, 16, 0, 0),
        b("Aztec", 16, 16, 16, 0),
        c("Alban", 16, 16, 32, 0),
        d("Aztec2", 16, 16, 48, 0),
        e("Bomb", 16, 16, 64, 0),
        f("Plant", 16, 16, 80, 0),
        g("Wasteland", 16, 16, 96, 0),
        h("Pool", 32, 16, 0, 32),
        i("Courbet", 32, 16, 32, 32),
        j("Sea", 32, 16, 64, 32),
        k("Sunset", 32, 16, 96, 32),
        l("Creebet", 32, 16, 128, 32),
        m("Wanderer", 16, 32, 0, 64),
        n("Graham", 16, 32, 16, 64),
        o("Match", 32, 32, 0, 128),
        p("Bust", 32, 32, 32, 128),
        q("Stage", 32, 32, 64, 128),
        r("Void", 32, 32, 96, 128),
        s("SkullAndRoses", 32, 32, 128, 128),
        t("Wither", 32, 32, 160, 128),
        u("Fighters", 64, 32, 0, 96),
        v("Pointer", 64, 64, 0, 192),
        w("Pigscene", 64, 64, 64, 192),
        x("BurningSkull", 64, 64, 128, 192),
        y("Skeleton", 64, 48, 192, 64),
        z("DonkeyKong", 64, 48, 192, 112);

        public static final int A;
        public final String B;
        public final int C;
        public final int D;
        public final int E;
        public final int F;

        private a(String string2, int n3, int n4, int n5, int n6) {
            this.B = string2;
            this.C = n3;
            this.D = n4;
            this.E = n5;
            this.F = n6;
        }

        static {
            A = "SkullAndRoses".length();
        }
    }
}

