/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteDatabase;

public final class RouteSelector {
    private final Address address;
    private final RouteDatabase routeDatabase;
    private final Call call;
    private final EventListener eventListener;
    private List<Proxy> proxies = Collections.emptyList();
    private int nextProxyIndex;
    private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
    private final List<Route> postponedRoutes = new ArrayList<Route>();

    public RouteSelector(Address address, RouteDatabase routeDatabase, Call call, EventListener eventListener) {
        this.address = address;
        this.routeDatabase = routeDatabase;
        this.call = call;
        this.eventListener = eventListener;
        this.resetNextProxy(address.url(), address.proxy());
    }

    public boolean hasNext() {
        return this.hasNextProxy() || !this.postponedRoutes.isEmpty();
    }

    public Selection next() throws IOException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        ArrayList<Route> routes = new ArrayList<Route>();
        while (this.hasNextProxy()) {
            Proxy proxy = this.nextProxy();
            int size = this.inetSocketAddresses.size();
            for (int i2 = 0; i2 < size; ++i2) {
                Route route = new Route(this.address, proxy, this.inetSocketAddresses.get(i2));
                if (this.routeDatabase.shouldPostpone(route)) {
                    this.postponedRoutes.add(route);
                    continue;
                }
                routes.add(route);
            }
            if (routes.isEmpty()) continue;
            break;
        }
        if (routes.isEmpty()) {
            routes.addAll(this.postponedRoutes);
            this.postponedRoutes.clear();
        }
        return new Selection(routes);
    }

    public void connectFailed(Route failedRoute, IOException failure) {
        if (failedRoute.proxy().type() != Proxy.Type.DIRECT && this.address.proxySelector() != null) {
            this.address.proxySelector().connectFailed(this.address.url().uri(), failedRoute.proxy().address(), failure);
        }
        this.routeDatabase.failed(failedRoute);
    }

    private void resetNextProxy(HttpUrl url, Proxy proxy) {
        List<Proxy> proxiesOrNull;
        this.proxies = proxy != null ? Collections.singletonList(proxy) : ((proxiesOrNull = this.address.proxySelector().select(url.uri())) != null && !proxiesOrNull.isEmpty() ? Util.immutableList(proxiesOrNull) : Util.immutableList(Proxy.NO_PROXY));
        this.nextProxyIndex = 0;
    }

    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private Proxy nextProxy() throws IOException {
        if (!this.hasNextProxy()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        Proxy result = this.proxies.get(this.nextProxyIndex++);
        this.resetNextInetSocketAddress(result);
        return result;
    }

    private void resetNextInetSocketAddress(Proxy proxy) throws IOException {
        int socketPort;
        String socketHost;
        this.inetSocketAddresses = new ArrayList<InetSocketAddress>();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            socketHost = this.address.url().host();
            socketPort = this.address.url().port();
        } else {
            SocketAddress proxyAddress = proxy.address();
            if (!(proxyAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + proxyAddress.getClass());
            }
            InetSocketAddress proxySocketAddress = (InetSocketAddress)proxyAddress;
            socketHost = RouteSelector.getHostString(proxySocketAddress);
            socketPort = proxySocketAddress.getPort();
        }
        if (socketPort < 1 || socketPort > 65535) {
            throw new SocketException("No route to " + socketHost + ":" + socketPort + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.inetSocketAddresses.add(InetSocketAddress.createUnresolved(socketHost, socketPort));
        } else {
            this.eventListener.dnsStart(this.call, socketHost);
            List<InetAddress> addresses = this.address.dns().lookup(socketHost);
            if (addresses.isEmpty()) {
                throw new UnknownHostException(this.address.dns() + " returned no addresses for " + socketHost);
            }
            this.eventListener.dnsEnd(this.call, socketHost, addresses);
            int size = addresses.size();
            for (int i2 = 0; i2 < size; ++i2) {
                InetAddress inetAddress = addresses.get(i2);
                this.inetSocketAddresses.add(new InetSocketAddress(inetAddress, socketPort));
            }
        }
    }

    static String getHostString(InetSocketAddress socketAddress) {
        InetAddress address = socketAddress.getAddress();
        if (address == null) {
            return socketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    public static final class Selection {
        private final List<Route> routes;
        private int nextRouteIndex = 0;

        Selection(List<Route> routes) {
            this.routes = routes;
        }

        public boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public Route next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return this.routes.get(this.nextRouteIndex++);
        }

        public List<Route> getAll() {
            return new ArrayList<Route>(this.routes);
        }
    }
}

