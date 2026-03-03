/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bns
implements bnj {
    private static final Logger b = LogManager.getLogger();
    private final bny c;
    private String d;
    protected static final bnt a = new bnt();
    private Map<String, bnr> e = Maps.newHashMap();

    public bns(bny bny2, String string) {
        this.c = bny2;
        this.d = string;
        bnq.a(a);
    }

    public void a(List<bnk> list) {
        this.e.clear();
        for (bnk bnk2 : list) {
            try {
                bog bog2 = (bog)bnk2.a(this.c, "language");
                if (bog2 == null) continue;
                for (bnr bnr2 : bog2.a()) {
                    if (this.e.containsKey(bnr2.a())) continue;
                    this.e.put(bnr2.a(), bnr2);
                }
            }
            catch (RuntimeException runtimeException) {
                b.warn("Unable to parse metadata section of resourcepack: " + bnk2.b(), (Throwable)runtimeException);
            }
            catch (IOException iOException) {
                b.warn("Unable to parse metadata section of resourcepack: " + bnk2.b(), (Throwable)iOException);
            }
        }
    }

    @Override
    public void a(bni bni2) {
        ArrayList<String> arrayList = Lists.newArrayList("en_US");
        if (!"en_US".equals(this.d)) {
            arrayList.add(this.d);
        }
        a.a(bni2, arrayList);
        dj.a(bns.a.a);
    }

    public boolean a() {
        return a.a();
    }

    public boolean b() {
        return this.c() != null && this.c().b();
    }

    public void a(bnr bnr2) {
        this.d = bnr2.a();
    }

    public bnr c() {
        return this.e.containsKey(this.d) ? this.e.get(this.d) : this.e.get("en_US");
    }

    public SortedSet<bnr> d() {
        return Sets.newTreeSet(this.e.values());
    }
}

