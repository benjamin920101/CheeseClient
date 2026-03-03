/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.helpers.Util;

class SimpleLogger
extends MarkerIgnoringBase {
    private static final long serialVersionUID = -632788891211436180L;
    private static final String CONFIGURATION_FILE = "Loggerger.properties";
    private static long START_TIME = System.currentTimeMillis();
    private static final Properties SIMPLE_LOGGER_PROPS = new Properties();
    private static final int LOG_LEVEL_TRACE = 0;
    private static final int LOG_LEVEL_DEBUG = 10;
    private static final int LOG_LEVEL_INFO = 20;
    private static final int LOG_LEVEL_WARN = 30;
    private static final int LOG_LEVEL_ERROR = 40;
    private static boolean INITIALIZED = false;
    private static int DEFAULT_LOG_LEVEL = 20;
    private static boolean SHOW_DATE_TIME = false;
    private static String DATE_TIME_FORMAT_STR = null;
    private static DateFormat DATE_FORMATTER = null;
    private static boolean SHOW_THREAD_NAME = true;
    private static boolean SHOW_LOG_NAME = true;
    private static boolean SHOW_SHORT_LOG_NAME = false;
    private static String LOG_FILE = "System.err";
    private static PrintStream TARGET_STREAM = null;
    private static boolean LEVEL_IN_BRACKETS = false;
    private static String WARN_LEVEL_STRING = "WARN";
    public static final String SYSTEM_PREFIX = "org.slf4j.Loggerger.";
    public static final String DEFAULT_LOG_LEVEL_KEY = "org.slf4j.Loggerger.defaultLogLevel";
    public static final String SHOW_DATE_TIME_KEY = "org.slf4j.Loggerger.showDateTime";
    public static final String DATE_TIME_FORMAT_KEY = "org.slf4j.Loggerger.dateTimeFormat";
    public static final String SHOW_THREAD_NAME_KEY = "org.slf4j.Loggerger.showThreadName";
    public static final String SHOW_LOG_NAME_KEY = "org.slf4j.Loggerger.showLogName";
    public static final String SHOW_SHORT_LOG_NAME_KEY = "org.slf4j.Loggerger.showShortLogName";
    public static final String LOG_FILE_KEY = "org.slf4j.Loggerger.logFile";
    public static final String LEVEL_IN_BRACKETS_KEY = "org.slf4j.Loggerger.levelInBrackets";
    public static final String WARN_LEVEL_STRING_KEY = "org.slf4j.Loggerger.warnLevelString";
    public static final String LOG_KEY_PREFIX = "org.slf4j.Loggerger.log.";
    protected int currentLogLevel = 20;
    private transient String shortLogName = null;

    private static String getStringProperty(String name) {
        String prop = null;
        try {
            prop = System.getProperty(name);
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return prop == null ? SIMPLE_LOGGER_PROPS.getProperty(name) : prop;
    }

    private static String getStringProperty(String name, String defaultValue) {
        String prop = SimpleLogger.getStringProperty(name);
        return prop == null ? defaultValue : prop;
    }

    private static boolean getBooleanProperty(String name, boolean defaultValue) {
        String prop = SimpleLogger.getStringProperty(name);
        return prop == null ? defaultValue : "true".equalsIgnoreCase(prop);
    }

    static void init() {
        INITIALIZED = true;
        SimpleLogger.loadProperties();
        String defaultLogLevelString = SimpleLogger.getStringProperty(DEFAULT_LOG_LEVEL_KEY, null);
        if (defaultLogLevelString != null) {
            DEFAULT_LOG_LEVEL = SimpleLogger.stringToLevel(defaultLogLevelString);
        }
        SHOW_LOG_NAME = SimpleLogger.getBooleanProperty(SHOW_LOG_NAME_KEY, SHOW_LOG_NAME);
        SHOW_SHORT_LOG_NAME = SimpleLogger.getBooleanProperty(SHOW_SHORT_LOG_NAME_KEY, SHOW_SHORT_LOG_NAME);
        SHOW_DATE_TIME = SimpleLogger.getBooleanProperty(SHOW_DATE_TIME_KEY, SHOW_DATE_TIME);
        SHOW_THREAD_NAME = SimpleLogger.getBooleanProperty(SHOW_THREAD_NAME_KEY, SHOW_THREAD_NAME);
        DATE_TIME_FORMAT_STR = SimpleLogger.getStringProperty(DATE_TIME_FORMAT_KEY, DATE_TIME_FORMAT_STR);
        LEVEL_IN_BRACKETS = SimpleLogger.getBooleanProperty(LEVEL_IN_BRACKETS_KEY, LEVEL_IN_BRACKETS);
        WARN_LEVEL_STRING = SimpleLogger.getStringProperty(WARN_LEVEL_STRING_KEY, WARN_LEVEL_STRING);
        LOG_FILE = SimpleLogger.getStringProperty(LOG_FILE_KEY, LOG_FILE);
        TARGET_STREAM = SimpleLogger.computeTargetStream(LOG_FILE);
        if (DATE_TIME_FORMAT_STR != null) {
            try {
                DATE_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMAT_STR);
            }
            catch (IllegalArgumentException e2) {
                Util.report("Bad date format in Loggerger.properties; will output relative time", e2);
            }
        }
    }

    private static PrintStream computeTargetStream(String logFile) {
        if ("System.err".equalsIgnoreCase(logFile)) {
            return System.err;
        }
        if ("System.out".equalsIgnoreCase(logFile)) {
            return System.out;
        }
        try {
            FileOutputStream fos = new FileOutputStream(logFile);
            PrintStream printStream = new PrintStream(fos);
            return printStream;
        }
        catch (FileNotFoundException e2) {
            Util.report("Could not open [" + logFile + "]. Defaulting to System.err", e2);
            return System.err;
        }
    }

    private static void loadProperties() {
        InputStream in2 = AccessController.doPrivileged(new PrivilegedAction<InputStream>(){

            @Override
            public InputStream run() {
                ClassLoader threadCL = Thread.currentThread().getContextClassLoader();
                if (threadCL != null) {
                    return threadCL.getResourceAsStream(SimpleLogger.CONFIGURATION_FILE);
                }
                return ClassLoader.getSystemResourceAsStream(SimpleLogger.CONFIGURATION_FILE);
            }
        });
        if (null != in2) {
            try {
                SIMPLE_LOGGER_PROPS.load(in2);
                in2.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    SimpleLogger(String name) {
        if (!INITIALIZED) {
            SimpleLogger.init();
        }
        this.name = name;
        String levelString = this.recursivelyComputeLevelString();
        this.currentLogLevel = levelString != null ? SimpleLogger.stringToLevel(levelString) : DEFAULT_LOG_LEVEL;
    }

    String recursivelyComputeLevelString() {
        String tempName = this.name;
        String levelString = null;
        int indexOfLastDot = tempName.length();
        while (levelString == null && indexOfLastDot > -1) {
            tempName = tempName.substring(0, indexOfLastDot);
            levelString = SimpleLogger.getStringProperty(LOG_KEY_PREFIX + tempName, null);
            indexOfLastDot = String.valueOf(tempName).lastIndexOf(".");
        }
        return levelString;
    }

    private static int stringToLevel(String levelStr) {
        if ("trace".equalsIgnoreCase(levelStr)) {
            return 0;
        }
        if ("debug".equalsIgnoreCase(levelStr)) {
            return 10;
        }
        if ("info".equalsIgnoreCase(levelStr)) {
            return 20;
        }
        if ("warn".equalsIgnoreCase(levelStr)) {
            return 30;
        }
        if ("error".equalsIgnoreCase(levelStr)) {
            return 40;
        }
        return 20;
    }

    private void log(int level, String message, Throwable t2) {
        if (!this.isLevelEnabled(level)) {
            return;
        }
        StringBuilder buf = new StringBuilder(32);
        if (SHOW_DATE_TIME) {
            if (DATE_FORMATTER != null) {
                buf.append(this.getFormattedDate());
                buf.append(' ');
            } else {
                buf.append(System.currentTimeMillis() - START_TIME);
                buf.append(' ');
            }
        }
        if (SHOW_THREAD_NAME) {
            buf.append('[');
            buf.append(Thread.currentThread().getName());
            buf.append("] ");
        }
        if (LEVEL_IN_BRACKETS) {
            buf.append('[');
        }
        switch (level) {
            case 0: {
                buf.append("TRACE");
                break;
            }
            case 10: {
                buf.append("DEBUG");
                break;
            }
            case 20: {
                buf.append("INFO");
                break;
            }
            case 30: {
                buf.append(WARN_LEVEL_STRING);
                break;
            }
            case 40: {
                buf.append("ERROR");
            }
        }
        if (LEVEL_IN_BRACKETS) {
            buf.append(']');
        }
        buf.append(' ');
        if (SHOW_SHORT_LOG_NAME) {
            if (this.shortLogName == null) {
                this.shortLogName = this.computeShortName();
            }
            buf.append(String.valueOf(this.shortLogName)).append(" - ");
        } else if (SHOW_LOG_NAME) {
            buf.append(String.valueOf(this.name)).append(" - ");
        }
        buf.append(message);
        this.write(buf, t2);
    }

    void write(StringBuilder buf, Throwable t2) {
        TARGET_STREAM.println(buf.toString());
        if (t2 != null) {
            t2.printStackTrace(TARGET_STREAM);
        }
        TARGET_STREAM.flush();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String getFormattedDate() {
        String dateText;
        Date now = new Date();
        DateFormat dateFormat = DATE_FORMATTER;
        synchronized (dateFormat) {
            dateText = DATE_FORMATTER.format(now);
        }
        return dateText;
    }

    private String computeShortName() {
        return this.name.substring(this.name.lastIndexOf(".") + 1);
    }

    private void formatAndLog(int level, String format, Object arg1, Object arg2) {
        if (!this.isLevelEnabled(level)) {
            return;
        }
        FormattingTuple tp2 = MessageFormatter.format(format, arg1, arg2);
        this.log(level, tp2.getMessage(), tp2.getThrowable());
    }

    private void formatAndLog(int level, String format, Object ... arguments) {
        if (!this.isLevelEnabled(level)) {
            return;
        }
        FormattingTuple tp2 = MessageFormatter.arrayFormat(format, arguments);
        this.log(level, tp2.getMessage(), tp2.getThrowable());
    }

    protected boolean isLevelEnabled(int logLevel) {
        return logLevel >= this.currentLogLevel;
    }

    @Override
    public boolean isTraceEnabled() {
        return this.isLevelEnabled(0);
    }

    @Override
    public void trace(String msg) {
        this.log(0, msg, null);
    }

    @Override
    public void trace(String format, Object param1) {
        this.formatAndLog(0, format, param1, (Object)null);
    }

    @Override
    public void trace(String format, Object param1, Object param2) {
        this.formatAndLog(0, format, param1, param2);
    }

    @Override
    public void trace(String format, Object ... argArray) {
        this.formatAndLog(0, format, argArray);
    }

    @Override
    public void trace(String msg, Throwable t2) {
        this.log(0, msg, t2);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.isLevelEnabled(10);
    }

    @Override
    public void debug(String msg) {
        this.log(10, msg, null);
    }

    @Override
    public void debug(String format, Object param1) {
        this.formatAndLog(10, format, param1, (Object)null);
    }

    @Override
    public void debug(String format, Object param1, Object param2) {
        this.formatAndLog(10, format, param1, param2);
    }

    @Override
    public void debug(String format, Object ... argArray) {
        this.formatAndLog(10, format, argArray);
    }

    @Override
    public void debug(String msg, Throwable t2) {
        this.log(10, msg, t2);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.isLevelEnabled(20);
    }

    @Override
    public void info(String msg) {
        this.log(20, msg, null);
    }

    @Override
    public void info(String format, Object arg2) {
        this.formatAndLog(20, format, arg2, (Object)null);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        this.formatAndLog(20, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object ... argArray) {
        this.formatAndLog(20, format, argArray);
    }

    @Override
    public void info(String msg, Throwable t2) {
        this.log(20, msg, t2);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.isLevelEnabled(30);
    }

    @Override
    public void warn(String msg) {
        this.log(30, msg, null);
    }

    @Override
    public void warn(String format, Object arg2) {
        this.formatAndLog(30, format, arg2, (Object)null);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        this.formatAndLog(30, format, arg1, arg2);
    }

    @Override
    public void warn(String format, Object ... argArray) {
        this.formatAndLog(30, format, argArray);
    }

    @Override
    public void warn(String msg, Throwable t2) {
        this.log(30, msg, t2);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.isLevelEnabled(40);
    }

    @Override
    public void error(String msg) {
        this.log(40, msg, null);
    }

    @Override
    public void error(String format, Object arg2) {
        this.formatAndLog(40, format, arg2, (Object)null);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        this.formatAndLog(40, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object ... argArray) {
        this.formatAndLog(40, format, argArray);
    }

    @Override
    public void error(String msg, Throwable t2) {
        this.log(40, msg, t2);
    }
}

