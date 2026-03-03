/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public abstract class bly
implements bmk {
    protected int a = -1;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected boolean e;

    public void a(boolean bl2, boolean bl3) {
        this.b = bl2;
        this.c = bl3;
        int n2 = -1;
        \u2603 = -1;
        if (bl2) {
            n2 = bl3 ? 9987 : 9729;
            \u2603 = 9729;
        } else {
            n2 = bl3 ? 9986 : 9728;
            \u2603 = 9728;
        }
        GL11.glTexParameteri(3553, 10241, n2);
        GL11.glTexParameteri(3553, 10240, \u2603);
    }

    @Override
    public void b(boolean bl2, boolean bl3) {
        this.d = this.b;
        this.e = this.c;
        this.a(bl2, bl3);
    }

    @Override
    public void a() {
        this.a(this.d, this.e);
    }

    @Override
    public int b() {
        if (this.a == -1) {
            this.a = bml.a();
        }
        return this.a;
    }

    public void c() {
        if (this.a != -1) {
            bml.a(this.a);
            this.a = -1;
        }
    }
}

