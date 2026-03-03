/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class in
implements ff<ic> {
    private int a;
    private a b;
    private aui c;

    public in() {
    }

    public in(pk pk2, a a2) {
        this.a = pk2.F();
        this.b = a2;
    }

    public in(pk pk2, aui aui2) {
        this(pk2, in$a.c);
        this.c = aui2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.a(a.class);
        if (this.b == in$a.c) {
            this.c = new aui(em2.readFloat(), em2.readFloat(), em2.readFloat());
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.a(this.b);
        if (this.b == in$a.c) {
            em2.writeFloat((float)this.c.a);
            em2.writeFloat((float)this.c.b);
            em2.writeFloat((float)this.c.c);
        }
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public pk a(adm adm2) {
        return adm2.a(this.a);
    }

    public a a() {
        return this.b;
    }

    public aui b() {
        return this.c;
    }

    public static enum a {
        a,
        b,
        c;

    }
}

