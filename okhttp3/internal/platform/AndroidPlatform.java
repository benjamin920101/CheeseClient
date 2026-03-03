/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.os.Build$VERSION
 *  android.util.Log
 */
package okhttp3.internal.platform;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;

class AndroidPlatform
extends Platform {
    private static final int MAX_LOG_LENGTH = 4000;
    private final Class<?> sslParametersClass;
    private final Method setUseSessionTickets;
    private final Method setHostname;
    private final Method getAlpnSelectedProtocol;
    private final Method setAlpnProtocols;
    private final CloseGuard closeGuard = CloseGuard.get();

    AndroidPlatform(Class<?> sslParametersClass, Method setUseSessionTickets, Method setHostname, Method getAlpnSelectedProtocol, Method setAlpnProtocols) {
        this.sslParametersClass = sslParametersClass;
        this.setUseSessionTickets = setUseSessionTickets;
        this.setHostname = setHostname;
        this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
        this.setAlpnProtocols = setAlpnProtocols;
    }

    @Override
    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        try {
            socket.connect(address, connectTimeout);
        }
        catch (AssertionError e2) {
            if (Util.isAndroidGetsocknameError(e2)) {
                throw new IOException((Throwable)((Object)e2));
            }
            throw e2;
        }
        catch (ClassCastException e3) {
            if (Build.VERSION.SDK_INT == 26) {
                throw new IOException("Exception in connect", e3);
            }
            throw e3;
        }
    }

    @Override
    @Nullable
    protected X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        X509TrustManager x509TrustManager;
        Object context = AndroidPlatform.readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters");
        if (context == null) {
            try {
                Class<?> gmsSslParametersClass = Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader());
                context = AndroidPlatform.readFieldOrNull(sslSocketFactory, gmsSslParametersClass, "sslParameters");
            }
            catch (ClassNotFoundException e2) {
                return super.trustManager(sslSocketFactory);
            }
        }
        if ((x509TrustManager = AndroidPlatform.readFieldOrNull(context, X509TrustManager.class, "x509TrustManager")) != null) {
            return x509TrustManager;
        }
        return AndroidPlatform.readFieldOrNull(context, X509TrustManager.class, "trustManager");
    }

    @Override
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        try {
            if (hostname != null) {
                this.setUseSessionTickets.invoke((Object)sslSocket, true);
                this.setHostname.invoke((Object)sslSocket, hostname);
            }
            this.setAlpnProtocols.invoke((Object)sslSocket, new Object[]{AndroidPlatform.concatLengthPrefixed(protocols)});
        }
        catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    @Nullable
    public String getSelectedProtocol(SSLSocket socket) {
        try {
            byte[] alpnResult = (byte[])this.getAlpnSelectedProtocol.invoke((Object)socket, new Object[0]);
            return alpnResult != null ? new String(alpnResult, StandardCharsets.UTF_8) : null;
        }
        catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Override
    public void log(int level, String message, @Nullable Throwable t2) {
        int logLevel;
        int n2 = logLevel = level == 5 ? 5 : 3;
        if (t2 != null) {
            message = message + '\n' + Log.getStackTraceString((Throwable)t2);
        }
        int length = message.length();
        for (int i2 = 0; i2 < length; ++i2) {
            int end;
            int newline = message.indexOf(10, i2);
            newline = newline != -1 ? newline : length;
            do {
                end = Math.min(newline, i2 + 4000);
                Log.println((int)logLevel, (String)"OkHttp", (String)message.substring(i2, end));
            } while ((i2 = end) < newline);
        }
    }

    @Override
    public Object getStackTraceForCloseable(String closer) {
        return this.closeGuard.createAndOpen(closer);
    }

    @Override
    public void logCloseableLeak(String message, Object stackTrace) {
        boolean reported = this.closeGuard.warnIfOpen(stackTrace);
        if (!reported) {
            this.log(5, message, null);
        }
    }

    @Override
    public boolean isCleartextTrafficPermitted(String hostname) {
        try {
            Class<?> networkPolicyClass = Class.forName("android.security.NetworkSecurityPolicy");
            Method getInstanceMethod = networkPolicyClass.getMethod("getInstance", new Class[0]);
            Object networkSecurityPolicy = getInstanceMethod.invoke(null, new Object[0]);
            return this.api24IsCleartextTrafficPermitted(hostname, networkPolicyClass, networkSecurityPolicy);
        }
        catch (ClassNotFoundException | NoSuchMethodException e2) {
            return super.isCleartextTrafficPermitted(hostname);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
            throw new AssertionError("unable to determine cleartext support", e3);
        }
    }

    private boolean api24IsCleartextTrafficPermitted(String hostname, Class<?> networkPolicyClass, Object networkSecurityPolicy) throws InvocationTargetException, IllegalAccessException {
        try {
            Method isCleartextTrafficPermittedMethod = networkPolicyClass.getMethod("isCleartextTrafficPermitted", String.class);
            return (Boolean)isCleartextTrafficPermittedMethod.invoke(networkSecurityPolicy, hostname);
        }
        catch (NoSuchMethodException e2) {
            return this.api23IsCleartextTrafficPermitted(hostname, networkPolicyClass, networkSecurityPolicy);
        }
    }

    private boolean api23IsCleartextTrafficPermitted(String hostname, Class<?> networkPolicyClass, Object networkSecurityPolicy) throws InvocationTargetException, IllegalAccessException {
        try {
            Method isCleartextTrafficPermittedMethod = networkPolicyClass.getMethod("isCleartextTrafficPermitted", new Class[0]);
            return (Boolean)isCleartextTrafficPermittedMethod.invoke(networkSecurityPolicy, new Object[0]);
        }
        catch (NoSuchMethodException e2) {
            return super.isCleartextTrafficPermitted(hostname);
        }
    }

    @Override
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager trustManager) {
        try {
            Class<?> extensionsClass = Class.forName("android.net.http.X509TrustManagerExtensions");
            Constructor<?> constructor = extensionsClass.getConstructor(X509TrustManager.class);
            Object extensions = constructor.newInstance(trustManager);
            Method checkServerTrusted = extensionsClass.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class);
            return new AndroidCertificateChainCleaner(extensions, checkServerTrusted);
        }
        catch (Exception e2) {
            throw new AssertionError((Object)e2);
        }
    }

    @Nullable
    public static Platform buildIfSupported() {
        Class<?> sslSocketClass;
        Class<?> sslParametersClass;
        try {
            sslParametersClass = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            sslSocketClass = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
        }
        catch (ClassNotFoundException ignored) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Method setUseSessionTickets = sslSocketClass.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
                Method setHostname = sslSocketClass.getMethod("setHostname", String.class);
                Method getAlpnSelectedProtocol = sslSocketClass.getMethod("getAlpnSelectedProtocol", new Class[0]);
                Method setAlpnProtocols = sslSocketClass.getMethod("setAlpnProtocols", byte[].class);
                return new AndroidPlatform(sslParametersClass, setUseSessionTickets, setHostname, getAlpnSelectedProtocol, setAlpnProtocols);
            }
            catch (NoSuchMethodException noSuchMethodException) {
                // empty catch block
            }
        }
        throw new IllegalStateException("Expected Android API level 21+ but was " + Build.VERSION.SDK_INT);
    }

    @Override
    public SSLContext getSSLContext() {
        boolean tryTls12;
        try {
            tryTls12 = Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22;
        }
        catch (NoClassDefFoundError e2) {
            tryTls12 = true;
        }
        if (tryTls12) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            }
            catch (NoSuchAlgorithmException e2) {
                // empty catch block
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        }
        catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException("No TLS provider", e3);
        }
    }

    static final class CloseGuard {
        private final Method getMethod;
        private final Method openMethod;
        private final Method warnIfOpenMethod;

        CloseGuard(Method getMethod, Method openMethod, Method warnIfOpenMethod) {
            this.getMethod = getMethod;
            this.openMethod = openMethod;
            this.warnIfOpenMethod = warnIfOpenMethod;
        }

        Object createAndOpen(String closer) {
            if (this.getMethod != null) {
                try {
                    Object closeGuardInstance = this.getMethod.invoke(null, new Object[0]);
                    this.openMethod.invoke(closeGuardInstance, closer);
                    return closeGuardInstance;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            return null;
        }

        boolean warnIfOpen(Object closeGuardInstance) {
            boolean reported = false;
            if (closeGuardInstance != null) {
                try {
                    this.warnIfOpenMethod.invoke(closeGuardInstance, new Object[0]);
                    reported = true;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            return reported;
        }

        static CloseGuard get() {
            Method warnIfOpenMethod;
            Method openMethod;
            Method getMethod;
            try {
                Class<?> closeGuardClass = Class.forName("dalvik.system.CloseGuard");
                getMethod = closeGuardClass.getMethod("get", new Class[0]);
                openMethod = closeGuardClass.getMethod("open", String.class);
                warnIfOpenMethod = closeGuardClass.getMethod("warnIfOpen", new Class[0]);
            }
            catch (Exception ignored) {
                getMethod = null;
                openMethod = null;
                warnIfOpenMethod = null;
            }
            return new CloseGuard(getMethod, openMethod, warnIfOpenMethod);
        }
    }

    static final class AndroidCertificateChainCleaner
    extends CertificateChainCleaner {
        private final Object x509TrustManagerExtensions;
        private final Method checkServerTrusted;

        AndroidCertificateChainCleaner(Object x509TrustManagerExtensions, Method checkServerTrusted) {
            this.x509TrustManagerExtensions = x509TrustManagerExtensions;
            this.checkServerTrusted = checkServerTrusted;
        }

        @Override
        public List<Certificate> clean(List<Certificate> chain, String hostname) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] certificates = chain.toArray(new X509Certificate[chain.size()]);
                return (List)this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, certificates, "RSA", hostname);
            }
            catch (InvocationTargetException e2) {
                SSLPeerUnverifiedException exception = new SSLPeerUnverifiedException(e2.getMessage());
                exception.initCause(e2);
                throw exception;
            }
            catch (IllegalAccessException e3) {
                throw new AssertionError((Object)e3);
            }
        }

        public boolean equals(Object other) {
            return other instanceof AndroidCertificateChainCleaner;
        }

        public int hashCode() {
            return 0;
        }
    }
}

