/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public class bfy
extends bfh {
    @Override
    public void a(adf adf2) {
        if (!this.b) {
            return;
        }
        for (bht bht2 : this.a) {
            bmt bmt2 = bht2.b(adf2.ordinal());
            bfl.E();
            this.a(bht2);
            bht2.f();
            bmt2.a();
            this.a();
            bmt2.a(7);
            bfl.F();
        }
        bqs.g(bqs.R, 0);
        bfl.G();
        this.a.clear();
    }

    private void a() {
        GL11.glVertexPointer(3, 5126, 28, 0L);
        GL11.glColorPointer(4, 5121, 28, 12L);
        GL11.glTexCoordPointer(2, 5126, 28, 16L);
        bqs.l(bqs.r);
        GL11.glTexCoordPointer(2, 5122, 28, 24L);
        bqs.l(bqs.q);
    }
}

