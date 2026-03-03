/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bmu {
    private static final Logger a = LogManager.getLogger();
    private final List<bmv> b = Lists.newArrayList();
    private final List<Integer> c = Lists.newArrayList();
    private int d = 0;
    private int e = -1;
    private List<Integer> f = Lists.newArrayList();
    private int g = -1;

    public bmu(bmu bmu22) {
        this();
        bmu bmu22;
        for (int i2 = 0; i2 < bmu22.i(); ++i2) {
            this.a(bmu22.c(i2));
        }
        this.d = bmu22.g();
    }

    public bmu() {
    }

    public void a() {
        this.b.clear();
        this.c.clear();
        this.e = -1;
        this.f.clear();
        this.g = -1;
        this.d = 0;
    }

    public bmu a(bmv bmv2) {
        if (bmv2.f() && this.j()) {
            a.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
            return this;
        }
        this.b.add(bmv2);
        this.c.add(this.d);
        switch (bmv2.b()) {
            case b: {
                this.g = this.d;
                break;
            }
            case c: {
                this.e = this.d;
                break;
            }
            case d: {
                this.f.add(bmv2.d(), this.d);
                break;
            }
        }
        this.d += bmv2.e();
        return this;
    }

    public boolean b() {
        return this.g >= 0;
    }

    public int c() {
        return this.g;
    }

    public boolean d() {
        return this.e >= 0;
    }

    public int e() {
        return this.e;
    }

    public boolean a(int n2) {
        return this.f.size() - 1 >= n2;
    }

    public int b(int n2) {
        return this.f.get(n2);
    }

    public String toString() {
        String string = "format: " + this.b.size() + " elements: ";
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            string = string + this.b.get(i2).toString();
            if (i2 == this.b.size() - 1) continue;
            string = string + " ";
        }
        return string;
    }

    private boolean j() {
        int n2 = this.b.size();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            bmv bmv2 = this.b.get(\u2603);
            if (!bmv2.f()) continue;
            return true;
        }
        return false;
    }

    public int f() {
        return this.g() / 4;
    }

    public int g() {
        return this.d;
    }

    public List<bmv> h() {
        return this.b;
    }

    public int i() {
        return this.b.size();
    }

    public bmv c(int n2) {
        return this.b.get(n2);
    }

    public int d(int n2) {
        return this.c.get(n2);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        bmu bmu2 = (bmu)object;
        if (this.d != bmu2.d) {
            return false;
        }
        if (!this.b.equals(bmu2.b)) {
            return false;
        }
        return this.c.equals(bmu2.c);
    }

    public int hashCode() {
        int n2 = this.b.hashCode();
        n2 = 31 * n2 + this.c.hashCode();
        n2 = 31 * n2 + this.d;
        return n2;
    }
}

