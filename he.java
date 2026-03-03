/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class he
implements ff<fj> {
    private int a;
    private oj b;
    private adp.a c;
    private adr d;

    public he() {
    }

    public he(int n2, oj oj2, adr adr2, adp.a a2) {
        this.a = n2;
        this.b = oj2;
        this.c = a2;
        this.d = adr2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readInt();
        this.b = oj.a(em2.readUnsignedByte());
        this.c = adp.a.a(em2.readUnsignedByte());
        this.d = adr.a(em2.c(16));
        if (this.d == null) {
            this.d = adr.b;
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeInt(this.a);
        em2.writeByte(this.b.a());
        em2.writeByte(this.c.a());
        em2.a(this.d.a());
    }

    public int a() {
        return this.a;
    }

    public oj b() {
        return this.b;
    }

    public adp.a c() {
        return this.c;
    }

    public adr d() {
        return this.d;
    }
}

