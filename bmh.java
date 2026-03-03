/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bmh
extends bly
implements bmn {
    private static final Logger h = LogManager.getLogger();
    public static final jy f = new jy("missingno");
    public static final jy g = new jy("textures/atlas/blocks.png");
    private final List<bmi> i = Lists.newArrayList();
    private final Map<String, bmi> j = Maps.newHashMap();
    private final Map<String, bmi> k = Maps.newHashMap();
    private final String l;
    private final bmb m;
    private int n;
    private final bmi o = new bmi("missingno");

    public bmh(String string) {
        this(string, null);
    }

    public bmh(String string, bmb bmb2) {
        this.l = string;
        this.m = bmb2;
    }

    private void g() {
        int[] nArray = bml.b;
        this.o.b(16);
        this.o.c(16);
        int[][] \u26032 = new int[this.n + 1][];
        \u26032[0] = nArray;
        this.o.a(Lists.newArrayList(new int[][][]{\u26032}));
    }

    @Override
    public void a(bni bni2) throws IOException {
        if (this.m != null) {
            this.a(bni2, this.m);
        }
    }

    public void a(bni bni2, bmb bmb2) {
        this.j.clear();
        bmb2.a(this);
        this.g();
        this.c();
        this.b(bni2);
    }

    public void b(bni bni2) {
        Object \u260311;
        int n3 = ave.C();
        bmf \u26032 = new bmf(n3, n3, true, 0, this.n);
        this.k.clear();
        this.i.clear();
        int n2 = Integer.MAX_VALUE;
        int n4 = 1 << this.n;
        for (Map.Entry<String, bmi> entry : this.j.entrySet()) {
            bmi hashMap = entry.getValue();
            jy jy2 = new jy(hashMap.i());
            jy jy3 = this.a(jy2, 0);
            try {
                bnh bnh2 = bni2.a(jy3);
                BufferedImage[] \u26035 = new BufferedImage[1 + this.n];
                \u26035[0] = bml.a(bnh2.b());
                bon \u26036 = (bon)bnh2.a("texture");
                if (\u26036 != null) {
                    List<Integer> list = \u26036.c();
                    if (!list.isEmpty()) {
                        int n42 = \u26035[0].getWidth();
                        int n5 = \u26035[0].getHeight();
                        if (ns.b(n42) != n42 || ns.b(n5) != n5) {
                            throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                    }
                    for (int n5 : list) {
                        if (n5 <= 0 || n5 >= \u26035.length - 1 || \u26035[n5] != null) continue;
                        jy \u26038 = this.a(jy2, n5);
                        try {
                            \u26035[n5] = bml.a(bni2.a(\u26038).b());
                        }
                        catch (IOException \u26039) {
                            h.error("Unable to load miplevel {} from: {}", n5, \u26038, \u26039);
                        }
                    }
                }
                \u260311 = (boa)bnh2.a("animation");
                hashMap.a(\u26035, (boa)\u260311);
            }
            catch (RuntimeException runtimeException) {
                h.error("Unable to parse metadata from " + jy3, (Throwable)runtimeException);
                continue;
            }
            catch (IOException iOException) {
                h.error("Using missing texture, unable to load " + jy3, (Throwable)iOException);
                continue;
            }
            n2 = Math.min(n2, Math.min(hashMap.c(), hashMap.d()));
            int n6 = Math.min(Integer.lowestOneBit(hashMap.c()), Integer.lowestOneBit(hashMap.d()));
            if (n6 < n4) {
                h.warn("Texture {} with size {}x{} limits mip level from {} to {}", jy3, hashMap.c(), hashMap.d(), ns.c(n4), ns.c(n6));
                n4 = n6;
            }
            \u26032.a(hashMap);
        }
        \u2603 = Math.min(n2, n4);
        \u2603 = ns.c(\u2603);
        if (\u2603 < this.n) {
            h.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", this.l, this.n, \u2603, \u2603);
            this.n = \u2603;
        }
        for (final bmi bmi2 : this.j.values()) {
            try {
                bmi2.d(this.n);
            }
            catch (Throwable throwable) {
                b b2 = b.a(throwable, "Applying mipmap");
                c c2 = b2.a("Sprite being mipmapped");
                c2.a("Sprite name", new Callable<String>(){

                    public String a() throws Exception {
                        return bmi2.i();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                c2.a("Sprite size", new Callable<String>(){

                    public String a() throws Exception {
                        return bmi2.c() + " x " + bmi2.d();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                c2.a("Sprite frames", new Callable<String>(){

                    public String a() throws Exception {
                        return bmi2.k() + " frames";
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                c2.a("Mipmap levels", this.n);
                throw new e(b2);
            }
        }
        this.o.d(this.n);
        \u26032.a(this.o);
        \u26032.c();
        h.info("Created: {}x{} {}-atlas", \u26032.a(), \u26032.b(), this.l);
        bml.a(this.b(), this.n, \u26032.a(), \u26032.b());
        HashMap<String, bmi> hashMap = Maps.newHashMap(this.j);
        for (bmi bmi3 : \u26032.d()) {
            String string = bmi3.i();
            hashMap.remove(string);
            this.k.put(string, bmi3);
            try {
                bml.a(bmi3.a(0), bmi3.c(), bmi3.d(), bmi3.a(), bmi3.b(), false, false);
            }
            catch (Throwable \u260310) {
                b b2 = b.a(\u260310, "Stitching texture atlas");
                \u260311 = b2.a("Texture being stitched together");
                ((c)\u260311).a("Atlas path", this.l);
                ((c)\u260311).a("Sprite", bmi3);
                throw new e(b2);
            }
            if (!bmi3.m()) continue;
            this.i.add(bmi3);
        }
        for (bmi bmi4 : hashMap.values()) {
            bmi4.a(this.o);
        }
    }

    private jy a(jy jy2, int n2) {
        if (n2 == 0) {
            return new jy(jy2.b(), String.format("%s/%s%s", this.l, jy2.a(), ".png"));
        }
        return new jy(jy2.b(), String.format("%s/mipmaps/%s.%d%s", this.l, jy2.a(), n2, ".png"));
    }

    public bmi a(String string) {
        bmi bmi2 = this.k.get(string);
        if (bmi2 == null) {
            bmi2 = this.o;
        }
        return bmi2;
    }

    public void d() {
        bml.b(this.b());
        for (bmi bmi2 : this.i) {
            bmi2.j();
        }
    }

    public bmi a(jy jy2) {
        if (jy2 == null) {
            throw new IllegalArgumentException("Location cannot be null!");
        }
        bmi bmi2 = this.j.get(jy2);
        if (bmi2 == null) {
            bmi2 = bmi.a(jy2);
            this.j.put(jy2.toString(), bmi2);
        }
        return bmi2;
    }

    @Override
    public void e() {
        this.d();
    }

    public void a(int n2) {
        this.n = n2;
    }

    public bmi f() {
        return this.o;
    }
}

