/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ia
implements ff<fj> {
    private int a;
    private final List<a> b = Lists.newArrayList();

    public ia() {
    }

    public ia(int n2, Collection<qc> collection) {
        this.a = n2;
        for (qc qc2 : collection) {
            this.b.add(new a(qc2.a().a(), qc2.b(), qc2.c()));
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        int n2 = em2.readInt();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            String string = em2.c(64);
            double \u26032 = em2.readDouble();
            ArrayList<qd> \u26033 = Lists.newArrayList();
            int \u26034 = em2.e();
            for (int i2 = 0; i2 < \u26034; ++i2) {
                UUID uUID = em2.g();
                \u26033.add(new qd(uUID, "Unknown synced attribute modifier", em2.readDouble(), em2.readByte()));
            }
            this.b.add(new a(string, \u26032, \u26033));
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeInt(this.b.size());
        for (a a2 : this.b) {
            em2.a(a2.a());
            em2.writeDouble(a2.b());
            em2.b(a2.c().size());
            for (qd qd2 : a2.c()) {
                em2.a(qd2.a());
                em2.writeDouble(qd2.d());
                em2.writeByte(qd2.c());
            }
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public List<a> b() {
        return this.b;
    }

    public class a {
        private final String b;
        private final double c;
        private final Collection<qd> d;

        public a(String string, double d2, Collection<qd> collection) {
            this.b = string;
            this.c = d2;
            this.d = collection;
        }

        public String a() {
            return this.b;
        }

        public double b() {
            return this.c;
        }

        public Collection<qd> c() {
            return this.d;
        }
    }
}

