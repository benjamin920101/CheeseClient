/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.DistinguishedNameParser;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
final class OkHostnameVerifier
implements HostnameVerifier {
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
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
        return OkHostnameVerifier.verifyAsIpAddress(host) ? this.verifyIpAddress(host, certificate) : this.verifyHostName(host, certificate);
    }

    static boolean verifyAsIpAddress(String host) {
        return VERIFY_AS_IP_ADDRESS.matcher(host).matches();
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

    private boolean verifyHostName(String hostName, X509Certificate certificate) {
        X500Principal principal;
        String cn2;
        hostName = hostName.toLowerCase(Locale.US);
        boolean hasDns = false;
        List<String> altNames = OkHostnameVerifier.getSubjectAltNames(certificate, 2);
        int size = altNames.size();
        for (int i2 = 0; i2 < size; ++i2) {
            hasDns = true;
            if (!this.verifyHostName(hostName, altNames.get(i2))) continue;
            return true;
        }
        if (!hasDns && (cn2 = new DistinguishedNameParser(principal = certificate.getSubjectX500Principal()).findMostSpecific("cn")) != null) {
            return this.verifyHostName(hostName, cn2);
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

    private boolean verifyHostName(String hostName, String pattern) {
        if (hostName == null || hostName.length() == 0 || hostName.startsWith(".") || hostName.endsWith("..")) {
            return false;
        }
        if (pattern == null || pattern.length() == 0 || pattern.startsWith(".") || pattern.endsWith("..")) {
            return false;
        }
        if (!hostName.endsWith(".")) {
            hostName = hostName + '.';
        }
        if (!pattern.endsWith(".")) {
            pattern = pattern + '.';
        }
        if (!(pattern = pattern.toLowerCase(Locale.US)).contains("*")) {
            return hostName.equals(pattern);
        }
        if (!pattern.startsWith("*.") || pattern.indexOf(42, 1) != -1) {
            return false;
        }
        if (hostName.length() < pattern.length()) {
            return false;
        }
        if ("*.".equals(pattern)) {
            return false;
        }
        String suffix = pattern.substring(1);
        if (!hostName.endsWith(suffix)) {
            return false;
        }
        int suffixStartIndexInHostName = hostName.length() - suffix.length();
        return suffixStartIndexInHostName <= 0 || hostName.lastIndexOf(46, suffixStartIndexInHostName - 1) == -1;
    }
}

