/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ir
implements ff<ic> {
    private cj a;
    private cq b;
    private a c;

    public ir() {
    }

    public ir(a a2, cj cj2, cq cq2) {
        this.c = a2;
        this.a = cj2;
        this.b = cq2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.c = em2.a(a.class);
        this.a = em2.c();
        this.b = cq.a(em2.readUnsignedByte());
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.c);
        em2.a(this.a);
        em2.writeByte(this.b.a());
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public cj a() {
        return this.a;
    }

    public cq b() {
        return this.b;
    }

    public a c() {
        return this.c;
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e,
        f;

    }
}

