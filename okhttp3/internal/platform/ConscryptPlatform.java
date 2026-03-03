/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.conscrypt.Conscrypt
 */
package okhttp3.internal.platform;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import org.conscrypt.Conscrypt;

public class ConscryptPlatform
extends Platform {
    private ConscryptPlatform() {
    }

    private Provider getProvider() {
        return Conscrypt.newProviderBuilder().provideTrustManager().build();
    }

    @Override
    @Nullable
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        if (!Conscrypt.isConscrypt((SSLSocketFactory)sslSocketFactory)) {
            return super.trustManager(sslSocketFactory);
        }
        try {
            Object sp2 = ConscryptPlatform.readFieldOrNull(sslSocketFactory, Object.class, "sslParameters");
            if (sp2 != null) {
                return ConscryptPlatform.readFieldOrNull(sp2, X509TrustManager.class, "x509TrustManager");
            }
            return null;
        }
        catch (Exception e2) {
            throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on Conscrypt", e2);
        }
    }

    @Override
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        if (Conscrypt.isConscrypt((SSLSocket)sslSocket)) {
            if (hostname != null) {
                Conscrypt.setUseSessionTickets((SSLSocket)sslSocket, (boolean)true);
                Conscrypt.setHostname((SSLSocket)sslSocket, (String)hostname);
            }
            List<String> names = Platform.alpnProtocolNames(protocols);
            Conscrypt.setApplicationProtocols((SSLSocket)sslSocket, (String[])names.toArray(new String[0]));
        } else {
            super.configureTlsExtensions(sslSocket, hostname, protocols);
        }
    }

    @Override
    @Nullable
    public String getSelectedProtocol(SSLSocket sslSocket) {
        if (Conscrypt.isConscrypt((SSLSocket)sslSocket)) {
            return Conscrypt.getApplicationProtocol((SSLSocket)sslSocket);
        }
        return super.getSelectedProtocol(sslSocket);
    }

    @Override
    public SSLContext getSSLContext() {
        try {
            return SSLContext.getInstance("TLSv1.3", this.getProvider());
        }
        catch (NoSuchAlgorithmException e2) {
            try {
                return SSLContext.getInstance("TLS", this.getProvider());
            }
            catch (NoSuchAlgorithmException e22) {
                throw new IllegalStateException("No TLS provider", e2);
            }
        }
    }

    public static ConscryptPlatform buildIfSupported() {
        try {
            Class.forName("org.conscrypt.Conscrypt");
            if (!Conscrypt.isAvailable()) {
                return null;
            }
            return new ConscryptPlatform();
        }
        catch (ClassNotFoundException e2) {
            return null;
        }
    }

    @Override
    public void configureSslSocketFactory(SSLSocketFactory socketFactory) {
        if (Conscrypt.isConscrypt((SSLSocketFactory)socketFactory)) {
            Conscrypt.setUseEngineSocket((SSLSocketFactory)socketFactory, (boolean)true);
        }
    }
}

