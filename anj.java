/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class anj
implements and,
aud {
    private static final Logger a = LogManager.getLogger();
    private Map<adg, dn> b = new ConcurrentHashMap<adg, dn>();
    private Set<adg> c = Collections.newSetFromMap(new ConcurrentHashMap());
    private final File d;
    private boolean e = false;

    public anj(File file) {
        this.d = file;
    }

    @Override
    public amy a(adm adm22, int n2, int n3) throws IOException {
        adm adm22;
        adg adg2 = new adg(n2, n3);
        dn \u26032 = this.b.get(adg2);
        if (\u26032 == null) {
            DataInputStream dataInputStream = ani.c(this.d, n2, n3);
            if (dataInputStream != null) {
                \u26032 = dx.a(dataInputStream);
            } else {
                return null;
            }
        }
        return this.a(adm22, n2, n3, \u26032);
    }

    protected amy a(adm adm2, int n2, int n3, dn dn2) {
        if (!dn2.b("Level", 10)) {
            a.error("Chunk file at " + n2 + "," + n3 + " is missing level data, skipping");
            return null;
        }
        \u2603 = dn2.m("Level");
        if (!\u2603.b("Sections", 9)) {
            a.error("Chunk file at " + n2 + "," + n3 + " is missing block data, skipping");
            return null;
        }
        amy amy2 = this.a(adm2, \u2603);
        if (!amy2.a(n2, n3)) {
            a.error("Chunk file at " + n2 + "," + n3 + " is in the wrong location; relocating. (Expected " + n2 + ", " + n3 + ", got " + amy2.a + ", " + amy2.b + ")");
            \u2603.a("xPos", n2);
            \u2603.a("zPos", n3);
            amy2 = this.a(adm2, \u2603);
        }
        return amy2;
    }

    @Override
    public void a(adm adm2, amy amy2) throws IOException, adn {
        adm2.I();
        try {
            dn dn2 = new dn();
            \u2603 = new dn();
            dn2.a("Level", \u2603);
            this.a(amy2, adm2, \u2603);
            this.a(amy2.j(), dn2);
        }
        catch (Exception exception) {
            a.error("Failed to save chunk", (Throwable)exception);
        }
    }

    protected void a(adg adg2, dn dn2) {
        if (!this.c.contains(adg2)) {
            this.b.put(adg2, dn2);
        }
        auc.a().a(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean c() {
        if (this.b.isEmpty()) {
            if (this.e) {
                a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", this.d.getName());
            }
            return false;
        }
        adg adg2 = this.b.keySet().iterator().next();
        try {
            this.c.add(adg2);
            dn dn2 = this.b.remove(adg2);
            if (dn2 != null) {
                try {
                    this.b(adg2, dn2);
                }
                catch (Exception exception) {
                    a.error("Failed to save chunk", (Throwable)exception);
                }
            }
            boolean bl2 = true;
            return bl2;
        }
        finally {
            this.c.remove(adg2);
        }
    }

    private void b(adg adg2, dn dn2) throws IOException {
        DataOutputStream dataOutputStream = ani.d(this.d, adg2.a, adg2.b);
        dx.a(dn2, (DataOutput)dataOutputStream);
        dataOutputStream.close();
    }

    @Override
    public void b(adm adm2, amy amy2) throws IOException {
    }

    @Override
    public void a() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void b() {
        try {
            this.e = true;
            while (this.c()) {
            }
        }
        finally {
            this.e = false;
        }
    }

    private void a(amy amy2, adm adm2, dn dn22) {
        dn22.a("V", (byte)1);
        dn22.a("xPos", amy2.a);
        dn22.a("zPos", amy2.b);
        dn22.a("LastUpdate", adm2.K());
        dn22.a("HeightMap", amy2.q());
        dn22.a("TerrainPopulated", amy2.t());
        dn22.a("LightPopulated", amy2.u());
        dn22.a("InhabitedTime", amy2.w());
        amz[] amzArray = amy2.h();
        du \u26032 = new du();
        boolean \u26033 = !adm2.t.o();
        for (amz amz2 : amzArray) {
            if (amz2 == null) continue;
            dn dn3 = new dn();
            dn3.a("Y", (byte)(amz2.d() >> 4 & 0xFF));
            byte[] \u26034 = new byte[amz2.g().length];
            amw \u26035 = new amw();
            amw \u26036 = null;
            for (int i2 = 0; i2 < amz2.g().length; ++i2) {
                char c2 = amz2.g()[i2];
                int \u26037 = i2 & 0xF;
                int \u26038 = i2 >> 8 & 0xF;
                int \u26039 = i2 >> 4 & 0xF;
                if (c2 >> 12 != 0) {
                    if (\u26036 == null) {
                        \u26036 = new amw();
                    }
                    \u26036.a(\u26037, \u26038, \u26039, c2 >> 12);
                }
                \u26034[i2] = (byte)(c2 >> 4 & 0xFF);
                \u26035.a(\u26037, \u26038, \u26039, c2 & 0xF);
            }
            dn3.a("Blocks", \u26034);
            dn3.a("Data", \u26035.a());
            if (\u26036 != null) {
                dn3.a("Add", \u26036.a());
            }
            dn3.a("BlockLight", amz2.h().a());
            if (\u26033) {
                dn3.a("SkyLight", amz2.i().a());
            } else {
                dn3.a("SkyLight", new byte[amz2.h().a().length]);
            }
            \u26032.a(dn3);
        }
        dn22.a("Sections", \u26032);
        dn22.a("Biomes", amy2.k());
        amy2.g(false);
        du du2 = new du();
        for (int i2 = 0; i2 < amy2.s().length; ++i2) {
            for (pk pk2 : amy2.s()[i2]) {
                if (!pk2.d(dn2 = new dn())) continue;
                amy2.g(true);
                du2.a(dn2);
            }
        }
        dn22.a("Entities", du2);
        du \u260310 = new du();
        for (akw akw2 : amy2.r().values()) {
            dn dn2 = new dn();
            akw2.b(dn2);
            \u260310.a(dn2);
        }
        dn22.a("TileEntities", \u260310);
        List<adw> list = adm2.a(amy2, false);
        if (list != null) {
            long l2 = adm2.K();
            du du3 = new du();
            for (adw adw2 : list) {
                dn dn4 = new dn();
                jy \u260312 = (jy)afh.c.c(adw2.a());
                dn4.a("i", \u260312 == null ? "" : \u260312.toString());
                dn4.a("x", adw2.a.n());
                dn4.a("y", adw2.a.o());
                dn4.a("z", adw2.a.p());
                dn4.a("t", (int)(adw2.b - l2));
                dn4.a("p", adw2.c);
                du3.a(dn4);
            }
            dn22.a("TileTicks", du3);
        }
    }

    private amy a(adm adm2, dn dn22) {
        du du2;
        dn dn22;
        Object \u260310;
        Object \u26039;
        Object \u26038;
        Object \u26037;
        int n2 = dn22.f("xPos");
        \u2603 = dn22.f("zPos");
        amy \u26032 = new amy(adm2, n2, \u2603);
        \u26032.a(dn22.l("HeightMap"));
        \u26032.d(dn22.n("TerrainPopulated"));
        \u26032.e(dn22.n("LightPopulated"));
        \u26032.c(dn22.g("InhabitedTime"));
        du \u26033 = dn22.c("Sections", 10);
        \u2603 = 16;
        amz[] \u26034 = new amz[\u2603];
        boolean \u26035 = !adm2.t.o();
        for (\u2603 = 0; \u2603 < \u26033.c(); ++\u2603) {
            dn dn3 = \u26033.b(\u2603);
            byte \u26036 = dn3.d("Y");
            \u26037 = new amz(\u26036 << 4, \u26035);
            \u26038 = dn3.k("Blocks");
            \u26039 = new amw(dn3.k("Data"));
            \u260310 = dn3.b("Add", 7) ? new amw(dn3.k("Add")) : null;
            char[] \u260311 = new char[((byte[])\u26038).length];
            for (int i2 = 0; i2 < \u260311.length; ++i2) {
                \u2603 = i2 & 0xF;
                \u2603 = i2 >> 8 & 0xF;
                \u2603 = i2 >> 4 & 0xF;
                \u2603 = \u260310 != null ? ((amw)\u260310).a(\u2603, \u2603, \u2603) : 0;
                \u260311[i2] = (char)(\u2603 << 12 | (\u26038[i2] & 0xFF) << 4 | ((amw)\u26039).a(\u2603, \u2603, \u2603));
            }
            ((amz)\u26037).a(\u260311);
            ((amz)\u26037).a(new amw(dn3.k("BlockLight")));
            if (\u26035) {
                ((amz)\u26037).b(new amw(dn3.k("SkyLight")));
            }
            ((amz)\u26037).e();
            \u26034[\u26036] = \u26037;
        }
        \u26032.a(\u26034);
        if (dn22.b("Biomes", 7)) {
            \u26032.a(dn22.k("Biomes"));
        }
        if ((\u2603 = dn22.c("Entities", 10)) != null) {
            for (int i3 = 0; i3 < \u2603.c(); ++i3) {
                dn dn4 = \u2603.b(i3);
                \u26037 = pm.a(dn4, adm2);
                \u26032.g(true);
                if (\u26037 == null) continue;
                \u26032.a((pk)\u26037);
                \u26038 = \u26037;
                \u26039 = dn4;
                while (((dn)\u26039).b("Riding", 10)) {
                    \u260310 = pm.a(((dn)\u26039).m("Riding"), adm2);
                    if (\u260310 != null) {
                        \u26032.a((pk)\u260310);
                        ((pk)\u26038).a((pk)\u260310);
                    }
                    \u26038 = \u260310;
                    \u26039 = ((dn)\u26039).m("Riding");
                }
            }
        }
        if ((du2 = dn22.c("TileEntities", 10)) != null) {
            for (int i4 = 0; i4 < du2.c(); ++i4) {
                \u26037 = du2.b(i4);
                \u26038 = akw.c((dn)\u26037);
                if (\u26038 == null) continue;
                \u26032.a((akw)\u26038);
            }
        }
        if (dn22.b("TileTicks", 9) && (\u2603 = dn22.c("TileTicks", 10)) != null) {
            for (int i5 = 0; i5 < \u2603.c(); ++i5) {
                \u26038 = \u2603.b(i5);
                \u26039 = ((dn)\u26038).b("i", 8) ? afh.b(((dn)\u26038).j("i")) : afh.c(((dn)\u26038).f("i"));
                adm2.b(new cj(((dn)\u26038).f("x"), ((dn)\u26038).f("y"), ((dn)\u26038).f("z")), (afh)\u26039, ((dn)\u26038).f("t"), ((dn)\u26038).f("p"));
            }
        }
        return \u26032;
    }
}

