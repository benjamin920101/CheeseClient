/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.BufferUtils;

public class blt {
    private final a a;
    private final String b;
    private int c;
    private int d = 0;

    private blt(a a2, int n2, String string) {
        this.a = a2;
        this.c = n2;
        this.b = string;
    }

    public void a(blq blq2) {
        ++this.d;
        bqs.b(blq2.h(), this.c);
    }

    public void b(blq blq2) {
        --this.d;
        if (this.d <= 0) {
            bqs.a(this.c);
            this.a.d().remove(this.b);
        }
    }

    public String a() {
        return this.b;
    }

    public static blt a(bni bni2, a a2, String string) throws IOException {
        blt blt2 = a2.d().get(string);
        if (blt2 == null) {
            jy jy2 = new jy("shaders/program/" + string + a2.b());
            BufferedInputStream \u26032 = new BufferedInputStream(bni2.a(jy2).b());
            byte[] \u26033 = blt.a(\u26032);
            ByteBuffer \u26034 = BufferUtils.createByteBuffer(\u26033.length);
            \u26034.put(\u26033);
            \u26034.position(0);
            int \u26035 = bqs.b(a2.c());
            bqs.a(\u26035, \u26034);
            bqs.c(\u26035);
            if (bqs.c(\u26035, bqs.n) == 0) {
                String string2 = StringUtils.trim(bqs.d(\u26035, 32768));
                kc \u26036 = new kc("Couldn't compile " + a2.a() + " program: " + string2);
                \u26036.b(jy2.a());
                throw \u26036;
            }
            blt2 = new blt(a2, \u26035, string);
            a2.d().put(string, blt2);
        }
        return blt2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static byte[] a(BufferedInputStream bufferedInputStream) throws IOException {
        try {
            byte[] byArray = IOUtils.toByteArray(bufferedInputStream);
            return byArray;
        }
        finally {
            bufferedInputStream.close();
        }
    }

    public static enum a {
        a("vertex", ".vsh", bqs.o),
        b("fragment", ".fsh", bqs.p);

        private final String c;
        private final String d;
        private final int e;
        private final Map<String, blt> f = Maps.newHashMap();

        private a(String string2, String string3, int n3) {
            this.c = string2;
            this.d = string3;
            this.e = n3;
        }

        public String a() {
            return this.c;
        }

        protected String b() {
            return this.d;
        }

        protected int c() {
            return this.e;
        }

        protected Map<String, blt> d() {
            return this.f;
        }
    }
}

