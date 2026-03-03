/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fw
implements ff<fj> {
    private oj a;
    private boolean b;

    public fw() {
    }

    public fw(oj oj2, boolean bl2) {
        this.a = oj2;
        this.b = bl2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = oj.a(em2.readUnsignedByte());
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a.a());
    }

    public boolean a() {
        return this.b;
    }

    public oj b() {
        return this.a;
    }
}

