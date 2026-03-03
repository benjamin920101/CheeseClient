/*
 * Decompiled with CFR 0.152.
 */
public class ats
implements Comparable<ats> {
    private final String a;
    private final String b;
    private final long c;
    private final long d;
    private final boolean e;
    private final adp.a f;
    private final boolean g;
    private final boolean h;

    public ats(String string, String string2, long l2, long l3, adp.a a2, boolean bl2, boolean bl3, boolean bl4) {
        this.a = string;
        this.b = string2;
        this.c = l2;
        this.d = l3;
        this.f = a2;
        this.e = bl2;
        this.g = bl3;
        this.h = bl4;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public long c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public long e() {
        return this.c;
    }

    public int a(ats ats2) {
        if (this.c < ats2.c) {
            return 1;
        }
        if (this.c > ats2.c) {
            return -1;
        }
        return this.a.compareTo(ats2.a);
    }

    public adp.a f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((ats)object);
    }
}

