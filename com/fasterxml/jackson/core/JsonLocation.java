/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core;

import java.io.Serializable;
import java.nio.charset.Charset;

public class JsonLocation
implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MAX_CONTENT_SNIPPET = 500;
    public static final JsonLocation NA = new JsonLocation(null, -1L, -1L, -1, -1);
    protected final long _totalBytes;
    protected final long _totalChars;
    protected final int _lineNr;
    protected final int _columnNr;
    final transient Object _sourceRef;

    public JsonLocation(Object srcRef, long totalChars, int lineNr, int colNr) {
        this(srcRef, -1L, totalChars, lineNr, colNr);
    }

    public JsonLocation(Object sourceRef, long totalBytes, long totalChars, int lineNr, int columnNr) {
        this._sourceRef = sourceRef;
        this._totalBytes = totalBytes;
        this._totalChars = totalChars;
        this._lineNr = lineNr;
        this._columnNr = columnNr;
    }

    public Object getSourceRef() {
        return this._sourceRef;
    }

    public int getLineNr() {
        return this._lineNr;
    }

    public int getColumnNr() {
        return this._columnNr;
    }

    public long getCharOffset() {
        return this._totalChars;
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public String sourceDescription() {
        return this._appendSourceDesc(new StringBuilder(100)).toString();
    }

    public int hashCode() {
        int hash = this._sourceRef == null ? 1 : this._sourceRef.hashCode();
        hash ^= this._lineNr;
        hash += this._columnNr;
        hash ^= (int)this._totalChars;
        return hash += (int)this._totalBytes;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof JsonLocation)) {
            return false;
        }
        JsonLocation otherLoc = (JsonLocation)other;
        if (this._sourceRef == null ? otherLoc._sourceRef != null : !this._sourceRef.equals(otherLoc._sourceRef)) {
            return false;
        }
        return this._lineNr == otherLoc._lineNr && this._columnNr == otherLoc._columnNr && this._totalChars == otherLoc._totalChars && this.getByteOffset() == otherLoc.getByteOffset();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(80);
        sb2.append("[Source: ");
        this._appendSourceDesc(sb2);
        sb2.append("; line: ");
        sb2.append(this._lineNr);
        sb2.append(", column: ");
        sb2.append(this._columnNr);
        sb2.append(']');
        return sb2.toString();
    }

    protected StringBuilder _appendSourceDesc(StringBuilder sb2) {
        int len;
        Object srcRef = this._sourceRef;
        if (srcRef == null) {
            sb2.append("UNKNOWN");
            return sb2;
        }
        Class<?> srcType = srcRef instanceof Class ? (Class<?>)srcRef : srcRef.getClass();
        String tn2 = srcType.getName();
        if (tn2.startsWith("java.")) {
            tn2 = srcType.getSimpleName();
        } else if (srcRef instanceof byte[]) {
            tn2 = "byte[]";
        } else if (srcRef instanceof char[]) {
            tn2 = "char[]";
        }
        sb2.append('(').append(tn2).append(')');
        String charStr = " chars";
        if (srcRef instanceof CharSequence) {
            CharSequence cs2 = (CharSequence)srcRef;
            len = cs2.length();
            len -= this._append(sb2, cs2.subSequence(0, Math.min(len, 500)).toString());
        } else if (srcRef instanceof char[]) {
            char[] ch = (char[])srcRef;
            len = ch.length;
            len -= this._append(sb2, new String(ch, 0, Math.min(len, 500)));
        } else if (srcRef instanceof byte[]) {
            byte[] b2 = (byte[])srcRef;
            int maxLen = Math.min(b2.length, 500);
            this._append(sb2, new String(b2, 0, maxLen, Charset.forName("UTF-8")));
            len = b2.length - maxLen;
            charStr = " bytes";
        } else {
            len = 0;
        }
        if (len > 0) {
            sb2.append("[truncated ").append(len).append(charStr).append(']');
        }
        return sb2;
    }

    private int _append(StringBuilder sb2, String content) {
        sb2.append('\"').append(content).append('\"');
        return content.length();
    }
}

