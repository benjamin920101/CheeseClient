/*
 * Decompiled with CFR 0.152.
 */
public class fc
extends IllegalArgumentException {
    public fc(fb fb2, String string) {
        super(String.format("Error parsing: %s: %s", fb2, string));
    }

    public fc(fb fb2, int n2) {
        super(String.format("Invalid index %d requested for %s", n2, fb2));
    }

    public fc(fb fb2, Throwable throwable) {
        super(String.format("Error while parsing: %s", fb2), throwable);
    }
}

