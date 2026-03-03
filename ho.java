/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ho
implements ff<fj> {
    private float a;
    private int b;
    private int c;

    public ho() {
    }

    public ho(float f2, int n2, int n3) {
        this.a = f2;
        this.b = n2;
        this.c = n3;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readFloat();
        this.c = em2.e();
        this.b = em2.e();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeFloat(this.a);
        em2.b(this.c);
        em2.b(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public float a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }
}

