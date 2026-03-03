/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ft
implements ff<fj> {
    private cj a;
    private int b;
    private dn c;

    public ft() {
    }

    public ft(cj cj2, int n2, dn dn2) {
        this.a = cj2;
        this.b = n2;
        this.c = dn2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
        this.b = em2.readUnsignedByte();
        this.c = em2.h();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte((byte)this.b);
        em2.a(this.c);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public cj a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public dn c() {
        return this.c;
    }
}

