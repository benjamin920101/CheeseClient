/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class dl
extends eb {
    private byte[] b;

    dl() {
    }

    public dl(byte[] byArray) {
        this.b = byArray;
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.b.length);
        dataOutput.write(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(192L);
        int n3 = dataInput.readInt();
        dw2.a(8 * n3);
        this.b = new byte[n3];
        dataInput.readFully(this.b);
    }

    @Override
    public byte a() {
        return 7;
    }

    @Override
    public String toString() {
        return "[" + this.b.length + " bytes]";
    }

    @Override
    public eb b() {
        byte[] byArray = new byte[this.b.length];
        System.arraycopy(this.b, 0, byArray, 0, this.b.length);
        return new dl(byArray);
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            return Arrays.equals(this.b, ((dl)object).b);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.b);
    }

    public byte[] c() {
        return this.b;
    }
}

