/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ContainerNode<T extends ContainerNode<T>>
extends BaseJsonNode
implements JsonNodeCreator {
    private static final long serialVersionUID = 1L;
    protected final JsonNodeFactory _nodeFactory;

    protected ContainerNode(JsonNodeFactory nc2) {
        this._nodeFactory = nc2;
    }

    protected ContainerNode() {
        this._nodeFactory = null;
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public String asText() {
        return "";
    }

    @Override
    public abstract int size();

    @Override
    public abstract JsonNode get(int var1);

    @Override
    public abstract JsonNode get(String var1);

    @Override
    public final BooleanNode booleanNode(boolean v2) {
        return this._nodeFactory.booleanNode(v2);
    }

    public JsonNode missingNode() {
        return this._nodeFactory.missingNode();
    }

    @Override
    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    @Override
    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    @Override
    public final ArrayNode arrayNode(int capacity) {
        return this._nodeFactory.arrayNode(capacity);
    }

    @Override
    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    @Override
    public final NumericNode numberNode(byte v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(short v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(int v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(long v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(float v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final NumericNode numberNode(double v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(BigInteger v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(BigDecimal v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Byte v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Short v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Integer v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Long v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Float v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final ValueNode numberNode(Double v2) {
        return this._nodeFactory.numberNode(v2);
    }

    @Override
    public final TextNode textNode(String text) {
        return this._nodeFactory.textNode(text);
    }

    @Override
    public final BinaryNode binaryNode(byte[] data) {
        return this._nodeFactory.binaryNode(data);
    }

    @Override
    public final BinaryNode binaryNode(byte[] data, int offset, int length) {
        return this._nodeFactory.binaryNode(data, offset, length);
    }

    @Override
    public final ValueNode pojoNode(Object pojo) {
        return this._nodeFactory.pojoNode(pojo);
    }

    @Override
    public final ValueNode rawValueNode(RawValue value) {
        return this._nodeFactory.rawValueNode(value);
    }

    public abstract T removeAll();
}

