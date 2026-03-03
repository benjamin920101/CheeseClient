/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ju
implements ff<jt> {
    private long a;

    public ju() {
    }

    public ju(long l2) {
        this.a = l2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readLong();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeLong(this.a);
    }

    @Override
    public void a(jt jt2) {
        jt2.a(this);
    }

    public long a() {
        return this.a;
    }
}

