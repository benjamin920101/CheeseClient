/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.ServiceLoader;

public class NioPathDeserializer
extends StdScalarDeserializer<Path> {
    private static final long serialVersionUID = 1L;
    private static final boolean areWindowsFilePathsSupported;

    public NioPathDeserializer() {
        super(Path.class);
    }

    @Override
    public Path deserialize(JsonParser p2, DeserializationContext ctxt) throws IOException {
        URI uri;
        if (!p2.hasToken(JsonToken.VALUE_STRING)) {
            return (Path)ctxt.handleUnexpectedToken(Path.class, p2);
        }
        String value = p2.getText();
        if (value.indexOf(58) < 0) {
            return Paths.get(value, new String[0]);
        }
        if (areWindowsFilePathsSupported && value.length() >= 2 && Character.isLetter(value.charAt(0)) && value.charAt(1) == ':') {
            return Paths.get(value, new String[0]);
        }
        try {
            uri = new URI(value);
        }
        catch (URISyntaxException e2) {
            return (Path)ctxt.handleInstantiationProblem(this.handledType(), value, e2);
        }
        try {
            return Paths.get(uri);
        }
        catch (FileSystemNotFoundException cause) {
            try {
                String scheme = uri.getScheme();
                for (FileSystemProvider provider : ServiceLoader.load(FileSystemProvider.class)) {
                    if (!provider.getScheme().equalsIgnoreCase(scheme)) continue;
                    return provider.getPath(uri);
                }
                return (Path)ctxt.handleInstantiationProblem(this.handledType(), value, cause);
            }
            catch (Throwable e3) {
                e3.addSuppressed(cause);
                return (Path)ctxt.handleInstantiationProblem(this.handledType(), value, e3);
            }
        }
        catch (Throwable e4) {
            return (Path)ctxt.handleInstantiationProblem(this.handledType(), value, e4);
        }
    }

    static {
        boolean isWindowsRootFound = false;
        for (File file : File.listRoots()) {
            String path = file.getPath();
            if (path.length() < 2 || !Character.isLetter(path.charAt(0)) || path.charAt(1) != ':') continue;
            isWindowsRootFound = true;
            break;
        }
        areWindowsFilePathsSupported = isWindowsRootFound;
    }
}

