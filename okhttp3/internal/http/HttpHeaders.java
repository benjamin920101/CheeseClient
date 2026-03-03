/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;

public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.encodeUtf8("\t ,=");

    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return HttpHeaders.contentLength(response.headers());
    }

    public static long contentLength(Headers headers) {
        return HttpHeaders.stringToLong(headers.get("Content-Length"));
    }

    private static long stringToLong(String s2) {
        if (s2 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(s2);
        }
        catch (NumberFormatException e2) {
            return -1L;
        }
    }

    public static boolean varyMatches(Response cachedResponse, Headers cachedRequest, Request newRequest) {
        for (String field : HttpHeaders.varyFields(cachedResponse)) {
            if (Objects.equals(cachedRequest.values(field), newRequest.headers(field))) continue;
            return false;
        }
        return true;
    }

    public static boolean hasVaryAll(Response response) {
        return HttpHeaders.hasVaryAll(response.headers());
    }

    public static boolean hasVaryAll(Headers responseHeaders) {
        return HttpHeaders.varyFields(responseHeaders).contains("*");
    }

    private static Set<String> varyFields(Response response) {
        return HttpHeaders.varyFields(response.headers());
    }

    public static Set<String> varyFields(Headers responseHeaders) {
        Set<String> result = Collections.emptySet();
        int size = responseHeaders.size();
        for (int i2 = 0; i2 < size; ++i2) {
            if (!"Vary".equalsIgnoreCase(responseHeaders.name(i2))) continue;
            String value = responseHeaders.value(i2);
            if (result.isEmpty()) {
                result = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
            }
            for (String varyField : value.split(",")) {
                result.add(varyField.trim());
            }
        }
        return result;
    }

    public static Headers varyHeaders(Response response) {
        Headers requestHeaders = response.networkResponse().request().headers();
        Headers responseHeaders = response.headers();
        return HttpHeaders.varyHeaders(requestHeaders, responseHeaders);
    }

    public static Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
        Set<String> varyFields = HttpHeaders.varyFields(responseHeaders);
        if (varyFields.isEmpty()) {
            return Util.EMPTY_HEADERS;
        }
        Headers.Builder result = new Headers.Builder();
        int size = requestHeaders.size();
        for (int i2 = 0; i2 < size; ++i2) {
            String fieldName = requestHeaders.name(i2);
            if (!varyFields.contains(fieldName)) continue;
            result.add(fieldName, requestHeaders.value(i2));
        }
        return result.build();
    }

    public static List<Challenge> parseChallenges(Headers responseHeaders, String headerName) {
        ArrayList<Challenge> result = new ArrayList<Challenge>();
        for (int h2 = 0; h2 < responseHeaders.size(); ++h2) {
            if (!headerName.equalsIgnoreCase(responseHeaders.name(h2))) continue;
            Buffer header = new Buffer().writeUtf8(responseHeaders.value(h2));
            HttpHeaders.parseChallengeHeader(result, header);
        }
        return result;
    }

    private static void parseChallengeHeader(List<Challenge> result, Buffer header) {
        String peek = null;
        while (true) {
            LinkedHashMap<String, String> parameters;
            String schemeName;
            block11: {
                if (peek == null) {
                    HttpHeaders.skipWhitespaceAndCommas(header);
                    peek = HttpHeaders.readToken(header);
                    if (peek == null) {
                        return;
                    }
                }
                schemeName = peek;
                boolean commaPrefixed = HttpHeaders.skipWhitespaceAndCommas(header);
                peek = HttpHeaders.readToken(header);
                if (peek == null) {
                    if (!header.exhausted()) {
                        return;
                    }
                    result.add(new Challenge(schemeName, Collections.emptyMap()));
                    return;
                }
                int eqCount = HttpHeaders.skipAll(header, (byte)61);
                boolean commaSuffixed = HttpHeaders.skipWhitespaceAndCommas(header);
                if (!commaPrefixed && (commaSuffixed || header.exhausted())) {
                    result.add(new Challenge(schemeName, Collections.singletonMap(null, peek + HttpHeaders.repeat('=', eqCount))));
                    peek = null;
                    continue;
                }
                parameters = new LinkedHashMap<String, String>();
                eqCount += HttpHeaders.skipAll(header, (byte)61);
                do {
                    String parameterValue;
                    if (peek == null) {
                        peek = HttpHeaders.readToken(header);
                        if (HttpHeaders.skipWhitespaceAndCommas(header)) break block11;
                        eqCount = HttpHeaders.skipAll(header, (byte)61);
                    }
                    if (eqCount == 0) break block11;
                    if (eqCount > 1) {
                        return;
                    }
                    if (HttpHeaders.skipWhitespaceAndCommas(header)) {
                        return;
                    }
                    String string = parameterValue = !header.exhausted() && header.getByte(0L) == 34 ? HttpHeaders.readQuotedString(header) : HttpHeaders.readToken(header);
                    if (parameterValue == null) {
                        return;
                    }
                    String replaced = parameters.put(peek, parameterValue);
                    peek = null;
                    if (replaced == null) continue;
                    return;
                } while (HttpHeaders.skipWhitespaceAndCommas(header) || header.exhausted());
                return;
            }
            result.add(new Challenge(schemeName, parameters));
        }
    }

    private static boolean skipWhitespaceAndCommas(Buffer buffer) {
        boolean commaFound = false;
        while (!buffer.exhausted()) {
            byte b2 = buffer.getByte(0L);
            if (b2 == 44) {
                buffer.readByte();
                commaFound = true;
                continue;
            }
            if (b2 != 32 && b2 != 9) break;
            buffer.readByte();
        }
        return commaFound;
    }

    private static int skipAll(Buffer buffer, byte b2) {
        int count = 0;
        while (!buffer.exhausted() && buffer.getByte(0L) == b2) {
            ++count;
            buffer.readByte();
        }
        return count;
    }

    private static String readQuotedString(Buffer buffer) {
        if (buffer.readByte() != 34) {
            throw new IllegalArgumentException();
        }
        Buffer result = new Buffer();
        long i2;
        while ((i2 = buffer.indexOfElement(QUOTED_STRING_DELIMITERS)) != -1L) {
            if (buffer.getByte(i2) == 34) {
                result.write(buffer, i2);
                buffer.readByte();
                return result.readUtf8();
            }
            if (buffer.size() == i2 + 1L) {
                return null;
            }
            result.write(buffer, i2);
            buffer.readByte();
            result.write(buffer, 1L);
        }
        return null;
    }

    private static String readToken(Buffer buffer) {
        try {
            long tokenSize = buffer.indexOfElement(TOKEN_DELIMITERS);
            if (tokenSize == -1L) {
                tokenSize = buffer.size();
            }
            return tokenSize != 0L ? buffer.readUtf8(tokenSize) : null;
        }
        catch (EOFException e2) {
            throw new AssertionError();
        }
    }

    private static String repeat(char c2, int count) {
        char[] array = new char[count];
        Arrays.fill(array, c2);
        return new String(array);
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl url, Headers headers) {
        if (cookieJar == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> cookies = Cookie.parseAll(url, headers);
        if (cookies.isEmpty()) {
            return;
        }
        cookieJar.saveFromResponse(url, cookies);
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int responseCode = response.code();
        if ((responseCode < 100 || responseCode >= 200) && responseCode != 204 && responseCode != 304) {
            return true;
        }
        return HttpHeaders.contentLength(response) != -1L || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"));
    }

    public static int skipUntil(String input, int pos, String characters) {
        while (pos < input.length() && characters.indexOf(input.charAt(pos)) == -1) {
            ++pos;
        }
        return pos;
    }

    public static int skipWhitespace(String input, int pos) {
        char c2;
        while (pos < input.length() && ((c2 = input.charAt(pos)) == ' ' || c2 == '\t')) {
            ++pos;
        }
        return pos;
    }

    public static int parseSeconds(String value, int defaultValue) {
        try {
            long seconds = Long.parseLong(value);
            if (seconds > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (seconds < 0L) {
                return 0;
            }
            return (int)seconds;
        }
        catch (NumberFormatException e2) {
            return defaultValue;
        }
    }
}

