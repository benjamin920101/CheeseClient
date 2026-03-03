/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class dq
extends eb {
    dq() {
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(64L);
    }

    @Override
    void a(DataOutput dataOutput) throws IOException {
    }

    @Override
    public byte a() {
        return 0;
    }

    @Override
    public String toString() {
        return "END";
    }

    @Override
    public eb b() {
        return new dq();
    }
}

