/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public interface Dns {
    public static final Dns SYSTEM = hostname -> {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        }
        try {
            return Arrays.asList(InetAddress.getAllByName(hostname));
        }
        catch (NullPointerException e2) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
            unknownHostException.initCause(e2);
            throw unknownHostException;
        }
    };

    public List<InetAddress> lookup(String var1) throws UnknownHostException;
}

