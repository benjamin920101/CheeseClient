/*
 * Decompiled with CFR 0.152.
 */
import java.nio.ByteBuffer;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class bfe {
    public void a(bfd bfd22) {
        bfd bfd22;
        if (bfd22.h() > 0) {
            int \u26037;
            Object \u26035;
            bmu bmu2 = bfd22.g();
            int \u26032 = bmu2.g();
            ByteBuffer \u26033 = bfd22.f();
            List<bmv> \u26034 = bmu2.h();
            block12: for (int i2 = 0; i2 < \u26034.size(); ++i2) {
                bmv bmv2 = \u26034.get(i2);
                \u26035 = bmv2.b();
                int \u26036 = bmv2.a().c();
                \u26037 = bmv2.d();
                \u26033.position(bmu2.d(i2));
                switch (1.a[((Enum)\u26035).ordinal()]) {
                    case 1: {
                        GL11.glVertexPointer(bmv2.c(), \u26036, \u26032, \u26033);
                        GL11.glEnableClientState(32884);
                        continue block12;
                    }
                    case 2: {
                        bqs.l(bqs.q + \u26037);
                        GL11.glTexCoordPointer(bmv2.c(), \u26036, \u26032, \u26033);
                        GL11.glEnableClientState(32888);
                        bqs.l(bqs.q);
                        continue block12;
                    }
                    case 3: {
                        GL11.glColorPointer(bmv2.c(), \u26036, \u26032, \u26033);
                        GL11.glEnableClientState(32886);
                        continue block12;
                    }
                    case 4: {
                        GL11.glNormalPointer(\u26036, \u26032, \u26033);
                        GL11.glEnableClientState(32885);
                    }
                }
            }
            GL11.glDrawArrays(bfd22.i(), 0, bfd22.h());
            int n2 = \u26034.size();
            block13: for (i2 = 0; i2 < n2; ++i2) {
                \u26035 = \u26034.get(i2);
                bmv.b b2 = ((bmv)\u26035).b();
                \u26037 = ((bmv)\u26035).d();
                switch (b2) {
                    case a: {
                        GL11.glDisableClientState(32884);
                        continue block13;
                    }
                    case d: {
                        bqs.l(bqs.q + \u26037);
                        GL11.glDisableClientState(32888);
                        bqs.l(bqs.q);
                        continue block13;
                    }
                    case c: {
                        GL11.glDisableClientState(32886);
                        bfl.G();
                        continue block13;
                    }
                    case b: {
                        GL11.glDisableClientState(32885);
                    }
                }
            }
        }
        bfd22.b();
    }
}

