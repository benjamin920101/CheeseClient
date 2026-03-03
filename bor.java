/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public enum bor {
    a(0, 0),
    b(0, 90),
    c(0, 180),
    d(0, 270),
    e(90, 0),
    f(90, 90),
    g(90, 180),
    h(90, 270),
    i(180, 0),
    j(180, 90),
    k(180, 180),
    l(180, 270),
    m(270, 0),
    n(270, 90),
    o(270, 180),
    p(270, 270);

    private static final Map<Integer, bor> q;
    private final int r;
    private final Matrix4f s;
    private final int t;
    private final int u;

    private static int b(int n2, int n3) {
        return n2 * 360 + n3;
    }

    private bor(int n3, int n4) {
        this.r = bor.b(n3, n4);
        this.s = new Matrix4f();
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        Matrix4f.rotate((float)(-n3) * ((float)Math.PI / 180), new Vector3f(1.0f, 0.0f, 0.0f), matrix4f, matrix4f);
        this.t = ns.a(n3 / 90);
        \u2603 = new Matrix4f();
        \u2603.setIdentity();
        Matrix4f.rotate((float)(-n4) * ((float)Math.PI / 180), new Vector3f(0.0f, 1.0f, 0.0f), \u2603, \u2603);
        this.u = ns.a(n4 / 90);
        Matrix4f.mul(\u2603, matrix4f, this.s);
    }

    public Matrix4f a() {
        return this.s;
    }

    public cq a(cq cq2) {
        cq cq3;
        int n2;
        cq3 = cq2;
        for (n2 = 0; n2 < this.t; ++n2) {
            cq3 = cq3.a(cq.a.a);
        }
        if (cq3.k() != cq.a.b) {
            for (n2 = 0; n2 < this.u; ++n2) {
                cq3 = cq3.a(cq.a.b);
            }
        }
        return cq3;
    }

    public int a(cq cq2, int n2) {
        int n3;
        n3 = n2;
        if (cq2.k() == cq.a.a) {
            n3 = (n3 + this.t) % 4;
        }
        cq cq3 = cq2;
        for (int i2 = 0; i2 < this.t; ++i2) {
            cq3 = cq3.a(cq.a.a);
        }
        if (cq3.k() == cq.a.b) {
            n3 = (n3 + this.u) % 4;
        }
        return n3;
    }

    public static bor a(int n2, int n3) {
        return q.get(bor.b(ns.b(n2, 360), ns.b(n3, 360)));
    }

    static {
        q = Maps.newHashMap();
        for (bor bor2 : bor.values()) {
            q.put(bor2.r, bor2);
        }
    }
}

