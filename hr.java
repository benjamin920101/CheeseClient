/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;

public class hr
implements ff<fj> {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e;
    private int f;
    private Collection<String> g;
    private int h;
    private int i;

    public hr() {
        this.e = auq.a.a.e;
        this.f = -1;
        this.g = Lists.newArrayList();
    }

    public hr(aul aul2, int n2) {
        this.e = auq.a.a.e;
        this.f = -1;
        this.g = Lists.newArrayList();
        this.a = aul2.b();
        this.h = n2;
        if (n2 == 0 || n2 == 2) {
            this.b = aul2.c();
            this.c = aul2.e();
            this.d = aul2.f();
            this.i = aul2.k();
            this.e = aul2.i().e;
            this.f = aul2.l().b();
        }
        if (n2 == 0) {
            this.g.addAll(aul2.d());
        }
    }

    public hr(aul aul2, Collection<String> collection, int n2) {
        this.e = auq.a.a.e;
        this.f = -1;
        this.g = Lists.newArrayList();
        if (n2 != 3 && n2 != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        }
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Players cannot be null/empty");
        }
        this.h = n2;
        this.a = aul2.b();
        this.g.addAll(collection);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(16);
        this.h = em2.readByte();
        if (this.h == 0 || this.h == 2) {
            this.b = em2.c(32);
            this.c = em2.c(16);
            this.d = em2.c(16);
            this.i = em2.readByte();
            this.e = em2.c(32);
            this.f = em2.readByte();
        }
        if (this.h == 0 || this.h == 3 || this.h == 4) {
            int n2 = em2.e();
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                this.g.add(em2.c(40));
            }
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte(this.h);
        if (this.h == 0 || this.h == 2) {
            em2.a(this.b);
            em2.a(this.c);
            em2.a(this.d);
            em2.writeByte(this.i);
            em2.a(this.e);
            em2.writeByte(this.f);
        }
        if (this.h == 0 || this.h == 3 || this.h == 4) {
            em2.b(this.g.size());
            for (String string : this.g) {
                em2.a(string);
            }
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Collection<String> e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return this.f;
    }

    public String i() {
        return this.e;
    }
}

