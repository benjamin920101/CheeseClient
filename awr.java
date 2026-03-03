/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.realms.RealmsButton;
import net.minecraft.realms.RealmsScreen;

public class awr
extends axu {
    private RealmsScreen a;

    public awr(RealmsScreen realmsScreen) {
        this.a = realmsScreen;
        this.n = Collections.synchronizedList(Lists.newArrayList());
    }

    public RealmsScreen a() {
        return this.a;
    }

    @Override
    public void b() {
        this.a.init();
        super.b();
    }

    public void a(String string, int n2, int n3, int n4) {
        super.a(this.q, string, n2, n3, n4);
    }

    public void b(String string, int n2, int n3, int n4) {
        super.c(this.q, string, n2, n3, n4);
    }

    @Override
    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.a.blit(n2, n3, n4, n5, n6, n7);
        super.b(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        super.a(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void c() {
        super.c();
    }

    @Override
    public boolean d() {
        return super.d();
    }

    @Override
    public void b_(int n2) {
        super.b_(n2);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.a.render(n2, n3, f2);
    }

    @Override
    public void a(zx zx2, int n2, int n3) {
        super.a(zx2, n2, n3);
    }

    @Override
    public void a(String string, int n2, int n3) {
        super.a(string, n2, n3);
    }

    @Override
    public void a(List<String> list, int n2, int n3) {
        super.a(list, n2, n3);
    }

    @Override
    public void e() {
        this.a.tick();
        super.e();
    }

    public int h() {
        return this.q.a;
    }

    public int c(String string) {
        return this.q.a(string);
    }

    public void c(String string, int n2, int n3, int n4) {
        this.q.a(string, (float)n2, (float)n3, n4);
    }

    public List<String> a(String string, int n2) {
        return this.q.c(string, n2);
    }

    @Override
    public final void a(avs avs2) {
        this.a.buttonClicked(((awp)avs2).f());
    }

    public void i() {
        this.n.clear();
    }

    public void a(RealmsButton realmsButton) {
        this.n.add(realmsButton.getProxy());
    }

    public List<RealmsButton> j() {
        ArrayList<RealmsButton> arrayList = Lists.newArrayListWithExpectedSize(this.n.size());
        for (avs avs2 : this.n) {
            arrayList.add(((awp)avs2).f());
        }
        return arrayList;
    }

    public void b(RealmsButton realmsButton) {
        this.n.remove(realmsButton);
    }

    @Override
    public void a(int n2, int n3, int n4) {
        this.a.mouseClicked(n2, n3, n4);
        super.a(n2, n3, n4);
    }

    @Override
    public void k() {
        this.a.mouseEvent();
        super.k();
    }

    @Override
    public void l() {
        this.a.keyboardEvent();
        super.l();
    }

    @Override
    public void b(int n2, int n3, int n4) {
        this.a.mouseReleased(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, int n4, long l2) {
        this.a.mouseDragged(n2, n3, n4, l2);
    }

    @Override
    public void a(char c2, int n2) {
        this.a.keyPressed(c2, n2);
    }

    @Override
    public void a(boolean bl2, int n2) {
        this.a.confirmResult(bl2, n2);
    }

    @Override
    public void m() {
        this.a.removed();
        super.m();
    }
}

