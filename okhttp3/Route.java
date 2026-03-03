/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.annotation.Nullable;
import okhttp3.Address;

public final class Route {
    final Address address;
    final Proxy proxy;
    final InetSocketAddress inetSocketAddress;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (address == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
    }

    public Address address() {
        return this.address;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }

    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public boolean equals(@Nullable Object other) {
        return other instanceof Route && ((Route)other).address.equals(this.address) && ((Route)other).proxy.equals(this.proxy) && ((Route)other).inetSocketAddress.equals(this.inetSocketAddress);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.address.hashCode();
        result = 31 * result + this.proxy.hashCode();
        result = 31 * result + this.inetSocketAddress.hashCode();
        return result;
    }

    public String toString() {
        return "Route{" + this.inetSocketAddress + "}";
    }
}

