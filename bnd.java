/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class bnd
extends bmx {
    public bnd(File file) {
        super(file);
    }

    @Override
    protected InputStream a(String string) throws IOException {
        return new BufferedInputStream(new FileInputStream(new File(this.a, string)));
    }

    @Override
    protected boolean b(String string) {
        return new File(this.a, string).isFile();
    }

    @Override
    public Set<String> c() {
        HashSet<String> hashSet = Sets.newHashSet();
        File \u26032 = new File(this.a, "assets/");
        if (\u26032.isDirectory()) {
            for (File file : \u26032.listFiles(DirectoryFileFilter.DIRECTORY)) {
                String string = bnd.a(\u26032, file);
                if (!string.equals(string.toLowerCase())) {
                    this.c(string);
                    continue;
                }
                hashSet.add(string.substring(0, string.length() - 1));
            }
        }
        return hashSet;
    }
}

