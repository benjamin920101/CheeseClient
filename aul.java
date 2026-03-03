/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class aul
extends auq {
    private final auo a;
    private final String b;
    private final Set<String> c = Sets.newHashSet();
    private String d;
    private String e = "";
    private String f = "";
    private boolean g = true;
    private boolean h = true;
    private auq.a i = auq.a.a;
    private auq.a j = auq.a.a;
    private a k = a.v;

    public aul(auo auo2, String string) {
        this.a = auo2;
        this.b = string;
        this.d = string;
    }

    @Override
    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public void a(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.d = string;
        this.a.b(this);
    }

    @Override
    public Collection<String> d() {
        return this.c;
    }

    public String e() {
        return this.e;
    }

    public void b(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        }
        this.e = string;
        this.a.b(this);
    }

    public String f() {
        return this.f;
    }

    public void c(String string) {
        this.f = string;
        this.a.b(this);
    }

    @Override
    public String d(String string) {
        return this.e() + string + this.f();
    }

    public static String a(auq auq2, String string) {
        if (auq2 == null) {
            return string;
        }
        return auq2.d(string);
    }

    @Override
    public boolean g() {
        return this.g;
    }

    public void a(boolean bl2) {
        this.g = bl2;
        this.a.b(this);
    }

    @Override
    public boolean h() {
        return this.h;
    }

    public void b(boolean bl2) {
        this.h = bl2;
        this.a.b(this);
    }

    @Override
    public auq.a i() {
        return this.i;
    }

    @Override
    public auq.a j() {
        return this.j;
    }

    public void a(auq.a a2) {
        this.i = a2;
        this.a.b(this);
    }

    public void b(auq.a a2) {
        this.j = a2;
        this.a.b(this);
    }

    public int k() {
        int n2 = 0;
        if (this.g()) {
            n2 |= 1;
        }
        if (this.h()) {
            n2 |= 2;
        }
        return n2;
    }

    public void a(int n2) {
        this.a((n2 & 1) > 0);
        this.b((n2 & 2) > 0);
    }

    public void a(a a2) {
        this.k = a2;
    }

    public a l() {
        return this.k;
    }
}

