/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.cfg.PackageVersion;

public class JsonMapper
extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public JsonMapper() {
        this(new JsonFactory());
    }

    public JsonMapper(JsonFactory f2) {
        super(f2);
    }

    protected JsonMapper(JsonMapper src) {
        super(src);
    }

    @Override
    public JsonMapper copy() {
        this._checkInvalidCopy(JsonMapper.class);
        return new JsonMapper(this);
    }

    public static Builder builder() {
        return new Builder(new JsonMapper());
    }

    public static Builder builder(JsonFactory streamFactory) {
        return new Builder(new JsonMapper(streamFactory));
    }

    public Builder rebuild() {
        return new Builder(this.copy());
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override
    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public boolean isEnabled(JsonReadFeature f2) {
        return this.isEnabled(f2.mappedFeature());
    }

    public boolean isEnabled(JsonWriteFeature f2) {
        return this.isEnabled(f2.mappedFeature());
    }

    public static class Builder
    extends MapperBuilder<JsonMapper, Builder> {
        public Builder(JsonMapper m2) {
            super(m2);
        }

        public Builder enable(JsonReadFeature ... features) {
            for (JsonReadFeature f2 : features) {
                ((JsonMapper)this._mapper).enable(f2.mappedFeature());
            }
            return this;
        }

        public Builder disable(JsonReadFeature ... features) {
            for (JsonReadFeature f2 : features) {
                ((JsonMapper)this._mapper).disable(f2.mappedFeature());
            }
            return this;
        }

        public Builder configure(JsonReadFeature f2, boolean state) {
            if (state) {
                ((JsonMapper)this._mapper).enable(f2.mappedFeature());
            } else {
                ((JsonMapper)this._mapper).disable(f2.mappedFeature());
            }
            return this;
        }

        public Builder enable(JsonWriteFeature ... features) {
            for (JsonWriteFeature f2 : features) {
                ((JsonMapper)this._mapper).enable(f2.mappedFeature());
            }
            return this;
        }

        public Builder disable(JsonWriteFeature ... features) {
            for (JsonWriteFeature f2 : features) {
                ((JsonMapper)this._mapper).disable(f2.mappedFeature());
            }
            return this;
        }

        public Builder configure(JsonWriteFeature f2, boolean state) {
            if (state) {
                ((JsonMapper)this._mapper).enable(f2.mappedFeature());
            } else {
                ((JsonMapper)this._mapper).disable(f2.mappedFeature());
            }
            return this;
        }
    }
}

