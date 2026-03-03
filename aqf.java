/*
 * Decompiled with CFR 0.152.
 */
import java.util.Map;

public class aqf
extends aqq {
    private double d = 0.004;

    public aqf() {
    }

    @Override
    public String a() {
        return "Mineshaft";
    }

    public aqf(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!entry.getKey().equals("chance")) continue;
            this.d = ns.a(entry.getValue(), this.d);
        }
    }

    @Override
    protected boolean a(int n2, int n3) {
        return this.b.nextDouble() < this.d && this.b.nextInt(80) < Math.max(Math.abs(n2), Math.abs(n3));
    }

    @Override
    protected aqu b(int n2, int n3) {
        return new aqh(this.c, this.b, n2, n3);
    }
}

