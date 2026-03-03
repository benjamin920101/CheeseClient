/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bpv
implements km {
    private final Random a = new Random();
    private final ave b;
    private bpj c;
    private int d = 100;

    public bpv(ave ave2) {
        this.b = ave2;
    }

    @Override
    public void c() {
        a a2 = this.b.X();
        if (this.c != null) {
            if (!a2.a().equals(this.c.a())) {
                this.b.W().b(this.c);
                this.d = ns.a(this.a, 0, a2.b() / 2);
            }
            if (!this.b.W().c(this.c)) {
                this.c = null;
                this.d = Math.min(ns.a(this.a, a2.b(), a2.c()), this.d);
            }
        }
        if (this.c == null && this.d-- <= 0) {
            this.a(a2);
        }
    }

    public void a(a a2) {
        this.c = bpf.a(a2.a());
        this.b.W().a(this.c);
        this.d = Integer.MAX_VALUE;
    }

    public void a() {
        if (this.c != null) {
            this.b.W().b(this.c);
            this.c = null;
            this.d = 0;
        }
    }

    public static enum a {
        a(new jy("minecraft:music.menu"), 20, 600),
        b(new jy("minecraft:music.game"), 12000, 24000),
        c(new jy("minecraft:music.game.creative"), 1200, 3600),
        d(new jy("minecraft:music.game.end.credits"), Integer.MAX_VALUE, Integer.MAX_VALUE),
        e(new jy("minecraft:music.game.nether"), 1200, 3600),
        f(new jy("minecraft:music.game.end.dragon"), 0, 0),
        g(new jy("minecraft:music.game.end"), 6000, 24000);

        private final jy h;
        private final int i;
        private final int j;

        private a(jy jy2, int n3, int n4) {
            this.h = jy2;
            this.i = n3;
            this.j = n4;
        }

        public jy a() {
            return this.h;
        }

        public int b() {
            return this.i;
        }

        public int c() {
            return this.j;
        }
    }
}

