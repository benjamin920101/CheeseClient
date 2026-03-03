/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.io.IOException;

public class TextNode
extends ValueNode {
    private static final long serialVersionUID = 2L;
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    protected final String _value;

    public TextNode(String v2) {
        this._value = v2;
    }

    public static TextNode valueOf(String v2) {
        if (v2 == null) {
            return null;
        }
        if (v2.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(v2);
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.STRING;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    @Override
    public String textValue() {
        return this._value;
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
        String str = this._value.trim();
        ByteArrayBuilder builder = new ByteArrayBuilder(4 + (str.length() * 3 >> 2));
        try {
            b64variant.decode(str, builder);
        }
        catch (IllegalArgumentException e2) {
            throw InvalidFormatException.from(null, String.format("Cannot access contents of TextNode as binary due to broken Base64 encoding: %s", e2.getMessage()), str, byte[].class);
        }
        return builder.toByteArray();
    }

    @Override
    public byte[] binaryValue() throws IOException {
        return this.getBinaryValue(Base64Variants.getDefaultVariant());
    }

    @Override
    public String asText() {
        return this._value;
    }

    @Override
    public String asText(String defaultValue) {
        return this._value == null ? defaultValue : this._value;
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        if (this._value != null) {
            String v2 = this._value.trim();
            if ("true".equals(v2)) {
                return true;
            }
            if ("false".equals(v2)) {
                return false;
            }
        }
        return defaultValue;
    }

    @Override
    public int asInt(int defaultValue) {
        return NumberInput.parseAsInt(this._value, defaultValue);
    }

    @Override
    public long asLong(long defaultValue) {
        return NumberInput.parseAsLong(this._value, defaultValue);
    }

    @Override
    public double asDouble(double defaultValue) {
        return NumberInput.parseAsDouble(this._value, defaultValue);
    }

    @Override
    public final void serialize(JsonGenerator g2, SerializerProvider provider) throws IOException {
        if (this._value == null) {
            g2.writeNull();
        } else {
            g2.writeString(this._value);
        }
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2 instanceof TextNode) {
            return ((TextNode)o2)._value.equals(this._value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this._value.hashCode();
    }

    @Deprecated
    protected static void appendQuoted(StringBuilder sb2, String content) {
        sb2.append('\"');
        CharTypes.appendQuoted(sb2, content);
        sb2.append('\"');
    }
}

