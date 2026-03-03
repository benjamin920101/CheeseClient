/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;

public abstract class amj<T extends Comparable<T>>
implements amo<T> {
    private final Class<T> a;
    private final String b;

    protected amj(String string, Class<T> clazz) {
        this.a = clazz;
        this.b = string;
    }

    @Override
    public String a() {
        return this.b;
    }

    @Override
    public Class<T> b() {
        return this.a;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.b).add("clazz", this.a).add("values", this.c()).toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        amj amj2 = (amj)object;
        return this.a.equals(amj2.a) && this.b.equals(amj2.b);
    }

    public int hashCode() {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }
}

