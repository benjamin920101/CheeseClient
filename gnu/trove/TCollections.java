/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove;

import gnu.trove.TByteCollection;
import gnu.trove.TCharCollection;
import gnu.trove.TDoubleCollection;
import gnu.trove.TFloatCollection;
import gnu.trove.TIntCollection;
import gnu.trove.TLongCollection;
import gnu.trove.TShortCollection;
import gnu.trove.impl.sync.TSynchronizedByteByteMap;
import gnu.trove.impl.sync.TSynchronizedByteCharMap;
import gnu.trove.impl.sync.TSynchronizedByteCollection;
import gnu.trove.impl.sync.TSynchronizedByteDoubleMap;
import gnu.trove.impl.sync.TSynchronizedByteFloatMap;
import gnu.trove.impl.sync.TSynchronizedByteIntMap;
import gnu.trove.impl.sync.TSynchronizedByteList;
import gnu.trove.impl.sync.TSynchronizedByteLongMap;
import gnu.trove.impl.sync.TSynchronizedByteObjectMap;
import gnu.trove.impl.sync.TSynchronizedByteSet;
import gnu.trove.impl.sync.TSynchronizedByteShortMap;
import gnu.trove.impl.sync.TSynchronizedCharByteMap;
import gnu.trove.impl.sync.TSynchronizedCharCharMap;
import gnu.trove.impl.sync.TSynchronizedCharCollection;
import gnu.trove.impl.sync.TSynchronizedCharDoubleMap;
import gnu.trove.impl.sync.TSynchronizedCharFloatMap;
import gnu.trove.impl.sync.TSynchronizedCharIntMap;
import gnu.trove.impl.sync.TSynchronizedCharList;
import gnu.trove.impl.sync.TSynchronizedCharLongMap;
import gnu.trove.impl.sync.TSynchronizedCharObjectMap;
import gnu.trove.impl.sync.TSynchronizedCharSet;
import gnu.trove.impl.sync.TSynchronizedCharShortMap;
import gnu.trove.impl.sync.TSynchronizedDoubleByteMap;
import gnu.trove.impl.sync.TSynchronizedDoubleCharMap;
import gnu.trove.impl.sync.TSynchronizedDoubleCollection;
import gnu.trove.impl.sync.TSynchronizedDoubleDoubleMap;
import gnu.trove.impl.sync.TSynchronizedDoubleFloatMap;
import gnu.trove.impl.sync.TSynchronizedDoubleIntMap;
import gnu.trove.impl.sync.TSynchronizedDoubleList;
import gnu.trove.impl.sync.TSynchronizedDoubleLongMap;
import gnu.trove.impl.sync.TSynchronizedDoubleObjectMap;
import gnu.trove.impl.sync.TSynchronizedDoubleSet;
import gnu.trove.impl.sync.TSynchronizedDoubleShortMap;
import gnu.trove.impl.sync.TSynchronizedFloatByteMap;
import gnu.trove.impl.sync.TSynchronizedFloatCharMap;
import gnu.trove.impl.sync.TSynchronizedFloatCollection;
import gnu.trove.impl.sync.TSynchronizedFloatDoubleMap;
import gnu.trove.impl.sync.TSynchronizedFloatFloatMap;
import gnu.trove.impl.sync.TSynchronizedFloatIntMap;
import gnu.trove.impl.sync.TSynchronizedFloatList;
import gnu.trove.impl.sync.TSynchronizedFloatLongMap;
import gnu.trove.impl.sync.TSynchronizedFloatObjectMap;
import gnu.trove.impl.sync.TSynchronizedFloatSet;
import gnu.trove.impl.sync.TSynchronizedFloatShortMap;
import gnu.trove.impl.sync.TSynchronizedIntByteMap;
import gnu.trove.impl.sync.TSynchronizedIntCharMap;
import gnu.trove.impl.sync.TSynchronizedIntCollection;
import gnu.trove.impl.sync.TSynchronizedIntDoubleMap;
import gnu.trove.impl.sync.TSynchronizedIntFloatMap;
import gnu.trove.impl.sync.TSynchronizedIntIntMap;
import gnu.trove.impl.sync.TSynchronizedIntList;
import gnu.trove.impl.sync.TSynchronizedIntLongMap;
import gnu.trove.impl.sync.TSynchronizedIntObjectMap;
import gnu.trove.impl.sync.TSynchronizedIntSet;
import gnu.trove.impl.sync.TSynchronizedIntShortMap;
import gnu.trove.impl.sync.TSynchronizedLongByteMap;
import gnu.trove.impl.sync.TSynchronizedLongCharMap;
import gnu.trove.impl.sync.TSynchronizedLongCollection;
import gnu.trove.impl.sync.TSynchronizedLongDoubleMap;
import gnu.trove.impl.sync.TSynchronizedLongFloatMap;
import gnu.trove.impl.sync.TSynchronizedLongIntMap;
import gnu.trove.impl.sync.TSynchronizedLongList;
import gnu.trove.impl.sync.TSynchronizedLongLongMap;
import gnu.trove.impl.sync.TSynchronizedLongObjectMap;
import gnu.trove.impl.sync.TSynchronizedLongSet;
import gnu.trove.impl.sync.TSynchronizedLongShortMap;
import gnu.trove.impl.sync.TSynchronizedObjectByteMap;
import gnu.trove.impl.sync.TSynchronizedObjectCharMap;
import gnu.trove.impl.sync.TSynchronizedObjectDoubleMap;
import gnu.trove.impl.sync.TSynchronizedObjectFloatMap;
import gnu.trove.impl.sync.TSynchronizedObjectIntMap;
import gnu.trove.impl.sync.TSynchronizedObjectLongMap;
import gnu.trove.impl.sync.TSynchronizedObjectShortMap;
import gnu.trove.impl.sync.TSynchronizedRandomAccessByteList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessCharList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessDoubleList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessFloatList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessIntList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessLongList;
import gnu.trove.impl.sync.TSynchronizedRandomAccessShortList;
import gnu.trove.impl.sync.TSynchronizedShortByteMap;
import gnu.trove.impl.sync.TSynchronizedShortCharMap;
import gnu.trove.impl.sync.TSynchronizedShortCollection;
import gnu.trove.impl.sync.TSynchronizedShortDoubleMap;
import gnu.trove.impl.sync.TSynchronizedShortFloatMap;
import gnu.trove.impl.sync.TSynchronizedShortIntMap;
import gnu.trove.impl.sync.TSynchronizedShortList;
import gnu.trove.impl.sync.TSynchronizedShortLongMap;
import gnu.trove.impl.sync.TSynchronizedShortObjectMap;
import gnu.trove.impl.sync.TSynchronizedShortSet;
import gnu.trove.impl.sync.TSynchronizedShortShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteList;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableByteShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharList;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableCharShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleList;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatList;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntList;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongList;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableLongShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectShortMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessByteList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessCharList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessDoubleList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessFloatList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessIntList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessLongList;
import gnu.trove.impl.unmodifiable.TUnmodifiableRandomAccessShortList;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortByteMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortCharMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortDoubleMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortFloatMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortIntMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortList;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortLongMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortObjectMap;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortSet;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortShortMap;
import gnu.trove.list.TByteList;
import gnu.trove.list.TCharList;
import gnu.trove.list.TDoubleList;
import gnu.trove.list.TFloatList;
import gnu.trove.list.TIntList;
import gnu.trove.list.TLongList;
import gnu.trove.list.TShortList;
import gnu.trove.map.TByteByteMap;
import gnu.trove.map.TByteCharMap;
import gnu.trove.map.TByteDoubleMap;
import gnu.trove.map.TByteFloatMap;
import gnu.trove.map.TByteIntMap;
import gnu.trove.map.TByteLongMap;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.TByteShortMap;
import gnu.trove.map.TCharByteMap;
import gnu.trove.map.TCharCharMap;
import gnu.trove.map.TCharDoubleMap;
import gnu.trove.map.TCharFloatMap;
import gnu.trove.map.TCharIntMap;
import gnu.trove.map.TCharLongMap;
import gnu.trove.map.TCharObjectMap;
import gnu.trove.map.TCharShortMap;
import gnu.trove.map.TDoubleByteMap;
import gnu.trove.map.TDoubleCharMap;
import gnu.trove.map.TDoubleDoubleMap;
import gnu.trove.map.TDoubleFloatMap;
import gnu.trove.map.TDoubleIntMap;
import gnu.trove.map.TDoubleLongMap;
import gnu.trove.map.TDoubleObjectMap;
import gnu.trove.map.TDoubleShortMap;
import gnu.trove.map.TFloatByteMap;
import gnu.trove.map.TFloatCharMap;
import gnu.trove.map.TFloatDoubleMap;
import gnu.trove.map.TFloatFloatMap;
import gnu.trove.map.TFloatIntMap;
import gnu.trove.map.TFloatLongMap;
import gnu.trove.map.TFloatObjectMap;
import gnu.trove.map.TFloatShortMap;
import gnu.trove.map.TIntByteMap;
import gnu.trove.map.TIntCharMap;
import gnu.trove.map.TIntDoubleMap;
import gnu.trove.map.TIntFloatMap;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.TIntLongMap;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TIntShortMap;
import gnu.trove.map.TLongByteMap;
import gnu.trove.map.TLongCharMap;
import gnu.trove.map.TLongDoubleMap;
import gnu.trove.map.TLongFloatMap;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TLongLongMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.TLongShortMap;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.map.TObjectCharMap;
import gnu.trove.map.TObjectDoubleMap;
import gnu.trove.map.TObjectFloatMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.TObjectLongMap;
import gnu.trove.map.TObjectShortMap;
import gnu.trove.map.TShortByteMap;
import gnu.trove.map.TShortCharMap;
import gnu.trove.map.TShortDoubleMap;
import gnu.trove.map.TShortFloatMap;
import gnu.trove.map.TShortIntMap;
import gnu.trove.map.TShortLongMap;
import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.TShortShortMap;
import gnu.trove.set.TByteSet;
import gnu.trove.set.TCharSet;
import gnu.trove.set.TDoubleSet;
import gnu.trove.set.TFloatSet;
import gnu.trove.set.TIntSet;
import gnu.trove.set.TLongSet;
import gnu.trove.set.TShortSet;
import java.util.RandomAccess;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TCollections {
    private TCollections() {
    }

    public static TDoubleCollection unmodifiableCollection(TDoubleCollection c2) {
        return new TUnmodifiableDoubleCollection(c2);
    }

    public static TFloatCollection unmodifiableCollection(TFloatCollection c2) {
        return new TUnmodifiableFloatCollection(c2);
    }

    public static TIntCollection unmodifiableCollection(TIntCollection c2) {
        return new TUnmodifiableIntCollection(c2);
    }

    public static TLongCollection unmodifiableCollection(TLongCollection c2) {
        return new TUnmodifiableLongCollection(c2);
    }

    public static TByteCollection unmodifiableCollection(TByteCollection c2) {
        return new TUnmodifiableByteCollection(c2);
    }

    public static TShortCollection unmodifiableCollection(TShortCollection c2) {
        return new TUnmodifiableShortCollection(c2);
    }

    public static TCharCollection unmodifiableCollection(TCharCollection c2) {
        return new TUnmodifiableCharCollection(c2);
    }

    public static TDoubleSet unmodifiableSet(TDoubleSet s2) {
        return new TUnmodifiableDoubleSet(s2);
    }

    public static TFloatSet unmodifiableSet(TFloatSet s2) {
        return new TUnmodifiableFloatSet(s2);
    }

    public static TIntSet unmodifiableSet(TIntSet s2) {
        return new TUnmodifiableIntSet(s2);
    }

    public static TLongSet unmodifiableSet(TLongSet s2) {
        return new TUnmodifiableLongSet(s2);
    }

    public static TByteSet unmodifiableSet(TByteSet s2) {
        return new TUnmodifiableByteSet(s2);
    }

    public static TShortSet unmodifiableSet(TShortSet s2) {
        return new TUnmodifiableShortSet(s2);
    }

    public static TCharSet unmodifiableSet(TCharSet s2) {
        return new TUnmodifiableCharSet(s2);
    }

    public static TDoubleList unmodifiableList(TDoubleList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessDoubleList(list) : new TUnmodifiableDoubleList(list);
    }

    public static TFloatList unmodifiableList(TFloatList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessFloatList(list) : new TUnmodifiableFloatList(list);
    }

    public static TIntList unmodifiableList(TIntList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessIntList(list) : new TUnmodifiableIntList(list);
    }

    public static TLongList unmodifiableList(TLongList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessLongList(list) : new TUnmodifiableLongList(list);
    }

    public static TByteList unmodifiableList(TByteList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessByteList(list) : new TUnmodifiableByteList(list);
    }

    public static TShortList unmodifiableList(TShortList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessShortList(list) : new TUnmodifiableShortList(list);
    }

    public static TCharList unmodifiableList(TCharList list) {
        return list instanceof RandomAccess ? new TUnmodifiableRandomAccessCharList(list) : new TUnmodifiableCharList(list);
    }

    public static TDoubleDoubleMap unmodifiableMap(TDoubleDoubleMap m2) {
        return new TUnmodifiableDoubleDoubleMap(m2);
    }

    public static TDoubleFloatMap unmodifiableMap(TDoubleFloatMap m2) {
        return new TUnmodifiableDoubleFloatMap(m2);
    }

    public static TDoubleIntMap unmodifiableMap(TDoubleIntMap m2) {
        return new TUnmodifiableDoubleIntMap(m2);
    }

    public static TDoubleLongMap unmodifiableMap(TDoubleLongMap m2) {
        return new TUnmodifiableDoubleLongMap(m2);
    }

    public static TDoubleByteMap unmodifiableMap(TDoubleByteMap m2) {
        return new TUnmodifiableDoubleByteMap(m2);
    }

    public static TDoubleShortMap unmodifiableMap(TDoubleShortMap m2) {
        return new TUnmodifiableDoubleShortMap(m2);
    }

    public static TDoubleCharMap unmodifiableMap(TDoubleCharMap m2) {
        return new TUnmodifiableDoubleCharMap(m2);
    }

    public static TFloatDoubleMap unmodifiableMap(TFloatDoubleMap m2) {
        return new TUnmodifiableFloatDoubleMap(m2);
    }

    public static TFloatFloatMap unmodifiableMap(TFloatFloatMap m2) {
        return new TUnmodifiableFloatFloatMap(m2);
    }

    public static TFloatIntMap unmodifiableMap(TFloatIntMap m2) {
        return new TUnmodifiableFloatIntMap(m2);
    }

    public static TFloatLongMap unmodifiableMap(TFloatLongMap m2) {
        return new TUnmodifiableFloatLongMap(m2);
    }

    public static TFloatByteMap unmodifiableMap(TFloatByteMap m2) {
        return new TUnmodifiableFloatByteMap(m2);
    }

    public static TFloatShortMap unmodifiableMap(TFloatShortMap m2) {
        return new TUnmodifiableFloatShortMap(m2);
    }

    public static TFloatCharMap unmodifiableMap(TFloatCharMap m2) {
        return new TUnmodifiableFloatCharMap(m2);
    }

    public static TIntDoubleMap unmodifiableMap(TIntDoubleMap m2) {
        return new TUnmodifiableIntDoubleMap(m2);
    }

    public static TIntFloatMap unmodifiableMap(TIntFloatMap m2) {
        return new TUnmodifiableIntFloatMap(m2);
    }

    public static TIntIntMap unmodifiableMap(TIntIntMap m2) {
        return new TUnmodifiableIntIntMap(m2);
    }

    public static TIntLongMap unmodifiableMap(TIntLongMap m2) {
        return new TUnmodifiableIntLongMap(m2);
    }

    public static TIntByteMap unmodifiableMap(TIntByteMap m2) {
        return new TUnmodifiableIntByteMap(m2);
    }

    public static TIntShortMap unmodifiableMap(TIntShortMap m2) {
        return new TUnmodifiableIntShortMap(m2);
    }

    public static TIntCharMap unmodifiableMap(TIntCharMap m2) {
        return new TUnmodifiableIntCharMap(m2);
    }

    public static TLongDoubleMap unmodifiableMap(TLongDoubleMap m2) {
        return new TUnmodifiableLongDoubleMap(m2);
    }

    public static TLongFloatMap unmodifiableMap(TLongFloatMap m2) {
        return new TUnmodifiableLongFloatMap(m2);
    }

    public static TLongIntMap unmodifiableMap(TLongIntMap m2) {
        return new TUnmodifiableLongIntMap(m2);
    }

    public static TLongLongMap unmodifiableMap(TLongLongMap m2) {
        return new TUnmodifiableLongLongMap(m2);
    }

    public static TLongByteMap unmodifiableMap(TLongByteMap m2) {
        return new TUnmodifiableLongByteMap(m2);
    }

    public static TLongShortMap unmodifiableMap(TLongShortMap m2) {
        return new TUnmodifiableLongShortMap(m2);
    }

    public static TLongCharMap unmodifiableMap(TLongCharMap m2) {
        return new TUnmodifiableLongCharMap(m2);
    }

    public static TByteDoubleMap unmodifiableMap(TByteDoubleMap m2) {
        return new TUnmodifiableByteDoubleMap(m2);
    }

    public static TByteFloatMap unmodifiableMap(TByteFloatMap m2) {
        return new TUnmodifiableByteFloatMap(m2);
    }

    public static TByteIntMap unmodifiableMap(TByteIntMap m2) {
        return new TUnmodifiableByteIntMap(m2);
    }

    public static TByteLongMap unmodifiableMap(TByteLongMap m2) {
        return new TUnmodifiableByteLongMap(m2);
    }

    public static TByteByteMap unmodifiableMap(TByteByteMap m2) {
        return new TUnmodifiableByteByteMap(m2);
    }

    public static TByteShortMap unmodifiableMap(TByteShortMap m2) {
        return new TUnmodifiableByteShortMap(m2);
    }

    public static TByteCharMap unmodifiableMap(TByteCharMap m2) {
        return new TUnmodifiableByteCharMap(m2);
    }

    public static TShortDoubleMap unmodifiableMap(TShortDoubleMap m2) {
        return new TUnmodifiableShortDoubleMap(m2);
    }

    public static TShortFloatMap unmodifiableMap(TShortFloatMap m2) {
        return new TUnmodifiableShortFloatMap(m2);
    }

    public static TShortIntMap unmodifiableMap(TShortIntMap m2) {
        return new TUnmodifiableShortIntMap(m2);
    }

    public static TShortLongMap unmodifiableMap(TShortLongMap m2) {
        return new TUnmodifiableShortLongMap(m2);
    }

    public static TShortByteMap unmodifiableMap(TShortByteMap m2) {
        return new TUnmodifiableShortByteMap(m2);
    }

    public static TShortShortMap unmodifiableMap(TShortShortMap m2) {
        return new TUnmodifiableShortShortMap(m2);
    }

    public static TShortCharMap unmodifiableMap(TShortCharMap m2) {
        return new TUnmodifiableShortCharMap(m2);
    }

    public static TCharDoubleMap unmodifiableMap(TCharDoubleMap m2) {
        return new TUnmodifiableCharDoubleMap(m2);
    }

    public static TCharFloatMap unmodifiableMap(TCharFloatMap m2) {
        return new TUnmodifiableCharFloatMap(m2);
    }

    public static TCharIntMap unmodifiableMap(TCharIntMap m2) {
        return new TUnmodifiableCharIntMap(m2);
    }

    public static TCharLongMap unmodifiableMap(TCharLongMap m2) {
        return new TUnmodifiableCharLongMap(m2);
    }

    public static TCharByteMap unmodifiableMap(TCharByteMap m2) {
        return new TUnmodifiableCharByteMap(m2);
    }

    public static TCharShortMap unmodifiableMap(TCharShortMap m2) {
        return new TUnmodifiableCharShortMap(m2);
    }

    public static TCharCharMap unmodifiableMap(TCharCharMap m2) {
        return new TUnmodifiableCharCharMap(m2);
    }

    public static <V> TDoubleObjectMap<V> unmodifiableMap(TDoubleObjectMap<V> m2) {
        return new TUnmodifiableDoubleObjectMap<V>(m2);
    }

    public static <V> TFloatObjectMap<V> unmodifiableMap(TFloatObjectMap<V> m2) {
        return new TUnmodifiableFloatObjectMap<V>(m2);
    }

    public static <V> TIntObjectMap<V> unmodifiableMap(TIntObjectMap<V> m2) {
        return new TUnmodifiableIntObjectMap<V>(m2);
    }

    public static <V> TLongObjectMap<V> unmodifiableMap(TLongObjectMap<V> m2) {
        return new TUnmodifiableLongObjectMap<V>(m2);
    }

    public static <V> TByteObjectMap<V> unmodifiableMap(TByteObjectMap<V> m2) {
        return new TUnmodifiableByteObjectMap<V>(m2);
    }

    public static <V> TShortObjectMap<V> unmodifiableMap(TShortObjectMap<V> m2) {
        return new TUnmodifiableShortObjectMap<V>(m2);
    }

    public static <V> TCharObjectMap<V> unmodifiableMap(TCharObjectMap<V> m2) {
        return new TUnmodifiableCharObjectMap<V>(m2);
    }

    public static <K> TObjectDoubleMap<K> unmodifiableMap(TObjectDoubleMap<K> m2) {
        return new TUnmodifiableObjectDoubleMap<K>(m2);
    }

    public static <K> TObjectFloatMap<K> unmodifiableMap(TObjectFloatMap<K> m2) {
        return new TUnmodifiableObjectFloatMap<K>(m2);
    }

    public static <K> TObjectIntMap<K> unmodifiableMap(TObjectIntMap<K> m2) {
        return new TUnmodifiableObjectIntMap<K>(m2);
    }

    public static <K> TObjectLongMap<K> unmodifiableMap(TObjectLongMap<K> m2) {
        return new TUnmodifiableObjectLongMap<K>(m2);
    }

    public static <K> TObjectByteMap<K> unmodifiableMap(TObjectByteMap<K> m2) {
        return new TUnmodifiableObjectByteMap<K>(m2);
    }

    public static <K> TObjectShortMap<K> unmodifiableMap(TObjectShortMap<K> m2) {
        return new TUnmodifiableObjectShortMap<K>(m2);
    }

    public static <K> TObjectCharMap<K> unmodifiableMap(TObjectCharMap<K> m2) {
        return new TUnmodifiableObjectCharMap<K>(m2);
    }

    public static TDoubleCollection synchronizedCollection(TDoubleCollection c2) {
        return new TSynchronizedDoubleCollection(c2);
    }

    static TDoubleCollection synchronizedCollection(TDoubleCollection c2, Object mutex) {
        return new TSynchronizedDoubleCollection(c2, mutex);
    }

    public static TFloatCollection synchronizedCollection(TFloatCollection c2) {
        return new TSynchronizedFloatCollection(c2);
    }

    static TFloatCollection synchronizedCollection(TFloatCollection c2, Object mutex) {
        return new TSynchronizedFloatCollection(c2, mutex);
    }

    public static TIntCollection synchronizedCollection(TIntCollection c2) {
        return new TSynchronizedIntCollection(c2);
    }

    static TIntCollection synchronizedCollection(TIntCollection c2, Object mutex) {
        return new TSynchronizedIntCollection(c2, mutex);
    }

    public static TLongCollection synchronizedCollection(TLongCollection c2) {
        return new TSynchronizedLongCollection(c2);
    }

    static TLongCollection synchronizedCollection(TLongCollection c2, Object mutex) {
        return new TSynchronizedLongCollection(c2, mutex);
    }

    public static TByteCollection synchronizedCollection(TByteCollection c2) {
        return new TSynchronizedByteCollection(c2);
    }

    static TByteCollection synchronizedCollection(TByteCollection c2, Object mutex) {
        return new TSynchronizedByteCollection(c2, mutex);
    }

    public static TShortCollection synchronizedCollection(TShortCollection c2) {
        return new TSynchronizedShortCollection(c2);
    }

    static TShortCollection synchronizedCollection(TShortCollection c2, Object mutex) {
        return new TSynchronizedShortCollection(c2, mutex);
    }

    public static TCharCollection synchronizedCollection(TCharCollection c2) {
        return new TSynchronizedCharCollection(c2);
    }

    static TCharCollection synchronizedCollection(TCharCollection c2, Object mutex) {
        return new TSynchronizedCharCollection(c2, mutex);
    }

    public static TDoubleSet synchronizedSet(TDoubleSet s2) {
        return new TSynchronizedDoubleSet(s2);
    }

    static TDoubleSet synchronizedSet(TDoubleSet s2, Object mutex) {
        return new TSynchronizedDoubleSet(s2, mutex);
    }

    public static TFloatSet synchronizedSet(TFloatSet s2) {
        return new TSynchronizedFloatSet(s2);
    }

    static TFloatSet synchronizedSet(TFloatSet s2, Object mutex) {
        return new TSynchronizedFloatSet(s2, mutex);
    }

    public static TIntSet synchronizedSet(TIntSet s2) {
        return new TSynchronizedIntSet(s2);
    }

    static TIntSet synchronizedSet(TIntSet s2, Object mutex) {
        return new TSynchronizedIntSet(s2, mutex);
    }

    public static TLongSet synchronizedSet(TLongSet s2) {
        return new TSynchronizedLongSet(s2);
    }

    static TLongSet synchronizedSet(TLongSet s2, Object mutex) {
        return new TSynchronizedLongSet(s2, mutex);
    }

    public static TByteSet synchronizedSet(TByteSet s2) {
        return new TSynchronizedByteSet(s2);
    }

    static TByteSet synchronizedSet(TByteSet s2, Object mutex) {
        return new TSynchronizedByteSet(s2, mutex);
    }

    public static TShortSet synchronizedSet(TShortSet s2) {
        return new TSynchronizedShortSet(s2);
    }

    static TShortSet synchronizedSet(TShortSet s2, Object mutex) {
        return new TSynchronizedShortSet(s2, mutex);
    }

    public static TCharSet synchronizedSet(TCharSet s2) {
        return new TSynchronizedCharSet(s2);
    }

    static TCharSet synchronizedSet(TCharSet s2, Object mutex) {
        return new TSynchronizedCharSet(s2, mutex);
    }

    public static TDoubleList synchronizedList(TDoubleList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessDoubleList(list) : new TSynchronizedDoubleList(list);
    }

    static TDoubleList synchronizedList(TDoubleList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessDoubleList(list, mutex) : new TSynchronizedDoubleList(list, mutex);
    }

    public static TFloatList synchronizedList(TFloatList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessFloatList(list) : new TSynchronizedFloatList(list);
    }

    static TFloatList synchronizedList(TFloatList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessFloatList(list, mutex) : new TSynchronizedFloatList(list, mutex);
    }

    public static TIntList synchronizedList(TIntList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessIntList(list) : new TSynchronizedIntList(list);
    }

    static TIntList synchronizedList(TIntList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessIntList(list, mutex) : new TSynchronizedIntList(list, mutex);
    }

    public static TLongList synchronizedList(TLongList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessLongList(list) : new TSynchronizedLongList(list);
    }

    static TLongList synchronizedList(TLongList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessLongList(list, mutex) : new TSynchronizedLongList(list, mutex);
    }

    public static TByteList synchronizedList(TByteList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessByteList(list) : new TSynchronizedByteList(list);
    }

    static TByteList synchronizedList(TByteList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessByteList(list, mutex) : new TSynchronizedByteList(list, mutex);
    }

    public static TShortList synchronizedList(TShortList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessShortList(list) : new TSynchronizedShortList(list);
    }

    static TShortList synchronizedList(TShortList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessShortList(list, mutex) : new TSynchronizedShortList(list, mutex);
    }

    public static TCharList synchronizedList(TCharList list) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessCharList(list) : new TSynchronizedCharList(list);
    }

    static TCharList synchronizedList(TCharList list, Object mutex) {
        return list instanceof RandomAccess ? new TSynchronizedRandomAccessCharList(list, mutex) : new TSynchronizedCharList(list, mutex);
    }

    public static TDoubleDoubleMap synchronizedMap(TDoubleDoubleMap m2) {
        return new TSynchronizedDoubleDoubleMap(m2);
    }

    public static TDoubleFloatMap synchronizedMap(TDoubleFloatMap m2) {
        return new TSynchronizedDoubleFloatMap(m2);
    }

    public static TDoubleIntMap synchronizedMap(TDoubleIntMap m2) {
        return new TSynchronizedDoubleIntMap(m2);
    }

    public static TDoubleLongMap synchronizedMap(TDoubleLongMap m2) {
        return new TSynchronizedDoubleLongMap(m2);
    }

    public static TDoubleByteMap synchronizedMap(TDoubleByteMap m2) {
        return new TSynchronizedDoubleByteMap(m2);
    }

    public static TDoubleShortMap synchronizedMap(TDoubleShortMap m2) {
        return new TSynchronizedDoubleShortMap(m2);
    }

    public static TDoubleCharMap synchronizedMap(TDoubleCharMap m2) {
        return new TSynchronizedDoubleCharMap(m2);
    }

    public static TFloatDoubleMap synchronizedMap(TFloatDoubleMap m2) {
        return new TSynchronizedFloatDoubleMap(m2);
    }

    public static TFloatFloatMap synchronizedMap(TFloatFloatMap m2) {
        return new TSynchronizedFloatFloatMap(m2);
    }

    public static TFloatIntMap synchronizedMap(TFloatIntMap m2) {
        return new TSynchronizedFloatIntMap(m2);
    }

    public static TFloatLongMap synchronizedMap(TFloatLongMap m2) {
        return new TSynchronizedFloatLongMap(m2);
    }

    public static TFloatByteMap synchronizedMap(TFloatByteMap m2) {
        return new TSynchronizedFloatByteMap(m2);
    }

    public static TFloatShortMap synchronizedMap(TFloatShortMap m2) {
        return new TSynchronizedFloatShortMap(m2);
    }

    public static TFloatCharMap synchronizedMap(TFloatCharMap m2) {
        return new TSynchronizedFloatCharMap(m2);
    }

    public static TIntDoubleMap synchronizedMap(TIntDoubleMap m2) {
        return new TSynchronizedIntDoubleMap(m2);
    }

    public static TIntFloatMap synchronizedMap(TIntFloatMap m2) {
        return new TSynchronizedIntFloatMap(m2);
    }

    public static TIntIntMap synchronizedMap(TIntIntMap m2) {
        return new TSynchronizedIntIntMap(m2);
    }

    public static TIntLongMap synchronizedMap(TIntLongMap m2) {
        return new TSynchronizedIntLongMap(m2);
    }

    public static TIntByteMap synchronizedMap(TIntByteMap m2) {
        return new TSynchronizedIntByteMap(m2);
    }

    public static TIntShortMap synchronizedMap(TIntShortMap m2) {
        return new TSynchronizedIntShortMap(m2);
    }

    public static TIntCharMap synchronizedMap(TIntCharMap m2) {
        return new TSynchronizedIntCharMap(m2);
    }

    public static TLongDoubleMap synchronizedMap(TLongDoubleMap m2) {
        return new TSynchronizedLongDoubleMap(m2);
    }

    public static TLongFloatMap synchronizedMap(TLongFloatMap m2) {
        return new TSynchronizedLongFloatMap(m2);
    }

    public static TLongIntMap synchronizedMap(TLongIntMap m2) {
        return new TSynchronizedLongIntMap(m2);
    }

    public static TLongLongMap synchronizedMap(TLongLongMap m2) {
        return new TSynchronizedLongLongMap(m2);
    }

    public static TLongByteMap synchronizedMap(TLongByteMap m2) {
        return new TSynchronizedLongByteMap(m2);
    }

    public static TLongShortMap synchronizedMap(TLongShortMap m2) {
        return new TSynchronizedLongShortMap(m2);
    }

    public static TLongCharMap synchronizedMap(TLongCharMap m2) {
        return new TSynchronizedLongCharMap(m2);
    }

    public static TByteDoubleMap synchronizedMap(TByteDoubleMap m2) {
        return new TSynchronizedByteDoubleMap(m2);
    }

    public static TByteFloatMap synchronizedMap(TByteFloatMap m2) {
        return new TSynchronizedByteFloatMap(m2);
    }

    public static TByteIntMap synchronizedMap(TByteIntMap m2) {
        return new TSynchronizedByteIntMap(m2);
    }

    public static TByteLongMap synchronizedMap(TByteLongMap m2) {
        return new TSynchronizedByteLongMap(m2);
    }

    public static TByteByteMap synchronizedMap(TByteByteMap m2) {
        return new TSynchronizedByteByteMap(m2);
    }

    public static TByteShortMap synchronizedMap(TByteShortMap m2) {
        return new TSynchronizedByteShortMap(m2);
    }

    public static TByteCharMap synchronizedMap(TByteCharMap m2) {
        return new TSynchronizedByteCharMap(m2);
    }

    public static TShortDoubleMap synchronizedMap(TShortDoubleMap m2) {
        return new TSynchronizedShortDoubleMap(m2);
    }

    public static TShortFloatMap synchronizedMap(TShortFloatMap m2) {
        return new TSynchronizedShortFloatMap(m2);
    }

    public static TShortIntMap synchronizedMap(TShortIntMap m2) {
        return new TSynchronizedShortIntMap(m2);
    }

    public static TShortLongMap synchronizedMap(TShortLongMap m2) {
        return new TSynchronizedShortLongMap(m2);
    }

    public static TShortByteMap synchronizedMap(TShortByteMap m2) {
        return new TSynchronizedShortByteMap(m2);
    }

    public static TShortShortMap synchronizedMap(TShortShortMap m2) {
        return new TSynchronizedShortShortMap(m2);
    }

    public static TShortCharMap synchronizedMap(TShortCharMap m2) {
        return new TSynchronizedShortCharMap(m2);
    }

    public static TCharDoubleMap synchronizedMap(TCharDoubleMap m2) {
        return new TSynchronizedCharDoubleMap(m2);
    }

    public static TCharFloatMap synchronizedMap(TCharFloatMap m2) {
        return new TSynchronizedCharFloatMap(m2);
    }

    public static TCharIntMap synchronizedMap(TCharIntMap m2) {
        return new TSynchronizedCharIntMap(m2);
    }

    public static TCharLongMap synchronizedMap(TCharLongMap m2) {
        return new TSynchronizedCharLongMap(m2);
    }

    public static TCharByteMap synchronizedMap(TCharByteMap m2) {
        return new TSynchronizedCharByteMap(m2);
    }

    public static TCharShortMap synchronizedMap(TCharShortMap m2) {
        return new TSynchronizedCharShortMap(m2);
    }

    public static TCharCharMap synchronizedMap(TCharCharMap m2) {
        return new TSynchronizedCharCharMap(m2);
    }

    public static <V> TDoubleObjectMap<V> synchronizedMap(TDoubleObjectMap<V> m2) {
        return new TSynchronizedDoubleObjectMap<V>(m2);
    }

    public static <V> TFloatObjectMap<V> synchronizedMap(TFloatObjectMap<V> m2) {
        return new TSynchronizedFloatObjectMap<V>(m2);
    }

    public static <V> TIntObjectMap<V> synchronizedMap(TIntObjectMap<V> m2) {
        return new TSynchronizedIntObjectMap<V>(m2);
    }

    public static <V> TLongObjectMap<V> synchronizedMap(TLongObjectMap<V> m2) {
        return new TSynchronizedLongObjectMap<V>(m2);
    }

    public static <V> TByteObjectMap<V> synchronizedMap(TByteObjectMap<V> m2) {
        return new TSynchronizedByteObjectMap<V>(m2);
    }

    public static <V> TShortObjectMap<V> synchronizedMap(TShortObjectMap<V> m2) {
        return new TSynchronizedShortObjectMap<V>(m2);
    }

    public static <V> TCharObjectMap<V> synchronizedMap(TCharObjectMap<V> m2) {
        return new TSynchronizedCharObjectMap<V>(m2);
    }

    public static <K> TObjectDoubleMap<K> synchronizedMap(TObjectDoubleMap<K> m2) {
        return new TSynchronizedObjectDoubleMap<K>(m2);
    }

    public static <K> TObjectFloatMap<K> synchronizedMap(TObjectFloatMap<K> m2) {
        return new TSynchronizedObjectFloatMap<K>(m2);
    }

    public static <K> TObjectIntMap<K> synchronizedMap(TObjectIntMap<K> m2) {
        return new TSynchronizedObjectIntMap<K>(m2);
    }

    public static <K> TObjectLongMap<K> synchronizedMap(TObjectLongMap<K> m2) {
        return new TSynchronizedObjectLongMap<K>(m2);
    }

    public static <K> TObjectByteMap<K> synchronizedMap(TObjectByteMap<K> m2) {
        return new TSynchronizedObjectByteMap<K>(m2);
    }

    public static <K> TObjectShortMap<K> synchronizedMap(TObjectShortMap<K> m2) {
        return new TSynchronizedObjectShortMap<K>(m2);
    }

    public static <K> TObjectCharMap<K> synchronizedMap(TObjectCharMap<K> m2) {
        return new TSynchronizedObjectCharMap<K>(m2);
    }
}

