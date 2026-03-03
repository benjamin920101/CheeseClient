/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Type;
import java.util.List;
import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.dyncall.DyncallLibrary;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class DyncallStructs {
    Pointer<DyncallLibrary.DCstruct> struct;

    DyncallStructs() {
    }

    static int toDCAlignment(long structIOAlignment) {
        return structIOAlignment <= 0L ? 0 : (int)structIOAlignment;
    }

    public static Pointer<DyncallLibrary.DCstruct> buildDCstruct(StructIO io2) {
        if (!BridJ.Switch.StructsByValue.enabled) {
            return null;
        }
        List<StructIO.AggregatedFieldDesc> aggregatedFields = io2.getAggregatedFields();
        Pointer<DyncallLibrary.DCstruct> struct = DyncallLibrary.dcNewStruct(aggregatedFields.size(), DyncallStructs.toDCAlignment(io2.getStructAlignment())).withReleaser(new Pointer.Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                DyncallLibrary.dcFreeStruct(p2.as(DyncallLibrary.DCstruct.class));
            }
        });
        DyncallStructs.fillDCstruct(io2.structType, struct, aggregatedFields);
        DyncallLibrary.dcCloseStruct(struct);
        long expectedSize = io2.getStructSize();
        long size = DyncallLibrary.dcStructSize(struct);
        if (expectedSize != size) {
            BridJ.error("Struct size computed for " + Utils.toString(io2.structType) + " by BridJ (" + expectedSize + " bytes) and dyncall (" + size + " bytes) differ !");
            return null;
        }
        return struct;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected static void fillDCstruct(Type structType, Pointer<DyncallLibrary.DCstruct> struct, List<StructIO.AggregatedFieldDesc> aggregatedFields) {
        for (StructIO.AggregatedFieldDesc aggregatedField : aggregatedFields) {
            int dctype;
            StructIO.FieldDecl field = aggregatedField.fields.get(0);
            Type fieldType = field.desc.nativeTypeOrPointerTargetType;
            if (fieldType == null) {
                fieldType = field.desc.valueType;
            }
            Class fieldClass = Utils.getClass(fieldType);
            int alignment = DyncallStructs.toDCAlignment(aggregatedField.alignment);
            long arrayLength = field.desc.arrayLength;
            if (StructObject.class.isAssignableFrom(fieldClass)) {
                StructIO subIO = StructIO.getInstance(fieldClass, fieldType);
                List<StructIO.AggregatedFieldDesc> subAggregatedFields = subIO.getAggregatedFields();
                DyncallLibrary.dcSubStruct(struct, subAggregatedFields.size(), alignment, arrayLength);
                try {
                    DyncallStructs.fillDCstruct(subIO.structType, struct, subAggregatedFields);
                    continue;
                }
                finally {
                    DyncallLibrary.dcCloseStruct(struct);
                    continue;
                }
            }
            if (fieldClass == Integer.TYPE) {
                dctype = 105;
            } else if (fieldClass == Long.TYPE || fieldClass == Long.class) {
                dctype = 108;
            } else if (fieldClass == Short.TYPE || fieldClass == Character.TYPE || fieldClass == Short.class || fieldClass == Character.class) {
                dctype = 115;
            } else if (fieldClass == Byte.TYPE || fieldClass == Boolean.TYPE || fieldClass == Byte.class || fieldClass == Boolean.class) {
                dctype = 99;
            } else if (fieldClass == Float.TYPE || fieldClass == Float.class) {
                dctype = 102;
            } else if (fieldClass == Double.TYPE || fieldClass == Double.class) {
                dctype = 100;
            } else if (Pointer.class.isAssignableFrom(fieldClass)) {
                dctype = 112;
            } else {
                throw new IllegalArgumentException("Unable to create dyncall struct field for type " + Utils.toString(fieldType) + " in struct " + Utils.toString(structType));
            }
            DyncallLibrary.dcStructField(struct, dctype, alignment, arrayLength);
        }
    }
}

