/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class il
implements ff<ic> {
    private int a;

    public il() {
    }

    public il(int n2) {
        this.a = n2;
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
    }
}

