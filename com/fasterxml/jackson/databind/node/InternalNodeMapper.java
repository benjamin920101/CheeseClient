/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;

final class InternalNodeMapper {
    private static final JsonMapper JSON_MAPPER = new JsonMapper();
    private static final ObjectWriter STD_WRITER = JSON_MAPPER.writer();
    private static final ObjectWriter PRETTY_WRITER = JSON_MAPPER.writer().withDefaultPrettyPrinter();
    private static final ObjectReader NODE_READER = JSON_MAPPER.readerFor(JsonNode.class);

    InternalNodeMapper() {
    }

    public static String nodeToString(JsonNode n2) {
        try {
            return STD_WRITER.writeValueAsString(n2);
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String nodeToPrettyString(JsonNode n2) {
        try {
            return PRETTY_WRITER.writeValueAsString(n2);
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static byte[] valueToBytes(Object value) throws IOException {
        return JSON_MAPPER.writeValueAsBytes(value);
    }

    public static JsonNode bytesToNode(byte[] json) throws IOException {
        return (JsonNode)NODE_READER.readValue(json);
    }
}

