/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ev {
    public static eu a(m m2, eu eu22, pk pk2) throws bz {
        Object object;
        eu \u26033 = null;
        if (eu22 instanceof ex) {
            ex \u26034 = (ex)eu22;
            String \u26032 = \u26034.g();
            if (o.b(\u26032)) {
                List<pk> list = o.b(m2, \u26032, pk.class);
                if (list.size() == 1) {
                    \u26032 = list.get(0).e_();
                } else {
                    throw new ca();
                }
            }
            \u26033 = pk2 != null && \u26032.equals("*") ? new ex(pk2.e_(), \u26034.h()) : new ex(\u26032, \u26034.h());
            ((ex)\u26033).b(\u26034.e());
        } else if (eu22 instanceof ey) {
            object = ((ey)eu22).g();
            \u26033 = o.b(m2, (String)object);
            if (\u26033 == null) {
                \u26033 = new fa("");
            }
        } else if (eu22 instanceof fa) {
            \u26033 = new fa(((fa)eu22).g());
        } else if (eu22 instanceof fb) {
            object = ((fb)eu22).j();
            for (int i2 = 0; i2 < ((Object[])object).length; ++i2) {
                Object object2 = object[i2];
                if (!(object2 instanceof eu)) continue;
                object[i2] = ev.a(m2, (eu)object2, pk2);
            }
            \u26033 = new fb(((fb)eu22).i(), (Object[])object);
        } else {
            return eu22;
        }
        object = eu22.b();
        if (object != null) {
            \u26033.a(((ez)object).m());
        }
        for (eu eu2 : eu22.a()) {
            \u26033.a(ev.a(m2, eu2, pk2));
        }
        return \u26033;
    }
}

