/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.net.InetAddress;

public class InetAddressSerializer
extends StdScalarSerializer<InetAddress>
implements ContextualSerializer {
    protected final boolean _asNumeric;

    public InetAddressSerializer() {
        this(false);
    }

    public InetAddressSerializer(boolean asNumeric) {
        super(InetAddress.class);
        this._asNumeric = asNumeric;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        JsonFormat.Shape shape;
        JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
        boolean asNumeric = false;
        if (format != null && ((shape = format.getShape()).isNumeric() || shape == JsonFormat.Shape.ARRAY)) {
            asNumeric = true;
        }
        if (asNumeric != this._asNumeric) {
            return new InetAddressSerializer(asNumeric);
        }
        return this;
    }

    @Override
    public void serialize(InetAddress value, JsonGenerator g2, SerializerProvider provider) throws IOException {
        String str;
        if (this._asNumeric) {
            str = value.getHostAddress();
        } else {
            str = value.toString().trim();
            int ix2 = str.indexOf(47);
            if (ix2 >= 0) {
                str = ix2 == 0 ? str.substring(1) : str.substring(0, ix2);
            }
        }
        g2.writeString(str);
    }

    @Override
    public void serializeWithType(InetAddress value, JsonGenerator g2, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g2, typeSer.typeId((Object)value, InetAddress.class, JsonToken.VALUE_STRING));
        this.serialize(value, g2, provider);
        typeSer.writeTypeSuffix(g2, typeIdDef);
    }
}

