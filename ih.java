/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ih
implements ff<ic> {
    private String a;
    private int b;
    private wn.b c;
    private boolean d;
    private int e;

    public ih() {
    }

    public ih(String string, int n2, wn.b b2, boolean bl2, int n3) {
        this.a = string;
        this.b = n2;
        this.c = b2;
        this.d = bl2;
        this.e = n3;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(7);
        this.b = em2.readByte();
        this.c = wn.b.a(em2.readByte());
        this.d = em2.readBoolean();
        this.e = em2.readUnsignedByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte(this.b);
        em2.writeByte(this.c.a());
        em2.writeBoolean(this.d);
        em2.writeByte(this.e);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public String a() {
        return this.a;
    }

    public wn.b c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }
}

