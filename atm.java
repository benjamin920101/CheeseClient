/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class atm
implements atp,
aty {
    private static final Logger a = LogManager.getLogger();
    private final File b;
    private final File c;
    private final File d;
    private final long e = MinecraftServer.az();
    private final String f;

    public atm(File file, String string, boolean bl2) {
        this.b = new File(file, string);
        this.b.mkdirs();
        this.c = new File(this.b, "playerdata");
        this.d = new File(this.b, "data");
        this.d.mkdirs();
        this.f = string;
        if (bl2) {
            this.c.mkdirs();
        }
        this.h();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void h() {
        try {
            File file = new File(this.b, "session.lock");
            DataOutputStream \u26032 = new DataOutputStream(new FileOutputStream(file));
            try {
                \u26032.writeLong(this.e);
            }
            finally {
                \u26032.close();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            throw new RuntimeException("Failed to check session lock, aborting");
        }
    }

    @Override
    public File b() {
        return this.b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void c() throws adn {
        try {
            File file = new File(this.b, "session.lock");
            DataInputStream \u26032 = new DataInputStream(new FileInputStream(file));
            try {
                if (\u26032.readLong() != this.e) {
                    throw new adn("The save is being accessed from another location, aborting");
                }
            }
            finally {
                \u26032.close();
            }
        }
        catch (IOException iOException) {
            throw new adn("Failed to check session lock, aborting");
        }
    }

    @Override
    public and a(anm anm2) {
        throw new RuntimeException("Old Chunk Storage is no longer supported.");
    }

    @Override
    public ato d() {
        File file = new File(this.b, "level.dat");
        if (file.exists()) {
            try {
                dn dn2 = dx.a(new FileInputStream(file));
                \u2603 = dn2.m("Data");
                return new ato(\u2603);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if ((file = new File(this.b, "level.dat_old")).exists()) {
            try {
                dn dn3 = dx.a(new FileInputStream(file));
                \u2603 = dn3.m("Data");
                return new ato(\u2603);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void a(ato ato2, dn dn2) {
        \u2603 = ato2.a(dn2);
        \u2603 = new dn();
        \u2603.a("Data", \u2603);
        try {
            File file = new File(this.b, "level.dat_new");
            \u2603 = new File(this.b, "level.dat_old");
            \u2603 = new File(this.b, "level.dat");
            dx.a(\u2603, new FileOutputStream(file));
            if (\u2603.exists()) {
                \u2603.delete();
            }
            \u2603.renameTo(\u2603);
            if (\u2603.exists()) {
                \u2603.delete();
            }
            file.renameTo(\u2603);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void a(ato ato2) {
        dn dn2 = ato2.a();
        \u2603 = new dn();
        \u2603.a("Data", dn2);
        try {
            File file = new File(this.b, "level.dat_new");
            \u2603 = new File(this.b, "level.dat_old");
            \u2603 = new File(this.b, "level.dat");
            dx.a(\u2603, new FileOutputStream(file));
            if (\u2603.exists()) {
                \u2603.delete();
            }
            \u2603.renameTo(\u2603);
            if (\u2603.exists()) {
                \u2603.delete();
            }
            file.renameTo(\u2603);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void a(wn wn2) {
        try {
            dn dn2 = new dn();
            wn2.e(dn2);
            File \u26032 = new File(this.c, wn2.aK().toString() + ".dat.tmp");
            File \u26033 = new File(this.c, wn2.aK().toString() + ".dat");
            dx.a(dn2, new FileOutputStream(\u26032));
            if (\u26033.exists()) {
                \u26033.delete();
            }
            \u26032.renameTo(\u26033);
        }
        catch (Exception exception) {
            a.warn("Failed to save player data for " + wn2.e_());
        }
    }

    @Override
    public dn b(wn wn2) {
        dn dn2 = null;
        try {
            File file = new File(this.c, wn2.aK().toString() + ".dat");
            if (file.exists() && file.isFile()) {
                dn2 = dx.a(new FileInputStream(file));
            }
        }
        catch (Exception exception) {
            a.warn("Failed to load player data for " + wn2.e_());
        }
        if (dn2 != null) {
            wn2.f(dn2);
        }
        return dn2;
    }

    @Override
    public aty e() {
        return this;
    }

    @Override
    public String[] f() {
        String[] stringArray = this.c.list();
        if (stringArray == null) {
            stringArray = new String[]{};
        }
        for (int i2 = 0; i2 < stringArray.length; ++i2) {
            if (!stringArray[i2].endsWith(".dat")) continue;
            stringArray[i2] = stringArray[i2].substring(0, stringArray[i2].length() - 4);
        }
        return stringArray;
    }

    @Override
    public void a() {
    }

    @Override
    public File a(String string) {
        return new File(this.d, string + ".dat");
    }

    @Override
    public String g() {
        return this.f;
    }
}

