/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class bgv {
    private Map<afh, bgy> a = Maps.newIdentityHashMap();
    private Set<afh> b = Sets.newIdentityHashSet();

    public void a(afh afh2, bgy bgy2) {
        this.a.put(afh2, bgy2);
    }

    public void a(afh ... afhArray) {
        Collections.addAll(this.b, afhArray);
    }

    public Map<alz, bov> a() {
        IdentityHashMap<alz, bov> identityHashMap = Maps.newIdentityHashMap();
        for (afh afh2 : afh.c) {
            if (this.b.contains(afh2)) continue;
            identityHashMap.putAll(((bgy)Objects.firstNonNull(this.a.get(afh2), new bgw())).a(afh2));
        }
        return identityHashMap;
    }
}

