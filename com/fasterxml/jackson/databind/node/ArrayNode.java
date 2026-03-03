/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayNode
extends ContainerNode<ArrayNode>
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<JsonNode> _children;

    public ArrayNode(JsonNodeFactory nf) {
        super(nf);
        this._children = new ArrayList<JsonNode>();
    }

    public ArrayNode(JsonNodeFactory nf, int capacity) {
        super(nf);
        this._children = new ArrayList<JsonNode>(capacity);
    }

    public ArrayNode(JsonNodeFactory nf, List<JsonNode> children) {
        super(nf);
        this._children = children;
    }

    @Override
    protected JsonNode _at(JsonPointer ptr) {
        return this.get(ptr.getMatchingIndex());
    }

    public ArrayNode deepCopy() {
        ArrayNode ret = new ArrayNode(this._nodeFactory);
        for (JsonNode element : this._children) {
            ret._children.add((JsonNode)element.deepCopy());
        }
        return ret;
    }

    @Override
    public boolean isEmpty(SerializerProvider serializers) {
        return this._children.isEmpty();
    }

    @Override
    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    @Override
    public int size() {
        return this._children.size();
    }

    @Override
    public boolean isEmpty() {
        return this._children.isEmpty();
    }

    @Override
    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    @Override
    public JsonNode get(int index) {
        if (index >= 0 && index < this._children.size()) {
            return this._children.get(index);
        }
        return null;
    }

    @Override
    public JsonNode get(String fieldName) {
        return null;
    }

    @Override
    public JsonNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    @Override
    public JsonNode path(int index) {
        if (index >= 0 && index < this._children.size()) {
            return this._children.get(index);
        }
        return MissingNode.getInstance();
    }

    @Override
    public JsonNode required(int index) {
        if (index >= 0 && index < this._children.size()) {
            return this._children.get(index);
        }
        return (JsonNode)this._reportRequiredViolation("No value at index #%d [0, %d) of `ArrayNode`", index, this._children.size());
    }

    @Override
    public boolean equals(Comparator<JsonNode> comparator, JsonNode o2) {
        if (!(o2 instanceof ArrayNode)) {
            return false;
        }
        ArrayNode other = (ArrayNode)o2;
        int len = this._children.size();
        if (other.size() != len) {
            return false;
        }
        List<JsonNode> l1 = this._children;
        List<JsonNode> l2 = other._children;
        for (int i2 = 0; i2 < len; ++i2) {
            if (l1.get(i2).equals(comparator, l2.get(i2))) continue;
            return false;
        }
        return true;
    }

    @Override
    public void serialize(JsonGenerator f2, SerializerProvider provider) throws IOException {
        List<JsonNode> c2 = this._children;
        int size = c2.size();
        f2.writeStartArray(this, size);
        for (int i2 = 0; i2 < size; ++i2) {
            JsonNode n2 = c2.get(i2);
            ((BaseJsonNode)n2).serialize(f2, provider);
        }
        f2.writeEndArray();
    }

    @Override
    public void serializeWithType(JsonGenerator g2, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g2, typeSer.typeId(this, JsonToken.START_ARRAY));
        for (JsonNode n2 : this._children) {
            ((BaseJsonNode)n2).serialize(g2, provider);
        }
        typeSer.writeTypeSuffix(g2, typeIdDef);
    }

    @Override
    public JsonNode findValue(String fieldName) {
        for (JsonNode node : this._children) {
            JsonNode value = node.findValue(fieldName);
            if (value == null) continue;
            return value;
        }
        return null;
    }

    @Override
    public List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
        for (JsonNode node : this._children) {
            foundSoFar = node.findValues(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        for (JsonNode node : this._children) {
            foundSoFar = node.findValuesAsText(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    @Override
    public ObjectNode findParent(String fieldName) {
        for (JsonNode node : this._children) {
            JsonNode parent = node.findParent(fieldName);
            if (parent == null) continue;
            return (ObjectNode)parent;
        }
        return null;
    }

    @Override
    public List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
        for (JsonNode node : this._children) {
            foundSoFar = node.findParents(fieldName, foundSoFar);
        }
        return foundSoFar;
    }

    public JsonNode set(int index, JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        if (index < 0 || index >= this._children.size()) {
            throw new IndexOutOfBoundsException("Illegal index " + index + ", array size " + this.size());
        }
        return this._children.set(index, value);
    }

    public ArrayNode add(JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        this._add(value);
        return this;
    }

    public ArrayNode addAll(ArrayNode other) {
        this._children.addAll(other._children);
        return this;
    }

    public ArrayNode addAll(Collection<? extends JsonNode> nodes) {
        for (JsonNode jsonNode : nodes) {
            this.add(jsonNode);
        }
        return this;
    }

    public ArrayNode insert(int index, JsonNode value) {
        if (value == null) {
            value = this.nullNode();
        }
        this._insert(index, value);
        return this;
    }

    public JsonNode remove(int index) {
        if (index >= 0 && index < this._children.size()) {
            return this._children.remove(index);
        }
        return null;
    }

    @Override
    public ArrayNode removeAll() {
        this._children.clear();
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode n2 = this.arrayNode();
        this._add(n2);
        return n2;
    }

    public ObjectNode addObject() {
        ObjectNode n2 = this.objectNode();
        this._add(n2);
        return n2;
    }

    public ArrayNode addPOJO(Object value) {
        if (value == null) {
            this.addNull();
        } else {
            this._add(this.pojoNode(value));
        }
        return this;
    }

    public ArrayNode addRawValue(RawValue raw) {
        if (raw == null) {
            this.addNull();
        } else {
            this._add(this.rawValueNode(raw));
        }
        return this;
    }

    public ArrayNode addNull() {
        this._add(this.nullNode());
        return this;
    }

    public ArrayNode add(int v2) {
        this._add(this.numberNode(v2));
        return this;
    }

    public ArrayNode add(Integer value) {
        if (value == null) {
            return this.addNull();
        }
        return this._add(this.numberNode((int)value));
    }

    public ArrayNode add(long v2) {
        return this._add(this.numberNode(v2));
    }

    public ArrayNode add(Long value) {
        if (value == null) {
            return this.addNull();
        }
        return this._add(this.numberNode((long)value));
    }

    public ArrayNode add(float v2) {
        return this._add(this.numberNode(v2));
    }

    public ArrayNode add(Float value) {
        if (value == null) {
            return this.addNull();
        }
        return this._add(this.numberNode(value.floatValue()));
    }

    public ArrayNode add(double v2) {
        return this._add(this.numberNode(v2));
    }

    public ArrayNode add(Double value) {
        if (value == null) {
            return this.addNull();
        }
        return this._add(this.numberNode((double)value));
    }

    public ArrayNode add(BigDecimal v2) {
        if (v2 == null) {
            return this.addNull();
        }
        return this._add(this.numberNode(v2));
    }

    public ArrayNode add(BigInteger v2) {
        if (v2 == null) {
            return this.addNull();
        }
        return this._add(this.numberNode(v2));
    }

    public ArrayNode add(String v2) {
        if (v2 == null) {
            return this.addNull();
        }
        return this._add(this.textNode(v2));
    }

    public ArrayNode add(boolean v2) {
        return this._add(this.booleanNode(v2));
    }

    public ArrayNode add(Boolean value) {
        if (value == null) {
            return this.addNull();
        }
        return this._add(this.booleanNode(value));
    }

    public ArrayNode add(byte[] v2) {
        if (v2 == null) {
            return this.addNull();
        }
        return this._add(this.binaryNode(v2));
    }

    public ArrayNode insertArray(int index) {
        ArrayNode n2 = this.arrayNode();
        this._insert(index, n2);
        return n2;
    }

    public ObjectNode insertObject(int index) {
        ObjectNode n2 = this.objectNode();
        this._insert(index, n2);
        return n2;
    }

    public ArrayNode insertPOJO(int index, Object value) {
        if (value == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.pojoNode(value));
    }

    public ArrayNode insertNull(int index) {
        this._insert(index, this.nullNode());
        return this;
    }

    public ArrayNode insert(int index, int v2) {
        this._insert(index, this.numberNode(v2));
        return this;
    }

    public ArrayNode insert(int index, Integer value) {
        if (value == null) {
            this.insertNull(index);
        } else {
            this._insert(index, this.numberNode((int)value));
        }
        return this;
    }

    public ArrayNode insert(int index, long v2) {
        return this._insert(index, this.numberNode(v2));
    }

    public ArrayNode insert(int index, Long value) {
        if (value == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.numberNode((long)value));
    }

    public ArrayNode insert(int index, float v2) {
        return this._insert(index, this.numberNode(v2));
    }

    public ArrayNode insert(int index, Float value) {
        if (value == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.numberNode(value.floatValue()));
    }

    public ArrayNode insert(int index, double v2) {
        return this._insert(index, this.numberNode(v2));
    }

    public ArrayNode insert(int index, Double value) {
        if (value == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.numberNode((double)value));
    }

    public ArrayNode insert(int index, BigDecimal v2) {
        if (v2 == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.numberNode(v2));
    }

    public ArrayNode insert(int index, BigInteger v2) {
        if (v2 == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.numberNode(v2));
    }

    public ArrayNode insert(int index, String v2) {
        if (v2 == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.textNode(v2));
    }

    public ArrayNode insert(int index, boolean v2) {
        return this._insert(index, this.booleanNode(v2));
    }

    public ArrayNode insert(int index, Boolean value) {
        if (value == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.booleanNode(value));
    }

    public ArrayNode insert(int index, byte[] v2) {
        if (v2 == null) {
            return this.insertNull(index);
        }
        return this._insert(index, this.binaryNode(v2));
    }

    @Override
    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (o2 == null) {
            return false;
        }
        if (o2 instanceof ArrayNode) {
            return this._children.equals(((ArrayNode)o2)._children);
        }
        return false;
    }

    protected boolean _childrenEqual(ArrayNode other) {
        return this._children.equals(other._children);
    }

    @Override
    public int hashCode() {
        return this._children.hashCode();
    }

    protected ArrayNode _add(JsonNode node) {
        this._children.add(node);
        return this;
    }

    protected ArrayNode _insert(int index, JsonNode node) {
        if (index < 0) {
            this._children.add(0, node);
        } else if (index >= this._children.size()) {
            this._children.add(node);
        } else {
            this._children.add(index, node);
        }
        return this;
    }
}

