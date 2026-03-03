/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ii
implements ff<ic> {
    private int a;
    private short b;
    private boolean c;

    public ii() {
    }

    public ii(int n2, short s2, boolean bl2) {
        this.a = n2;
        this.b = s2;
        this.c = bl2;
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readByte();
        this.b = em2.readShort();
        this.c = em2.readByte() != 0;
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.writeShort(this.b);
        em2.writeByte(this.c ? 1 : 0);
    }

    public int a() {
        return this.a;
    }

    public short b() {
        return this.b;
    }
}

