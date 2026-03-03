/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.NodeCursor;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser
extends ParserMinimalBase {
    protected ObjectCodec _objectCodec;
    protected NodeCursor _nodeCursor;
    protected JsonToken _nextToken;
    protected boolean _startContainer;
    protected boolean _closed;

    public TreeTraversingParser(JsonNode n2) {
        this(n2, null);
    }

    public TreeTraversingParser(JsonNode n2, ObjectCodec codec) {
        super(0);
        this._objectCodec = codec;
        if (n2.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.ArrayCursor(n2, null);
        } else if (n2.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.ObjectCursor(n2, null);
        } else {
            this._nodeCursor = new NodeCursor.RootCursor(n2, null);
        }
    }

    @Override
    public void setCodec(ObjectCodec c2) {
        this._objectCodec = c2;
    }

    @Override
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override
    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    @Override
    public JsonToken nextToken() throws IOException, JsonParseException {
        if (this._nextToken != null) {
            this._currToken = this._nextToken;
            this._nextToken = null;
            return this._currToken;
        }
        if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        }
        if (this._nodeCursor == null) {
            this._closed = true;
            return null;
        }
        this._currToken = this._nodeCursor.nextToken();
        if (this._currToken != null) {
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        }
        this._currToken = this._nodeCursor.endToken();
        this._nodeCursor = this._nodeCursor.getParent();
        return this._currToken;
    }

    @Override
    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    @Override
    public boolean isClosed() {
        return this._closed;
    }

    @Override
    public String getCurrentName() {
        return this._nodeCursor == null ? null : this._nodeCursor.getCurrentName();
    }

    @Override
    public void overrideCurrentName(String name) {
        if (this._nodeCursor != null) {
            this._nodeCursor.overrideCurrentName(name);
        }
    }

    @Override
    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    @Override
    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    @Override
    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME: {
                return this._nodeCursor.getCurrentName();
            }
            case VALUE_STRING: {
                return this.currentNode().textValue();
            }
            case VALUE_NUMBER_INT: 
            case VALUE_NUMBER_FLOAT: {
                return String.valueOf(this.currentNode().numberValue());
            }
            case VALUE_EMBEDDED_OBJECT: {
                JsonNode n2 = this.currentNode();
                if (n2 == null || !n2.isBinary()) break;
                return n2.asText();
            }
        }
        return this._currToken == null ? null : this._currToken.asString();
    }

    @Override
    public char[] getTextCharacters() throws IOException, JsonParseException {
        return this.getText().toCharArray();
    }

    @Override
    public int getTextLength() throws IOException, JsonParseException {
        return this.getText().length();
    }

    @Override
    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    @Override
    public boolean hasTextCharacters() {
        return false;
    }

    @Override
    public JsonParser.NumberType getNumberType() throws IOException {
        JsonNode n2 = this.currentNumericNode();
        return n2 == null ? null : n2.numberType();
    }

    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        return this.currentNumericNode().bigIntegerValue();
    }

    @Override
    public BigDecimal getDecimalValue() throws IOException {
        return this.currentNumericNode().decimalValue();
    }

    @Override
    public double getDoubleValue() throws IOException {
        return this.currentNumericNode().doubleValue();
    }

    @Override
    public float getFloatValue() throws IOException {
        return (float)this.currentNumericNode().doubleValue();
    }

    @Override
    public int getIntValue() throws IOException {
        NumericNode node = (NumericNode)this.currentNumericNode();
        if (!node.canConvertToInt()) {
            this.reportOverflowInt();
        }
        return node.intValue();
    }

    @Override
    public long getLongValue() throws IOException {
        NumericNode node = (NumericNode)this.currentNumericNode();
        if (!node.canConvertToLong()) {
            this.reportOverflowLong();
        }
        return node.longValue();
    }

    @Override
    public Number getNumberValue() throws IOException {
        return this.currentNumericNode().numberValue();
    }

    @Override
    public Object getEmbeddedObject() {
        JsonNode n2;
        if (!this._closed && (n2 = this.currentNode()) != null) {
            if (n2.isPojo()) {
                return ((POJONode)n2).getPojo();
            }
            if (n2.isBinary()) {
                return ((BinaryNode)n2).binaryValue();
            }
        }
        return null;
    }

    @Override
    public boolean isNaN() {
        JsonNode n2;
        if (!this._closed && (n2 = this.currentNode()) instanceof NumericNode) {
            return ((NumericNode)n2).isNaN();
        }
        return false;
    }

    @Override
    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException, JsonParseException {
        JsonNode n2 = this.currentNode();
        if (n2 != null) {
            if (n2 instanceof TextNode) {
                return ((TextNode)n2).getBinaryValue(b64variant);
            }
            return n2.binaryValue();
        }
        return null;
    }

    @Override
    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException, JsonParseException {
        byte[] data = this.getBinaryValue(b64variant);
        if (data != null) {
            out.write(data, 0, data.length);
            return data.length;
        }
        return 0;
    }

    protected JsonNode currentNode() {
        if (this._closed || this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() throws JsonParseException {
        JsonNode n2 = this.currentNode();
        if (n2 == null || !n2.isNumber()) {
            JsonToken t2 = n2 == null ? null : n2.asToken();
            throw this._constructError("Current token (" + (Object)((Object)t2) + ") not numeric, cannot use numeric value accessors");
        }
        return n2;
    }

    @Override
    protected void _handleEOF() throws JsonParseException {
        this._throwInternal();
    }
}

