/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import java.io.IOException;
import java.io.InputStream;
import org.bridj.relocated.org.objectweb.asm.AnnotationVisitor;
import org.bridj.relocated.org.objectweb.asm.Attribute;
import org.bridj.relocated.org.objectweb.asm.ByteVector;
import org.bridj.relocated.org.objectweb.asm.ClassVisitor;
import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.Handle;
import org.bridj.relocated.org.objectweb.asm.Item;
import org.bridj.relocated.org.objectweb.asm.Label;
import org.bridj.relocated.org.objectweb.asm.MethodVisitor;
import org.bridj.relocated.org.objectweb.asm.MethodWriter;
import org.bridj.relocated.org.objectweb.asm.Opcodes;
import org.bridj.relocated.org.objectweb.asm.Type;

public class ClassReader {
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    public final byte[] b;
    private final int[] a;
    private final String[] c;
    private final int d;
    public final int header;

    public ClassReader(byte[] byArray) {
        this(byArray, 0, byArray.length);
    }

    public ClassReader(byte[] byArray, int n2, int n3) {
        this.b = byArray;
        if (this.readShort(6) > 51) {
            throw new IllegalArgumentException();
        }
        this.a = new int[this.readUnsignedShort(n2 + 8)];
        int n4 = this.a.length;
        this.c = new String[n4];
        int n5 = 0;
        int n6 = n2 + 10;
        for (int i2 = 1; i2 < n4; ++i2) {
            int n7;
            this.a[i2] = n6 + 1;
            switch (byArray[n6]) {
                case 3: 
                case 4: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 18: {
                    n7 = 5;
                    break;
                }
                case 5: 
                case 6: {
                    n7 = 9;
                    ++i2;
                    break;
                }
                case 1: {
                    n7 = 3 + this.readUnsignedShort(n6 + 1);
                    if (n7 <= n5) break;
                    n5 = n7;
                    break;
                }
                case 15: {
                    n7 = 4;
                    break;
                }
                default: {
                    n7 = 3;
                }
            }
            n6 += n7;
        }
        this.d = n5;
        this.header = n6;
    }

    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }

    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.d]);
    }

    public String getSuperName() {
        int n2 = this.a[this.readUnsignedShort(this.header + 4)];
        return n2 == 0 ? null : this.readUTF8(n2, new char[this.d]);
    }

    public String[] getInterfaces() {
        int n2 = this.header + 6;
        int n3 = this.readUnsignedShort(n2);
        String[] stringArray = new String[n3];
        if (n3 > 0) {
            char[] cArray = new char[this.d];
            for (int i2 = 0; i2 < n3; ++i2) {
                stringArray[i2] = this.readClass(n2 += 2, cArray);
            }
        }
        return stringArray;
    }

    void a(ClassWriter classWriter) {
        int n2;
        char[] cArray = new char[this.d];
        int n3 = this.a.length;
        Item[] itemArray = new Item[n3];
        for (n2 = 1; n2 < n3; ++n2) {
            int n4;
            int n5 = this.a[n2];
            byte by = this.b[n5 - 1];
            Item item = new Item(n2);
            switch (by) {
                case 9: 
                case 10: 
                case 11: {
                    int n6 = this.a[this.readUnsignedShort(n5 + 2)];
                    item.a(by, this.readClass(n5, cArray), this.readUTF8(n6, cArray), this.readUTF8(n6 + 2, cArray));
                    break;
                }
                case 3: {
                    item.a(this.readInt(n5));
                    break;
                }
                case 4: {
                    item.a(Float.intBitsToFloat(this.readInt(n5)));
                    break;
                }
                case 12: {
                    item.a(by, this.readUTF8(n5, cArray), this.readUTF8(n5 + 2, cArray), null);
                    break;
                }
                case 5: {
                    item.a(this.readLong(n5));
                    ++n2;
                    break;
                }
                case 6: {
                    item.a(Double.longBitsToDouble(this.readLong(n5)));
                    ++n2;
                    break;
                }
                case 1: {
                    String string = this.c[n2];
                    if (string == null) {
                        n5 = this.a[n2];
                        string = this.c[n2] = this.a(n5 + 2, this.readUnsignedShort(n5), cArray);
                    }
                    item.a(by, string, null, null);
                    break;
                }
                case 15: {
                    n4 = this.a[this.readUnsignedShort(n5 + 1)];
                    int n6 = this.a[this.readUnsignedShort(n4 + 2)];
                    item.a(20 + this.readByte(n5), this.readClass(n4, cArray), this.readUTF8(n6, cArray), this.readUTF8(n6 + 2, cArray));
                    break;
                }
                case 18: {
                    if (classWriter.A == null) {
                        this.a(classWriter, itemArray, cArray);
                    }
                    int n6 = this.a[this.readUnsignedShort(n5 + 2)];
                    item.a(this.readUTF8(n6, cArray), this.readUTF8(n6 + 2, cArray), this.readUnsignedShort(n5));
                    break;
                }
                default: {
                    item.a(by, this.readUTF8(n5, cArray), null, null);
                }
            }
            n4 = item.j % itemArray.length;
            item.k = itemArray[n4];
            itemArray[n4] = item;
        }
        n2 = this.a[1] - 1;
        classWriter.d.putByteArray(this.b, n2, this.header - n2);
        classWriter.e = itemArray;
        classWriter.f = (int)(0.75 * (double)n3);
        classWriter.c = n3;
    }

    private void a(ClassWriter classWriter, Item[] itemArray, char[] cArray) {
        int n2;
        int n3 = this.header;
        n3 += 8 + (this.readUnsignedShort(n3 + 6) << 1);
        int n4 = this.readUnsignedShort(n3);
        n3 += 2;
        while (n4 > 0) {
            n2 = this.readUnsignedShort(n3 + 6);
            n3 += 8;
            while (n2 > 0) {
                n3 += 6 + this.readInt(n3 + 2);
                --n2;
            }
            --n4;
        }
        n4 = this.readUnsignedShort(n3);
        n3 += 2;
        while (n4 > 0) {
            n2 = this.readUnsignedShort(n3 + 6);
            n3 += 8;
            while (n2 > 0) {
                n3 += 6 + this.readInt(n3 + 2);
                --n2;
            }
            --n4;
        }
        n4 = this.readUnsignedShort(n3);
        n3 += 2;
        while (n4 > 0) {
            String string = this.readUTF8(n3, cArray);
            int n5 = this.readInt(n3 + 2);
            if ("BootstrapMethods".equals(string)) {
                int n6 = this.readUnsignedShort(n3 + 6);
                int n7 = n3 + 8;
                for (n2 = 0; n2 < n6; ++n2) {
                    int n8 = this.readConst(this.readUnsignedShort(n7), cArray).hashCode();
                    int n9 = n7 + 4;
                    for (int i2 = this.readUnsignedShort(n7 + 2); i2 > 0; --i2) {
                        n8 ^= this.readConst(this.readUnsignedShort(n9), cArray).hashCode();
                        n9 += 2;
                    }
                    Item item = new Item(n2);
                    item.a(n7 - n3 - 8, n8 & Integer.MAX_VALUE);
                    int n10 = item.j % itemArray.length;
                    item.k = itemArray[n10];
                    itemArray[n10] = item;
                    n7 = n9;
                }
                classWriter.z = n6;
                ByteVector byteVector = new ByteVector(n5 + 62);
                byteVector.putByteArray(this.b, n3 + 8, n5 - 2);
                classWriter.A = byteVector;
                return;
            }
            n3 += 6 + n5;
            --n4;
        }
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(ClassReader.a(inputStream, false));
    }

    public ClassReader(String string) throws IOException {
        this(ClassReader.a(ClassLoader.getSystemResourceAsStream(string.replace('.', '/') + ".class"), true));
    }

    private static byte[] a(InputStream inputStream, boolean bl2) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        try {
            byte[] byArray = new byte[inputStream.available()];
            int n2 = 0;
            while (true) {
                byte[] byArray2;
                int n3;
                if ((n3 = inputStream.read(byArray, n2, byArray.length - n2)) == -1) {
                    byte[] byArray3;
                    if (n2 < byArray.length) {
                        byArray3 = new byte[n2];
                        System.arraycopy(byArray, 0, byArray3, 0, n2);
                        byArray = byArray3;
                    }
                    byArray3 = byArray;
                    return byArray3;
                }
                if ((n2 += n3) != byArray.length) continue;
                int n4 = inputStream.read();
                if (n4 < 0) {
                    byArray2 = byArray;
                    return byArray2;
                }
                byArray2 = new byte[byArray.length + 1000];
                System.arraycopy(byArray, 0, byArray2, 0, n2);
                byArray2[n2++] = (byte)n4;
                byArray = byArray2;
            }
        }
        finally {
            if (bl2) {
                inputStream.close();
            }
        }
    }

    public void accept(ClassVisitor classVisitor, int n2) {
        this.accept(classVisitor, new Attribute[0], n2);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public void accept(ClassVisitor var1_1, Attribute[] var2_2, int var3_3) {
        var4_4 = this.b;
        var5_5 = new char[this.d];
        var6_6 = 0;
        var7_7 = 0;
        var8_8 = null;
        var9_9 = this.header;
        var10_10 = this.readUnsignedShort(var9_9);
        var11_11 = this.readClass(var9_9 + 2, var5_5);
        var12_12 = this.a[this.readUnsignedShort(var9_9 + 4)];
        var13_13 = var12_12 == 0 ? null : this.readUTF8(var12_12, var5_5);
        var14_14 = new String[this.readUnsignedShort(var9_9 + 6)];
        var15_15 = 0;
        var9_9 += 8;
        for (var16_16 = 0; var16_16 < var14_14.length; ++var16_16) {
            var14_14[var16_16] = this.readClass(var9_9, var5_5);
            var9_9 += 2;
        }
        var17_17 = (var3_3 & 1) != 0;
        var18_18 = (var3_3 & 2) != 0;
        var19_19 = (var3_3 & 8) != 0;
        var12_12 = var9_9;
        var16_16 = this.readUnsignedShort(var12_12);
        var12_12 += 2;
        while (var16_16 > 0) {
            var20_20 = this.readUnsignedShort(var12_12 + 6);
            var12_12 += 8;
            while (var20_20 > 0) {
                var12_12 += 6 + this.readInt(var12_12 + 2);
                --var20_20;
            }
            --var16_16;
        }
        var16_16 = this.readUnsignedShort(var12_12);
        var12_12 += 2;
        while (var16_16 > 0) {
            var20_20 = this.readUnsignedShort(var12_12 + 6);
            var12_12 += 8;
            while (var20_20 > 0) {
                var12_12 += 6 + this.readInt(var12_12 + 2);
                --var20_20;
            }
            --var16_16;
        }
        var21_21 = null;
        var22_22 = null;
        var23_23 = null;
        var24_24 = null;
        var25_25 = null;
        var26_26 = null;
        var27_27 = null;
        var16_16 = this.readUnsignedShort(var12_12);
        var12_12 += 2;
        while (var16_16 > 0) {
            var28_28 = this.readUTF8(var12_12, var5_5);
            if ("SourceFile".equals(var28_28)) {
                var22_22 = this.readUTF8(var12_12 + 6, var5_5);
            } else if ("InnerClasses".equals(var28_28)) {
                var15_15 = var12_12 + 6;
            } else if ("EnclosingMethod".equals(var28_28)) {
                var24_24 = this.readClass(var12_12 + 6, var5_5);
                var29_29 = this.readUnsignedShort(var12_12 + 8);
                if (var29_29 != 0) {
                    var25_25 = this.readUTF8(this.a[var29_29], var5_5);
                    var26_26 = this.readUTF8(this.a[var29_29] + 2, var5_5);
                }
            } else if ("Signature".equals(var28_28)) {
                var21_21 = this.readUTF8(var12_12 + 6, var5_5);
            } else if ("RuntimeVisibleAnnotations".equals(var28_28)) {
                var6_6 = var12_12 + 6;
            } else if ("Deprecated".equals(var28_28)) {
                var10_10 |= 131072;
            } else if ("Synthetic".equals(var28_28)) {
                var10_10 |= 266240;
            } else if ("SourceDebugExtension".equals(var28_28)) {
                var29_29 = this.readInt(var12_12 + 2);
                var23_23 = this.a(var12_12 + 6, var29_29, new char[var29_29]);
            } else if ("RuntimeInvisibleAnnotations".equals(var28_28)) {
                var7_7 = var12_12 + 6;
            } else if ("BootstrapMethods".equals(var28_28)) {
                var29_29 = this.readUnsignedShort(var12_12 + 6);
                var27_27 = new int[var29_29];
                var30_30 = var12_12 + 8;
                for (var20_20 = 0; var20_20 < var29_29; ++var20_20) {
                    var27_27[var20_20] = var30_30;
                    var30_30 += 2 + this.readUnsignedShort(var30_30 + 2) << 1;
                }
            } else {
                var31_34 = this.a(var2_2, var28_28, var12_12 + 6, this.readInt(var12_12 + 2), var5_5, -1, null);
                if (var31_34 != null) {
                    var31_34.a = var8_8;
                    var8_8 = var31_34;
                }
            }
            var12_12 += 6 + this.readInt(var12_12 + 2);
            --var16_16;
        }
        var1_1.visit(this.readInt(4), var10_10, var11_11, var21_21, var13_13, var14_14);
        if (!(var18_18 || var22_22 == null && var23_23 == null)) {
            var1_1.visitSource(var22_22, var23_23);
        }
        if (var24_24 != null) {
            var1_1.visitOuterClass(var24_24, var25_25, var26_26);
        }
        for (var16_16 = 1; var16_16 >= 0; --var16_16) {
            v0 = var12_12 = var16_16 == 0 ? var7_7 : var6_6;
            if (var12_12 == 0) continue;
            var20_20 = this.readUnsignedShort(var12_12);
            var12_12 += 2;
            while (var20_20 > 0) {
                var12_12 = this.a(var12_12 + 2, var5_5, true, var1_1.visitAnnotation(this.readUTF8(var12_12, var5_5), var16_16 != 0));
                --var20_20;
            }
        }
        while (var8_8 != null) {
            var31_34 = var8_8.a;
            var8_8.a = null;
            var1_1.visitAttribute(var8_8);
            var8_8 = var31_34;
        }
        if (var15_15 != 0) {
            var16_16 = this.readUnsignedShort(var15_15);
            var15_15 += 2;
            while (var16_16 > 0) {
                var1_1.visitInnerClass(this.readUnsignedShort(var15_15) == 0 ? null : this.readClass(var15_15, var5_5), this.readUnsignedShort(var15_15 + 2) == 0 ? null : this.readClass(var15_15 + 2, var5_5), this.readUnsignedShort(var15_15 + 4) == 0 ? null : this.readUTF8(var15_15 + 4, var5_5), this.readUnsignedShort(var15_15 + 6));
                var15_15 += 8;
                --var16_16;
            }
        }
        var16_16 = this.readUnsignedShort(var9_9);
        var9_9 += 2;
        while (var16_16 > 0) {
            var10_10 = this.readUnsignedShort(var9_9);
            var11_11 = this.readUTF8(var9_9 + 2, var5_5);
            var32_35 = this.readUTF8(var9_9 + 4, var5_5);
            var29_29 = 0;
            var21_21 = null;
            var6_6 = 0;
            var7_7 = 0;
            var8_8 = null;
            var20_20 = this.readUnsignedShort(var9_9 + 6);
            var9_9 += 8;
            while (var20_20 > 0) {
                var28_28 = this.readUTF8(var9_9, var5_5);
                if ("ConstantValue".equals(var28_28)) {
                    var29_29 = this.readUnsignedShort(var9_9 + 6);
                } else if ("Signature".equals(var28_28)) {
                    var21_21 = this.readUTF8(var9_9 + 6, var5_5);
                } else if ("Deprecated".equals(var28_28)) {
                    var10_10 |= 131072;
                } else if ("Synthetic".equals(var28_28)) {
                    var10_10 |= 266240;
                } else if ("RuntimeVisibleAnnotations".equals(var28_28)) {
                    var6_6 = var9_9 + 6;
                } else if ("RuntimeInvisibleAnnotations".equals(var28_28)) {
                    var7_7 = var9_9 + 6;
                } else {
                    var31_34 = this.a(var2_2, var28_28, var9_9 + 6, this.readInt(var9_9 + 2), var5_5, -1, null);
                    if (var31_34 != null) {
                        var31_34.a = var8_8;
                        var8_8 = var31_34;
                    }
                }
                var9_9 += 6 + this.readInt(var9_9 + 2);
                --var20_20;
            }
            var30_32 = var1_1.visitField(var10_10, var11_11, var32_35, var21_21, var29_29 == 0 ? null : this.readConst(var29_29, var5_5));
            if (var30_32 != null) {
                for (var20_20 = 1; var20_20 >= 0; --var20_20) {
                    v1 = var12_12 = var20_20 == 0 ? var7_7 : var6_6;
                    if (var12_12 == 0) continue;
                    var33_36 = this.readUnsignedShort(var12_12);
                    var12_12 += 2;
                    while (var33_36 > 0) {
                        var12_12 = this.a(var12_12 + 2, var5_5, true, var30_32.visitAnnotation(this.readUTF8(var12_12, var5_5), var20_20 != 0));
                        --var33_36;
                    }
                }
                while (var8_8 != null) {
                    var31_34 = var8_8.a;
                    var8_8.a = null;
                    var30_32.visitAttribute(var8_8);
                    var8_8 = var31_34;
                }
                var30_32.visitEnd();
            }
            --var16_16;
        }
        var16_16 = this.readUnsignedShort(var9_9);
        var9_9 += 2;
        while (var16_16 > 0) {
            var29_29 = var9_9 + 6;
            var10_10 = this.readUnsignedShort(var9_9);
            var11_11 = this.readUTF8(var9_9 + 2, var5_5);
            var32_35 = this.readUTF8(var9_9 + 4, var5_5);
            var21_21 = null;
            var6_6 = 0;
            var7_7 = 0;
            var30_33 = 0;
            var34_37 = 0;
            var35_38 = 0;
            var8_8 = null;
            var12_12 = 0;
            var15_15 = 0;
            var20_20 = this.readUnsignedShort(var9_9 + 6);
            var9_9 += 8;
            while (var20_20 > 0) {
                var28_28 = this.readUTF8(var9_9, var5_5);
                var36_40 = this.readInt(var9_9 + 2);
                var9_9 += 6;
                if ("Code".equals(var28_28)) {
                    if (!var17_17) {
                        var12_12 = var9_9;
                    }
                } else if ("Exceptions".equals(var28_28)) {
                    var15_15 = var9_9;
                } else if ("Signature".equals(var28_28)) {
                    var21_21 = this.readUTF8(var9_9, var5_5);
                } else if ("Deprecated".equals(var28_28)) {
                    var10_10 |= 131072;
                } else if ("RuntimeVisibleAnnotations".equals(var28_28)) {
                    var6_6 = var9_9;
                } else if ("AnnotationDefault".equals(var28_28)) {
                    var30_33 = var9_9;
                } else if ("Synthetic".equals(var28_28)) {
                    var10_10 |= 266240;
                } else if ("RuntimeInvisibleAnnotations".equals(var28_28)) {
                    var7_7 = var9_9;
                } else if ("RuntimeVisibleParameterAnnotations".equals(var28_28)) {
                    var34_37 = var9_9;
                } else if ("RuntimeInvisibleParameterAnnotations".equals(var28_28)) {
                    var35_38 = var9_9;
                } else {
                    var31_34 = this.a(var2_2, var28_28, var9_9, var36_40, var5_5, -1, null);
                    if (var31_34 != null) {
                        var31_34.a = var8_8;
                        var8_8 = var31_34;
                    }
                }
                var9_9 += var36_40;
                --var20_20;
            }
            if (var15_15 == 0) {
                var36_39 = null;
            } else {
                var36_39 = new String[this.readUnsignedShort(var15_15)];
                var15_15 += 2;
                for (var20_20 = 0; var20_20 < var36_39.length; ++var20_20) {
                    var36_39[var20_20] = this.readClass(var15_15, var5_5);
                    var15_15 += 2;
                }
            }
            var37_41 = var1_1.visitMethod(var10_10, var11_11, var32_35, var21_21, var36_39);
            if (var37_41 == null) ** GOTO lbl307
            if (!(var37_41 instanceof MethodWriter)) ** GOTO lbl-1000
            var38_42 = (MethodWriter)var37_41;
            if (var38_42.b.M != this || var21_21 != var38_42.g) ** GOTO lbl-1000
            var39_44 = 0;
            if (var36_39 == null) {
                var39_44 = var38_42.j == 0 ? 1 : 0;
            } else if (var36_39.length == var38_42.j) {
                var39_44 = 1;
                for (var20_20 = var36_39.length - 1; var20_20 >= 0; --var20_20) {
                    if (var38_42.k[var20_20] == this.readUnsignedShort(var15_15 -= 2)) continue;
                    var39_44 = 0;
                    break;
                }
            }
            if (var39_44 != 0) {
                var38_42.h = var29_29;
                var38_42.i = var9_9 - var29_29;
            } else lbl-1000:
            // 3 sources

            {
                if (var30_33 != 0) {
                    var38_42 = var37_41.visitAnnotationDefault();
                    this.a(var30_33, var5_5, null, (AnnotationVisitor)var38_42);
                    if (var38_42 != null) {
                        var38_42.visitEnd();
                    }
                }
                for (var20_20 = 1; var20_20 >= 0; --var20_20) {
                    v2 = var15_15 = var20_20 == 0 ? var7_7 : var6_6;
                    if (var15_15 == 0) continue;
                    var33_36 = this.readUnsignedShort(var15_15);
                    var15_15 += 2;
                    while (var33_36 > 0) {
                        var15_15 = this.a(var15_15 + 2, var5_5, true, var37_41.visitAnnotation(this.readUTF8(var15_15, var5_5), var20_20 != 0));
                        --var33_36;
                    }
                }
                if (var34_37 != 0) {
                    this.a(var34_37, var32_35, var5_5, true, var37_41);
                }
                if (var35_38 != 0) {
                    this.a(var35_38, var32_35, var5_5, false, var37_41);
                }
                while (var8_8 != null) {
                    var31_34 = var8_8.a;
                    var8_8.a = null;
                    var37_41.visitAttribute(var8_8);
                    var8_8 = var31_34;
                }
lbl307:
                // 2 sources

                if (var37_41 != null && var12_12 != 0) {
                    var38_43 = this.readUnsignedShort(var12_12);
                    var39_44 = this.readUnsignedShort(var12_12 + 2);
                    var40_45 = this.readInt(var12_12 + 4);
                    var41_46 = var12_12 += 8;
                    var42_47 = var12_12 + var40_45;
                    var37_41.visitCode();
                    var43_48 = new Label[var40_45 + 2];
                    this.readLabel(var40_45 + 1, var43_48);
                    block60: while (var12_12 < var42_47) {
                        var15_15 = var12_12 - var41_46;
                        var44_49 = var4_4[var12_12] & 255;
                        switch (ClassWriter.a[var44_49]) {
                            case 0: 
                            case 4: {
                                ++var12_12;
                                continue block60;
                            }
                            case 9: {
                                this.readLabel(var15_15 + this.readShort(var12_12 + 1), var43_48);
                                var12_12 += 3;
                                continue block60;
                            }
                            case 10: {
                                this.readLabel(var15_15 + this.readInt(var12_12 + 1), var43_48);
                                var12_12 += 5;
                                continue block60;
                            }
                            case 17: {
                                var44_49 = var4_4[var12_12 + 1] & 255;
                                if (var44_49 == 132) {
                                    var12_12 += 6;
                                    continue block60;
                                }
                                var12_12 += 4;
                                continue block60;
                            }
                            case 14: {
                                var12_12 = var12_12 + 4 - (var15_15 & 3);
                                this.readLabel(var15_15 + this.readInt(var12_12), var43_48);
                                var20_20 = this.readInt(var12_12 + 8) - this.readInt(var12_12 + 4) + 1;
                                var12_12 += 12;
                                while (var20_20 > 0) {
                                    this.readLabel(var15_15 + this.readInt(var12_12), var43_48);
                                    var12_12 += 4;
                                    --var20_20;
                                }
                                continue block60;
                            }
                            case 15: {
                                var12_12 = var12_12 + 4 - (var15_15 & 3);
                                this.readLabel(var15_15 + this.readInt(var12_12), var43_48);
                                var20_20 = this.readInt(var12_12 + 4);
                                var12_12 += 8;
                                while (var20_20 > 0) {
                                    this.readLabel(var15_15 + this.readInt(var12_12 + 4), var43_48);
                                    var12_12 += 8;
                                    --var20_20;
                                }
                                continue block60;
                            }
                            case 1: 
                            case 3: 
                            case 11: {
                                var12_12 += 2;
                                continue block60;
                            }
                            case 2: 
                            case 5: 
                            case 6: 
                            case 12: 
                            case 13: {
                                var12_12 += 3;
                                continue block60;
                            }
                            case 7: 
                            case 8: {
                                var12_12 += 5;
                                continue block60;
                            }
                        }
                        var12_12 += 4;
                    }
                    var20_20 = this.readUnsignedShort(var12_12);
                    var12_12 += 2;
                    while (var20_20 > 0) {
                        var44_51 = this.readLabel(this.readUnsignedShort(var12_12), var43_48);
                        var45_53 = this.readLabel(this.readUnsignedShort(var12_12 + 2), var43_48);
                        var46_55 = this.readLabel(this.readUnsignedShort(var12_12 + 4), var43_48);
                        var47_56 = this.readUnsignedShort(var12_12 + 6);
                        if (var47_56 == 0) {
                            var37_41.visitTryCatchBlock(var44_51, var45_53, var46_55, null);
                        } else {
                            var37_41.visitTryCatchBlock(var44_51, var45_53, var46_55, this.readUTF8(this.a[var47_56], var5_5));
                        }
                        var12_12 += 8;
                        --var20_20;
                    }
                    var44_49 = 0;
                    var45_52 = 0;
                    var46_54 = 0;
                    var47_56 = 0;
                    var48_57 = 0;
                    var49_58 = 0;
                    var50_59 = 0;
                    var51_60 = 0;
                    var52_61 = 0;
                    var53_62 = 0;
                    var54_63 = null;
                    var55_64 = null;
                    var56_65 = true;
                    var8_8 = null;
                    var20_20 = this.readUnsignedShort(var12_12);
                    var12_12 += 2;
                    while (var20_20 > 0) {
                        var28_28 = this.readUTF8(var12_12, var5_5);
                        if ("LocalVariableTable".equals(var28_28)) {
                            if (!var18_18) {
                                var44_49 = var12_12 + 6;
                                var15_15 = var12_12 + 8;
                                for (var33_36 = this.readUnsignedShort(var12_12 + 6); var33_36 > 0; --var33_36) {
                                    var57_66 = this.readUnsignedShort(var15_15);
                                    if (var43_48[var57_66] == null) {
                                        this.readLabel((int)var57_66, (Label[])var43_48).a |= 1;
                                    }
                                    if (var43_48[var57_66 += this.readUnsignedShort(var15_15 + 2)] == null) {
                                        this.readLabel((int)var57_66, (Label[])var43_48).a |= 1;
                                    }
                                    var15_15 += 10;
                                }
                            }
                        } else if ("LocalVariableTypeTable".equals(var28_28)) {
                            var45_52 = var12_12 + 6;
                        } else if ("LineNumberTable".equals(var28_28)) {
                            if (!var18_18) {
                                var15_15 = var12_12 + 8;
                                for (var33_36 = this.readUnsignedShort(var12_12 + 6); var33_36 > 0; --var33_36) {
                                    var57_66 = this.readUnsignedShort(var15_15);
                                    if (var43_48[var57_66] == null) {
                                        this.readLabel((int)var57_66, (Label[])var43_48).a |= 1;
                                    }
                                    var43_48[var57_66].b = this.readUnsignedShort(var15_15 + 2);
                                    var15_15 += 4;
                                }
                            }
                        } else if ("StackMapTable".equals(var28_28)) {
                            if ((var3_3 & 4) == 0) {
                                var46_54 = var12_12 + 8;
                                var47_56 = this.readInt(var12_12 + 2);
                                var48_57 = this.readUnsignedShort(var12_12 + 6);
                            }
                        } else if ("StackMap".equals(var28_28)) {
                            if ((var3_3 & 4) == 0) {
                                var46_54 = var12_12 + 8;
                                var47_56 = this.readInt(var12_12 + 2);
                                var48_57 = this.readUnsignedShort(var12_12 + 6);
                                var56_65 = false;
                            }
                        } else {
                            for (var33_36 = 0; var33_36 < var2_2.length; ++var33_36) {
                                if (!var2_2[var33_36].type.equals(var28_28) || (var31_34 = var2_2[var33_36].read(this, var12_12 + 6, this.readInt(var12_12 + 2), var5_5, var41_46 - 8, var43_48)) == null) continue;
                                var31_34.a = var8_8;
                                var8_8 = var31_34;
                            }
                        }
                        var12_12 += 6 + this.readInt(var12_12 + 2);
                        --var20_20;
                    }
                    if (var46_54 != 0) {
                        var54_63 = new Object[var39_44];
                        var55_64 = new Object[var38_43];
                        if (var19_19) {
                            var58_68 = 0;
                            if ((var10_10 & 8) == 0) {
                                var54_63[var58_68++] = "<init>".equals(var11_11) != false ? Opcodes.UNINITIALIZED_THIS : this.readClass(this.header + 2, var5_5);
                            }
                            var20_20 = 1;
                            block68: while (true) {
                                var33_36 = var20_20;
                                switch (var32_35.charAt(var20_20++)) {
                                    case 'B': 
                                    case 'C': 
                                    case 'I': 
                                    case 'S': 
                                    case 'Z': {
                                        var54_63[var58_68++] = Opcodes.INTEGER;
                                        continue block68;
                                    }
                                    case 'F': {
                                        var54_63[var58_68++] = Opcodes.FLOAT;
                                        continue block68;
                                    }
                                    case 'J': {
                                        var54_63[var58_68++] = Opcodes.LONG;
                                        continue block68;
                                    }
                                    case 'D': {
                                        var54_63[var58_68++] = Opcodes.DOUBLE;
                                        continue block68;
                                    }
                                    case '[': {
                                        while (var32_35.charAt(var20_20) == '[') {
                                            ++var20_20;
                                        }
                                        if (var32_35.charAt(var20_20) == 'L') {
                                            ++var20_20;
                                            while (var32_35.charAt(var20_20) != ';') {
                                                ++var20_20;
                                            }
                                        }
                                        var54_63[var58_68++] = var32_35.substring(var33_36, ++var20_20);
                                        continue block68;
                                    }
                                    case 'L': {
                                        while (var32_35.charAt(var20_20) != ';') {
                                            ++var20_20;
                                        }
                                        var54_63[var58_68++] = var32_35.substring(var33_36 + 1, var20_20++);
                                        continue block68;
                                    }
                                }
                                break;
                            }
                            var51_60 = var58_68;
                        }
                        var50_59 = -1;
                        for (var20_20 = var46_54; var20_20 < var46_54 + var47_56 - 2; ++var20_20) {
                            if (var4_4[var20_20] != 8 || (var33_36 = this.readUnsignedShort(var20_20 + 1)) < 0 || var33_36 >= var40_45 || (var4_4[var41_46 + var33_36] & 255) != 187) continue;
                            this.readLabel(var33_36, var43_48);
                        }
                    }
                    var12_12 = var41_46;
                    block73: while (var12_12 < var42_47) {
                        var15_15 = var12_12 - var41_46;
                        var58_69 = var43_48[var15_15];
                        if (var58_69 != null) {
                            var37_41.visitLabel(var58_69);
                            if (!var18_18 && var58_69.b > 0) {
                                var37_41.visitLineNumber(var58_69.b, var58_69);
                            }
                        }
                        while (var54_63 != null && (var50_59 == var15_15 || var50_59 == -1)) {
                            if (!var56_65 || var19_19) {
                                var37_41.visitFrame(-1, var51_60, var54_63, var53_62, var55_64);
                            } else if (var50_59 != -1) {
                                var37_41.visitFrame(var49_58, var52_61, var54_63, var53_62, var55_64);
                            }
                            if (var48_57 > 0) {
                                if (var56_65) {
                                    var59_70 = var4_4[var46_54++] & 255;
                                } else {
                                    var59_70 = 255;
                                    var50_59 = -1;
                                }
                                var52_61 = 0;
                                if (var59_70 < 64) {
                                    var60_72 = var59_70;
                                    var49_58 = 3;
                                    var53_62 = 0;
                                } else if (var59_70 < 128) {
                                    var60_72 = var59_70 - 64;
                                    var46_54 = this.a(var55_64, 0, var46_54, var5_5, var43_48);
                                    var49_58 = 4;
                                    var53_62 = 1;
                                } else {
                                    var60_72 = this.readUnsignedShort(var46_54);
                                    var46_54 += 2;
                                    if (var59_70 == 247) {
                                        var46_54 = this.a(var55_64, 0, var46_54, var5_5, var43_48);
                                        var49_58 = 4;
                                        var53_62 = 1;
                                    } else if (var59_70 >= 248 && var59_70 < 251) {
                                        var49_58 = 2;
                                        var52_61 = 251 - var59_70;
                                        var51_60 -= var52_61;
                                        var53_62 = 0;
                                    } else if (var59_70 == 251) {
                                        var49_58 = 3;
                                        var53_62 = 0;
                                    } else if (var59_70 < 255) {
                                        var20_20 = var19_19 != false ? var51_60 : 0;
                                        for (var33_36 = var59_70 - 251; var33_36 > 0; --var33_36) {
                                            var46_54 = this.a(var54_63, var20_20++, var46_54, var5_5, var43_48);
                                        }
                                        var49_58 = 1;
                                        var52_61 = var59_70 - 251;
                                        var51_60 += var52_61;
                                        var53_62 = 0;
                                    } else {
                                        var49_58 = 0;
                                        var52_61 = var51_60 = this.readUnsignedShort(var46_54);
                                        var46_54 += 2;
                                        var20_20 = 0;
                                        for (var61_73 = var51_60; var61_73 > 0; --var61_73) {
                                            var46_54 = this.a(var54_63, var20_20++, var46_54, var5_5, var43_48);
                                        }
                                        var61_73 = var53_62 = this.readUnsignedShort(var46_54);
                                        var46_54 += 2;
                                        var20_20 = 0;
                                        while (var61_73 > 0) {
                                            var46_54 = this.a(var55_64, var20_20++, var46_54, var5_5, var43_48);
                                            --var61_73;
                                        }
                                    }
                                }
                                this.readLabel(var50_59 += var60_72 + 1, var43_48);
                                --var48_57;
                                continue;
                            }
                            var54_63 = null;
                        }
                        var59_70 = var4_4[var12_12] & 255;
                        switch (ClassWriter.a[var59_70]) {
                            case 0: {
                                var37_41.visitInsn(var59_70);
                                ++var12_12;
                                continue block73;
                            }
                            case 4: {
                                if (var59_70 > 54) {
                                    var37_41.visitVarInsn(54 + ((var59_70 -= 59) >> 2), var59_70 & 3);
                                } else {
                                    var37_41.visitVarInsn(21 + ((var59_70 -= 26) >> 2), var59_70 & 3);
                                }
                                ++var12_12;
                                continue block73;
                            }
                            case 9: {
                                var37_41.visitJumpInsn(var59_70, var43_48[var15_15 + this.readShort(var12_12 + 1)]);
                                var12_12 += 3;
                                continue block73;
                            }
                            case 10: {
                                var37_41.visitJumpInsn(var59_70 - 33, var43_48[var15_15 + this.readInt(var12_12 + 1)]);
                                var12_12 += 5;
                                continue block73;
                            }
                            case 17: {
                                var59_70 = var4_4[var12_12 + 1] & 255;
                                if (var59_70 == 132) {
                                    var37_41.visitIincInsn(this.readUnsignedShort(var12_12 + 2), this.readShort(var12_12 + 4));
                                    var12_12 += 6;
                                    continue block73;
                                }
                                var37_41.visitVarInsn(var59_70, this.readUnsignedShort(var12_12 + 2));
                                var12_12 += 4;
                                continue block73;
                            }
                            case 14: {
                                var12_12 = var12_12 + 4 - (var15_15 & 3);
                                var57_66 = var15_15 + this.readInt(var12_12);
                                var60_72 = this.readInt(var12_12 + 4);
                                var61_73 = this.readInt(var12_12 + 8);
                                var12_12 += 12;
                                var62_74 = new Label[var61_73 - var60_72 + 1];
                                for (var20_20 = 0; var20_20 < var62_74.length; ++var20_20) {
                                    var62_74[var20_20] = var43_48[var15_15 + this.readInt(var12_12)];
                                    var12_12 += 4;
                                }
                                var37_41.visitTableSwitchInsn(var60_72, var61_73, var43_48[var57_66], var62_74);
                                continue block73;
                            }
                            case 15: {
                                var12_12 = var12_12 + 4 - (var15_15 & 3);
                                var57_66 = var15_15 + this.readInt(var12_12);
                                var20_20 = this.readInt(var12_12 + 4);
                                var12_12 += 8;
                                var63_76 /* !! */  = new int[var20_20];
                                var64_77 = new Label[var20_20];
                                for (var20_20 = 0; var20_20 < var63_76 /* !! */ .length; ++var20_20) {
                                    var63_76 /* !! */ [var20_20] = this.readInt(var12_12);
                                    var64_77[var20_20] = var43_48[var15_15 + this.readInt(var12_12 + 4)];
                                    var12_12 += 8;
                                }
                                var37_41.visitLookupSwitchInsn(var43_48[var57_66], var63_76 /* !! */ , var64_77);
                                continue block73;
                            }
                            case 3: {
                                var37_41.visitVarInsn(var59_70, var4_4[var12_12 + 1] & 255);
                                var12_12 += 2;
                                continue block73;
                            }
                            case 1: {
                                var37_41.visitIntInsn(var59_70, var4_4[var12_12 + 1]);
                                var12_12 += 2;
                                continue block73;
                            }
                            case 2: {
                                var37_41.visitIntInsn(var59_70, this.readShort(var12_12 + 1));
                                var12_12 += 3;
                                continue block73;
                            }
                            case 11: {
                                var37_41.visitLdcInsn(this.readConst(var4_4[var12_12 + 1] & 255, var5_5));
                                var12_12 += 2;
                                continue block73;
                            }
                            case 12: {
                                var37_41.visitLdcInsn(this.readConst(this.readUnsignedShort(var12_12 + 1), var5_5));
                                var12_12 += 3;
                                continue block73;
                            }
                            case 6: 
                            case 7: {
                                var65_79 = this.a[this.readUnsignedShort(var12_12 + 1)];
                                var66_80 = this.readClass(var65_79, var5_5);
                                var65_79 = this.a[this.readUnsignedShort(var65_79 + 2)];
                                var67_82 = this.readUTF8(var65_79, var5_5);
                                var68_83 = this.readUTF8(var65_79 + 2, var5_5);
                                if (var59_70 < 182) {
                                    var37_41.visitFieldInsn(var59_70, var66_80, var67_82, var68_83);
                                } else {
                                    var37_41.visitMethodInsn(var59_70, var66_80, var67_82, var68_83);
                                }
                                if (var59_70 == 185) {
                                    var12_12 += 5;
                                    continue block73;
                                }
                                var12_12 += 3;
                                continue block73;
                            }
                            case 8: {
                                var65_79 = this.a[this.readUnsignedShort(var12_12 + 1)];
                                var66_81 = var27_27[this.readUnsignedShort(var65_79)];
                                var65_79 = this.a[this.readUnsignedShort(var65_79 + 2)];
                                var67_82 = this.readUTF8(var65_79, var5_5);
                                var68_83 = this.readUTF8(var65_79 + 2, var5_5);
                                var69_84 = this.readUnsignedShort(var66_81);
                                var70_85 = (Handle)this.readConst(var69_84, var5_5);
                                var71_86 = this.readUnsignedShort(var66_81 + 2);
                                var72_87 = new Object[var71_86];
                                var66_81 += 4;
                                for (var73_88 = 0; var73_88 < var71_86; ++var73_88) {
                                    var74_89 = this.readUnsignedShort(var66_81);
                                    var72_87[var73_88] = this.readConst(var74_89, var5_5);
                                    var66_81 += 2;
                                }
                                var37_41.visitInvokeDynamicInsn(var67_82, var68_83, var70_85, var72_87);
                                var12_12 += 5;
                                continue block73;
                            }
                            case 5: {
                                var37_41.visitTypeInsn(var59_70, this.readClass(var12_12 + 1, var5_5));
                                var12_12 += 3;
                                continue block73;
                            }
                            case 13: {
                                var37_41.visitIincInsn(var4_4[var12_12 + 1] & 255, var4_4[var12_12 + 2]);
                                var12_12 += 3;
                                continue block73;
                            }
                        }
                        var37_41.visitMultiANewArrayInsn(this.readClass(var12_12 + 1, var5_5), var4_4[var12_12 + 3] & 255);
                        var12_12 += 4;
                    }
                    var58_67 = var43_48[var42_47 - var41_46];
                    if (var58_67 != null) {
                        var37_41.visitLabel(var58_67);
                    }
                    if (!var18_18 && var44_49 != 0) {
                        var59_71 = null;
                        if (var45_52 != 0) {
                            var33_36 = this.readUnsignedShort(var45_52) * 3;
                            var15_15 = var45_52 + 2;
                            var59_71 = new int[var33_36];
                            while (var33_36 > 0) {
                                var59_71[--var33_36] = var15_15 + 6;
                                var59_71[--var33_36] = this.readUnsignedShort(var15_15 + 8);
                                var59_71[--var33_36] = this.readUnsignedShort(var15_15);
                                var15_15 += 10;
                            }
                        }
                        var15_15 = var44_49 + 2;
                        for (var33_36 = this.readUnsignedShort(var44_49); var33_36 > 0; --var33_36) {
                            var60_72 = this.readUnsignedShort(var15_15);
                            var61_73 = this.readUnsignedShort(var15_15 + 2);
                            var62_75 = this.readUnsignedShort(var15_15 + 8);
                            var63_76 /* !! */  = null;
                            if (var59_71 != null) {
                                for (var64_78 = 0; var64_78 < var59_71.length; var64_78 += 3) {
                                    if (var59_71[var64_78] != var60_72 || var59_71[var64_78 + 1] != var62_75) continue;
                                    var63_76 /* !! */  = (int[])this.readUTF8(var59_71[var64_78 + 2], var5_5);
                                    break;
                                }
                            }
                            var37_41.visitLocalVariable(this.readUTF8(var15_15 + 4, var5_5), this.readUTF8(var15_15 + 6, var5_5), (String)var63_76 /* !! */ , var43_48[var60_72], var43_48[var60_72 + var61_73], var62_75);
                            var15_15 += 10;
                        }
                    }
                    while (var8_8 != null) {
                        var31_34 = var8_8.a;
                        var8_8.a = null;
                        var37_41.visitAttribute(var8_8);
                        var8_8 = var31_34;
                    }
                    var37_41.visitMaxs(var38_43, var39_44);
                }
                if (var37_41 != null) {
                    var37_41.visitEnd();
                }
            }
            --var16_16;
        }
        var1_1.visitEnd();
    }

    private void a(int n2, String string, char[] cArray, boolean bl2, MethodVisitor methodVisitor) {
        AnnotationVisitor annotationVisitor;
        int n3;
        int n4 = this.b[n2++] & 0xFF;
        int n5 = Type.getArgumentTypes(string).length - n4;
        for (n3 = 0; n3 < n5; ++n3) {
            annotationVisitor = methodVisitor.visitParameterAnnotation(n3, "Ljava/lang/Synthetic;", false);
            if (annotationVisitor == null) continue;
            annotationVisitor.visitEnd();
        }
        while (n3 < n4 + n5) {
            int n6 = this.readUnsignedShort(n2);
            n2 += 2;
            while (n6 > 0) {
                annotationVisitor = methodVisitor.visitParameterAnnotation(n3, this.readUTF8(n2, cArray), bl2);
                n2 = this.a(n2 + 2, cArray, true, annotationVisitor);
                --n6;
            }
            ++n3;
        }
    }

    private int a(int n2, char[] cArray, boolean bl2, AnnotationVisitor annotationVisitor) {
        int n3 = this.readUnsignedShort(n2);
        n2 += 2;
        if (bl2) {
            while (n3 > 0) {
                n2 = this.a(n2 + 2, cArray, this.readUTF8(n2, cArray), annotationVisitor);
                --n3;
            }
        } else {
            while (n3 > 0) {
                n2 = this.a(n2, cArray, null, annotationVisitor);
                --n3;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return n2;
    }

    private int a(int n2, char[] cArray, String string, AnnotationVisitor annotationVisitor) {
        if (annotationVisitor == null) {
            switch (this.b[n2] & 0xFF) {
                case 101: {
                    return n2 + 5;
                }
                case 64: {
                    return this.a(n2 + 3, cArray, true, null);
                }
                case 91: {
                    return this.a(n2 + 1, cArray, false, null);
                }
            }
            return n2 + 3;
        }
        block5 : switch (this.b[n2++] & 0xFF) {
            case 68: 
            case 70: 
            case 73: 
            case 74: {
                annotationVisitor.visit(string, this.readConst(this.readUnsignedShort(n2), cArray));
                n2 += 2;
                break;
            }
            case 66: {
                annotationVisitor.visit(string, new Byte((byte)this.readInt(this.a[this.readUnsignedShort(n2)])));
                n2 += 2;
                break;
            }
            case 90: {
                annotationVisitor.visit(string, this.readInt(this.a[this.readUnsignedShort(n2)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                n2 += 2;
                break;
            }
            case 83: {
                annotationVisitor.visit(string, new Short((short)this.readInt(this.a[this.readUnsignedShort(n2)])));
                n2 += 2;
                break;
            }
            case 67: {
                annotationVisitor.visit(string, new Character((char)this.readInt(this.a[this.readUnsignedShort(n2)])));
                n2 += 2;
                break;
            }
            case 115: {
                annotationVisitor.visit(string, this.readUTF8(n2, cArray));
                n2 += 2;
                break;
            }
            case 101: {
                annotationVisitor.visitEnum(string, this.readUTF8(n2, cArray), this.readUTF8(n2 + 2, cArray));
                n2 += 4;
                break;
            }
            case 99: {
                annotationVisitor.visit(string, Type.getType(this.readUTF8(n2, cArray)));
                n2 += 2;
                break;
            }
            case 64: {
                n2 = this.a(n2 + 2, cArray, true, annotationVisitor.visitAnnotation(string, this.readUTF8(n2, cArray)));
                break;
            }
            case 91: {
                int n3 = this.readUnsignedShort(n2);
                n2 += 2;
                if (n3 == 0) {
                    return this.a(n2 - 2, cArray, false, annotationVisitor.visitArray(string));
                }
                switch (this.b[n2++] & 0xFF) {
                    case 66: {
                        byte[] byArray = new byte[n3];
                        for (int i2 = 0; i2 < n3; ++i2) {
                            byArray[i2] = (byte)this.readInt(this.a[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, byArray);
                        --n2;
                        break block5;
                    }
                    case 90: {
                        boolean[] blArray = new boolean[n3];
                        for (int i3 = 0; i3 < n3; ++i3) {
                            blArray[i3] = this.readInt(this.a[this.readUnsignedShort(n2)]) != 0;
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, blArray);
                        --n2;
                        break block5;
                    }
                    case 83: {
                        short[] sArray = new short[n3];
                        for (int i4 = 0; i4 < n3; ++i4) {
                            sArray[i4] = (short)this.readInt(this.a[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, sArray);
                        --n2;
                        break block5;
                    }
                    case 67: {
                        char[] cArray2 = new char[n3];
                        for (int i5 = 0; i5 < n3; ++i5) {
                            cArray2[i5] = (char)this.readInt(this.a[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, cArray2);
                        --n2;
                        break block5;
                    }
                    case 73: {
                        int[] nArray = new int[n3];
                        for (int i6 = 0; i6 < n3; ++i6) {
                            nArray[i6] = this.readInt(this.a[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, nArray);
                        --n2;
                        break block5;
                    }
                    case 74: {
                        long[] lArray = new long[n3];
                        for (int i7 = 0; i7 < n3; ++i7) {
                            lArray[i7] = this.readLong(this.a[this.readUnsignedShort(n2)]);
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, lArray);
                        --n2;
                        break block5;
                    }
                    case 70: {
                        float[] fArray = new float[n3];
                        for (int i8 = 0; i8 < n3; ++i8) {
                            fArray[i8] = Float.intBitsToFloat(this.readInt(this.a[this.readUnsignedShort(n2)]));
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, fArray);
                        --n2;
                        break block5;
                    }
                    case 68: {
                        double[] dArray = new double[n3];
                        for (int i9 = 0; i9 < n3; ++i9) {
                            dArray[i9] = Double.longBitsToDouble(this.readLong(this.a[this.readUnsignedShort(n2)]));
                            n2 += 3;
                        }
                        annotationVisitor.visit(string, dArray);
                        --n2;
                        break block5;
                    }
                }
                n2 = this.a(n2 - 3, cArray, false, annotationVisitor.visitArray(string));
            }
        }
        return n2;
    }

    private int a(Object[] objectArray, int n2, int n3, char[] cArray, Label[] labelArray) {
        int n4 = this.b[n3++] & 0xFF;
        switch (n4) {
            case 0: {
                objectArray[n2] = Opcodes.TOP;
                break;
            }
            case 1: {
                objectArray[n2] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                objectArray[n2] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                objectArray[n2] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                objectArray[n2] = Opcodes.LONG;
                break;
            }
            case 5: {
                objectArray[n2] = Opcodes.NULL;
                break;
            }
            case 6: {
                objectArray[n2] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                objectArray[n2] = this.readClass(n3, cArray);
                n3 += 2;
                break;
            }
            default: {
                objectArray[n2] = this.readLabel(this.readUnsignedShort(n3), labelArray);
                n3 += 2;
            }
        }
        return n3;
    }

    protected Label readLabel(int n2, Label[] labelArray) {
        if (labelArray[n2] == null) {
            labelArray[n2] = new Label();
        }
        return labelArray[n2];
    }

    private Attribute a(Attribute[] attributeArray, String string, int n2, int n3, char[] cArray, int n4, Label[] labelArray) {
        for (int i2 = 0; i2 < attributeArray.length; ++i2) {
            if (!attributeArray[i2].type.equals(string)) continue;
            return attributeArray[i2].read(this, n2, n3, cArray, n4, labelArray);
        }
        return new Attribute(string).read(this, n2, n3, null, -1, null);
    }

    public int getItemCount() {
        return this.a.length;
    }

    public int getItem(int n2) {
        return this.a[n2];
    }

    public int getMaxStringLength() {
        return this.d;
    }

    public int readByte(int n2) {
        return this.b[n2] & 0xFF;
    }

    public int readUnsignedShort(int n2) {
        byte[] byArray = this.b;
        return (byArray[n2] & 0xFF) << 8 | byArray[n2 + 1] & 0xFF;
    }

    public short readShort(int n2) {
        byte[] byArray = this.b;
        return (short)((byArray[n2] & 0xFF) << 8 | byArray[n2 + 1] & 0xFF);
    }

    public int readInt(int n2) {
        byte[] byArray = this.b;
        return (byArray[n2] & 0xFF) << 24 | (byArray[n2 + 1] & 0xFF) << 16 | (byArray[n2 + 2] & 0xFF) << 8 | byArray[n2 + 3] & 0xFF;
    }

    public long readLong(int n2) {
        long l2 = this.readInt(n2);
        long l3 = (long)this.readInt(n2 + 4) & 0xFFFFFFFFL;
        return l2 << 32 | l3;
    }

    public String readUTF8(int n2, char[] cArray) {
        int n3 = this.readUnsignedShort(n2);
        String string = this.c[n3];
        if (string != null) {
            return string;
        }
        n2 = this.a[n3];
        this.c[n3] = this.a(n2 + 2, this.readUnsignedShort(n2), cArray);
        return this.c[n3];
    }

    private String a(int n2, int n3, char[] cArray) {
        int n4 = n2 + n3;
        byte[] byArray = this.b;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        while (n2 < n4) {
            int n8 = byArray[n2++];
            switch (n6) {
                case 0: {
                    if ((n8 &= 0xFF) < 128) {
                        cArray[n5++] = (char)n8;
                        break;
                    }
                    if (n8 < 224 && n8 > 191) {
                        n7 = (char)(n8 & 0x1F);
                        n6 = 1;
                        break;
                    }
                    n7 = (char)(n8 & 0xF);
                    n6 = 2;
                    break;
                }
                case 1: {
                    cArray[n5++] = (char)(n7 << 6 | n8 & 0x3F);
                    n6 = 0;
                    break;
                }
                case 2: {
                    n7 = (char)(n7 << 6 | n8 & 0x3F);
                    n6 = 1;
                }
            }
        }
        return new String(cArray, 0, n5);
    }

    public String readClass(int n2, char[] cArray) {
        return this.readUTF8(this.a[this.readUnsignedShort(n2)], cArray);
    }

    public Object readConst(int n2, char[] cArray) {
        int n3 = this.a[n2];
        switch (this.b[n3 - 1]) {
            case 3: {
                return new Integer(this.readInt(n3));
            }
            case 4: {
                return new Float(Float.intBitsToFloat(this.readInt(n3)));
            }
            case 5: {
                return new Long(this.readLong(n3));
            }
            case 6: {
                return new Double(Double.longBitsToDouble(this.readLong(n3)));
            }
            case 7: {
                return Type.getObjectType(this.readUTF8(n3, cArray));
            }
            case 8: {
                return this.readUTF8(n3, cArray);
            }
            case 16: {
                return Type.getMethodType(this.readUTF8(n3, cArray));
            }
        }
        int n4 = this.readByte(n3);
        int[] nArray = this.a;
        int n5 = nArray[this.readUnsignedShort(n3 + 1)];
        String string = this.readClass(n5, cArray);
        n5 = nArray[this.readUnsignedShort(n5 + 2)];
        String string2 = this.readUTF8(n5, cArray);
        String string3 = this.readUTF8(n5 + 2, cArray);
        return new Handle(n4, string, string2, string3);
    }
}

