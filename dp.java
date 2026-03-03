/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dp
extends eb.a {
    private double b;

    dp() {
    }

    public dp(double d2) {
        this.b = d2;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(128L);
        this.b = dataInput.readDouble();
    }

    @Override
    public byte a() {
        return 6;
    }

    @Override
    public String toString() {
        return "" + this.b + "d";
    }

    @Override
    public eb b() {
        return new dp(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dp dp2 = (dp)object;
            return this.b == dp2.b;
        }
        return false;
    }

    @Override
    public int hashCode() {
        long l2 = Double.doubleToLongBits(this.b);
        return super.hashCode() ^ (int)(l2 ^ l2 >>> 32);
    }

    @Override
    public long c() {
        return (long)Math.floor(this.b);
    }

    @Override
    public int d() {
        return ns.c(this.b);
    }

    @Override
    public short e() {
        return (short)(ns.c(this.b) & 0xFFFF);
    }

    @Override
    public byte f() {
        return (byte)(ns.c(this.b) & 0xFF);
    }

    @Override
    public double g() {
        return this.b;
    }

    @Override
    public float h() {
        return (float)this.b;
    }
}

