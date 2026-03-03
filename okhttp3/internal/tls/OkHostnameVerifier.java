/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.Util;

public final class OkHostnameVerifier
implements HostnameVerifier {
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;

    private OkHostnameVerifier() {
    }

    @Override
    public boolean verify(String host, SSLSession session) {
        try {
            Certificate[] certificates = session.getPeerCertificates();
            return this.verify(host, (X509Certificate)certificates[0]);
        }
        catch (SSLException e2) {
            return false;
        }
    }

    public boolean verify(String host, X509Certificate certificate) {
        return Util.verifyAsIpAddress(host) ? this.verifyIpAddress(host, certificate) : this.verifyHostname(host, certificate);
    }

    private boolean verifyIpAddress(String ipAddress, X509Certificate certificate) {
        List<String> altNames = OkHostnameVerifier.getSubjectAltNames(certificate, 7);
        int size = altNames.size();
        for (int i2 = 0; i2 < size; ++i2) {
            if (!ipAddress.equalsIgnoreCase(altNames.get(i2))) continue;
            return true;
        }
        return false;
    }

    private boolean verifyHostname(String hostname, X509Certificate certificate) {
        hostname = hostname.toLowerCase(Locale.US);
        List<String> altNames = OkHostnameVerifier.getSubjectAltNames(certificate, 2);
        for (String altName : altNames) {
            if (!this.verifyHostname(hostname, altName)) continue;
            return true;
        }
        return false;
    }

    public static List<String> allSubjectAltNames(X509Certificate certificate) {
        List<String> altIpaNames = OkHostnameVerifier.getSubjectAltNames(certificate, 7);
        List<String> altDnsNames = OkHostnameVerifier.getSubjectAltNames(certificate, 2);
        ArrayList<String> result = new ArrayList<String>(altIpaNames.size() + altDnsNames.size());
        result.addAll(altIpaNames);
        result.addAll(altDnsNames);
        return result;
    }

    private static List<String> getSubjectAltNames(X509Certificate certificate, int type) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) {
                return Collections.emptyList();
            }
            for (List<?> subjectAltName : subjectAltNames) {
                String altName;
                Integer altNameType;
                List<?> entry = subjectAltName;
                if (entry == null || entry.size() < 2 || (altNameType = (Integer)entry.get(0)) == null || altNameType != type || (altName = (String)entry.get(1)) == null) continue;
                result.add(altName);
            }
            return result;
        }
        catch (CertificateParsingException e2) {
            return Collections.emptyList();
        }
    }

    public boolean verifyHostname(String hostname, String pattern) {
        if (hostname == null || hostname.length() == 0 || hostname.startsWith(".") || hostname.endsWith("..")) {
            return false;
        }
        if (pattern == null || pattern.length() == 0 || pattern.startsWith(".") || pattern.endsWith("..")) {
            return false;
        }
        if (!hostname.endsWith(".")) {
            hostname = hostname + '.';
        }
        if (!pattern.endsWith(".")) {
            pattern = pattern + '.';
        }
        if (!(pattern = pattern.toLowerCase(Locale.US)).contains("*")) {
            return hostname.equals(pattern);
        }
        if (!pattern.startsWith("*.") || pattern.indexOf(42, 1) != -1) {
            return false;
        }
        if (hostname.length() < pattern.length()) {
            return false;
        }
        if ("*.".equals(pattern)) {
            return false;
        }
        String suffix = pattern.substring(1);
        if (!hostname.endsWith(suffix)) {
            return false;
        }
        int suffixStartIndexInHostname = hostname.length() - suffix.length();
        return suffixStartIndexInHostname <= 0 || hostname.lastIndexOf(46, suffixStartIndexInHostname - 1) == -1;
    }
}

