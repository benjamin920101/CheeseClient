/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class amx
extends amy {
    public amx(adm adm2, int n2, int n3) {
        super(adm2, n2, n3);
    }

    @Override
    public boolean a(int n2, int n3) {
        return n2 == this.a && n3 == this.b;
    }

    @Override
    public int b(int n2, int n3) {
        return 0;
    }

    @Override
    public void a() {
    }

    @Override
    public void b() {
    }

    @Override
    public afh a(cj cj2) {
        return afi.a;
    }

    @Override
    public int b(cj cj2) {
        return 255;
    }

    @Override
    public int c(cj cj2) {
        return 0;
    }

    @Override
    public int a(ads ads2, cj cj2) {
        return ads2.c;
    }

    @Override
    public void a(ads ads2, cj cj2, int n2) {
    }

    @Override
    public int a(cj cj2, int n2) {
        return 0;
    }

    @Override
    public void a(pk pk2) {
    }

    @Override
    public void b(pk pk2) {
    }

    @Override
    public void a(pk pk2, int n2) {
    }

    @Override
    public boolean d(cj cj2) {
        return false;
    }

    @Override
    public akw a(cj cj2, amy.a a2) {
        return null;
    }

    @Override
    public void a(akw akw2) {
    }

    @Override
    public void a(cj cj2, akw akw2) {
    }

    @Override
    public void e(cj cj2) {
    }

    @Override
    public void c() {
    }

    @Override
    public void d() {
    }

    @Override
    public void e() {
    }

    @Override
    public void a(pk pk2, aug aug2, List<pk> list, Predicate<? super pk> predicate) {
    }

    @Override
    public <T extends pk> void a(Class<? extends T> clazz, aug aug2, List<T> list, Predicate<? super T> predicate) {
    }

    @Override
    public boolean a(boolean bl2) {
        return false;
    }

    @Override
    public Random a(long l2) {
        return new Random(this.p().J() + (long)(this.a * this.a * 4987142) + (long)(this.a * 5947611) + (long)(this.b * this.b) * 4392871L + (long)(this.b * 389711) ^ l2);
    }

    @Override
    public boolean f() {
        return true;
    }

    @Override
    public boolean c(int n2, int n3) {
        return true;
    }
}

