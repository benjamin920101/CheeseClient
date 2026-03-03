/*
 * Decompiled with CFR 0.152.
 */
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class x
extends i {
    private static final Logger a = LogManager.getLogger();
    private long b;
    private int c;

    @Override
    public String c() {
        return "debug";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.debug.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1) {
            throw new cf("commands.debug.usage", new Object[0]);
        }
        if (stringArray[0].equals("start")) {
            if (stringArray.length != 1) {
                throw new cf("commands.debug.usage", new Object[0]);
            }
            x.a(m2, (k)this, "commands.debug.start", new Object[0]);
            MinecraftServer.N().au();
            this.b = MinecraftServer.az();
            this.c = MinecraftServer.N().at();
        } else if (stringArray[0].equals("stop")) {
            if (stringArray.length != 1) {
                throw new cf("commands.debug.usage", new Object[0]);
            }
            if (!MinecraftServer.N().c.a) {
                throw new bz("commands.debug.notStarted", new Object[0]);
            }
            long l2 = MinecraftServer.az();
            int \u26032 = MinecraftServer.N().at();
            \u2603 = l2 - this.b;
            int \u26033 = \u26032 - this.c;
            this.a(\u2603, \u26033);
            MinecraftServer.N().c.a = false;
            x.a(m2, (k)this, "commands.debug.stop", Float.valueOf((float)\u2603 / 1000.0f), \u26033);
        } else {
            throw new cf("commands.debug.usage", new Object[0]);
        }
    }

    private void a(long l2, int n2) {
        File file = new File(MinecraftServer.N().d("debug"), "profile-results-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + ".txt");
        file.getParentFile().mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(this.b(l2, n2));
            fileWriter.close();
        }
        catch (Throwable throwable) {
            a.error("Could not save profiler results to " + file, throwable);
        }
    }

    private String b(long l2, int n2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---- Minecraft Profiler Results ----\n");
        stringBuilder.append("// ");
        stringBuilder.append(x.d());
        stringBuilder.append("\n\n");
        stringBuilder.append("Time span: ").append(l2).append(" ms\n");
        stringBuilder.append("Tick span: ").append(n2).append(" ticks\n");
        stringBuilder.append("// This is approximately ").append(String.format("%.2f", Float.valueOf((float)n2 / ((float)l2 / 1000.0f)))).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
        stringBuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        this.a(0, "root", stringBuilder);
        stringBuilder.append("--- END PROFILE DUMP ---\n\n");
        return stringBuilder.toString();
    }

    private void a(int n2, String string, StringBuilder stringBuilder2) {
        List<nt.a> list = MinecraftServer.N().c.b(string);
        if (list == null || list.size() < 3) {
            return;
        }
        for (int i2 = 1; i2 < list.size(); ++i2) {
            StringBuilder stringBuilder2;
            nt.a a2 = list.get(i2);
            stringBuilder2.append(String.format("[%02d] ", n2));
            for (int i3 = 0; i3 < n2; ++i3) {
                stringBuilder2.append(" ");
            }
            stringBuilder2.append(a2.c).append(" - ").append(String.format("%.2f", a2.a)).append("%/").append(String.format("%.2f", a2.b)).append("%\n");
            if (a2.c.equals("unspecified")) continue;
            try {
                this.a(n2 + 1, string + "." + a2.c, stringBuilder2);
                continue;
            }
            catch (Exception \u26032) {
                stringBuilder2.append("[[ EXCEPTION ").append(\u26032).append(" ]]");
            }
        }
    }

    private static String d() {
        String[] stringArray = new String[]{"Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server."};
        try {
            return stringArray[(int)(System.nanoTime() % (long)stringArray.length)];
        }
        catch (Throwable \u26032) {
            return "Witty comment unavailable :(";
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return x.a(stringArray, "start", "stop");
        }
        return null;
    }
}

