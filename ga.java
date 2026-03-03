/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ga
implements ff<fj> {
    private int a;
    private short b;
    private boolean c;

    public ga() {
    }

    public ga(int n2, short s2, boolean bl2) {
        this.a = n2;
        this.b = s2;
        this.c = bl2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readUnsignedByte();
        this.b = em2.readShort();
        this.c = em2.readBoolean();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.writeShort(this.b);
        em2.writeBoolean(this.c);
    }

    public int a() {
        return this.a;
    }

    public short b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }
}

