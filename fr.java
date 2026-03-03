/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;

public class fr
implements ff<fj> {
    private Map<mw, Integer> a;

    public fr() {
    }

    public fr(Map<mw, Integer> map) {
        this.a = map;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        int n2 = em2.e();
        this.a = Maps.newHashMap();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            mw mw2 = na.a(em2.c(Short.MAX_VALUE));
            int \u26032 = em2.e();
            if (mw2 == null) continue;
            this.a.put(mw2, \u26032);
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a.size());
        for (Map.Entry<mw, Integer> entry : this.a.entrySet()) {
            em2.a(entry.getKey().e);
            em2.b(entry.getValue());
        }
    }

    public Map<mw, Integer> a() {
        return this.a;
    }
}

