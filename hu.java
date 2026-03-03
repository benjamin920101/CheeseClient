/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hu
implements ff<fj> {
    private long a;
    private long b;

    public hu() {
    }

    public hu(long l2, long l3, boolean bl2) {
        this.a = l2;
        this.b = l3;
        if (!bl2) {
            this.b = -this.b;
            if (this.b == 0L) {
                this.b = -1L;
            }
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readLong();
        this.b = em2.readLong();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeLong(this.a);
        em2.writeLong(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }
}

