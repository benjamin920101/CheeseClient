/*
 * Decompiled with CFR 0.152.
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class dx {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static dn a(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputStream)));
        try {
            dn dn2 = dx.a(dataInputStream, dw.a);
            return dn2;
        }
        finally {
            dataInputStream.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void a(dn dn2, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputStream)));
        try {
            dx.a(dn2, (DataOutput)dataOutputStream);
        }
        finally {
            dataOutputStream.close();
        }
    }

    public static void a(dn dn2, File file) throws IOException {
        \u2603 = new File(file.getAbsolutePath() + "_tmp");
        if (\u2603.exists()) {
            \u2603.delete();
        }
        dx.b(dn2, \u2603);
        if (file.exists()) {
            file.delete();
        }
        if (file.exists()) {
            throw new IOException("Failed to delete " + file);
        }
        \u2603.renameTo(file);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void b(dn dn2, File file) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dx.a(dn2, (DataOutput)dataOutputStream);
        }
        finally {
            dataOutputStream.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static dn a(File file) throws IOException {
        if (!file.exists()) {
            return null;
        }
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            dn dn2 = dx.a(dataInputStream, dw.a);
            return dn2;
        }
        finally {
            dataInputStream.close();
        }
    }

    public static dn a(DataInputStream dataInputStream) throws IOException {
        return dx.a(dataInputStream, dw.a);
    }

    public static dn a(DataInput dataInput, dw dw2) throws IOException {
        eb eb2 = dx.a(dataInput, 0, dw2);
        if (eb2 instanceof dn) {
            return (dn)eb2;
        }
        throw new IOException("Root tag must be a named compound tag");
    }

    public static void a(dn dn2, DataOutput dataOutput) throws IOException {
        dx.a((eb)dn2, dataOutput);
    }

    private static void a(eb eb2, DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(eb2.a());
        if (eb2.a() == 0) {
            return;
        }
        dataOutput.writeUTF("");
        eb2.a(dataOutput);
    }

    private static eb a(DataInput dataInput, int n2, dw dw2) throws IOException {
        byte by = dataInput.readByte();
        if (by == 0) {
            return new dq();
        }
        dataInput.readUTF();
        eb \u26032 = eb.a(by);
        try {
            \u26032.a(dataInput, n2, dw2);
        }
        catch (IOException \u26033) {
            b b2 = b.a(\u26033, "Loading NBT data");
            c \u26034 = b2.a("NBT Tag");
            \u26034.a("Tag name", "[UNNAMED TAG]");
            \u26034.a("Tag type", by);
            throw new e(b2);
        }
        return \u26032;
    }
}

