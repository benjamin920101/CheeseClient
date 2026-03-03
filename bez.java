/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class bez
extends oq
implements oo {
    private String a;
    private Map<Integer, Integer> b = Maps.newHashMap();

    public bez(String string, eu eu2, int n2) {
        super(eu2, n2);
        this.a = string;
    }

    @Override
    public int a_(int n2) {
        if (this.b.containsKey(n2)) {
            return this.b.get(n2);
        }
        return 0;
    }

    @Override
    public void b(int n2, int n3) {
        this.b.put(n2, n3);
    }

    @Override
    public int g() {
        return this.b.size();
    }

    @Override
    public boolean r_() {
        return false;
    }

    @Override
    public void a(on on2) {
    }

    @Override
    public on i() {
        return on.a;
    }

    @Override
    public String k() {
        return this.a;
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        throw new UnsupportedOperationException();
    }
}

