/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ls<T>
extends ma<T> {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    protected final Date b;
    protected final String c;
    protected final Date d;
    protected final String e;

    public ls(T t2, Date date, String string, Date date2, String string2) {
        super(t2);
        this.b = date == null ? new Date() : date;
        this.c = string == null ? "(Unknown)" : string;
        this.d = date2;
        this.e = string2 == null ? "Banned by an operator." : string2;
    }

    protected ls(T t2, JsonObject jsonObject) {
        super(t2, jsonObject);
        Date date;
        try {
            date = jsonObject.has("created") ? a.parse(jsonObject.get("created").getAsString()) : new Date();
        }
        catch (ParseException parseException) {
            date = new Date();
        }
        this.b = date;
        this.c = jsonObject.has("source") ? jsonObject.get("source").getAsString() : "(Unknown)";
        try {
            \u2603 = jsonObject.has("expires") ? a.parse(jsonObject.get("expires").getAsString()) : null;
        }
        catch (ParseException \u26032) {
            \u2603 = null;
        }
        this.d = \u2603;
        this.e = jsonObject.has("reason") ? jsonObject.get("reason").getAsString() : "Banned by an operator.";
    }

    public Date c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    @Override
    boolean e() {
        if (this.d == null) {
            return false;
        }
        return this.d.before(new Date());
    }

    @Override
    protected void a(JsonObject jsonObject) {
        jsonObject.addProperty("created", a.format(this.b));
        jsonObject.addProperty("source", this.c);
        jsonObject.addProperty("expires", this.d == null ? "forever" : a.format(this.d));
        jsonObject.addProperty("reason", this.e);
    }
}

