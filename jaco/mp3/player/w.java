/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.C;

final class w
extends Thread {
    private /* synthetic */ C a;

    w(C c2) {
        this.a = c2;
    }

    @Override
    public final void run() {
        Thread thread = thread.a;
        ((C)thread).a.skipForward();
    }
}

