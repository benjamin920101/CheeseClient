/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class bnc
extends bmx
implements Closeable {
    public static final Splitter b = Splitter.on('/').omitEmptyStrings().limit(3);
    private ZipFile c;

    public bnc(File file) {
        super(file);
    }

    private ZipFile d() throws IOException {
        if (this.c == null) {
            this.c = new ZipFile(this.a);
        }
        return this.c;
    }

    @Override
    protected InputStream a(String string) throws IOException {
        ZipFile zipFile = this.d();
        ZipEntry \u26032 = zipFile.getEntry(string);
        if (\u26032 == null) {
            throw new bnl(this.a, string);
        }
        return zipFile.getInputStream(\u26032);
    }

    @Override
    public boolean b(String string) {
        try {
            return this.d().getEntry(string) != null;
        }
        catch (IOException iOException) {
            return false;
        }
    }

    @Override
    public Set<String> c() {
        try {
            ZipFile zipFile = this.d();
        }
        catch (IOException iOException) {
            return Collections.emptySet();
        }
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        HashSet<String> \u26032 = Sets.newHashSet();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            String \u26033 = zipEntry.getName();
            if (!\u26033.startsWith("assets/") || (\u2603 = Lists.newArrayList(b.split(\u26033))).size() <= 1) continue;
            String \u26034 = (String)\u2603.get(1);
            if (!\u26034.equals(\u26034.toLowerCase())) {
                this.c(\u26034);
                continue;
            }
            \u26032.add(\u26034);
        }
        return \u26032;
    }

    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    @Override
    public void close() throws IOException {
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }
}

