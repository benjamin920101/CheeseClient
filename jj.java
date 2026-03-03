/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class jj
implements ff<jf> {
    private eu a;

    public jj() {
    }

    public jj(eu eu2) {
        this.a = eu2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.d();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    @Override
    public void a(jf jf2) {
        jf2.a(this);
    }

    public eu a() {
        return this.a;
    }
}

