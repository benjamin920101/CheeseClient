/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.utils.Checks;

public class ErrorHandler
implements Consumer<Throwable> {
    private static final Consumer<? super Throwable> empty = e2 -> {};
    private final Consumer<? super Throwable> base;
    private final Map<Predicate<? super Throwable>, Consumer<? super Throwable>> cases = new LinkedHashMap<Predicate<? super Throwable>, Consumer<? super Throwable>>();

    public ErrorHandler() {
        this(RestAction.getDefaultFailure());
    }

    public ErrorHandler(@Nonnull Consumer<? super Throwable> base) {
        Checks.notNull(base, "Consumer");
        this.base = base;
    }

    @Nonnull
    public ErrorHandler ignore(@Nonnull ErrorResponse ignored, ErrorResponse ... errorResponses) {
        Checks.notNull((Object)ignored, "ErrorResponse");
        Checks.noneNull((Object[])errorResponses, "ErrorResponse");
        return this.ignore(EnumSet.of(ignored, errorResponses));
    }

    @Nonnull
    public ErrorHandler ignore(@Nonnull Collection<ErrorResponse> errorResponses) {
        return this.handle(errorResponses, empty);
    }

    @Nonnull
    public ErrorHandler ignore(@Nonnull Class<?> clazz, Class<?> ... classes) {
        Checks.notNull(clazz, "Classes");
        Checks.noneNull(classes, "Classes");
        return this.ignore((? super Throwable it2) -> {
            if (clazz.isInstance(it2)) {
                return true;
            }
            for (Class e2 : classes) {
                if (!e2.isInstance(it2)) continue;
                return true;
            }
            return false;
        });
    }

    @Nonnull
    public ErrorHandler ignore(@Nonnull Predicate<? super Throwable> condition) {
        return this.handle(condition, empty);
    }

    @Nonnull
    public ErrorHandler handle(@Nonnull ErrorResponse response, @Nonnull Consumer<? super ErrorResponseException> handler) {
        Checks.notNull((Object)response, "ErrorResponse");
        return this.handle(EnumSet.of(response), handler);
    }

    @Nonnull
    public ErrorHandler handle(@Nonnull Collection<ErrorResponse> errorResponses, @Nonnull Consumer<? super ErrorResponseException> handler) {
        Checks.notNull(handler, "Handler");
        Checks.noneNull(errorResponses, "ErrorResponse");
        return this.handle(ErrorResponseException.class, (? super T it2) -> errorResponses.contains((Object)it2.getErrorResponse()), handler);
    }

    @Nonnull
    public <T> ErrorHandler handle(@Nonnull Class<T> clazz, @Nonnull Consumer<? super T> handler) {
        Checks.notNull(clazz, "Class");
        Checks.notNull(handler, "Handler");
        return this.handle(clazz::isInstance, (? super Throwable ex2) -> handler.accept((Object)clazz.cast(ex2)));
    }

    @Nonnull
    public <T> ErrorHandler handle(@Nonnull Class<T> clazz, @Nonnull Predicate<? super T> condition, @Nonnull Consumer<? super T> handler) {
        Checks.notNull(clazz, "Class");
        Checks.notNull(handler, "Handler");
        return this.handle((? super Throwable it2) -> clazz.isInstance(it2) && condition.test((Object)clazz.cast(it2)), (? super Throwable ex2) -> handler.accept((Object)clazz.cast(ex2)));
    }

    @Nonnull
    public ErrorHandler handle(@Nonnull Collection<Class<?>> clazz, @Nullable Predicate<? super Throwable> condition, @Nonnull Consumer<? super Throwable> handler) {
        Checks.noneNull(clazz, "Class");
        Checks.notNull(handler, "Handler");
        ArrayList classes = new ArrayList(clazz);
        Predicate<Throwable> check = it2 -> classes.stream().anyMatch(c2 -> c2.isInstance(it2)) && (condition == null || condition.test((Throwable)it2));
        return this.handle(check, handler);
    }

    @Nonnull
    public ErrorHandler handle(@Nonnull Predicate<? super Throwable> condition, @Nonnull Consumer<? super Throwable> handler) {
        Checks.notNull(condition, "Condition");
        Checks.notNull(handler, "Handler");
        this.cases.put(condition, handler);
        return this;
    }

    @Override
    public void accept(Throwable t2) {
        for (Map.Entry<Predicate<? super Throwable>, Consumer<? super Throwable>> entry : this.cases.entrySet()) {
            Predicate<? super Throwable> condition = entry.getKey();
            Consumer<? super Throwable> callback = entry.getValue();
            if (!condition.test(t2)) continue;
            callback.accept(t2);
            return;
        }
        this.base.accept(t2);
    }
}

