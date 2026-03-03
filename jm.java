/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public class jm
implements ff<jk> {
    private byte[] a = new byte[0];
    private byte[] b = new byte[0];

    public jm() {
    }

    public jm(SecretKey secretKey, PublicKey publicKey, byte[] byArray) {
        this.a = ng.a(publicKey, secretKey.getEncoded());
        this.b = ng.a(publicKey, byArray);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.a();
        this.b = em2.a();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.a(this.b);
    }

    @Override
    public void a(jk jk2) {
        jk2.a(this);
    }

    public SecretKey a(PrivateKey privateKey) {
        return ng.a(privateKey, this.a);
    }

    public byte[] b(PrivateKey privateKey) {
        if (privateKey == null) {
            return this.b;
        }
        return ng.b(privateKey, this.b);
    }
}

