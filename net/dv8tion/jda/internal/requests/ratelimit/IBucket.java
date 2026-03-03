/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.ratelimit;

import java.util.Queue;
import net.dv8tion.jda.api.requests.Request;

public interface IBucket {
    public Queue<Request> getRequests();
}

