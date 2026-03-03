/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gc
implements ff<fj> {
    private int a;
    private String b;
    private eu c;
    private int d;
    private int e;

    public gc() {
    }

    public gc(int n2, String string, eu eu2) {
        this(n2, string, eu2, 0);
    }

    public gc(int n2, String string, eu eu2, int n3) {
        this.a = n2;
        this.b = string;
        this.c = eu2;
        this.d = n3;
    }

    public gc(int n2, String string, eu eu2, int n3, int n4) {
        this(n2, string, eu2, n3);
        this.e = n4;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readUnsignedByte();
        this.b = em2.c(32);
        this.c = em2.d();
        this.d = em2.readUnsignedByte();
        if (this.b.equals("EntityHorse")) {
            this.e = em2.readInt();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.a(this.b);
        em2.a(this.c);
        em2.writeByte(this.d);
        if (this.b.equals("EntityHorse")) {
            em2.writeInt(this.e);
        }
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public eu c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean f() {
        return this.d > 0;
    }
}

