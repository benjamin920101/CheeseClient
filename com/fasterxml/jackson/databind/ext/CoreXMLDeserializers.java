/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers
extends Deserializers.Base {
    static final DatatypeFactory _dataTypeFactory;
    protected static final int TYPE_DURATION = 1;
    protected static final int TYPE_G_CALENDAR = 2;
    protected static final int TYPE_QNAME = 3;

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
        Class<?> raw = type.getRawClass();
        if (raw == QName.class) {
            return new Std(raw, 3);
        }
        if (raw == XMLGregorianCalendar.class) {
            return new Std(raw, 2);
        }
        if (raw == Duration.class) {
            return new Std(raw, 1);
        }
        return null;
    }

    static {
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static class Std
    extends FromStringDeserializer<Object> {
        private static final long serialVersionUID = 1L;
        protected final int _kind;

        public Std(Class<?> raw, int kind) {
            super(raw);
            this._kind = kind;
        }

        @Override
        public Object deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
            if (this._kind == 2 && p2.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return this._gregorianFromDate(ctxt, this._parseDate(p2, ctxt));
            }
            return super.deserialize(p2, ctxt);
        }

        @Override
        protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
            switch (this._kind) {
                case 1: {
                    return _dataTypeFactory.newDuration(value);
                }
                case 3: {
                    return QName.valueOf(value);
                }
                case 2: {
                    Date d2;
                    try {
                        d2 = this._parseDate(value, ctxt);
                    }
                    catch (JsonMappingException e2) {
                        return _dataTypeFactory.newXMLGregorianCalendar(value);
                    }
                    return this._gregorianFromDate(ctxt, d2);
                }
            }
            throw new IllegalStateException();
        }

        protected XMLGregorianCalendar _gregorianFromDate(DeserializationContext ctxt, Date d2) {
            if (d2 == null) {
                return null;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(d2);
            TimeZone tz2 = ctxt.getTimeZone();
            if (tz2 != null) {
                calendar.setTimeZone(tz2);
            }
            return _dataTypeFactory.newXMLGregorianCalendar(calendar);
        }
    }
}

