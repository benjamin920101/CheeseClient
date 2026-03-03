/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public class bjc
extends bjo<vt> {
    private static final jy e = new jy("textures/entity/guardian.png");
    private static final jy j = new jy("textures/entity/guardian_elder.png");
    private static final jy k = new jy("textures/entity/guardian_beam.png");
    int a;

    public bjc(biu biu2) {
        super(biu2, new bbg(), 0.5f);
        this.a = ((bbg)this.f).a();
    }

    @Override
    public boolean a(vt vt2, bia bia2, double d2, double d3, double d4) {
        if (super.a(vt2, bia2, d2, d3, d4)) {
            return true;
        }
        if (vt2.cp() && (\u2603 = vt2.cq()) != null) {
            aui aui2 = this.a(\u2603, (double)\u2603.K * 0.5, 1.0f);
            \u2603 = this.a(vt2, (double)vt2.aS(), 1.0f);
            if (bia2.a(aug.a(\u2603.a, \u2603.b, \u2603.c, aui2.a, aui2.b, aui2.c))) {
                return true;
            }
        }
        return false;
    }

    private aui a(pr pr2, double d2, float f2) {
        double d3 = pr2.P + (pr2.s - pr2.P) * (double)f2;
        \u2603 = d2 + pr2.Q + (pr2.t - pr2.Q) * (double)f2;
        \u2603 = pr2.R + (pr2.u - pr2.R) * (double)f2;
        return new aui(d3, \u2603, \u2603);
    }

    @Override
    public void a(vt vt2, double d2, double d3, double d4, float f2, float f3) {
        if (this.a != ((bbg)this.f).a()) {
            this.f = new bbg();
            this.a = ((bbg)this.f).a();
        }
        super.a(vt2, d2, d3, d4, f2, f3);
        pr pr2 = vt2.cq();
        if (pr2 != null) {
            float f4 = vt2.q(f3);
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            this.a(k);
            GL11.glTexParameterf(3553, 10242, 10497.0f);
            GL11.glTexParameterf(3553, 10243, 10497.0f);
            bfl.f();
            bfl.p();
            bfl.k();
            bfl.a(true);
            \u2603 = 240.0f;
            bqs.a(bqs.r, \u2603, \u2603);
            bfl.a(770, 1, 1, 0);
            \u2603 = (float)vt2.o.K() + f3;
            \u2603 = \u2603 * 0.5f % 1.0f;
            \u2603 = vt2.aS();
            bfl.E();
            bfl.b((float)d2, (float)d3 + \u2603, (float)d4);
            aui \u26034 = this.a(pr2, (double)pr2.K * 0.5, f3);
            aui \u26035 = this.a(vt2, (double)\u2603, f3);
            aui \u26036 = \u26034.d(\u26035);
            double \u26037 = \u26036.b() + 1.0;
            \u26036 = \u26036.a();
            \u2603 = (float)Math.acos(\u26036.b);
            \u2603 = (float)Math.atan2(\u26036.c, \u26036.a);
            bfl.b((1.5707964f + -\u2603) * 57.295776f, 0.0f, 1.0f, 0.0f);
            bfl.b(\u2603 * 57.295776f, 1.0f, 0.0f, 0.0f);
            boolean \u26038 = true;
            double \u26039 = (double)\u2603 * 0.05 * (1.0 - (double)(\u26038 & true) * 2.5);
            \u26033.a(7, bms.i);
            \u2603 = f4 * f4;
            int \u260310 = 64 + (int)(\u2603 * 240.0f);
            int \u260311 = 32 + (int)(\u2603 * 192.0f);
            int \u260312 = 128 - (int)(\u2603 * 64.0f);
            double \u260313 = (double)\u26038 * 0.2;
            double \u260314 = \u260313 * 1.41;
            double \u260315 = 0.0 + Math.cos(\u26039 + 2.356194490192345) * \u260314;
            double \u260316 = 0.0 + Math.sin(\u26039 + 2.356194490192345) * \u260314;
            double \u260317 = 0.0 + Math.cos(\u26039 + 0.7853981633974483) * \u260314;
            double \u260318 = 0.0 + Math.sin(\u26039 + 0.7853981633974483) * \u260314;
            double \u260319 = 0.0 + Math.cos(\u26039 + 3.9269908169872414) * \u260314;
            double \u260320 = 0.0 + Math.sin(\u26039 + 3.9269908169872414) * \u260314;
            double \u260321 = 0.0 + Math.cos(\u26039 + 5.497787143782138) * \u260314;
            double \u260322 = 0.0 + Math.sin(\u26039 + 5.497787143782138) * \u260314;
            double \u260323 = 0.0 + Math.cos(\u26039 + Math.PI) * \u260313;
            double \u260324 = 0.0 + Math.sin(\u26039 + Math.PI) * \u260313;
            double \u260325 = 0.0 + Math.cos(\u26039 + 0.0) * \u260313;
            double \u260326 = 0.0 + Math.sin(\u26039 + 0.0) * \u260313;
            double \u260327 = 0.0 + Math.cos(\u26039 + 1.5707963267948966) * \u260313;
            double \u260328 = 0.0 + Math.sin(\u26039 + 1.5707963267948966) * \u260313;
            double \u260329 = 0.0 + Math.cos(\u26039 + 4.71238898038469) * \u260313;
            double \u260330 = 0.0 + Math.sin(\u26039 + 4.71238898038469) * \u260313;
            double \u260331 = \u26037;
            double \u260332 = 0.0;
            double \u260333 = 0.4999;
            double \u260334 = -1.0f + \u2603;
            double \u260335 = \u26037 * (0.5 / \u260313) + \u260334;
            \u26033.b(\u260323, \u260331, \u260324).a(0.4999, \u260335).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260323, 0.0, \u260324).a(0.4999, \u260334).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260325, 0.0, \u260326).a(0.0, \u260334).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260325, \u260331, \u260326).a(0.0, \u260335).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260327, \u260331, \u260328).a(0.4999, \u260335).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260327, 0.0, \u260328).a(0.4999, \u260334).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260329, 0.0, \u260330).a(0.0, \u260334).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260329, \u260331, \u260330).a(0.0, \u260335).b(\u260310, \u260311, \u260312, 255).d();
            double \u260336 = 0.0;
            if (vt2.W % 2 == 0) {
                \u260336 = 0.5;
            }
            \u26033.b(\u260315, \u260331, \u260316).a(0.5, \u260336 + 0.5).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260317, \u260331, \u260318).a(1.0, \u260336 + 0.5).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260321, \u260331, \u260322).a(1.0, \u260336).b(\u260310, \u260311, \u260312, 255).d();
            \u26033.b(\u260319, \u260331, \u260320).a(0.5, \u260336).b(\u260310, \u260311, \u260312, 255).d();
            \u26032.b();
            bfl.F();
        }
    }

    @Override
    protected void a(vt vt2, float f2) {
        if (vt2.cn()) {
            bfl.a(2.35f, 2.35f, 2.35f);
        }
    }

    @Override
    protected jy a(vt vt2) {
        return vt2.cn() ? j : e;
    }
}

