/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ame {
    private static final Joiner a = Joiner.on(",");
    private final List<String[]> b = Lists.newArrayList();
    private final Map<Character, Predicate<amc>> c = Maps.newHashMap();
    private int d;
    private int e;

    private ame() {
        this.c.put(Character.valueOf(' '), Predicates.alwaysTrue());
    }

    public ame a(String ... stringArray2) {
        String[] stringArray2;
        if (ArrayUtils.isEmpty(stringArray2) || StringUtils.isEmpty(stringArray2[0])) {
            throw new IllegalArgumentException("Empty pattern for aisle");
        }
        if (this.b.isEmpty()) {
            this.d = stringArray2.length;
            this.e = stringArray2[0].length();
        }
        if (stringArray2.length != this.d) {
            throw new IllegalArgumentException("Expected aisle with height of " + this.d + ", but was given one with a height of " + stringArray2.length + ")");
        }
        for (String string : stringArray2) {
            if (string.length() != this.e) {
                throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.e + ", found one with " + string.length() + ")");
            }
            for (char c2 : string.toCharArray()) {
                if (this.c.containsKey(Character.valueOf(c2))) continue;
                this.c.put(Character.valueOf(c2), null);
            }
        }
        this.b.add(stringArray2);
        return this;
    }

    public static ame a() {
        return new ame();
    }

    public ame a(char c2, Predicate<amc> predicate) {
        this.c.put(Character.valueOf(c2), predicate);
        return this;
    }

    public amd b() {
        return new amd(this.c());
    }

    private Predicate<amc>[][][] c() {
        this.d();
        Predicate[][][] predicateArray = (Predicate[][][])Array.newInstance(Predicate.class, this.b.size(), this.d, this.e);
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            for (\u2603 = 0; \u2603 < this.d; ++\u2603) {
                for (\u2603 = 0; \u2603 < this.e; ++\u2603) {
                    predicateArray[i2][\u2603][\u2603] = this.c.get(Character.valueOf(this.b.get(i2)[\u2603].charAt(\u2603)));
                }
            }
        }
        return predicateArray;
    }

    private void d() {
        ArrayList<Character> arrayList = Lists.newArrayList();
        for (Map.Entry<Character, Predicate<amc>> entry : this.c.entrySet()) {
            if (entry.getValue() != null) continue;
            arrayList.add(entry.getKey());
        }
        if (!arrayList.isEmpty()) {
            throw new IllegalStateException("Predicates for character(s) " + a.join(arrayList) + " are missing");
        }
    }
}

