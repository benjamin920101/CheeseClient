/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;

public class gz
implements ff<fj> {
    private a a;
    private final List<b> b = Lists.newArrayList();

    public gz() {
    }

    public gz(a a2, lf ... lfArray) {
        this.a = a2;
        for (lf lf2 : lfArray) {
            this.b.add(new b(lf2.cd(), lf2.h, lf2.c.b(), lf2.E()));
        }
    }

    public gz(a a2, Iterable<lf> iterable) {
        this.a = a2;
        for (lf lf2 : iterable) {
            this.b.add(new b(lf2.cd(), lf2.h, lf2.c.b(), lf2.E()));
        }
    }

    @Override
    public void a(em em22) throws IOException {
        this.a = em22.a(a.class);
        int n2 = em22.e();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            GameProfile \u26035 = null;
            int \u26032 = 0;
            adp.a \u26033 = null;
            eu \u26034 = null;
            switch (this.a) {
                case a: {
                    em em22;
                    \u26035 = new GameProfile(em22.g(), em22.c(16));
                    int n3 = em22.e();
                    for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                        String string = em22.c(Short.MAX_VALUE);
                        \u2603 = em22.c(Short.MAX_VALUE);
                        if (em22.readBoolean()) {
                            \u26035.getProperties().put(string, new Property(string, \u2603, em22.c(Short.MAX_VALUE)));
                            continue;
                        }
                        \u26035.getProperties().put(string, new Property(string, \u2603));
                    }
                    \u26033 = adp.a.a(em22.e());
                    \u26032 = em22.e();
                    if (!em22.readBoolean()) break;
                    \u26034 = em22.d();
                    break;
                }
                case b: {
                    em em22;
                    \u26035 = new GameProfile(em22.g(), null);
                    \u26033 = adp.a.a(em22.e());
                    break;
                }
                case c: {
                    \u26035 = new GameProfile(em22.g(), null);
                    \u26032 = em22.e();
                    break;
                }
                case d: {
                    \u26035 = new GameProfile(em22.g(), null);
                    if (!em22.readBoolean()) break;
                    \u26034 = em22.d();
                    break;
                }
                case e: {
                    \u26035 = new GameProfile(em22.g(), null);
                }
            }
            this.b.add(new b(\u26035, \u26032, \u26033, \u26034));
        }
    }

    @Override
    public void b(em em22) throws IOException {
        em22.a(this.a);
        em22.b(this.b.size());
        for (b b2 : this.b) {
            switch (this.a) {
                case a: {
                    em em22;
                    em22.a(b2.a().getId());
                    em22.a(b2.a().getName());
                    em22.b(b2.a().getProperties().size());
                    for (Property property : b2.a().getProperties().values()) {
                        em22.a(property.getName());
                        em22.a(property.getValue());
                        if (property.hasSignature()) {
                            em22.writeBoolean(true);
                            em22.a(property.getSignature());
                            continue;
                        }
                        em22.writeBoolean(false);
                    }
                    em22.b(b2.c().a());
                    em22.b(b2.b());
                    if (b2.d() == null) {
                        em22.writeBoolean(false);
                        break;
                    }
                    em22.writeBoolean(true);
                    em22.a(b2.d());
                    break;
                }
                case b: {
                    em em22;
                    em22.a(b2.a().getId());
                    em22.b(b2.c().a());
                    break;
                }
                case c: {
                    em em22;
                    em22.a(b2.a().getId());
                    em22.b(b2.b());
                    break;
                }
                case d: {
                    em em22;
                    em22.a(b2.a().getId());
                    if (b2.d() == null) {
                        em22.writeBoolean(false);
                        break;
                    }
                    em22.writeBoolean(true);
                    em22.a(b2.d());
                    break;
                }
                case e: {
                    em em22;
                    em22.a(b2.a().getId());
                }
            }
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public List<b> a() {
        return this.b;
    }

    public a b() {
        return this.a;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("action", (Object)this.a).add("entries", this.b).toString();
    }

    public class b {
        private final int b;
        private final adp.a c;
        private final GameProfile d;
        private final eu e;

        public b(GameProfile gameProfile, int n2, adp.a a2, eu eu2) {
            this.d = gameProfile;
            this.b = n2;
            this.c = a2;
            this.e = eu2;
        }

        public GameProfile a() {
            return this.d;
        }

        public int b() {
            return this.b;
        }

        public adp.a c() {
            return this.c;
        }

        public eu d() {
            return this.e;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("latency", this.b).add("gameMode", (Object)this.c).add("profile", this.d).add("displayName", this.e == null ? null : eu.a.a(this.e)).toString();
        }
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e;

    }
}

