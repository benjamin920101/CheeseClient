/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class bjd
extends bjo<tp> {
    private static final Map<String, jy> a = Maps.newHashMap();
    private static final jy e = new jy("textures/entity/horse/horse_white.png");
    private static final jy j = new jy("textures/entity/horse/mule.png");
    private static final jy k = new jy("textures/entity/horse/donkey.png");
    private static final jy l = new jy("textures/entity/horse/horse_zombie.png");
    private static final jy m = new jy("textures/entity/horse/horse_skeleton.png");

    public bjd(biu biu2, bbh bbh2, float f2) {
        super(biu2, bbh2, f2);
    }

    @Override
    protected void a(tp tp2, float f2) {
        \u2603 = 1.0f;
        int n2 = tp2.cl();
        if (n2 == 1) {
            \u2603 *= 0.87f;
        } else if (n2 == 2) {
            \u2603 *= 0.92f;
        }
        bfl.a(\u2603, \u2603, \u2603);
        super.a(tp2, f2);
    }

    @Override
    protected jy a(tp tp2) {
        if (!tp2.cJ()) {
            switch (tp2.cl()) {
                default: {
                    return e;
                }
                case 2: {
                    return j;
                }
                case 1: {
                    return k;
                }
                case 3: {
                    return l;
                }
                case 4: 
            }
            return m;
        }
        return this.b(tp2);
    }

    private jy b(tp tp2) {
        String string = tp2.cL();
        if (!tp2.cK()) {
            return null;
        }
        jy \u26032 = a.get(string);
        if (\u26032 == null) {
            \u26032 = new jy(string);
            ave.A().P().a(\u26032, new bmd(tp2.cM()));
            a.put(string, \u26032);
        }
        return \u26032;
    }
}

