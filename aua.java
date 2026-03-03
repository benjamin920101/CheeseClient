/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class aua {
    private atp b;
    protected Map<String, ate> a = Maps.newHashMap();
    private List<ate> c = Lists.newArrayList();
    private Map<String, Short> d = Maps.newHashMap();

    public aua(atp atp2) {
        this.b = atp2;
        this.b();
    }

    public ate a(Class<? extends ate> clazz, String string) {
        ate ate2;
        block7: {
            ate2 = this.a.get(string);
            if (ate2 != null) {
                return ate2;
            }
            if (this.b != null) {
                try {
                    File file = this.b.a(string);
                    if (file == null || !file.exists()) break block7;
                    try {
                        ate2 = clazz.getConstructor(String.class).newInstance(string);
                    }
                    catch (Exception exception) {
                        throw new RuntimeException("Failed to instantiate " + clazz.toString(), exception);
                    }
                    FileInputStream fileInputStream = new FileInputStream(file);
                    dn \u26032 = dx.a(fileInputStream);
                    fileInputStream.close();
                    ate2.a(\u26032.m("data"));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        if (ate2 != null) {
            this.a.put(string, ate2);
            this.c.add(ate2);
        }
        return ate2;
    }

    public void a(String string, ate ate2) {
        if (this.a.containsKey(string)) {
            this.c.remove(this.a.remove(string));
        }
        this.a.put(string, ate2);
        this.c.add(ate2);
    }

    public void a() {
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            ate ate2 = this.c.get(i2);
            if (!ate2.d()) continue;
            this.a(ate2);
            ate2.a(false);
        }
    }

    private void a(ate ate2) {
        if (this.b == null) {
            return;
        }
        try {
            File file = this.b.a(ate2.a);
            if (file != null) {
                dn dn2 = new dn();
                ate2.b(dn2);
                \u2603 = new dn();
                \u2603.a("data", dn2);
                FileOutputStream \u26032 = new FileOutputStream(file);
                dx.a(\u2603, \u26032);
                \u26032.close();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void b() {
        try {
            this.d.clear();
            if (this.b == null) {
                return;
            }
            File file = this.b.a("idcounts");
            if (file != null && file.exists()) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                dn \u26032 = dx.a(dataInputStream);
                dataInputStream.close();
                for (String string : \u26032.c()) {
                    eb eb2 = \u26032.a(string);
                    if (!(eb2 instanceof dz)) continue;
                    dz \u26033 = (dz)eb2;
                    String \u26034 = string;
                    short \u26035 = \u26033.e();
                    this.d.put(\u26034, \u26035);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int a(String string) {
        Short s2 = this.d.get(string);
        if (s2 == null) {
            s2 = 0;
        } else {
            Short s3 = s2;
            Short s4 = s2 = Short.valueOf((short)(s2 + 1));
        }
        this.d.put(string, s2);
        if (this.b == null) {
            return s2.shortValue();
        }
        try {
            File file = this.b.a("idcounts");
            if (file != null) {
                dn dn2 = new dn();
                for (String string2 : this.d.keySet()) {
                    short s5 = this.d.get(string2);
                    dn2.a(string2, s5);
                }
                DataOutputStream \u26032 = new DataOutputStream(new FileOutputStream(file));
                dx.a(dn2, (DataOutput)\u26032);
                \u26032.close();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return s2.shortValue();
    }
}

