/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.Okio;
import okio.Sink;
import okio.Source;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem(){

        @Override
        public Source source(File file) throws FileNotFoundException {
            return Okio.source(file);
        }

        @Override
        public Sink sink(File file) throws FileNotFoundException {
            try {
                return Okio.sink(file);
            }
            catch (FileNotFoundException e2) {
                file.getParentFile().mkdirs();
                return Okio.sink(file);
            }
        }

        @Override
        public Sink appendingSink(File file) throws FileNotFoundException {
            try {
                return Okio.appendingSink(file);
            }
            catch (FileNotFoundException e2) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        @Override
        public boolean exists(File file) {
            return file.exists();
        }

        @Override
        public long size(File file) {
            return file.length();
        }

        @Override
        public void rename(File from, File to2) throws IOException {
            this.delete(to2);
            if (!from.renameTo(to2)) {
                throw new IOException("failed to rename " + from + " to " + to2);
            }
        }

        @Override
        public void deleteContents(File directory) throws IOException {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("not a readable directory: " + directory);
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    this.deleteContents(file);
                }
                if (file.delete()) continue;
                throw new IOException("failed to delete " + file);
            }
        }
    };

    public Source source(File var1) throws FileNotFoundException;

    public Sink sink(File var1) throws FileNotFoundException;

    public Sink appendingSink(File var1) throws FileNotFoundException;

    public void delete(File var1) throws IOException;

    public boolean exists(File var1);

    public long size(File var1);

    public void rename(File var1, File var2) throws IOException;

    public void deleteContents(File var1) throws IOException;
}

