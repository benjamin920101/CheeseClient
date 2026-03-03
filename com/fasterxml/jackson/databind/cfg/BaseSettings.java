/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class BaseSettings
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    protected final ClassIntrospector _classIntrospector;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final PropertyNamingStrategy _propertyNamingStrategy;
    protected final TypeFactory _typeFactory;
    protected final TypeResolverBuilder<?> _typeResolverBuilder;
    protected final PolymorphicTypeValidator _typeValidator;
    protected final DateFormat _dateFormat;
    protected final HandlerInstantiator _handlerInstantiator;
    protected final Locale _locale;
    protected final TimeZone _timeZone;
    protected final Base64Variant _defaultBase64;

    public BaseSettings(ClassIntrospector ci, AnnotationIntrospector ai2, PropertyNamingStrategy pns, TypeFactory tf2, TypeResolverBuilder<?> typer, DateFormat dateFormat, HandlerInstantiator hi2, Locale locale, TimeZone tz2, Base64Variant defaultBase64, PolymorphicTypeValidator ptv) {
        this._classIntrospector = ci;
        this._annotationIntrospector = ai2;
        this._propertyNamingStrategy = pns;
        this._typeFactory = tf2;
        this._typeResolverBuilder = typer;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = hi2;
        this._locale = locale;
        this._timeZone = tz2;
        this._defaultBase64 = defaultBase64;
        this._typeValidator = ptv;
    }

    @Deprecated
    public BaseSettings(ClassIntrospector ci, AnnotationIntrospector ai2, PropertyNamingStrategy pns, TypeFactory tf2, TypeResolverBuilder<?> typer, DateFormat dateFormat, HandlerInstantiator hi2, Locale locale, TimeZone tz2, Base64Variant defaultBase64) {
        this(ci, ai2, pns, tf2, typer, dateFormat, hi2, locale, tz2, defaultBase64, null);
    }

    public BaseSettings copy() {
        return new BaseSettings(this._classIntrospector.copy(), this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withClassIntrospector(ClassIntrospector ci) {
        if (this._classIntrospector == ci) {
            return this;
        }
        return new BaseSettings(ci, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withAnnotationIntrospector(AnnotationIntrospector ai2) {
        if (this._annotationIntrospector == ai2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, ai2, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withInsertedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(ai2, this._annotationIntrospector));
    }

    public BaseSettings withAppendedAnnotationIntrospector(AnnotationIntrospector ai2) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(this._annotationIntrospector, ai2));
    }

    public BaseSettings withPropertyNamingStrategy(PropertyNamingStrategy pns) {
        if (this._propertyNamingStrategy == pns) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, pns, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withTypeFactory(TypeFactory tf2) {
        if (this._typeFactory == tf2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, tf2, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withTypeResolverBuilder(TypeResolverBuilder<?> typer) {
        if (this._typeResolverBuilder == typer) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, typer, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withDateFormat(DateFormat df2) {
        if (this._dateFormat == df2) {
            return this;
        }
        if (df2 != null && this.hasExplicitTimeZone()) {
            df2 = this._force(df2, this._timeZone);
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, df2, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings withHandlerInstantiator(HandlerInstantiator hi2) {
        if (this._handlerInstantiator == hi2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, hi2, this._locale, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings with(Locale l2) {
        if (this._locale == l2) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, l2, this._timeZone, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings with(TimeZone tz2) {
        if (tz2 == null) {
            throw new IllegalArgumentException();
        }
        if (tz2 == this._timeZone) {
            return this;
        }
        DateFormat df2 = this._force(this._dateFormat, tz2);
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, df2, this._handlerInstantiator, this._locale, tz2, this._defaultBase64, this._typeValidator);
    }

    public BaseSettings with(Base64Variant base64) {
        if (base64 == this._defaultBase64) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, base64, this._typeValidator);
    }

    public BaseSettings with(PolymorphicTypeValidator v2) {
        if (v2 == this._typeValidator) {
            return this;
        }
        return new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64, v2);
    }

    public ClassIntrospector getClassIntrospector() {
        return this._classIntrospector;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._propertyNamingStrategy;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public TypeResolverBuilder<?> getTypeResolverBuilder() {
        return this._typeResolverBuilder;
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return this._typeValidator;
    }

    public DateFormat getDateFormat() {
        return this._dateFormat;
    }

    public HandlerInstantiator getHandlerInstantiator() {
        return this._handlerInstantiator;
    }

    public Locale getLocale() {
        return this._locale;
    }

    public TimeZone getTimeZone() {
        TimeZone tz2 = this._timeZone;
        return tz2 == null ? DEFAULT_TIMEZONE : tz2;
    }

    public boolean hasExplicitTimeZone() {
        return this._timeZone != null;
    }

    public Base64Variant getBase64Variant() {
        return this._defaultBase64;
    }

    private DateFormat _force(DateFormat df2, TimeZone tz2) {
        if (df2 instanceof StdDateFormat) {
            return ((StdDateFormat)df2).withTimeZone(tz2);
        }
        df2 = (DateFormat)df2.clone();
        df2.setTimeZone(tz2);
        return df2;
    }
}

