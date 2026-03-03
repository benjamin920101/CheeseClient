/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class bkm
extends bje<we> {
    private static final jy j = new jy("textures/entity/zombie/zombie.png");
    private static final jy k = new jy("textures/entity/zombie/zombie_villager.png");
    private final bbj l;
    private final bcj m;
    private final List<blb<we>> n;
    private final List<blb<we>> o;

    public bkm(biu biu2) {
        super(biu2, new bcn(), 0.5f, 1.0f);
        blb blb2 = (blb)this.h.get(0);
        this.l = this.a;
        this.m = new bcj();
        this.a(new bky(this));
        bkx \u26032 = new bkx(this){

            @Override
            protected void a() {
                this.c = new bcn(0.5f, true);
                this.d = new bcn(1.0f, true);
            }
        };
        this.a(\u26032);
        this.o = Lists.newArrayList(this.h);
        if (blb2 instanceof bks) {
            this.b(blb2);
            this.a(new bks(this.m.e));
        }
        this.b(\u26032);
        this.a(new blg(this));
        this.n = Lists.newArrayList(this.h);
    }

    @Override
    public void a(we we2, double d2, double d3, double d4, float f2, float f3) {
        this.b(we2);
        super.a(we2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(we we2) {
        if (we2.co()) {
            return k;
        }
        return j;
    }

    private void b(we we2) {
        if (we2.co()) {
            this.f = this.m;
            this.h = this.n;
        } else {
            this.f = this.l;
            this.h = this.o;
        }
        this.a = (bbj)this.f;
    }

    @Override
    protected void a(we we2, float f2, float f3, float f4) {
        if (we2.cp()) {
            f3 += (float)(Math.cos((double)we2.W * 3.25) * Math.PI * 0.25);
        }
        super.a(we2, f2, f3, f4);
    }
}

