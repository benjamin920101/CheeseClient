/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gr
implements ff<fj> {
    private cy a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;
    private boolean j;
    private int[] k;

    public gr() {
    }

    public gr(cy cy2, boolean bl2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n2, int ... nArray) {
        this.a = cy2;
        this.j = bl2;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
        this.g = f7;
        this.h = f8;
        this.i = n2;
        this.k = nArray;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = cy.a(em2.readInt());
        if (this.a == null) {
            this.a = cy.J;
        }
        this.j = em2.readBoolean();
        this.b = em2.readFloat();
        this.c = em2.readFloat();
        this.d = em2.readFloat();
        this.e = em2.readFloat();
        this.f = em2.readFloat();
        this.g = em2.readFloat();
        this.h = em2.readFloat();
        this.i = em2.readInt();
        int n2 = this.a.d();
        this.k = new int[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            this.k[\u2603] = em2.e();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeInt(this.a.c());
        em2.writeBoolean(this.j);
        em2.writeFloat(this.b);
        em2.writeFloat(this.c);
        em2.writeFloat(this.d);
        em2.writeFloat(this.e);
        em2.writeFloat(this.f);
        em2.writeFloat(this.g);
        em2.writeFloat(this.h);
        em2.writeInt(this.i);
        int n2 = this.a.d();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            em2.b(this.k[\u2603]);
        }
    }

    public cy a() {
        return this.a;
    }

    public boolean b() {
        return this.j;
    }

    public double c() {
        return this.b;
    }

    public double d() {
        return this.c;
    }

    public double e() {
        return this.d;
    }

    public float f() {
        return this.e;
    }

    public float g() {
        return this.f;
    }

    public float h() {
        return this.g;
    }

    public float i() {
        return this.h;
    }

    public int j() {
        return this.i;
    }

    public int[] k() {
        return this.k;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }
}

