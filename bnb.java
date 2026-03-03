/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bnb
implements bni {
    private static final Logger b = LogManager.getLogger();
    protected final List<bnk> a = Lists.newArrayList();
    private final bny c;

    public bnb(bny bny2) {
        this.c = bny2;
    }

    public void a(bnk bnk2) {
        this.a.add(bnk2);
    }

    @Override
    public Set<String> a() {
        return null;
    }

    @Override
    public bnh a(jy jy2) throws IOException {
        bnk bnk2 = null;
        jy \u26032 = bnb.c(jy2);
        for (int i2 = this.a.size() - 1; i2 >= 0; --i2) {
            bnk bnk3 = this.a.get(i2);
            if (bnk2 == null && bnk3.b(\u26032)) {
                bnk2 = bnk3;
            }
            if (!bnk3.b(jy2)) continue;
            InputStream \u26033 = null;
            if (bnk2 != null) {
                \u26033 = this.a(\u26032, bnk2);
            }
            return new bno(bnk3.b(), jy2, this.a(jy2, bnk3), \u26033, this.c);
        }
        throw new FileNotFoundException(jy2.toString());
    }

    protected InputStream a(jy jy2, bnk bnk2) throws IOException {
        InputStream inputStream = bnk2.a(jy2);
        return b.isDebugEnabled() ? new a(inputStream, jy2, bnk2.b()) : inputStream;
    }

    @Override
    public List<bnh> b(jy jy2) throws IOException {
        ArrayList<bnh> arrayList = Lists.newArrayList();
        jy \u26032 = bnb.c(jy2);
        for (bnk bnk2 : this.a) {
            if (!bnk2.b(jy2)) continue;
            InputStream inputStream = bnk2.b(\u26032) ? this.a(\u26032, bnk2) : null;
            arrayList.add(new bno(bnk2.b(), jy2, this.a(jy2, bnk2), inputStream, this.c));
        }
        if (arrayList.isEmpty()) {
            throw new FileNotFoundException(jy2.toString());
        }
        return arrayList;
    }

    static jy c(jy jy2) {
        return new jy(jy2.b(), jy2.a() + ".mcmeta");
    }

    static class a
    extends InputStream {
        private final InputStream a;
        private final String b;
        private boolean c = false;

        public a(InputStream inputStream, jy jy2, String string) {
            this.a = inputStream;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new Exception().printStackTrace(new PrintStream(byteArrayOutputStream));
            this.b = "Leaked resource: '" + jy2 + "' loaded from pack: '" + string + "'\n" + byteArrayOutputStream.toString();
        }

        @Override
        public void close() throws IOException {
            this.a.close();
            this.c = true;
        }

        protected void finalize() throws Throwable {
            if (!this.c) {
                b.warn(this.b);
            }
            super.finalize();
        }

        @Override
        public int read() throws IOException {
            return this.a.read();
        }
    }
}

