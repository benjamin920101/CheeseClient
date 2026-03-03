/*
 * Decompiled with CFR 0.152.
 */
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;

public class bfw {
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public int f;
    public int g;
    public int h;
    public float[] i;
    public int j;

    public bfw(int n2, int n3, boolean bl2) {
        this.e = bl2;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = new float[4];
        this.i[0] = 1.0f;
        this.i[1] = 1.0f;
        this.i[2] = 1.0f;
        this.i[3] = 0.0f;
        this.a(n2, n3);
    }

    public void a(int n2, int n3) {
        if (!bqs.i()) {
            this.c = n2;
            this.d = n3;
            return;
        }
        bfl.j();
        if (this.f >= 0) {
            this.a();
        }
        this.b(n2, n3);
        this.b();
        bqs.h(bqs.c, 0);
    }

    public void a() {
        if (!bqs.i()) {
            return;
        }
        this.d();
        this.e();
        if (this.h > -1) {
            bqs.h(this.h);
            this.h = -1;
        }
        if (this.g > -1) {
            bml.a(this.g);
            this.g = -1;
        }
        if (this.f > -1) {
            bqs.h(bqs.c, 0);
            bqs.i(this.f);
            this.f = -1;
        }
    }

    public void b(int n2, int n3) {
        this.c = n2;
        this.d = n3;
        this.a = n2;
        this.b = n3;
        if (!bqs.i()) {
            this.f();
            return;
        }
        this.f = bqs.g();
        this.g = bml.a();
        if (this.e) {
            this.h = bqs.h();
        }
        this.a(9728);
        bfl.i(this.g);
        GL11.glTexImage2D(3553, 0, 32856, this.a, this.b, 0, 6408, 5121, (ByteBuffer)null);
        bqs.h(bqs.c, this.f);
        bqs.a(bqs.c, bqs.e, 3553, this.g, 0);
        if (this.e) {
            bqs.i(bqs.d, this.h);
            bqs.a(bqs.d, 33190, this.a, this.b);
            bqs.b(bqs.c, bqs.f, bqs.d, this.h);
        }
        this.f();
        this.d();
    }

    public void a(int n2) {
        if (bqs.i()) {
            this.j = n2;
            bfl.i(this.g);
            GL11.glTexParameterf(3553, 10241, n2);
            GL11.glTexParameterf(3553, 10240, n2);
            GL11.glTexParameterf(3553, 10242, 10496.0f);
            GL11.glTexParameterf(3553, 10243, 10496.0f);
            bfl.i(0);
        }
    }

    public void b() {
        int n2 = bqs.j(bqs.c);
        if (n2 == bqs.g) {
            return;
        }
        if (n2 == bqs.h) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
        }
        if (n2 == bqs.i) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
        }
        if (n2 == bqs.j) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
        }
        if (n2 == bqs.k) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
        }
        throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + n2);
    }

    public void c() {
        if (bqs.i()) {
            bfl.i(this.g);
        }
    }

    public void d() {
        if (bqs.i()) {
            bfl.i(0);
        }
    }

    public void a(boolean bl2) {
        if (bqs.i()) {
            bqs.h(bqs.c, this.f);
            if (bl2) {
                bfl.b(0, 0, this.c, this.d);
            }
        }
    }

    public void e() {
        if (bqs.i()) {
            bqs.h(bqs.c, 0);
        }
    }

    public void a(float f2, float f3, float f4, float f5) {
        this.i[0] = f2;
        this.i[1] = f3;
        this.i[2] = f4;
        this.i[3] = f5;
    }

    public void c(int n2, int n3) {
        this.a(n2, n3, true);
    }

    public void a(int n2, int n3, boolean bl2) {
        if (!bqs.i()) {
            return;
        }
        bfl.a(true, true, true, false);
        bfl.i();
        bfl.a(false);
        bfl.n(5889);
        bfl.D();
        bfl.a(0.0, n2, n3, 0.0, 1000.0, 3000.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -2000.0f);
        bfl.b(0, 0, n2, n3);
        bfl.w();
        bfl.f();
        bfl.c();
        if (bl2) {
            bfl.k();
            bfl.g();
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.c();
        float f2 = n2;
        \u2603 = n3;
        \u2603 = (float)this.c / (float)this.a;
        \u2603 = (float)this.d / (float)this.b;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.i);
        \u26033.b(0.0, (double)\u2603, 0.0).a(0.0, 0.0).b(255, 255, 255, 255).d();
        \u26033.b((double)f2, (double)\u2603, 0.0).a(\u2603, 0.0).b(255, 255, 255, 255).d();
        \u26033.b((double)f2, 0.0, 0.0).a(\u2603, \u2603).b(255, 255, 255, 255).d();
        \u26033.b(0.0, 0.0, 0.0).a(0.0, \u2603).b(255, 255, 255, 255).d();
        \u26032.b();
        this.d();
        bfl.a(true);
        bfl.a(true, true, true, true);
    }

    public void f() {
        this.a(true);
        bfl.a(this.i[0], this.i[1], this.i[2], this.i[3]);
        int n2 = 16384;
        if (this.e) {
            bfl.a(1.0);
            n2 |= 0x100;
        }
        bfl.m(n2);
        this.e();
    }
}

