/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public class StringDeserializer
extends StdScalarDeserializer<String> {
    private static final long serialVersionUID = 1L;
    public static final StringDeserializer instance = new StringDeserializer();

    public StringDeserializer() {
        super(String.class);
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        return "";
    }

    @Override
    public String deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        String text;
        if (p2.hasToken(JsonToken.VALUE_STRING)) {
            return p2.getText();
        }
        JsonToken t2 = p2.currentToken();
        if (t2 == JsonToken.START_ARRAY) {
            return (String)this._deserializeFromArray(p2, ctxt);
        }
        if (t2 == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob2 = p2.getEmbeddedObject();
            if (ob2 == null) {
                return null;
            }
            if (ob2 instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[])ob2, false);
            }
            return ob2.toString();
        }
        if (t2.isScalarValue() && (text = p2.getValueAsString()) != null) {
            return text;
        }
        return (String)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    @Override
    public String deserializeWithType(JsonParser p2, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p2, ctxt);
    }
}

