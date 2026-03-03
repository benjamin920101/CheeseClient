/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

public final class Cookie {
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private final String name;
    private final String value;
    private final long expiresAt;
    private final String domain;
    private final String path;
    private final boolean secure;
    private final boolean httpOnly;
    private final boolean persistent;
    private final boolean hostOnly;

    private Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean hostOnly, boolean persistent) {
        this.name = name;
        this.value = value;
        this.expiresAt = expiresAt;
        this.domain = domain;
        this.path = path;
        this.secure = secure;
        this.httpOnly = httpOnly;
        this.hostOnly = hostOnly;
        this.persistent = persistent;
    }

    Cookie(Builder builder) {
        if (builder.name == null) {
            throw new NullPointerException("builder.name == null");
        }
        if (builder.value == null) {
            throw new NullPointerException("builder.value == null");
        }
        if (builder.domain == null) {
            throw new NullPointerException("builder.domain == null");
        }
        this.name = builder.name;
        this.value = builder.value;
        this.expiresAt = builder.expiresAt;
        this.domain = builder.domain;
        this.path = builder.path;
        this.secure = builder.secure;
        this.httpOnly = builder.httpOnly;
        this.persistent = builder.persistent;
        this.hostOnly = builder.hostOnly;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public String domain() {
        return this.domain;
    }

    public String path() {
        return this.path;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public boolean secure() {
        return this.secure;
    }

    public boolean matches(HttpUrl url) {
        boolean domainMatch;
        boolean bl2 = domainMatch = this.hostOnly ? url.host().equals(this.domain) : Cookie.domainMatch(url.host(), this.domain);
        if (!domainMatch) {
            return false;
        }
        if (!Cookie.pathMatch(url, this.path)) {
            return false;
        }
        return !this.secure || url.isHttps();
    }

    private static boolean domainMatch(String urlHost, String domain) {
        if (urlHost.equals(domain)) {
            return true;
        }
        return urlHost.endsWith(domain) && urlHost.charAt(urlHost.length() - domain.length() - 1) == '.' && !Util.verifyAsIpAddress(urlHost);
    }

    private static boolean pathMatch(HttpUrl url, String path) {
        String urlPath = url.encodedPath();
        if (urlPath.equals(path)) {
            return true;
        }
        if (urlPath.startsWith(path)) {
            if (path.endsWith("/")) {
                return true;
            }
            if (urlPath.charAt(path.length()) == '/') {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static Cookie parse(HttpUrl url, String setCookie) {
        return Cookie.parse(System.currentTimeMillis(), url, setCookie);
    }

    @Nullable
    static Cookie parse(long currentTimeMillis, HttpUrl url, String setCookie) {
        long deltaMilliseconds;
        int pos = 0;
        int limit = setCookie.length();
        int cookiePairEnd = Util.delimiterOffset(setCookie, pos, limit, ';');
        int pairEqualsSign = Util.delimiterOffset(setCookie, pos, cookiePairEnd, '=');
        if (pairEqualsSign == cookiePairEnd) {
            return null;
        }
        String cookieName = Util.trimSubstring(setCookie, pos, pairEqualsSign);
        if (cookieName.isEmpty() || Util.indexOfControlOrNonAscii(cookieName) != -1) {
            return null;
        }
        String cookieValue = Util.trimSubstring(setCookie, pairEqualsSign + 1, cookiePairEnd);
        if (Util.indexOfControlOrNonAscii(cookieValue) != -1) {
            return null;
        }
        long expiresAt = 253402300799999L;
        long deltaSeconds = -1L;
        String domain = null;
        String path = null;
        boolean secureOnly = false;
        boolean httpOnly = false;
        boolean hostOnly = true;
        boolean persistent = false;
        pos = cookiePairEnd + 1;
        while (pos < limit) {
            String attributeValue;
            int attributePairEnd = Util.delimiterOffset(setCookie, pos, limit, ';');
            int attributeEqualsSign = Util.delimiterOffset(setCookie, pos, attributePairEnd, '=');
            String attributeName = Util.trimSubstring(setCookie, pos, attributeEqualsSign);
            String string = attributeValue = attributeEqualsSign < attributePairEnd ? Util.trimSubstring(setCookie, attributeEqualsSign + 1, attributePairEnd) : "";
            if (attributeName.equalsIgnoreCase("expires")) {
                try {
                    expiresAt = Cookie.parseExpires(attributeValue, 0, attributeValue.length());
                    persistent = true;
                }
                catch (IllegalArgumentException illegalArgumentException) {}
            } else if (attributeName.equalsIgnoreCase("max-age")) {
                try {
                    deltaSeconds = Cookie.parseMaxAge(attributeValue);
                    persistent = true;
                }
                catch (NumberFormatException numberFormatException) {}
            } else if (attributeName.equalsIgnoreCase("domain")) {
                try {
                    domain = Cookie.parseDomain(attributeValue);
                    hostOnly = false;
                }
                catch (IllegalArgumentException illegalArgumentException) {}
            } else if (attributeName.equalsIgnoreCase("path")) {
                path = attributeValue;
            } else if (attributeName.equalsIgnoreCase("secure")) {
                secureOnly = true;
            } else if (attributeName.equalsIgnoreCase("httponly")) {
                httpOnly = true;
            }
            pos = attributePairEnd + 1;
        }
        if (deltaSeconds == Long.MIN_VALUE) {
            expiresAt = Long.MIN_VALUE;
        } else if (deltaSeconds != -1L && ((expiresAt = currentTimeMillis + (deltaMilliseconds = deltaSeconds <= 9223372036854775L ? deltaSeconds * 1000L : Long.MAX_VALUE)) < currentTimeMillis || expiresAt > 253402300799999L)) {
            expiresAt = 253402300799999L;
        }
        String urlHost = url.host();
        if (domain == null) {
            domain = urlHost;
        } else if (!Cookie.domainMatch(urlHost, domain)) {
            return null;
        }
        if (urlHost.length() != domain.length() && PublicSuffixDatabase.get().getEffectiveTldPlusOne(domain) == null) {
            return null;
        }
        if (path == null || !path.startsWith("/")) {
            String encodedPath = url.encodedPath();
            int lastSlash = encodedPath.lastIndexOf(47);
            path = lastSlash != 0 ? encodedPath.substring(0, lastSlash) : "/";
        }
        return new Cookie(cookieName, cookieValue, expiresAt, domain, path, secureOnly, httpOnly, hostOnly, persistent);
    }

    private static long parseExpires(String s2, int pos, int limit) {
        pos = Cookie.dateCharacterOffset(s2, pos, limit, false);
        int hour = -1;
        int minute = -1;
        int second = -1;
        int dayOfMonth = -1;
        int month = -1;
        int year = -1;
        Matcher matcher = TIME_PATTERN.matcher(s2);
        while (pos < limit) {
            int end = Cookie.dateCharacterOffset(s2, pos + 1, limit, true);
            matcher.region(pos, end);
            if (hour == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                hour = Integer.parseInt(matcher.group(1));
                minute = Integer.parseInt(matcher.group(2));
                second = Integer.parseInt(matcher.group(3));
            } else if (dayOfMonth == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                dayOfMonth = Integer.parseInt(matcher.group(1));
            } else if (month == -1 && matcher.usePattern(MONTH_PATTERN).matches()) {
                String monthString = matcher.group(1).toLowerCase(Locale.US);
                month = MONTH_PATTERN.pattern().indexOf(monthString) / 4;
            } else if (year == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                year = Integer.parseInt(matcher.group(1));
            }
            pos = Cookie.dateCharacterOffset(s2, end + 1, limit, false);
        }
        if (year >= 70 && year <= 99) {
            year += 1900;
        }
        if (year >= 0 && year <= 69) {
            year += 2000;
        }
        if (year < 1601) {
            throw new IllegalArgumentException();
        }
        if (month == -1) {
            throw new IllegalArgumentException();
        }
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException();
        }
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException();
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException();
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException();
        }
        GregorianCalendar calendar = new GregorianCalendar(Util.UTC);
        calendar.setLenient(false);
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, dayOfMonth);
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private static int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
        for (int i2 = pos; i2 < limit; ++i2) {
            char c2 = input.charAt(i2);
            boolean dateCharacter = c2 < ' ' && c2 != '\t' || c2 >= '\u007f' || c2 >= '0' && c2 <= '9' || c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z' || c2 == ':';
            if (dateCharacter != !invert) continue;
            return i2;
        }
        return limit;
    }

    private static long parseMaxAge(String s2) {
        try {
            long parsed = Long.parseLong(s2);
            return parsed <= 0L ? Long.MIN_VALUE : parsed;
        }
        catch (NumberFormatException e2) {
            if (s2.matches("-?\\d+")) {
                return s2.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e2;
        }
    }

    private static String parseDomain(String s2) {
        String canonicalDomain;
        if (s2.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (s2.startsWith(".")) {
            s2 = s2.substring(1);
        }
        if ((canonicalDomain = Util.canonicalizeHost(s2)) == null) {
            throw new IllegalArgumentException();
        }
        return canonicalDomain;
    }

    public static List<Cookie> parseAll(HttpUrl url, Headers headers) {
        List<String> cookieStrings = headers.values("Set-Cookie");
        ArrayList<Cookie> cookies = null;
        int size = cookieStrings.size();
        for (int i2 = 0; i2 < size; ++i2) {
            Cookie cookie = Cookie.parse(url, cookieStrings.get(i2));
            if (cookie == null) continue;
            if (cookies == null) {
                cookies = new ArrayList<Cookie>();
            }
            cookies.add(cookie);
        }
        return cookies != null ? Collections.unmodifiableList(cookies) : Collections.emptyList();
    }

    public String toString() {
        return this.toString(false);
    }

    String toString(boolean forObsoleteRfc2965) {
        StringBuilder result = new StringBuilder();
        result.append(this.name);
        result.append('=');
        result.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                result.append("; max-age=0");
            } else {
                result.append("; expires=").append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            result.append("; domain=");
            if (forObsoleteRfc2965) {
                result.append(".");
            }
            result.append(this.domain);
        }
        result.append("; path=").append(this.path);
        if (this.secure) {
            result.append("; secure");
        }
        if (this.httpOnly) {
            result.append("; httponly");
        }
        return result.toString();
    }

    public boolean equals(@Nullable Object other) {
        if (!(other instanceof Cookie)) {
            return false;
        }
        Cookie that = (Cookie)other;
        return that.name.equals(this.name) && that.value.equals(this.value) && that.domain.equals(this.domain) && that.path.equals(this.path) && that.expiresAt == this.expiresAt && that.secure == this.secure && that.httpOnly == this.httpOnly && that.persistent == this.persistent && that.hostOnly == this.hostOnly;
    }

    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + this.name.hashCode();
        hash = 31 * hash + this.value.hashCode();
        hash = 31 * hash + this.domain.hashCode();
        hash = 31 * hash + this.path.hashCode();
        hash = 31 * hash + (int)(this.expiresAt ^ this.expiresAt >>> 32);
        hash = 31 * hash + (this.secure ? 0 : 1);
        hash = 31 * hash + (this.httpOnly ? 0 : 1);
        hash = 31 * hash + (this.persistent ? 0 : 1);
        hash = 31 * hash + (this.hostOnly ? 0 : 1);
        return hash;
    }

    public static final class Builder {
        @Nullable
        String name;
        @Nullable
        String value;
        long expiresAt = 253402300799999L;
        @Nullable
        String domain;
        String path = "/";
        boolean secure;
        boolean httpOnly;
        boolean persistent;
        boolean hostOnly;

        public Builder name(String name) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            if (!name.trim().equals(name)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.name = name;
            return this;
        }

        public Builder value(String value) {
            if (value == null) {
                throw new NullPointerException("value == null");
            }
            if (!value.trim().equals(value)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.value = value;
            return this;
        }

        public Builder expiresAt(long expiresAt) {
            if (expiresAt <= 0L) {
                expiresAt = Long.MIN_VALUE;
            }
            if (expiresAt > 253402300799999L) {
                expiresAt = 253402300799999L;
            }
            this.expiresAt = expiresAt;
            this.persistent = true;
            return this;
        }

        public Builder domain(String domain) {
            return this.domain(domain, false);
        }

        public Builder hostOnlyDomain(String domain) {
            return this.domain(domain, true);
        }

        private Builder domain(String domain, boolean hostOnly) {
            if (domain == null) {
                throw new NullPointerException("domain == null");
            }
            String canonicalDomain = Util.canonicalizeHost(domain);
            if (canonicalDomain == null) {
                throw new IllegalArgumentException("unexpected domain: " + domain);
            }
            this.domain = canonicalDomain;
            this.hostOnly = hostOnly;
            return this;
        }

        public Builder path(String path) {
            if (!path.startsWith("/")) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.path = path;
            return this;
        }

        public Builder secure() {
            this.secure = true;
            return this;
        }

        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public Cookie build() {
            return new Cookie(this);
        }
    }
}

