/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ip
implements ff<ic> {
    protected double a;
    protected double b;
    protected double c;
    protected float d;
    protected float e;
    protected boolean f;
    protected boolean g;
    protected boolean h;

    public ip() {
    }

    public ip(boolean bl2) {
        this.f = bl2;
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.f = em2.readUnsignedByte() != 0;
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.f ? 1 : 0);
    }

    public double a() {
        return this.a;
    }

    public double b() {
        return this.b;
    }

    public double c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }

    public float e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    @Override
    public void a(boolean bl2) {
        this.g = bl2;
    }

    public static class c
    extends ip {
        public c() {
            this.h = true;
        }

        public c(float f2, float f3, boolean bl2) {
            this.d = f2;
            this.e = f3;
            this.f = bl2;
            this.h = true;
        }

        @Override
        public void a(em em2) throws IOException {
            this.d = em2.readFloat();
            this.e = em2.readFloat();
            super.a(em2);
        }

        @Override
        public void b(em em2) throws IOException {
            em2.writeFloat(this.d);
            em2.writeFloat(this.e);
            super.b(em2);
        }
    }

    public static class a
    extends ip {
        public a() {
            this.g = true;
        }

        public a(double d2, double d3, double d4, boolean bl2) {
            this.a = d2;
            this.b = d3;
            this.c = d4;
            this.f = bl2;
            this.g = true;
        }

        @Override
        public void a(em em2) throws IOException {
            this.a = em2.readDouble();
            this.b = em2.readDouble();
            this.c = em2.readDouble();
            super.a(em2);
        }

        @Override
        public void b(em em2) throws IOException {
            em2.writeDouble(this.a);
            em2.writeDouble(this.b);
            em2.writeDouble(this.c);
            super.b(em2);
        }
    }

    public static class b
    extends ip {
        public b() {
            this.g = true;
            this.h = true;
        }

        public b(double d2, double d3, double d4, float f2, float f3, boolean bl2) {
            this.a = d2;
            this.b = d3;
            this.c = d4;
            this.d = f2;
            this.e = f3;
            this.f = bl2;
            this.h = true;
            this.g = true;
        }

        @Override
        public void a(em em2) throws IOException {
            this.a = em2.readDouble();
            this.b = em2.readDouble();
            this.c = em2.readDouble();
            this.d = em2.readFloat();
            this.e = em2.readFloat();
            super.a(em2);
        }

        @Override
        public void b(em em2) throws IOException {
            em2.writeDouble(this.a);
            em2.writeDouble(this.b);
            em2.writeDouble(this.c);
            em2.writeFloat(this.d);
            em2.writeFloat(this.e);
            super.b(em2);
        }
    }
}

