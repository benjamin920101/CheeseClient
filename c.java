/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;

public class c {
    private final b a;
    private final String b;
    private final List<a> c = Lists.newArrayList();
    private StackTraceElement[] d = new StackTraceElement[0];

    public c(b b2, String string) {
        this.a = b2;
        this.b = string;
    }

    public static String a(double d2, double d3, double d4) {
        return String.format("%.2f,%.2f,%.2f - %s", d2, d3, d4, c.a(new cj(d2, d3, d4)));
    }

    public static String a(cj cj2) {
        int n2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        StringBuilder \u26032 = new StringBuilder();
        try {
            \u26032.append(String.format("World: (%d,%d,%d)", n2, \u2603, \u2603));
        }
        catch (Throwable \u26033) {
            \u26032.append("(Error finding world loc)");
        }
        \u26032.append(", ");
        try {
            \u2603 = n2 >> 4;
            \u2603 = \u2603 >> 4;
            \u2603 = n2 & 0xF;
            \u2603 = \u2603 >> 4;
            \u2603 = \u2603 & 0xF;
            \u2603 = \u2603 << 4;
            \u2603 = \u2603 << 4;
            \u2603 = (\u2603 + 1 << 4) - 1;
            \u2603 = (\u2603 + 1 << 4) - 1;
            \u26032.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603));
        }
        catch (Throwable \u26034) {
            \u26032.append("(Error finding chunk loc)");
        }
        \u26032.append(", ");
        try {
            \u2603 = n2 >> 9;
            \u2603 = \u2603 >> 9;
            \u2603 = \u2603 << 5;
            \u2603 = \u2603 << 5;
            \u2603 = (\u2603 + 1 << 5) - 1;
            \u2603 = (\u2603 + 1 << 5) - 1;
            \u2603 = \u2603 << 9;
            \u2603 = \u2603 << 9;
            \u2603 = (\u2603 + 1 << 9) - 1;
            \u2603 = (\u2603 + 1 << 9) - 1;
            \u26032.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603));
        }
        catch (Throwable \u26035) {
            \u26032.append("(Error finding world loc)");
        }
        return \u26032.toString();
    }

    public void a(String string, Callable<String> callable) {
        try {
            this.a(string, callable.call());
        }
        catch (Throwable throwable) {
            this.a(string, throwable);
        }
    }

    public void a(String string, Object object) {
        this.c.add(new a(string, object));
    }

    public void a(String string, Throwable throwable) {
        this.a(string, (Object)throwable);
    }

    public int a(int n2) {
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        if (stackTraceElementArray.length <= 0) {
            return 0;
        }
        this.d = new StackTraceElement[stackTraceElementArray.length - 3 - n2];
        System.arraycopy(stackTraceElementArray, 3 + n2, this.d, 0, this.d.length);
        return this.d.length;
    }

    public boolean a(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        if (this.d.length == 0 || stackTraceElement == null) {
            return false;
        }
        \u2603 = this.d[0];
        if (!(\u2603.isNativeMethod() == stackTraceElement.isNativeMethod() && \u2603.getClassName().equals(stackTraceElement.getClassName()) && \u2603.getFileName().equals(stackTraceElement.getFileName()) && \u2603.getMethodName().equals(stackTraceElement.getMethodName()))) {
            return false;
        }
        if (stackTraceElement2 != null != this.d.length > 1) {
            return false;
        }
        if (stackTraceElement2 != null && !this.d[1].equals(stackTraceElement2)) {
            return false;
        }
        this.d[0] = stackTraceElement;
        return true;
    }

    public void b(int n2) {
        StackTraceElement[] stackTraceElementArray = new StackTraceElement[this.d.length - n2];
        System.arraycopy(this.d, 0, stackTraceElementArray, 0, stackTraceElementArray.length);
        this.d = stackTraceElementArray;
    }

    public void a(StringBuilder stringBuilder2) {
        stringBuilder2.append("-- ").append(this.b).append(" --\n");
        stringBuilder2.append("Details:");
        for (a a2 : this.c) {
            stringBuilder2.append("\n\t");
            stringBuilder2.append(a2.a());
            stringBuilder2.append(": ");
            stringBuilder2.append(a2.b());
        }
        if (this.d != null && this.d.length > 0) {
            StringBuilder stringBuilder2;
            stringBuilder2.append("\nStacktrace:");
            for (StackTraceElement stackTraceElement : this.d) {
                stringBuilder2.append("\n\tat ");
                stringBuilder2.append(stackTraceElement.toString());
            }
        }
    }

    public StackTraceElement[] a() {
        return this.d;
    }

    public static void a(c c2, final cj cj2, final afh afh2, final int n2) {
        \u2603 = afh.a(afh2);
        c2.a("Block type", new Callable<String>(){

            public String a() throws Exception {
                try {
                    return String.format("ID #%d (%s // %s)", \u2603, afh2.a(), afh2.getClass().getCanonicalName());
                }
                catch (Throwable throwable) {
                    return "ID #" + \u2603;
                }
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Block data value", new Callable<String>(){

            public String a() throws Exception {
                if (n2 < 0) {
                    return "Unknown? (Got " + n2 + ")";
                }
                String string = String.format("%4s", Integer.toBinaryString(n2)).replace(" ", "0");
                return String.format("%1$d / 0x%1$X / 0b%2$s", n2, string);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Block location", new Callable<String>(){

            public String a() throws Exception {
                return c.a(cj2);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
    }

    public static void a(c c2, final cj cj2, final alz alz2) {
        c2.a("Block", new Callable<String>(){

            public String a() throws Exception {
                return alz2.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Block location", new Callable<String>(){

            public String a() throws Exception {
                return c.a(cj2);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
    }

    static class a {
        private final String a;
        private final String b;

        public a(String string, Object object2) {
            this.a = string;
            if (object2 == null) {
                this.b = "~~NULL~~";
            } else if (object2 instanceof Throwable) {
                Throwable throwable = (Throwable)object2;
                this.b = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                Object object2;
                this.b = object2.toString();
            }
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }
    }
}

