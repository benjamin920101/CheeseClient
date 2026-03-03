/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class bak
implements bag,
bah {
    private static final Ordering<bdc> a = Ordering.from(new Comparator<bdc>(){

        public int a(bdc bdc2, bdc bdc3) {
            return ComparisonChain.start().compare(bdc2.a().getId(), bdc3.a().getId()).result();
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((bdc)object, (bdc)object2);
        }
    });
    private final List<bah> b = Lists.newArrayList();

    public bak() {
        this(a.sortedCopy(ave.A().u().d()));
    }

    public bak(Collection<bdc> collection) {
        for (bdc bdc2 : a.sortedCopy(collection)) {
            if (bdc2.b() == adp.a.e) continue;
            this.b.add(new bad(bdc2.a()));
        }
    }

    @Override
    public List<bah> a() {
        return this.b;
    }

    @Override
    public eu b() {
        return new fa("Select a player to teleport to");
    }

    @Override
    public void a(baf baf2) {
        baf2.a(this);
    }

    @Override
    public eu A_() {
        return new fa("Teleport to player");
    }

    @Override
    public void a(float f2, int n2) {
        ave.A().P().a(awm.a);
        avp.a(0, 0, 0.0f, 0.0f, 16, 16, 256.0f, 256.0f);
    }

    @Override
    public boolean B_() {
        return !this.b.isEmpty();
    }
}

