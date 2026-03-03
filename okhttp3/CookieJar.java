/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.util.Collections;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public interface CookieJar {
    public static final CookieJar NO_COOKIES = new CookieJar(){

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return Collections.emptyList();
        }
    };

    public void saveFromResponse(HttpUrl var1, List<Cookie> var2);

    public List<Cookie> loadForRequest(HttpUrl var1);
}

