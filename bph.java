/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class bph {
    private final List<a> a = Lists.newArrayList();
    private boolean b;
    private bpg c;

    public List<a> a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean bl2) {
        this.b = bl2;
    }

    public bpg c() {
        return this.c;
    }

    public void a(bpg bpg2) {
        this.c = bpg2;
    }

    public static class bph$a {
        private String a;
        private float b = 1.0f;
        private float c = 1.0f;
        private int d = 1;
        private a e = a.a;
        private boolean f = false;

        public String a() {
            return this.a;
        }

        public void a(String string) {
            this.a = string;
        }

        public float b() {
            return this.b;
        }

        public void a(float f2) {
            this.b = f2;
        }

        public float c() {
            return this.c;
        }

        public void b(float f2) {
            this.c = f2;
        }

        public int d() {
            return this.d;
        }

        public void a(int n2) {
            this.d = n2;
        }

        public a e() {
            return this.e;
        }

        public void a(a a2) {
            this.e = a2;
        }

        public boolean f() {
            return this.f;
        }

        public void a(boolean bl2) {
            this.f = bl2;
        }

        public static enum a {
            a("file"),
            b("event");

            private final String c;

            private a(String string2) {
                this.c = string2;
            }

            public static a a(String string) {
                for (a a2 : bph$a$a.values()) {
                    if (!a2.c.equals(string)) continue;
                    return a2;
                }
                return null;
            }
        }
    }
}

