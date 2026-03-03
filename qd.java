/*
 * Decompiled with CFR 0.152.
 */
import io.netty.util.internal.ThreadLocalRandom;
import java.util.UUID;
import org.apache.commons.lang3.Validate;

public class qd {
    private final double a;
    private final int b;
    private final String c;
    private final UUID d;
    private boolean e = true;

    public qd(String string, double d2, int n2) {
        this(ns.a(ThreadLocalRandom.current()), string, d2, n2);
    }

    public qd(UUID uUID, String string, double d2, int n2) {
        this.d = uUID;
        this.c = string;
        this.a = d2;
        this.b = n2;
        Validate.notEmpty(string, "Modifier name cannot be empty", new Object[0]);
        Validate.inclusiveBetween(0L, 2L, n2, "Invalid operation");
    }

    public UUID a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public double d() {
        return this.a;
    }

    public boolean e() {
        return this.e;
    }

    public qd a(boolean bl2) {
        this.e = bl2;
        return this;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        qd qd2 = (qd)object;
        return !(this.d != null ? !this.d.equals(qd2.d) : qd2.d != null);
    }

    public int hashCode() {
        return this.d != null ? this.d.hashCode() : 0;
    }

    public String toString() {
        return "AttributeModifier{amount=" + this.a + ", operation=" + this.b + ", name='" + this.c + '\'' + ", id=" + this.d + ", serialize=" + this.e + '}';
    }
}

