/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okio.Base64;
import okio.Buffer;
import okio.Util;

public class ByteString
implements Serializable,
Comparable<ByteString> {
    static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final long serialVersionUID = 1L;
    public static final ByteString EMPTY = ByteString.of(new byte[0]);
    final byte[] data;
    transient int hashCode;
    transient String utf8;

    ByteString(byte[] data) {
        this.data = data;
    }

    public static ByteString of(byte ... data) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new ByteString((byte[])data.clone());
    }

    public static ByteString of(byte[] data, int offset, int byteCount) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }
        Util.checkOffsetAndCount(data.length, offset, byteCount);
        byte[] copy = new byte[byteCount];
        System.arraycopy(data, offset, copy, 0, byteCount);
        return new ByteString(copy);
    }

    public static ByteString of(ByteBuffer data) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }
        byte[] copy = new byte[data.remaining()];
        data.get(copy);
        return new ByteString(copy);
    }

    public static ByteString encodeUtf8(String s2) {
        if (s2 == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(s2.getBytes(Util.UTF_8));
        byteString.utf8 = s2;
        return byteString;
    }

    public static ByteString encodeString(String s2, Charset charset) {
        if (s2 == null) {
            throw new IllegalArgumentException("s == null");
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return new ByteString(s2.getBytes(charset));
    }

    public String utf8() {
        String result = this.utf8;
        return result != null ? result : (this.utf8 = new String(this.data, Util.UTF_8));
    }

    public String string(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return new String(this.data, charset);
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public ByteString md5() {
        return this.digest("MD5");
    }

    public ByteString sha1() {
        return this.digest("SHA-1");
    }

    public ByteString sha256() {
        return this.digest("SHA-256");
    }

    public ByteString sha512() {
        return this.digest("SHA-512");
    }

    private ByteString digest(String algorithm) {
        try {
            return ByteString.of(MessageDigest.getInstance(algorithm).digest(this.data));
        }
        catch (NoSuchAlgorithmException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    public ByteString hmacSha1(ByteString key) {
        return this.hmac("HmacSHA1", key);
    }

    public ByteString hmacSha256(ByteString key) {
        return this.hmac("HmacSHA256", key);
    }

    public ByteString hmacSha512(ByteString key) {
        return this.hmac("HmacSHA512", key);
    }

    private ByteString hmac(String algorithm, ByteString key) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            return ByteString.of(mac.doFinal(this.data));
        }
        catch (NoSuchAlgorithmException e2) {
            throw new AssertionError((Object)e2);
        }
        catch (InvalidKeyException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }

    @Nullable
    public static ByteString decodeBase64(String base64) {
        if (base64 == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] decoded = Base64.decode(base64);
        return decoded != null ? new ByteString(decoded) : null;
    }

    public String hex() {
        char[] result = new char[this.data.length * 2];
        int c2 = 0;
        for (byte b2 : this.data) {
            result[c2++] = HEX_DIGITS[b2 >> 4 & 0xF];
            result[c2++] = HEX_DIGITS[b2 & 0xF];
        }
        return new String(result);
    }

    public static ByteString decodeHex(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex == null");
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + hex);
        }
        byte[] result = new byte[hex.length() / 2];
        for (int i2 = 0; i2 < result.length; ++i2) {
            int d1 = ByteString.decodeHexDigit(hex.charAt(i2 * 2)) << 4;
            int d2 = ByteString.decodeHexDigit(hex.charAt(i2 * 2 + 1));
            result[i2] = (byte)(d1 + d2);
        }
        return ByteString.of(result);
    }

    private static int decodeHexDigit(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - 48;
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return c2 - 97 + 10;
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return c2 - 65 + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    public static ByteString read(InputStream in2, int byteCount) throws IOException {
        int read;
        if (in2 == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        byte[] result = new byte[byteCount];
        for (int offset = 0; offset < byteCount; offset += read) {
            read = in2.read(result, offset, byteCount - offset);
            if (read != -1) continue;
            throw new EOFException();
        }
        return new ByteString(result);
    }

    public ByteString toAsciiLowercase() {
        for (int i2 = 0; i2 < this.data.length; ++i2) {
            byte c2 = this.data[i2];
            if (c2 < 65 || c2 > 90) continue;
            byte[] lowercase = (byte[])this.data.clone();
            lowercase[i2++] = (byte)(c2 - -32);
            while (i2 < lowercase.length) {
                c2 = lowercase[i2];
                if (c2 >= 65 && c2 <= 90) {
                    lowercase[i2] = (byte)(c2 - -32);
                }
                ++i2;
            }
            return new ByteString(lowercase);
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        for (int i2 = 0; i2 < this.data.length; ++i2) {
            byte c2 = this.data[i2];
            if (c2 < 97 || c2 > 122) continue;
            byte[] lowercase = (byte[])this.data.clone();
            lowercase[i2++] = (byte)(c2 - 32);
            while (i2 < lowercase.length) {
                c2 = lowercase[i2];
                if (c2 >= 97 && c2 <= 122) {
                    lowercase[i2] = (byte)(c2 - 32);
                }
                ++i2;
            }
            return new ByteString(lowercase);
        }
        return this;
    }

    public ByteString substring(int beginIndex) {
        return this.substring(beginIndex, this.data.length);
    }

    public ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (endIndex > this.data.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (beginIndex == 0 && endIndex == this.data.length) {
            return this;
        }
        byte[] copy = new byte[subLen];
        System.arraycopy(this.data, beginIndex, copy, 0, subLen);
        return new ByteString(copy);
    }

    public byte getByte(int pos) {
        return this.data[pos];
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return (byte[])this.data.clone();
    }

    byte[] internalArray() {
        return this.data;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
    }

    public void write(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        out.write(this.data);
    }

    void write(Buffer buffer) {
        buffer.write(this.data, 0, this.data.length);
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        return other.rangeEquals(otherOffset, this.data, offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        return offset >= 0 && offset <= this.data.length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && Util.arrayRangeEquals(this.data, offset, other, otherOffset, byteCount);
    }

    public final boolean startsWith(ByteString prefix) {
        return this.rangeEquals(0, prefix, 0, prefix.size());
    }

    public final boolean startsWith(byte[] prefix) {
        return this.rangeEquals(0, prefix, 0, prefix.length);
    }

    public final boolean endsWith(ByteString suffix) {
        return this.rangeEquals(this.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public final boolean endsWith(byte[] suffix) {
        return this.rangeEquals(this.size() - suffix.length, suffix, 0, suffix.length);
    }

    public final int indexOf(ByteString other) {
        return this.indexOf(other.internalArray(), 0);
    }

    public final int indexOf(ByteString other, int fromIndex) {
        return this.indexOf(other.internalArray(), fromIndex);
    }

    public final int indexOf(byte[] other) {
        return this.indexOf(other, 0);
    }

    public int indexOf(byte[] other, int fromIndex) {
        int limit = this.data.length - other.length;
        for (int i2 = fromIndex = Math.max(fromIndex, 0); i2 <= limit; ++i2) {
            if (!Util.arrayRangeEquals(this.data, i2, other, 0, other.length)) continue;
            return i2;
        }
        return -1;
    }

    public final int lastIndexOf(ByteString other) {
        return this.lastIndexOf(other.internalArray(), this.size());
    }

    public final int lastIndexOf(ByteString other, int fromIndex) {
        return this.lastIndexOf(other.internalArray(), fromIndex);
    }

    public final int lastIndexOf(byte[] other) {
        return this.lastIndexOf(other, this.size());
    }

    public int lastIndexOf(byte[] other, int fromIndex) {
        for (int i2 = fromIndex = Math.min(fromIndex, this.data.length - other.length); i2 >= 0; --i2) {
            if (!Util.arrayRangeEquals(this.data, i2, other, 0, other.length)) continue;
            return i2;
        }
        return -1;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        return o2 instanceof ByteString && ((ByteString)o2).size() == this.data.length && ((ByteString)o2).rangeEquals(0, this.data, 0, this.data.length);
    }

    public int hashCode() {
        int result = this.hashCode;
        return result != 0 ? result : (this.hashCode = Arrays.hashCode(this.data));
    }

    @Override
    public int compareTo(ByteString byteString) {
        int sizeA = this.size();
        int sizeB = byteString.size();
        int size = Math.min(sizeA, sizeB);
        for (int i2 = 0; i2 < size; ++i2) {
            int byteB;
            int byteA = this.getByte(i2) & 0xFF;
            if (byteA == (byteB = byteString.getByte(i2) & 0xFF)) continue;
            return byteA < byteB ? -1 : 1;
        }
        if (sizeA == sizeB) {
            return 0;
        }
        return sizeA < sizeB ? -1 : 1;
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String text = this.utf8();
        int i2 = ByteString.codePointIndexToCharIndex(text, 64);
        if (i2 == -1) {
            return this.data.length <= 64 ? "[hex=" + this.hex() + "]" : "[size=" + this.data.length + " hex=" + this.substring(0, 64).hex() + "\u2026]";
        }
        String safeText = text.substring(0, i2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        return i2 < text.length() ? "[size=" + this.data.length + " text=" + safeText + "\u2026]" : "[text=" + safeText + "]";
    }

    static int codePointIndexToCharIndex(String s2, int codePointCount) {
        int c2;
        int j2 = 0;
        int length = s2.length();
        for (int i2 = 0; i2 < length; i2 += Character.charCount(c2)) {
            if (j2 == codePointCount) {
                return i2;
            }
            c2 = s2.codePointAt(i2);
            if (Character.isISOControl(c2) && c2 != 10 && c2 != 13 || c2 == 65533) {
                return -1;
            }
            ++j2;
        }
        return s2.length();
    }

    private void readObject(ObjectInputStream in2) throws IOException {
        int dataLength = in2.readInt();
        ByteString byteString = ByteString.read(in2, dataLength);
        try {
            Field field = ByteString.class.getDeclaredField("data");
            field.setAccessible(true);
            field.set(this, byteString.data);
        }
        catch (NoSuchFieldException e2) {
            throw new AssertionError();
        }
        catch (IllegalAccessException e3) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }
}

