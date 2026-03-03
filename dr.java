/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dr
extends eb.a {
    private float b;

    dr() {
    }

    public dr(float f2) {
        this.b = f2;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeFloat(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(96L);
        this.b = dataInput.readFloat();
    }

    @Override
    public byte a() {
        return 5;
    }

    @Override
    public String toString() {
        return "" + this.b + "f";
    }

    @Override
    public eb b() {
        return new dr(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dr dr2 = (dr)object;
            return this.b == dr2.b;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Float.floatToIntBits(this.b);
    }

    @Override
    public long c() {
        return (long)this.b;
    }

    @Override
    public int d() {
        return ns.d(this.b);
    }

    @Override
    public short e() {
        return (short)(ns.d(this.b) & 0xFFFF);
    }

    @Override
    public byte f() {
        return (byte)(ns.d(this.b) & 0xFF);
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

