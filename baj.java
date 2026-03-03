/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import java.util.List;

public class baj {
    private final bag a;
    private final List<bah> b;
    private final int c;

    public baj(bag bag2, List<bah> list, int n2) {
        this.a = bag2;
        this.b = list;
        this.c = n2;
    }

    public bah a(int n2) {
        if (n2 < 0 || n2 >= this.b.size()) {
            return baf.a;
        }
        return Objects.firstNonNull(this.b.get(n2), baf.a);
    }

    public int b() {
        return this.c;
    }
}

