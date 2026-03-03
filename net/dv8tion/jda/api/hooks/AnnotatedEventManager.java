/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.utils.ClassWalker;

public class AnnotatedEventManager
implements IEventManager {
    private final Set<Object> listeners = ConcurrentHashMap.newKeySet();
    private final Map<Class<?>, Map<Object, List<Method>>> methods = new ConcurrentHashMap();

    @Override
    public void register(@Nonnull Object listener) {
        if (this.listeners.add(listener)) {
            this.updateMethods();
        }
    }

    @Override
    public void unregister(@Nonnull Object listener) {
        if (this.listeners.remove(listener)) {
            this.updateMethods();
        }
    }

    @Override
    @Nonnull
    public List<Object> getRegisteredListeners() {
        return Collections.unmodifiableList(new ArrayList<Object>(this.listeners));
    }

    @Override
    public void handle(@Nonnull GenericEvent event) {
        for (Class<?> eventClass : ClassWalker.walk(event.getClass())) {
            Map<Object, List<Method>> listeners = this.methods.get(eventClass);
            if (listeners == null) continue;
            listeners.forEach((key, value) -> value.forEach(method -> {
                block3: {
                    try {
                        method.setAccessible(true);
                        method.invoke(key, event);
                    }
                    catch (IllegalAccessException | InvocationTargetException e1) {
                        JDAImpl.LOG.error("Couldn't access annotated EventListener method", e1);
                    }
                    catch (Throwable throwable) {
                        JDAImpl.LOG.error("One of the EventListeners had an uncaught exception", throwable);
                        if (!(throwable instanceof Error)) break block3;
                        throw (Error)throwable;
                    }
                }
            }));
        }
    }

    private void updateMethods() {
        this.methods.clear();
        for (Object listener : this.listeners) {
            Method[] allMethods;
            boolean isClass = listener instanceof Class;
            Class<?> c2 = isClass ? (Class<?>)listener : listener.getClass();
            for (Method m2 : allMethods = c2.getDeclaredMethods()) {
                Class<?>[] pType;
                if (!m2.isAnnotationPresent(SubscribeEvent.class) || isClass && !Modifier.isStatic(m2.getModifiers()) || (pType = m2.getParameterTypes()).length != 1 || !GenericEvent.class.isAssignableFrom(pType[0])) continue;
                Class<?> eventClass = pType[0];
                if (!this.methods.containsKey(eventClass)) {
                    this.methods.put(eventClass, new ConcurrentHashMap());
                }
                if (!this.methods.get(eventClass).containsKey(listener)) {
                    this.methods.get(eventClass).put(listener, new CopyOnWriteArrayList());
                }
                this.methods.get(eventClass).get(listener).add(m2);
            }
        }
    }
}

