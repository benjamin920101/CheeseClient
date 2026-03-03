/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.UUID;

public class iz
implements ff<ic> {
    private UUID a;

    public iz() {
    }

    public iz(UUID uUID) {
        this.a = uUID;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.g();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public pk a(le le2) {
        return le2.a(this.a);
    }
}

