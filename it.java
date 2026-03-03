/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class it
implements ff<ic> {
    private float a;
    private float b;
    private boolean c;
    private boolean d;

    public it() {
    }

    public it(float f2, float f3, boolean bl2, boolean bl3) {
        this.a = f2;
        this.b = f3;
        this.c = bl2;
        this.d = bl3;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readFloat();
        this.b = em2.readFloat();
        byte by = em2.readByte();
        this.c = (by & 1) > 0;
        this.d = (by & 2) > 0;
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeFloat(this.a);
        em2.writeFloat(this.b);
        byte by = 0;
        if (this.c) {
            by = (byte)(by | '\u0001');
        }
        if (this.d) {
            by = (byte)(by | 2);
        }
        em2.writeByte(by);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public float a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }
}

