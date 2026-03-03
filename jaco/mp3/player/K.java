/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.B;
import jaco.mp3.player.Q;
import jaco.mp3.player.k;
import jaco.mp3.player.m;
import jaco.mp3.player.z;

final class K
extends B
implements m {
    @Override
    protected final void b() {
        if (this.c == 3) {
            int n2 = 0;
            while (n2 < this.d) {
                this.e[n2] = new k(n2);
                ++n2;
            }
            return;
        }
        if (this.c == 1) {
            int n3 = 0;
            while (n3 < this.b.k()) {
                this.e[n3] = new Q(n3);
                ++n3;
            }
            while (n3 < this.d) {
                this.e[n3] = new z(n3);
                ++n3;
            }
            return;
        }
        int n4 = 0;
        while (n4 < this.d) {
            this.e[n4] = new Q(n4);
            ++n4;
        }
    }

    @Override
    protected final void c() {
        int n2 = 0;
        while (n2 < this.d) {
            ((k)this.e[n2]).a(this.a, this.f);
            ++n2;
        }
    }
}

