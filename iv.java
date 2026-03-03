/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class iv
implements ff<ic> {
    private int a;

    public iv() {
    }

    public iv(int n2) {
        this.a = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readShort();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeShort(this.a);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public int a() {
        return this.a;
    }
}

