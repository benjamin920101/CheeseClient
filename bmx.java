/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class bmx
implements bnk {
    private static final Logger b = LogManager.getLogger();
    protected final File a;

    public bmx(File file) {
        this.a = file;
    }

    private static String c(jy jy2) {
        return String.format("%s/%s/%s", "assets", jy2.b(), jy2.a());
    }

    protected static String a(File file, File file2) {
        return file.toURI().relativize(file2.toURI()).getPath();
    }

    @Override
    public InputStream a(jy jy2) throws IOException {
        return this.a(bmx.c(jy2));
    }

    @Override
    public boolean b(jy jy2) {
        return this.b(bmx.c(jy2));
    }

    protected abstract InputStream a(String var1) throws IOException;

    protected abstract boolean b(String var1);

    protected void c(String string) {
        b.warn("ResourcePack: ignored non-lowercase namespace: %s in %s", string, this.a);
    }

    @Override
    public <T extends bnw> T a(bny bny2, String string) throws IOException {
        return bmx.a(bny2, this.a("pack.mcmeta"), string);
    }

    static <T extends bnw> T a(bny bny2, InputStream inputStream, String string) {
        JsonObject jsonObject = null;
        BufferedReader \u26032 = null;
        try {
            \u26032 = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
            jsonObject = new JsonParser().parse(\u26032).getAsJsonObject();
        }
        catch (RuntimeException \u26033) {
            try {
                throw new JsonParseException(\u26033);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(\u26032);
                throw throwable;
            }
        }
        IOUtils.closeQuietly(\u26032);
        return bny2.a(string, jsonObject);
    }

    @Override
    public BufferedImage a() throws IOException {
        return bml.a(this.a("pack.png"));
    }

    @Override
    public String b() {
        return this.a.getName();
    }
}

