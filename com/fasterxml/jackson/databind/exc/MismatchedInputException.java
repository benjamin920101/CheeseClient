/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;

public class MismatchedInputException
extends JsonMappingException {
    protected Class<?> _targetType;

    protected MismatchedInputException(JsonParser p2, String msg) {
        this(p2, msg, (JavaType)null);
    }

    protected MismatchedInputException(JsonParser p2, String msg, JsonLocation loc) {
        super((Closeable)p2, msg, loc);
    }

    protected MismatchedInputException(JsonParser p2, String msg, Class<?> targetType) {
        super(p2, msg);
        this._targetType = targetType;
    }

    protected MismatchedInputException(JsonParser p2, String msg, JavaType targetType) {
        super(p2, msg);
        this._targetType = ClassUtil.rawClass(targetType);
    }

    @Deprecated
    public static MismatchedInputException from(JsonParser p2, String msg) {
        return MismatchedInputException.from(p2, (Class)null, msg);
    }

    public static MismatchedInputException from(JsonParser p2, JavaType targetType, String msg) {
        return new MismatchedInputException(p2, msg, targetType);
    }

    public static MismatchedInputException from(JsonParser p2, Class<?> targetType, String msg) {
        return new MismatchedInputException(p2, msg, targetType);
    }

    public MismatchedInputException setTargetType(JavaType t2) {
        this._targetType = t2.getRawClass();
        return this;
    }

    public Class<?> getTargetType() {
        return this._targetType;
    }
}

