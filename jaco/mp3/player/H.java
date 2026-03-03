/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.M;
import jaco.mp3.player.r;
import jaco.mp3.player.v;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public final class H {
    static byte a = 0;
    static byte b = 1;
    private final int[] c;
    private int d;
    private byte[] e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private final int[] j;
    private final PushbackInputStream k;
    private final M l;
    private final byte[] m;
    private r[] n;
    private byte[] o;
    private boolean p;

    public H(InputStream inputStream) {
        int n2;
        H h2;
        InputStream inputStream2;
        block16: {
            this.c = new int[433];
            this.e = new byte[1732];
            int[] nArray = new int[18];
            nArray[1] = 1;
            nArray[2] = 3;
            nArray[3] = 7;
            nArray[4] = 15;
            nArray[5] = 31;
            nArray[6] = 63;
            nArray[7] = 127;
            nArray[8] = 255;
            nArray[9] = 511;
            nArray[10] = 1023;
            nArray[11] = 2047;
            nArray[12] = 4095;
            nArray[13] = 8191;
            nArray[14] = 16383;
            nArray[15] = Short.MAX_VALUE;
            nArray[16] = 65535;
            nArray[17] = 131071;
            this.j = nArray;
            this.l = new M();
            this.m = new byte[4];
            this.n = new r[1];
            this.o = null;
            this.p = true;
            if (inputStream == null) {
                throw new NullPointerException("in");
            }
            inputStream2 = inputStream = new BufferedInputStream(inputStream);
            h2 = this;
            n2 = -1;
            try {
                try {
                    inputStream2.mark(10);
                    InputStream inputStream3 = inputStream2;
                    byte[] byArray = new byte[4];
                    int n3 = -10;
                    inputStream3.read(byArray, 0, 3);
                    if (byArray[0] == 73 && byArray[1] == 68 && byArray[2] == 51) {
                        inputStream3.read(byArray, 0, 3);
                        inputStream3.read(byArray, 0, 4);
                        n3 = (byArray[0] << 21) + (byArray[1] << 14) + (byArray[2] << 7) + byArray[3];
                    }
                    n2 = n3 + 10;
                }
                catch (IOException iOException) {
                    try {
                        inputStream2.reset();
                    }
                    catch (IOException iOException2) {}
                    break block16;
                }
            }
            catch (Throwable throwable) {
                try {
                    inputStream2.reset();
                }
                catch (IOException iOException) {}
                throw throwable;
            }
            try {
                inputStream2.reset();
            }
            catch (IOException iOException) {}
        }
        try {
            if (n2 > 0) {
                h2.o = new byte[n2];
                inputStream2.read(h2.o, 0, h2.o.length);
            }
        }
        catch (IOException iOException) {}
        this.p = true;
        this.k = new PushbackInputStream(inputStream, 1732);
        this.d();
    }

    public final void a() {
        try {
            this.k.close();
            return;
        }
        catch (IOException iOException) {
            throw H.a(258, iOException);
        }
    }

    public final M b() {
        M m2;
        block7: {
            m2 = null;
            try {
                m2 = ((H)((Object)v5)).f();
                if (((H)((Object)v5)).p) {
                    m2.a(((H)((Object)v5)).e);
                    ((H)((Object)v5)).p = false;
                }
            }
            catch (v v2) {
                v v3 = v2;
                if (v2.a() == 261) {
                    try {
                        ((H)((Object)v5)).d();
                        m2 = ((H)((Object)v5)).f();
                    }
                    catch (v v4) {
                        v v5 = v4;
                        if (v4.a() != 260) {
                            throw H.a(v5.a(), v5);
                        }
                        break block7;
                    }
                }
                if (v3.a() == 260) break block7;
                throw H.a(v3.a(), v3);
            }
        }
        return m2;
    }

    private M f() {
        if (this.d == -1) {
            H h2 = this;
            h2.l.a(h2, h2.n);
        }
        return this.l;
    }

    public final void c() {
        if (this.f == -1 && this.g == -1 && this.d > 0) {
            try {
                this.k.unread(this.e, 0, this.d);
                return;
            }
            catch (IOException iOException) {
                throw H.b(258);
            }
        }
    }

    public final void d() {
        this.d = -1;
        this.f = -1;
        this.g = -1;
    }

    public final boolean a(int n2) {
        int n3 = this.b(this.m, 0, 4);
        int n4 = this.m[0] << 24 & 0xFF000000 | this.m[1] << 16 & 0xFF0000 | this.m[2] << 8 & 0xFF00 | this.m[3] & 0xFF;
        try {
            this.k.unread(this.m, 0, n3);
        }
        catch (IOException iOException) {}
        boolean bl2 = false;
        switch (n3) {
            case 0: {
                bl2 = true;
                break;
            }
            case 4: {
                bl2 = this.a(n4, n2, this.h);
            }
        }
        return bl2;
    }

    protected static v b(int n2) {
        return new v(n2, null);
    }

    private static v a(int n2, Throwable throwable) {
        return new v(n2, throwable);
    }

    final int a(byte by) {
        boolean bl2;
        int n2 = this.b(this.m, 0, 3);
        if (n2 != 3) {
            throw H.a(260, null);
        }
        int n3 = this.m[0] << 16 & 0xFF0000 | this.m[1] << 8 & 0xFF00 | this.m[2] & 0xFF;
        do {
            n3 <<= 8;
            if (this.b(this.m, 3, 1) != 1) {
                throw H.a(260, null);
            }
            bl2 = this.a(n3 |= this.m[3] & 0xFF, (int)by, this.h);
            n2 = bl2 ? '\u0001' : '\u0000';
        } while (!bl2);
        return n3;
    }

    private boolean a(int n2, int n3, int n4) {
        boolean bl2;
        if (n3 == 0) {
            bl2 = (n2 & 0xFFE00000) == -2097152;
        } else {
            boolean bl3 = (n2 & 0xFFF80C00) == n4 && (n2 & 0xC0) == 192 == this.i ? true : (bl2 = false);
        }
        if (bl2) {
            boolean bl4 = bl2 = (n2 >>> 10 & 3) != 3;
        }
        if (bl2) {
            boolean bl5 = bl2 = (n2 >>> 17 & 3) != 0;
        }
        if (bl2) {
            bl2 = (n2 >>> 19 & 3) != 1;
        }
        return bl2;
    }

    final int c(int n2) {
        int n3 = this.a(this.e, 0, n2);
        this.d = n2;
        this.f = -1;
        this.g = -1;
        return n3;
    }

    final void e() {
        int n2 = 0;
        byte[] byArray = this.e;
        int n3 = this.d;
        int n4 = 0;
        while (n4 < n3) {
            byte by = 0;
            byte by2 = 0;
            byte by3 = 0;
            byte by4 = byArray[n4];
            if (n4 + 1 < n3) {
                by = byArray[n4 + 1];
            }
            if (n4 + 2 < n3) {
                by2 = byArray[n4 + 2];
            }
            if (n4 + 3 < n3) {
                by3 = byArray[n4 + 3];
            }
            this.c[n2++] = by4 << 24 & 0xFF000000 | by << 16 & 0xFF0000 | by2 << 8 & 0xFF00 | by3 & 0xFF;
            n4 += 4;
        }
        this.f = 0;
        this.g = 0;
    }

    public final int d(int n2) {
        int n3 = this.g + n2;
        if (this.f < 0) {
            this.f = 0;
        }
        if (n3 <= 32) {
            int n4 = this.c[this.f] >>> 32 - n3 & this.j[n2];
            if ((this.g += n2) == 32) {
                this.g = 0;
                ++this.f;
            }
            return n4;
        }
        int n5 = this.c[this.f] & 0xFFFF;
        ++this.f;
        int n6 = this.c[this.f] & 0xFFFF0000;
        n5 = n5 << 16 & 0xFFFF0000 | n6 >>> 16 & 0xFFFF;
        n5 >>>= 48 - n3;
        this.g = n3 - 32;
        return n5 &= this.j[n2];
    }

    final void e(int n2) {
        this.h = n2 & 0xFFFFFF3F;
        this.i = (n2 & 0xC0) == 192;
    }

    private int a(byte[] byArray, int n2, int n3) {
        int n4 = 0;
        try {
            while (n3 > 0) {
                int n5 = this.k.read(byArray, n2, n3);
                if (n5 == -1) {
                    while (n3-- > 0) {
                        byArray[n2++] = 0;
                    }
                    break;
                }
                n4 += n5;
                n2 += n5;
                n3 -= n5;
            }
        }
        catch (IOException iOException) {
            throw H.a(258, iOException);
        }
        return n4;
    }

    private int b(byte[] byArray, int n2, int n3) {
        int n4 = 0;
        try {
            while (n3 > 0) {
                int n5 = this.k.read(byArray, n2, n3);
                if (n5 != -1) {
                    n4 += n5;
                    n2 += n5;
                    n3 -= n5;
                    continue;
                }
                break;
            }
        }
        catch (IOException iOException) {
            throw H.a(258, iOException);
        }
        return n4;
    }
}

