/*
 * Decompiled with CFR 0.152.
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class g {
    public static a a() {
        String string = System.getProperty("os.name").toLowerCase();
        if (string.contains("win")) {
            return a.c;
        }
        if (string.contains("mac")) {
            return a.d;
        }
        if (string.contains("solaris")) {
            return a.b;
        }
        if (string.contains("sunos")) {
            return a.b;
        }
        if (string.contains("linux")) {
            return a.a;
        }
        if (string.contains("unix")) {
            return a.a;
        }
        return a.e;
    }

    public static <V> V a(FutureTask<V> futureTask, Logger logger) {
        try {
            futureTask.run();
            return futureTask.get();
        }
        catch (ExecutionException executionException) {
            logger.fatal("Error executing task", (Throwable)executionException);
        }
        catch (InterruptedException interruptedException) {
            logger.fatal("Error executing task", (Throwable)interruptedException);
        }
        return null;
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e;

    }
}

