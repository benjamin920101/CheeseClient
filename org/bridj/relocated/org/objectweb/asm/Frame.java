/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.Item;
import org.bridj.relocated.org.objectweb.asm.Label;
import org.bridj.relocated.org.objectweb.asm.Type;

final class Frame {
    static final int[] a;
    Label b;
    int[] c;
    int[] d;
    private int[] e;
    private int[] f;
    private int g;
    private int h;
    private int[] i;

    Frame() {
    }

    private int a(int n2) {
        if (this.e == null || n2 >= this.e.length) {
            return 0x2000000 | n2;
        }
        int n3 = this.e[n2];
        if (n3 == 0) {
            n3 = this.e[n2] = 0x2000000 | n2;
        }
        return n3;
    }

    private void a(int n2, int n3) {
        int n4;
        if (this.e == null) {
            this.e = new int[10];
        }
        if (n2 >= (n4 = this.e.length)) {
            int[] nArray = new int[Math.max(n2 + 1, 2 * n4)];
            System.arraycopy(this.e, 0, nArray, 0, n4);
            this.e = nArray;
        }
        this.e[n2] = n3;
    }

    private void b(int n2) {
        int n3;
        if (this.f == null) {
            this.f = new int[10];
        }
        if (this.g >= (n3 = this.f.length)) {
            int[] nArray = new int[Math.max(this.g + 1, 2 * n3)];
            System.arraycopy(this.f, 0, nArray, 0, n3);
            this.f = nArray;
        }
        this.f[this.g++] = n2;
        int n4 = this.b.f + this.g;
        if (n4 > this.b.g) {
            this.b.g = n4;
        }
    }

    private void a(ClassWriter classWriter, String string) {
        int n2 = Frame.b(classWriter, string);
        if (n2 != 0) {
            this.b(n2);
            if (n2 == 0x1000004 || n2 == 0x1000003) {
                this.b(0x1000000);
            }
        }
    }

    private static int b(ClassWriter classWriter, String string) {
        int n2;
        int n3 = string.charAt(0) == '(' ? string.indexOf(41) + 1 : 0;
        switch (string.charAt(n3)) {
            case 'V': {
                return 0;
            }
            case 'B': 
            case 'C': 
            case 'I': 
            case 'S': 
            case 'Z': {
                return 0x1000001;
            }
            case 'F': {
                return 0x1000002;
            }
            case 'J': {
                return 0x1000004;
            }
            case 'D': {
                return 0x1000003;
            }
            case 'L': {
                String string2 = string.substring(n3 + 1, string.length() - 1);
                return 0x1700000 | classWriter.c(string2);
            }
        }
        int n4 = n3 + 1;
        while (string.charAt(n4) == '[') {
            ++n4;
        }
        switch (string.charAt(n4)) {
            case 'Z': {
                n2 = 0x1000009;
                break;
            }
            case 'C': {
                n2 = 0x100000B;
                break;
            }
            case 'B': {
                n2 = 0x100000A;
                break;
            }
            case 'S': {
                n2 = 0x100000C;
                break;
            }
            case 'I': {
                n2 = 0x1000001;
                break;
            }
            case 'F': {
                n2 = 0x1000002;
                break;
            }
            case 'J': {
                n2 = 0x1000004;
                break;
            }
            case 'D': {
                n2 = 0x1000003;
                break;
            }
            default: {
                String string3 = string.substring(n4 + 1, string.length() - 1);
                n2 = 0x1700000 | classWriter.c(string3);
            }
        }
        return n4 - n3 << 28 | n2;
    }

    private int a() {
        if (this.g > 0) {
            return this.f[--this.g];
        }
        return 0x3000000 | -(--this.b.f);
    }

    private void c(int n2) {
        if (this.g >= n2) {
            this.g -= n2;
        } else {
            this.b.f -= n2 - this.g;
            this.g = 0;
        }
    }

