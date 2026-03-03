/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UUIDDeserializer
extends FromStringDeserializer<UUID> {
    private static final long serialVersionUID = 1L;
    static final int[] HEX_DIGITS;

    public UUIDDeserializer() {
        super(UUID.class);
    }

    @Override
    protected UUID _deserialize(String id2, DeserializationContext ctxt) throws IOException {
        if (id2.length() != 36) {
            if (id2.length() == 24) {
                byte[] stuff = Base64Variants.getDefaultVariant().decode(id2);
                return this._fromBytes(stuff, ctxt);
            }
            return this._badFormat(id2, ctxt);
        }
        if (id2.charAt(8) != '-' || id2.charAt(13) != '-' || id2.charAt(18) != '-' || id2.charAt(23) != '-') {
            this._badFormat(id2, ctxt);
        }
        long l1 = this.intFromChars(id2, 0, ctxt);
        long l2 = (long)this.shortFromChars(id2, 9, ctxt) << 16;
        long hi2 = (l1 <<= 32) + (l2 |= (long)this.shortFromChars(id2, 14, ctxt));
        int i1 = this.shortFromChars(id2, 19, ctxt) << 16 | this.shortFromChars(id2, 24, ctxt);
        l1 = i1;
        l2 = this.intFromChars(id2, 28, ctxt);
        l2 = l2 << 32 >>> 32;
        long lo2 = (l1 <<= 32) | l2;
        return new UUID(hi2, lo2);
    }

    @Override
    protected UUID _deserializeEmbedded(Object ob2, DeserializationContext ctxt) throws IOException {
        if (ob2 instanceof byte[]) {
            return this._fromBytes((byte[])ob2, ctxt);
        }
        super._deserializeEmbedded(ob2, ctxt);
        return null;
    }

    private UUID _badFormat(String uuidStr, DeserializationContext ctxt) throws IOException {
        return (UUID)ctxt.handleWeirdStringValue(this.handledType(), uuidStr, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    int intFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        return (this.byteFromChars(str, index, ctxt) << 24) + (this.byteFromChars(str, index + 2, ctxt) << 16) + (this.byteFromChars(str, index + 4, ctxt) << 8) + this.byteFromChars(str, index + 6, ctxt);
    }

    int shortFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        return (this.byteFromChars(str, index, ctxt) << 8) + this.byteFromChars(str, index + 2, ctxt);
    }

    int byteFromChars(String str, int index, DeserializationContext ctxt) throws JsonMappingException {
        int hex;
        char c1 = str.charAt(index);
        char c2 = str.charAt(index + 1);
        if (c1 <= '\u007f' && c2 <= '\u007f' && (hex = HEX_DIGITS[c1] << 4 | HEX_DIGITS[c2]) >= 0) {
            return hex;
        }
        if (c1 > '\u007f' || HEX_DIGITS[c1] < 0) {
            return this._badChar(str, index, ctxt, c1);
        }
        return this._badChar(str, index + 1, ctxt, c2);
    }

    int _badChar(String uuidStr, int index, DeserializationContext ctxt, char c2) throws JsonMappingException {
        throw ctxt.weirdStringException(uuidStr, this.handledType(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", Character.valueOf(c2), Integer.toHexString(c2)));
    }

    private UUID _fromBytes(byte[] bytes, DeserializationContext ctxt) throws JsonMappingException {
        if (bytes.length != 16) {
            throw InvalidFormatException.from(ctxt.getParser(), "Can only construct UUIDs from byte[16]; got " + bytes.length + " bytes", bytes, this.handledType());
        }
        return new UUID(UUIDDeserializer._long(bytes, 0), UUIDDeserializer._long(bytes, 8));
    }

    private static long _long(byte[] b2, int offset) {
        long l1 = (long)UUIDDeserializer._int(b2, offset) << 32;
        long l2 = UUIDDeserializer._int(b2, offset + 4);
        l2 = l2 << 32 >>> 32;
        return l1 | l2;
    }

    private static int _int(byte[] b2, int offset) {
        return b2[offset] << 24 | (b2[offset + 1] & 0xFF) << 16 | (b2[offset + 2] & 0xFF) << 8 | b2[offset + 3] & 0xFF;
    }

    static {
        int i2;
        HEX_DIGITS = new int[127];
        Arrays.fill(HEX_DIGITS, -1);
        for (i2 = 0; i2 < 10; ++i2) {
            UUIDDeserializer.HEX_DIGITS[48 + i2] = i2;
        }
        for (i2 = 0; i2 < 6; ++i2) {
            UUIDDeserializer.HEX_DIGITS[97 + i2] = 10 + i2;
            UUIDDeserializer.HEX_DIGITS[65 + i2] = 10 + i2;
        }
    }
}

