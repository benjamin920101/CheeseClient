/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;

public class bgp {
    public static final List<String> a = Lists.newArrayList("layer0", "layer1", "layer2", "layer3", "layer4");

    public bgl a(bmh bmh2, bgl bgl2) {
        HashMap<String, String> hashMap = Maps.newHashMap();
        ArrayList<bgh> \u26032 = Lists.newArrayList();
        for (int i2 = 0; i2 < a.size() && bgl2.b(\u2603 = a.get(i2)); ++i2) {
            String string = bgl2.c(\u2603);
            hashMap.put(\u2603, string);
            bmi \u26033 = bmh2.a(new jy(string).toString());
            \u26032.addAll(this.a(i2, \u2603, \u26033));
        }
        if (\u26032.isEmpty()) {
            return null;
        }
        hashMap.put("particle", bgl2.b("particle") ? bgl2.c("particle") : (String)hashMap.get("layer0"));
        return new bgl(\u26032, hashMap, false, false, bgl2.g());
    }

    private List<bgh> a(int n2, String string, bmi bmi2) {
        HashMap<cq, bgi> hashMap = Maps.newHashMap();
        hashMap.put(cq.d, new bgi(null, n2, string, new bgk(new float[]{0.0f, 0.0f, 16.0f, 16.0f}, 0)));
        hashMap.put(cq.c, new bgi(null, n2, string, new bgk(new float[]{16.0f, 0.0f, 0.0f, 16.0f}, 0)));
        ArrayList<bgh> \u26032 = Lists.newArrayList();
        \u26032.add(new bgh(new Vector3f(0.0f, 0.0f, 7.5f), new Vector3f(16.0f, 16.0f, 8.5f), hashMap, null, true));
        \u26032.addAll(this.a(bmi2, string, n2));
        return \u26032;
    }

    private List<bgh> a(bmi bmi2, String string, int n2) {
        float f2 = bmi2.c();
        \u2603 = bmi2.d();
        ArrayList<bgh> \u26032 = Lists.newArrayList();
        for (a a2 : this.a(bmi2)) {
            float f3 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = a2.b();
            \u2603 = a2.c();
            \u2603 = a2.d();
            b \u26033 = a2.a();
            switch (\u26033) {
                case a: {
                    f3 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603 + 1.0f;
                    \u2603 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603;
                    \u2603 = 16.0f / f2;
                    \u2603 = 16.0f / (\u2603 - 1.0f);
                    break;
                }
                case b: {
                    \u2603 = \u2603 = \u2603;
                    f3 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603 + 1.0f;
                    \u2603 = \u2603 + 1.0f;
                    \u2603 = \u2603 + 1.0f;
                    \u2603 = 16.0f / f2;
                    \u2603 = 16.0f / (\u2603 - 1.0f);
                    break;
                }
                case c: {
                    f3 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603 + 1.0f;
                    \u2603 = 16.0f / (f2 - 1.0f);
                    \u2603 = 16.0f / \u2603;
                    break;
                }
                case d: {
                    \u2603 = \u2603 = \u2603;
                    f3 = \u2603 + 1.0f;
                    \u2603 = \u2603 + 1.0f;
                    \u2603 = \u2603 = \u2603;
                    \u2603 = \u2603 = \u2603 + 1.0f;
                    \u2603 = 16.0f / (f2 - 1.0f);
                    \u2603 = 16.0f / \u2603;
                }
            }
            \u2603 = 16.0f / f2;
            \u2603 = 16.0f / \u2603;
            f3 *= \u2603;
            \u2603 *= \u2603;
            \u2603 *= \u2603;
            \u2603 *= \u2603;
            \u2603 = 16.0f - \u2603;
            \u2603 = 16.0f - \u2603;
            HashMap<cq, bgi> \u26034 = Maps.newHashMap();
            \u26034.put(\u26033.a(), new bgi(null, n2, string, new bgk(new float[]{\u2603 *= \u2603, \u2603 *= \u2603, \u2603 *= \u2603, \u2603 *= \u2603}, 0)));
            switch (\u26033) {
                case a: {
                    \u26032.add(new bgh(new Vector3f(f3, \u2603, 7.5f), new Vector3f(\u2603, \u2603, 8.5f), \u26034, null, true));
                    break;
                }
                case b: {
                    \u26032.add(new bgh(new Vector3f(f3, \u2603, 7.5f), new Vector3f(\u2603, \u2603, 8.5f), \u26034, null, true));
                    break;
                }
                case c: {
                    \u26032.add(new bgh(new Vector3f(f3, \u2603, 7.5f), new Vector3f(f3, \u2603, 8.5f), \u26034, null, true));
                    break;
                }
                case d: {
                    \u26032.add(new bgh(new Vector3f(\u2603, \u2603, 7.5f), new Vector3f(\u2603, \u2603, 8.5f), \u26034, null, true));
                }
            }
        }
        return \u26032;
    }

