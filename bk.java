/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bk
extends i {
    @Override
    public String c() {
        return "stats";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.stats.usage";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        n \u26035;
        int \u26033;
        boolean \u26032;
        if (stringArray2.length < 1) {
            throw new cf("commands.stats.usage", new Object[0]);
        }
        if (stringArray2[0].equals("entity")) {
            \u26032 = false;
        } else {
            String[] stringArray2;
            if (!stringArray2[0].equals("block")) throw new cf("commands.stats.usage", new Object[0]);
            \u26032 = true;
        }
        if (\u26032) {
            if (stringArray2.length < 5) {
                throw new cf("commands.stats.block.usage", new Object[0]);
            }
            \u26033 = 4;
        } else {
            if (stringArray2.length < 3) {
                throw new cf("commands.stats.entity.usage", new Object[0]);
            }
            \u26033 = 2;
        }
        String string = stringArray2[\u26033++];
        if ("set".equals(string)) {
            if (stringArray2.length < \u26033 + 3) {
                if (\u26033 != 5) throw new cf("commands.stats.entity.set.usage", new Object[0]);
                throw new cf("commands.stats.block.set.usage", new Object[0]);
            }
        } else {
            if (!"clear".equals(string)) throw new cf("commands.stats.usage", new Object[0]);
            if (stringArray2.length < \u26033 + 1) {
                if (\u26033 != 5) throw new cf("commands.stats.entity.clear.usage", new Object[0]);
                throw new cf("commands.stats.block.clear.usage", new Object[0]);
            }
        }
        if ((\u2603 = n.a.a(stringArray2[\u26033++])) == null) {
            throw new bz("commands.stats.failed", new Object[0]);
        }
        adm \u26034 = m2.e();
        if (\u26032) {
            Object object = bk.a(m2, stringArray2, 1, false);
            object2 = \u26034.s((cj)object);
            if (object2 == null) {
                throw new bz("commands.stats.noCompatibleBlock", ((df)object).n(), ((df)object).o(), ((df)object).p());
            }
            if (object2 instanceof akz) {
                \u26035 = ((akz)object2).c();
            } else {
                Object object2;
                if (!(object2 instanceof aln)) throw new bz("commands.stats.noCompatibleBlock", ((df)object).n(), ((df)object).o(), ((df)object).p());
                \u26035 = ((aln)object2).d();
            }
        } else {
            object = bk.b(m2, stringArray2[1]);
            \u26035 = ((pk)object).aU();
        }
        if ("set".equals(string)) {
            object = stringArray2[\u26033++];
            object2 = stringArray2[\u26033];
            if (((String)object).length() == 0 || ((String)object2).length() == 0) {
                throw new bz("commands.stats.failed", new Object[0]);
            }
            n.a(\u26035, \u2603, (String)object, (String)object2);
            bk.a(m2, (k)this, "commands.stats.success", \u2603.b(), object2, object);
        } else if ("clear".equals(string)) {
            n.a(\u26035, \u2603, null, null);
            bk.a(m2, (k)this, "commands.stats.cleared", \u2603.b());
        }
        if (!\u26032) return;
        object = bk.a(m2, stringArray2, 1, false);
        object2 = \u26034.s((cj)object);
        ((akw)object2).p_();
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bk.a(stringArray, "entity", "block");
        }
        if (stringArray.length == 2 && stringArray[0].equals("entity")) {
            return bk.a(stringArray, this.d());
        }
        if (stringArray.length >= 2 && stringArray.length <= 4 && stringArray[0].equals("block")) {
            return bk.a(stringArray, 1, cj2);
        }
        if (stringArray.length == 3 && stringArray[0].equals("entity") || stringArray.length == 5 && stringArray[0].equals("block")) {
            return bk.a(stringArray, "set", "clear");
        }
        if (stringArray.length == 4 && stringArray[0].equals("entity") || stringArray.length == 6 && stringArray[0].equals("block")) {
            return bk.a(stringArray, n.a.c());
        }
        if (stringArray.length == 6 && stringArray[0].equals("entity") || stringArray.length == 8 && stringArray[0].equals("block")) {
            return bk.a(stringArray, this.e());
        }
        return null;
    }

    protected String[] d() {
        return MinecraftServer.N().K();
    }

    protected List<String> e() {
        Collection<auk> collection = MinecraftServer.N().a(0).Z().c();
        ArrayList<String> \u26032 = Lists.newArrayList();
        for (auk auk2 : collection) {
            if (auk2.c().b()) continue;
            \u26032.add(auk2.b());
        }
        return \u26032;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return stringArray.length > 0 && stringArray[0].equals("entity") && n2 == 1;
    }
}

