/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Function;

public class aae
extends yo {
    protected final afh b;
    protected final Function<zx, String> c;

    public aae(afh afh2, afh afh3, Function<zx, String> function) {
        super(afh2);
        this.b = afh3;
        this.c = function;
        this.d(0);
        this.a(true);
    }

    public aae(afh afh2, afh afh3, final String[] stringArray) {
        this(afh2, afh3, new Function<zx, String>(){

            public String a(zx zx2) {
                int n2 = zx2.i();
                if (n2 < 0 || n2 >= stringArray.length) {
                    n2 = 0;
                }
                return stringArray[n2];
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        });
    }

    @Override
    public int a(int n2) {
        return n2;
    }

    @Override
    public String e_(zx zx2) {
        return super.a() + "." + this.c.apply(zx2);
    }
}

