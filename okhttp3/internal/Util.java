/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AccessControlException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.http2.Header;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;
import okio.Source;

public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final String[] EMPTY_STRING_ARRAY;
    public static final Headers EMPTY_HEADERS;
    public static final ResponseBody EMPTY_RESPONSE;
    public static final RequestBody EMPTY_REQUEST;
    private static final Options UNICODE_BOMS;
    private static final Charset UTF_32BE;
    private static final Charset UTF_32LE;
    public static final TimeZone UTC;
    public static final Comparator<String> NATURAL_ORDER;
    private static final Method addSuppressedExceptionMethod;
    private static final Pattern VERIFY_AS_IP_ADDRESS;

    public static void addSuppressedIfPossible(Throwable e2, Throwable suppressed) {
        if (addSuppressedExceptionMethod != null) {
            try {
                addSuppressedExceptionMethod.invoke((Object)e2, suppressed);
            }
            catch (IllegalAccessException | InvocationTargetException reflectiveOperationException) {
                // empty catch block
            }
        }
    }

    private Util() {
    }

    public static void checkOffsetAndCount(long arrayLength, long offset, long count) {
        if ((offset | count) < 0L || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (RuntimeException rethrown) {
                throw rethrown;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            }
            catch (AssertionError e2) {
                if (!Util.isAndroidGetsocknameError(e2)) {
                    throw e2;
                }
            }
            catch (RuntimeException rethrown) {
                throw rethrown;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            }
            catch (RuntimeException rethrown) {
                throw rethrown;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static boolean discard(Source source, int timeout, TimeUnit timeUnit) {
        try {
            return Util.skipAll(source, timeout, timeUnit);
        }
        catch (IOException e2) {
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean skipAll(Source source, int duration, TimeUnit timeUnit) throws IOException {
        long now = System.nanoTime();
        long originalDuration = source.timeout().hasDeadline() ? source.timeout().deadlineNanoTime() - now : Long.MAX_VALUE;
        source.timeout().deadlineNanoTime(now + Math.min(originalDuration, timeUnit.toNanos(duration)));
        try {
            Buffer skipBuffer = new Buffer();
            while (source.read(skipBuffer, 8192L) != -1L) {
                skipBuffer.clear();
            }
            boolean bl2 = true;
            return bl2;
        }
        catch (InterruptedIOException e2) {
            boolean bl3 = false;
            return bl3;
        }
        finally {
            if (originalDuration == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(now + originalDuration);
            }
        }
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList<T>(list));
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return map.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(new LinkedHashMap<K, V>(map));
    }

    @SafeVarargs
    public static <T> List<T> immutableList(T ... elements) {
        return Collections.unmodifiableList(Arrays.asList((Object[])elements.clone()));
    }

    public static ThreadFactory threadFactory(String name, boolean daemon) {
        return runnable -> {
            Thread result = new Thread(runnable, name);
            result.setDaemon(daemon);
            return result;
        };
    }

    public static String[] intersect(Comparator<? super String> comparator, String[] first, String[] second) {
        ArrayList<String> result = new ArrayList<String>();
        block0: for (String a2 : first) {
            for (String b2 : second) {
                if (comparator.compare(a2, b2) != 0) continue;
                result.add(a2);
                continue block0;
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static boolean nonEmptyIntersection(Comparator<String> comparator, String[] first, String[] second) {
        if (first == null || second == null || first.length == 0 || second.length == 0) {
            return false;
        }
        for (String a2 : first) {
            for (String b2 : second) {
                if (comparator.compare(a2, b2) != 0) continue;
                return true;
            }
        }
        return false;
    }

    public static String hostHeader(HttpUrl url, boolean includeDefaultPort) {
        String host = url.host().contains(":") ? "[" + url.host() + "]" : url.host();
        return includeDefaultPort || url.port() != HttpUrl.defaultPort(url.scheme()) ? host + ":" + url.port() : host;
    }

    public static boolean isAndroidGetsocknameError(AssertionError e2) {
        return ((Throwable)((Object)e2)).getCause() != null && ((Throwable)((Object)e2)).getMessage() != null && ((Throwable)((Object)e2)).getMessage().contains("getsockname failed");
    }

    public static int indexOf(Comparator<String> comparator, String[] array, String value) {
        int size = array.length;
        for (int i2 = 0; i2 < size; ++i2) {
            if (comparator.compare(array[i2], value) != 0) continue;
            return i2;
        }
        return -1;
    }

    public static String[] concat(String[] array, String value) {
        String[] result = new String[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[result.length - 1] = value;
        return result;
    }

    public static int skipLeadingAsciiWhitespace(String input, int pos, int limit) {
        block3: for (int i2 = pos; i2 < limit; ++i2) {
            switch (input.charAt(i2)) {
                case '\t': 
                case '\n': 
                case '\f': 
                case '\r': 
                case ' ': {
                    continue block3;
                }
                default: {
                    return i2;
                }
            }
        }
        return limit;
    }

    public static int skipTrailingAsciiWhitespace(String input, int pos, int limit) {
        block3: for (int i2 = limit - 1; i2 >= pos; --i2) {
            switch (input.charAt(i2)) {
                case '\t': 
                case '\n': 
                case '\f': 
                case '\r': 
                case ' ': {
                    continue block3;
                }
                default: {
                    return i2 + 1;
                }
            }
        }
        return pos;
    }

    public static String trimSubstring(String string, int pos, int limit) {
        int start = Util.skipLeadingAsciiWhitespace(string, pos, limit);
        int end = Util.skipTrailingAsciiWhitespace(string, start, limit);
        return string.substring(start, end);
    }

    public static int delimiterOffset(String input, int pos, int limit, String delimiters) {
        for (int i2 = pos; i2 < limit; ++i2) {
            if (delimiters.indexOf(input.charAt(i2)) == -1) continue;
            return i2;
        }
        return limit;
    }

    public static int delimiterOffset(String input, int pos, int limit, char delimiter) {
        for (int i2 = pos; i2 < limit; ++i2) {
            if (input.charAt(i2) != delimiter) continue;
            return i2;
        }
        return limit;
    }

    public static String canonicalizeHost(String host) {
        if (host.contains(":")) {
            InetAddress inetAddress;
            InetAddress inetAddress2 = inetAddress = host.startsWith("[") && host.endsWith("]") ? Util.decodeIpv6(host, 1, host.length() - 1) : Util.decodeIpv6(host, 0, host.length());
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                return Util.inet6AddressToAscii(address);
            }
            if (address.length == 4) {
                return inetAddress.getHostAddress();
            }
            throw new AssertionError((Object)("Invalid IPv6 address: '" + host + "'"));
        }
        try {
            String result = IDN.toASCII(host).toLowerCase(Locale.US);
            if (result.isEmpty()) {
                return null;
            }
            if (Util.containsInvalidHostnameAsciiCodes(result)) {
                return null;
            }
            return result;
        }
        catch (IllegalArgumentException e2) {
            return null;
        }
    }

    private static boolean containsInvalidHostnameAsciiCodes(String hostnameAscii) {
        for (int i2 = 0; i2 < hostnameAscii.length(); ++i2) {
            char c2 = hostnameAscii.charAt(i2);
            if (c2 <= '\u001f' || c2 >= '\u007f') {
                return true;
            }
            if (" #%/:?@[\\]".indexOf(c2) == -1) continue;
            return true;
        }
        return false;
    }

    public static int indexOfControlOrNonAscii(String input) {
        int length = input.length();
        for (int i2 = 0; i2 < length; ++i2) {
            char c2 = input.charAt(i2);
            if (c2 > '\u001f' && c2 < '\u007f') continue;
            return i2;
        }
        return -1;
    }

    public static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
    }

    public static String format(String format, Object ... args) {
        return String.format(Locale.US, format, args);
    }

    public static Charset bomAwareCharset(BufferedSource source, Charset charset) throws IOException {
        switch (source.select(UNICODE_BOMS)) {
            case 0: {
                return StandardCharsets.UTF_8;
            }
            case 1: {
                return StandardCharsets.UTF_16BE;
            }
            case 2: {
                return StandardCharsets.UTF_16LE;
            }
            case 3: {
                return UTF_32BE;
            }
            case 4: {
                return UTF_32LE;
            }
            case -1: {
                return charset;
            }
        }
        throw new AssertionError();
    }

    public static int checkDuration(String name, long duration, TimeUnit unit) {
        if (duration < 0L) {
            throw new IllegalArgumentException(name + " < 0");
        }
        if (unit == null) {
            throw new NullPointerException("unit == null");
        }
        long millis = unit.toMillis(duration);
        if (millis > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(name + " too large.");
        }
        if (millis == 0L && duration > 0L) {
            throw new IllegalArgumentException(name + " too small.");
        }
        return (int)millis;
    }

    public static int decodeHexDigit(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - 48;
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return c2 - 97 + 10;
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return c2 - 65 + 10;
        }
        return -1;
    }

    @Nullable
    private static InetAddress decodeIpv6(String input, int pos, int limit) {
        byte[] address = new byte[16];
        int b2 = 0;
        int compress = -1;
        int groupOffset = -1;
        int i2 = pos;
        while (i2 < limit) {
            char c2;
            int hexDigit;
            if (b2 == address.length) {
                return null;
            }
            if (i2 + 2 <= limit && input.regionMatches(i2, "::", 0, 2)) {
                if (compress != -1) {
                    return null;
                }
                compress = b2 += 2;
                if ((i2 += 2) == limit) {
                    break;
                }
            } else if (b2 != 0 && !input.regionMatches(i2, ":", 0, 1)) {
                if (input.regionMatches(i2, ".", 0, 1)) {
                    if (!Util.decodeIpv4Suffix(input, groupOffset, limit, address, b2 - 2)) {
                        return null;
                    }
                    b2 += 2;
                    break;
                }
                return null;
            }
            int value = 0;
            groupOffset = ++i2;
            while (i2 < limit && (hexDigit = Util.decodeHexDigit(c2 = input.charAt(i2))) != -1) {
                value = (value << 4) + hexDigit;
                ++i2;
            }
            int groupLength = i2 - groupOffset;
            if (groupLength == 0 || groupLength > 4) {
                return null;
            }
            address[b2++] = (byte)(value >>> 8 & 0xFF);
            address[b2++] = (byte)(value & 0xFF);
        }
        if (b2 != address.length) {
            if (compress == -1) {
                return null;
            }
            System.arraycopy(address, compress, address, address.length - (b2 - compress), b2 - compress);
            Arrays.fill(address, compress, compress + (address.length - b2), (byte)0);
        }
        try {
            return InetAddress.getByAddress(address);
        }
        catch (UnknownHostException e2) {
            throw new AssertionError();
        }
    }

    private static boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
        int b2 = addressOffset;
        int i2 = pos;
        while (i2 < limit) {
            char c2;
            if (b2 == address.length) {
                return false;
            }
            if (b2 != addressOffset && input.charAt(i2) != '.') {
                return false;
            }
            int value = 0;
            int groupOffset = ++i2;
            while (i2 < limit && (c2 = input.charAt(i2)) >= '0' && c2 <= '9') {
                if (value == 0 && groupOffset != i2) {
                    return false;
                }
                if ((value = value * 10 + c2 - 48) > 255) {
                    return false;
                }
                ++i2;
            }
            int groupLength = i2 - groupOffset;
            if (groupLength == 0) {
                return false;
            }
            address[b2++] = (byte)value;
        }
        return b2 == addressOffset + 4;
    }

    private static String inet6AddressToAscii(byte[] address) {
        int longestRunOffset = -1;
        int longestRunLength = 0;
        for (int i2 = 0; i2 < address.length; i2 += 2) {
            int currentRunOffset = i2;
            while (i2 < 16 && address[i2] == 0 && address[i2 + 1] == 0) {
                i2 += 2;
            }
            int currentRunLength = i2 - currentRunOffset;
            if (currentRunLength <= longestRunLength || currentRunLength < 4) continue;
            longestRunOffset = currentRunOffset;
            longestRunLength = currentRunLength;
        }
        Buffer result = new Buffer();
        int i3 = 0;
        while (i3 < address.length) {
            if (i3 == longestRunOffset) {
                result.writeByte(58);
                if ((i3 += longestRunLength) != 16) continue;
                result.writeByte(58);
                continue;
            }
            if (i3 > 0) {
                result.writeByte(58);
            }
            int group = (address[i3] & 0xFF) << 8 | address[i3 + 1] & 0xFF;
            result.writeHexadecimalUnsignedLong(group);
            i3 += 2;
        }
        return result.readUtf8();
    }

    public static X509TrustManager platformTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore)null);
            Object[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager)trustManagers[0];
        }
        catch (GeneralSecurityException e2) {
            throw new AssertionError("No System TLS", e2);
        }
    }

    public static Headers toHeaders(List<Header> headerBlock) {
        Headers.Builder builder = new Headers.Builder();
        for (Header header : headerBlock) {
            Internal.instance.addLenient(builder, header.name.utf8(), header.value.utf8());
        }
        return builder.build();
    }

    public static List<Header> toHeaderBlock(Headers headers) {
        ArrayList<Header> result = new ArrayList<Header>();
        for (int i2 = 0; i2 < headers.size(); ++i2) {
            result.add(new Header(headers.name(i2), headers.value(i2)));
        }
        return result;
    }

    public static String getSystemProperty(String key, @Nullable String defaultValue) {
        String value;
        try {
            value = System.getProperty(key);
        }
        catch (AccessControlException ex2) {
            return defaultValue;
        }
        return value != null ? value : defaultValue;
    }

    static {
        Method m2;
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_STRING_ARRAY = new String[0];
        EMPTY_HEADERS = Headers.of(new String[0]);
        EMPTY_RESPONSE = ResponseBody.create(null, EMPTY_BYTE_ARRAY);
        EMPTY_REQUEST = RequestBody.create(null, EMPTY_BYTE_ARRAY);
        UNICODE_BOMS = Options.of(ByteString.decodeHex("efbbbf"), ByteString.decodeHex("feff"), ByteString.decodeHex("fffe"), ByteString.decodeHex("0000ffff"), ByteString.decodeHex("ffff0000"));
        UTF_32BE = Charset.forName("UTF-32BE");
        UTF_32LE = Charset.forName("UTF-32LE");
        UTC = TimeZone.getTimeZone("GMT");
        NATURAL_ORDER = String::compareTo;
        try {
            m2 = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        }
        catch (Exception e2) {
            m2 = null;
        }
        addSuppressedExceptionMethod = m2;
        VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }
}

