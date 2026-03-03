/*
 * Decompiled with CFR 0.152.
 */
public class auk {
    private final auo a;
    private final String b;
    private final auu c;
    private auu.a d;
    private String e;

    public auk(auo auo2, String string, auu auu2) {
        this.a = auo2;
        this.b = string;
        this.c = auu2;
        this.e = string;
        this.d = auu2.c();
    }

    public auo a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public auu c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public void a(String string) {
        this.e = string;
        this.a.b(this);
    }

    public auu.a e() {
        return this.d;
    }

    public void a(auu.a a2) {
        this.d = a2;
        this.a.b(this);
    }
}

