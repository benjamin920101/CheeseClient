/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public class aml
extends amm<cq> {
    protected aml(String string, Collection<cq> collection) {
        super(string, cq.class, collection);
    }

    public static aml a(String string) {
        return aml.a(string, Predicates.<cq>alwaysTrue());
    }

    public static aml a(String string, Predicate<cq> predicate) {
        return aml.a(string, Collections2.filter(Lists.newArrayList(cq.values()), predicate));
    }

    public static aml a(String string, Collection<cq> collection) {
        return new aml(string, collection);
    }
}

