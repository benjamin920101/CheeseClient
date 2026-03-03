/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.ByteVector;
import org.bridj.relocated.org.objectweb.asm.Edge;
import org.bridj.relocated.org.objectweb.asm.Frame;
import org.bridj.relocated.org.objectweb.asm.MethodWriter;

public class Label {
    public Object info;
    int a;
    int b;
    int c;
    private int d;
    private int[] e;
    int f;
    int g;
    Frame h;
    Label i;
    Edge j;
    Label k;

    public int getOffset() {
        if ((this.a & 2) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.c;
    }

    void a(MethodWriter methodWriter, ByteVector byteVector, int n2, boolean bl2) {
        if ((this.a & 2) == 0) {
            if (bl2) {
                this.a(-1 - n2, byteVector.b);
                byteVector.putInt(-1);
            } else {
                this.a(n2, byteVector.b);
                byteVector.putShort(-1);
            }
        } else if (bl2) {
            byteVector.putInt(this.c - n2);
        } else {
            byteVector.putShort(this.c - n2);
        }
    }

    private void a(int n2, int n3) {
        if (this.e == null) {
            this.e = new int[6];
        }
        if (this.d >= this.e.length) {
            int[] nArray = new int[this.e.length + 6];
            System.arraycopy(this.e, 0, nArray, 0, this.e.length);
            this.e = nArray;
        }
        this.e[this.d++] = n2;
        this.e[this.d++] = n3;
    }

    boolean a(MethodWriter methodWriter, int n2, byte[] byArray) {
        boolean bl2 = false;
        this.a |= 2;
        this.c = n2;
        int n3 = 0;
        while (n3 < this.d) {
            int n4;
            int n5 = this.e[n3++];
            int n6 = this.e[n3++];
            if (n5 >= 0) {
                n4 = n2 - n5;
                if (n4 < Short.MIN_VALUE || n4 > Short.MAX_VALUE) {
                    int n7 = byArray[n6 - 1] & 0xFF;
                    byArray[n6 - 1] = n7 <= 168 ? (byte)(n7 + 49) : (byte)(n7 + 20);
                    bl2 = true;
                }
                byArray[n6++] = (byte)(n4 >>> 8);
                byArray[n6] = (byte)n4;
                continue;
            }
            n4 = n2 + n5 + 1;
            byArray[n6++] = (byte)(n4 >>> 24);
            byArray[n6++] = (byte)(n4 >>> 16);
            byArray[n6++] = (byte)(n4 >>> 8);
            byArray[n6] = (byte)n4;
        }
        return bl2;
    }

    Label a() {
        return this.h == null ? this : this.h.b;
    }

    boolean a(long l2) {
        if ((this.a & 0x400) != 0) {
            return (this.e[(int)(l2 >>> 32)] & (int)l2) != 0;
        }
        return false;
    }

    boolean a(Label label) {
        if ((this.a & 0x400) == 0 || (label.a & 0x400) == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.length; ++i2) {
            if ((this.e[i2] & label.e[i2]) == 0) continue;
            return true;
        }
        return false;
    }

    void a(long l2, int n2) {
        if ((this.a & 0x400) == 0) {
            this.a |= 0x400;
            this.e = new int[(n2 - 1) / 32 + 1];
        }
        int n3 = (int)(l2 >>> 32);
        this.e[n3] = this.e[n3] | (int)l2;
    }

    void b(Label label, long l2, int n2) {
        Label label2 = this;
        while (label2 != null) {
            Edge edge;
            Label label3 = label2;
            label2 = label3.k;
            label3.k = null;
            if (label != null) {
                if ((label3.a & 0x800) != 0) continue;
                label3.a |= 0x800;
                if ((label3.a & 0x100) != 0 && !label3.a(label)) {
                    edge = new Edge();
                    edge.a = label3.f;
                    edge.b = label.j.b;
                    edge.c = label3.j;
                    label3.j = edge;
                }
            } else {
                if (label3.a(l2)) continue;
                label3.a(l2, n2);
            }
            edge = label3.j;
            while (edge != null) {
                if (((label3.a & 0x80) == 0 || edge != label3.j.c) && edge.b.k == null) {
                    edge.b.k = label2;
                    label2 = edge.b;
                }
                edge = edge.c;
            }
        }
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}

