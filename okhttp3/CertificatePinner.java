/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.HttpUrl;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final Set<Pin> pins;
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;

    CertificatePinner(Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        return other instanceof CertificatePinner && Objects.equals(this.certificateChainCleaner, ((CertificatePinner)other).certificateChainCleaner) && this.pins.equals(((CertificatePinner)other).pins);
    }

    public int hashCode() {
        int result = Objects.hashCode(this.certificateChainCleaner);
        result = 31 * result + this.pins.hashCode();
        return result;
    }

    public void check(String hostname, List<Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        List<Pin> pins = this.findMatchingPins(hostname);
        if (pins.isEmpty()) {
            return;
        }
        if (this.certificateChainCleaner != null) {
            peerCertificates = this.certificateChainCleaner.clean(peerCertificates, hostname);
        }
        int certsSize = peerCertificates.size();
        for (int c2 = 0; c2 < certsSize; ++c2) {
            X509Certificate x509Certificate = (X509Certificate)peerCertificates.get(c2);
            ByteString sha1 = null;
            ByteString sha256 = null;
            int pinsSize = pins.size();
            for (int p2 = 0; p2 < pinsSize; ++p2) {
                Pin pin = pins.get(p2);
                if (pin.hashAlgorithm.equals("sha256/")) {
                    if (sha256 == null) {
                        sha256 = CertificatePinner.sha256(x509Certificate);
                    }
                    if (!pin.hash.equals(sha256)) continue;
                    return;
                }
                if (pin.hashAlgorithm.equals("sha1/")) {
                    if (sha1 == null) {
                        sha1 = CertificatePinner.sha1(x509Certificate);
                    }
                    if (!pin.hash.equals(sha1)) continue;
                    return;
                }
                throw new AssertionError((Object)("unsupported hashAlgorithm: " + pin.hashAlgorithm));
            }
        }
        StringBuilder message = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
        int certsSize2 = peerCertificates.size();
        for (int c3 = 0; c3 < certsSize2; ++c3) {
            X509Certificate x509Certificate = (X509Certificate)peerCertificates.get(c3);
            message.append("\n    ").append(CertificatePinner.pin(x509Certificate)).append(": ").append(x509Certificate.getSubjectDN().getName());
        }
        message.append("\n  Pinned certificates for ").append(hostname).append(":");
        int pinsSize = pins.size();
        for (int p3 = 0; p3 < pinsSize; ++p3) {
            Pin pin = pins.get(p3);
            message.append("\n    ").append(pin);
        }
        throw new SSLPeerUnverifiedException(message.toString());
    }

    public void check(String hostname, Certificate ... peerCertificates) throws SSLPeerUnverifiedException {
        this.check(hostname, Arrays.asList(peerCertificates));
    }

    List<Pin> findMatchingPins(String hostname) {
        List<Pin> result = Collections.emptyList();
        for (Pin pin : this.pins) {
            if (!pin.matches(hostname)) continue;
            if (result.isEmpty()) {
                result = new ArrayList<Pin>();
            }
            result.add(pin);
        }
        return result;
    }

    CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner certificateChainCleaner) {
        return Objects.equals(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public static String pin(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + CertificatePinner.sha256((X509Certificate)certificate).base64();
    }

    static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public static final class Builder {
        private final List<Pin> pins = new ArrayList<Pin>();

        public Builder add(String pattern, String ... pins) {
            if (pattern == null) {
                throw new NullPointerException("pattern == null");
            }
            for (String pin : pins) {
                this.pins.add(new Pin(pattern, pin));
            }
            return this;
        }

        public CertificatePinner build() {
            return new CertificatePinner(new LinkedHashSet<Pin>(this.pins), null);
        }
    }

    static final class Pin {
        private static final String WILDCARD = "*.";
        final String pattern;
        final String canonicalHostname;
        final String hashAlgorithm;
        final ByteString hash;

        Pin(String pattern, String pin) {
            this.pattern = pattern;
            String string = this.canonicalHostname = pattern.startsWith(WILDCARD) ? HttpUrl.get("http://" + pattern.substring(WILDCARD.length())).host() : HttpUrl.get("http://" + pattern).host();
            if (pin.startsWith("sha1/")) {
                this.hashAlgorithm = "sha1/";
                this.hash = ByteString.decodeBase64(pin.substring("sha1/".length()));
            } else if (pin.startsWith("sha256/")) {
                this.hashAlgorithm = "sha256/";
                this.hash = ByteString.decodeBase64(pin.substring("sha256/".length()));
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
            }
            if (this.hash == null) {
                throw new IllegalArgumentException("pins must be base64: " + pin);
            }
        }

        boolean matches(String hostname) {
            if (this.pattern.startsWith(WILDCARD)) {
                int firstDot = hostname.indexOf(46);
                return hostname.length() - firstDot - 1 == this.canonicalHostname.length() && hostname.regionMatches(false, firstDot + 1, this.canonicalHostname, 0, this.canonicalHostname.length());
            }
            return hostname.equals(this.canonicalHostname);
        }

        public boolean equals(Object other) {
            return other instanceof Pin && this.pattern.equals(((Pin)other).pattern) && this.hashAlgorithm.equals(((Pin)other).hashAlgorithm) && this.hash.equals(((Pin)other).hash);
        }

        public int hashCode() {
            int result = 17;
            result = 31 * result + this.pattern.hashCode();
            result = 31 * result + this.hashAlgorithm.hashCode();
            result = 31 * result + this.hash.hashCode();
            return result;
        }

        public String toString() {
            return this.hashAlgorithm + this.hash.base64();
        }
    }
}

