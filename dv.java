/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dv
extends eb.a {
    private long b;

    dv() {
    }

    public dv(long l2) {
        this.b = l2;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(128L);
        this.b = dataInput.readLong();
    }

    @Override
    public byte a() {
        return 4;
    }

    @Override
    public String toString() {
        return "" + this.b + "L";
    }

    @Override
    public eb b() {
        return new dv(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dv dv2 = (dv)object;
            return this.b == dv2.b;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ (int)(this.b ^ this.b >>> 32);
    }

    @Override
    public long c() {
        return this.b;
    }

    @Override
    public int d() {
        return (int)(this.b & 0xFFFFFFFFFFFFFFFFL);
    }

    @Override
    public short e() {
        return (short)(this.b & 0xFFFFL);
    }

    @Override
    public byte f() {
        return (byte)(this.b & 0xFFL);
    }

    @Override
    public double g() {
        return this.b;
    }

    @Override
    public float h() {
        return this.b;
    }
}

