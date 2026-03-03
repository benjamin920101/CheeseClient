/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data.etf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterOutputStream;

public class ExTermDecoder {
    public static Object unpack(ByteBuffer buffer) {
        if (buffer.get() != -125) {
            throw new IllegalArgumentException("Failed header check");
        }
        return ExTermDecoder.unpack0(buffer);
    }

    public static Map<String, Object> unpackMap(ByteBuffer buffer) {
        byte tag = buffer.get(1);
        if (tag != 116) {
            throw new IllegalArgumentException("Cannot unpack map from tag " + tag);
        }
        return (Map)ExTermDecoder.unpack(buffer);
    }

    public static List<Object> unpackList(ByteBuffer buffer) {
        byte tag = buffer.get(1);
        if (tag != 108) {
            throw new IllegalArgumentException("Cannot unpack list from tag " + tag);
        }
        return (List)ExTermDecoder.unpack(buffer);
    }

    private static Object unpack0(ByteBuffer buffer) {
        byte tag = buffer.get();
        switch (tag) {
            case 80: {
                return ExTermDecoder.unpackCompressed(buffer);
            }
            case 97: {
                return ExTermDecoder.unpackSmallInt(buffer);
            }
            case 110: {
                return ExTermDecoder.unpackSmallBigint(buffer);
            }
            case 98: {
                return ExTermDecoder.unpackInt(buffer);
            }
            case 99: {
                return ExTermDecoder.unpackOldFloat(buffer);
            }
            case 70: {
                return ExTermDecoder.unpackFloat(buffer);
            }
            case 119: {
                return ExTermDecoder.unpackSmallAtom(buffer, StandardCharsets.UTF_8);
            }
            case 115: {
                return ExTermDecoder.unpackSmallAtom(buffer, StandardCharsets.ISO_8859_1);
            }
            case 118: {
                return ExTermDecoder.unpackAtom(buffer, StandardCharsets.UTF_8);
            }
            case 100: {
                return ExTermDecoder.unpackAtom(buffer, StandardCharsets.ISO_8859_1);
            }
            case 116: {
                return ExTermDecoder.unpackMap0(buffer);
            }
            case 108: {
                return ExTermDecoder.unpackList0(buffer);
            }
            case 106: {
                return Collections.emptyList();
            }
            case 107: {
                return ExTermDecoder.unpackString(buffer);
            }
            case 109: {
                return ExTermDecoder.unpackBinary(buffer);
            }
        }
        throw new IllegalArgumentException("Unknown tag " + tag);
    }

    private static Object unpackCompressed(ByteBuffer buffer) {
        int size = buffer.getInt();
        ByteArrayOutputStream decompressed = new ByteArrayOutputStream(size);
        try (InflaterOutputStream inflater = new InflaterOutputStream(decompressed);){
            inflater.write(buffer.array(), buffer.position(), buffer.remaining());
        }
        catch (IOException e2) {
            throw new UncheckedIOException(e2);
        }
        buffer = ByteBuffer.wrap(decompressed.toByteArray());
        return ExTermDecoder.unpack0(buffer);
    }

    private static double unpackOldFloat(ByteBuffer buffer) {
        String bytes = ExTermDecoder.getString(buffer, StandardCharsets.ISO_8859_1, 31);
        return Double.parseDouble(bytes);
    }

    private static double unpackFloat(ByteBuffer buffer) {
        return buffer.getDouble();
    }

    private static long unpackSmallBigint(ByteBuffer buffer) {
        int arity = Byte.toUnsignedInt(buffer.get());
        int sign = Byte.toUnsignedInt(buffer.get());
        long sum = 0L;
        long offset = 0L;
        while (arity-- > 0) {
            sum += Byte.toUnsignedLong(buffer.get()) << (int)offset;
            offset += 8L;
        }
        return sign == 0 ? sum : -sum;
    }

    private static int unpackSmallInt(ByteBuffer buffer) {
        return Byte.toUnsignedInt(buffer.get());
    }

    private static int unpackInt(ByteBuffer buffer) {
        return buffer.getInt();
    }

    private static List<Object> unpackString(ByteBuffer buffer) {
        int length = Short.toUnsignedInt(buffer.getShort());
        ArrayList<Object> bytes = new ArrayList<Object>(length);
        while (length-- > 0) {
            bytes.add(buffer.get());
        }
        return bytes;
    }

    private static String unpackBinary(ByteBuffer buffer) {
        int length = buffer.getInt();
        return ExTermDecoder.getString(buffer, StandardCharsets.UTF_8, length);
    }

    private static Object unpackSmallAtom(ByteBuffer buffer, Charset charset) {
        int length = Byte.toUnsignedInt(buffer.get());
        return ExTermDecoder.unpackAtom(buffer, charset, length);
    }

    private static Object unpackAtom(ByteBuffer buffer, Charset charset) {
        int length = Short.toUnsignedInt(buffer.getShort());
        return ExTermDecoder.unpackAtom(buffer, charset, length);
    }

    private static Object unpackAtom(ByteBuffer buffer, Charset charset, int length) {
        String value;
        switch (value = ExTermDecoder.getString(buffer, charset, length)) {
            case "true": {
                return true;
            }
            case "false": {
                return false;
            }
            case "nil": {
                return null;
            }
        }
        return value;
    }

    private static String getString(ByteBuffer buffer, Charset charset, int length) {
        byte[] array = new byte[length];
        buffer.get(array);
        return new String(array, charset);
    }

    private static List<Object> unpackList0(ByteBuffer buffer) {
        int length = buffer.getInt();
        ArrayList<Object> list = new ArrayList<Object>(length);
        while (length-- > 0) {
            list.add(ExTermDecoder.unpack0(buffer));
        }
        Object tail = ExTermDecoder.unpack0(buffer);
        if (tail != Collections.emptyList()) {
            throw new IllegalArgumentException("Unexpected tail " + tail);
        }
        return list;
    }

    private static Map<String, Object> unpackMap0(ByteBuffer buffer) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int arity = buffer.getInt();
        while (arity-- > 0) {
            String key = (String)ExTermDecoder.unpack0(buffer);
            Object value = ExTermDecoder.unpack0(buffer);
            map.put(key, value);
        }
        return map;
    }
}

