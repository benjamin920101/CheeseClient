/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;

class Jdk8WithJettyBootPlatform
extends Platform {
    private final Method putMethod;
    private final Method getMethod;
    private final Method removeMethod;
    private final Class<?> clientProviderClass;
    private final Class<?> serverProviderClass;

    Jdk8WithJettyBootPlatform(Method putMethod, Method getMethod, Method removeMethod, Class<?> clientProviderClass, Class<?> serverProviderClass) {
        this.putMethod = putMethod;
        this.getMethod = getMethod;
        this.removeMethod = removeMethod;
        this.clientProviderClass = clientProviderClass;
        this.serverProviderClass = serverProviderClass;
    }

    @Override
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        List<String> names = Jdk8WithJettyBootPlatform.alpnProtocolNames(protocols);
        try {
            Object alpnProvider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, (InvocationHandler)new AlpnProvider(names));
            this.putMethod.invoke(null, sslSocket, alpnProvider);
        }
        catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        }
    }

    @Override
    public void afterHandshake(SSLSocket sslSocket) {
        try {
            this.removeMethod.invoke(null, sslSocket);
        }
        catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        }
    }

    @Override
    @Nullable
    public String getSelectedProtocol(SSLSocket socket) {
        try {
            AlpnProvider provider = (AlpnProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, socket));
            if (!provider.unsupported && provider.selected == null) {
                Platform.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            return provider.unsupported ? null : provider.selected;
        }
        catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }

    public static Platform buildIfSupported() {
        try {
            String alpnClassName = "org.eclipse.jetty.alpn.ALPN";
            Class<?> alpnClass = Class.forName(alpnClassName);
            Class<?> providerClass = Class.forName(alpnClassName + "$Provider");
            Class<?> clientProviderClass = Class.forName(alpnClassName + "$ClientProvider");
            Class<?> serverProviderClass = Class.forName(alpnClassName + "$ServerProvider");
            Method putMethod = alpnClass.getMethod("put", SSLSocket.class, providerClass);
            Method getMethod = alpnClass.getMethod("get", SSLSocket.class);
            Method removeMethod = alpnClass.getMethod("remove", SSLSocket.class);
            return new Jdk8WithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass);
        }
        catch (ClassNotFoundException | NoSuchMethodException reflectiveOperationException) {
            return null;
        }
    }

    private static class AlpnProvider
    implements InvocationHandler {
        private final List<String> protocols;
        boolean unsupported;
        String selected;

        AlpnProvider(List<String> protocols) {
            this.protocols = protocols;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (args == null) {
                args = Util.EMPTY_STRING_ARRAY;
            }
            if (methodName.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (methodName.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            }
            if (methodName.equals("protocols") && args.length == 0) {
                return this.protocols;
            }
            if ((methodName.equals("selectProtocol") || methodName.equals("select")) && String.class == returnType && args.length == 1 && args[0] instanceof List) {
                List peerProtocols = (List)args[0];
                int size = peerProtocols.size();
                for (int i2 = 0; i2 < size; ++i2) {
                    String protocol = (String)peerProtocols.get(i2);
                    if (!this.protocols.contains(protocol)) continue;
                    this.selected = protocol;
                    return this.selected;
                }
                this.selected = this.protocols.get(0);
                return this.selected;
            }
            if ((methodName.equals("protocolSelected") || methodName.equals("selected")) && args.length == 1) {
                this.selected = (String)args[0];
                return null;
            }
            return method.invoke((Object)this, args);
        }
    }
}

