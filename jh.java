/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.security.PublicKey;

public class jh
implements ff<jf> {
    private String a;
    private PublicKey b;
    private byte[] c;

    public jh() {
    }

    public jh(String string, PublicKey publicKey, byte[] byArray) {
        this.a = string;
        this.b = publicKey;
        this.c = byArray;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(20);
        this.b = ng.a(em2.a());
        this.c = em2.a();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.a(this.b.getEncoded());
        em2.a(this.c);
    }

    @Override
    public void a(jf jf2) {
        jf2.a(this);
    }

    public String a() {
        return this.a;
    }

    public PublicKey b() {
        return this.b;
    }

    public byte[] c() {
        return this.c;
    }
}

