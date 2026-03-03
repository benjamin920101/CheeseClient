/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DateTimeSerializerBase<T>
extends StdScalarSerializer<T>
implements ContextualSerializer {
    protected final Boolean _useTimestamp;
    protected final DateFormat _customFormat;
    protected final AtomicReference<DateFormat> _reusedCustomFormat;

    protected DateTimeSerializerBase(Class<T> type, Boolean useTimestamp, DateFormat customFormat) {
        super(type);
        this._useTimestamp = useTimestamp;
        this._customFormat = customFormat;
        this._reusedCustomFormat = customFormat == null ? null : new AtomicReference();
    }

    public abstract DateTimeSerializerBase<T> withFormat(Boolean var1, DateFormat var2);

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        boolean changeTZ;
        boolean asString;
        JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
        if (format == null) {
            return this;
        }
        JsonFormat.Shape shape = format.getShape();
        if (shape.isNumeric()) {
            return this.withFormat(Boolean.TRUE, null);
        }
        if (format.hasPattern()) {
            Locale loc = format.hasLocale() ? format.getLocale() : serializers.getLocale();
            SimpleDateFormat df2 = new SimpleDateFormat(format.getPattern(), loc);
            TimeZone tz2 = format.hasTimeZone() ? format.getTimeZone() : serializers.getTimeZone();
            df2.setTimeZone(tz2);
            return this.withFormat(Boolean.FALSE, df2);
        }
        boolean hasLocale = format.hasLocale();
        boolean hasTZ = format.hasTimeZone();
        boolean bl2 = asString = shape == JsonFormat.Shape.STRING;
        if (!(hasLocale || hasTZ || asString)) {
            return this;
        }
        DateFormat df0 = serializers.getConfig().getDateFormat();
        if (df0 instanceof StdDateFormat) {
            StdDateFormat std = (StdDateFormat)df0;
            if (format.hasLocale()) {
                std = std.withLocale(format.getLocale());
            }
            if (format.hasTimeZone()) {
                std = std.withTimeZone(format.getTimeZone());
            }
            return this.withFormat(Boolean.FALSE, std);
        }
        if (!(df0 instanceof SimpleDateFormat)) {
            serializers.reportBadDefinition(this.handledType(), String.format("Configured `DateFormat` (%s) not a `SimpleDateFormat`; cannot configure `Locale` or `TimeZone`", df0.getClass().getName()));
        }
        SimpleDateFormat df3 = (SimpleDateFormat)df0;
        df3 = hasLocale ? new SimpleDateFormat(df3.toPattern(), format.getLocale()) : (SimpleDateFormat)df3.clone();
        TimeZone newTz = format.getTimeZone();
        boolean bl3 = changeTZ = newTz != null && !newTz.equals(df3.getTimeZone());
        if (changeTZ) {
            df3.setTimeZone(newTz);
        }
        return this.withFormat(Boolean.FALSE, df3);
    }

    @Override
    public boolean isEmpty(SerializerProvider serializers, T value) {
        return false;
    }

    protected abstract long _timestamp(T var1);

    @Override
    public JsonNode getSchema(SerializerProvider serializers, Type typeHint) {
        return this.createSchemaNode(this._asTimestamp(serializers) ? "number" : "string", true);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        this._acceptJsonFormatVisitor(visitor, typeHint, this._asTimestamp(visitor.getProvider()));
    }

    @Override
    public abstract void serialize(T var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

    protected boolean _asTimestamp(SerializerProvider serializers) {
        if (this._useTimestamp != null) {
            return this._useTimestamp;
        }
        if (this._customFormat == null) {
            if (serializers != null) {
                return serializers.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            }
            throw new IllegalArgumentException("Null SerializerProvider passed for " + this.handledType().getName());
        }
        return false;
    }

    protected void _acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint, boolean asNumber) throws JsonMappingException {
        if (asNumber) {
            this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.LONG, JsonValueFormat.UTC_MILLISEC);
        } else {
            this.visitStringFormat(visitor, typeHint, JsonValueFormat.DATE_TIME);
        }
    }

    protected void _serializeAsString(Date value, JsonGenerator g2, SerializerProvider provider) throws IOException {
        if (this._customFormat == null) {
            provider.defaultSerializeDateValue(value, g2);
            return;
        }
        DateFormat f2 = this._reusedCustomFormat.getAndSet(null);
        if (f2 == null) {
            f2 = (DateFormat)this._customFormat.clone();
        }
        g2.writeString(f2.format(value));
        this._reusedCustomFormat.compareAndSet(null, f2);
    }
}

