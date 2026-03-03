/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;
import net.dv8tion.jda.internal.utils.JDALogger;

public class ErrorResponseException
extends RuntimeException {
    private final ErrorResponse errorResponse;
    private final Response response;
    private final String meaning;
    private final int code;
    private final List<SchemaError> schemaErrors;

    private ErrorResponseException(ErrorResponse errorResponse, Response response, int code, String meaning, List<SchemaError> schemaErrors) {
        super(code + ": " + meaning + (schemaErrors.isEmpty() ? "" : "\n" + schemaErrors.stream().map(SchemaError::toString).collect(Collectors.joining("\n"))));
        this.response = response;
        if (response != null && response.getException() != null) {
            this.initCause(response.getException());
        }
        this.errorResponse = errorResponse;
        this.code = code;
        this.meaning = meaning;
        this.schemaErrors = schemaErrors;
    }

    public boolean isServerError() {
        return this.errorResponse == ErrorResponse.SERVER_ERROR;
    }

    public String getMeaning() {
        return this.meaning;
    }

    public int getErrorCode() {
        return this.code;
    }

    public ErrorResponse getErrorResponse() {
        return this.errorResponse;
    }

    public Response getResponse() {
        return this.response;
    }

    @Nonnull
    public List<SchemaError> getSchemaErrors() {
        return this.schemaErrors;
    }

    public static ErrorResponseException create(ErrorResponse errorResponse, Response response) {
        String meaning = errorResponse.getMeaning();
        int code = errorResponse.getCode();
        ArrayList<SchemaError> schemaErrors = new ArrayList<SchemaError>();
        try {
            Optional<DataObject> optObj = response.optObject();
            if (response.isError() && response.getException() != null) {
                code = response.code;
                meaning = response.getException().getClass().getName();
            } else if (optObj.isPresent()) {
                DataObject obj = optObj.get();
                if (!obj.isNull("code") || !obj.isNull("message")) {
                    if (!obj.isNull("code")) {
                        code = obj.getInt("code");
                    }
                    if (!obj.isNull("message")) {
                        meaning = obj.getString("message");
                    }
                } else {
                    code = response.code;
                    meaning = obj.toString();
                }
                obj.optObject("errors").ifPresent(schema -> ErrorResponseException.parseSchema(schemaErrors, "", schema));
            } else {
                code = response.code;
                meaning = response.getString();
            }
        }
        catch (Exception e2) {
            JDALogger.getLog(ErrorResponseException.class).error("Failed to parse parts of error response. Body: {}", (Object)response.getString(), (Object)e2);
        }
        return new ErrorResponseException(errorResponse, response, code, meaning, schemaErrors);
    }

    private static void parseSchema(List<SchemaError> schemaErrors, String currentLocation, DataObject errors) {
        for (String name : errors.keys()) {
            DataObject schemaError = errors.getObject(name);
            if (!schemaError.isNull("_errors")) {
                schemaErrors.add(ErrorResponseException.parseSchemaError(currentLocation + name, schemaError));
                continue;
            }
            if (schemaError.keys().stream().allMatch(Helpers::isNumeric)) {
                for (String index : schemaError.keys()) {
                    DataObject properties = schemaError.getObject(index);
                    String location = String.format("%s%s[%s].", currentLocation, name, index);
                    if (properties.hasKey("_errors")) {
                        schemaErrors.add(ErrorResponseException.parseSchemaError(location.substring(0, location.length() - 1), properties));
                        continue;
                    }
                    ErrorResponseException.parseSchema(schemaErrors, location, properties);
                }
                continue;
            }
            String location = String.format("%s%s.", currentLocation, name);
            ErrorResponseException.parseSchema(schemaErrors, location, schemaError);
        }
    }

    private static SchemaError parseSchemaError(String location, DataObject obj) {
        List codes = obj.getArray("_errors").stream(DataArray::getObject).map(json -> new ErrorCode(json.getString("code"), json.getString("message"))).collect(Collectors.toList());
        return new SchemaError(location, codes);
    }

    @Nonnull
    public static Consumer<Throwable> ignore(@Nonnull Collection<ErrorResponse> set) {
        return ErrorResponseException.ignore(RestAction.getDefaultFailure(), set);
    }

    @Nonnull
    public static Consumer<Throwable> ignore(@Nonnull ErrorResponse ignored, ErrorResponse ... errorResponses) {
        return ErrorResponseException.ignore(RestAction.getDefaultFailure(), ignored, errorResponses);
    }

    @Nonnull
    public static Consumer<Throwable> ignore(@Nonnull Consumer<? super Throwable> orElse, @Nonnull ErrorResponse ignored, ErrorResponse ... errorResponses) {
        return ErrorResponseException.ignore(orElse, EnumSet.of(ignored, errorResponses));
    }

    @Nonnull
    public static Consumer<Throwable> ignore(@Nonnull Consumer<? super Throwable> orElse, @Nonnull Collection<ErrorResponse> set) {
        Checks.notNull(orElse, "Callback");
        Checks.notEmpty(set, "Ignored collection");
        EnumSet<ErrorResponse> ignored = EnumSet.copyOf(set);
        return new ErrorHandler(orElse).ignore(ignored);
    }

    public static class SchemaError {
        private final String location;
        private final List<ErrorCode> errors;

        private SchemaError(String location, List<ErrorCode> codes) {
            this.location = location;
            this.errors = codes;
        }

        @Nonnull
        public String getLocation() {
            return this.location;
        }

        @Nonnull
        public List<ErrorCode> getErrors() {
            return this.errors;
        }

        public String toString() {
            return this.location + "\n\t- " + this.errors.stream().map(Object::toString).collect(Collectors.joining("\n\t- "));
        }
    }

    public static class ErrorCode {
        private final String code;
        private final String message;

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Nonnull
        public String getCode() {
            return this.code;
        }

        @Nonnull
        public String getMessage() {
            return this.message;
        }

        public String toString() {
            return this.code + ": " + this.message;
        }
    }
}

