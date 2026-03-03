/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDeallocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

final class WebcamSignalHandler
implements SignalHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamSignalHandler.class);
    private WebcamDeallocator deallocator = null;
    private SignalHandler handler = Signal.handle(new Signal("TERM"), this);

    @Override
    public void handle(Signal signal) {
        LOG.warn("Detected signal {} {}, calling deallocator", (Object)signal.getName(), (Object)signal.getNumber());
        if (this.handler == SIG_DFL || this.handler == SIG_IGN) {
            return;
        }
        try {
            this.deallocator.deallocate();
        }
        finally {
            this.handler.handle(signal);
        }
    }

    public void set(WebcamDeallocator deallocator) {
        this.deallocator = deallocator;
    }

    public WebcamDeallocator get() {
        return this.deallocator;
    }

    public void reset() {
        this.deallocator = null;
    }
}

