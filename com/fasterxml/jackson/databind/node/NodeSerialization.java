/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.node.InternalNodeMapper;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

class NodeSerialization
implements Serializable,
Externalizable {
    private static final long serialVersionUID = 1L;
    public byte[] json;

    public NodeSerialization() {
    }

    public NodeSerialization(byte[] b2) {
        this.json = b2;
    }

    protected Object readResolve() {
        try {
            return InternalNodeMapper.bytesToNode(this.json);
        }
        catch (IOException e2) {
            throw new IllegalArgumentException("Failed to JDK deserialize `JsonNode` value: " + e2.getMessage(), e2);
        }
    }

    public static NodeSerialization from(Object o2) {
        try {
            return new NodeSerialization(InternalNodeMapper.valueToBytes(o2));
        }
        catch (IOException e2) {
            throw new IllegalArgumentException("Failed to JDK serialize `" + o2.getClass().getSimpleName() + "` value: " + e2.getMessage(), e2);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.json.length);
        out.write(this.json);
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException {
        int len = in2.readInt();
        this.json = new byte[len];
        in2.readFully(this.json, 0, len);
    }
}

