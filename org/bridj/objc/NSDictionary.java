/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import java.util.HashMap;
import java.util.Map;
import org.bridj.BridJ;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.objc.FoundationLibrary;
import org.bridj.objc.NSObject;
import org.bridj.objc.NSString;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NSDictionary
extends NSObject {
    public native Pointer<NSObject> valueForKey(Pointer<NSString> var1);

    public native Pointer<NSObject> objectForKey(Pointer<NSObject> var1);

    public native int count();

    public native void getObjects_andKeys(Pointer<Pointer<NSObject>> var1, Pointer<Pointer<NSObject>> var2);

    public static native Pointer<NSDictionary> dictionaryWithContentsOfFile(Pointer<NSString> var0);

    public static native Pointer<NSDictionary> dictionaryWithObjects_forKeys_count(Pointer<Pointer<NSObject>> var0, Pointer<Pointer<NSObject>> var1, int var2);

    public static Pointer<NSDictionary> pointerToNSDictionary(Map<String, NSObject> map) {
        int n2 = map.size();
        Pointer<Pointer<NSObject>> objects = Pointer.allocatePointers(NSObject.class, n2);
        Pointer<Pointer<NSObject>> keys = Pointer.allocatePointers(NSObject.class, n2);
        int i2 = 0;
        for (Map.Entry<String, NSObject> e2 : map.entrySet()) {
            keys.set(i2, FoundationLibrary.pointerToNSString(e2.getKey()));
            objects.set(i2, Pointer.pointerTo((NativeObject)e2.getValue()));
            ++i2;
        }
        return NSDictionary.dictionaryWithObjects_forKeys_count(objects, keys, n2);
    }

    public static NSDictionary valueOf(Map<String, NSObject> map) {
        return NSDictionary.pointerToNSDictionary(map).get();
    }

    public Map<String, NSObject> toMap() {
        int n2 = this.count();
        Pointer<Pointer<NSObject>> objects = Pointer.allocatePointers(NSObject.class, n2);
        Pointer<Pointer<NSObject>> keys = Pointer.allocatePointers(NSString.class, n2);
        this.getObjects_andKeys(objects, keys);
        HashMap<String, NSObject> ret = new HashMap<String, NSObject>();
        for (int i2 = 0; i2 < n2; ++i2) {
            Pointer<NSObject> key = keys.get(i2);
            Pointer<NSObject> value = objects.get(i2);
            ret.put(((NSString)key.get()).toString(), value == null ? null : value.get());
        }
        return ret;
    }

    static {
        BridJ.register();
    }
}

