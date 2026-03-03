/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahy
extends afc {
    protected ahy() {
        super(arm.e);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new all();
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        super.a(adm2, cj2, alz2, f2, n2);
        \u2603 = 15 + adm2.s.nextInt(15) + adm2.s.nextInt(15);
        this.b(adm2, cj2, \u2603);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return null;
    }
}

