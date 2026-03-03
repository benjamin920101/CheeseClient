/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fb
extends es {
    private final String d;
    private final Object[] e;
    private final Object f = new Object();
    private long g = -1L;
    List<eu> b = Lists.newArrayList();
    public static final Pattern c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public fb(String string, Object ... objectArray) {
        this.d = string;
        this.e = objectArray;
        for (Object object : objectArray) {
            if (!(object instanceof eu)) continue;
            ((eu)object).b().a(this.b());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    synchronized void g() {
        Object object = this.f;
        synchronized (object) {
            long l2 = di.a();
            if (l2 == this.g) {
                return;
            }
            this.g = l2;
            this.b.clear();
        }
        try {
            this.b(di.a(this.d));
        }
        catch (fc fc2) {
            this.b.clear();
            try {
                this.b(di.b(this.d));
            }
            catch (fc fc3) {
                throw fc2;
            }
        }
    }

    protected void b(String string) {
        boolean bl2 = false;
        Matcher \u26032 = c.matcher(string);
        int \u26033 = 0;
        int \u26034 = 0;
        try {
            while (\u26032.find(\u26034)) {
                Object object;
                int n2 = \u26032.start();
                \u2603 = \u26032.end();
                if (n2 > \u26034) {
                    object = new fa(String.format(string.substring(\u26034, n2), new Object[0]));
                    ((es)object).b().a(this.b());
                    this.b.add((eu)object);
                }
                object = \u26032.group(2);
                String \u26035 = string.substring(n2, \u2603);
                if ("%".equals(object) && "%%".equals(\u26035)) {
                    \u2603 = new fa("%");
                    ((es)\u2603).b().a(this.b());
                    this.b.add((eu)\u2603);
                } else if ("s".equals(object)) {
                    \u2603 = \u26032.group(1);
                    int n3 = \u2603 = \u2603 != null ? Integer.parseInt((String)\u2603) - 1 : \u26033++;
                    if (\u2603 < this.e.length) {
                        this.b.add(this.a(\u2603));
                    }
                } else {
                    throw new fc(this, "Unsupported format: '" + \u26035 + "'");
                }
                \u26034 = \u2603;
            }
            if (\u26034 < string.length()) {
                fa \u26036 = new fa(String.format(string.substring(\u26034), new Object[0]));
                \u26036.b().a(this.b());
                this.b.add(\u26036);
            }
        }
        catch (IllegalFormatException illegalFormatException) {
            throw new fc(this, (Throwable)illegalFormatException);
        }
    }

    private eu a(int n2) {
        eu \u26032;
        if (n2 >= this.e.length) {
            throw new fc(this, n2);
        }
        Object object = this.e[n2];
        if (object instanceof eu) {
            \u26032 = (eu)object;
        } else {
            \u26032 = new fa(object == null ? "null" : object.toString());
            \u26032.b().a(this.b());
        }
        return \u26032;
    }

    @Override
    public eu a(ez ez2) {
        super.a(ez2);
        for (Object object : this.e) {
            if (!(object instanceof eu)) continue;
            ((eu)object).b().a(this.b());
        }
        if (this.g > -1L) {
            for (eu eu2 : this.b) {
                eu2.b().a(ez2);
            }
        }
        return this;
    }

    @Override
    public Iterator<eu> iterator() {
        this.g();
        return Iterators.concat(fb.a(this.b), fb.a(this.a));
    }

    @Override
    public String e() {
        this.g();
        StringBuilder stringBuilder = new StringBuilder();
        for (eu eu2 : this.b) {
            stringBuilder.append(eu2.e());
        }
        return stringBuilder.toString();
    }

    public fb h() {
        Object[] objectArray = new Object[this.e.length];
        for (int i2 = 0; i2 < this.e.length; ++i2) {
            objectArray[i2] = this.e[i2] instanceof eu ? ((eu)this.e[i2]).f() : this.e[i2];
        }
        fb \u26032 = new fb(this.d, objectArray);
        \u26032.a(this.b().m());
        for (eu eu2 : this.a()) {
            \u26032.a(eu2.f());
        }
        return \u26032;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof fb) {
            fb fb2 = (fb)object;
            return Arrays.equals(this.e, fb2.e) && this.d.equals(fb2.d) && super.equals(object);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int n2 = super.hashCode();
        n2 = 31 * n2 + this.d.hashCode();
        n2 = 31 * n2 + Arrays.hashCode(this.e);
        return n2;
    }

    @Override
    public String toString() {
        return "TranslatableComponent{key='" + this.d + '\'' + ", args=" + Arrays.toString(this.e) + ", siblings=" + this.a + ", style=" + this.b() + '}';
    }

    public String i() {
        return this.d;
    }

    public Object[] j() {
        return this.e;
    }

    @Override
    public /* synthetic */ eu f() {
        return this.h();
    }
}

