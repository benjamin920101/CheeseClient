/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class BeanSerializerBase
extends StdSerializer<Object>
implements ContextualSerializer,
ResolvableSerializer,
JsonFormatVisitable,
SchemaAware {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final JavaType _beanType;
    protected final BeanPropertyWriter[] _props;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final AnyGetterWriter _anyGetterWriter;
    protected final Object _propertyFilterId;
    protected final AnnotatedMember _typeId;
    protected final ObjectIdWriter _objectIdWriter;
    protected final JsonFormat.Shape _serializationShape;

    protected BeanSerializerBase(JavaType type, BeanSerializerBuilder builder, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        super(type);
        this._beanType = type;
        this._props = properties;
        this._filteredProps = filteredProperties;
        if (builder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
        } else {
            this._typeId = builder.getTypeId();
            this._anyGetterWriter = builder.getAnyGetter();
            this._propertyFilterId = builder.getFilterId();
            this._objectIdWriter = builder.getObjectIdWriter();
            JsonFormat.Value format = builder.getBeanDescription().findExpectedFormat(null);
            this._serializationShape = format == null ? null : format.getShape();
        }
    }

    public BeanSerializerBase(BeanSerializerBase src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        super(src._handledType);
        this._beanType = src._beanType;
        this._props = properties;
        this._filteredProps = filteredProperties;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter) {
        this(src, objectIdWriter, src._propertyFilterId);
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter, Object filterId) {
        super(src._handledType);
        this._beanType = src._beanType;
        this._props = src._props;
        this._filteredProps = src._filteredProps;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = filterId;
        this._serializationShape = src._serializationShape;
    }

    @Deprecated
    protected BeanSerializerBase(BeanSerializerBase src, String[] toIgnore) {
        this(src, ArrayBuilders.arrayToSet(toIgnore));
    }

    protected BeanSerializerBase(BeanSerializerBase src, Set<String> toIgnore) {
        super(src._handledType);
        this._beanType = src._beanType;
        BeanPropertyWriter[] propsIn = src._props;
        BeanPropertyWriter[] fpropsIn = src._filteredProps;
        int len = propsIn.length;
        ArrayList<BeanPropertyWriter> propsOut = new ArrayList<BeanPropertyWriter>(len);
        ArrayList<BeanPropertyWriter> fpropsOut = fpropsIn == null ? null : new ArrayList<BeanPropertyWriter>(len);
        for (int i2 = 0; i2 < len; ++i2) {
            BeanPropertyWriter bpw2 = propsIn[i2];
            if (toIgnore != null && toIgnore.contains(bpw2.getName())) continue;
            propsOut.add(bpw2);
            if (fpropsIn == null) continue;
            fpropsOut.add(fpropsIn[i2]);
        }
        this._props = propsOut.toArray(new BeanPropertyWriter[propsOut.size()]);
        this._filteredProps = fpropsOut == null ? null : fpropsOut.toArray(new BeanPropertyWriter[fpropsOut.size()]);
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter var1);

    protected abstract BeanSerializerBase withIgnorals(Set<String> var1);

    @Deprecated
    protected BeanSerializerBase withIgnorals(String[] toIgnore) {
        return this.withIgnorals(ArrayBuilders.arrayToSet(toIgnore));
    }

    protected abstract BeanSerializerBase asArraySerializer();

    public abstract BeanSerializerBase withFilterId(Object var1);

    protected BeanSerializerBase(BeanSerializerBase src) {
        this(src, src._props, src._filteredProps);
    }

    protected BeanSerializerBase(BeanSerializerBase src, NameTransformer unwrapper) {
        this(src, BeanSerializerBase.rename(src._props, unwrapper), BeanSerializerBase.rename(src._filteredProps, unwrapper));
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] props, NameTransformer transformer) {
        if (props == null || props.length == 0 || transformer == null || transformer == NameTransformer.NOP) {
            return props;
        }
        int len = props.length;
        BeanPropertyWriter[] result = new BeanPropertyWriter[len];
        for (int i2 = 0; i2 < len; ++i2) {
            BeanPropertyWriter bpw2 = props[i2];
            if (bpw2 == null) continue;
            result[i2] = bpw2.rename(transformer);
        }
        return result;
    }

    @Override
    public void resolve(SerializerProvider provider) throws JsonMappingException {
        int filteredCount = this._filteredProps == null ? 0 : this._filteredProps.length;
        int len = this._props.length;
        for (int i2 = 0; i2 < len; ++i2) {
            BeanPropertyWriter w2;
            JsonSerializer<Object> nullSer;
            BeanPropertyWriter prop = this._props[i2];
            if (!prop.willSuppressNulls() && !prop.hasNullSerializer() && (nullSer = provider.findNullValueSerializer(prop)) != null) {
                prop.assignNullSerializer(nullSer);
                if (i2 < filteredCount && (w2 = this._filteredProps[i2]) != null) {
                    w2.assignNullSerializer(nullSer);
                }
            }
            if (prop.hasSerializer()) continue;
            JsonSerializer<Object> ser = this.findConvertingSerializer(provider, prop);
            if (ser == null) {
                TypeSerializer typeSer;
                JavaType type = prop.getSerializationType();
                if (type == null && !(type = prop.getType()).isFinal()) {
                    if (!type.isContainerType() && type.containedTypeCount() <= 0) continue;
                    prop.setNonTrivialBaseType(type);
                    continue;
                }
                ser = provider.findValueSerializer(type, (BeanProperty)prop);
                if (type.isContainerType() && (typeSer = (TypeSerializer)type.getContentType().getTypeHandler()) != null && ser instanceof ContainerSerializer) {
                    ContainerSerializer<?> ser2 = ((ContainerSerializer)ser).withValueTypeSerializer(typeSer);
                    ser = ser2;
                }
            }
            if (i2 < filteredCount && (w2 = this._filteredProps[i2]) != null) {
                w2.assignSerializer(ser);
                continue;
            }
            prop.assignSerializer(ser);
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(provider);
        }
    }

    protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider provider, BeanPropertyWriter prop) throws JsonMappingException {
        Object convDef;
        AnnotatedMember m2;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if (intr != null && (m2 = prop.getMember()) != null && (convDef = intr.findSerializationConverter(m2)) != null) {
            Converter<Object, Object> conv = provider.converterInstance(prop.getMember(), convDef);
            JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
            JsonSerializer<Object> ser = delegateType.isJavaLangObject() ? null : provider.findValueSerializer(delegateType, (BeanProperty)prop);
            return new StdDelegatingSerializer(conv, delegateType, ser);
        }
        return null;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonSerializer<Object> ser;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember accessor = property == null || intr == null ? null : property.getMember();
        SerializationConfig config = provider.getConfig();
        JsonFormat.Value format = this.findFormatOverrides(provider, property, this.handledType());
        JsonFormat.Shape shape = null;
        if (format != null && format.hasShape() && (shape = format.getShape()) != JsonFormat.Shape.ANY && shape != this._serializationShape) {
            if (ClassUtil.isEnumType(this._handledType)) {
                switch (shape) {
                    case STRING: 
                    case NUMBER: 
                    case NUMBER_INT: {
                        BeanDescription desc = config.introspectClassAnnotations(this._beanType);
                        EnumSerializer ser2 = EnumSerializer.construct(this._beanType.getRawClass(), provider.getConfig(), desc, format);
                        return provider.handlePrimaryContextualization(ser2, property);
                    }
                }
            } else if (!(shape != JsonFormat.Shape.NATURAL || this._beanType.isMapLikeType() && Map.class.isAssignableFrom(this._handledType) || !Map.Entry.class.isAssignableFrom(this._handledType))) {
                JavaType mapEntryType = this._beanType.findSuperType(Map.Entry.class);
                JavaType kt = mapEntryType.containedTypeOrUnknown(0);
                JavaType vt2 = mapEntryType.containedTypeOrUnknown(1);
                MapEntrySerializer ser3 = new MapEntrySerializer(this._beanType, kt, vt2, false, null, property);
                return provider.handlePrimaryContextualization(ser3, property);
            }
        }
        ObjectIdWriter oiw = this._objectIdWriter;
        Set<String> ignoredProps = null;
        Object newFilterId = null;
        if (accessor != null) {
            Object filterId;
            ObjectIdInfo objectIdInfo;
            JsonIgnoreProperties.Value ignorals = intr.findPropertyIgnorals(accessor);
            if (ignorals != null) {
                ignoredProps = ignorals.findIgnoredForSerialization();
            }
            if ((objectIdInfo = intr.findObjectIdInfo(accessor)) == null) {
                if (oiw != null && (objectIdInfo = intr.findObjectReferenceInfo(accessor, null)) != null) {
                    oiw = this._objectIdWriter.withAlwaysAsId(objectIdInfo.getAlwaysAsId());
                }
            } else {
                PropertyBasedObjectIdGenerator gen;
                objectIdInfo = intr.findObjectReferenceInfo(accessor, objectIdInfo);
                Class<? extends ObjectIdGenerator<?>> implClass = objectIdInfo.getGeneratorType();
                JavaType type = provider.constructType(implClass);
                JavaType idType = provider.getTypeFactory().findTypeParameters(type, ObjectIdGenerator.class)[0];
                if (implClass == ObjectIdGenerators.PropertyGenerator.class) {
                    String propName = objectIdInfo.getPropertyName().getSimpleName();
                    BeanPropertyWriter idProp = null;
                    int i2 = 0;
                    int len = this._props.length;
                    while (true) {
                        BeanPropertyWriter prop;
                        if (i2 == len) {
                            provider.reportBadDefinition(this._beanType, String.format("Invalid Object Id definition for %s: cannot find property with name '%s'", this.handledType().getName(), propName));
                        }
                        if (propName.equals((prop = this._props[i2]).getName())) {
                            idProp = prop;
                            if (i2 <= 0) break;
                            System.arraycopy(this._props, 0, this._props, 1, i2);
                            this._props[0] = idProp;
                            if (this._filteredProps == null) break;
                            BeanPropertyWriter fp2 = this._filteredProps[i2];
                            System.arraycopy(this._filteredProps, 0, this._filteredProps, 1, i2);
                            this._filteredProps[0] = fp2;
                            break;
                        }
                        ++i2;
                    }
                    idType = idProp.getType();
                    gen = new PropertyBasedObjectIdGenerator(objectIdInfo, idProp);
                    oiw = ObjectIdWriter.construct(idType, null, gen, objectIdInfo.getAlwaysAsId());
                } else {
                    gen = provider.objectIdGeneratorInstance(accessor, objectIdInfo);
                    oiw = ObjectIdWriter.construct(idType, objectIdInfo.getPropertyName(), gen, objectIdInfo.getAlwaysAsId());
                }
            }
            if (!((filterId = intr.findFilterId(accessor)) == null || this._propertyFilterId != null && filterId.equals(this._propertyFilterId))) {
                newFilterId = filterId;
            }
        }
        BeanSerializerBase contextual = this;
        if (oiw != null && (oiw = oiw.withSerializer(ser = provider.findValueSerializer(oiw.idType, property))) != this._objectIdWriter) {
            contextual = contextual.withObjectIdWriter(oiw);
        }
        if (ignoredProps != null && !ignoredProps.isEmpty()) {
            contextual = contextual.withIgnorals(ignoredProps);
        }
        if (newFilterId != null) {
            contextual = contextual.withFilterId(newFilterId);
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == JsonFormat.Shape.ARRAY) {
            return contextual.asArraySerializer();
        }
        return contextual;
    }

    @Override
    public Iterator<PropertyWriter> properties() {
        return Arrays.asList(this._props).iterator();
    }

    @Override
    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    @Override
    public abstract void serialize(Object var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

    @Override
    public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        if (this._objectIdWriter != null) {
            gen.setCurrentValue(bean);
            this._serializeWithObjectId(bean, gen, provider, typeSer);
            return;
        }
        gen.setCurrentValue(bean);
        WritableTypeId typeIdDef = this._typeIdDef(typeSer, bean, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(gen, typeIdDef);
        if (this._propertyFilterId != null) {
            this.serializeFieldsFiltered(bean, gen, provider);
        } else {
            this.serializeFields(bean, gen, provider);
        }
        typeSer.writeTypeSuffix(gen, typeIdDef);
    }

    protected final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, boolean startEndObject) throws IOException {
        ObjectIdWriter w2 = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w2.generator);
        if (objectId.writeAsId(gen, provider, w2)) {
            return;
        }
        Object id2 = objectId.generateId(bean);
        if (w2.alwaysAsId) {
            w2.serializer.serialize(id2, gen, provider);
            return;
        }
        if (startEndObject) {
            gen.writeStartObject(bean);
        }
        objectId.writeAsField(gen, provider, w2);
        if (this._propertyFilterId != null) {
            this.serializeFieldsFiltered(bean, gen, provider);
        } else {
            this.serializeFields(bean, gen, provider);
        }
        if (startEndObject) {
            gen.writeEndObject();
        }
    }

    protected final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        ObjectIdWriter w2 = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w2.generator);
        if (objectId.writeAsId(gen, provider, w2)) {
            return;
        }
        Object id2 = objectId.generateId(bean);
        if (w2.alwaysAsId) {
            w2.serializer.serialize(id2, gen, provider);
            return;
        }
        this._serializeObjectId(bean, gen, provider, typeSer, objectId);
    }

    protected void _serializeObjectId(Object bean, JsonGenerator g2, SerializerProvider provider, TypeSerializer typeSer, WritableObjectId objectId) throws IOException {
        ObjectIdWriter w2 = this._objectIdWriter;
        WritableTypeId typeIdDef = this._typeIdDef(typeSer, bean, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(g2, typeIdDef);
        objectId.writeAsField(g2, provider, w2);
        if (this._propertyFilterId != null) {
            this.serializeFieldsFiltered(bean, g2, provider);
        } else {
            this.serializeFields(bean, g2, provider);
        }
        typeSer.writeTypeSuffix(g2, typeIdDef);
    }

    protected final WritableTypeId _typeIdDef(TypeSerializer typeSer, Object bean, JsonToken valueShape) {
        if (this._typeId == null) {
            return typeSer.typeId(bean, valueShape);
        }
        Object typeId = this._typeId.getValue(bean);
        if (typeId == null) {
            typeId = "";
        }
        return typeSer.typeId(bean, valueShape, typeId);
    }

    @Deprecated
    protected final String _customTypeId(Object bean) {
        Object typeId = this._typeId.getValue(bean);
        if (typeId == null) {
            return "";
        }
        return typeId instanceof String ? (String)typeId : typeId.toString();
    }

    protected void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        int i2;
        BeanPropertyWriter[] props = this._filteredProps != null && provider.getActiveView() != null ? this._filteredProps : this._props;
        try {
            for (BeanPropertyWriter prop : props) {
                if (prop == null) continue;
                prop.serializeAsField(bean, gen, provider);
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(bean, gen, provider);
            }
        }
        catch (Exception e2) {
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            this.wrapAndThrow(provider, (Throwable)e2, bean, name);
        }
        catch (StackOverflowError e3) {
            JsonMappingException mapE = new JsonMappingException((Closeable)gen, "Infinite recursion (StackOverflowError)", (Throwable)e3);
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            mapE.prependPath(new JsonMappingException.Reference(bean, name));
            throw mapE;
        }
    }

    protected void serializeFieldsFiltered(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonGenerationException {
        int i2;
        BeanPropertyWriter[] props = this._filteredProps != null && provider.getActiveView() != null ? this._filteredProps : this._props;
        PropertyFilter filter = this.findPropertyFilter(provider, this._propertyFilterId, bean);
        if (filter == null) {
            this.serializeFields(bean, gen, provider);
            return;
        }
        try {
            for (BeanPropertyWriter prop : props) {
                if (prop == null) continue;
                filter.serializeAsField(bean, gen, provider, prop);
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndFilter(bean, gen, provider, filter);
            }
        }
        catch (Exception e2) {
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            this.wrapAndThrow(provider, (Throwable)e2, bean, name);
        }
        catch (StackOverflowError e3) {
            JsonMappingException mapE = new JsonMappingException((Closeable)gen, "Infinite recursion (StackOverflowError)", (Throwable)e3);
            String name = i2 == props.length ? "[anySetter]" : props[i2].getName();
            mapE.prependPath(new JsonMappingException.Reference(bean, name));
            throw mapE;
        }
    }

    @Override
    @Deprecated
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        String id2;
        ObjectNode o2 = this.createSchemaNode("object", true);
        JsonSerializableSchema ann2 = this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (ann2 != null && (id2 = ann2.id()) != null && id2.length() > 0) {
            o2.put("id", id2);
        }
        ObjectNode propertiesNode = o2.objectNode();
        PropertyFilter filter = this._propertyFilterId != null ? this.findPropertyFilter(provider, this._propertyFilterId, null) : null;
        for (int i2 = 0; i2 < this._props.length; ++i2) {
            BeanPropertyWriter prop = this._props[i2];
            if (filter == null) {
                prop.depositSchemaProperty(propertiesNode, provider);
                continue;
            }
            filter.depositSchemaProperty((PropertyWriter)prop, propertiesNode, provider);
        }
        o2.set("properties", propertiesNode);
        return o2;
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (visitor == null) {
            return;
        }
        JsonObjectFormatVisitor objectVisitor = visitor.expectObjectFormat(typeHint);
        if (objectVisitor == null) {
            return;
        }
        SerializerProvider provider = visitor.getProvider();
        if (this._propertyFilterId != null) {
            PropertyFilter filter = this.findPropertyFilter(visitor.getProvider(), this._propertyFilterId, null);
            int end = this._props.length;
            for (int i2 = 0; i2 < end; ++i2) {
                filter.depositSchemaProperty((PropertyWriter)this._props[i2], objectVisitor, provider);
            }
        } else {
            Class<?> view = this._filteredProps == null || provider == null ? null : provider.getActiveView();
            for (BeanPropertyWriter prop : view != null ? this._filteredProps : this._props) {
                if (prop == null) continue;
                prop.depositSchemaProperty(objectVisitor, provider);
            }
        }
    }
}

