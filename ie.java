/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ie
implements ff<ic> {
    private String a;

    public ie() {
    }

    public ie(String string) {
        if (string.length() > 100) {
            string = string.substring(0, 100);
        }
        this.a = string;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(100);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public String a() {
        return this.a;
    }
}

