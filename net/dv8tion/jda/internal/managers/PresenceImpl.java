/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.managers;

import java.util.Collections;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class PresenceImpl
implements Presence {
    private final JDAImpl api;
    private boolean idle = false;
    private Activity activity = null;
    private OnlineStatus status = OnlineStatus.ONLINE;

    public PresenceImpl(JDAImpl jda) {
        this.api = jda;
    }

    @Override
    @Nonnull
    public JDA getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public OnlineStatus getStatus() {
        return this.status;
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }

    @Override
    public boolean isIdle() {
        return this.idle;
    }

    @Override
    public void setStatus(OnlineStatus status) {
        this.setPresence(status, this.activity, this.idle);
    }

    @Override
    public void setActivity(Activity game) {
        this.setPresence(this.status, game);
    }

    @Override
    public void setIdle(boolean idle) {
        this.setPresence(this.status, idle);
    }

    @Override
    public void setPresence(OnlineStatus status, Activity activity, boolean idle) {
        Checks.check(status != OnlineStatus.UNKNOWN, "Cannot set the presence status to an unknown OnlineStatus!");
        if (status == OnlineStatus.OFFLINE || status == null) {
            status = OnlineStatus.INVISIBLE;
        }
        this.idle = idle;
        this.status = status;
        this.activity = activity;
        this.update();
    }

    @Override
    public void setPresence(OnlineStatus status, Activity activity) {
        this.setPresence(status, activity, this.idle);
    }

    @Override
    public void setPresence(OnlineStatus status, boolean idle) {
        this.setPresence(status, this.activity, idle);
    }

    @Override
    public void setPresence(Activity game, boolean idle) {
        this.setPresence(this.status, game, idle);
    }

    public PresenceImpl setCacheStatus(OnlineStatus status) {
        if (status == null) {
            throw new NullPointerException("Null OnlineStatus is not allowed.");
        }
        if (status == OnlineStatus.OFFLINE) {
            status = OnlineStatus.INVISIBLE;
        }
        this.status = status;
        return this;
    }

    public PresenceImpl setCacheActivity(Activity game) {
        this.activity = game;
        return this;
    }

    public PresenceImpl setCacheIdle(boolean idle) {
        this.idle = idle;
        return this;
    }

    public DataObject getFullPresence() {
        DataObject activity = this.getGameJson(this.activity);
        return DataObject.empty().put("afk", this.idle).put("since", System.currentTimeMillis()).put("activities", DataArray.fromCollection(activity == null ? Collections.emptyList() : Collections.singletonList(activity))).put("status", this.getStatus().getKey());
    }

    private DataObject getGameJson(Activity activity) {
        if (activity == null || activity.getName() == null || activity.getType() == null) {
            return null;
        }
        DataObject gameObj = DataObject.empty();
        gameObj.put("name", activity.getName());
        gameObj.put("type", activity.getType().getKey());
        if (activity.getUrl() != null) {
            gameObj.put("url", activity.getUrl());
        }
        return gameObj;
    }

    protected void update() {
        DataObject data = this.getFullPresence();
        JDA.Status status = this.api.getStatus();
        if (status == JDA.Status.RECONNECT_QUEUED || status == JDA.Status.SHUTDOWN || status == JDA.Status.SHUTTING_DOWN) {
            return;
        }
        this.api.getClient().send(DataObject.empty().put("d", data).put("op", 3));
    }
}

