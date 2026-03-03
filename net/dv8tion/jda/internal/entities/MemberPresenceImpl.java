/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ClientType;

public class MemberPresenceImpl {
    private List<Activity> activities = Collections.emptyList();
    private EnumMap<ClientType, OnlineStatus> clientStatus;
    private OnlineStatus status = OnlineStatus.OFFLINE;

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setClientStatus(EnumMap<ClientType, OnlineStatus> clientStatus) {
        this.clientStatus = clientStatus;
    }

    public void setOnlineStatus(OnlineStatus status) {
        this.status = status;
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public EnumMap<ClientType, OnlineStatus> getClientStatus() {
        if (this.clientStatus == null) {
            return new EnumMap<ClientType, OnlineStatus>(ClientType.class);
        }
        return this.clientStatus;
    }

    public OnlineStatus getOnlineStatus() {
        return this.status;
    }

    public void setOnlineStatus(ClientType type, OnlineStatus clientStatus) {
        if (this.clientStatus == null) {
            if (clientStatus == null || clientStatus == OnlineStatus.OFFLINE) {
                return;
            }
            this.clientStatus = new EnumMap(ClientType.class);
        }
        if (clientStatus == OnlineStatus.OFFLINE) {
            this.clientStatus.remove((Object)type);
        } else {
            this.clientStatus.put(type, clientStatus);
        }
    }
}

