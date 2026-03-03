/*
 * Decompiled with CFR 0.152.
 */
public class bou
implements bnj {
    private db<bov, boq> a;
    private final bmh b;
    private final bgc c;
    private boq d;

    public bou(bmh bmh2) {
        this.b = bmh2;
        this.c = new bgc(this);
    }

    @Override
    public void a(bni bni2) {
        bot bot2 = new bot(bni2, this.b, this.c);
        this.a = bot2.a();
        this.d = this.a.a(bot.a);
        this.c.c();
    }

    public boq a(bov bov2) {
        if (bov2 == null) {
            return this.d;
        }
        boq boq2 = this.a.a(bov2);
        return boq2 == null ? this.d : boq2;
    }

    public boq a() {
        return this.d;
    }

    public bmh b() {
        return this.b;
    }

    public bgc c() {
        return this.c;
    }
}

