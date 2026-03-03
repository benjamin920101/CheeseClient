/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;

public class WebcamExceptionHandler
implements Thread.UncaughtExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamExceptionHandler.class);
    private static final WebcamExceptionHandler INSTANCE = new WebcamExceptionHandler();

    private WebcamExceptionHandler() {
    }

    @Override
    public void uncaughtException(Thread t2, Throwable e2) {
        ILoggerFactory context = LoggerFactory.getILoggerFactory();
        if (context instanceof NOPLoggerFactory) {
            System.err.println(String.format("Exception in thread %s", t2.getName()));
            e2.printStackTrace();
        } else {
            LOG.error(String.format("Exception in thread %s", t2.getName()), e2);
        }
    }

    public static void handle(Throwable e2) {
        INSTANCE.uncaughtException(Thread.currentThread(), e2);
    }

    public static final WebcamExceptionHandler getInstance() {
        return INSTANCE;
    }
}

