/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.util.glu.Project;

public class ayy
extends ayl {
    private static final jy C = new jy("textures/gui/container/enchanting_table.png");
    private static final jy D = new jy("textures/entity/enchanting_table_book.png");
    private static final bay E = new bay();
    private final wm F;
    private Random G = new Random();
    private xs H;
    public int u;
    public float v;
    public float w;
    public float x;
    public float y;
    public float z;
    public float A;
    zx B;
    private final op I;

    public ayy(wm wm2, adm adm2, op op2) {
        super(new xs(wm2, adm2));
        this.F = wm2;
        this.H = (xs)this.h;
        this.I = op2;
    }

    @Override
    protected void b(int n2, int n3) {
        this.q.a(this.I.f_().c(), 12, 5, 0x404040);
        this.q.a(this.F.f_().c(), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    public void e() {
        super.e();
        this.a();
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        for (\u2603 = 0; \u2603 < 3; ++\u2603) {
            \u2603 = n2 - (\u2603 + 60);
            \u2603 = n3 - (\u2603 + 14 + 19 * \u2603);
            if (\u2603 < 0 || \u2603 < 0 || \u2603 >= 108 || \u2603 >= 19 || !this.H.a((wn)this.j.h, \u2603)) continue;
            this.j.c.a(this.H.d, \u2603);
        }
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(C);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        bfl.E();
        bfl.n(5889);
        bfl.E();
        bfl.D();
        avr avr2 = new avr(this.j);
        bfl.b((avr2.a() - 320) / 2 * avr2.e(), (avr2.b() - 240) / 2 * avr2.e(), 320 * avr2.e(), 240 * avr2.e());
        bfl.b(-0.34f, 0.23f, 0.0f);
        Project.gluPerspective(90.0f, 1.3333334f, 9.0f, 80.0f);
        float \u26032 = 1.0f;
        bfl.n(5888);
        bfl.D();
        avc.b();
        bfl.b(0.0f, 3.3f, -16.0f);
        bfl.a(\u26032, \u26032, \u26032);
        float \u26033 = 5.0f;
        bfl.a(\u26033, \u26033, \u26033);
        bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
        this.j.P().a(D);
        bfl.b(20.0f, 1.0f, 0.0f, 0.0f);
        float \u26034 = this.A + (this.z - this.A) * f2;
        bfl.b((1.0f - \u26034) * 0.2f, (1.0f - \u26034) * 0.1f, (1.0f - \u26034) * 0.25f);
        bfl.b(-(1.0f - \u26034) * 90.0f - 90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(180.0f, 1.0f, 0.0f, 0.0f);
        float \u26035 = this.w + (this.v - this.w) * f2 + 0.25f;
        float \u26036 = this.w + (this.v - this.w) * f2 + 0.75f;
        \u26035 = (\u26035 - (float)ns.b((double)\u26035)) * 1.6f - 0.3f;
        \u26036 = (\u26036 - (float)ns.b((double)\u26036)) * 1.6f - 0.3f;
        if (\u26035 < 0.0f) {
            \u26035 = 0.0f;
        }
        if (\u26036 < 0.0f) {
            \u26036 = 0.0f;
        }
        if (\u26035 > 1.0f) {
            \u26035 = 1.0f;
        }
        if (\u26036 > 1.0f) {
            \u26036 = 1.0f;
        }
        bfl.B();
        E.a(null, 0.0f, \u26035, \u26036, \u26034, 0.0f, 0.0625f);
        bfl.C();
        avc.a();
        bfl.n(5889);
        bfl.b(0, 0, this.j.d, this.j.e);
        bfl.F();
        bfl.n(5888);
        bfl.F();
        avc.a();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        ayx.a().a(this.H.f);
        int \u26037 = this.H.e();
        for (int i2 = 0; i2 < 3; ++i2) {
            String string;
            \u2603 = \u2603 + 60;
            \u2603 = \u2603 + 20;
            \u2603 = 86;
            String string2 = ayx.a().b();
            this.e = 0.0f;
            this.j.P().a(C);
            int \u26038 = this.H.g[i2];
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            if (\u26038 == 0) {
                this.b(\u2603, \u2603 + 14 + 19 * i2, 0, 185, 108, 19);
                continue;
            }
            string = "" + \u26038;
            avn \u26039 = this.j.l;
            int \u260310 = 6839882;
            if (!(\u26037 >= i2 + 1 && this.j.h.bB >= \u26038 || this.j.h.bA.d)) {
                this.b(\u2603, \u2603 + 14 + 19 * i2, 0, 185, 108, 19);
                this.b(\u2603 + 1, \u2603 + 15 + 19 * i2, 16 * i2, 239, 16, 16);
                \u26039.a(string2, \u2603, \u2603 + 16 + 19 * i2, \u2603, (\u260310 & 0xFEFEFE) >> 1);
                \u260310 = 4226832;
            } else {
                int n4 = n2 - (\u2603 + 60);
                \u2603 = n3 - (\u2603 + 14 + 19 * i2);
                if (n4 >= 0 && \u2603 >= 0 && n4 < 108 && \u2603 < 19) {
                    this.b(\u2603, \u2603 + 14 + 19 * i2, 0, 204, 108, 19);
                    \u260310 = 0xFFFF80;
                } else {
                    this.b(\u2603, \u2603 + 14 + 19 * i2, 0, 166, 108, 19);
                }
                this.b(\u2603 + 1, \u2603 + 15 + 19 * i2, 16 * i2, 223, 16, 16);
                \u26039.a(string2, \u2603, \u2603 + 16 + 19 * i2, \u2603, \u260310);
                \u260310 = 8453920;
            }
            \u26039 = this.j.k;
            \u26039.a(string, (float)(\u2603 + 86 - \u26039.a(string)), (float)(\u2603 + 16 + 19 * i2 + 7), \u260310);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        boolean bl2 = this.j.h.bA.d;
        int \u26032 = this.H.e();
        for (int i2 = 0; i2 < 3; ++i2) {
            String string;
            \u2603 = this.H.g[i2];
            \u2603 = this.H.h[i2];
            \u2603 = i2 + 1;
            if (!this.c(60, 14 + 19 * i2, 108, 17, n2, n3) || \u2603 <= 0 || \u2603 < 0) continue;
            ArrayList<String> arrayList = Lists.newArrayList();
            if (\u2603 >= 0 && aci.c(\u2603 & 0xFF) != null) {
                string = aci.c(\u2603 & 0xFF).d((\u2603 & 0xFF00) >> 8);
                arrayList.add(a.p.toString() + a.u.toString() + bnq.a("container.enchant.clue", string));
            }
            if (!bl2) {
                if (\u2603 >= 0) {
                    arrayList.add("");
                }
                if (this.j.h.bB < \u2603) {
                    arrayList.add(a.m.toString() + "Level Requirement: " + this.H.g[i2]);
                } else {
                    string = "";
                    string = \u2603 == 1 ? bnq.a("container.enchant.lapis.one", new Object[0]) : bnq.a("container.enchant.lapis.many", \u2603);
                    if (\u26032 >= \u2603) {
                        arrayList.add(a.h.toString() + "" + string);
                    } else {
                        arrayList.add(a.m.toString() + "" + string);
                    }
                    string = \u2603 == 1 ? bnq.a("container.enchant.level.one", new Object[0]) : bnq.a("container.enchant.level.many", \u2603);
                    arrayList.add(a.h.toString() + "" + string);
                }
            }
            this.a(arrayList, n2, n3);
            break;
        }
    }

    public void a() {
        zx zx2 = this.h.a(0).d();
        if (!zx.b(zx2, this.B)) {
            this.B = zx2;
            do {
                this.x += (float)(this.G.nextInt(4) - this.G.nextInt(4));
            } while (this.v <= this.x + 1.0f && this.v >= this.x - 1.0f);
        }
        ++this.u;
        this.w = this.v;
        this.A = this.z;
        boolean \u26032 = false;
        for (int i2 = 0; i2 < 3; ++i2) {
            if (this.H.g[i2] == 0) continue;
            \u26032 = true;
        }
        this.z = \u26032 ? (this.z += 0.2f) : (this.z -= 0.2f);
        this.z = ns.a(this.z, 0.0f, 1.0f);
        float f2 = (this.x - this.v) * 0.4f;
        \u2603 = 0.2f;
        f2 = ns.a(f2, -\u2603, \u2603);
        this.y += (f2 - this.y) * 0.9f;
        this.v += this.y;
    }
}

