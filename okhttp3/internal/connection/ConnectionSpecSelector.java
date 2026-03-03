/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;

public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> connectionSpecs;
    private int nextModeIndex = 0;
    private boolean isFallbackPossible;
    private boolean isFallback;

    public ConnectionSpecSelector(List<ConnectionSpec> connectionSpecs) {
        this.connectionSpecs = connectionSpecs;
    }

    public ConnectionSpec configureSecureSocket(SSLSocket sslSocket) throws IOException {
        ConnectionSpec tlsConfiguration = null;
        int size = this.connectionSpecs.size();
        for (int i2 = this.nextModeIndex; i2 < size; ++i2) {
            ConnectionSpec connectionSpec = this.connectionSpecs.get(i2);
            if (!connectionSpec.isCompatible(sslSocket)) continue;
            tlsConfiguration = connectionSpec;
            this.nextModeIndex = i2 + 1;
            break;
        }
        if (tlsConfiguration == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.isFallback + ", modes=" + this.connectionSpecs + ", supported protocols=" + Arrays.toString(sslSocket.getEnabledProtocols()));
        }
        this.isFallbackPossible = this.isFallbackPossible(sslSocket);
        Internal.instance.apply(tlsConfiguration, sslSocket, this.isFallback);
        return tlsConfiguration;
    }

    public boolean connectionFailed(IOException e2) {
        this.isFallback = true;
        if (!this.isFallbackPossible) {
            return false;
        }
        if (e2 instanceof ProtocolException) {
            return false;
        }
        if (e2 instanceof InterruptedIOException) {
            return false;
        }
        if (e2 instanceof SSLHandshakeException && e2.getCause() instanceof CertificateException) {
            return false;
        }
        if (e2 instanceof SSLPeerUnverifiedException) {
            return false;
        }
        return e2 instanceof SSLException;
    }

    private boolean isFallbackPossible(SSLSocket socket) {
        for (int i2 = this.nextModeIndex; i2 < this.connectionSpecs.size(); ++i2) {
            if (!this.connectionSpecs.get(i2).isCompatible(socket)) continue;
            return true;
        }
        return false;
    }
}

