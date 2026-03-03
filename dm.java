/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dm
extends eb.a {
    private byte b;

    dm() {
    }

    public dm(byte by) {
        this.b = by;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(72L);
        this.b = dataInput.readByte();
    }

    @Override
    public byte a() {
        return 1;
    }

    @Override
    public String toString() {
        return "" + this.b + "b";
    }

    @Override
    public eb b() {
        return new dm(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dm dm2 = (dm)object;
            return this.b == dm2.b;
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
        return this.b;
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

