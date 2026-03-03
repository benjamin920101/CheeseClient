/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;

public class gk
implements ff<fj> {
    private double a;
    private double b;
    private double c;
    private float d;
    private List<cj> e;
    private float f;
    private float g;
    private float h;

    public gk() {
    }

    public gk(double d2, double d3, double d4, float f2, List<cj> list, aui aui2) {
        this.a = d2;
        this.b = d3;
        this.c = d4;
        this.d = f2;
        this.e = Lists.newArrayList(list);
        if (aui2 != null) {
            this.f = (float)aui2.a;
            this.g = (float)aui2.b;
            this.h = (float)aui2.c;
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readFloat();
        this.b = em2.readFloat();
        this.c = em2.readFloat();
        this.d = em2.readFloat();
        int n2 = em2.readInt();
        this.e = Lists.newArrayListWithCapacity(n2);
        \u2603 = (int)this.a;
        \u2603 = (int)this.b;
        \u2603 = (int)this.c;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            \u2603 = em2.readByte() + \u2603;
            \u2603 = em2.readByte() + \u2603;
            \u2603 = em2.readByte() + \u2603;
            this.e.add(new cj(\u2603, \u2603, \u2603));
        }
        this.f = em2.readFloat();
        this.g = em2.readFloat();
        this.h = em2.readFloat();
    }

    @Override
    public void b(em em22) throws IOException {
        em em22;
        em22.writeFloat((float)this.a);
        em22.writeFloat((float)this.b);
        em22.writeFloat((float)this.c);
        em22.writeFloat(this.d);
        em22.writeInt(this.e.size());
        int n2 = (int)this.a;
        \u2603 = (int)this.b;
        \u2603 = (int)this.c;
        for (cj cj2 : this.e) {
            int n3 = cj2.n() - n2;
            \u2603 = cj2.o() - \u2603;
            \u2603 = cj2.p() - \u2603;
            em22.writeByte(n3);
            em22.writeByte(\u2603);
            em22.writeByte(\u2603);
        }
        em22.writeFloat(this.f);
        em22.writeFloat(this.g);
        em22.writeFloat(this.h);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public float a() {
        return this.f;
    }

    public float b() {
        return this.g;
    }

    public float c() {
        return this.h;
    }

    public double d() {
        return this.a;
    }

    public double e() {
        return this.b;
    }

    public double f() {
        return this.c;
    }

    public float g() {
        return this.d;
    }

    public List<cj> h() {
        return this.e;
    }
}

