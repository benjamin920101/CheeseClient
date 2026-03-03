/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.ServiceLoader;
import net.dv8tion.jda.internal.utils.SimpleLogger;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDALogger {
    public static final boolean SLF4J_ENABLED;
    private static final Map<String, Logger> LOGS;

    private JDALogger() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Logger getLog(String name) {
        Map<String, Logger> map = LOGS;
        synchronized (map) {
            if (SLF4J_ENABLED) {
                return LoggerFactory.getLogger(name);
            }
            return LOGS.computeIfAbsent(name, SimpleLogger::new);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Logger getLog(Class<?> clazz) {
        Map<String, Logger> map = LOGS;
        synchronized (map) {
            if (SLF4J_ENABLED) {
                return LoggerFactory.getLogger(clazz);
            }
            return LOGS.computeIfAbsent(clazz.getName(), n2 -> new SimpleLogger(clazz.getSimpleName()));
        }
    }

    public static Object getLazyString(final LazyEvaluation lazyLambda) {
        return new Object(){

            public String toString() {
                try {
                    return lazyLambda.getString();
                }
                catch (Exception ex2) {
                    StringWriter sw2 = new StringWriter();
                    ex2.printStackTrace(new PrintWriter(sw2));
                    return "Error while evaluating lazy String... " + sw2.toString();
                }
            }
        };
    }

    static {
        boolean tmp = false;
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            tmp = true;
        }
        catch (ClassNotFoundException eStatic) {
            try {
                Class<?> serviceProviderInterface = Class.forName("org.slf4j.spi.SLF4JServiceProvider");
                tmp = ServiceLoader.load(serviceProviderInterface).iterator().hasNext();
            }
            catch (ClassNotFoundException eService) {
                LoggerFactory.getLogger(JDALogger.class);
                tmp = false;
            }
        }
        SLF4J_ENABLED = tmp;
        LOGS = new CaseInsensitiveMap<String, Logger>();
    }

    @FunctionalInterface
    public static interface LazyEvaluation {
        public String getString() throws Exception;
    }
}

