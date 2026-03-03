/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class kc
extends IOException {
    private final List<a> a = Lists.newArrayList();
    private final String b;

    public kc(String string) {
        this.a.add(new a());
        this.b = string;
    }

    public kc(String string, Throwable throwable) {
        super(throwable);
        this.a.add(new a());
        this.b = string;
    }

    public void a(String string) {
        this.a.get(0).a(string);
    }

    public void b(String string) {
        this.a.get(0).a = string;
        this.a.add(0, new a());
    }

    @Override
    public String getMessage() {
        return "Invalid " + this.a.get(this.a.size() - 1).toString() + ": " + this.b;
    }

    public static kc a(Exception exception) {
        if (exception instanceof kc) {
            return (kc)exception;
        }
        String string = exception.getMessage();
        if (exception instanceof FileNotFoundException) {
            string = "File not found";
        }
        return new kc(string, exception);
    }

    public static class a {
        private String a = null;
        private final List<String> b = Lists.newArrayList();

        private a() {
        }

        private void a(String string) {
            this.b.add(0, string);
        }

        public String b() {
            return StringUtils.join(this.b, "->");
        }

        public String toString() {
            if (this.a != null) {
                if (!this.b.isEmpty()) {
                    return this.a + " " + this.b();
                }
                return this.a;
            }
            if (!this.b.isEmpty()) {
                return "(Unknown file) " + this.b();
            }
            return "(Unknown file)";
        }
    }
}

