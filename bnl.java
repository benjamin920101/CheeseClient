/*
 * Decompiled with CFR 0.152.
 */
import java.io.File;
import java.io.FileNotFoundException;

public class bnl
extends FileNotFoundException {
    public bnl(File file, String string) {
        super(String.format("'%s' in ResourcePack '%s'", string, file));
    }
}

