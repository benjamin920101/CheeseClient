/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class lu
extends mb<String, lv> {
    public lu(File file) {
        super(file);
    }

    @Override
    protected ma<String> a(JsonObject jsonObject) {
        return new lv(jsonObject);
    }

    public boolean a(SocketAddress socketAddress) {
        String string = this.c(socketAddress);
        return this.d(string);
    }

    @Override
    public lv b(SocketAddress socketAddress) {
        String string = this.c(socketAddress);
        return (lv)this.b(string);
    }

    private String c(SocketAddress socketAddress) {
        String string = socketAddress.toString();
        if (string.contains("/")) {
            string = string.substring(string.indexOf(47) + 1);
        }
        if (string.contains(":")) {
            string = string.substring(0, string.indexOf(58));
        }
        return string;
    }
}

