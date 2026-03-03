/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class bgx
extends bgu {
    private final amo<?> a;
    private final String c;
    private final List<amo<?>> d;

    private bgx(amo<?> amo2, String string, List<amo<?>> list) {
        this.a = amo2;
        this.c = string;
        this.d = list;
    }

    @Override
    protected bov a(alz alz2) {
        LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
        String \u26032 = this.a == null ? ((jy)afh.c.c(alz2.c())).toString() : this.a.a((Comparable)linkedHashMap.remove(this.a));
        if (this.c != null) {
            \u26032 = \u26032 + this.c;
        }
        for (amo<?> amo2 : this.d) {
            linkedHashMap.remove(amo2);
        }
        return new bov(\u26032, this.a(linkedHashMap));
    }

    public static class a {
        private amo<?> a;
        private String b;
        private final List<amo<?>> c = Lists.newArrayList();

        public a a(amo<?> amo2) {
            this.a = amo2;
            return this;
        }

        public a a(String string) {
            this.b = string;
            return this;
        }

        public a a(amo<?> ... amoArray) {
            Collections.addAll(this.c, amoArray);
            return this;
        }

        public bgx a() {
            return new bgx(this.a, this.b, this.c);
        }
    }
}

