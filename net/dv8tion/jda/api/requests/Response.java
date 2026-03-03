/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.exceptions.ParsingException;
import net.dv8tion.jda.api.utils.IOFunction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.utils.IOUtil;

public class Response
implements Closeable {
    public static final int ERROR_CODE = -1;
    public static final String ERROR_MESSAGE = "ERROR";
    public static final IOFunction<BufferedReader, DataObject> JSON_SERIALIZE_OBJECT = DataObject::fromJson;
    public static final IOFunction<BufferedReader, DataArray> JSON_SERIALIZE_ARRAY = DataArray::fromJson;
    public final int code;
    public final String message;
    public final long retryAfter;
    private final InputStream body;
    private final okhttp3.Response rawResponse;
    private final Set<String> cfRays;
    private String fallbackString;
    private Object object;
    private boolean attemptedParsing = false;
    private Exception exception;

    public Response(@Nullable okhttp3.Response response, @Nonnull Exception exception, @Nonnull Set<String> cfRays) {
        this(response, response != null ? response.code() : -1, ERROR_MESSAGE, -1L, cfRays);
        this.exception = exception;
    }

    public Response(@Nullable okhttp3.Response response, int code, @Nonnull String message, long retryAfter, @Nonnull Set<String> cfRays) {
        this.rawResponse = response;
        this.code = code;
        this.message = message;
        this.exception = null;
        this.retryAfter = retryAfter;
        this.cfRays = cfRays;
        if (response == null) {
            this.body = null;
        } else {
            try {
                this.body = IOUtil.getBody(response);
            }
            catch (Exception e2) {
                throw new IllegalStateException("An error occurred while parsing the response for a RestAction", e2);
            }
        }
    }

    public Response(long retryAfter, @Nonnull Set<String> cfRays) {
        this(null, 429, "TOO MANY REQUESTS", retryAfter, cfRays);
    }

    public Response(@Nonnull okhttp3.Response response, long retryAfter, @Nonnull Set<String> cfRays) {
        this(response, response.code(), response.message(), retryAfter, cfRays);
    }

    @Nonnull
    public DataArray getArray() {
        return this.get(DataArray.class, JSON_SERIALIZE_ARRAY);
    }

    @Nonnull
    public Optional<DataArray> optArray() {
        return this.parseBody(true, DataArray.class, JSON_SERIALIZE_ARRAY);
    }

    @Nonnull
    public DataObject getObject() {
        return this.get(DataObject.class, JSON_SERIALIZE_OBJECT);
    }

    @Nonnull
    public Optional<DataObject> optObject() {
        return this.parseBody(true, DataObject.class, JSON_SERIALIZE_OBJECT);
    }

    @Nonnull
    public String getString() {
        return this.parseBody(String.class, this::readString).orElseGet(() -> this.fallbackString == null ? "N/A" : this.fallbackString);
    }

    @Nonnull
    public <T> T get(Class<T> clazz, IOFunction<BufferedReader, T> parser) {
        return this.parseBody(clazz, parser).orElseThrow(IllegalStateException::new);
    }

    @Nullable
    public okhttp3.Response getRawResponse() {
        return this.rawResponse;
    }

    @Nonnull
    public Set<String> getCFRays() {
        return this.cfRays;
    }

    @Nullable
    public Exception getException() {
        return this.exception;
    }

    public boolean isError() {
        return this.code == -1;
    }

    public boolean isOk() {
        return this.code > 199 && this.code < 300;
    }

    public boolean isRateLimit() {
        return this.code == 429;
    }

    public String toString() {
        return this.exception == null ? "HTTPResponse[" + this.code + (this.object == null ? "" : ", " + this.object.toString()) + ']' : "HTTPException[" + this.exception.getMessage() + ']';
    }

    @Override
    public void close() {
        if (this.rawResponse != null) {
            this.rawResponse.close();
        }
    }

    private String readString(BufferedReader reader) {
        return reader.lines().collect(Collectors.joining("\n"));
    }

    private <T> Optional<T> parseBody(Class<T> clazz, IOFunction<BufferedReader, T> parser) {
        return this.parseBody(false, clazz, parser);
    }

    private <T> Optional<T> parseBody(boolean opt, Class<T> clazz, IOFunction<BufferedReader, T> parser) {
        if (this.attemptedParsing) {
            if (this.object != null && clazz.isAssignableFrom(this.object.getClass())) {
                return Optional.of(clazz.cast(this.object));
            }
            return Optional.empty();
        }
        this.attemptedParsing = true;
        if (this.body == null || this.rawResponse == null || this.rawResponse.body().contentLength() == 0L) {
            return Optional.empty();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.body));
            reader.mark(1024);
            T t2 = parser.apply(reader);
            this.object = t2;
            return Optional.ofNullable(t2);
        }
        catch (Exception e2) {
            try {
                reader.reset();
                this.fallbackString = this.readString(reader);
                reader.close();
            }
            catch (IOException | NullPointerException exception) {
                // empty catch block
            }
            if (opt && e2 instanceof ParsingException) {
                return Optional.empty();
            }
            throw new IllegalStateException("An error occurred while parsing the response for a RestAction", e2);
        }
    }
}

