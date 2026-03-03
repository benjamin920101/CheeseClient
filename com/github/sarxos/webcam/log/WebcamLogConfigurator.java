/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ch.qos.logback.classic.LoggerContext
 *  ch.qos.logback.classic.joran.JoranConfigurator
 *  ch.qos.logback.core.Context
 */
package com.github.sarxos.webcam.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamLogConfigurator {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamLogConfigurator.class);

    public static void configure(InputStream is2) {
        ClassLoader cl2 = Thread.currentThread().getContextClassLoader();
        try {
            String[] names;
            for (String name : names = new String[]{"ch.qos.logback.classic.LoggerContext", "ch.qos.logback.classic.joran.JoranConfigurator"}) {
                Class.forName(name, false, cl2);
            }
            LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext((Context)context);
            context.reset();
            configurator.doConfigure(is2);
        }
        catch (ClassNotFoundException e2) {
            System.err.println("WLogC: Logback JARs are missing in classpath");
        }
        catch (NoClassDefFoundError e3) {
            System.err.println("WLogC: Logback JARs are missing in classpath");
        }
        catch (Throwable e4) {
            e4.printStackTrace();
        }
    }

    public static void configure(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            WebcamLogConfigurator.configure(fis);
        }
        catch (FileNotFoundException e2) {
            LOG.error("File not found " + file, e2);
            e2.printStackTrace();
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e3) {
                    LOG.error("Cannot close file " + file, e3);
                    e3.printStackTrace();
                }
            }
        }
    }

    public static void configure(String file) {
        WebcamLogConfigurator.configure(new File(file));
    }
}