    private void a(String string) {
        char c2 = string.charAt(0);
        if (c2 == '(') {
            this.c((Type.getArgumentsAndReturnSizes(string) >> 2) - 1);
        } else if (c2 == 'J' || c2 == 'D') {
            this.c(2);
        } else {
            this.c(1);
        }
    }

    private void d(int n2) {
        int n3;
        if (this.i == null) {
            this.i = new int[2];
        }
        if (this.h >= (n3 = this.i.length)) {
            int[] nArray = new int[Math.max(this.h + 1, 2 * n3)];
            System.arraycopy(this.i, 0, nArray, 0, n3);
            this.i = nArray;
        }
        this.i[this.h++] = n2;
    }

    private int a(ClassWriter classWriter, int n2) {
        int n3;
        if (n2 == 0x1000006) {
            n3 = 0x1700000 | classWriter.c(classWriter.I);
        } else if ((n2 & 0xFFF00000) == 0x1800000) {
            String string = classWriter.H[n2 & 0xFFFFF].g;
            n3 = 0x1700000 | classWriter.c(string);
        } else {
            return n2;
        }
        for (int i2 = 0; i2 < this.h; ++i2) {
            int n4 = this.i[i2];
            int n5 = n4 & 0xF0000000;
            int n6 = n4 & 0xF000000;
            if (n6 == 0x2000000) {
                n4 = n5 + this.c[n4 & 0x7FFFFF];
            } else if (n6 == 0x3000000) {
                n4 = n5 + this.d[this.d.length - (n4 & 0x7FFFFF)];
            }
            if (n2 != n4) continue;
            return n3;
        }
        return n2;
    }

    void a(ClassWriter classWriter, int n2, Type[] typeArray, int n3) {
        this.c = new int[n3];
        this.d = new int[0];
        int n4 = 0;
        if ((n2 & 8) == 0) {
            this.c[n4++] = (n2 & 0x40000) == 0 ? 0x1700000 | classWriter.c(classWriter.I) : 0x1000006;
        }
        for (int i2 = 0; i2 < typeArray.length; ++i2) {
            int n5 = Frame.b(classWriter, typeArray[i2].getDescriptor());
            this.c[n4++] = n5;
            if (n5 != 0x1000004 && n5 != 0x1000003) continue;
            this.c[n4++] = 0x1000000;
        }
        while (n4 < n3) {
            this.c[n4++] = 0x1000000;
        }
    }

