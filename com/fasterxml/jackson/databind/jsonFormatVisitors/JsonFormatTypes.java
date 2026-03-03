/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

public enum JsonFormatTypes {
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    OBJECT,
    ARRAY,
    NULL,
    ANY;

    private static final Map<String, JsonFormatTypes> _byLCName;

    @JsonValue
    public String value() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static JsonFormatTypes forValue(String s2) {
        return _byLCName.get(s2);
    }

    static {
        _byLCName = new HashMap<String, JsonFormatTypes>();
        for (JsonFormatTypes t2 : JsonFormatTypes.values()) {
            _byLCName.put(t2.name().toLowerCase(), t2);
        }
    }
}

