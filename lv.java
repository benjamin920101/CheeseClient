/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import java.util.Date;

public class lv
extends ls<String> {
    public lv(String string) {
        this(string, (Date)null, (String)null, (Date)null, (String)null);
    }

    public lv(String string, Date date, String string2, Date date2, String string3) {
        super(string, date, string2, date2, string3);
    }

    public lv(JsonObject jsonObject) {
        super(lv.b(jsonObject), jsonObject);
    }

    private static String b(JsonObject jsonObject) {
        return jsonObject.has("ip") ? jsonObject.get("ip").getAsString() : null;
    }

    @Override
    protected void a(JsonObject jsonObject) {
        if (this.f() == null) {
            return;
        }
        jsonObject.addProperty("ip", (String)this.f());
        super.a(jsonObject);
    }
}

