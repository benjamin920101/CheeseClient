/*
 * Decompiled with CFR 0.152.
 */
import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kg
extends PrintStream {
    private static final Logger a = LogManager.getLogger();
    private final String b;

    public kg(String string, OutputStream outputStream) {
        super(outputStream);
        this.b = string;
    }

    @Override
    public void println(String string) {
        this.a(string);
    }

    @Override
    public void println(Object object) {
        this.a(String.valueOf(object));
    }

    private void a(String string) {
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        StackTraceElement \u26032 = stackTraceElementArray[Math.min(3, stackTraceElementArray.length)];
        a.info("[{}]@.({}:{}): {}", this.b, \u26032.getFileName(), \u26032.getLineNumber(), string);
    }
}

