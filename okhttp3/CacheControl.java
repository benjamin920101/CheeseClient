/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    private final boolean noCache;
    private final boolean noStore;
    private final int maxAgeSeconds;
    private final int sMaxAgeSeconds;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final boolean mustRevalidate;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean onlyIfCached;
    private final boolean noTransform;
    private final boolean immutable;
    @Nullable
    String headerValue;

    private CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, @Nullable String headerValue) {
        this.noCache = noCache;
        this.noStore = noStore;
        this.maxAgeSeconds = maxAgeSeconds;
        this.sMaxAgeSeconds = sMaxAgeSeconds;
        this.isPrivate = isPrivate;
        this.isPublic = isPublic;
        this.mustRevalidate = mustRevalidate;
        this.maxStaleSeconds = maxStaleSeconds;
        this.minFreshSeconds = minFreshSeconds;
        this.onlyIfCached = onlyIfCached;
        this.noTransform = noTransform;
        this.immutable = immutable;
        this.headerValue = headerValue;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    public static CacheControl parse(Headers headers) {
        boolean noCache = false;
        boolean noStore = false;
        int maxAgeSeconds = -1;
        int sMaxAgeSeconds = -1;
        boolean isPrivate = false;
        boolean isPublic = false;
        boolean mustRevalidate = false;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean onlyIfCached = false;
        boolean noTransform = false;
        boolean immutable = false;
        boolean canUseHeaderValue = true;
        String headerValue = null;
        int size = headers.size();
        for (int i2 = 0; i2 < size; ++i2) {
            String name = headers.name(i2);
            String value = headers.value(i2);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (headerValue != null) {
                    canUseHeaderValue = false;
                } else {
                    headerValue = value;
                }
            } else {
                if (!name.equalsIgnoreCase("Pragma")) continue;
                canUseHeaderValue = false;
            }
            int pos = 0;
            while (pos < value.length()) {
                String parameter;
                int tokenStart = pos;
                pos = HttpHeaders.skipUntil(value, pos, "=,;");
                String directive = value.substring(tokenStart, pos).trim();
                if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
                    ++pos;
                    parameter = null;
                } else {
                    int parameterStart;
                    ++pos;
                    if ((pos = HttpHeaders.skipWhitespace(value, pos)) < value.length() && value.charAt(pos) == '\"') {
                        parameterStart = ++pos;
                        pos = HttpHeaders.skipUntil(value, pos, "\"");
                        parameter = value.substring(parameterStart, pos);
                        ++pos;
                    } else {
                        parameterStart = pos;
                        pos = HttpHeaders.skipUntil(value, pos, ",;");
                        parameter = value.substring(parameterStart, pos).trim();
                    }
                }
                if ("no-cache".equalsIgnoreCase(directive)) {
                    noCache = true;
                    continue;
                }
                if ("no-store".equalsIgnoreCase(directive)) {
                    noStore = true;
                    continue;
                }
                if ("max-age".equalsIgnoreCase(directive)) {
                    maxAgeSeconds = HttpHeaders.parseSeconds(parameter, -1);
                    continue;
                }
                if ("s-maxage".equalsIgnoreCase(directive)) {
                    sMaxAgeSeconds = HttpHeaders.parseSeconds(parameter, -1);
                    continue;
                }
                if ("private".equalsIgnoreCase(directive)) {
                    isPrivate = true;
                    continue;
                }
                if ("public".equalsIgnoreCase(directive)) {
                    isPublic = true;
                    continue;
                }
                if ("must-revalidate".equalsIgnoreCase(directive)) {
                    mustRevalidate = true;
                    continue;
                }
                if ("max-stale".equalsIgnoreCase(directive)) {
                    maxStaleSeconds = HttpHeaders.parseSeconds(parameter, Integer.MAX_VALUE);
                    continue;
                }
                if ("min-fresh".equalsIgnoreCase(directive)) {
                    minFreshSeconds = HttpHeaders.parseSeconds(parameter, -1);
                    continue;
                }
                if ("only-if-cached".equalsIgnoreCase(directive)) {
                    onlyIfCached = true;
                    continue;
                }
                if ("no-transform".equalsIgnoreCase(directive)) {
                    noTransform = true;
                    continue;
                }
                if (!"immutable".equalsIgnoreCase(directive)) continue;
                immutable = true;
            }
        }
        if (!canUseHeaderValue) {
            headerValue = null;
        }
        return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue);
    }

    public String toString() {
        String result = this.headerValue;
        return result != null ? result : (this.headerValue = this.headerValue());
    }

    private String headerValue() {
        StringBuilder result = new StringBuilder();
        if (this.noCache) {
            result.append("no-cache, ");
        }
        if (this.noStore) {
            result.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            result.append("max-age=").append(this.maxAgeSeconds).append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            result.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
        }
        if (this.isPrivate) {
            result.append("private, ");
        }
        if (this.isPublic) {
            result.append("public, ");
        }
        if (this.mustRevalidate) {
            result.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            result.append("max-stale=").append(this.maxStaleSeconds).append(", ");
        }
        if (this.minFreshSeconds != -1) {
            result.append("min-fresh=").append(this.minFreshSeconds).append(", ");
        }
        if (this.onlyIfCached) {
            result.append("only-if-cached, ");
        }
        if (this.noTransform) {
            result.append("no-transform, ");
        }
        if (this.immutable) {
            result.append("immutable, ");
        }
        if (result.length() == 0) {
            return "";
        }
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }

    public static final class Builder {
        boolean noCache;
        boolean noStore;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean onlyIfCached;
        boolean noTransform;
        boolean immutable;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int maxAge, TimeUnit timeUnit) {
            if (maxAge < 0) {
                throw new IllegalArgumentException("maxAge < 0: " + maxAge);
            }
            long maxAgeSecondsLong = timeUnit.toSeconds(maxAge);
            this.maxAgeSeconds = maxAgeSecondsLong > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)maxAgeSecondsLong;
            return this;
        }

        public Builder maxStale(int maxStale, TimeUnit timeUnit) {
            if (maxStale < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + maxStale);
            }
            long maxStaleSecondsLong = timeUnit.toSeconds(maxStale);
            this.maxStaleSeconds = maxStaleSecondsLong > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)maxStaleSecondsLong;
            return this;
        }

        public Builder minFresh(int minFresh, TimeUnit timeUnit) {
            if (minFresh < 0) {
                throw new IllegalArgumentException("minFresh < 0: " + minFresh);
            }
            long minFreshSecondsLong = timeUnit.toSeconds(minFresh);
            this.minFreshSeconds = minFreshSecondsLong > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)minFreshSecondsLong;
            return this;
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder immutable() {
            this.immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }
}

