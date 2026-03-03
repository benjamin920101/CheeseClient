/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class bhn {
    private final bht a;
    private final ReentrantLock b = new ReentrantLock();
    private final List<Runnable> c = Lists.newArrayList();
    private final b d;
    private bfg e;
    private bhq f;
    private a g = bhn$a.a;
    private boolean h;

    public bhn(bht bht2, b b2) {
        this.a = bht2;
        this.d = b2;
    }

    public a a() {
        return this.g;
    }

    public bht b() {
        return this.a;
    }

    public bhq c() {
        return this.f;
    }

    public void a(bhq bhq2) {
        this.f = bhq2;
    }

    public bfg d() {
        return this.e;
    }

    public void a(bfg bfg2) {
        this.e = bfg2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(a a2) {
        this.b.lock();
        try {
            this.g = a2;
        }
        finally {
            this.b.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void e() {
        this.b.lock();
        try {
            if (this.d == bhn$b.a && this.g != bhn$a.d) {
                this.a.a(true);
            }
            this.h = true;
            this.g = bhn$a.d;
            for (Runnable runnable : this.c) {
                runnable.run();
            }
        }
        finally {
            this.b.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(Runnable runnable) {
        this.b.lock();
        try {
            this.c.add(runnable);
            if (this.h) {
                runnable.run();
            }
        }
        finally {
            this.b.unlock();
        }
    }

    public ReentrantLock f() {
        return this.b;
    }

    public b g() {
        return this.d;
    }

    public boolean h() {
        return this.h;
    }

    public static enum a {
        a,
        b,
        c,
        d;

    }

    public static enum b {
        a,
        b;

    }
}

