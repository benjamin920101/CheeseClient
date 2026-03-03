/*
 * Decompiled with CFR 0.152.
 */
import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

public class avn
implements bnj {
    private static final jy[] c = new jy[256];
    private int[] d = new int[256];
    public int a = 9;
    public Random b = new Random();
    private byte[] e = new byte[65536];
    private int[] f = new int[32];
    private final jy g;
    private final bmj h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private float m;
    private float n;
    private float o;
    private float p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;

    public avn(avh avh2, jy jy2, bmj bmj2, boolean bl2) {
        this.g = jy2;
        this.h = bmj2;
        this.k = bl2;
        bmj2.a(this.g);
        for (int i2 = 0; i2 < 32; ++i2) {
            \u2603 = (i2 >> 3 & 1) * 85;
            \u2603 = (i2 >> 2 & 1) * 170 + \u2603;
            \u2603 = (i2 >> 1 & 1) * 170 + \u2603;
            \u2603 = (i2 >> 0 & 1) * 170 + \u2603;
            if (i2 == 6) {
                \u2603 += 85;
            }
            if (avh2.e) {
                \u2603 = (\u2603 * 30 + \u2603 * 59 + \u2603 * 11) / 100;
                \u2603 = (\u2603 * 30 + \u2603 * 70) / 100;
                \u2603 = (\u2603 * 30 + \u2603 * 70) / 100;
                \u2603 = \u2603;
                \u2603 = \u2603;
                \u2603 = \u2603;
            }
            if (i2 >= 16) {
                \u2603 /= 4;
                \u2603 /= 4;
                \u2603 /= 4;
            }
            this.f[i2] = (\u2603 & 0xFF) << 16 | (\u2603 & 0xFF) << 8 | \u2603 & 0xFF;
        }
        this.d();
    }

    @Override
    public void a(bni bni2) {
        this.c();
    }

    private void c() {
        try {
            BufferedImage bufferedImage = bml.a(ave.A().Q().a(this.g).b());
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        int n2 = bufferedImage.getWidth();
        \u2603 = bufferedImage.getHeight();
        int[] \u26032 = new int[n2 * \u2603];
        bufferedImage.getRGB(0, 0, n2, \u2603, \u26032, 0, n2);
        \u2603 = \u2603 / 16;
        \u2603 = n2 / 16;
        \u2603 = 1;
        float \u26033 = 8.0f / (float)\u2603;
        for (\u2603 = 0; \u2603 < 256; ++\u2603) {
            int n3;
            \u2603 = \u2603 % 16;
            \u2603 = \u2603 / 16;
            if (\u2603 == 32) {
                this.d[\u2603] = 3 + \u2603;
            }
            for (n3 = \u2603 - 1; n3 >= 0; --n3) {
                \u2603 = \u2603 * \u2603 + n3;
                boolean bl2 = true;
                for (int i2 = 0; i2 < \u2603 && bl2; ++i2) {
                    \u2603 = (\u2603 * \u2603 + i2) * n2;
                    if ((\u26032[\u2603 + \u2603] >> 24 & 0xFF) == 0) continue;
                    bl2 = false;
                }
                if (!bl2) break;
            }
            this.d[\u2603] = (int)(0.5 + (double)((float)(++n3) * \u26033)) + \u2603;
        }
    }

    private void d() {
        InputStream inputStream = null;
        try {
            inputStream = ave.A().Q().a(new jy("font/glyph_sizes.bin")).b();
            inputStream.read(this.e);
        }
        catch (IOException \u26032) {
            try {
                throw new RuntimeException(\u26032);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(inputStream);
                throw throwable;
            }
        }
        IOUtils.closeQuietly(inputStream);
    }

    private float a(char c2, boolean bl2) {
        if (c2 == ' ') {
            return 4.0f;
        }
        int n2 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c2);
        if (n2 != -1 && !this.k) {
            return this.a(n2, bl2);
        }
        return this.b(c2, bl2);
    }

    private float a(int n2, boolean bl2) {
        int n3 = n2 % 16 * 8;
        \u2603 = n2 / 16 * 8;
        boolean \u26032 = bl2;
        this.h.a(this.g);
        \u2603 = this.d[n2];
        float \u26033 = (float)\u2603 - 0.01f;
        GL11.glBegin(5);
        GL11.glTexCoord2f((float)n3 / 128.0f, (float)\u2603 / 128.0f);
        GL11.glVertex3f(this.i + (float)\u26032, this.j, 0.0f);
        GL11.glTexCoord2f((float)n3 / 128.0f, ((float)\u2603 + 7.99f) / 128.0f);
        GL11.glVertex3f(this.i - (float)\u26032, this.j + 7.99f, 0.0f);
        GL11.glTexCoord2f(((float)n3 + \u26033 - 1.0f) / 128.0f, (float)\u2603 / 128.0f);
        GL11.glVertex3f(this.i + \u26033 - 1.0f + (float)\u26032, this.j, 0.0f);
        GL11.glTexCoord2f(((float)n3 + \u26033 - 1.0f) / 128.0f, ((float)\u2603 + 7.99f) / 128.0f);
        GL11.glVertex3f(this.i + \u26033 - 1.0f - (float)\u26032, this.j + 7.99f, 0.0f);
        GL11.glEnd();
        return \u2603;
    }

    private jy a(int n2) {
        if (c[n2] == null) {
            avn.c[n2] = new jy(String.format("textures/font/unicode_page_%02x.png", n2));
        }
        return c[n2];
    }

    private void b(int n2) {
        this.h.a(this.a(n2));
    }

    private float b(char c2, boolean bl2) {
        if (this.e[c2] == 0) {
            return 0.0f;
        }
        int n2 = c2 / 256;
        this.b(n2);
        \u2603 = this.e[c2] >>> 4;
        \u2603 = this.e[c2] & 0xF;
        float \u26032 = \u2603;
        float \u26033 = \u2603 + 1;
        float \u26034 = (float)(c2 % 16 * 16) + \u26032;
        float \u26035 = (c2 & 0xFF) / 16 * 16;
        float \u26036 = \u26033 - \u26032 - 0.02f;
        float \u26037 = bl2 ? 1.0f : 0.0f;
        GL11.glBegin(5);
        GL11.glTexCoord2f(\u26034 / 256.0f, \u26035 / 256.0f);
        GL11.glVertex3f(this.i + \u26037, this.j, 0.0f);
        GL11.glTexCoord2f(\u26034 / 256.0f, (\u26035 + 15.98f) / 256.0f);
        GL11.glVertex3f(this.i - \u26037, this.j + 7.99f, 0.0f);
        GL11.glTexCoord2f((\u26034 + \u26036) / 256.0f, \u26035 / 256.0f);
        GL11.glVertex3f(this.i + \u26036 / 2.0f + \u26037, this.j, 0.0f);
        GL11.glTexCoord2f((\u26034 + \u26036) / 256.0f, (\u26035 + 15.98f) / 256.0f);
        GL11.glVertex3f(this.i + \u26036 / 2.0f - \u26037, this.j + 7.99f, 0.0f);
        GL11.glEnd();
        return (\u26033 - \u26032) / 2.0f + 1.0f;
    }

    public int a(String string, float f2, float f3, int n2) {
        return this.a(string, f2, f3, n2, true);
    }

    public int a(String string, int n2, int n3, int n4) {
        return this.a(string, (float)n2, (float)n3, n4, false);
    }

    public int a(String string2, float f2, float f3, int n2, boolean bl2) {
        int \u26032;
        bfl.d();
        this.e();
        if (bl2) {
            \u26032 = this.b(string2, f2 + 1.0f, f3 + 1.0f, n2, true);
            \u26032 = Math.max(\u26032, this.b(string2, f2, f3, n2, false));
        } else {
            String string2;
            \u26032 = this.b(string2, f2, f3, n2, false);
        }
        return \u26032;
    }

    private String c(String string2) {
        try {
            Bidi bidi = new Bidi(new ArabicShaping(8).shape(string2), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        }
        catch (ArabicShapingException arabicShapingException) {
            String string2;
            return string2;
        }
    }

    private void e() {
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
    }

    private void a(String string, boolean bl2) {
        for (int i2 = 0; i2 < string.length(); ++i2) {
            bfd \u26034;
            int \u26032;
            char \u26033 = string.charAt(i2);
            if (\u26033 == '\u00a7' && i2 + 1 < string.length()) {
                \u26032 = "0123456789abcdefklmnor".indexOf(string.toLowerCase(Locale.ENGLISH).charAt(i2 + 1));
                if (\u26032 < 16) {
                    this.r = false;
                    this.s = false;
                    this.v = false;
                    this.u = false;
                    this.t = false;
                    if (\u26032 < 0 || \u26032 > 15) {
                        \u26032 = 15;
                    }
                    if (bl2) {
                        \u26032 += 16;
                    }
                    this.q = n2 = this.f[\u26032];
                    bfl.c((float)(n2 >> 16) / 255.0f, (float)(n2 >> 8 & 0xFF) / 255.0f, (float)(n2 & 0xFF) / 255.0f, this.p);
                } else if (\u26032 == 16) {
                    this.r = true;
                } else if (\u26032 == 17) {
                    this.s = true;
                } else if (\u26032 == 18) {
                    this.v = true;
                } else if (\u26032 == 19) {
                    this.u = true;
                } else if (\u26032 == 20) {
                    this.t = true;
                } else if (\u26032 == 21) {
                    this.r = false;
                    this.s = false;
                    this.v = false;
                    this.u = false;
                    this.t = false;
                    bfl.c(this.m, this.n, this.o, this.p);
                }
                ++i2;
                continue;
            }
            \u26032 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(\u26033);
            if (this.r && \u26032 != -1) {
                int n2 = this.a(\u26033);
                while (n2 != this.a(\u2603 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".charAt(\u26032 = this.b.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".length())))) {
                }
                \u26033 = \u2603;
            }
            float f2 = this.k ? 0.5f : 1.0f;
            char c2 = \u2603 = (\u26033 == '\u0000' || \u26032 == -1 || this.k) && bl2 ? (char)'\u0001' : '\u0000';
            if (\u2603 != '\u0000') {
                this.i -= f2;
                this.j -= f2;
            }
            \u2603 = this.a(\u26033, this.t);
            if (\u2603 != '\u0000') {
                this.i += f2;
                this.j += f2;
            }
            if (this.s) {
                this.i += f2;
                if (\u2603 != '\u0000') {
                    this.i -= f2;
                    this.j -= f2;
                }
                this.a(\u26033, this.t);
                this.i -= f2;
                if (\u2603 != '\u0000') {
                    this.i += f2;
                    this.j += f2;
                }
                \u2603 += 1.0f;
            }
            if (this.v) {
                bfx bfx2 = bfx.a();
                \u26034 = bfx2.c();
                bfl.x();
                \u26034.a(7, bms.e);
                \u26034.b((double)this.i, (double)(this.j + (float)(this.a / 2)), 0.0).d();
                \u26034.b((double)(this.i + \u2603), (double)(this.j + (float)(this.a / 2)), 0.0).d();
                \u26034.b((double)(this.i + \u2603), (double)(this.j + (float)(this.a / 2) - 1.0f), 0.0).d();
                \u26034.b((double)this.i, (double)(this.j + (float)(this.a / 2) - 1.0f), 0.0).d();
                bfx2.b();
                bfl.w();
            }
            if (this.u) {
                bfx2 = bfx.a();
                \u26034 = bfx2.c();
                bfl.x();
                \u26034.a(7, bms.e);
                int \u26035 = this.u ? -1 : 0;
                \u26034.b((double)(this.i + (float)\u26035), (double)(this.j + (float)this.a), 0.0).d();
                \u26034.b((double)(this.i + \u2603), (double)(this.j + (float)this.a), 0.0).d();
                \u26034.b((double)(this.i + \u2603), (double)(this.j + (float)this.a - 1.0f), 0.0).d();
                \u26034.b((double)(this.i + (float)\u26035), (double)(this.j + (float)this.a - 1.0f), 0.0).d();
                bfx2.b();
                bfl.w();
            }
            this.i += (float)((int)\u2603);
        }
    }

    private int a(String string2, int n2, int n3, int n4, int n5, boolean bl2) {
        String string2;
        if (this.l) {
            int n6 = this.a(this.c(string2));
            n2 = n2 + n4 - n6;
        }
        return this.b(string2, n2, n3, n5, bl2);
    }

    private int b(String string2, float f2, float f3, int n22, boolean bl2) {
        int n22;
        if (string2 == null) {
            return 0;
        }
        if (this.l) {
            String string2 = this.c(string2);
        }
        if ((n22 & 0xFC000000) == 0) {
            n22 |= 0xFF000000;
        }
        if (bl2) {
            n22 = (n22 & 0xFCFCFC) >> 2 | n22 & 0xFF000000;
        }
        this.m = (float)(n22 >> 16 & 0xFF) / 255.0f;
        this.n = (float)(n22 >> 8 & 0xFF) / 255.0f;
        this.o = (float)(n22 & 0xFF) / 255.0f;
        this.p = (float)(n22 >> 24 & 0xFF) / 255.0f;
        bfl.c(this.m, this.n, this.o, this.p);
        this.i = f2;
        this.j = f3;
        this.a(string2, bl2);
        return (int)this.i;
    }

    public int a(String string) {
        if (string == null) {
            return 0;
        }
        int n2 = 0;
        boolean \u26032 = false;
        for (\u2603 = 0; \u2603 < string.length(); ++\u2603) {
            char c2 = string.charAt(\u2603);
            int \u26033 = this.a(c2);
            if (\u26033 < 0 && \u2603 < string.length() - 1) {
                if ((c2 = string.charAt(++\u2603)) == 'l' || c2 == 'L') {
                    \u26032 = true;
                } else if (c2 == 'r' || c2 == 'R') {
                    \u26032 = false;
                }
                \u26033 = 0;
            }
            n2 += \u26033;
            if (!\u26032 || \u26033 <= 0) continue;
            ++n2;
        }
        return n2;
    }

    public int a(char c2) {
        if (c2 == '\u00a7') {
            return -1;
        }
        if (c2 == ' ') {
            return 4;
        }
        int n2 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c2);
        if (c2 > '\u0000' && n2 != -1 && !this.k) {
            return this.d[n2];
        }
        if (this.e[c2] != 0) {
            \u2603 = this.e[c2] >>> 4;
            \u2603 = this.e[c2] & 0xF;
            if (\u2603 > 7) {
                \u2603 = 15;
                \u2603 = 0;
            }
            return (++\u2603 - \u2603) / 2 + 1;
        }
        return 0;
    }

    public String a(String string, int n2) {
        return this.a(string, n2, false);
    }

    public String a(String string, int n2, boolean bl2) {
        StringBuilder stringBuilder = new StringBuilder();
        int \u26032 = 0;
        int \u26033 = bl2 ? string.length() - 1 : 0;
        int \u26034 = bl2 ? -1 : 1;
        boolean \u26035 = false;
        boolean \u26036 = false;
        for (int i2 = \u26033; i2 >= 0 && i2 < string.length() && \u26032 < n2; i2 += \u26034) {
            char c2 = string.charAt(i2);
            int \u26037 = this.a(c2);
            if (\u26035) {
                \u26035 = false;
                if (c2 == 'l' || c2 == 'L') {
                    \u26036 = true;
                } else if (c2 == 'r' || c2 == 'R') {
                    \u26036 = false;
                }
            } else if (\u26037 < 0) {
                \u26035 = true;
            } else {
                \u26032 += \u26037;
                if (\u26036) {
                    ++\u26032;
                }
            }
            if (\u26032 > n2) break;
            if (bl2) {
                stringBuilder.insert(0, c2);
                continue;
            }
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    private String d(String string) {
        while (string != null && string.endsWith("\n")) {
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }

    public void a(String string2, int n2, int n3, int n4, int n5) {
        this.e();
        this.q = n5;
        String string2 = this.d(string2);
        this.a(string2, n2, n3, n4, false);
    }

    private void a(String string, int n2, int n3, int n4, boolean bl2) {
        List<String> list = this.c(string, n4);
        for (String string2 : list) {
            this.a(string2, n2, n3, n4, this.q, bl2);
            n3 += this.a;
        }
    }

    public int b(String string, int n2) {
        return this.a * this.c(string, n2).size();
    }

    public void a(boolean bl2) {
        this.k = bl2;
    }

    public boolean a() {
        return this.k;
    }

    public void b(boolean bl2) {
        this.l = bl2;
    }

    public List<String> c(String string, int n2) {
        return Arrays.asList(this.d(string, n2).split("\n"));
    }

    String d(String string, int n2) {
        \u2603 = this.e(string, n2);
        if (string.length() <= \u2603) {
            return string;
        }
        String string2 = string.substring(0, \u2603);
        char \u26032 = string.charAt(\u2603);
        boolean \u26033 = \u26032 == ' ' || \u26032 == '\n';
        \u2603 = avn.b(string2) + string.substring(\u2603 + (\u26033 ? 1 : 0));
        return string2 + "\n" + this.d(\u2603, n2);
    }

    private int e(String string, int n2) {
        int \u26033;
        \u2603 = string.length();
        \u2603 = 0;
        \u26033 = -1;
        boolean \u26032 = false;
        for (int i2 = 0; i2 < \u2603; ++i2) {
            char c2 = string.charAt(i2);
            switch (c2) {
                case '\u00a7': {
                    char c3;
                    if (i2 >= \u2603 - 1) break;
                    if ((c3 = string.charAt(++i2)) == 'l' || c3 == 'L') {
                        \u26032 = true;
                        break;
                    }
                    if (c3 != 'r' && c3 != 'R' && !avn.c(c3)) break;
                    \u26032 = false;
                    break;
                }
                case '\n': {
                    --i2;
                    break;
                }
                case ' ': {
                    \u26033 = i2;
                }
                default: {
                    \u2603 += this.a(c2);
                    if (!\u26032) break;
                    ++\u2603;
                }
            }
            if (c2 == '\n') {
                \u26033 = ++i2;
                break;
            }
            if (\u2603 > n2) break;
        }
        if (i2 != \u2603 && \u26033 != -1 && \u26033 < i2) {
            return \u26033;
        }
        return i2;
    }

    private static boolean c(char c2) {
        return c2 >= '0' && c2 <= '9' || c2 >= 'a' && c2 <= 'f' || c2 >= 'A' && c2 <= 'F';
    }

    private static boolean d(char c2) {
        return c2 >= 'k' && c2 <= 'o' || c2 >= 'K' && c2 <= 'O' || c2 == 'r' || c2 == 'R';
    }

    public static String b(String string) {
        String \u26032;
        \u26032 = "";
        int n2 = -1;
        \u2603 = string.length();
        while ((n2 = string.indexOf(167, n2 + 1)) != -1) {
            if (n2 >= \u2603 - 1) continue;
            char c2 = string.charAt(n2 + 1);
            if (avn.c(c2)) {
                \u26032 = "\u00a7" + c2;
                continue;
            }
            if (!avn.d(c2)) continue;
            \u26032 = \u26032 + "\u00a7" + c2;
        }
        return \u26032;
    }

    public boolean b() {
        return this.l;
    }

    public int b(char c2) {
        return this.f["0123456789abcdef".indexOf(c2)];
    }
}

