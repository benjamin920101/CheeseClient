/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import com.neovisionaries.ws.client.WebSocketFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;
import net.dv8tion.jda.internal.utils.BufferedRequestBody;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Okio;
import org.slf4j.Logger;

public class IOUtil {
    private static final Logger log = JDALogger.getLog(IOUtil.class);

    public static void silentClose(AutoCloseable closeable) {
        try {
            closeable.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void silentClose(Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static String getHost(String uri) {
        return URI.create(uri).getHost();
    }

    public static void setServerName(WebSocketFactory factory, String url) {
        String host = IOUtil.getHost(url);
        if (host != null) {
            factory.setServerName(host);
        }
    }

    public static OkHttpClient.Builder newHttpClientBuilder() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(25);
        ConnectionPool connectionPool = new ConnectionPool(5, 10L, TimeUnit.SECONDS);
        return new OkHttpClient.Builder().connectionPool(connectionPool).dispatcher(dispatcher);
    }

    public static byte[] readFully(File file) throws IOException {
        Checks.notNull(file, "File");
        Checks.check(file.exists(), "Provided file does not exist!");
        try (FileInputStream is2 = new FileInputStream(file);){
            int offset;
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("Cannot read the file into memory completely due to it being too large!");
            }
            byte[] bytes = new byte[(int)length];
            int numRead = 0;
            for (offset = 0; offset < bytes.length && (numRead = ((InputStream)is2).read(bytes, offset, bytes.length - offset)) >= 0; offset += numRead) {
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            ((InputStream)is2).close();
            byte[] byArray = bytes;
            return byArray;
        }
    }

    public static byte[] readFully(InputStream stream) throws IOException {
        Checks.notNull(stream, "InputStream");
        byte[] buffer = new byte[1024];
        try (ByteArrayOutputStream bos2 = new ByteArrayOutputStream();){
            int readAmount = 0;
            while ((readAmount = stream.read(buffer)) != -1) {
                bos2.write(buffer, 0, readAmount);
            }
            byte[] byArray = bos2.toByteArray();
            return byArray;
        }
    }

    public static RequestBody createRequestBody(MediaType contentType, InputStream stream) {
        return new BufferedRequestBody(Okio.source(stream), contentType);
    }

    public static short getShortBigEndian(byte[] arr2, int offset) {
        return (short)((arr2[offset] & 0xFF) << 8 | arr2[offset + 1] & 0xFF);
    }

    public static short getShortLittleEndian(byte[] arr2, int offset) {
        return (short)(arr2[offset] & 0xFF | (arr2[offset + 1] & 0xFF) << 8);
    }

    public static int getIntBigEndian(byte[] arr2, int offset) {
        return arr2[offset + 3] & 0xFF | (arr2[offset + 2] & 0xFF) << 8 | (arr2[offset + 1] & 0xFF) << 16 | (arr2[offset] & 0xFF) << 24;
    }

    public static void setIntBigEndian(byte[] arr2, int offset, int it2) {
        arr2[offset] = (byte)(it2 >>> 24 & 0xFF);
        arr2[offset + 1] = (byte)(it2 >>> 16 & 0xFF);
        arr2[offset + 2] = (byte)(it2 >>> 8 & 0xFF);
        arr2[offset + 3] = (byte)(it2 & 0xFF);
    }

    public static ByteBuffer reallocate(ByteBuffer original, int length) {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(original);
        return buffer;
    }

    public static InputStream getBody(Response response) throws IOException {
        String encoding = response.header("content-encoding", "");
        BufferedInputStream data = new BufferedInputStream(response.body().byteStream());
        ((InputStream)data).mark(256);
        try {
            if (encoding.equalsIgnoreCase("gzip")) {
                return new GZIPInputStream(data);
            }
            if (encoding.equalsIgnoreCase("deflate")) {
                return new InflaterInputStream(data, new Inflater(true));
            }
        }
        catch (EOFException | ZipException ex2) {
            ((InputStream)data).reset();
            log.error("Failed to read gzip content for response. Headers: {}\nContent: '{}'", response.headers(), JDALogger.getLazyString(() -> new String(IOUtil.readFully(data))), ex2);
            return null;
        }
        return data;
    }
}

