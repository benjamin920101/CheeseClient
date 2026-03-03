/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataType;
import net.dv8tion.jda.api.utils.data.SerializableArray;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.api.utils.data.etf.ExTermDecoder;
import net.dv8tion.jda.api.utils.data.etf.ExTermEncoder;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataObject
implements SerializableData {
    private static final Logger log = LoggerFactory.getLogger(DataObject.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleModule module = new SimpleModule();
    private static final MapType mapType;
    protected final Map<String, Object> data;

    protected DataObject(@Nonnull Map<String, Object> data) {
        this.data = data;
    }

    @Nonnull
    public static DataObject empty() {
        return new DataObject(new HashMap<String, Object>());
    }

    @Nonnull
    public static DataObject fromJson(@Nonnull byte[] data) {
        try {
            Map map = (Map)mapper.readValue(data, (JavaType)mapType);
            return new DataObject(map);
        }
        catch (IOException ex2) {
            throw new ParsingException(ex2);
        }
    }

    @Nonnull
    public static DataObject fromJson(@Nonnull String json) {
        try {
            Map map = (Map)mapper.readValue(json, (JavaType)mapType);
            return new DataObject(map);
        }
        catch (IOException ex2) {
            throw new ParsingException(ex2);
        }
    }

    @Nonnull
    public static DataObject fromJson(@Nonnull InputStream stream) {
        try {
            Map map = (Map)mapper.readValue(stream, (JavaType)mapType);
            return new DataObject(map);
        }
        catch (IOException ex2) {
            throw new ParsingException(ex2);
        }
    }

    @Nonnull
    public static DataObject fromJson(@Nonnull Reader stream) {
        try {
            Map map = (Map)mapper.readValue(stream, (JavaType)mapType);
            return new DataObject(map);
        }
        catch (IOException ex2) {
            throw new ParsingException(ex2);
        }
    }

    @Nonnull
    public static DataObject fromETF(@Nonnull byte[] data) {
        Checks.notNull(data, "Data");
        try {
            Map<String, Object> map = ExTermDecoder.unpackMap(ByteBuffer.wrap(data));
            return new DataObject(map);
        }
        catch (Exception ex2) {
            log.error("Failed to parse ETF data {}", (Object)Arrays.toString(data), (Object)ex2);
            throw new ParsingException(ex2);
        }
    }

    public boolean hasKey(@Nonnull String key) {
        return this.data.containsKey(key);
    }

    public boolean isNull(@Nonnull String key) {
        return this.data.get(key) == null;
    }

    public boolean isType(@Nonnull String key, @Nonnull DataType type) {
        return type.isType(this.data.get(key));
    }

    @Nonnull
    public DataObject getObject(@Nonnull String key) {
        return this.optObject(key).orElseThrow(() -> this.valueError(key, "DataObject"));
    }

    @Nonnull
    public Optional<DataObject> optObject(@Nonnull String key) {
        Map child = null;
        try {
            child = this.get(Map.class, key);
        }
        catch (ClassCastException ex2) {
            log.error("Unable to extract child data", ex2);
        }
        return child == null ? Optional.empty() : Optional.of(new DataObject(child));
    }

    @Nonnull
    public DataArray getArray(@Nonnull String key) {
        return this.optArray(key).orElseThrow(() -> this.valueError(key, "DataArray"));
    }

    @Nonnull
    public Optional<DataArray> optArray(@Nonnull String key) {
        List child = null;
        try {
            child = this.get(List.class, key);
        }
        catch (ClassCastException ex2) {
            log.error("Unable to extract child data", ex2);
        }
        return child == null ? Optional.empty() : Optional.of(new DataArray(child));
    }

    @Nonnull
    public Optional<Object> opt(@Nonnull String key) {
        return Optional.ofNullable(this.data.get(key));
    }

    @Nonnull
    public Object get(@Nonnull String key) {
        Object value = this.data.get(key);
        if (value == null) {
            throw this.valueError(key, "any");
        }
        return value;
    }

    @Nonnull
    public String getString(@Nonnull String key) {
        String value = this.getString(key, null);
        if (value == null) {
            throw this.valueError(key, "String");
        }
        return value;
    }

    @Contract(value="_, !null -> !null")
    public String getString(@Nonnull String key, @Nullable String defaultValue) {
        String value = this.get(String.class, key, UnaryOperator.identity(), String::valueOf);
        return value == null ? defaultValue : value;
    }

    public boolean getBoolean(@Nonnull String key) {
        return this.getBoolean(key, false);
    }

    public boolean getBoolean(@Nonnull String key, boolean defaultValue) {
        Boolean value = this.get(Boolean.class, key, Boolean::parseBoolean, null);
        return value == null ? defaultValue : value;
    }

    public long getLong(@Nonnull String key) {
        Long value = this.get(Long.class, key, MiscUtil::parseLong, Number::longValue);
        if (value == null) {
            throw this.valueError(key, "long");
        }
        return value;
    }

    public long getLong(@Nonnull String key, long defaultValue) {
        Long value = this.get(Long.class, key, Long::parseLong, Number::longValue);
        return value == null ? defaultValue : value;
    }

    public long getUnsignedLong(@Nonnull String key) {
        Long value = this.get(Long.class, key, Long::parseUnsignedLong, Number::longValue);
        if (value == null) {
            throw this.valueError(key, "unsigned long");
        }
        return value;
    }

    public long getUnsignedLong(@Nonnull String key, long defaultValue) {
        Long value = this.get(Long.class, key, Long::parseUnsignedLong, Number::longValue);
        return value == null ? defaultValue : value;
    }

    public int getInt(@Nonnull String key) {
        Integer value = this.get(Integer.class, key, Integer::parseInt, Number::intValue);
        if (value == null) {
            throw this.valueError(key, "int");
        }
        return value;
    }

    public int getInt(@Nonnull String key, int defaultValue) {
        Integer value = this.get(Integer.class, key, Integer::parseInt, Number::intValue);
        return value == null ? defaultValue : value;
    }

    public int getUnsignedInt(@Nonnull String key) {
        Integer value = this.get(Integer.class, key, Integer::parseUnsignedInt, Number::intValue);
        if (value == null) {
            throw this.valueError(key, "unsigned int");
        }
        return value;
    }

    public int getUnsignedInt(@Nonnull String key, int defaultValue) {
        Integer value = this.get(Integer.class, key, Integer::parseUnsignedInt, Number::intValue);
        return value == null ? defaultValue : value;
    }

    @Nonnull
    public DataObject remove(@Nonnull String key) {
        this.data.remove(key);
        return this;
    }

    @Nonnull
    public DataObject putNull(@Nonnull String key) {
        this.data.put(key, null);
        return this;
    }

    @Nonnull
    public DataObject put(@Nonnull String key, @Nullable Object value) {
        if (value instanceof SerializableData) {
            this.data.put(key, ((SerializableData)value).toData().data);
        } else if (value instanceof SerializableArray) {
            this.data.put(key, ((SerializableArray)value).toDataArray().data);
        } else {
            this.data.put(key, value);
        }
        return this;
    }

    @Nonnull
    public Collection<Object> values() {
        return this.data.values();
    }

    @Nonnull
    public Set<String> keys() {
        return this.data.keySet();
    }

    @Nonnull
    public byte[] toJson() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mapper.writeValue(outputStream, this.data);
            return outputStream.toByteArray();
        }
        catch (IOException e2) {
            throw new UncheckedIOException(e2);
        }
    }

    @Nonnull
    public byte[] toETF() {
        ByteBuffer buffer = ExTermEncoder.pack(this.data);
        return Arrays.copyOfRange(buffer.array(), buffer.arrayOffset(), buffer.arrayOffset() + buffer.limit());
    }

    public String toString() {
        try {
            return mapper.writeValueAsString(this.data);
        }
        catch (JsonProcessingException e2) {
            throw new ParsingException(e2);
        }
    }

    @Nonnull
    public String toPrettyString() {
        DefaultIndenter indent = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.withObjectIndenter(indent).withArrayIndenter(indent);
        try {
            return mapper.writer(printer).writeValueAsString(this.data);
        }
        catch (JsonProcessingException e2) {
            throw new ParsingException(e2);
        }
    }

    @Nonnull
    public Map<String, Object> toMap() {
        return this.data;
    }

    @Override
    @Nonnull
    public DataObject toData() {
        return this;
    }

    private ParsingException valueError(String key, String expectedType) {
        return new ParsingException("Unable to resolve value with key " + key + " to type " + expectedType + ": " + this.data.get(key));
    }

    @Nullable
    private <T> T get(@Nonnull Class<T> type, @Nonnull String key) {
        return this.get(type, key, null, null);
    }

    @Nullable
    private <T> T get(@Nonnull Class<T> type, @Nonnull String key, @Nullable Function<String, T> stringParse, @Nullable Function<Number, T> numberParse) {
        Object value = this.data.get(key);
        if (value == null) {
            return null;
        }
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        if (type == String.class) {
            return type.cast(value.toString());
        }
        if (value instanceof Number && numberParse != null) {
            return numberParse.apply((Number)value);
        }
        if (value instanceof String && stringParse != null) {
            return stringParse.apply((String)value);
        }
        throw new ParsingException(Helpers.format("Cannot parse value for %s into type %s: %s instance of %s", key, type.getSimpleName(), value, value.getClass().getSimpleName()));
    }

    static {
        module.addAbstractTypeMapping(Map.class, HashMap.class);
        module.addAbstractTypeMapping(List.class, ArrayList.class);
        mapper.registerModule(module);
        mapType = mapper.getTypeFactory().constructRawMapType(HashMap.class);
    }
}

