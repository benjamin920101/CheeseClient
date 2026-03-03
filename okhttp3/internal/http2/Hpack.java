/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Huffman;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

final class Hpack {
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    static final Header[] STATIC_HEADER_TABLE = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
    static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = Hpack.nameToFirstIndex();

    private Hpack() {
    }

    private static Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap<ByteString, Integer> result = new LinkedHashMap<ByteString, Integer>(STATIC_HEADER_TABLE.length);
        for (int i2 = 0; i2 < STATIC_HEADER_TABLE.length; ++i2) {
            if (result.containsKey(Hpack.STATIC_HEADER_TABLE[i2].name)) continue;
            result.put(Hpack.STATIC_HEADER_TABLE[i2].name, i2);
        }
        return Collections.unmodifiableMap(result);
    }

    static ByteString checkLowercase(ByteString name) throws IOException {
        int length = name.size();
        for (int i2 = 0; i2 < length; ++i2) {
            byte c2 = name.getByte(i2);
            if (c2 < 65 || c2 > 90) continue;
            throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.utf8());
        }
        return name;
    }

    static final class Writer {
        private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
        private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
        private final Buffer out;
        private final boolean useCompression;
        private int smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
        private boolean emitDynamicTableSizeUpdate;
        int headerTableSizeSetting;
        int maxDynamicTableByteCount;
        Header[] dynamicTable = new Header[8];
        int nextHeaderIndex = this.dynamicTable.length - 1;
        int headerCount = 0;
        int dynamicTableByteCount = 0;

        Writer(Buffer out) {
            this(4096, true, out);
        }

        Writer(int headerTableSizeSetting, boolean useCompression, Buffer out) {
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.maxDynamicTableByteCount = headerTableSizeSetting;
            this.useCompression = useCompression;
            this.out = out;
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int bytesToRecover) {
            int entriesToEvict = 0;
            if (bytesToRecover > 0) {
                for (int j2 = this.dynamicTable.length - 1; j2 >= this.nextHeaderIndex && bytesToRecover > 0; --j2) {
                    bytesToRecover -= this.dynamicTable[j2].hpackSize;
                    this.dynamicTableByteCount -= this.dynamicTable[j2].hpackSize;
                    --this.headerCount;
                    ++entriesToEvict;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
                Arrays.fill(this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + entriesToEvict, null);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        private void insertIntoDynamicTable(Header entry) {
            int delta = entry.hpackSize;
            if (delta > this.maxDynamicTableByteCount) {
                this.clearDynamicTable();
                return;
            }
            int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
            this.evictToRecoverBytes(bytesToRecover);
            if (this.headerCount + 1 > this.dynamicTable.length) {
                Header[] doubled = new Header[this.dynamicTable.length * 2];
                System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = doubled;
            }
            int index = this.nextHeaderIndex--;
            this.dynamicTable[index] = entry;
            ++this.headerCount;
            this.dynamicTableByteCount += delta;
        }

        void writeHeaders(List<Header> headerBlock) throws IOException {
            if (this.emitDynamicTableSizeUpdate) {
                if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount) {
                    this.writeInt(this.smallestHeaderTableSizeSetting, 31, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                this.writeInt(this.maxDynamicTableByteCount, 31, 32);
            }
            int size = headerBlock.size();
            for (int i2 = 0; i2 < size; ++i2) {
                Header header = headerBlock.get(i2);
                ByteString name = header.name.toAsciiLowercase();
                ByteString value = header.value;
                int headerIndex = -1;
                int headerNameIndex = -1;
                Integer staticIndex = NAME_TO_FIRST_INDEX.get(name);
                if (staticIndex != null && (headerNameIndex = staticIndex + 1) > 1 && headerNameIndex < 8) {
                    if (Objects.equals(Hpack.STATIC_HEADER_TABLE[headerNameIndex - 1].value, value)) {
                        headerIndex = headerNameIndex;
                    } else if (Objects.equals(Hpack.STATIC_HEADER_TABLE[headerNameIndex].value, value)) {
                        headerIndex = headerNameIndex + 1;
                    }
                }
                if (headerIndex == -1) {
                    int length = this.dynamicTable.length;
                    for (int j2 = this.nextHeaderIndex + 1; j2 < length; ++j2) {
                        if (!Objects.equals(this.dynamicTable[j2].name, name)) continue;
                        if (Objects.equals(this.dynamicTable[j2].value, value)) {
                            headerIndex = j2 - this.nextHeaderIndex + STATIC_HEADER_TABLE.length;
                            break;
                        }
                        if (headerNameIndex != -1) continue;
                        headerNameIndex = j2 - this.nextHeaderIndex + STATIC_HEADER_TABLE.length;
                    }
                }
                if (headerIndex != -1) {
                    this.writeInt(headerIndex, 127, 128);
                    continue;
                }
                if (headerNameIndex == -1) {
                    this.out.writeByte(64);
                    this.writeByteString(name);
                    this.writeByteString(value);
                    this.insertIntoDynamicTable(header);
                    continue;
                }
                if (name.startsWith(Header.PSEUDO_PREFIX) && !Header.TARGET_AUTHORITY.equals(name)) {
                    this.writeInt(headerNameIndex, 15, 0);
                    this.writeByteString(value);
                    continue;
                }
                this.writeInt(headerNameIndex, 63, 64);
                this.writeByteString(value);
                this.insertIntoDynamicTable(header);
            }
        }

        void writeInt(int value, int prefixMask, int bits) {
            if (value < prefixMask) {
                this.out.writeByte(bits | value);
                return;
            }
            this.out.writeByte(bits | prefixMask);
            value -= prefixMask;
            while (value >= 128) {
                int b2 = value & 0x7F;
                this.out.writeByte(b2 | 0x80);
                value >>>= 7;
            }
            this.out.writeByte(value);
        }

        void writeByteString(ByteString data) throws IOException {
            if (this.useCompression && Huffman.get().encodedLength(data) < data.size()) {
                Buffer huffmanBuffer = new Buffer();
                Huffman.get().encode(data, huffmanBuffer);
                ByteString huffmanBytes = huffmanBuffer.readByteString();
                this.writeInt(huffmanBytes.size(), 127, 128);
                this.out.write(huffmanBytes);
            } else {
                this.writeInt(data.size(), 127, 0);
                this.out.write(data);
            }
        }

        void setHeaderTableSizeSetting(int headerTableSizeSetting) {
            this.headerTableSizeSetting = headerTableSizeSetting;
            int effectiveHeaderTableSize = Math.min(headerTableSizeSetting, 16384);
            if (this.maxDynamicTableByteCount == effectiveHeaderTableSize) {
                return;
            }
            if (effectiveHeaderTableSize < this.maxDynamicTableByteCount) {
                this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, effectiveHeaderTableSize);
            }
            this.emitDynamicTableSizeUpdate = true;
            this.maxDynamicTableByteCount = effectiveHeaderTableSize;
            this.adjustDynamicTableByteCount();
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
                if (this.maxDynamicTableByteCount == 0) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
                }
            }
        }
    }

    static final class Reader {
        private final List<Header> headerList = new ArrayList<Header>();
        private final BufferedSource source;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        Header[] dynamicTable = new Header[8];
        int nextHeaderIndex = this.dynamicTable.length - 1;
        int headerCount = 0;
        int dynamicTableByteCount = 0;

        Reader(int headerTableSizeSetting, Source source) {
            this(headerTableSizeSetting, headerTableSizeSetting, source);
        }

        Reader(int headerTableSizeSetting, int maxDynamicTableByteCount, Source source) {
            this.headerTableSizeSetting = headerTableSizeSetting;
            this.maxDynamicTableByteCount = maxDynamicTableByteCount;
            this.source = Okio.buffer(source);
        }

        int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount < this.dynamicTableByteCount) {
                if (this.maxDynamicTableByteCount == 0) {
                    this.clearDynamicTable();
                } else {
                    this.evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
                }
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int bytesToRecover) {
            int entriesToEvict = 0;
            if (bytesToRecover > 0) {
                for (int j2 = this.dynamicTable.length - 1; j2 >= this.nextHeaderIndex && bytesToRecover > 0; --j2) {
                    bytesToRecover -= this.dynamicTable[j2].hpackSize;
                    this.dynamicTableByteCount -= this.dynamicTable[j2].hpackSize;
                    --this.headerCount;
                    ++entriesToEvict;
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
                this.nextHeaderIndex += entriesToEvict;
            }
            return entriesToEvict;
        }

        void readHeaders() throws IOException {
            while (!this.source.exhausted()) {
                int index;
                int b2 = this.source.readByte() & 0xFF;
                if (b2 == 128) {
                    throw new IOException("index == 0");
                }
                if ((b2 & 0x80) == 128) {
                    index = this.readInt(b2, 127);
                    this.readIndexedHeader(index - 1);
                    continue;
                }
                if (b2 == 64) {
                    this.readLiteralHeaderWithIncrementalIndexingNewName();
                    continue;
                }
                if ((b2 & 0x40) == 64) {
                    index = this.readInt(b2, 63);
                    this.readLiteralHeaderWithIncrementalIndexingIndexedName(index - 1);
                    continue;
                }
                if ((b2 & 0x20) == 32) {
                    this.maxDynamicTableByteCount = this.readInt(b2, 31);
                    if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
                        throw new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
                    }
                    this.adjustDynamicTableByteCount();
                    continue;
                }
                if (b2 == 16 || b2 == 0) {
                    this.readLiteralHeaderWithoutIndexingNewName();
                    continue;
                }
                index = this.readInt(b2, 15);
                this.readLiteralHeaderWithoutIndexingIndexedName(index - 1);
            }
        }

        public List<Header> getAndResetHeaderList() {
            ArrayList<Header> result = new ArrayList<Header>(this.headerList);
            this.headerList.clear();
            return result;
        }

        private void readIndexedHeader(int index) throws IOException {
            if (this.isStaticHeader(index)) {
                Header staticEntry = STATIC_HEADER_TABLE[index];
                this.headerList.add(staticEntry);
            } else {
                int dynamicTableIndex = this.dynamicTableIndex(index - STATIC_HEADER_TABLE.length);
                if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
                    throw new IOException("Header index too large " + (index + 1));
                }
                this.headerList.add(this.dynamicTable[dynamicTableIndex]);
            }
        }

        private int dynamicTableIndex(int index) {
            return this.nextHeaderIndex + 1 + index;
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
            ByteString name = this.getName(index);
            ByteString value = this.readByteString();
            this.headerList.add(new Header(name, value));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            ByteString name = Hpack.checkLowercase(this.readByteString());
            ByteString value = this.readByteString();
            this.headerList.add(new Header(name, value));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
            ByteString name = this.getName(nameIndex);
            ByteString value = this.readByteString();
            this.insertIntoDynamicTable(-1, new Header(name, value));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            ByteString name = Hpack.checkLowercase(this.readByteString());
            ByteString value = this.readByteString();
            this.insertIntoDynamicTable(-1, new Header(name, value));
        }

        private ByteString getName(int index) throws IOException {
            if (this.isStaticHeader(index)) {
                return Hpack.STATIC_HEADER_TABLE[index].name;
            }
            int dynamicTableIndex = this.dynamicTableIndex(index - STATIC_HEADER_TABLE.length);
            if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length) {
                throw new IOException("Header index too large " + (index + 1));
            }
            return this.dynamicTable[dynamicTableIndex].name;
        }

        private boolean isStaticHeader(int index) {
            return index >= 0 && index <= STATIC_HEADER_TABLE.length - 1;
        }

        private void insertIntoDynamicTable(int index, Header entry) {
            this.headerList.add(entry);
            int delta = entry.hpackSize;
            if (index != -1) {
                delta -= this.dynamicTable[this.dynamicTableIndex((int)index)].hpackSize;
            }
            if (delta > this.maxDynamicTableByteCount) {
                this.clearDynamicTable();
                return;
            }
            int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
            int entriesEvicted = this.evictToRecoverBytes(bytesToRecover);
            if (index == -1) {
                if (this.headerCount + 1 > this.dynamicTable.length) {
                    Header[] doubled = new Header[this.dynamicTable.length * 2];
                    System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = doubled;
                }
                index = this.nextHeaderIndex--;
                this.dynamicTable[index] = entry;
                ++this.headerCount;
            } else {
                index += this.dynamicTableIndex(index) + entriesEvicted;
                this.dynamicTable[index] = entry;
            }
            this.dynamicTableByteCount += delta;
        }

        private int readByte() throws IOException {
            return this.source.readByte() & 0xFF;
        }

        int readInt(int firstByte, int prefixMask) throws IOException {
            int b2;
            int prefix = firstByte & prefixMask;
            if (prefix < prefixMask) {
                return prefix;
            }
            int result = prefixMask;
            int shift = 0;
            while (((b2 = this.readByte()) & 0x80) != 0) {
                result += (b2 & 0x7F) << shift;
                shift += 7;
            }
            return result += b2 << shift;
        }

        ByteString readByteString() throws IOException {
            int firstByte = this.readByte();
            boolean huffmanDecode = (firstByte & 0x80) == 128;
            int length = this.readInt(firstByte, 127);
            if (huffmanDecode) {
                return ByteString.of(Huffman.get().decode(this.source.readByteArray(length)));
            }
            return this.source.readByteString(length);
        }
    }
}

