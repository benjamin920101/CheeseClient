/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

public class ByteVector {
    byte[] a;
    int b;

    public ByteVector() {
        this.a = new byte[64];
    }

    public ByteVector(int n2) {
        this.a = new byte[n2];
    }

    public ByteVector putByte(int n2) {
        int n3 = this.b;
        if (n3 + 1 > this.a.length) {
            this.a(1);
        }
        this.a[n3++] = (byte)n2;
        this.b = n3;
        return this;
    }

    ByteVector a(int n2, int n3) {
        int n4 = this.b;
        if (n4 + 2 > this.a.length) {
            this.a(2);
        }
        byte[] byArray = this.a;
        byArray[n4++] = (byte)n2;
        byArray[n4++] = (byte)n3;
        this.b = n4;
        return this;
    }

    public ByteVector putShort(int n2) {
        int n3 = this.b;
        if (n3 + 2 > this.a.length) {
            this.a(2);
        }
        byte[] byArray = this.a;
        byArray[n3++] = (byte)(n2 >>> 8);
        byArray[n3++] = (byte)n2;
        this.b = n3;
        return this;
    }

    ByteVector b(int n2, int n3) {
        int n4 = this.b;
        if (n4 + 3 > this.a.length) {
            this.a(3);
        }
        byte[] byArray = this.a;
        byArray[n4++] = (byte)n2;
        byArray[n4++] = (byte)(n3 >>> 8);
        byArray[n4++] = (byte)n3;
        this.b = n4;
        return this;
    }

    public ByteVector putInt(int n2) {
        int n3 = this.b;
        if (n3 + 4 > this.a.length) {
            this.a(4);
        }
        byte[] byArray = this.a;
        byArray[n3++] = (byte)(n2 >>> 24);
        byArray[n3++] = (byte)(n2 >>> 16);
        byArray[n3++] = (byte)(n2 >>> 8);
        byArray[n3++] = (byte)n2;
        this.b = n3;
        return this;
    }

    public ByteVector putLong(long l2) {
        int n2 = this.b;
        if (n2 + 8 > this.a.length) {
            this.a(8);
        }
        byte[] byArray = this.a;
        int n3 = (int)(l2 >>> 32);
        byArray[n2++] = (byte)(n3 >>> 24);
        byArray[n2++] = (byte)(n3 >>> 16);
        byArray[n2++] = (byte)(n3 >>> 8);
        byArray[n2++] = (byte)n3;
        n3 = (int)l2;
        byArray[n2++] = (byte)(n3 >>> 24);
        byArray[n2++] = (byte)(n3 >>> 16);
        byArray[n2++] = (byte)(n3 >>> 8);
        byArray[n2++] = (byte)n3;
        this.b = n2;
        return this;
    }

    public ByteVector putUTF8(String string) {
        int n2 = this.b;
        int n3 = string.length();
        if (n2 + 2 + n3 > this.a.length) {
            this.a(2 + n3);
        }
        byte[] byArray = this.a;
        byArray[n2++] = (byte)(n3 >>> 8);
        byArray[n2++] = (byte)n3;
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4;
            char c2 = string.charAt(i2);
            if (c2 >= '\u0001' && c2 <= '\u007f') {
                byArray[n2++] = (byte)c2;
                continue;
            }
            int n5 = i2;
            for (n4 = i2; n4 < n3; ++n4) {
                c2 = string.charAt(n4);
                if (c2 >= '\u0001' && c2 <= '\u007f') {
                    ++n5;
                    continue;
                }
                if (c2 > '\u07ff') {
                    n5 += 3;
                    continue;
                }
                n5 += 2;
            }
            byArray[this.b] = (byte)(n5 >>> 8);
            byArray[this.b + 1] = (byte)n5;
            if (this.b + 2 + n5 > byArray.length) {
                this.b = n2;
                this.a(2 + n5);
                byArray = this.a;
            }
            for (n4 = i2; n4 < n3; ++n4) {
                c2 = string.charAt(n4);
                if (c2 >= '\u0001' && c2 <= '\u007f') {
                    byArray[n2++] = (byte)c2;
                    continue;
                }
                if (c2 > '\u07ff') {
                    byArray[n2++] = (byte)(0xE0 | c2 >> 12 & 0xF);
                    byArray[n2++] = (byte)(0x80 | c2 >> 6 & 0x3F);
                    byArray[n2++] = (byte)(0x80 | c2 & 0x3F);
                    continue;
                }
                byArray[n2++] = (byte)(0xC0 | c2 >> 6 & 0x1F);
                byArray[n2++] = (byte)(0x80 | c2 & 0x3F);
            }
            break;
        }
        this.b = n2;
        return this;
    }

    public ByteVector putByteArray(byte[] byArray, int n2, int n3) {
        if (this.b + n3 > this.a.length) {
            this.a(n3);
        }
        if (byArray != null) {
            System.arraycopy(byArray, n2, this.a, this.b, n3);
        }
        this.b += n3;
        return this;
    }

    private void a(int n2) {
        int n3 = 2 * this.a.length;
        int n4 = this.b + n2;
        byte[] byArray = new byte[n3 > n4 ? n3 : n4];
        System.arraycopy(this.a, 0, byArray, 0, this.b);
        this.a = byArray;
    }
}

