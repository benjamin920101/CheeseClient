/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RejectedExecutionException;
import javax.net.ssl.SSLPeerUnverifiedException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.requests.RateLimiter;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.ratelimit.BotRateLimiter;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.MDC;

public class Requester {
    public static final Logger LOG = JDALogger.getLog(Requester.class);
    public static final String DISCORD_API_PREFIX = Helpers.format("https://discord.com/api/v%d/", 8);
    public static final String USER_AGENT = "DiscordBot (https://github.com/DV8FromTheWorld/JDA, " + JDAInfo.VERSION + ")";
    public static final RequestBody EMPTY_BODY = RequestBody.create(null, new byte[0]);
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_OCTET = MediaType.parse("application/octet-stream; charset=utf-8");
    protected final JDAImpl api;
    protected final AuthorizationConfig authConfig;
    private final RateLimiter rateLimiter;
    private final OkHttpClient httpClient;
    private boolean isContextReady = false;
    private ConcurrentMap<String, String> contextMap = null;
    private volatile boolean retryOnTimeout = false;

    public Requester(JDA api2) {
        this(api2, ((JDAImpl)api2).getAuthorizationConfig());
    }

    public Requester(JDA api2, AuthorizationConfig authConfig) {
        if (authConfig == null) {
            throw new NullPointerException("Provided config was null!");
        }
        this.authConfig = authConfig;
        this.api = (JDAImpl)api2;
        this.rateLimiter = new BotRateLimiter(this);
        this.httpClient = this.api.getHttpClient();
    }

    public void setContextReady(boolean ready) {
        this.isContextReady = ready;
    }

    public void setContext() {
        if (!this.isContextReady) {
            return;
        }
        if (this.contextMap == null) {
            this.contextMap = this.api.getContextMap();
        }
        this.contextMap.forEach(MDC::put);
    }

    public JDAImpl getJDA() {
        return this.api;
    }

    public <T> void request(net.dv8tion.jda.api.requests.Request<T> apiRequest) {
        if (this.rateLimiter.isStopped) {
            throw new RejectedExecutionException("The Requester has been stopped! No new requests can be requested!");
        }
        if (apiRequest.shouldQueue()) {
            this.rateLimiter.queueRequest(apiRequest);
        } else {
            this.execute(apiRequest, true);
        }
    }

    private static boolean isRetry(Throwable e2) {
        return e2 instanceof SocketException || e2 instanceof SocketTimeoutException || e2 instanceof SSLPeerUnverifiedException;
    }

    public Long execute(net.dv8tion.jda.api.requests.Request<?> apiRequest) {
        return this.execute(apiRequest, false);
    }

