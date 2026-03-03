/*
 * Decompiled with CFR 0.152.
 */
public class fa
extends es {
    private final String b;

    public fa(String string) {
        this.b = string;
    }

    public String g() {
        return this.b;
    }

    @Override
    public String e() {
        return this.b;
    }

    public fa h() {
        fa fa2 = new fa(this.b);
        fa2.a(this.b().m());
        for (eu eu2 : this.a()) {
            fa2.a(eu2.f());
        }
        return fa2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof fa) {
            fa fa2 = (fa)object;
            return this.b.equals(fa2.g()) && super.equals(object);
        }
        return false;
    }

    @Override
    public String toString() {
        return "TextComponent{text='" + this.b + '\'' + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    @Override
    public /* synthetic */ eu f() {
        return this.h();
    }
}

