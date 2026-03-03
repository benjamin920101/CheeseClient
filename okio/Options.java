/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import okio.Buffer;
import okio.ByteString;

public final class Options
extends AbstractList<ByteString>
implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStrings, int[] trie) {
        this.byteStrings = byteStrings;
        this.trie = trie;
    }

    public static Options of(ByteString ... byteStrings) {
        int i2;
        if (byteStrings.length == 0) {
            return new Options(new ByteString[0], new int[]{0, -1});
        }
        ArrayList<ByteString> list = new ArrayList<ByteString>(Arrays.asList(byteStrings));
        Collections.sort(list);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (i2 = 0; i2 < list.size(); ++i2) {
            indexes.add(-1);
        }
        for (i2 = 0; i2 < list.size(); ++i2) {
            int sortedIndex = Collections.binarySearch(list, byteStrings[i2]);
            indexes.set(sortedIndex, i2);
        }
        if (((ByteString)list.get(0)).size() == 0) {
            throw new IllegalArgumentException("the empty byte string is not a supported option");
        }
        for (int a2 = 0; a2 < list.size(); ++a2) {
            ByteString byteString;
            ByteString prefix = (ByteString)list.get(a2);
            int b2 = a2 + 1;
            while (b2 < list.size() && (byteString = (ByteString)list.get(b2)).startsWith(prefix)) {
                if (byteString.size() == prefix.size()) {
                    throw new IllegalArgumentException("duplicate option: " + byteString);
                }
                if ((Integer)indexes.get(b2) > (Integer)indexes.get(a2)) {
                    list.remove(b2);
                    indexes.remove(b2);
                    continue;
                }
                ++b2;
            }
        }
        Buffer trieBytes = new Buffer();
        Options.buildTrieRecursive(0L, trieBytes, 0, list, 0, list.size(), indexes);
        int[] trie = new int[Options.intCount(trieBytes)];
        for (int i3 = 0; i3 < trie.length; ++i3) {
            trie[i3] = trieBytes.readInt();
        }
        if (!trieBytes.exhausted()) {
            throw new AssertionError();
        }
        return new Options((ByteString[])byteStrings.clone(), trie);
    }

    private static void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
        if (fromIndex >= toIndex) {
            throw new AssertionError();
        }
        for (int i2 = fromIndex; i2 < toIndex; ++i2) {
            if (byteStrings.get(i2).size() < byteStringOffset) {
                throw new AssertionError();
            }
        }
        ByteString from = byteStrings.get(fromIndex);
        ByteString to2 = byteStrings.get(toIndex - 1);
        int prefixIndex = -1;
        if (byteStringOffset == from.size()) {
            prefixIndex = indexes.get(fromIndex);
            from = byteStrings.get(++fromIndex);
        }
        if (from.getByte(byteStringOffset) != to2.getByte(byteStringOffset)) {
            int selectChoiceCount = 1;
            for (int i3 = fromIndex + 1; i3 < toIndex; ++i3) {
                if (byteStrings.get(i3 - 1).getByte(byteStringOffset) == byteStrings.get(i3).getByte(byteStringOffset)) continue;
                ++selectChoiceCount;
            }
            long childNodesOffset = nodeOffset + (long)Options.intCount(node) + 2L + (long)(selectChoiceCount * 2);
            node.writeInt(selectChoiceCount);
            node.writeInt(prefixIndex);
            for (int i4 = fromIndex; i4 < toIndex; ++i4) {
                byte rangeByte = byteStrings.get(i4).getByte(byteStringOffset);
                if (i4 != fromIndex && rangeByte == byteStrings.get(i4 - 1).getByte(byteStringOffset)) continue;
                node.writeInt(rangeByte & 0xFF);
            }
            Buffer childNodes = new Buffer();
            int rangeStart = fromIndex;
            while (rangeStart < toIndex) {
                byte rangeByte = byteStrings.get(rangeStart).getByte(byteStringOffset);
                int rangeEnd = toIndex;
                for (int i5 = rangeStart + 1; i5 < toIndex; ++i5) {
                    if (rangeByte == byteStrings.get(i5).getByte(byteStringOffset)) continue;
                    rangeEnd = i5;
                    break;
                }
                if (rangeStart + 1 == rangeEnd && byteStringOffset + 1 == byteStrings.get(rangeStart).size()) {
                    node.writeInt(indexes.get(rangeStart));
                } else {
                    node.writeInt((int)(-1L * (childNodesOffset + (long)Options.intCount(childNodes))));
                    Options.buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + 1, byteStrings, rangeStart, rangeEnd, indexes);
                }
                rangeStart = rangeEnd;
            }
            node.write(childNodes, childNodes.size());
        } else {
            int scanByteCount = 0;
            int max = Math.min(from.size(), to2.size());
            for (int i6 = byteStringOffset; i6 < max && from.getByte(i6) == to2.getByte(i6); ++i6) {
                ++scanByteCount;
            }
            long childNodesOffset = nodeOffset + (long)Options.intCount(node) + 2L + (long)scanByteCount + 1L;
            node.writeInt(-scanByteCount);
            node.writeInt(prefixIndex);
            for (int i7 = byteStringOffset; i7 < byteStringOffset + scanByteCount; ++i7) {
                node.writeInt(from.getByte(i7) & 0xFF);
            }
            if (fromIndex + 1 == toIndex) {
                if (byteStringOffset + scanByteCount != byteStrings.get(fromIndex).size()) {
                    throw new AssertionError();
                }
                node.writeInt(indexes.get(fromIndex));
            } else {
                Buffer childNodes = new Buffer();
                node.writeInt((int)(-1L * (childNodesOffset + (long)Options.intCount(childNodes))));
                Options.buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + scanByteCount, byteStrings, fromIndex, toIndex, indexes);
                node.write(childNodes, childNodes.size());
            }
        }
    }

    @Override
    public ByteString get(int i2) {
        return this.byteStrings[i2];
    }

    @Override
    public final int size() {
        return this.byteStrings.length;
    }

    private static int intCount(Buffer trieBytes) {
        return (int)(trieBytes.size() / 4L);
    }
}

