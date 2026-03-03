/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public final class CreatorCandidate {
    protected final AnnotationIntrospector _intr;
    protected final AnnotatedWithParams _creator;
    protected final int _paramCount;
    protected final Param[] _params;

    protected CreatorCandidate(AnnotationIntrospector intr, AnnotatedWithParams ct2, Param[] params, int count) {
        this._intr = intr;
        this._creator = ct2;
        this._params = params;
        this._paramCount = count;
    }

    public static CreatorCandidate construct(AnnotationIntrospector intr, AnnotatedWithParams creator, BeanPropertyDefinition[] propDefs) {
        int pcount = creator.getParameterCount();
        Param[] params = new Param[pcount];
        for (int i2 = 0; i2 < pcount; ++i2) {
            AnnotatedParameter annParam = creator.getParameter(i2);
            JacksonInject.Value injectId = intr.findInjectableValue(annParam);
            params[i2] = new Param(annParam, propDefs == null ? null : propDefs[i2], injectId);
        }
        return new CreatorCandidate(intr, creator, params, pcount);
    }

    public AnnotatedWithParams creator() {
        return this._creator;
    }

    public int paramCount() {
        return this._paramCount;
    }

    public JacksonInject.Value injection(int i2) {
        return this._params[i2].injection;
    }

    public AnnotatedParameter parameter(int i2) {
        return this._params[i2].annotated;
    }

    public BeanPropertyDefinition propertyDef(int i2) {
        return this._params[i2].propDef;
    }

    public PropertyName paramName(int i2) {
        BeanPropertyDefinition propDef = this._params[i2].propDef;
        if (propDef != null) {
            return propDef.getFullName();
        }
        return null;
    }

    public PropertyName explicitParamName(int i2) {
        BeanPropertyDefinition propDef = this._params[i2].propDef;
        if (propDef != null && propDef.isExplicitlyNamed()) {
            return propDef.getFullName();
        }
        return null;
    }

    public PropertyName findImplicitParamName(int i2) {
        String str = this._intr.findImplicitPropertyName(this._params[i2].annotated);
        if (str != null && !str.isEmpty()) {
            return PropertyName.construct(str);
        }
        return null;
    }

    public int findOnlyParamWithoutInjection() {
        int missing = -1;
        for (int i2 = 0; i2 < this._paramCount; ++i2) {
            if (this._params[i2].injection != null) continue;
            if (missing >= 0) {
                return -1;
            }
            missing = i2;
        }
        return missing;
    }

    public String toString() {
        return this._creator.toString();
    }

    public static final class Param {
        public final AnnotatedParameter annotated;
        public final BeanPropertyDefinition propDef;
        public final JacksonInject.Value injection;

        public Param(AnnotatedParameter p2, BeanPropertyDefinition pd2, JacksonInject.Value i2) {
            this.annotated = p2;
            this.propDef = pd2;
            this.injection = i2;
        }

        public PropertyName fullName() {
            if (this.propDef == null) {
                return null;
            }
            return this.propDef.getFullName();
        }

        public boolean hasFullName() {
            if (this.propDef == null) {
                return false;
            }
            PropertyName n2 = this.propDef.getFullName();
            return n2.hasSimpleName();
        }
    }
}

