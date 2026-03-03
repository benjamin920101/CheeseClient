/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fx
implements ff<fj> {
    private String[] a;

    public fx() {
    }

    public fx(String[] stringArray) {
        this.a = stringArray;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = new String[em2.e()];
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = em2.c(Short.MAX_VALUE);
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a.length);
        for (String string : this.a) {
            em2.a(string);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public String[] a() {
        return this.a;
    }
}

