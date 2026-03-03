/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class aef
extends aec {
    private ady b;
    private float c;

    public aef(ady ady2, float f2) {
        this.b = ady2;
        this.c = f2;
    }

    @Override
    public ady a(cj cj2) {
        return this.b;
    }

    @Override
    public ady[] a(ady[] adyArray2, int n2, int n3, int n4, int n5) {
        ady[] adyArray2;
        if (adyArray2 == null || adyArray2.length < n4 * n5) {
            adyArray2 = new ady[n4 * n5];
        }
        Arrays.fill(adyArray2, 0, n4 * n5, this.b);
        return adyArray2;
    }

    @Override
    public float[] a(float[] fArray2, int n2, int n3, int n4, int n5) {
        float[] fArray2;
        if (fArray2 == null || fArray2.length < n4 * n5) {
            fArray2 = new float[n4 * n5];
        }
        Arrays.fill(fArray2, 0, n4 * n5, this.c);
        return fArray2;
    }

    @Override
    public ady[] b(ady[] adyArray2, int n2, int n3, int n4, int n5) {
        ady[] adyArray2;
        if (adyArray2 == null || adyArray2.length < n4 * n5) {
            adyArray2 = new ady[n4 * n5];
        }
        Arrays.fill(adyArray2, 0, n4 * n5, this.b);
        return adyArray2;
    }

    @Override
    public ady[] a(ady[] adyArray, int n2, int n3, int n4, int n5, boolean bl2) {
        return this.b(adyArray, n2, n3, n4, n5);
    }

    @Override
    public cj a(int n2, int n3, int n4, List<ady> list, Random random) {
        if (list.contains(this.b)) {
            return new cj(n2 - n4 + random.nextInt(n4 * 2 + 1), 0, n3 - n4 + random.nextInt(n4 * 2 + 1));
        }
        return null;
    }

    @Override
    public boolean a(int n2, int n3, int n4, List<ady> list) {
        return list.contains(this.b);
    }
}

