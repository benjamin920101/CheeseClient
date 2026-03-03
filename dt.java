/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dt
extends eb.a {
    private int b;

    dt() {
    }

    public dt(int n2) {
        this.b = n2;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(96L);
        this.b = dataInput.readInt();
    }

    @Override
    public byte a() {
        return 3;
    }

    @Override
    public String toString() {
        return "" + this.b;
    }

    @Override
    public eb b() {
        return new dt(this.b);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            dt dt2 = (dt)object;
            return this.b == dt2.b;
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
        return (short)(this.b & 0xFFFF);
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

