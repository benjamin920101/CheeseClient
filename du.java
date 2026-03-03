/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class du
extends eb {
    private static final Logger b = LogManager.getLogger();
    private List<eb> c = Lists.newArrayList();
    private byte d = 0;

    @Override
    void a(DataOutput dataOutput) throws IOException {
        this.d = !this.c.isEmpty() ? this.c.get(0).a() : (byte)0;
        dataOutput.writeByte(this.d);
        dataOutput.writeInt(this.c.size());
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            this.c.get(i2).a(dataOutput);
        }
    }

    @Override
    void a(DataInput dataInput, int n2, dw dw2) throws IOException {
        dw2.a(296L);
        if (n2 > 512) {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        }
        this.d = dataInput.readByte();
        int n3 = dataInput.readInt();
        if (this.d == 0 && n3 > 0) {
            throw new RuntimeException("Missing type on ListTag");
        }
        dw2.a(32L * (long)n3);
        this.c = Lists.newArrayListWithCapacity(n3);
        for (\u2603 = 0; \u2603 < n3; ++\u2603) {
            eb eb2 = eb.a(this.d);
            eb2.a(dataInput, n2 + 1, dw2);
            this.c.add(eb2);
        }
    }

    @Override
    public byte a() {
        return 9;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            if (i2 != 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(i2).append(':').append(this.c.get(i2));
        }
        return stringBuilder.append(']').toString();
    }

    public void a(eb eb2) {
        if (eb2.a() == 0) {
            b.warn("Invalid TagEnd added to ListTag");
            return;
        }
        if (this.d == 0) {
            this.d = eb2.a();
        } else if (this.d != eb2.a()) {
            b.warn("Adding mismatching tag types to tag list");
            return;
        }
        this.c.add(eb2);
    }

    public void a(int n2, eb eb2) {
        if (eb2.a() == 0) {
            b.warn("Invalid TagEnd added to ListTag");
            return;
        }
        if (n2 < 0 || n2 >= this.c.size()) {
            b.warn("index out of bounds to set tag in tag list");
            return;
        }
        if (this.d == 0) {
            this.d = eb2.a();
        } else if (this.d != eb2.a()) {
            b.warn("Adding mismatching tag types to tag list");
            return;
        }
        this.c.set(n2, eb2);
    }

    public eb a(int n2) {
        return this.c.remove(n2);
    }

    @Override
    public boolean c_() {
        return this.c.isEmpty();
    }

    public dn b(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return new dn();
        }
        eb eb2 = this.c.get(n2);
        if (eb2.a() == 10) {
            return (dn)eb2;
        }
        return new dn();
    }

    public int[] c(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return new int[0];
        }
        eb eb2 = this.c.get(n2);
        if (eb2.a() == 11) {
            return ((ds)eb2).c();
        }
        return new int[0];
    }

    public double d(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return 0.0;
        }
        eb eb2 = this.c.get(n2);
        if (eb2.a() == 6) {
            return ((dp)eb2).g();
        }
        return 0.0;
    }

    public float e(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return 0.0f;
        }
        eb eb2 = this.c.get(n2);
        if (eb2.a() == 5) {
            return ((dr)eb2).h();
        }
        return 0.0f;
    }

    public String f(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return "";
        }
        eb eb2 = this.c.get(n2);
        if (eb2.a() == 8) {
            return eb2.a_();
        }
        return eb2.toString();
    }

    public eb g(int n2) {
        if (n2 < 0 || n2 >= this.c.size()) {
            return new dq();
        }
        return this.c.get(n2);
    }

    public int c() {
        return this.c.size();
    }

    @Override
    public eb b() {
        du du2 = new du();
        du2.d = this.d;
        for (eb eb2 : this.c) {
            \u2603 = eb2.b();
            du2.c.add(\u2603);
        }
        return du2;
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            du du2 = (du)object;
            if (this.d == du2.d) {
                return this.c.equals(du2.c);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.c.hashCode();
    }

    public int f() {
        return this.d;
    }
}

