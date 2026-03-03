/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.PayloadGenerator;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFrame;
import java.util.Timer;
import java.util.TimerTask;

abstract class PeriodicalFrameSender {
    private final WebSocket mWebSocket;
    private String mTimerName;
    private Timer mTimer;
    private boolean mScheduled;
    private long mInterval;
    private PayloadGenerator mGenerator;

    public PeriodicalFrameSender(WebSocket webSocket, String timerName, PayloadGenerator generator) {
        this.mWebSocket = webSocket;
        this.mTimerName = timerName;
        this.mGenerator = generator;
    }

    public void start() {
        this.setInterval(this.getInterval());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void stop() {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            if (this.mTimer == null) {
                return;
            }
            this.mScheduled = false;
            this.mTimer.cancel();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long getInterval() {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            return this.mInterval;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setInterval(long interval) {
        if (interval < 0L) {
            interval = 0L;
        }
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            this.mInterval = interval;
        }
        if (interval == 0L) {
            return;
        }
        if (!this.mWebSocket.isOpen()) {
            return;
        }
        periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            if (this.mTimer == null) {
                this.mTimer = this.mTimerName == null ? new Timer() : new Timer(this.mTimerName);
            }
            if (!this.mScheduled) {
                this.mScheduled = PeriodicalFrameSender.schedule(this.mTimer, new Task(), interval);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PayloadGenerator getPayloadGenerator() {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            return this.mGenerator;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setPayloadGenerator(PayloadGenerator generator) {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            this.mGenerator = generator;
        }
    }

    public String getTimerName() {
        return this.mTimerName;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setTimerName(String timerName) {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            this.mTimerName = timerName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doTask() {
        PeriodicalFrameSender periodicalFrameSender = this;
        synchronized (periodicalFrameSender) {
            if (this.mInterval == 0L || !this.mWebSocket.isOpen()) {
                this.mScheduled = false;
                return;
            }
            this.mWebSocket.sendFrame(this.createFrame());
            this.mScheduled = PeriodicalFrameSender.schedule(this.mTimer, new Task(), this.mInterval);
        }
    }

    private WebSocketFrame createFrame() {
        byte[] payload = this.generatePayload();
        return this.createFrame(payload);
    }

    private byte[] generatePayload() {
        if (this.mGenerator == null) {
            return null;
        }
        try {
            return this.mGenerator.generate();
        }
        catch (Throwable t2) {
            return null;
        }
    }

    private static boolean schedule(Timer timer, Task task, long interval) {
        try {
            timer.schedule((TimerTask)task, interval);
            return true;
        }
        catch (RuntimeException e2) {
            return false;
        }
    }

    protected abstract WebSocketFrame createFrame(byte[] var1);

    private final class Task
    extends TimerTask {
        private Task() {
        }

        public void run() {
            PeriodicalFrameSender.this.doTask();
        }
    }
}

