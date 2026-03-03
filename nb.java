/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class nb {
    protected final Map<mw, my> a = Maps.newConcurrentMap();

    public boolean a(mq mq2) {
        return this.a((mw)mq2) > 0;
    }

    public boolean b(mq mq2) {
        return mq2.c == null || this.a(mq2.c);
    }

    public int c(mq mq2) {
        if (this.a(mq2)) {
            return 0;
        }
        int n2 = 0;
        mq \u26032 = mq2.c;
        while (\u26032 != null && !this.a(\u26032)) {
            \u26032 = \u26032.c;
            ++n2;
        }
        return n2;
    }

    public void b(wn wn2, mw mw2, int n2) {
        if (mw2.d() && !this.b((mq)mw2)) {
            return;
        }
        this.a(wn2, mw2, this.a(mw2) + n2);
    }

    public void a(wn wn2, mw mw2, int n2) {
        my my2 = this.a.get(mw2);
        if (my2 == null) {
            my2 = new my();
            this.a.put(mw2, my2);
        }
        my2.a(n2);
    }

    public int a(mw mw2) {
        my my2 = this.a.get(mw2);
        return my2 == null ? 0 : my2.a();
    }

    public <T extends mz> T b(mw mw2) {
        my my2 = this.a.get(mw2);
        if (my2 != null) {
            return my2.b();
        }
        return null;
    }

    public <T extends mz> T a(mw mw2, T t2) {
        my my2 = this.a.get(mw2);
        if (my2 == null) {
            my2 = new my();
            this.a.put(mw2, my2);
        }
        my2.a(t2);
        return t2;
    }
}

