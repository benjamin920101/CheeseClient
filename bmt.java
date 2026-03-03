/*
 * Decompiled with CFR 0.152.
 */
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;

public class bmt {
    private int a;
    private final bmu b;
    private int c;

    public bmt(bmu bmu2) {
        this.b = bmu2;
        this.a = bqs.e();
    }

    public void a() {
        bqs.g(bqs.R, this.a);
    }

    public void a(ByteBuffer byteBuffer) {
        this.a();
        bqs.a(bqs.R, byteBuffer, 35044);
        this.b();
        this.c = byteBuffer.limit() / this.b.g();
    }

    public void a(int n2) {
        GL11.glDrawArrays(n2, 0, this.c);
    }

    public void b() {
        bqs.g(bqs.R, 0);
    }

    public void c() {
        if (this.a >= 0) {
            bqs.g(this.a);
            this.a = -1;
        }
    }
}

