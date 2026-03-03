/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data.etf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.SerializableData;

public class ExTermEncoder {
    public static ByteBuffer pack(Object data) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte)-125);
        ByteBuffer packed = ExTermEncoder.pack(buffer, data);
        ((Buffer)packed).flip();
        return packed;
    }

    private static ByteBuffer pack(ByteBuffer buffer, Object value) {
        if (value instanceof String) {
            return ExTermEncoder.packBinary(buffer, (String)value);
        }
        if (value instanceof Map) {
            return ExTermEncoder.packMap(buffer, (Map)value);
        }
        if (value instanceof SerializableData) {
            return ExTermEncoder.packMap(buffer, ((SerializableData)value).toData().toMap());
        }
        if (value instanceof Collection) {
            return ExTermEncoder.packList(buffer, (Collection)value);
        }
        if (value instanceof DataArray) {
            return ExTermEncoder.packList(buffer, ((DataArray)value).toList());
        }
        if (value instanceof Byte) {
            return ExTermEncoder.packSmallInt(buffer, (Byte)value);
        }
        if (value instanceof Integer || value instanceof Short) {
            return ExTermEncoder.packInt(buffer, (Integer)value);
        }
        if (value instanceof Long) {
            return ExTermEncoder.packLong(buffer, (Long)value);
        }
        if (value instanceof Float || value instanceof Double) {
            return ExTermEncoder.packFloat(buffer, (Double)value);
        }
        if (value instanceof Boolean) {
            return ExTermEncoder.packAtom(buffer, String.valueOf(value));
        }
        if (value == null) {
            return ExTermEncoder.packAtom(buffer, "nil");
        }
        if (value instanceof long[]) {
            return ExTermEncoder.packArray(buffer, (long[])value);
        }
        if (value instanceof int[]) {
            return ExTermEncoder.packArray(buffer, (int[])value);
        }
        if (value instanceof short[]) {
            return ExTermEncoder.packArray(buffer, (short[])value);
        }
        if (value instanceof byte[]) {
            return ExTermEncoder.packArray(buffer, (byte[])value);
        }
        if (value instanceof Object[]) {
            return ExTermEncoder.packList(buffer, Arrays.asList((Object[])value));
        }
        throw new UnsupportedOperationException("Cannot pack value of type " + value.getClass().getName());
    }

    private static ByteBuffer realloc(ByteBuffer buffer, int length) {
        if (buffer.remaining() >= length) {
            return buffer;
        }
        ByteBuffer allocated = ByteBuffer.allocate(buffer.position() + length << 1);
        ((Buffer)buffer).flip();
        allocated.put(buffer);
        return allocated;
    }

    private static ByteBuffer packMap(ByteBuffer buffer, Map<String, Object> data) {
        buffer = ExTermEncoder.realloc(buffer, data.size() + 5);
        buffer.put((byte)116);
        buffer.putInt(data.size());
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            buffer = ExTermEncoder.packBinary(buffer, entry.getKey());
            buffer = ExTermEncoder.pack(buffer, entry.getValue());
        }
        return buffer;
    }

    private static ByteBuffer packList(ByteBuffer buffer, Collection<Object> data) {
        if (data.isEmpty()) {
            return ExTermEncoder.packNil(buffer);
        }
        buffer = ExTermEncoder.realloc(buffer, data.size() + 6);
        buffer.put((byte)108);
        buffer.putInt(data.size());
        for (Object element : data) {
            buffer = ExTermEncoder.pack(buffer, element);
        }
        return ExTermEncoder.packNil(buffer);
    }

    private static ByteBuffer packBinary(ByteBuffer buffer, String value) {
        byte[] encoded = value.getBytes(StandardCharsets.UTF_8);
        buffer = ExTermEncoder.realloc(buffer, encoded.length * 4 + 5);
        buffer.put((byte)109);
        buffer.putInt(encoded.length);
        buffer.put(encoded);
        return buffer;
    }

    private static ByteBuffer packSmallInt(ByteBuffer buffer, byte value) {
        buffer = ExTermEncoder.realloc(buffer, 2);
        buffer.put((byte)97);
        buffer.put(value);
        return buffer;
    }

    private static ByteBuffer packInt(ByteBuffer buffer, int value) {
        if (ExTermEncoder.countBytes(value) <= 1 && value >= 0) {
            return ExTermEncoder.packSmallInt(buffer, (byte)value);
        }
        buffer = ExTermEncoder.realloc(buffer, 5);
        buffer.put((byte)98);
        buffer.putInt(value);
        return buffer;
    }

    private static ByteBuffer packLong(ByteBuffer buffer, long value) {
        byte bytes = ExTermEncoder.countBytes(value);
        if (bytes <= 1) {
            return ExTermEncoder.packSmallInt(buffer, (byte)value);
        }
        if (bytes <= 4 && value >= 0L) {
            buffer = ExTermEncoder.realloc(buffer, 5);
            buffer.put((byte)98);
            buffer.putInt((int)value);
            return buffer;
        }
        buffer = ExTermEncoder.realloc(buffer, 3 + bytes);
        buffer.put((byte)110);
        buffer.put(bytes);
        buffer.put((byte)0);
        while (value > 0L) {
            buffer.put((byte)value);
            value >>>= 8;
        }
        return buffer;
    }

    private static ByteBuffer packFloat(ByteBuffer buffer, double value) {
        buffer = ExTermEncoder.realloc(buffer, 9);
        buffer.put((byte)70);
        buffer.putDouble(value);
        return buffer;
    }

    private static ByteBuffer packAtom(ByteBuffer buffer, String value) {
        byte[] array = value.getBytes(StandardCharsets.ISO_8859_1);
        buffer = ExTermEncoder.realloc(buffer, array.length + 3);
        buffer.put((byte)100);
        buffer.putShort((short)array.length);
        buffer.put(array);
        return buffer;
    }

    private static ByteBuffer packArray(ByteBuffer buffer, long[] array) {
        if (array.length == 0) {
            return ExTermEncoder.packNil(buffer);
        }
        buffer = ExTermEncoder.realloc(buffer, array.length * 8 + 6);
        buffer.put((byte)108);
        buffer.putInt(array.length);
        for (long it2 : array) {
            buffer = ExTermEncoder.packLong(buffer, it2);
        }
        return ExTermEncoder.packNil(buffer);
    }

    private static ByteBuffer packArray(ByteBuffer buffer, int[] array) {
        if (array.length == 0) {
            return ExTermEncoder.packNil(buffer);
        }
        buffer = ExTermEncoder.realloc(buffer, array.length * 4 + 6);
        buffer.put((byte)108);
        buffer.putInt(array.length);
        for (int it2 : array) {
            buffer = ExTermEncoder.packInt(buffer, it2);
        }
        return ExTermEncoder.packNil(buffer);
    }

    private static ByteBuffer packArray(ByteBuffer buffer, short[] array) {
        if (array.length == 0) {
            return ExTermEncoder.packNil(buffer);
        }
        buffer = ExTermEncoder.realloc(buffer, array.length * 2 + 6);
        buffer.put((byte)108);
        buffer.putInt(array.length);
        for (short it2 : array) {
            buffer = ExTermEncoder.packInt(buffer, it2);
        }
        return ExTermEncoder.packNil(buffer);
    }

    private static ByteBuffer packArray(ByteBuffer buffer, byte[] array) {
        if (array.length == 0) {
            return ExTermEncoder.packNil(buffer);
        }
        buffer = ExTermEncoder.realloc(buffer, array.length + 6);
        buffer.put((byte)108);
        buffer.putInt(array.length);
        for (byte it2 : array) {
            buffer = ExTermEncoder.packSmallInt(buffer, it2);
        }
        return ExTermEncoder.packNil(buffer);
    }

    private static ByteBuffer packNil(ByteBuffer buffer) {
        buffer = ExTermEncoder.realloc(buffer, 1);
        buffer.put((byte)106);
        return buffer;
    }

    private static byte countBytes(long value) {
        int leadingZeros = Long.numberOfLeadingZeros(value);
        return (byte)Math.ceil((double)(64 - leadingZeros) / 8.0);
    }
}

