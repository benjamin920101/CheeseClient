/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fy
implements ff<fj> {
    private eu a;
    private byte b;

    public fy() {
    }

    public fy(eu eu2) {
        this(eu2, 1);
    }

    public fy(eu eu2, byte by) {
        this.a = eu2;
        this.b = by;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.d();
        this.b = em2.readByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public eu a() {
        return this.a;
    }

    public boolean b() {
        return this.b == 1 || this.b == 2;
    }

    public byte c() {
        return this.b;
    }
}

