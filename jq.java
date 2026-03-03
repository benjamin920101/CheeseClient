/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class jq
implements ff<jp> {
    private long a;

    public jq() {
    }

    public jq(long l2) {
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
    public void a(jp jp2) {
        jp2.a(this);
    }
}