    private List<a> a(bmi bmi2) {
        int n2 = bmi2.c();
        \u2603 = bmi2.d();
        ArrayList<a> \u26032 = Lists.newArrayList();
        for (\u2603 = 0; \u2603 < bmi2.k(); ++\u2603) {
            int[] nArray = bmi2.a(\u2603)[0];
            for (int i2 = 0; i2 < \u2603; ++i2) {
                for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                    boolean bl2 = !this.a(nArray, \u2603, i2, n2, \u2603);
                    this.a(b.a, \u26032, nArray, \u2603, i2, n2, \u2603, bl2);
                    this.a(b.b, \u26032, nArray, \u2603, i2, n2, \u2603, bl2);
                    this.a(b.c, \u26032, nArray, \u2603, i2, n2, \u2603, bl2);
                    this.a(b.d, \u26032, nArray, \u2603, i2, n2, \u2603, bl2);
                }
            }
        }
        return \u26032;
    }

    private void a(b b2, List<a> list, int[] nArray, int n2, int n3, int n4, int n5, boolean bl2) {
        boolean bl3 = \u2603 = this.a(nArray, n2 + b2.b(), n3 + b2.c(), n4, n5) && bl2;
        if (\u2603) {
            this.a(list, b2, n2, n3);
        }
    }

    private void a(List<a> list, b b2, int n2, int n3) {
        a a2 = null;
        for (a a3 : list) {
            if (a3.a() != b2) continue;
            int n4 = \u2603 = b2.d() ? n3 : n2;
            if (a3.d() != \u2603) continue;
            a2 = a3;
            break;
        }
        int \u26032 = b2.d() ? n3 : n2;
        int n5 = \u2603 = b2.d() ? n2 : n3;
        if (a2 == null) {
            list.add(new a(b2, \u2603, \u26032));
        } else {
            a2.a(\u2603);
        }
    }

    private boolean a(int[] nArray, int n2, int n3, int n4, int n5) {
        if (n2 < 0 || n3 < 0 || n2 >= n4 || n3 >= n5) {
            return true;
        }
        return (nArray[n3 * n4 + n2] >> 24 & 0xFF) == 0;
    }

    static class a {
        private final b a;
        private int b;
        private int c;
        private final int d;

        public a(b b2, int n2, int n3) {
            this.a = b2;
            this.b = n2;
            this.c = n2;
            this.d = n3;
        }

        public void a(int n2) {
            if (n2 < this.b) {
                this.b = n2;
            } else if (n2 > this.c) {
                this.c = n2;
            }
        }

        public b a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }
    }

    static enum b {
        a(cq.b, 0, -1),
        b(cq.a, 0, 1),
        c(cq.f, -1, 0),
        d(cq.e, 1, 0);

        private final cq e;
        private final int f;
        private final int g;

        private b(cq cq2, int n3, int n4) {
            this.e = cq2;
            this.f = n3;
            this.g = n4;
        }

        public cq a() {
            return this.e;
        }

        public int b() {
            return this.f;
        }

        public int c() {
            return this.g;
        }

        private boolean d() {
            return this == b || this == a;
        }
    }
}

