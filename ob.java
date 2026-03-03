/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ob
extends oa.a {
    private zx b;
    private int c;
    private int d;

    public ob(zw zw2, int n2, int n3, int n4, int n5) {
        super(n5);
        this.b = new zx(zw2, 1, n2);
        this.c = n3;
        this.d = n4;
    }

    public ob(zx zx2, int n2, int n3, int n4) {
        super(n4);
        this.b = zx2;
        this.c = n2;
        this.d = n3;
    }

    public static void a(Random random, List<ob> list, og og2, int n2) {
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            ob ob2 = oa.a(random, list);
            int \u26032 = ob2.c + random.nextInt(ob2.d - ob2.c + 1);
            if (ob2.b.c() >= \u26032) {
                zx zx2 = ob2.b.k();
                zx2.b = \u26032;
                og2.a(random.nextInt(og2.o_()), zx2);
                continue;
            }
            for (int i2 = 0; i2 < \u26032; ++i2) {
                zx zx3 = ob2.b.k();
                zx3.b = 1;
                og2.a(random.nextInt(og2.o_()), zx3);
            }
        }
    }

    public static void a(Random random, List<ob> list, alc alc2, int n2) {
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            ob ob2 = oa.a(random, list);
            int \u26032 = ob2.c + random.nextInt(ob2.d - ob2.c + 1);
            if (ob2.b.c() >= \u26032) {
                zx zx2 = ob2.b.k();
                zx2.b = \u26032;
                alc2.a(random.nextInt(alc2.o_()), zx2);
                continue;
            }
            for (int i2 = 0; i2 < \u26032; ++i2) {
                zx zx3 = ob2.b.k();
                zx3.b = 1;
                alc2.a(random.nextInt(alc2.o_()), zx3);
            }
        }
    }

    public static List<ob> a(List<ob> list, ob ... obArray) {
        ArrayList<ob> arrayList = Lists.newArrayList(list);
        Collections.addAll(arrayList, obArray);
        return arrayList;
    }
}

