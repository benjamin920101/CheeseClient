/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hp
implements ff<fj> {
    private float a;
    private int b;
    private float c;

    public hp() {
    }

    public hp(float f2, int n2, float f3) {
        this.a = f2;
        this.b = n2;
        this.c = f3;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readFloat();
        this.b = em2.e();
        this.c = em2.readFloat();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeFloat(this.a);
        em2.b(this.b);
        em2.writeFloat(this.c);
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

    public float c() {
        return this.c;
    }
}

