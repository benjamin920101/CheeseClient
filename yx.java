/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class yx
extends zw {
    public yx() {
        this.a(true);
        this.d(0);
        this.a(yz.l);
    }

    @Override
    public String e_(zx zx2) {
        if (zx2.i() == 1) {
            return "item.charcoal";
        }
        return "item.coal";
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
        list.add(new zx(zw2, 1, 1));
    }
}

