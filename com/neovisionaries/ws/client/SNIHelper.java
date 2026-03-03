/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Misc;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class SNIHelper {
    private static Constructor<?> sSNIHostNameConstructor;
    private static Method sSetServerNamesMethod;

    SNIHelper() {
    }

    private static void initialize() throws Exception {
        sSNIHostNameConstructor = Misc.getConstructor("javax.net.ssl.SNIHostName", new Class[]{String.class});
        sSetServerNamesMethod = Misc.getMethod("javax.net.ssl.SSLParameters", "setServerNames", new Class[]{List.class});
    }

    private static Object createSNIHostName(String hostname) {
        return Misc.newInstance(sSNIHostNameConstructor, hostname);
    }

    private static List<Object> createSNIHostNames(String[] hostnames) {
        ArrayList<Object> list = new ArrayList<Object>(hostnames.length);
        for (String hostname : hostnames) {
            list.add(SNIHelper.createSNIHostName(hostname));
        }
        return list;
    }

    private static void setServerNames(SSLParameters parameters, String[] hostnames) {
        Misc.invoke(sSetServerNamesMethod, parameters, SNIHelper.createSNIHostNames(hostnames));
    }

    static void setServerNames(Socket socket, String[] hostnames) {
        if (!(socket instanceof SSLSocket)) {
            return;
        }
        if (hostnames == null) {
            return;
        }
        int androidSDKVersion = SNIHelper.getAndroidSDKVersion();
        if (androidSDKVersion > 0 && androidSDKVersion < 24) {
            try {
                Method method = socket.getClass().getMethod("setHostname", String.class);
                method.invoke((Object)socket, hostnames[0]);
            }
            catch (Exception e2) {
                System.err.println("SNI configuration failed: " + e2.getMessage());
            }
            return;
        }
        SSLParameters parameters = ((SSLSocket)socket).getSSLParameters();
        if (parameters == null) {
            return;
        }
        SNIHelper.setServerNames(parameters, hostnames);
    }

    public static int getAndroidSDKVersion() {
        try {
            return Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt(null);
        }
        catch (Exception ex2) {
            try {
                return Integer.parseInt((String)Class.forName("android.os.Build$VERSION").getField("SDK").get(null));
            }
            catch (Exception ex1) {
                return 0;
            }
        }
    }

    static {
        try {
            SNIHelper.initialize();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

