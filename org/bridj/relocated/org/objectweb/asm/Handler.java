/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.Label;

class Handler {
    Label a;
    Label b;
    Label c;
    String d;
    int e;
    Handler f;

    Handler() {
    }

    static Handler a(Handler handler, Label label, Label label2) {
        int n2;
        if (handler == null) {
            return null;
        }
        handler.f = Handler.a(handler.f, label, label2);
        int n3 = handler.a.c;
        int n4 = handler.b.c;
        int n5 = label.c;
        int n6 = n2 = label2 == null ? Integer.MAX_VALUE : label2.c;
        if (n5 < n4 && n2 > n3) {
            if (n5 <= n3) {
                if (n2 >= n4) {
                    handler = handler.f;
                } else {
                    handler.a = label2;
                }
            } else if (n2 >= n4) {
                handler.b = label;
            } else {
                Handler handler2 = new Handler();
                handler2.a = label2;
                handler2.b = handler.b;
                handler2.c = handler.c;
                handler2.d = handler.d;
                handler2.e = handler.e;
                handler2.f = handler.f;
                handler.b = label;
                handler.f = handler2;
            }
        }
        return handler;
    }
}

