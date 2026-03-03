/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class amk
extends amj<Boolean> {
    private final ImmutableSet<Boolean> a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));

    protected amk(String string) {
        super(string, Boolean.class);
    }

    @Override
    public Collection<Boolean> c() {
        return this.a;
    }

    public static amk a(String string) {
        return new amk(string);
    }

    @Override
    public String a(Boolean bl2) {
        return bl2.toString();
    }
}

