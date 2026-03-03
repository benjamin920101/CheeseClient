/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.H;
import jaco.mp3.player.J;
import jaco.mp3.player.M;
import jaco.mp3.player.MP3Player;
import jaco.mp3.player.R;
import jaco.mp3.player.c;
import jaco.mp3.player.w;
import java.net.URL;

final class C
extends Thread {
    final /* synthetic */ MP3Player a;

    C(MP3Player mP3Player) {
        this.a = mP3Player;
    }

    @Override
    public final void run() {
        H h2;
        c c2 = new c();
        try {
            h2 = new H(((URL)MP3Player.a(this.a).get(MP3Player.b(this.a))).openStream());
        }
        catch (Exception exception) {
            h2 = null;
            exception.printStackTrace();
        }
        if (h2 != null) {
            Object object;
            R r2;
            try {
                r2 = new R();
                r2.a(c2);
            }
            catch (Exception exception) {
                r2 = null;
                try {
                    h2.a();
                }
                catch (Exception exception2) {}
                exception.printStackTrace();
            }
            if (r2 != null) {
                try {
                    while (!MP3Player.c(this.a)) {
                        if (MP3Player.d(this.a)) {
                            Thread.sleep(100L);
                            continue;
                        }
                        object = h2.b();
                        if (object != null) {
                            object = (J)c2.a((M)object, h2);
                            r2.a(((J)object).a(), 0, ((J)object).b());
                            h2.d();
                            continue;
                        }
                        break;
                    }
                }
                catch (Exception exception) {
                    object = exception;
                    exception.printStackTrace();
                }
                if (!MP3Player.c(this.a)) {
                    r2.c();
                }
                r2.a();
            }
            try {
                h2.a();
            }
            catch (Exception exception) {
                object = exception;
                exception.printStackTrace();
            }
        }
        if (!MP3Player.c(this.a)) {
            new w(this).start();
        }
        MP3Player.a(this.a, false);
        MP3Player.b(this.a, true);
    }
}

