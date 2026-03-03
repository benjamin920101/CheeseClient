/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public abstract class bgu
implements bgy {
    protected Map<alz, bov> b = Maps.newLinkedHashMap();

    public String a(Map<amo, Comparable> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<amo, Comparable> entry : map.entrySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            amo amo2 = entry.getKey();
            Comparable \u26032 = entry.getValue();
            stringBuilder.append(amo2.a());
            stringBuilder.append("=");
            stringBuilder.append(amo2.a(\u26032));
        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append("normal");
        }
        return stringBuilder.toString();
    }

    @Override
    public Map<alz, bov> a(afh afh2) {
        for (alz alz2 : afh2.P().a()) {
            this.b.put(alz2, this.a(alz2));
        }
        return this.b;
    }

    protected abstract bov a(alz var1);
}

