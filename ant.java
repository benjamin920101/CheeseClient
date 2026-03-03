/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ant {
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;
    public final float h;
    public final float i;
    public final float j;
    public final float k;
    public final float l;
    public final float m;
    public final float n;
    public final float o;
    public final float p;
    public final int q;
    public final boolean r;
    public final boolean s;
    public final int t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final boolean x;
    public final boolean y;
    public final boolean z;
    public final boolean A;
    public final int B;
    public final boolean C;
    public final int D;
    public final boolean E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final int J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;
    public final int S;
    public final int T;
    public final int U;
    public final int V;
    public final int W;
    public final int X;
    public final int Y;
    public final int Z;
    public final int aa;
    public final int ab;
    public final int ac;
    public final int ad;
    public final int ae;
    public final int af;
    public final int ag;
    public final int ah;
    public final int ai;
    public final int aj;
    public final int ak;
    public final int al;
    public final int am;
    public final int an;
    public final int ao;
    public final int ap;
    public final int aq;
    public final int ar;
    public final int as;
    public final int at;
    public final int au;
    public final int av;
    public final int aw;
    public final int ax;
    public final int ay;
    public final int az;

    private ant(a a2) {
        this.a = a2.b;
        this.b = a2.c;
        this.c = a2.d;
        this.d = a2.e;
        this.e = a2.f;
        this.f = a2.g;
        this.g = a2.h;
        this.h = a2.i;
        this.i = a2.j;
        this.j = a2.k;
        this.k = a2.l;
        this.l = a2.m;
        this.m = a2.n;
        this.n = a2.o;
        this.o = a2.p;
        this.p = a2.q;
        this.q = a2.r;
        this.r = a2.s;
        this.s = a2.t;
        this.t = a2.u;
        this.u = a2.v;
        this.v = a2.w;
        this.w = a2.x;
        this.x = a2.y;
        this.y = a2.z;
        this.z = a2.A;
        this.A = a2.B;
        this.B = a2.C;
        this.C = a2.D;
        this.D = a2.E;
        this.E = a2.F;
        this.F = a2.G;
        this.G = a2.H;
        this.H = a2.I;
        this.I = a2.J;
        this.J = a2.K;
        this.K = a2.L;
        this.L = a2.M;
        this.M = a2.N;
        this.N = a2.O;
        this.O = a2.P;
        this.P = a2.Q;
        this.Q = a2.R;
        this.R = a2.S;
        this.S = a2.T;
        this.T = a2.U;
        this.U = a2.V;
        this.V = a2.W;
        this.W = a2.X;
        this.X = a2.Y;
        this.Y = a2.Z;
        this.Z = a2.aa;
        this.aa = a2.ab;
        this.ab = a2.ac;
        this.ac = a2.ad;
        this.ad = a2.ae;
        this.ae = a2.af;
        this.af = a2.ag;
        this.ag = a2.ah;
        this.ah = a2.ai;
        this.ai = a2.aj;
        this.aj = a2.ak;
        this.ak = a2.al;
        this.al = a2.am;
        this.am = a2.an;
        this.an = a2.ao;
        this.ao = a2.ap;
        this.ap = a2.aq;
        this.aq = a2.ar;
        this.ar = a2.as;
        this.as = a2.at;
        this.at = a2.au;
        this.au = a2.av;
        this.av = a2.aw;
        this.aw = a2.ax;
        this.ax = a2.ay;
        this.ay = a2.az;
        this.az = a2.aA;
    }

    public static class b
    implements JsonDeserializer<a>,
    JsonSerializer<a> {
        public a a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            a \u26032 = new a();
            try {
                \u26032.b = ni.a(jsonObject, "coordinateScale", \u26032.b);
                \u26032.c = ni.a(jsonObject, "heightScale", \u26032.c);
                \u26032.e = ni.a(jsonObject, "lowerLimitScale", \u26032.e);
                \u26032.d = ni.a(jsonObject, "upperLimitScale", \u26032.d);
                \u26032.f = ni.a(jsonObject, "depthNoiseScaleX", \u26032.f);
                \u26032.g = ni.a(jsonObject, "depthNoiseScaleZ", \u26032.g);
                \u26032.h = ni.a(jsonObject, "depthNoiseScaleExponent", \u26032.h);
                \u26032.i = ni.a(jsonObject, "mainNoiseScaleX", \u26032.i);
                \u26032.j = ni.a(jsonObject, "mainNoiseScaleY", \u26032.j);
                \u26032.k = ni.a(jsonObject, "mainNoiseScaleZ", \u26032.k);
                \u26032.l = ni.a(jsonObject, "baseSize", \u26032.l);
                \u26032.m = ni.a(jsonObject, "stretchY", \u26032.m);
                \u26032.n = ni.a(jsonObject, "biomeDepthWeight", \u26032.n);
                \u26032.o = ni.a(jsonObject, "biomeDepthOffset", \u26032.o);
                \u26032.p = ni.a(jsonObject, "biomeScaleWeight", \u26032.p);
                \u26032.q = ni.a(jsonObject, "biomeScaleOffset", \u26032.q);
                \u26032.r = ni.a(jsonObject, "seaLevel", \u26032.r);
                \u26032.s = ni.a(jsonObject, "useCaves", \u26032.s);
                \u26032.t = ni.a(jsonObject, "useDungeons", \u26032.t);
                \u26032.u = ni.a(jsonObject, "dungeonChance", \u26032.u);
                \u26032.v = ni.a(jsonObject, "useStrongholds", \u26032.v);
                \u26032.w = ni.a(jsonObject, "useVillages", \u26032.w);
                \u26032.x = ni.a(jsonObject, "useMineShafts", \u26032.x);
                \u26032.y = ni.a(jsonObject, "useTemples", \u26032.y);
                \u26032.z = ni.a(jsonObject, "useMonuments", \u26032.z);
                \u26032.A = ni.a(jsonObject, "useRavines", \u26032.A);
                \u26032.B = ni.a(jsonObject, "useWaterLakes", \u26032.B);
                \u26032.C = ni.a(jsonObject, "waterLakeChance", \u26032.C);
                \u26032.D = ni.a(jsonObject, "useLavaLakes", \u26032.D);
                \u26032.E = ni.a(jsonObject, "lavaLakeChance", \u26032.E);
                \u26032.F = ni.a(jsonObject, "useLavaOceans", \u26032.F);
                \u26032.G = ni.a(jsonObject, "fixedBiome", \u26032.G);
                if (\u26032.G >= 38 || \u26032.G < -1) {
                    \u26032.G = -1;
                } else if (\u26032.G >= ady.x.az) {
                    \u26032.G += 2;
                }
                \u26032.H = ni.a(jsonObject, "biomeSize", \u26032.H);
                \u26032.I = ni.a(jsonObject, "riverSize", \u26032.I);
                \u26032.J = ni.a(jsonObject, "dirtSize", \u26032.J);
                \u26032.K = ni.a(jsonObject, "dirtCount", \u26032.K);
                \u26032.L = ni.a(jsonObject, "dirtMinHeight", \u26032.L);
                \u26032.M = ni.a(jsonObject, "dirtMaxHeight", \u26032.M);
                \u26032.N = ni.a(jsonObject, "gravelSize", \u26032.N);
                \u26032.O = ni.a(jsonObject, "gravelCount", \u26032.O);
                \u26032.P = ni.a(jsonObject, "gravelMinHeight", \u26032.P);
                \u26032.Q = ni.a(jsonObject, "gravelMaxHeight", \u26032.Q);
                \u26032.R = ni.a(jsonObject, "graniteSize", \u26032.R);
                \u26032.S = ni.a(jsonObject, "graniteCount", \u26032.S);
                \u26032.T = ni.a(jsonObject, "graniteMinHeight", \u26032.T);
                \u26032.U = ni.a(jsonObject, "graniteMaxHeight", \u26032.U);
                \u26032.V = ni.a(jsonObject, "dioriteSize", \u26032.V);
                \u26032.W = ni.a(jsonObject, "dioriteCount", \u26032.W);
                \u26032.X = ni.a(jsonObject, "dioriteMinHeight", \u26032.X);
                \u26032.Y = ni.a(jsonObject, "dioriteMaxHeight", \u26032.Y);
                \u26032.Z = ni.a(jsonObject, "andesiteSize", \u26032.Z);
                \u26032.aa = ni.a(jsonObject, "andesiteCount", \u26032.aa);
                \u26032.ab = ni.a(jsonObject, "andesiteMinHeight", \u26032.ab);
                \u26032.ac = ni.a(jsonObject, "andesiteMaxHeight", \u26032.ac);
                \u26032.ad = ni.a(jsonObject, "coalSize", \u26032.ad);
                \u26032.ae = ni.a(jsonObject, "coalCount", \u26032.ae);
                \u26032.af = ni.a(jsonObject, "coalMinHeight", \u26032.af);
                \u26032.ag = ni.a(jsonObject, "coalMaxHeight", \u26032.ag);
                \u26032.ah = ni.a(jsonObject, "ironSize", \u26032.ah);
                \u26032.ai = ni.a(jsonObject, "ironCount", \u26032.ai);
                \u26032.aj = ni.a(jsonObject, "ironMinHeight", \u26032.aj);
                \u26032.ak = ni.a(jsonObject, "ironMaxHeight", \u26032.ak);
                \u26032.al = ni.a(jsonObject, "goldSize", \u26032.al);
                \u26032.am = ni.a(jsonObject, "goldCount", \u26032.am);
                \u26032.an = ni.a(jsonObject, "goldMinHeight", \u26032.an);
                \u26032.ao = ni.a(jsonObject, "goldMaxHeight", \u26032.ao);
                \u26032.ap = ni.a(jsonObject, "redstoneSize", \u26032.ap);
                \u26032.aq = ni.a(jsonObject, "redstoneCount", \u26032.aq);
                \u26032.ar = ni.a(jsonObject, "redstoneMinHeight", \u26032.ar);
                \u26032.as = ni.a(jsonObject, "redstoneMaxHeight", \u26032.as);
                \u26032.at = ni.a(jsonObject, "diamondSize", \u26032.at);
                \u26032.au = ni.a(jsonObject, "diamondCount", \u26032.au);
                \u26032.av = ni.a(jsonObject, "diamondMinHeight", \u26032.av);
                \u26032.aw = ni.a(jsonObject, "diamondMaxHeight", \u26032.aw);
                \u26032.ax = ni.a(jsonObject, "lapisSize", \u26032.ax);
                \u26032.ay = ni.a(jsonObject, "lapisCount", \u26032.ay);
                \u26032.az = ni.a(jsonObject, "lapisCenterHeight", \u26032.az);
                \u26032.aA = ni.a(jsonObject, "lapisSpread", \u26032.aA);
            }
            catch (Exception exception) {
                // empty catch block
            }
            return \u26032;
        }

        public JsonElement a(a a2, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("coordinateScale", Float.valueOf(a2.b));
            jsonObject.addProperty("heightScale", Float.valueOf(a2.c));
            jsonObject.addProperty("lowerLimitScale", Float.valueOf(a2.e));
            jsonObject.addProperty("upperLimitScale", Float.valueOf(a2.d));
            jsonObject.addProperty("depthNoiseScaleX", Float.valueOf(a2.f));
            jsonObject.addProperty("depthNoiseScaleZ", Float.valueOf(a2.g));
            jsonObject.addProperty("depthNoiseScaleExponent", Float.valueOf(a2.h));
            jsonObject.addProperty("mainNoiseScaleX", Float.valueOf(a2.i));
            jsonObject.addProperty("mainNoiseScaleY", Float.valueOf(a2.j));
            jsonObject.addProperty("mainNoiseScaleZ", Float.valueOf(a2.k));
            jsonObject.addProperty("baseSize", Float.valueOf(a2.l));
            jsonObject.addProperty("stretchY", Float.valueOf(a2.m));
            jsonObject.addProperty("biomeDepthWeight", Float.valueOf(a2.n));
            jsonObject.addProperty("biomeDepthOffset", Float.valueOf(a2.o));
            jsonObject.addProperty("biomeScaleWeight", Float.valueOf(a2.p));
            jsonObject.addProperty("biomeScaleOffset", Float.valueOf(a2.q));
            jsonObject.addProperty("seaLevel", a2.r);
            jsonObject.addProperty("useCaves", a2.s);
            jsonObject.addProperty("useDungeons", a2.t);
            jsonObject.addProperty("dungeonChance", a2.u);
            jsonObject.addProperty("useStrongholds", a2.v);
            jsonObject.addProperty("useVillages", a2.w);
            jsonObject.addProperty("useMineShafts", a2.x);
            jsonObject.addProperty("useTemples", a2.y);
            jsonObject.addProperty("useMonuments", a2.z);
            jsonObject.addProperty("useRavines", a2.A);
            jsonObject.addProperty("useWaterLakes", a2.B);
            jsonObject.addProperty("waterLakeChance", a2.C);
            jsonObject.addProperty("useLavaLakes", a2.D);
            jsonObject.addProperty("lavaLakeChance", a2.E);
            jsonObject.addProperty("useLavaOceans", a2.F);
            jsonObject.addProperty("fixedBiome", a2.G);
            jsonObject.addProperty("biomeSize", a2.H);
            jsonObject.addProperty("riverSize", a2.I);
            jsonObject.addProperty("dirtSize", a2.J);
            jsonObject.addProperty("dirtCount", a2.K);
            jsonObject.addProperty("dirtMinHeight", a2.L);
            jsonObject.addProperty("dirtMaxHeight", a2.M);
            jsonObject.addProperty("gravelSize", a2.N);
            jsonObject.addProperty("gravelCount", a2.O);
            jsonObject.addProperty("gravelMinHeight", a2.P);
            jsonObject.addProperty("gravelMaxHeight", a2.Q);
            jsonObject.addProperty("graniteSize", a2.R);
            jsonObject.addProperty("graniteCount", a2.S);
            jsonObject.addProperty("graniteMinHeight", a2.T);
            jsonObject.addProperty("graniteMaxHeight", a2.U);
            jsonObject.addProperty("dioriteSize", a2.V);
            jsonObject.addProperty("dioriteCount", a2.W);
            jsonObject.addProperty("dioriteMinHeight", a2.X);
            jsonObject.addProperty("dioriteMaxHeight", a2.Y);
            jsonObject.addProperty("andesiteSize", a2.Z);
            jsonObject.addProperty("andesiteCount", a2.aa);
            jsonObject.addProperty("andesiteMinHeight", a2.ab);
            jsonObject.addProperty("andesiteMaxHeight", a2.ac);
            jsonObject.addProperty("coalSize", a2.ad);
            jsonObject.addProperty("coalCount", a2.ae);
            jsonObject.addProperty("coalMinHeight", a2.af);
            jsonObject.addProperty("coalMaxHeight", a2.ag);
            jsonObject.addProperty("ironSize", a2.ah);
            jsonObject.addProperty("ironCount", a2.ai);
            jsonObject.addProperty("ironMinHeight", a2.aj);
            jsonObject.addProperty("ironMaxHeight", a2.ak);
            jsonObject.addProperty("goldSize", a2.al);
            jsonObject.addProperty("goldCount", a2.am);
            jsonObject.addProperty("goldMinHeight", a2.an);
            jsonObject.addProperty("goldMaxHeight", a2.ao);
            jsonObject.addProperty("redstoneSize", a2.ap);
            jsonObject.addProperty("redstoneCount", a2.aq);
            jsonObject.addProperty("redstoneMinHeight", a2.ar);
            jsonObject.addProperty("redstoneMaxHeight", a2.as);
            jsonObject.addProperty("diamondSize", a2.at);
            jsonObject.addProperty("diamondCount", a2.au);
            jsonObject.addProperty("diamondMinHeight", a2.av);
            jsonObject.addProperty("diamondMaxHeight", a2.aw);
            jsonObject.addProperty("lapisSize", a2.ax);
            jsonObject.addProperty("lapisCount", a2.ay);
            jsonObject.addProperty("lapisCenterHeight", a2.az);
            jsonObject.addProperty("lapisSpread", a2.aA);
            return jsonObject;
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((a)object, type, jsonSerializationContext);
        }
    }

    public static class a {
        static final Gson a = new GsonBuilder().registerTypeAdapter((Type)((Object)a.class), new b()).create();
        public float b = 684.412f;
        public float c = 684.412f;
        public float d = 512.0f;
        public float e = 512.0f;
        public float f = 200.0f;
        public float g = 200.0f;
        public float h = 0.5f;
        public float i = 80.0f;
        public float j = 160.0f;
        public float k = 80.0f;
        public float l = 8.5f;
        public float m = 12.0f;
        public float n = 1.0f;
        public float o = 0.0f;
        public float p = 1.0f;
        public float q = 0.0f;
        public int r = 63;
        public boolean s = true;
        public boolean t = true;
        public int u = 8;
        public boolean v = true;
        public boolean w = true;
        public boolean x = true;
        public boolean y = true;
        public boolean z = true;
        public boolean A = true;
        public boolean B = true;
        public int C = 4;
        public boolean D = true;
        public int E = 80;
        public boolean F = false;
        public int G = -1;
        public int H = 4;
        public int I = 4;
        public int J = 33;
        public int K = 10;
        public int L = 0;
        public int M = 256;
        public int N = 33;
        public int O = 8;
        public int P = 0;
        public int Q = 256;
        public int R = 33;
        public int S = 10;
        public int T = 0;
        public int U = 80;
        public int V = 33;
        public int W = 10;
        public int X = 0;
        public int Y = 80;
        public int Z = 33;
        public int aa = 10;
        public int ab = 0;
        public int ac = 80;
        public int ad = 17;
        public int ae = 20;
        public int af = 0;
        public int ag = 128;
        public int ah = 9;
        public int ai = 20;
        public int aj = 0;
        public int ak = 64;
        public int al = 9;
        public int am = 2;
        public int an = 0;
        public int ao = 32;
        public int ap = 8;
        public int aq = 8;
        public int ar = 0;
        public int as = 16;
        public int at = 8;
        public int au = 1;
        public int av = 0;
        public int aw = 16;
        public int ax = 7;
        public int ay = 1;
        public int az = 16;
        public int aA = 16;

        public static a a(String string) {
            if (string.length() == 0) {
                return new a();
            }
            try {
                return a.fromJson(string, a.class);
            }
            catch (Exception exception) {
                return new a();
            }
        }

        public String toString() {
            return a.toJson(this);
        }

        public a() {
            this.a();
        }

        public void a() {
            this.b = 684.412f;
            this.c = 684.412f;
            this.d = 512.0f;
            this.e = 512.0f;
            this.f = 200.0f;
            this.g = 200.0f;
            this.h = 0.5f;
            this.i = 80.0f;
            this.j = 160.0f;
            this.k = 80.0f;
            this.l = 8.5f;
            this.m = 12.0f;
            this.n = 1.0f;
            this.o = 0.0f;
            this.p = 1.0f;
            this.q = 0.0f;
            this.r = 63;
            this.s = true;
            this.t = true;
            this.u = 8;
            this.v = true;
            this.w = true;
            this.x = true;
            this.y = true;
            this.z = true;
            this.A = true;
            this.B = true;
            this.C = 4;
            this.D = true;
            this.E = 80;
            this.F = false;
            this.G = -1;
            this.H = 4;
            this.I = 4;
            this.J = 33;
            this.K = 10;
            this.L = 0;
            this.M = 256;
            this.N = 33;
            this.O = 8;
            this.P = 0;
            this.Q = 256;
            this.R = 33;
            this.S = 10;
            this.T = 0;
            this.U = 80;
            this.V = 33;
            this.W = 10;
            this.X = 0;
            this.Y = 80;
            this.Z = 33;
            this.aa = 10;
            this.ab = 0;
            this.ac = 80;
            this.ad = 17;
            this.ae = 20;
            this.af = 0;
            this.ag = 128;
            this.ah = 9;
            this.ai = 20;
            this.aj = 0;
            this.ak = 64;
            this.al = 9;
            this.am = 2;
            this.an = 0;
            this.ao = 32;
            this.ap = 8;
            this.aq = 8;
            this.ar = 0;
            this.as = 16;
            this.at = 8;
            this.au = 1;
            this.av = 0;
            this.aw = 16;
            this.ax = 7;
            this.ay = 1;
            this.az = 16;
            this.aA = 16;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || this.getClass() != object.getClass()) {
                return false;
            }
            a a2 = (a)object;
            if (this.aa != a2.aa) {
                return false;
            }
            if (this.ac != a2.ac) {
                return false;
            }
            if (this.ab != a2.ab) {
                return false;
            }
            if (this.Z != a2.Z) {
                return false;
            }
            if (Float.compare(a2.l, this.l) != 0) {
                return false;
            }
            if (Float.compare(a2.o, this.o) != 0) {
                return false;
            }
            if (Float.compare(a2.n, this.n) != 0) {
                return false;
            }
            if (Float.compare(a2.q, this.q) != 0) {
                return false;
            }
            if (Float.compare(a2.p, this.p) != 0) {
                return false;
            }
            if (this.H != a2.H) {
                return false;
            }
            if (this.ae != a2.ae) {
                return false;
            }
            if (this.ag != a2.ag) {
                return false;
            }
            if (this.af != a2.af) {
                return false;
            }
            if (this.ad != a2.ad) {
                return false;
            }
            if (Float.compare(a2.b, this.b) != 0) {
                return false;
            }
            if (Float.compare(a2.h, this.h) != 0) {
                return false;
            }
            if (Float.compare(a2.f, this.f) != 0) {
                return false;
            }
            if (Float.compare(a2.g, this.g) != 0) {
                return false;
            }
            if (this.au != a2.au) {
                return false;
            }
            if (this.aw != a2.aw) {
                return false;
            }
            if (this.av != a2.av) {
                return false;
            }
            if (this.at != a2.at) {
                return false;
            }
            if (this.W != a2.W) {
                return false;
            }
            if (this.Y != a2.Y) {
                return false;
            }
            if (this.X != a2.X) {
                return false;
            }
            if (this.V != a2.V) {
                return false;
            }
            if (this.K != a2.K) {
                return false;
            }
            if (this.M != a2.M) {
                return false;
            }
            if (this.L != a2.L) {
                return false;
            }
            if (this.J != a2.J) {
                return false;
            }
            if (this.u != a2.u) {
                return false;
            }
            if (this.G != a2.G) {
                return false;
            }
            if (this.am != a2.am) {
                return false;
            }
            if (this.ao != a2.ao) {
                return false;
            }
            if (this.an != a2.an) {
                return false;
            }
            if (this.al != a2.al) {
                return false;
            }
            if (this.S != a2.S) {
                return false;
            }
            if (this.U != a2.U) {
                return false;
            }
            if (this.T != a2.T) {
                return false;
            }
            if (this.R != a2.R) {
                return false;
            }
            if (this.O != a2.O) {
                return false;
            }
            if (this.Q != a2.Q) {
                return false;
            }
            if (this.P != a2.P) {
                return false;
            }
            if (this.N != a2.N) {
                return false;
            }
            if (Float.compare(a2.c, this.c) != 0) {
                return false;
            }
            if (this.ai != a2.ai) {
                return false;
            }
            if (this.ak != a2.ak) {
                return false;
            }
            if (this.aj != a2.aj) {
                return false;
            }
            if (this.ah != a2.ah) {
                return false;
            }
            if (this.az != a2.az) {
                return false;
            }
            if (this.ay != a2.ay) {
                return false;
            }
            if (this.ax != a2.ax) {
                return false;
            }
            if (this.aA != a2.aA) {
                return false;
            }
            if (this.E != a2.E) {
                return false;
            }
            if (Float.compare(a2.e, this.e) != 0) {
                return false;
            }
            if (Float.compare(a2.i, this.i) != 0) {
                return false;
            }
            if (Float.compare(a2.j, this.j) != 0) {
                return false;
            }
            if (Float.compare(a2.k, this.k) != 0) {
                return false;
            }
            if (this.aq != a2.aq) {
                return false;
            }
            if (this.as != a2.as) {
                return false;
            }
            if (this.ar != a2.ar) {
                return false;
            }
            if (this.ap != a2.ap) {
                return false;
            }
            if (this.I != a2.I) {
                return false;
            }
            if (this.r != a2.r) {
                return false;
            }
            if (Float.compare(a2.m, this.m) != 0) {
                return false;
            }
            if (Float.compare(a2.d, this.d) != 0) {
                return false;
            }
            if (this.s != a2.s) {
                return false;
            }
            if (this.t != a2.t) {
                return false;
            }
            if (this.D != a2.D) {
                return false;
            }
            if (this.F != a2.F) {
                return false;
            }
            if (this.x != a2.x) {
                return false;
            }
            if (this.A != a2.A) {
                return false;
            }
            if (this.v != a2.v) {
                return false;
            }
            if (this.y != a2.y) {
                return false;
            }
            if (this.z != a2.z) {
                return false;
            }
            if (this.w != a2.w) {
                return false;
            }
            if (this.B != a2.B) {
                return false;
            }
            return this.C == a2.C;
        }

        public int hashCode() {
            int n2 = this.b != 0.0f ? Float.floatToIntBits(this.b) : 0;
            n2 = 31 * n2 + (this.c != 0.0f ? Float.floatToIntBits(this.c) : 0);
            n2 = 31 * n2 + (this.d != 0.0f ? Float.floatToIntBits(this.d) : 0);
            n2 = 31 * n2 + (this.e != 0.0f ? Float.floatToIntBits(this.e) : 0);
            n2 = 31 * n2 + (this.f != 0.0f ? Float.floatToIntBits(this.f) : 0);
            n2 = 31 * n2 + (this.g != 0.0f ? Float.floatToIntBits(this.g) : 0);
            n2 = 31 * n2 + (this.h != 0.0f ? Float.floatToIntBits(this.h) : 0);
            n2 = 31 * n2 + (this.i != 0.0f ? Float.floatToIntBits(this.i) : 0);
            n2 = 31 * n2 + (this.j != 0.0f ? Float.floatToIntBits(this.j) : 0);
            n2 = 31 * n2 + (this.k != 0.0f ? Float.floatToIntBits(this.k) : 0);
            n2 = 31 * n2 + (this.l != 0.0f ? Float.floatToIntBits(this.l) : 0);
            n2 = 31 * n2 + (this.m != 0.0f ? Float.floatToIntBits(this.m) : 0);
            n2 = 31 * n2 + (this.n != 0.0f ? Float.floatToIntBits(this.n) : 0);
            n2 = 31 * n2 + (this.o != 0.0f ? Float.floatToIntBits(this.o) : 0);
            n2 = 31 * n2 + (this.p != 0.0f ? Float.floatToIntBits(this.p) : 0);
            n2 = 31 * n2 + (this.q != 0.0f ? Float.floatToIntBits(this.q) : 0);
            n2 = 31 * n2 + this.r;
            n2 = 31 * n2 + (this.s ? 1 : 0);
            n2 = 31 * n2 + (this.t ? 1 : 0);
            n2 = 31 * n2 + this.u;
            n2 = 31 * n2 + (this.v ? 1 : 0);
            n2 = 31 * n2 + (this.w ? 1 : 0);
            n2 = 31 * n2 + (this.x ? 1 : 0);
            n2 = 31 * n2 + (this.y ? 1 : 0);
            n2 = 31 * n2 + (this.z ? 1 : 0);
            n2 = 31 * n2 + (this.A ? 1 : 0);
            n2 = 31 * n2 + (this.B ? 1 : 0);
            n2 = 31 * n2 + this.C;
            n2 = 31 * n2 + (this.D ? 1 : 0);
            n2 = 31 * n2 + this.E;
            n2 = 31 * n2 + (this.F ? 1 : 0);
            n2 = 31 * n2 + this.G;
            n2 = 31 * n2 + this.H;
            n2 = 31 * n2 + this.I;
            n2 = 31 * n2 + this.J;
            n2 = 31 * n2 + this.K;
            n2 = 31 * n2 + this.L;
            n2 = 31 * n2 + this.M;
            n2 = 31 * n2 + this.N;
            n2 = 31 * n2 + this.O;
            n2 = 31 * n2 + this.P;
            n2 = 31 * n2 + this.Q;
            n2 = 31 * n2 + this.R;
            n2 = 31 * n2 + this.S;
            n2 = 31 * n2 + this.T;
            n2 = 31 * n2 + this.U;
            n2 = 31 * n2 + this.V;
            n2 = 31 * n2 + this.W;
            n2 = 31 * n2 + this.X;
            n2 = 31 * n2 + this.Y;
            n2 = 31 * n2 + this.Z;
            n2 = 31 * n2 + this.aa;
            n2 = 31 * n2 + this.ab;
            n2 = 31 * n2 + this.ac;
            n2 = 31 * n2 + this.ad;
            n2 = 31 * n2 + this.ae;
            n2 = 31 * n2 + this.af;
            n2 = 31 * n2 + this.ag;
            n2 = 31 * n2 + this.ah;
            n2 = 31 * n2 + this.ai;
            n2 = 31 * n2 + this.aj;
            n2 = 31 * n2 + this.ak;
            n2 = 31 * n2 + this.al;
            n2 = 31 * n2 + this.am;
            n2 = 31 * n2 + this.an;
            n2 = 31 * n2 + this.ao;
            n2 = 31 * n2 + this.ap;
            n2 = 31 * n2 + this.aq;
            n2 = 31 * n2 + this.ar;
            n2 = 31 * n2 + this.as;
            n2 = 31 * n2 + this.at;
            n2 = 31 * n2 + this.au;
            n2 = 31 * n2 + this.av;
            n2 = 31 * n2 + this.aw;
            n2 = 31 * n2 + this.ax;
            n2 = 31 * n2 + this.ay;
            n2 = 31 * n2 + this.az;
            n2 = 31 * n2 + this.aA;
            return n2;
        }

        public ant b() {
            return new ant(this);
        }
    }
}

