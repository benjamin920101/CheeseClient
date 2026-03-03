/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import org.apache.commons.lang3.Validate;

public class gs
implements ff<fj> {
    private String a;
    private int b;
    private int c = Integer.MAX_VALUE;
    private int d;
    private float e;
    private int f;

    public gs() {
    }

    public gs(String string, double d2, double d3, double d4, float f2, float f3) {
        Validate.notNull(string, "name", new Object[0]);
        this.a = string;
        this.b = (int)(d2 * 8.0);
        this.c = (int)(d3 * 8.0);
        this.d = (int)(d4 * 8.0);
        this.e = f2;
        this.f = (int)(f3 * 63.0f);
        f3 = ns.a(f3, 0.0f, 255.0f);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(256);
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.e = em2.readFloat();
        this.f = em2.readUnsignedByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeFloat(this.e);
        em2.writeByte(this.f);
    }

    public String a() {
        return this.a;
    }

    public double b() {
        return (float)this.b / 8.0f;
    }

    public double c() {
        return (float)this.c / 8.0f;
    }

    public double d() {
        return (float)this.d / 8.0f;
    }

    public float e() {
        return this.e;
    }

    public float f() {
        return (float)this.f / 63.0f;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }
}

