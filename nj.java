/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class nj {
    public static final ListeningExecutorService a = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool(new ThreadFactoryBuilder().setDaemon(true).setNameFormat("Downloader %d").build()));
    private static final AtomicInteger b = new AtomicInteger(0);
    private static final Logger c = LogManager.getLogger();

    public static String a(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('&');
            }
            try {
                stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
            if (entry.getValue() == null) continue;
            stringBuilder.append('=');
            try {
                stringBuilder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static String a(URL uRL, Map<String, Object> map, boolean bl2) {
        return nj.a(uRL, nj.a(map), bl2);
    }

    private static String a(URL uRL2, String string, boolean bl2) {
        try {
            URL uRL2;
            Proxy proxy = proxy2 = MinecraftServer.N() == null ? null : MinecraftServer.N().ay();
            if (proxy2 == null) {
                Proxy proxy2 = Proxy.NO_PROXY;
            }
            HttpURLConnection \u26032 = (HttpURLConnection)uRL2.openConnection(proxy2);
            \u26032.setRequestMethod("POST");
            \u26032.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            \u26032.setRequestProperty("Content-Length", "" + string.getBytes().length);
            \u26032.setRequestProperty("Content-Language", "en-US");
            \u26032.setUseCaches(false);
            \u26032.setDoInput(true);
            \u26032.setDoOutput(true);
            DataOutputStream \u26033 = new DataOutputStream(\u26032.getOutputStream());
            \u26033.writeBytes(string);
            \u26033.flush();
            \u26033.close();
            BufferedReader \u26034 = new BufferedReader(new InputStreamReader(\u26032.getInputStream()));
            StringBuffer \u26035 = new StringBuffer();
            while ((\u2603 = \u26034.readLine()) != null) {
                \u26035.append(\u2603);
                \u26035.append('\r');
            }
            \u26034.close();
            return \u26035.toString();
        }
        catch (Exception exception) {
            if (!bl2) {
                c.error("Could not post to " + uRL2, (Throwable)exception);
            }
            return "";
        }
    }

    public static ListenableFuture<Object> a(final File file, final String string, final Map<String, String> map, final int n2, final nu nu2, final Proxy proxy) {
        ListenableFuture<Object> listenableFuture = a.submit(new Runnable(){

            /*
             * Exception decompiling
             */
            @Override
            public void run() {
                /*
                 * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                 * 
                 * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 10[WHILELOOP]
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
                 *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
                 *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
                 *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
                 *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
                 *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
                 *     at org.benf.cfr.reader.Main.main(Main.java:54)
                 */
                throw new IllegalStateException("Decompilation failed");
            }
        });
        return listenableFuture;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int a() throws IOException {
        ServerSocket serverSocket = null;
        int \u26032 = -1;
        try {
            serverSocket = new ServerSocket(0);
            \u26032 = serverSocket.getLocalPort();
        }
        finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }
            catch (IOException iOException) {}
        }
        return \u26032;
    }

    public static String a(URL uRL) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
        httpURLConnection.setRequestMethod("GET");
        BufferedReader \u26032 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder \u26033 = new StringBuilder();
        while ((\u2603 = \u26032.readLine()) != null) {
            \u26033.append(\u2603);
            \u26033.append('\r');
        }
        \u26032.close();
        return \u26033.toString();
    }

    static /* synthetic */ Logger b() {
        return c;
    }
}

