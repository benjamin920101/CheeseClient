/*
 * Decompiled with CFR 0.152.
 */
public class ey
extends es {
    private final String b;

    public ey(String string) {
        this.b = string;
    }

    public String g() {
        return this.b;
    }

    @Override
    public String e() {
        return this.b;
    }

    public ey h() {
        ey ey2 = new ey(this.b);
        ey2.a(this.b().m());
        for (eu eu2 : this.a()) {
            ey2.a(eu2.f());
        }
        return ey2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof ey) {
            ey ey2 = (ey)object;
            return this.b.equals(ey2.b) && super.equals(object);
        }
        return false;
    }

    @Override
    public String toString() {
        return "SelectorComponent{pattern='" + this.b + '\'' + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    @Override
    public /* synthetic */ eu f() {
        return this.h();
    }
}

