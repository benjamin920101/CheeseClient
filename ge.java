/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ge
implements ff<fj> {
    private int a;
    private int b;
    private int c;

    public ge() {
    }

    public ge(int n2, int n3, int n4) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readUnsignedByte();
        this.b = em2.readShort();
        this.c = em2.readShort();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.writeShort(this.b);
        em2.writeShort(this.c);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}

