/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;

public final class HttpUrl {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    static final String QUERY_ENCODE_SET = " \"'<>#";
    static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String FRAGMENT_ENCODE_SET = "";
    static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    final String scheme;
    private final String username;
    private final String password;
    final String host;
    final int port;
    private final List<String> pathSegments;
    @Nullable
    private final List<String> queryNamesAndValues;
    @Nullable
    private final String fragment;
    private final String url;

    HttpUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.username = HttpUrl.percentDecode(builder.encodedUsername, false);
        this.password = HttpUrl.percentDecode(builder.encodedPassword, false);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = this.percentDecode(builder.encodedPathSegments, false);
        this.queryNamesAndValues = builder.encodedQueryNamesAndValues != null ? this.percentDecode(builder.encodedQueryNamesAndValues, true) : null;
        this.fragment = builder.encodedFragment != null ? HttpUrl.percentDecode(builder.encodedFragment, false) : null;
        this.url = builder.toString();
    }

    public URL url() {
        try {
            return new URL(this.url);
        }
        catch (MalformedURLException e2) {
            throw new RuntimeException(e2);
        }
    }

    public URI uri() {
        String uri = this.newBuilder().reencodeForUri().toString();
        try {
            return new URI(uri);
        }
        catch (URISyntaxException e2) {
            try {
                String stripped = uri.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", FRAGMENT_ENCODE_SET);
                return URI.create(stripped);
            }
            catch (Exception e1) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String scheme() {
        return this.scheme;
    }

    public boolean isHttps() {
        return this.scheme.equals("https");
    }

    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return FRAGMENT_ENCODE_SET;
        }
        int usernameStart = this.scheme.length() + 3;
        int usernameEnd = Util.delimiterOffset(this.url, usernameStart, this.url.length(), ":@");
        return this.url.substring(usernameStart, usernameEnd);
    }

    public String username() {
        return this.username;
    }

    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return FRAGMENT_ENCODE_SET;
        }
        int passwordStart = this.url.indexOf(58, this.scheme.length() + 3) + 1;
        int passwordEnd = this.url.indexOf(64);
        return this.url.substring(passwordStart, passwordEnd);
    }

    public String password() {
        return this.password;
    }

    public String host() {
        return this.host;
    }

    public int port() {
        return this.port;
    }

    public static int defaultPort(String scheme) {
        if (scheme.equals("http")) {
            return 80;
        }
        if (scheme.equals("https")) {
            return 443;
        }
        return -1;
    }

    public int pathSize() {
        return this.pathSegments.size();
    }

    public String encodedPath() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        int pathEnd = Util.delimiterOffset(this.url, pathStart, this.url.length(), "?#");
        return this.url.substring(pathStart, pathEnd);
    }

    static void pathSegmentsToString(StringBuilder out, List<String> pathSegments) {
        int size = pathSegments.size();
        for (int i2 = 0; i2 < size; ++i2) {
            out.append('/');
            out.append(pathSegments.get(i2));
        }
    }

    public List<String> encodedPathSegments() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        int pathEnd = Util.delimiterOffset(this.url, pathStart, this.url.length(), "?#");
        ArrayList<String> result = new ArrayList<String>();
        int i2 = pathStart;
        while (i2 < pathEnd) {
            int segmentEnd = Util.delimiterOffset(this.url, ++i2, pathEnd, '/');
            result.add(this.url.substring(i2, segmentEnd));
            i2 = segmentEnd;
        }
        return result;
    }

    public List<String> pathSegments() {
        return this.pathSegments;
    }

    @Nullable
    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = this.url.indexOf(63) + 1;
        int queryEnd = Util.delimiterOffset(this.url, queryStart, this.url.length(), '#');
        return this.url.substring(queryStart, queryEnd);
    }

    static void namesAndValuesToQueryString(StringBuilder out, List<String> namesAndValues) {
        int size = namesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String name = namesAndValues.get(i2);
            String value = namesAndValues.get(i2 + 1);
            if (i2 > 0) {
                out.append('&');
            }
            out.append(name);
            if (value == null) continue;
            out.append('=');
            out.append(value);
        }
    }

    static List<String> queryStringToNamesAndValues(String encodedQuery) {
        ArrayList<String> result = new ArrayList<String>();
        int pos = 0;
        while (pos <= encodedQuery.length()) {
            int equalsOffset;
            int ampersandOffset = encodedQuery.indexOf(38, pos);
            if (ampersandOffset == -1) {
                ampersandOffset = encodedQuery.length();
            }
            if ((equalsOffset = encodedQuery.indexOf(61, pos)) == -1 || equalsOffset > ampersandOffset) {
                result.add(encodedQuery.substring(pos, ampersandOffset));
                result.add(null);
            } else {
                result.add(encodedQuery.substring(pos, equalsOffset));
                result.add(encodedQuery.substring(equalsOffset + 1, ampersandOffset));
            }
            pos = ampersandOffset + 1;
        }
        return result;
    }

    @Nullable
    public String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        HttpUrl.namesAndValuesToQueryString(result, this.queryNamesAndValues);
        return result.toString();
    }

    public int querySize() {
        return this.queryNamesAndValues != null ? this.queryNamesAndValues.size() / 2 : 0;
    }

    @Nullable
    public String queryParameter(String name) {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int size = this.queryNamesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (!name.equals(this.queryNamesAndValues.get(i2))) continue;
            return this.queryNamesAndValues.get(i2 + 1);
        }
        return null;
    }

    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        LinkedHashSet<String> result = new LinkedHashSet<String>();
        int size = this.queryNamesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            result.add(this.queryNamesAndValues.get(i2));
        }
        return Collections.unmodifiableSet(result);
    }

    public List<String> queryParameterValues(String name) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        ArrayList<String> result = new ArrayList<String>();
        int size = this.queryNamesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (!name.equals(this.queryNamesAndValues.get(i2))) continue;
            result.add(this.queryNamesAndValues.get(i2 + 1));
        }
        return Collections.unmodifiableList(result);
    }

    public String queryParameterName(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get(index * 2);
    }

    public String queryParameterValue(int index) {
        if (this.queryNamesAndValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.queryNamesAndValues.get(index * 2 + 1);
    }

    @Nullable
    public String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        int fragmentStart = this.url.indexOf(35) + 1;
        return this.url.substring(fragmentStart);
    }

    @Nullable
    public String fragment() {
        return this.fragment;
    }

    public String redact() {
        return this.newBuilder("/...").username(FRAGMENT_ENCODE_SET).password(FRAGMENT_ENCODE_SET).build().toString();
    }

    @Nullable
    public HttpUrl resolve(String link) {
        Builder builder = this.newBuilder(link);
        return builder != null ? builder.build() : null;
    }

    public Builder newBuilder() {
        Builder result = new Builder();
        result.scheme = this.scheme;
        result.encodedUsername = this.encodedUsername();
        result.encodedPassword = this.encodedPassword();
        result.host = this.host;
        result.port = this.port != HttpUrl.defaultPort(this.scheme) ? this.port : -1;
        result.encodedPathSegments.clear();
        result.encodedPathSegments.addAll(this.encodedPathSegments());
        result.encodedQuery(this.encodedQuery());
        result.encodedFragment = this.encodedFragment();
        return result;
    }

    @Nullable
    public Builder newBuilder(String link) {
        try {
            return new Builder().parse(this, link);
        }
        catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    @Nullable
    public static HttpUrl parse(String url) {
        try {
            return HttpUrl.get(url);
        }
        catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    public static HttpUrl get(String url) {
        return new Builder().parse(null, url).build();
    }

    @Nullable
    public static HttpUrl get(URL url) {
        return HttpUrl.parse(url.toString());
    }

    @Nullable
    public static HttpUrl get(URI uri) {
        return HttpUrl.parse(uri.toString());
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof HttpUrl && ((HttpUrl)other).url.equals(this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return this.url;
    }

    @Nullable
    public String topPrivateDomain() {
        if (Util.verifyAsIpAddress(this.host)) {
            return null;
        }
        return PublicSuffixDatabase.get().getEffectiveTldPlusOne(this.host);
    }

    static String percentDecode(String encoded, boolean plusIsSpace) {
        return HttpUrl.percentDecode(encoded, 0, encoded.length(), plusIsSpace);
    }

    private List<String> percentDecode(List<String> list, boolean plusIsSpace) {
        int size = list.size();
        ArrayList<String> result = new ArrayList<String>(size);
        for (int i2 = 0; i2 < size; ++i2) {
            String s2 = list.get(i2);
            result.add(s2 != null ? HttpUrl.percentDecode(s2, plusIsSpace) : null);
        }
        return Collections.unmodifiableList(result);
    }

    static String percentDecode(String encoded, int pos, int limit, boolean plusIsSpace) {
        for (int i2 = pos; i2 < limit; ++i2) {
            char c2 = encoded.charAt(i2);
            if (c2 != '%' && (c2 != '+' || !plusIsSpace)) continue;
            Buffer out = new Buffer();
            out.writeUtf8(encoded, pos, i2);
            HttpUrl.percentDecode(out, encoded, i2, limit, plusIsSpace);
            return out.readUtf8();
        }
        return encoded.substring(pos, limit);
    }

    static void percentDecode(Buffer out, String encoded, int pos, int limit, boolean plusIsSpace) {
        int codePoint;
        for (int i2 = pos; i2 < limit; i2 += Character.charCount(codePoint)) {
            codePoint = encoded.codePointAt(i2);
            if (codePoint == 37 && i2 + 2 < limit) {
                int d1 = Util.decodeHexDigit(encoded.charAt(i2 + 1));
                int d2 = Util.decodeHexDigit(encoded.charAt(i2 + 2));
                if (d1 != -1 && d2 != -1) {
                    out.writeByte((d1 << 4) + d2);
                    i2 += 2;
                    continue;
                }
            } else if (codePoint == 43 && plusIsSpace) {
                out.writeByte(32);
                continue;
            }
            out.writeUtf8CodePoint(codePoint);
        }
    }

    static boolean percentEncoded(String encoded, int pos, int limit) {
        return pos + 2 < limit && encoded.charAt(pos) == '%' && Util.decodeHexDigit(encoded.charAt(pos + 1)) != -1 && Util.decodeHexDigit(encoded.charAt(pos + 2)) != -1;
    }

    static String canonicalize(String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, @Nullable Charset charset) {
        int codePoint;
        for (int i2 = pos; i2 < limit; i2 += Character.charCount(codePoint)) {
            codePoint = input.codePointAt(i2);
            if (!(codePoint < 32 || codePoint == 127 || codePoint >= 128 && asciiOnly || encodeSet.indexOf(codePoint) != -1 || codePoint == 37 && (!alreadyEncoded || strict && !HttpUrl.percentEncoded(input, i2, limit))) && (codePoint != 43 || !plusIsSpace)) continue;
            Buffer out = new Buffer();
            out.writeUtf8(input, pos, i2);
            HttpUrl.canonicalize(out, input, i2, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset);
            return out.readUtf8();
        }
        return input.substring(pos, limit);
    }

    static void canonicalize(Buffer out, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, @Nullable Charset charset) {
        int codePoint;
        Buffer encodedCharBuffer = null;
        for (int i2 = pos; i2 < limit; i2 += Character.charCount(codePoint)) {
            codePoint = input.codePointAt(i2);
            if (alreadyEncoded && (codePoint == 9 || codePoint == 10 || codePoint == 12 || codePoint == 13)) continue;
            if (codePoint == 43 && plusIsSpace) {
                out.writeUtf8(alreadyEncoded ? "+" : "%2B");
                continue;
            }
            if (codePoint < 32 || codePoint == 127 || codePoint >= 128 && asciiOnly || encodeSet.indexOf(codePoint) != -1 || codePoint == 37 && (!alreadyEncoded || strict && !HttpUrl.percentEncoded(input, i2, limit))) {
                if (encodedCharBuffer == null) {
                    encodedCharBuffer = new Buffer();
                }
                if (charset == null || charset.equals(StandardCharsets.UTF_8)) {
                    encodedCharBuffer.writeUtf8CodePoint(codePoint);
                } else {
                    encodedCharBuffer.writeString(input, i2, i2 + Character.charCount(codePoint), charset);
                }
                while (!encodedCharBuffer.exhausted()) {
                    int b2 = encodedCharBuffer.readByte() & 0xFF;
                    out.writeByte(37);
                    out.writeByte(HEX_DIGITS[b2 >> 4 & 0xF]);
                    out.writeByte(HEX_DIGITS[b2 & 0xF]);
                }
                continue;
            }
            out.writeUtf8CodePoint(codePoint);
        }
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly, @Nullable Charset charset) {
        return HttpUrl.canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, charset);
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean asciiOnly) {
        return HttpUrl.canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, strict, plusIsSpace, asciiOnly, null);
    }

    public static final class Builder {
        @Nullable
        String scheme;
        String encodedUsername = "";
        String encodedPassword = "";
        @Nullable
        String host;
        int port = -1;
        final List<String> encodedPathSegments = new ArrayList<String>();
        @Nullable
        List<String> encodedQueryNamesAndValues;
        @Nullable
        String encodedFragment;
        static final String INVALID_HOST = "Invalid URL host";

        public Builder() {
            this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
        }

        public Builder scheme(String scheme) {
            if (scheme == null) {
                throw new NullPointerException("scheme == null");
            }
            if (scheme.equalsIgnoreCase("http")) {
                this.scheme = "http";
            } else if (scheme.equalsIgnoreCase("https")) {
                this.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return this;
        }

        public Builder username(String username) {
            if (username == null) {
                throw new NullPointerException("username == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(username, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedUsername(String encodedUsername) {
            if (encodedUsername == null) {
                throw new NullPointerException("encodedUsername == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(encodedUsername, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                throw new NullPointerException("password == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(password, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedPassword(String encodedPassword) {
            if (encodedPassword == null) {
                throw new NullPointerException("encodedPassword == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(encodedPassword, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder host(String host) {
            if (host == null) {
                throw new NullPointerException("host == null");
            }
            String encoded = Builder.canonicalizeHost(host, 0, host.length());
            if (encoded == null) {
                throw new IllegalArgumentException("unexpected host: " + host);
            }
            this.host = encoded;
            return this;
        }

        public Builder port(int port) {
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("unexpected port: " + port);
            }
            this.port = port;
            return this;
        }

        int effectivePort() {
            return this.port != -1 ? this.port : HttpUrl.defaultPort(this.scheme);
        }

        public Builder addPathSegment(String pathSegment) {
            if (pathSegment == null) {
                throw new NullPointerException("pathSegment == null");
            }
            this.push(pathSegment, 0, pathSegment.length(), false, false);
            return this;
        }

        public Builder addPathSegments(String pathSegments) {
            if (pathSegments == null) {
                throw new NullPointerException("pathSegments == null");
            }
            return this.addPathSegments(pathSegments, false);
        }

        public Builder addEncodedPathSegment(String encodedPathSegment) {
            if (encodedPathSegment == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            this.push(encodedPathSegment, 0, encodedPathSegment.length(), false, true);
            return this;
        }

        public Builder addEncodedPathSegments(String encodedPathSegments) {
            if (encodedPathSegments == null) {
                throw new NullPointerException("encodedPathSegments == null");
            }
            return this.addPathSegments(encodedPathSegments, true);
        }

        private Builder addPathSegments(String pathSegments, boolean alreadyEncoded) {
            int segmentEnd;
            int offset = 0;
            do {
                boolean addTrailingSlash = (segmentEnd = Util.delimiterOffset(pathSegments, offset, pathSegments.length(), "/\\")) < pathSegments.length();
                this.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
            } while ((offset = segmentEnd + 1) <= pathSegments.length());
            return this;
        }

        public Builder setPathSegment(int index, String pathSegment) {
            if (pathSegment == null) {
                throw new NullPointerException("pathSegment == null");
            }
            String canonicalPathSegment = HttpUrl.canonicalize(pathSegment, 0, pathSegment.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, false, true, null);
            if (this.isDot(canonicalPathSegment) || this.isDotDot(canonicalPathSegment)) {
                throw new IllegalArgumentException("unexpected path segment: " + pathSegment);
            }
            this.encodedPathSegments.set(index, canonicalPathSegment);
            return this;
        }

        public Builder setEncodedPathSegment(int index, String encodedPathSegment) {
            if (encodedPathSegment == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            String canonicalPathSegment = HttpUrl.canonicalize(encodedPathSegment, 0, encodedPathSegment.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, false, true, null);
            this.encodedPathSegments.set(index, canonicalPathSegment);
            if (this.isDot(canonicalPathSegment) || this.isDotDot(canonicalPathSegment)) {
                throw new IllegalArgumentException("unexpected path segment: " + encodedPathSegment);
            }
            return this;
        }

        public Builder removePathSegment(int index) {
            this.encodedPathSegments.remove(index);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
            }
            return this;
        }

        public Builder encodedPath(String encodedPath) {
            if (encodedPath == null) {
                throw new NullPointerException("encodedPath == null");
            }
            if (!encodedPath.startsWith("/")) {
                throw new IllegalArgumentException("unexpected encodedPath: " + encodedPath);
            }
            this.resolvePath(encodedPath, 0, encodedPath.length());
            return this;
        }

        public Builder query(@Nullable String query) {
            this.encodedQueryNamesAndValues = query != null ? HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(query, HttpUrl.QUERY_ENCODE_SET, false, false, true, true)) : null;
            return this;
        }

        public Builder encodedQuery(@Nullable String encodedQuery) {
            this.encodedQueryNamesAndValues = encodedQuery != null ? HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(encodedQuery, HttpUrl.QUERY_ENCODE_SET, true, false, true, true)) : null;
            return this;
        }

        public Builder addQueryParameter(String name, @Nullable String value) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList<String>();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(name, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
            this.encodedQueryNamesAndValues.add(value != null ? HttpUrl.canonicalize(value, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true) : null);
            return this;
        }

        public Builder addEncodedQueryParameter(String encodedName, @Nullable String encodedValue) {
            if (encodedName == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList<String>();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(encodedName, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, true));
            this.encodedQueryNamesAndValues.add(encodedValue != null ? HttpUrl.canonicalize(encodedValue, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, true) : null);
            return this;
        }

        public Builder setQueryParameter(String name, @Nullable String value) {
            this.removeAllQueryParameters(name);
            this.addQueryParameter(name, value);
            return this;
        }

        public Builder setEncodedQueryParameter(String encodedName, @Nullable String encodedValue) {
            this.removeAllEncodedQueryParameters(encodedName);
            this.addEncodedQueryParameter(encodedName, encodedValue);
            return this;
        }

        public Builder removeAllQueryParameters(String name) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            String nameToRemove = HttpUrl.canonicalize(name, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true);
            this.removeAllCanonicalQueryParameters(nameToRemove);
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String encodedName) {
            if (encodedName == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(HttpUrl.canonicalize(encodedName, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, true));
            return this;
        }

        private void removeAllCanonicalQueryParameters(String canonicalName) {
            for (int i2 = this.encodedQueryNamesAndValues.size() - 2; i2 >= 0; i2 -= 2) {
                if (!canonicalName.equals(this.encodedQueryNamesAndValues.get(i2))) continue;
                this.encodedQueryNamesAndValues.remove(i2 + 1);
                this.encodedQueryNamesAndValues.remove(i2);
                if (!this.encodedQueryNamesAndValues.isEmpty()) continue;
                this.encodedQueryNamesAndValues = null;
                return;
            }
        }

        public Builder fragment(@Nullable String fragment) {
            this.encodedFragment = fragment != null ? HttpUrl.canonicalize(fragment, HttpUrl.FRAGMENT_ENCODE_SET, false, false, false, false) : null;
            return this;
        }

        public Builder encodedFragment(@Nullable String encodedFragment) {
            this.encodedFragment = encodedFragment != null ? HttpUrl.canonicalize(encodedFragment, HttpUrl.FRAGMENT_ENCODE_SET, true, false, false, false) : null;
            return this;
        }

        Builder reencodeForUri() {
            int i2;
            int size = this.encodedPathSegments.size();
            for (i2 = 0; i2 < size; ++i2) {
                String pathSegment = this.encodedPathSegments.get(i2);
                this.encodedPathSegments.set(i2, HttpUrl.canonicalize(pathSegment, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, true));
            }
            if (this.encodedQueryNamesAndValues != null) {
                size = this.encodedQueryNamesAndValues.size();
                for (i2 = 0; i2 < size; ++i2) {
                    String component = this.encodedQueryNamesAndValues.get(i2);
                    if (component == null) continue;
                    this.encodedQueryNamesAndValues.set(i2, HttpUrl.canonicalize(component, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, true));
                }
            }
            if (this.encodedFragment != null) {
                this.encodedFragment = HttpUrl.canonicalize(this.encodedFragment, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, false);
            }
            return this;
        }

        public HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.host == null) {
                throw new IllegalStateException("host == null");
            }
            return new HttpUrl(this);
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            if (this.scheme != null) {
                result.append(this.scheme);
                result.append("://");
            } else {
                result.append("//");
            }
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                result.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    result.append(':');
                    result.append(this.encodedPassword);
                }
                result.append('@');
            }
            if (this.host != null) {
                if (this.host.indexOf(58) != -1) {
                    result.append('[');
                    result.append(this.host);
                    result.append(']');
                } else {
                    result.append(this.host);
                }
            }
            if (this.port != -1 || this.scheme != null) {
                int effectivePort = this.effectivePort();
                if (this.scheme == null || effectivePort != HttpUrl.defaultPort(this.scheme)) {
                    result.append(':');
                    result.append(effectivePort);
                }
            }
            HttpUrl.pathSegmentsToString(result, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                result.append('?');
                HttpUrl.namesAndValuesToQueryString(result, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                result.append('#');
                result.append(this.encodedFragment);
            }
            return result.toString();
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        Builder parse(@Nullable HttpUrl base, String input) {
            int limit;
            int pos;
            block19: {
                pos = Util.skipLeadingAsciiWhitespace(input, 0, input.length());
                int schemeDelimiterOffset = Builder.schemeDelimiterOffset(input, pos, limit = Util.skipTrailingAsciiWhitespace(input, pos, input.length()));
                if (schemeDelimiterOffset != -1) {
                    if (input.regionMatches(true, pos, "https:", 0, 6)) {
                        this.scheme = "https";
                        pos += "https:".length();
                    } else {
                        if (!input.regionMatches(true, pos, "http:", 0, 5)) throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + input.substring(0, schemeDelimiterOffset) + "'");
                        this.scheme = "http";
                        pos += "http:".length();
                    }
                } else {
                    if (base == null) throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
                    this.scheme = base.scheme;
                }
                boolean hasUsername = false;
                boolean hasPassword = false;
                int slashCount = Builder.slashCount(input, pos, limit);
                if (slashCount >= 2 || base == null || !base.scheme.equals(this.scheme)) {
                    pos += slashCount;
                    while (true) {
                        int componentDelimiterOffset;
                        int c2 = (componentDelimiterOffset = Util.delimiterOffset(input, pos, limit, "@/\\?#")) != limit ? (int)input.charAt(componentDelimiterOffset) : -1;
                        switch (c2) {
                            case 64: {
                                if (!hasPassword) {
                                    int passwordColonOffset = Util.delimiterOffset(input, pos, componentDelimiterOffset, ':');
                                    String canonicalUsername = HttpUrl.canonicalize(input, pos, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                    String string = this.encodedUsername = hasUsername ? this.encodedUsername + "%40" + canonicalUsername : canonicalUsername;
                                    if (passwordColonOffset != componentDelimiterOffset) {
                                        hasPassword = true;
                                        this.encodedPassword = HttpUrl.canonicalize(input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                    }
                                    hasUsername = true;
                                } else {
                                    this.encodedPassword = this.encodedPassword + "%40" + HttpUrl.canonicalize(input, pos, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                }
                                pos = componentDelimiterOffset + 1;
                                break;
                            }
                            case -1: 
                            case 35: 
                            case 47: 
                            case 63: 
                            case 92: {
                                int portColonOffset = Builder.portColonOffset(input, pos, componentDelimiterOffset);
                                if (portColonOffset + 1 < componentDelimiterOffset) {
                                    this.host = Builder.canonicalizeHost(input, pos, portColonOffset);
                                    this.port = Builder.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                                    if (this.port == -1) {
                                        throw new IllegalArgumentException("Invalid URL port: \"" + input.substring(portColonOffset + 1, componentDelimiterOffset) + '\"');
                                    }
                                } else {
                                    this.host = Builder.canonicalizeHost(input, pos, portColonOffset);
                                    this.port = HttpUrl.defaultPort(this.scheme);
                                }
                                if (this.host == null) {
                                    throw new IllegalArgumentException("Invalid URL host: \"" + input.substring(pos, portColonOffset) + '\"');
                                }
                                pos = componentDelimiterOffset;
                                break block19;
                            }
                        }
                    }
                }
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host;
                this.port = base.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(base.encodedPathSegments());
                if (pos == limit || input.charAt(pos) == '#') {
                    this.encodedQuery(base.encodedQuery());
                }
            }
            int pathDelimiterOffset = Util.delimiterOffset(input, pos, limit, "?#");
            this.resolvePath(input, pos, pathDelimiterOffset);
            pos = pathDelimiterOffset;
            if (pos < limit && input.charAt(pos) == '?') {
                int queryDelimiterOffset = Util.delimiterOffset(input, pos, limit, '#');
                this.encodedQueryNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(input, pos + 1, queryDelimiterOffset, HttpUrl.QUERY_ENCODE_SET, true, false, true, true, null));
                pos = queryDelimiterOffset;
            }
            if (pos >= limit || input.charAt(pos) != '#') return this;
            this.encodedFragment = HttpUrl.canonicalize(input, pos + 1, limit, HttpUrl.FRAGMENT_ENCODE_SET, true, false, false, false, null);
            return this;
        }

        private void resolvePath(String input, int pos, int limit) {
            if (pos == limit) {
                return;
            }
            char c2 = input.charAt(pos);
            if (c2 == '/' || c2 == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
                ++pos;
            } else {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, HttpUrl.FRAGMENT_ENCODE_SET);
            }
            int i2 = pos;
            while (i2 < limit) {
                int pathSegmentDelimiterOffset = Util.delimiterOffset(input, i2, limit, "/\\");
                boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                this.push(input, i2, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                i2 = pathSegmentDelimiterOffset;
                if (!segmentHasTrailingSlash) continue;
                ++i2;
            }
        }

        private void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = HttpUrl.canonicalize(input, pos, limit, HttpUrl.PATH_SEGMENT_ENCODE_SET, alreadyEncoded, false, false, true, null);
            if (this.isDot(segment)) {
                return;
            }
            if (this.isDotDot(segment)) {
                this.pop();
                return;
            }
            if (this.encodedPathSegments.get(this.encodedPathSegments.size() - 1).isEmpty()) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
            } else {
                this.encodedPathSegments.add(segment);
            }
            if (addTrailingSlash) {
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
            }
        }

        private boolean isDot(String input) {
            return input.equals(".") || input.equalsIgnoreCase("%2e");
        }

        private boolean isDotDot(String input) {
            return input.equals("..") || input.equalsIgnoreCase("%2e.") || input.equalsIgnoreCase(".%2e") || input.equalsIgnoreCase("%2e%2e");
        }

        private void pop() {
            String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
            if (removed.isEmpty() && !this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, HttpUrl.FRAGMENT_ENCODE_SET);
            } else {
                this.encodedPathSegments.add(HttpUrl.FRAGMENT_ENCODE_SET);
            }
        }

        private static int schemeDelimiterOffset(String input, int pos, int limit) {
            if (limit - pos < 2) {
                return -1;
            }
            char c0 = input.charAt(pos);
            if (!(c0 >= 'a' && c0 <= 'z' || c0 >= 'A' && c0 <= 'Z')) {
                return -1;
            }
            for (int i2 = pos + 1; i2 < limit; ++i2) {
                char c2 = input.charAt(i2);
                if (c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z' || c2 >= '0' && c2 <= '9' || c2 == '+' || c2 == '-' || c2 == '.') continue;
                if (c2 == ':') {
                    return i2;
                }
                return -1;
            }
            return -1;
        }

        private static int slashCount(String input, int pos, int limit) {
            char c2;
            int slashCount = 0;
            while (pos < limit && ((c2 = input.charAt(pos)) == '\\' || c2 == '/')) {
                ++slashCount;
                ++pos;
            }
            return slashCount;
        }

        private static int portColonOffset(String input, int pos, int limit) {
            block4: for (int i2 = pos; i2 < limit; ++i2) {
                switch (input.charAt(i2)) {
                    case '[': {
                        while (++i2 < limit && input.charAt(i2) != ']') {
                        }
                        continue block4;
                    }
                    case ':': {
                        return i2;
                    }
                }
            }
            return limit;
        }

        @Nullable
        private static String canonicalizeHost(String input, int pos, int limit) {
            String percentDecoded = HttpUrl.percentDecode(input, pos, limit, false);
            return Util.canonicalizeHost(percentDecoded);
        }

        private static int parsePort(String input, int pos, int limit) {
            try {
                String portString = HttpUrl.canonicalize(input, pos, limit, HttpUrl.FRAGMENT_ENCODE_SET, false, false, false, true, null);
                int i2 = Integer.parseInt(portString);
                if (i2 > 0 && i2 <= 65535) {
                    return i2;
                }
                return -1;
            }
            catch (NumberFormatException e2) {
                return -1;
            }
        }
    }
}

