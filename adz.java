/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class adz {
    private final aec a;
    private long b;
    private nq<a> c = new nq();
    private List<a> d = Lists.newArrayList();

    public adz(aec aec2) {
        this.a = aec2;
    }

    public a a(int n2, int n3) {
        long l2 = (long)(n2 >>= 4) & 0xFFFFFFFFL | ((long)(n3 >>= 4) & 0xFFFFFFFFL) << 32;
        a \u26032 = this.c.a(l2);
        if (\u26032 == null) {
            \u26032 = new a(n2, n3);
            this.c.a(l2, \u26032);
            this.d.add(\u26032);
        }
        \u26032.e = MinecraftServer.az();
        return \u26032;
    }

    public ady a(int n2, int n3, ady ady2) {
        \u2603 = this.a(n2, n3).a(n2, n3);
        if (\u2603 == null) {
            return ady2;
        }
        return \u2603;
    }

    public void a() {
        long l2 = MinecraftServer.az();
        \u2603 = l2 - this.b;
        if (\u2603 > 7500L || \u2603 < 0L) {
            this.b = l2;
            for (int i2 = 0; i2 < this.d.size(); ++i2) {
                a a2 = this.d.get(i2);
                long \u26032 = l2 - a2.e;
                if (\u26032 <= 30000L && \u26032 >= 0L) continue;
                this.d.remove(i2--);
                long \u26033 = (long)a2.c & 0xFFFFFFFFL | ((long)a2.d & 0xFFFFFFFFL) << 32;
                this.c.d(\u26033);
            }
        }
    }

    public ady[] c(int n2, int n3) {
        return this.a((int)n2, (int)n3).b;
    }

    public class a {
        public float[] a = new float[256];
        public ady[] b = new ady[256];
        public int c;
        public int d;
        public long e;

        public a(int n2, int n3) {
            this.c = n2;
            this.d = n3;
            adz.this.a.a(this.a, n2 << 4, n3 << 4, 16, 16);
            adz.this.a.a(this.b, n2 << 4, n3 << 4, 16, 16, false);
        }

        public ady a(int n2, int n3) {
            return this.b[n2 & 0xF | (n3 & 0xF) << 4];
        }
    }
}

