/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ea
extends eb {
    private String b;

    public ea() {
        this.b = "";
    }

    public ea(String string) {
        this.b = string;
        if (string == null) {
            throw new IllegalArgumentException("Empty string not allowed");
        }
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.b);
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(288L);
        this.b = dataInput.readUTF();
        dw2.a(16 * this.b.length());
    }

    @Override
    public byte a() {
        return 8;
    }

    @Override
    public String toString() {
        return "\"" + this.b.replace("\"", "\\\"") + "\"";
    }

    @Override
    public eb b() {
        return new ea(this.b);
    }

    @Override
    public boolean c_() {
        return this.b.isEmpty();
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            ea ea2 = (ea)object;
            return this.b == null && ea2.b == null || this.b != null && this.b.equals(ea2.b);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.b.hashCode();
    }

    @Override
    public String a_() {
        return this.b;
    }
}

