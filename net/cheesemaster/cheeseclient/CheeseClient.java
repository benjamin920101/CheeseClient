/*
 * Decompiled with CFR 0.152.
 */
package net.cheesemaster.cheeseclient;

public class CheeseClient {
    private static final CheeseClient INSTANCE = new CheeseClient();
    public static String clientName = "CheeseClient";
    public static String clientVersion = "1.8.9 v2.0";
    public static String clientAuthor = "CheeseMaster";

    public static final CheeseClient getInstance() {
        return INSTANCE;
    }

    public static void start() {
        System.out.println("Client was started");
    }

    public static void stop() {
        System.out.println("Client was stopped");
    }
}

