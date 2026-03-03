/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;

public class StackTraceElementDeserializer
extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1L;

    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    @Override
    public StackTraceElement deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        JsonToken t2 = p2.currentToken();
        if (t2 == JsonToken.START_OBJECT) {
            String className = "";
            String methodName = "";
            String fileName = "";
            String moduleName = null;
            String moduleVersion = null;
            String classLoaderName = null;
            int lineNumber = -1;
            while ((t2 = p2.nextValue()) != JsonToken.END_OBJECT) {
                String propName = p2.getCurrentName();
                if ("className".equals(propName)) {
                    className = p2.getText();
                } else if ("classLoaderName".equals(propName)) {
                    classLoaderName = p2.getText();
                } else if ("fileName".equals(propName)) {
                    fileName = p2.getText();
                } else if ("lineNumber".equals(propName)) {
                    lineNumber = t2.isNumeric() ? p2.getIntValue() : this._parseIntPrimitive(p2, ctxt);
                } else if ("methodName".equals(propName)) {
                    methodName = p2.getText();
                } else if (!"nativeMethod".equals(propName)) {
                    if ("moduleName".equals(propName)) {
                        moduleName = p2.getText();
                    } else if ("moduleVersion".equals(propName)) {
                        moduleVersion = p2.getText();
                    } else if (!"declaringClass".equals(propName) && !"format".equals(propName)) {
                        this.handleUnknownProperty(p2, ctxt, this._valueClass, propName);
                    }
                }
                p2.skipChildren();
            }
            return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, classLoaderName);
        }
        if (t2 == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            p2.nextToken();
            StackTraceElement value = this.deserialize(p2, ctxt);
            if (p2.nextToken() != JsonToken.END_ARRAY) {
                this.handleMissingEndArrayForSingle(p2, ctxt);
            }
            return value;
        }
        return (StackTraceElement)ctxt.handleUnexpectedToken(this._valueClass, p2);
    }

    @Deprecated
    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion) {
        return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, null);
    }

    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion, String classLoaderName) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }
}

