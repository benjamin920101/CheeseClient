/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.c;

public abstract class a {
    private boolean a = false;
    private c b = null;

    public final synchronized void a(c c2) {
        if (!this.f()) {
            boolean bl2;
            this.b = c2;
            v0.a = bl2 = true;
        }
    }

    private synchronized boolean f() {
        return this.a;
    }

    public final synchronized void a() {
        if (this.f()) {
            this.b();
            boolean bl2 = false;
            a a2 = this;
            this.a = bl2;
            this.b = null;
        }
    }

    protected void b() {
    }

    public final void a(short[] sArray, int n2, int n3) {
        if (this.f()) {
            this.b(sArray, 0, n3);
        }
    }

    protected void b(short[] sArray, int n2, int n3) {
    }

    public final void c() {
        if (this.f()) {
            this.d();
        }
    }

    protected void d() {
    }

    protected final c e() {
        return this.b;
    }
}

