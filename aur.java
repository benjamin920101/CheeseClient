/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aur
implements auu {
    private final String j;

    public aur(String string, a a2) {
        this.j = string + a2.e();
        auu.a.put(this.j, this);
    }

    @Override
    public String a() {
        return this.j;
    }

    @Override
    public int a(List<wn> list) {
        return 0;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public auu.a c() {
        return auu.a.a;
    }
}

