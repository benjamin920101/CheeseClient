/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aut
extends aus {
    public aut(String string) {
        super(string);
    }

    @Override
    public int a(List<wn> list2) {
        List<wn> list2;
        float f2 = 0.0f;
        for (wn wn2 : list2) {
            f2 += wn2.bn() + wn2.bN();
        }
        if (list2.size() > 0) {
            f2 /= (float)list2.size();
        }
        return ns.f(f2);
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public auu.a c() {
        return auu.a.b;
    }
}

