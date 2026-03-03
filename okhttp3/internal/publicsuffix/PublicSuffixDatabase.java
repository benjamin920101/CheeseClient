/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

public final class PublicSuffixDatabase {
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = new byte[]{42};
    private static final String[] EMPTY_RULE = new String[0];
    private static final String[] PREVAILING_RULE = new String[]{"*"};
    private static final byte EXCEPTION_MARKER = 33;
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);
    private byte[] publicSuffixListBytes;
    private byte[] publicSuffixExceptionListBytes;

    public static PublicSuffixDatabase get() {
        return instance;
    }

    public String getEffectiveTldPlusOne(String domain) {
        String[] rule;
        if (domain == null) {
            throw new NullPointerException("domain == null");
        }
        String unicodeDomain = IDN.toUnicode(domain);
        String[] domainLabels = unicodeDomain.split("\\.");
        if (domainLabels.length == (rule = this.findMatchingRule(domainLabels)).length && rule[0].charAt(0) != '!') {
            return null;
        }
        int firstLabelOffset = rule[0].charAt(0) == '!' ? domainLabels.length - rule.length : domainLabels.length - (rule.length + 1);
        StringBuilder effectiveTldPlusOne = new StringBuilder();
        String[] punycodeLabels = domain.split("\\.");
        for (int i2 = firstLabelOffset; i2 < punycodeLabels.length; ++i2) {
            effectiveTldPlusOne.append(punycodeLabels[i2]).append('.');
        }
        effectiveTldPlusOne.deleteCharAt(effectiveTldPlusOne.length() - 1);
        return effectiveTldPlusOne.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String[] findMatchingRule(String[] domainLabels) {
        String rule;
        int labelIndex;
        if (!this.listRead.get() && this.listRead.compareAndSet(false, true)) {
            this.readTheListUninterruptibly();
        } else {
            try {
                this.readCompleteLatch.await();
            }
            catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
        PublicSuffixDatabase ignored = this;
        synchronized (ignored) {
            if (this.publicSuffixListBytes == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        byte[][] domainLabelsUtf8Bytes = new byte[domainLabels.length][];
        for (int i2 = 0; i2 < domainLabels.length; ++i2) {
            domainLabelsUtf8Bytes[i2] = domainLabels[i2].getBytes(StandardCharsets.UTF_8);
        }
        String exactMatch = null;
        for (int i3 = 0; i3 < domainLabelsUtf8Bytes.length; ++i3) {
            String rule2 = PublicSuffixDatabase.binarySearchBytes(this.publicSuffixListBytes, domainLabelsUtf8Bytes, i3);
            if (rule2 == null) continue;
            exactMatch = rule2;
            break;
        }
        String wildcardMatch = null;
        if (domainLabelsUtf8Bytes.length > 1) {
            byte[][] labelsWithWildcard = (byte[][])domainLabelsUtf8Bytes.clone();
            for (labelIndex = 0; labelIndex < labelsWithWildcard.length - 1; ++labelIndex) {
                labelsWithWildcard[labelIndex] = WILDCARD_LABEL;
                rule = PublicSuffixDatabase.binarySearchBytes(this.publicSuffixListBytes, labelsWithWildcard, labelIndex);
                if (rule == null) continue;
                wildcardMatch = rule;
                break;
            }
        }
        String exception = null;
        if (wildcardMatch != null) {
            for (labelIndex = 0; labelIndex < domainLabelsUtf8Bytes.length - 1; ++labelIndex) {
                rule = PublicSuffixDatabase.binarySearchBytes(this.publicSuffixExceptionListBytes, domainLabelsUtf8Bytes, labelIndex);
                if (rule == null) continue;
                exception = rule;
                break;
            }
        }
        if (exception != null) {
            exception = "!" + exception;
            return exception.split("\\.");
        }
        if (exactMatch == null && wildcardMatch == null) {
            return PREVAILING_RULE;
        }
        String[] exactRuleLabels = exactMatch != null ? exactMatch.split("\\.") : EMPTY_RULE;
        String[] wildcardRuleLabels = wildcardMatch != null ? wildcardMatch.split("\\.") : EMPTY_RULE;
        return exactRuleLabels.length > wildcardRuleLabels.length ? exactRuleLabels : wildcardRuleLabels;
    }

    private static String binarySearchBytes(byte[] bytesToSearch, byte[][] labels, int labelIndex) {
        int low = 0;
        int high = bytesToSearch.length;
        String match = null;
        while (low < high) {
            int compareResult;
            int mid;
            for (mid = (low + high) / 2; mid > -1 && bytesToSearch[mid] != 10; --mid) {
            }
            ++mid;
            int end = 1;
            while (bytesToSearch[mid + end] != 10) {
                ++end;
            }
            int publicSuffixLength = mid + end - mid;
            int currentLabelIndex = labelIndex;
            int currentLabelByteIndex = 0;
            int publicSuffixByteIndex = 0;
            boolean expectDot = false;
            while (true) {
                int byte0;
                if (expectDot) {
                    byte0 = 46;
                    expectDot = false;
                } else {
                    byte0 = labels[currentLabelIndex][currentLabelByteIndex] & 0xFF;
                }
                int byte1 = bytesToSearch[mid + publicSuffixByteIndex] & 0xFF;
                compareResult = byte0 - byte1;
                if (compareResult != 0) break;
                ++currentLabelByteIndex;
                if (++publicSuffixByteIndex == publicSuffixLength) break;
                if (labels[currentLabelIndex].length != currentLabelByteIndex) continue;
                if (currentLabelIndex == labels.length - 1) break;
                ++currentLabelIndex;
                currentLabelByteIndex = -1;
                expectDot = true;
            }
            if (compareResult < 0) {
                high = mid - 1;
                continue;
            }
            if (compareResult > 0) {
                low = mid + end + 1;
                continue;
            }
            int publicSuffixBytesLeft = publicSuffixLength - publicSuffixByteIndex;
            int labelBytesLeft = labels[currentLabelIndex].length - currentLabelByteIndex;
            for (int i2 = currentLabelIndex + 1; i2 < labels.length; ++i2) {
                labelBytesLeft += labels[i2].length;
            }
            if (labelBytesLeft < publicSuffixBytesLeft) {
                high = mid - 1;
                continue;
            }
            if (labelBytesLeft > publicSuffixBytesLeft) {
                low = mid + end + 1;
                continue;
            }
            match = new String(bytesToSearch, mid, publicSuffixLength, StandardCharsets.UTF_8);
            break;
        }
        return match;
    }

    private void readTheListUninterruptibly() {
        boolean interrupted = false;
        while (true) {
            try {
                this.readTheList();
                return;
            }
            catch (InterruptedIOException e2) {
                Thread.interrupted();
                interrupted = true;
                continue;
            }
            catch (IOException e3) {
                Platform.get().log(5, "Failed to read public suffix list", e3);
                return;
            }
            break;
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void readTheList() throws IOException {
        byte[] publicSuffixExceptionListBytes;
        byte[] publicSuffixListBytes;
        InputStream resource = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (resource == null) {
            return;
        }
        try (BufferedSource bufferedSource = Okio.buffer(new GzipSource(Okio.source(resource)));){
            int totalBytes = bufferedSource.readInt();
            publicSuffixListBytes = new byte[totalBytes];
            bufferedSource.readFully(publicSuffixListBytes);
            int totalExceptionBytes = bufferedSource.readInt();
            publicSuffixExceptionListBytes = new byte[totalExceptionBytes];
            bufferedSource.readFully(publicSuffixExceptionListBytes);
        }
        PublicSuffixDatabase publicSuffixDatabase = this;
        synchronized (publicSuffixDatabase) {
            this.publicSuffixListBytes = publicSuffixListBytes;
            this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
        }
        this.readCompleteLatch.countDown();
    }

    void setListBytes(byte[] publicSuffixListBytes, byte[] publicSuffixExceptionListBytes) {
        this.publicSuffixListBytes = publicSuffixListBytes;
        this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}

