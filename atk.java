/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class atk
extends atn {
    private static final Logger b = LogManager.getLogger();

    public atk(File file) {
        super(file);
    }

    @Override
    public String a() {
        return "Anvil";
    }

    @Override
    public List<ats> b() throws atq {
        if (this.a == null || !this.a.exists() || !this.a.isDirectory()) {
            throw new atq("Unable to read or access folder where game worlds are saved!");
        }
        ArrayList<ats> arrayList = Lists.newArrayList();
        for (File file : \u2603 = this.a.listFiles()) {
            if (!file.isDirectory() || (\u2603 = this.c(\u2603 = file.getName())) == null || \u2603.l() != 19132 && \u2603.l() != 19133) continue;
            boolean bl2 = \u2603.l() != this.c();
            String \u26032 = \u2603.k();
            if (StringUtils.isEmpty(\u26032)) {
                \u26032 = \u2603;
            }
            long \u26033 = 0L;
            arrayList.add(new ats(\u2603, \u26032, \u2603.m(), \u26033, \u2603.r(), bl2, \u2603.t(), \u2603.v()));
        }
        return arrayList;
    }

    protected int c() {
        return 19133;
    }

    @Override
    public void d() {
        ani.a();
    }

    @Override
    public atp a(String string, boolean bl2) {
        return new atj(this.a, string, bl2);
    }

    @Override
    public boolean a(String string) {
        ato ato2 = this.c(string);
        return ato2 != null && ato2.l() == 19132;
    }

    @Override
    public boolean b(String string) {
        ato ato2 = this.c(string);
        return ato2 != null && ato2.l() != this.c();
    }

    @Override
    public boolean a(String string, nu nu2) {
        nu2.a(0);
        ArrayList<File> arrayList = Lists.newArrayList();
        \u2603 = Lists.newArrayList();
        \u2603 = Lists.newArrayList();
        File \u26032 = new File(this.a, string);
        File \u26033 = new File(\u26032, "DIM-1");
        File \u26034 = new File(\u26032, "DIM1");
        b.info("Scanning folders...");
        this.a(\u26032, arrayList);
        if (\u26033.exists()) {
            this.a(\u26033, \u2603);
        }
        if (\u26034.exists()) {
            this.a(\u26034, \u2603);
        }
        int \u26035 = arrayList.size() + \u2603.size() + \u2603.size();
        b.info("Total conversion count is " + \u26035);
        ato \u26036 = this.c(string);
        aec \u26037 = null;
        \u26037 = \u26036.u() == adr.c ? new aef(ady.q, 0.5f) : new aec(\u26036.b(), \u26036.u(), \u26036.B());
        this.a(new File(\u26032, "region"), arrayList, \u26037, 0, \u26035, nu2);
        this.a(new File(\u26033, "region"), \u2603, (aec)new aef(ady.x, 0.0f), arrayList.size(), \u26035, nu2);
        this.a(new File(\u26034, "region"), \u2603, (aec)new aef(ady.y, 0.0f), arrayList.size() + \u2603.size(), \u26035, nu2);
        \u26036.e(19133);
        if (\u26036.u() == adr.h) {
            \u26036.a(adr.b);
        }
        this.g(string);
        atp \u26038 = this.a(string, false);
        \u26038.a(\u26036);
        return true;
    }

    private void g(String string) {
        File file = new File(this.a, string);
        if (!file.exists()) {
            b.warn("Unable to create level.dat_mcr backup");
            return;
        }
        \u2603 = new File(file, "level.dat");
        if (!\u2603.exists()) {
            b.warn("Unable to create level.dat_mcr backup");
            return;
        }
        \u2603 = new File(file, "level.dat_mcr");
        if (!\u2603.renameTo(\u2603)) {
            b.warn("Unable to create level.dat_mcr backup");
        }
    }

    private void a(File file, Iterable<File> iterable, aec aec2, int n2, int n3, nu nu2) {
        for (File file2 : iterable) {
            this.a(file, file2, aec2, n2, n3, nu2);
            int n4 = (int)Math.round(100.0 * (double)(++n2) / (double)n3);
            nu2.a(n4);
        }
    }

    private void a(File file, File file2, aec aec2, int n2, int n3, nu nu2) {
        try {
            String string = file2.getName();
            anh \u26032 = new anh(file2);
            anh \u26033 = new anh(new File(file, string.substring(0, string.length() - ".mcr".length()) + ".mca"));
            for (int i2 = 0; i2 < 32; ++i2) {
                for (n4 = 0; n4 < 32; ++n4) {
                    if (!\u26032.c(i2, n4) || \u26033.c(i2, n4)) continue;
                    DataInputStream dataInputStream = \u26032.a(i2, n4);
                    if (dataInputStream == null) {
                        b.warn("Failed to fetch input stream");
                        continue;
                    }
                    dn \u26034 = dx.a(dataInputStream);
                    dataInputStream.close();
                    dn \u26035 = \u26034.m("Level");
                    ang.a \u26036 = ang.a(\u26035);
                    dn \u26037 = new dn();
                    dn \u26038 = new dn();
                    \u26037.a("Level", \u26038);
                    ang.a(\u26036, \u26038, aec2);
                    DataOutputStream \u26039 = \u26033.b(i2, n4);
                    dx.a(\u26037, (DataOutput)\u26039);
                    \u26039.close();
                }
                int n4 = (int)Math.round(100.0 * (double)(n2 * 1024) / (double)(n3 * 1024));
                \u2603 = (int)Math.round(100.0 * (double)((i2 + 1) * 32 + n2 * 1024) / (double)(n3 * 1024));
                if (\u2603 <= n4) continue;
                nu2.a(\u2603);
            }
            \u26032.c();
            \u26033.c();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void a(File file, Collection<File> collection) {
        File file2 = new File(file, "region");
        File[] \u26032 = file2.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File file, String string) {
                return string.endsWith(".mcr");
            }
        });
        if (\u26032 != null) {
            Collections.addAll(collection, \u26032);
        }
    }
}

