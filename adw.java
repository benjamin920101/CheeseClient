/*
 * Decompiled with CFR 0.152.
 */
public class adw
implements Comparable<adw> {
    private static long d;
    private final afh e;
    public final cj a;
    public long b;
    public int c;
    private long f = d++;

    public adw(cj cj2, afh afh2) {
        this.a = cj2;
        this.e = afh2;
    }

    public boolean equals(Object object) {
        if (object instanceof adw) {
            adw adw2 = (adw)object;
            return this.a.equals(adw2.a) && afh.a(this.e, adw2.e);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public adw a(long l2) {
        this.b = l2;
        return this;
    }

    public void a(int n2) {
        this.c = n2;
    }

    public int a(adw adw2) {
        if (this.b < adw2.b) {
            return -1;
        }
        if (this.b > adw2.b) {
            return 1;
        }
        if (this.c != adw2.c) {
            return this.c - adw2.c;
        }
        if (this.f < adw2.f) {
            return -1;
        }
        if (this.f > adw2.f) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return afh.a(this.e) + ": " + this.a + ", " + this.b + ", " + this.c + ", " + this.f;
    }

    public afh a() {
        return this.e;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((adw)object);
    }
}

