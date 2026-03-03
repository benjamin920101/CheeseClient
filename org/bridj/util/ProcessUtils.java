/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bridj.BridJ;
import org.bridj.Platform;
import org.bridj.ann.Convention;
import org.bridj.ann.Library;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ProcessUtils {
    public static int getCurrentProcessId() {
        if (Platform.isWindows()) {
            BridJ.register(Kernel32.class);
            return Kernel32.GetCurrentProcessId();
        }
        BridJ.register(LibC.class);
        return LibC.getpid();
    }

    public static String[] computeJavaProcessArgs(Class<?> mainClass, List<?> mainArgs) {
        ArrayList<String> args = new ArrayList<String>();
        args.add(new File(new File(System.getProperty("java.home")), "bin" + File.separator + "java").toString());
        args.add("-cp");
        args.add(System.getProperty("java.class.path"));
        args.add(mainClass.getName());
        for (Object arg2 : mainArgs) {
            args.add(arg2.toString());
        }
        return args.toArray(new String[args.size()]);
    }

    public static Process startJavaProcess(Class<?> mainClass, List<?> mainArgs) throws IOException {
        ProcessBuilder b2 = new ProcessBuilder(new String[0]);
        b2.command(ProcessUtils.computeJavaProcessArgs(mainClass, mainArgs));
        b2.redirectErrorStream(true);
        return b2.start();
    }

    @Library(value="c")
    static class LibC {
        LibC() {
        }

        public static native int getpid();
    }

    @Library(value="kernel32")
    @Convention(value=Convention.Style.StdCall)
    static class Kernel32 {
        Kernel32() {
        }

        public static native int GetCurrentProcessId();
    }
}

