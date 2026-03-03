/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

public final class Handle {
    final int a;
    final String b;
    final String c;
    final String d;

    public Handle(int n2, String string, String string2, String string3) {
        this.a = n2;
        this.b = string;
        this.c = string2;
        this.d = string3;
    }

    public int getTag() {
        return this.a;
    }

    public String getOwner() {
        return this.b;
    }

    public String getName() {
        return this.c;
    }

    public String getDesc() {
        return this.d;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Handle)) {
            return false;
        }
        Handle handle = (Handle)object;
        return this.a == handle.a && this.b.equals(handle.b) && this.c.equals(handle.c) && this.d.equals(handle.d);
    }

    public int hashCode() {
        return this.a + this.b.hashCode() * this.c.hashCode() * this.d.hashCode();
    }

    public String toString() {
        return this.b + '.' + this.c + this.d + " (" + this.a + ')';
    }
}

