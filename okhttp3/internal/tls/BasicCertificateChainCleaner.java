/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.tls;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.auth.x500.X500Principal;
import okhttp3.internal.tls.CertificateChainCleaner;

public final class BasicCertificateChainCleaner
extends CertificateChainCleaner {
    private static final int MAX_SIGNERS = 9;
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts = new LinkedHashMap<X500Principal, Set<X509Certificate>>();

    public BasicCertificateChainCleaner(X509Certificate ... caCerts) {
        for (X509Certificate caCert : caCerts) {
            X500Principal subject = caCert.getSubjectX500Principal();
            Set<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(subject);
            if (subjectCaCerts == null) {
                subjectCaCerts = new LinkedHashSet<X509Certificate>(1);
                this.subjectToCaCerts.put(subject, subjectCaCerts);
            }
            subjectCaCerts.add(caCert);
        }
    }

    @Override
    public List<Certificate> clean(List<Certificate> chain, String hostname) throws SSLPeerUnverifiedException {
        ArrayDeque<Certificate> queue = new ArrayDeque<Certificate>(chain);
        ArrayList<Certificate> result = new ArrayList<Certificate>();
        result.add((Certificate)queue.removeFirst());
        boolean foundTrustedCertificate = false;
        block0: for (int c2 = 0; c2 < 9; ++c2) {
            X509Certificate toVerify = (X509Certificate)result.get(result.size() - 1);
            X509Certificate trustedCert = this.findByIssuerAndSignature(toVerify);
            if (trustedCert != null) {
                if (result.size() > 1 || !toVerify.equals(trustedCert)) {
                    result.add(trustedCert);
                }
                if (this.verifySignature(trustedCert, trustedCert)) {
                    return result;
                }
                foundTrustedCertificate = true;
                continue;
            }
            Iterator i2 = queue.iterator();
            while (i2.hasNext()) {
                X509Certificate signingCert = (X509Certificate)i2.next();
                if (!this.verifySignature(toVerify, signingCert)) continue;
                i2.remove();
                result.add(signingCert);
                continue block0;
            }
            if (foundTrustedCertificate) {
                return result;
            }
            throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + toVerify);
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + result);
    }

    private boolean verifySignature(X509Certificate toVerify, X509Certificate signingCert) {
        if (!toVerify.getIssuerDN().equals(signingCert.getSubjectDN())) {
            return false;
        }
        try {
            toVerify.verify(signingCert.getPublicKey());
            return true;
        }
        catch (GeneralSecurityException verifyFailed) {
            return false;
        }
    }

    private X509Certificate findByIssuerAndSignature(X509Certificate cert) {
        X500Principal issuer = cert.getIssuerX500Principal();
        Set<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(issuer);
        if (subjectCaCerts == null) {
            return null;
        }
        for (X509Certificate caCert : subjectCaCerts) {
            PublicKey publicKey = caCert.getPublicKey();
            try {
                cert.verify(publicKey);
                return caCert;
            }
            catch (Exception exception) {
            }
        }
        return null;
    }

    public int hashCode() {
        return this.subjectToCaCerts.hashCode();
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return other instanceof BasicCertificateChainCleaner && ((BasicCertificateChainCleaner)other).subjectToCaCerts.equals(this.subjectToCaCerts);
    }
}

