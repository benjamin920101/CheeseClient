/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class ds
extends eb {
    private int[] b;

    ds() {
    }

    public ds(int[] nArray) {
        this.b = nArray;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.b.length);
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            dataOutput.writeInt(this.b[i2]);
        }
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(192L);
        int n3 = dataInput.readInt();
        dw2.a(32 * n3);
        this.b = new int[n3];
        for (\u2603 = 0; \u2603 < n3; ++\u2603) {
            this.b[\u2603] = dataInput.readInt();
        }
    }

    @Override
    public byte a() {
        return 11;
    }

    @Override
    public String toString() {
        String string = "[";
        for (int n2 : this.b) {
            string = string + n2 + ",";
        }
        return string + "]";
    }

    @Override
    public eb b() {
        int[] nArray = new int[this.b.length];
        System.arraycopy(this.b, 0, nArray, 0, this.b.length);
        return new ds(nArray);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            return Arrays.equals(this.b, ((ds)object).b);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.b);
    }

    public int[] c() {
        return this.b;
    }
}

