/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class ta {
    ps a;
    List<pk> b = Lists.newArrayList();
    List<pk> c = Lists.newArrayList();

    public ta(ps ps2) {
        this.a = ps2;
    }

    public void a() {
        this.b.clear();
        this.c.clear();
    }

    public boolean a(pk pk2) {
        if (this.b.contains(pk2)) {
            return true;
        }
        if (this.c.contains(pk2)) {
            return false;
        }
        this.a.o.B.a("canSee");
        boolean bl2 = this.a.t(pk2);
        this.a.o.B.b();
        if (bl2) {
            this.b.add(pk2);
        } else {
            this.c.add(pk2);
        }
        return bl2;
    }
}