    public Long execute(net.dv8tion.jda.api.requests.Request<?> apiRequest, boolean handleOnRateLimit) {
        return this.execute(apiRequest, false, handleOnRateLimit);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Long execute(net.dv8tion.jda.api.requests.Request<?> apiRequest, boolean retried, boolean handleOnRatelimit) {
        Long l2;
        Route.CompiledRoute route = apiRequest.getRoute();
        Long retryAfter = this.rateLimiter.getRateLimit(route);
        if (retryAfter != null && retryAfter > 0L) {
            if (handleOnRatelimit) {
                apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(retryAfter, Collections.emptySet()));
            }
            return retryAfter;
        }
        Request.Builder builder = new Request.Builder();
        String url = DISCORD_API_PREFIX + route.getCompiledRoute();
        builder.url(url);
        String method = apiRequest.getRoute().getMethod().toString();
        RequestBody body = apiRequest.getBody();
        if (body == null && HttpMethod.requiresRequestBody(method)) {
            body = EMPTY_BODY;
        }
        builder.method(method, body).header("X-RateLimit-Precision", "millisecond").header("user-agent", USER_AGENT).header("accept-encoding", "gzip");
        if (url.startsWith(DISCORD_API_PREFIX)) {
            builder.header("authorization", this.api.getToken());
        }
        if (apiRequest.getHeaders() != null) {
            for (Map.Entry header : apiRequest.getHeaders().entrySet()) {
                builder.addHeader((String)header.getKey(), (String)header.getValue());
            }
        }
        Request request = builder.build();
        LinkedHashSet<String> rays = new LinkedHashSet<String>();
        Response[] responses = new Response[4];
        Response lastResponse = null;
        try {
            LOG.trace("Executing request {} {}", (Object)apiRequest.getRoute().getMethod(), (Object)url);
            int attempt = 0;
            do {
                if (apiRequest.isSkipped()) {
                    l2 = null;
                    return l2;
                }
                Call call = this.httpClient.newCall(request);
                responses[attempt] = lastResponse = call.execute();
                String cfRay = lastResponse.header("CF-RAY");
                if (cfRay != null) {
                    rays.add(cfRay);
                }
                if (lastResponse.code() < 500) break;
                LOG.debug("Requesting {} -> {} returned status {}... retrying (attempt {})", new Object[]{apiRequest.getRoute().getMethod(), url, lastResponse.code(), ++attempt});
                try {
                    Thread.sleep(50 * attempt);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            } while (attempt < 3 && lastResponse.code() >= 500);
            LOG.trace("Finished Request {} {} with code {}", new Object[]{route.getMethod(), lastResponse.request().url(), lastResponse.code()});
            if (lastResponse.code() >= 500) {
                net.dv8tion.jda.api.requests.Response response = new net.dv8tion.jda.api.requests.Response(lastResponse, -1L, rays);
                apiRequest.handleResponse(response);
                Response[] responseArray = null;
                return responseArray;
            }
            retryAfter = this.rateLimiter.handleResponse(route, lastResponse);
            if (!rays.isEmpty()) {
                LOG.debug("Received response with following cf-rays: {}", (Object)rays);
            }
            if (retryAfter == null) {
                apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(lastResponse, -1L, rays));
            } else if (handleOnRatelimit) {
                apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(lastResponse, retryAfter, rays));
            }
            l2 = retryAfter;
            return l2;
        }
        catch (UnknownHostException e2) {
            LOG.error("DNS resolution failed: {}", (Object)e2.getMessage());
            apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(lastResponse, e2, rays));
            l2 = null;
            return l2;
        }
        catch (IOException e3) {
            if (this.retryOnTimeout && !retried && Requester.isRetry(e3)) {
                l2 = this.execute(apiRequest, true, handleOnRatelimit);
                return l2;
            }
            LOG.error("There was an I/O error while executing a REST request: {}", (Object)e3.getMessage());
            apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(lastResponse, e3, rays));
            l2 = null;
            return l2;
        }
        catch (Exception e4) {
            LOG.error("There was an unexpected error while executing a REST request", e4);
            apiRequest.handleResponse(new net.dv8tion.jda.api.requests.Response(lastResponse, e4, rays));
            l2 = null;
            return l2;
        }
        finally {
            for (Response r2 : responses) {
                if (r2 == null) break;
                r2.close();
            }
        }
    }

    private void applyBody(net.dv8tion.jda.api.requests.Request<?> apiRequest, Request.Builder builder) {
        String method = apiRequest.getRoute().getMethod().toString();
        RequestBody body = apiRequest.getBody();
        if (body == null && HttpMethod.requiresRequestBody(method)) {
            body = EMPTY_BODY;
        }
        builder.method(method, body);
    }

    private void applyHeaders(net.dv8tion.jda.api.requests.Request<?> apiRequest, Request.Builder builder, boolean authorized) {
        builder.header("user-agent", USER_AGENT).header("accept-encoding", "gzip").header("x-ratelimit-precision", "millisecond");
        if (authorized) {
            builder.header("authorization", this.authConfig.getToken());
        }
        if (apiRequest.getHeaders() != null) {
            for (Map.Entry header : apiRequest.getHeaders().entrySet()) {
                builder.addHeader((String)header.getKey(), (String)header.getValue());
            }
        }
    }

    public OkHttpClient getHttpClient() {
        return this.httpClient;
    }

    public RateLimiter getRateLimiter() {
        return this.rateLimiter;
    }

    public void setRetryOnTimeout(boolean retryOnTimeout) {
        this.retryOnTimeout = retryOnTimeout;
    }

    public boolean stop() {
        return this.rateLimiter.stop();
    }

    public void shutdown() {
        this.rateLimiter.shutdown();
    }
}

