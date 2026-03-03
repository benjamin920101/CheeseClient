/*
 * Decompiled with CFR 0.152.
 */
public class bnr
implements Comparable<bnr> {
    private final String a;
    private final String b;
    private final String c;
    private final boolean d;

    public bnr(String string, String string2, String string3, boolean bl2) {
        this.a = string;
        this.b = string2;
        this.c = string3;
        this.d = bl2;
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.d;
    }

    public String toString() {
        return String.format("%s (%s)", this.c, this.b);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof bnr)) {
            return false;
        }
        return this.a.equals(((bnr)object).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public int a(bnr bnr2) {
        return this.a.compareTo(bnr2.a);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((bnr)object);
    }
}

