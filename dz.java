/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dz
extends eb.a {
    private short b;

    public dz() {
    }

    public dz(short s2) {
        this.b = s2;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeShort(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(80L);
        this.b = dataInput.readShort();
    }

    @Override
    public byte a() {
        return 2;
    }

    @Override
    public String toString() {
        return "" + this.b + "s";
    }

    @Override
    public eb b() {
        return new dz(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dz dz2 = (dz)object;
            return this.b == dz2.b;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.b;
    }

    @Override
    public long c() {
        return this.b;
    }

    @Override
    public int d() {
        return this.b;
    }

    @Override
    public short e() {
        return this.b;
    }

    @Override
    public byte f() {
        return (byte)(this.b & 0xFF);
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

