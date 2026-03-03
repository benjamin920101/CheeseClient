/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class sq<T extends pr>
extends sp {
    private qa g;

    public sq(qa qa2, Class<T> clazz, boolean bl2, Predicate<? super T> predicate) {
        super(qa2, clazz, 10, bl2, false, predicate);
        this.g = qa2;
    }

    @Override
    public boolean a() {
        return !this.g.cl() && super.a();
    }
}

