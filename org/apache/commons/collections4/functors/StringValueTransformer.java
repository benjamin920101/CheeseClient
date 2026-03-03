/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Transformer;

public final class StringValueTransformer<T>
implements Transformer<T, String>,
Serializable {
    private static final long serialVersionUID = 7511110693171758606L;
    private static final Transformer<Object, String> INSTANCE = new StringValueTransformer<Object>();

    public static <T> Transformer<T, String> stringValueTransformer() {
        return INSTANCE;
    }

    private StringValueTransformer() {
    }

    @Override
    public String transform(T input) {
        return String.valueOf(input);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}

