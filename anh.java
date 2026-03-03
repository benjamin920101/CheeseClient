/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.server.MinecraftServer;

public class anh {
    private static final byte[] a = new byte[4096];
    private final File b;
    private RandomAccessFile c;
    private final int[] d = new int[1024];
    private final int[] e = new int[1024];
    private List<Boolean> f;
    private int g;
    private long h;

    public anh(File file) {
        this.b = file;
        this.g = 0;
        try {
            int n2;
            if (file.exists()) {
                this.h = file.lastModified();
            }
            this.c = new RandomAccessFile(file, "rw");
            if (this.c.length() < 4096L) {
                for (n2 = 0; n2 < 1024; ++n2) {
                    this.c.writeInt(0);
                }
                for (n2 = 0; n2 < 1024; ++n2) {
                    this.c.writeInt(0);
                }
                this.g += 8192;
            }
            if ((this.c.length() & 0xFFFL) != 0L) {
                n2 = 0;
                while ((long)n2 < (this.c.length() & 0xFFFL)) {
                    this.c.write(0);
                    ++n2;
                }
            }
            n2 = (int)this.c.length() / 4096;
            this.f = Lists.newArrayListWithCapacity(n2);
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                this.f.add(true);
            }
            this.f.set(0, false);
            this.f.set(1, false);
            this.c.seek(0L);
            for (\u2603 = 0; \u2603 < 1024; ++\u2603) {
                this.d[\u2603] = \u2603 = this.c.readInt();
                if (\u2603 == 0 || (\u2603 >> 8) + (\u2603 & 0xFF) > this.f.size()) continue;
                for (\u2603 = 0; \u2603 < (\u2603 & 0xFF); ++\u2603) {
                    this.f.set((\u2603 >> 8) + \u2603, false);
                }
            }
            for (\u2603 = 0; \u2603 < 1024; ++\u2603) {
                this.e[\u2603] = \u2603 = this.c.readInt();
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public synchronized DataInputStream a(int n2, int n3) {
        if (this.d(n2, n3)) {
            return null;
        }
        try {
            \u2603 = this.e(n2, n3);
            if (\u2603 == 0) {
                return null;
            }
            \u2603 = \u2603 >> 8;
            \u2603 = \u2603 & 0xFF;
            if (\u2603 + \u2603 > this.f.size()) {
                return null;
            }
            this.c.seek(\u2603 * 4096);
            \u2603 = this.c.readInt();
            if (\u2603 > 4096 * \u2603) {
                return null;
            }
            if (\u2603 <= 0) {
                return null;
            }
            byte by = this.c.readByte();
            if (by == 1) {
                byte[] byArray = new byte[\u2603 - 1];
                this.c.read(byArray);
                return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(byArray))));
            }
            if (by == 2) {
                byte[] byArray = new byte[\u2603 - 1];
                this.c.read(byArray);
                return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(byArray))));
            }
            return null;
        }
        catch (IOException iOException) {
            return null;
        }
    }

    public DataOutputStream b(int n2, int n3) {
        if (this.d(n2, n3)) {
            return null;
        }
        return new DataOutputStream(new DeflaterOutputStream(new a(n2, n3)));
    }

    protected synchronized void a(int n2, int n3, byte[] byArray, int n4) {
        try {
            \u2603 = this.e(n2, n3);
            \u2603 = \u2603 >> 8;
            \u2603 = \u2603 & 0xFF;
            \u2603 = (n4 + 5) / 4096 + 1;
            if (\u2603 >= 256) {
                return;
            }
            if (\u2603 != 0 && \u2603 == \u2603) {
                this.a(\u2603, byArray, n4);
            } else {
                for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                    this.f.set(\u2603 + \u2603, true);
                }
                \u2603 = this.f.indexOf(true);
                \u2603 = 0;
                if (\u2603 != -1) {
                    for (\u2603 = \u2603; \u2603 < this.f.size(); ++\u2603) {
                        if (\u2603 != 0) {
                            \u2603 = this.f.get(\u2603).booleanValue() ? ++\u2603 : 0;
                        } else if (this.f.get(\u2603).booleanValue()) {
                            \u2603 = \u2603;
                            \u2603 = 1;
                        }
                        if (\u2603 >= \u2603) break;
                    }
                }
                if (\u2603 >= \u2603) {
                    \u2603 = \u2603;
                    this.a(n2, n3, \u2603 << 8 | \u2603);
                    for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                        this.f.set(\u2603 + \u2603, false);
                    }
                    this.a(\u2603, byArray, n4);
                } else {
                    this.c.seek(this.c.length());
                    \u2603 = this.f.size();
                    for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                        this.c.write(a);
                        this.f.add(false);
                    }
                    this.g += 4096 * \u2603;
                    this.a(\u2603, byArray, n4);
                    this.a(n2, n3, \u2603 << 8 | \u2603);
                }
            }
            this.b(n2, n3, (int)(MinecraftServer.az() / 1000L));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void a(int n2, byte[] byArray, int n3) throws IOException {
        this.c.seek(n2 * 4096);
        this.c.writeInt(n3 + 1);
        this.c.writeByte(2);
        this.c.write(byArray, 0, n3);
    }

    private boolean d(int n2, int n3) {
        return n2 < 0 || n2 >= 32 || n3 < 0 || n3 >= 32;
    }

    private int e(int n2, int n3) {
        return this.d[n2 + n3 * 32];
    }

    public boolean c(int n2, int n3) {
        return this.e(n2, n3) != 0;
    }

    private void a(int n2, int n3, int n4) throws IOException {
        this.d[n2 + n3 * 32] = n4;
        this.c.seek((n2 + n3 * 32) * 4);
        this.c.writeInt(n4);
    }

    private void b(int n2, int n3, int n4) throws IOException {
        this.e[n2 + n3 * 32] = n4;
        this.c.seek(4096 + (n2 + n3 * 32) * 4);
        this.c.writeInt(n4);
    }

    public void c() throws IOException {
        if (this.c != null) {
            this.c.close();
        }
    }

    class a
    extends ByteArrayOutputStream {
        private int b;
        private int c;

        public a(int n2, int n3) {
            super(8096);
            this.b = n2;
            this.c = n3;
        }

        @Override
        public void close() {
            anh.this.a(this.b, this.c, this.buf, this.count);
        }
    }
}

