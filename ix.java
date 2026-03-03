/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ix
implements ff<ic> {
    private cj a;
    private eu[] b;

    public ix() {
    }

    public ix(cj cj2, eu[] euArray) {
        this.a = cj2;
        this.b = new eu[]{euArray[0], euArray[1], euArray[2], euArray[3]};
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
        this.b = new eu[4];
        for (int i2 = 0; i2 < 4; ++i2) {
            String string = em2.c(384);
            this.b[i2] = \u2603 = eu.a.a(string);
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        for (int i2 = 0; i2 < 4; ++i2) {
            eu eu2 = this.b[i2];
            String \u26032 = eu.a.a(eu2);
            em2.a(\u26032);
        }
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public cj a() {
        return this.a;
    }

    public eu[] b() {
        return this.b;
    }
}

