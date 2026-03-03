/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data;

import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum DataType {
    INT,
    FLOAT,
    STRING,
    OBJECT,
    ARRAY,
    BOOLEAN,
    NULL,
    UNKNOWN;


    @Nonnull
    public static DataType getType(@Nullable Object value) {
        for (DataType type : DataType.values()) {
            if (!type.isType(value)) continue;
            return type;
        }
        return UNKNOWN;
    }

    public boolean isType(@Nullable Object value) {
        switch (this) {
            case INT: {
                return value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Byte;
            }
            case FLOAT: {
                return value instanceof Double || value instanceof Float;
            }
            case STRING: {
                return value instanceof String;
            }
            case BOOLEAN: {
                return value instanceof Boolean;
            }
            case ARRAY: {
                return value instanceof List;
            }
            case OBJECT: {
                return value instanceof Map;
            }
            case NULL: {
                return value == null;
            }
        }
        return false;
    }
}

