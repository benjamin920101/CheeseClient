/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

class StringList {
    public final ByteBuffer stringsBuffer;
    public final IntBuffer offsetsBuffer;

    public StringList(String[] strings) {
        int i2;
        int n2 = strings.length;
        byte[][] bytes = new byte[n2][];
        this.offsetsBuffer = ByteBuffer.allocateDirect(n2 * 4).asIntBuffer();
        int offset = 0;
        for (i2 = 0; i2 < n2; ++i2) {
            this.offsetsBuffer.put(i2, offset);
            bytes[i2] = strings[i2].getBytes();
            offset += bytes[i2].length + 1;
        }
        this.stringsBuffer = ByteBuffer.allocateDirect(offset);
        for (i2 = 0; i2 < n2; ++i2) {
            byte[] str = bytes[i2];
            this.stringsBuffer.put(str);
            this.stringsBuffer.put((byte)0);
        }
    }
}

