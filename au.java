/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class au
extends i {
    @Override
    public String c() {
        return "particle";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.particle.usage";
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        adm adm2;
        if (stringArray2.length < 8) {
            throw new cf("commands.particle.usage", new Object[0]);
        }
        boolean \u26033 = false;
        cy \u26032 = null;
        for (cy cy2 : cy.values()) {
            if (cy2.f()) {
                if (!stringArray2[0].startsWith(cy2.b())) continue;
                \u26033 = true;
                \u26032 = cy2;
                break;
            }
            if (!stringArray2[0].equals(cy2.b())) continue;
            \u26033 = true;
            \u26032 = cy2;
            break;
        }
        if (!\u26033) {
            throw new bz("commands.particle.notFound", stringArray2[0]);
        }
        String string = stringArray2[0];
        aui \u26034 = m2.d();
        double \u26035 = (float)au.b(\u26034.a, stringArray2[1], true);
        double \u26036 = (float)au.b(\u26034.b, stringArray2[2], true);
        double \u26037 = (float)au.b(\u26034.c, stringArray2[3], true);
        double \u26038 = (float)au.c(stringArray2[4]);
        double \u26039 = (float)au.c(stringArray2[5]);
        double \u260310 = (float)au.c(stringArray2[6]);
        double \u260311 = (float)au.c(stringArray2[7]);
        int \u260312 = 0;
        if (stringArray2.length > 8) {
            \u260312 = au.a(stringArray2[8], 0);
        }
        boolean \u260313 = false;
        if (stringArray2.length > 9 && "force".equals(stringArray2[9])) {
            \u260313 = true;
        }
        if ((adm2 = m2.e()) instanceof le) {
            le le2 = (le)adm2;
            int[] \u260314 = new int[\u26032.d()];
            if (\u26032.f()) {
                String[] stringArray3 = stringArray2[0].split("_", 3);
                for (int i2 = 1; i2 < stringArray3.length; ++i2) {
                    try {
                        \u260314[i2 - 1] = Integer.parseInt(stringArray3[i2]);
                        continue;
                    }
                    catch (NumberFormatException numberFormatException) {
                        throw new bz("commands.particle.notFound", stringArray2[0]);
                    }
                }
            }
            le2.a(\u26032, \u260313, \u26035, \u26036, \u26037, \u260312, \u26038, \u26039, \u260310, \u260311, \u260314);
            au.a(m2, (k)this, "commands.particle.success", string, Math.max(\u260312, 1));
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return au.a(stringArray, cy.a());
        }
        if (stringArray.length > 1 && stringArray.length <= 4) {
            return au.a(stringArray, 1, cj2);
        }
        if (stringArray.length == 10) {
            return au.a(stringArray, "normal", "force");
        }
        return null;
    }
}

