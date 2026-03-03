/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class im
implements ff<ic> {
    private String a;
    private em b;

    public im() {
    }

    public im(String string, em em2) {
        this.a = string;
        this.b = em2;
        if (em2.writerIndex() > Short.MAX_VALUE) {
            throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(20);
        int n2 = em2.readableBytes();
        if (n2 < 0 || n2 > Short.MAX_VALUE) {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
        this.b = new em(em2.readBytes(n2));
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeBytes(this.b);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public String a() {
        return this.a;
    }

    public em b() {
        return this.b;
    }
}

