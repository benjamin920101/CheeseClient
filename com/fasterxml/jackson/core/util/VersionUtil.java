/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

public class VersionUtil {
    private static final Pattern V_SEP = Pattern.compile("[-_./;:]");

    protected VersionUtil() {
    }

    @Deprecated
    public Version version() {
        return Version.unknownVersion();
    }

    public static Version versionFor(Class<?> cls) {
        Version version = VersionUtil.packageVersionFor(cls);
        return version == null ? Version.unknownVersion() : version;
    }

    public static Version packageVersionFor(Class<?> cls) {
        Version v2 = null;
        try {
            String versionInfoClassName = cls.getPackage().getName() + ".PackageVersion";
            Class<?> vClass = Class.forName(versionInfoClassName, true, cls.getClassLoader());
            try {
                v2 = ((Versioned)vClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0])).version();
            }
            catch (Exception e2) {
                throw new IllegalArgumentException("Failed to get Versioned out of " + vClass);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return v2 == null ? Version.unknownVersion() : v2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public static Version mavenVersionFor(ClassLoader cl2, String groupId, String artifactId) {
        InputStream pomProperties = cl2.getResourceAsStream("META-INF/maven/" + groupId.replaceAll("\\.", "/") + "/" + artifactId + "/pom.properties");
        if (pomProperties != null) {
            try {
                Properties props = new Properties();
                props.load(pomProperties);
                String versionStr = props.getProperty("version");
                String pomPropertiesArtifactId = props.getProperty("artifactId");
                String pomPropertiesGroupId = props.getProperty("groupId");
                Version version = VersionUtil.parseVersion(versionStr, pomPropertiesGroupId, pomPropertiesArtifactId);
                return version;
            }
            catch (IOException iOException) {
            }
            finally {
                VersionUtil._close(pomProperties);
            }
        }
        return Version.unknownVersion();
    }

    public static Version parseVersion(String s2, String groupId, String artifactId) {
        if (s2 != null && (s2 = s2.trim()).length() > 0) {
            String[] parts = V_SEP.split(s2);
            return new Version(VersionUtil.parseVersionPart(parts[0]), parts.length > 1 ? VersionUtil.parseVersionPart(parts[1]) : 0, parts.length > 2 ? VersionUtil.parseVersionPart(parts[2]) : 0, parts.length > 3 ? parts[3] : null, groupId, artifactId);
        }
        return Version.unknownVersion();
    }

    protected static int parseVersionPart(String s2) {
        char c2;
        int number = 0;
        int len = s2.length();
        for (int i2 = 0; i2 < len && (c2 = s2.charAt(i2)) <= '9' && c2 >= '0'; ++i2) {
            number = number * 10 + (c2 - 48);
        }
        return number;
    }

    private static final void _close(Closeable c2) {
        try {
            c2.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}

