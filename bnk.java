/*
 * Decompiled with CFR 0.152.
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public interface bnk {
    public InputStream a(jy var1) throws IOException;

    public boolean b(jy var1);

    public Set<String> c();

    public <T extends bnw> T a(bny var1, String var2) throws IOException;

    public BufferedImage a() throws IOException;

    public String b();
}

