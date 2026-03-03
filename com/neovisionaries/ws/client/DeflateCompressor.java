/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

class DeflateCompressor {
    DeflateCompressor() {
    }

    public static byte[] compress(byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Deflater deflater = DeflateCompressor.createDeflater();
        DeflaterOutputStream dos = new DeflaterOutputStream((OutputStream)baos, deflater);
        dos.write(input, 0, input.length);
        dos.close();
        deflater.end();
        return baos.toByteArray();
    }

    private static Deflater createDeflater() {
        return new Deflater(-1, true);
    }
}

