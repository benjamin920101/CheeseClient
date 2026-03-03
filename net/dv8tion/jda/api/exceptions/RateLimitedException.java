/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Helpers;

public class RateLimitedException
extends Exception {
    private final String rateLimitedRoute;
    private final long retryAfter;

    public RateLimitedException(Route.CompiledRoute route, long retryAfter) {
        this(route.getBaseRoute().getRoute() + ":" + route.getMajorParameters(), retryAfter);
    }

    public RateLimitedException(String route, long retryAfter) {
        super(Helpers.format("The request was ratelimited! Retry-After: %d  Route: %s", retryAfter, route));
        this.rateLimitedRoute = route;
        this.retryAfter = retryAfter;
    }

    public String getRateLimitedRoute() {
        return this.rateLimitedRoute;
    }

    public long getRetryAfter() {
        return this.retryAfter;
    }
}

