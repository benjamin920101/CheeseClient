/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class bqa
extends dd<jy, bpy> {
    private Map<jy, bpy> a;

    @Override
    protected Map<jy, bpy> b() {
        this.a = Maps.newHashMap();
        return this.a;
    }

    @Override
    public void a(bpy bpy2) {
        this.a(bpy2.c(), bpy2);
    }

    public void a() {
        this.a.clear();
    }
}