    void a(int n2, int n3, ClassWriter classWriter, Item item) {
        block0 : switch (n2) {
            case 0: 
            case 116: 
            case 117: 
            case 118: 
            case 119: 
            case 145: 
            case 146: 
            case 147: 
            case 167: 
            case 177: {
                break;
            }
            case 1: {
                this.b(0x1000005);
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 16: 
            case 17: 
            case 21: {
                this.b(0x1000001);
                break;
            }
            case 9: 
            case 10: 
            case 22: {
                this.b(0x1000004);
                this.b(0x1000000);
                break;
            }
            case 11: 
            case 12: 
            case 13: 
            case 23: {
                this.b(0x1000002);
                break;
            }
            case 14: 
            case 15: 
            case 24: {
                this.b(0x1000003);
                this.b(0x1000000);
                break;
            }
            case 18: {
                switch (item.b) {
                    case 3: {
                        this.b(0x1000001);
                        break block0;
                    }
                    case 5: {
                        this.b(0x1000004);
                        this.b(0x1000000);
                        break block0;
                    }
                    case 4: {
                        this.b(0x1000002);
                        break block0;
                    }
                    case 6: {
                        this.b(0x1000003);
                        this.b(0x1000000);
                        break block0;
                    }
                    case 7: {
                        this.b(0x1700000 | classWriter.c("java/lang/Class"));
                        break block0;
                    }
                    case 8: {
                        this.b(0x1700000 | classWriter.c("java/lang/String"));
                        break block0;
                    }
                    case 16: {
                        this.b(0x1700000 | classWriter.c("java/lang/invoke/MethodType"));
                        break block0;
                    }
                }
                this.b(0x1700000 | classWriter.c("java/lang/invoke/MethodHandle"));
                break;
            }
            case 25: {
                this.b(this.a(n3));
                break;
            }
            case 46: 
            case 51: 
            case 52: 
            case 53: {
                this.c(2);
                this.b(0x1000001);
                break;
            }
            case 47: 
            case 143: {
                this.c(2);
                this.b(0x1000004);
                this.b(0x1000000);
                break;
            }
            case 48: {
                this.c(2);
                this.b(0x1000002);
                break;
            }
            case 49: 
            case 138: {
                this.c(2);
                this.b(0x1000003);
                this.b(0x1000000);
                break;
            }
            case 50: {
                this.c(1);
                int n4 = this.a();
                this.b(-268435456 + n4);
                break;
            }
            case 54: 
            case 56: 
            case 58: {
                int n5 = this.a();
                this.a(n3, n5);
                if (n3 <= 0) break;
                int n6 = this.a(n3 - 1);
                if (n6 == 0x1000004 || n6 == 0x1000003) {
                    this.a(n3 - 1, 0x1000000);
                    break;
                }
                if ((n6 & 0xF000000) == 0x1000000) break;
                this.a(n3 - 1, n6 | 0x800000);
                break;
            }
            case 55: 
            case 57: {
                this.c(1);
                int n7 = this.a();
                this.a(n3, n7);
                this.a(n3 + 1, 0x1000000);
                if (n3 <= 0) break;
                int n8 = this.a(n3 - 1);
                if (n8 == 0x1000004 || n8 == 0x1000003) {
                    this.a(n3 - 1, 0x1000000);
                    break;
                }
                if ((n8 & 0xF000000) == 0x1000000) break;
                this.a(n3 - 1, n8 | 0x800000);
                break;
            }
            case 79: 
            case 81: 
            case 83: 
            case 84: 
            case 85: 
            case 86: {
                this.c(3);
                break;
            }
            case 80: 
            case 82: {
                this.c(4);
                break;
            }
            case 87: 
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: 
            case 170: 
            case 171: 
            case 172: 
            case 174: 
            case 176: 
            case 191: 
            case 194: 
            case 195: 
            case 198: 
            case 199: {
                this.c(1);
                break;
            }
            case 88: 
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: 
            case 173: 
            case 175: {
                this.c(2);
                break;
            }
            case 89: {
                int n9 = this.a();
                this.b(n9);
                this.b(n9);
                break;
            }
            case 90: {
                int n10 = this.a();
                int n11 = this.a();
                this.b(n10);
                this.b(n11);
                this.b(n10);
                break;
            }
            case 91: {
                int n12 = this.a();
                int n13 = this.a();
                int n14 = this.a();
                this.b(n12);
                this.b(n14);
                this.b(n13);
                this.b(n12);
                break;
            }
            case 92: {
                int n15 = this.a();
                int n16 = this.a();
                this.b(n16);
                this.b(n15);
                this.b(n16);
                this.b(n15);
                break;
            }
            case 93: {
                int n17 = this.a();
                int n18 = this.a();
                int n19 = this.a();
                this.b(n18);
                this.b(n17);
                this.b(n19);
                this.b(n18);
                this.b(n17);
                break;
            }
            case 94: {
                int n20 = this.a();
                int n21 = this.a();
                int n22 = this.a();
                int n23 = this.a();
                this.b(n21);
                this.b(n20);
                this.b(n23);
                this.b(n22);
                this.b(n21);
                this.b(n20);
                break;
            }
            case 95: {
                int n24 = this.a();
                int n25 = this.a();
                this.b(n24);
                this.b(n25);
                break;
            }
            case 96: 
            case 100: 
            case 104: 
            case 108: 
            case 112: 
            case 120: 
            case 122: 
            case 124: 
            case 126: 
            case 128: 
            case 130: 
            case 136: 
            case 142: 
            case 149: 
            case 150: {
                this.c(2);
                this.b(0x1000001);
                break;
            }
            case 97: 
            case 101: 
            case 105: 
            case 109: 
            case 113: 
            case 127: 
            case 129: 
            case 131: {
                this.c(4);
                this.b(0x1000004);
                this.b(0x1000000);
                break;
            }
            case 98: 
            case 102: 
            case 106: 
            case 110: 
            case 114: 
            case 137: 
            case 144: {
                this.c(2);
                this.b(0x1000002);
                break;
            }
            case 99: 
            case 103: 
            case 107: 
            case 111: 
            case 115: {
                this.c(4);
                this.b(0x1000003);
                this.b(0x1000000);
                break;
            }
            case 121: 
            case 123: 
            case 125: {
                this.c(3);
                this.b(0x1000004);
                this.b(0x1000000);
                break;
            }
            case 132: {
                this.a(n3, 0x1000001);
                break;
            }
            case 133: 
            case 140: {
                this.c(1);
                this.b(0x1000004);
                this.b(0x1000000);
                break;
            }
            case 134: {
                this.c(1);
                this.b(0x1000002);
                break;
            }
            case 135: 
            case 141: {
                this.c(1);
                this.b(0x1000003);
                this.b(0x1000000);
                break;
            }
            case 139: 
            case 190: 
            case 193: {
                this.c(1);
                this.b(0x1000001);
                break;
            }
            case 148: 
            case 151: 
            case 152: {
                this.c(4);
                this.b(0x1000001);
                break;
            }
            case 168: 
            case 169: {
                throw new RuntimeException("JSR/RET are not supported with computeFrames option");
            }
            case 178: {
                this.a(classWriter, item.i);
                break;
            }
            case 179: {
                this.a(item.i);
                break;
            }
            case 180: {
                this.c(1);
                this.a(classWriter, item.i);
                break;
            }
            case 181: {
                this.a(item.i);
                this.a();
                break;
            }
            case 182: 
            case 183: 
            case 184: 
            case 185: {
                this.a(item.i);
                if (n2 != 184) {
                    int n26 = this.a();
                    if (n2 == 183 && item.h.charAt(0) == '<') {
                        this.d(n26);
                    }
                }
                this.a(classWriter, item.i);
                break;
            }
            case 186: {
                this.a(item.h);
                this.a(classWriter, item.h);
                break;
            }
            case 187: {
                this.b(0x1800000 | classWriter.a(item.g, n3));
                break;
            }
            case 188: {
                this.a();
                switch (n3) {
                    case 4: {
                        this.b(0x11000009);
                        break block0;
                    }
                    case 5: {
                        this.b(0x1100000B);
                        break block0;
                    }
                    case 8: {
                        this.b(0x1100000A);
                        break block0;
                    }
                    case 9: {
                        this.b(0x1100000C);
                        break block0;
                    }
                    case 10: {
                        this.b(0x11000001);
                        break block0;
                    }
                    case 6: {
                        this.b(0x11000002);
                        break block0;
                    }
                    case 7: {
                        this.b(0x11000003);
                        break block0;
                    }
                }
                this.b(0x11000004);
                break;
            }
            case 189: {
                String string = item.g;
                this.a();
                if (string.charAt(0) == '[') {
                    this.a(classWriter, '[' + string);
                    break;
                }
                this.b(0x11700000 | classWriter.c(string));
                break;
            }
            case 192: {
                String string = item.g;
                this.a();
                if (string.charAt(0) == '[') {
                    this.a(classWriter, string);
                    break;
                }
                this.b(0x1700000 | classWriter.c(string));
                break;
            }
            default: {
                this.c(n3);
                this.a(classWriter, item.g);
            }
        }
    }

    boolean a(ClassWriter classWriter, Frame frame, int n2) {
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        boolean bl2 = false;
        int n8 = this.c.length;
        int n9 = this.d.length;
        if (frame.c == null) {
            frame.c = new int[n8];
            bl2 = true;
        }
        for (n7 = 0; n7 < n8; ++n7) {
            if (this.e != null && n7 < this.e.length) {
                n6 = this.e[n7];
                if (n6 == 0) {
                    n5 = this.c[n7];
                } else {
                    n4 = n6 & 0xF0000000;
                    n3 = n6 & 0xF000000;
                    if (n3 == 0x1000000) {
                        n5 = n6;
                    } else {
                        n5 = n3 == 0x2000000 ? n4 + this.c[n6 & 0x7FFFFF] : n4 + this.d[n9 - (n6 & 0x7FFFFF)];
                        if ((n6 & 0x800000) != 0 && (n5 == 0x1000004 || n5 == 0x1000003)) {
                            n5 = 0x1000000;
                        }
                    }
                }
            } else {
                n5 = this.c[n7];
            }
            if (this.i != null) {
                n5 = this.a(classWriter, n5);
            }
            bl2 |= Frame.a(classWriter, n5, frame.c, n7);
        }
        if (n2 > 0) {
            for (n7 = 0; n7 < n8; ++n7) {
                n5 = this.c[n7];
                bl2 |= Frame.a(classWriter, n5, frame.c, n7);
            }
            if (frame.d == null) {
                frame.d = new int[1];
                bl2 = true;
            }
            return bl2 |= Frame.a(classWriter, n2, frame.d, 0);
        }
        int n10 = this.d.length + this.b.f;
        if (frame.d == null) {
            frame.d = new int[n10 + this.g];
            bl2 = true;
        }
        for (n7 = 0; n7 < n10; ++n7) {
            n5 = this.d[n7];
            if (this.i != null) {
                n5 = this.a(classWriter, n5);
            }
            bl2 |= Frame.a(classWriter, n5, frame.d, n7);
        }
        for (n7 = 0; n7 < this.g; ++n7) {
            n6 = this.f[n7];
            n4 = n6 & 0xF0000000;
            n3 = n6 & 0xF000000;
            if (n3 == 0x1000000) {
                n5 = n6;
            } else {
                n5 = n3 == 0x2000000 ? n4 + this.c[n6 & 0x7FFFFF] : n4 + this.d[n9 - (n6 & 0x7FFFFF)];
                if ((n6 & 0x800000) != 0 && (n5 == 0x1000004 || n5 == 0x1000003)) {
                    n5 = 0x1000000;
                }
            }
            if (this.i != null) {
                n5 = this.a(classWriter, n5);
            }
            bl2 |= Frame.a(classWriter, n5, frame.d, n10 + n7);
        }
        return bl2;
    }

    private static boolean a(ClassWriter classWriter, int n2, int[] nArray, int n3) {
        int n4;
        int n5 = nArray[n3];
        if (n5 == n2) {
            return false;
        }
        if ((n2 & 0xFFFFFFF) == 0x1000005) {
            if (n5 == 0x1000005) {
                return false;
            }
            n2 = 0x1000005;
        }
        if (n5 == 0) {
            nArray[n3] = n2;
            return true;
        }
        if ((n5 & 0xFF00000) == 0x1700000 || (n5 & 0xF0000000) != 0) {
            if (n2 == 0x1000005) {
                return false;
            }
            n4 = (n2 & 0xFFF00000) == (n5 & 0xFFF00000) ? ((n5 & 0xFF00000) == 0x1700000 ? n2 & 0xF0000000 | 0x1700000 | classWriter.a(n2 & 0xFFFFF, n5 & 0xFFFFF) : 0x1700000 | classWriter.c("java/lang/Object")) : ((n2 & 0xFF00000) == 0x1700000 || (n2 & 0xF0000000) != 0 ? 0x1700000 | classWriter.c("java/lang/Object") : 0x1000000);
        } else {
            n4 = n5 == 0x1000005 ? ((n2 & 0xFF00000) == 0x1700000 || (n2 & 0xF0000000) != 0 ? n2 : 0x1000000) : 0x1000000;
        }
        if (n5 != n4) {
            nArray[n3] = n4;
            return true;
        }
        return false;
    }

    static {
        int[] nArray = new int[202];
        String string = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            nArray[i2] = string.charAt(i2) - 69;
        }
        a = nArray;
    }
}

