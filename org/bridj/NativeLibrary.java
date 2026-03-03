/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bridj.BridJ;
import org.bridj.JNI;
import org.bridj.NativeEntities;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.ann.Symbol;
import org.bridj.demangling.Demangler;
import org.bridj.demangling.GCC4Demangler;
import org.bridj.demangling.VC9Demangler;
import org.bridj.util.AnnotationUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NativeLibrary {
    volatile long handle;
    volatile long symbols;
    String path;
    final File canonicalFile;
    NativeEntities nativeEntities = new NativeEntities();
    Map<Long, Demangler.Symbol> addrToName;
    Map<String, Demangler.Symbol> nameToSym;

    protected NativeLibrary(String path, long handle, long symbols) throws IOException {
        this.path = path;
        this.handle = handle;
        this.symbols = symbols;
        this.canonicalFile = path == null ? null : new File(path).getCanonicalFile();
        Platform.addNativeLibrary(this);
    }

    long getSymbolsHandle() {
        return this.symbols;
    }

    NativeEntities getNativeEntities() {
        return this.nativeEntities;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static String followGNULDScript(String path) {
        try {
            Reader r2 = new FileReader(path);
            try {
                String line;
                char c2;
                while ((c2 = (char)r2.read()) == ' ' || c2 == '\t' || c2 == '\n') {
                }
                if (c2 != '/') return path;
                if (r2.read() != 42) return path;
                BufferedReader br2 = new BufferedReader(r2);
                r2 = br2;
                StringBuilder b2 = new StringBuilder("/*");
                while ((line = br2.readLine()) != null) {
                    b2.append(line).append('\n');
                }
                String src = b2.toString();
                Pattern ldGroupPattern = Pattern.compile("GROUP\\s*\\(\\s*([^\\s)]+)[^)]*\\)");
                Matcher m2 = ldGroupPattern.matcher(src);
                if (m2.find()) {
                    String actualPath = m2.group(1);
                    if (BridJ.verbose) {
                        BridJ.info("Parsed LD script '" + path + "', found absolute reference to '" + actualPath + "'");
                    }
                    String string = actualPath;
                    return string;
                }
                BridJ.error("Failed to parse LD script '" + path + "' !");
                return path;
            }
            finally {
                r2.close();
            }
        }
        catch (Throwable th2) {
            BridJ.error("Unexpected error: " + th2, th2);
        }
        return path;
    }

    public static NativeLibrary load(String path) throws IOException {
        long handle = 0L;
        File file = new File(path);
        boolean exists = file.exists();
        if (file.isAbsolute() && !exists) {
            return null;
        }
        if (Platform.isUnix() && exists) {
            path = NativeLibrary.followGNULDScript(path);
        }
        if ((handle = JNI.loadLibrary(path)) == 0L) {
            return null;
        }
        long symbols = JNI.loadLibrarySymbols(path);
        return new NativeLibrary(path, handle, symbols);
    }

    long getHandle() {
        if (this.path != null && this.handle == 0L) {
            throw new RuntimeException("Library was released and cannot be used anymore");
        }
        return this.handle;
    }

    protected void finalize() throws Throwable {
        this.release();
    }

    public synchronized void release() {
        if (this.handle == 0L) {
            return;
        }
        if (BridJ.verbose) {
            BridJ.info("Releasing library '" + this.path + "'");
        }
        this.nativeEntities.release();
        JNI.freeLibrarySymbols(this.symbols);
        JNI.freeLibrary(this.handle);
        this.handle = 0L;
        if (this.canonicalFile != null && Platform.temporaryExtractedLibraryCanonicalFiles.remove(this.canonicalFile)) {
            if (this.canonicalFile.delete()) {
                if (BridJ.verbose) {
                    BridJ.info("Deleted temporary library file '" + this.canonicalFile + "'");
                }
            } else {
                BridJ.error("Failed to delete temporary library file '" + this.canonicalFile + "'");
            }
        }
    }

    public Pointer<?> getSymbolPointer(String name) {
        return Pointer.pointerToAddress(this.getSymbolAddress(name));
    }

    public long getSymbolAddress(String name) {
        Demangler.Symbol addr;
        if (this.nameToSym != null && (addr = this.nameToSym.get(name)) != null) {
            return addr.getAddress();
        }
        long address = JNI.findSymbolInLibrary(this.getHandle(), name);
        if (address == 0L) {
            address = JNI.findSymbolInLibrary(this.getHandle(), "_" + name);
        }
        return address;
    }

    public synchronized Demangler.Symbol getSymbol(AnnotatedElement member) throws FileNotFoundException {
        Symbol mg = AnnotationUtils.getAnnotation(Symbol.class, member, new Annotation[0]);
        String name = null;
        if (member instanceof Member) {
            name = ((Member)((Object)member)).getName();
        }
        ArrayList<String> names = new ArrayList<String>();
        if (mg != null) {
            names.addAll(Arrays.asList(mg.value()));
        }
        if (name != null) {
            names.add(name);
        }
        for (String n2 : names) {
            Demangler.Symbol handle = this.getSymbol(n2);
            if (handle == null) {
                handle = this.getSymbol("_" + n2);
            }
            if (handle == null) {
                handle = this.getSymbol(n2 + (Platform.useUnicodeVersionOfWindowsAPIs ? "W" : "A"));
            }
            if (handle == null) continue;
            return handle;
        }
        if (member instanceof Method) {
            Method method = (Method)member;
            for (Demangler.Symbol symbol : this.getSymbols()) {
                if (!symbol.matches(method)) continue;
                return symbol;
            }
        }
        return null;
    }

    public boolean isMSVC() {
        return Platform.isWindows();
    }

    public Demangler.Symbol getFirstMatchingSymbol(SymbolAccepter accepter) {
        for (Demangler.Symbol symbol : this.getSymbols()) {
            if (!accepter.accept(symbol)) continue;
            return symbol;
        }
        return null;
    }

    public Collection<Demangler.Symbol> getSymbols() {
        block2: {
            try {
                this.scanSymbols();
            }
            catch (Exception ex2) {
                if ($assertionsDisabled || BridJ.error("Failed to scan symbols of library '" + this.path + "'", ex2)) break block2;
                throw new AssertionError();
            }
        }
        return this.nameToSym == null ? Collections.EMPTY_LIST : Collections.unmodifiableCollection(this.nameToSym.values());
    }

    public String getSymbolName(long address) {
        if (this.addrToName == null && this.getSymbolsHandle() != 0L) {
            return JNI.findSymbolName(this.getHandle(), this.getSymbolsHandle(), address);
        }
        Demangler.Symbol symbol = this.getSymbol(address);
        return symbol == null ? null : symbol.getSymbol();
    }

    public Demangler.Symbol getSymbol(long address) {
        try {
            this.scanSymbols();
            Demangler.Symbol symbol = this.addrToName.get(address);
            return symbol;
        }
        catch (Exception ex2) {
            throw new RuntimeException("Failed to get name of address " + address, ex2);
        }
    }

    public Demangler.Symbol getSymbol(String name) {
        try {
            long addr;
            if (this.nameToSym == null && (addr = JNI.findSymbolInLibrary(this.getHandle(), name)) != 0L) {
                Demangler.Symbol symbol = new Demangler.Symbol(name, this);
                symbol.setAddress(addr);
                return symbol;
            }
            this.scanSymbols();
            if (this.nameToSym == null) {
                return null;
            }
            Demangler.Symbol symbol = this.nameToSym.get(name);
            if (this.addrToName == null && symbol == null && (addr = JNI.findSymbolInLibrary(this.getHandle(), name)) != 0L) {
                symbol = new Demangler.Symbol(name, this);
                symbol.setAddress(addr);
                this.nameToSym.put(name, symbol);
            }
            return symbol;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return null;
        }
    }

    void scanSymbols() throws Exception {
        if (this.addrToName != null) {
            return;
        }
        this.nameToSym = new HashMap<String, Demangler.Symbol>();
        String[] symbs = null;
        if (symbs == null) {
            symbs = JNI.getLibrarySymbols(this.getHandle(), this.getSymbolsHandle());
        }
        if (symbs == null) {
            return;
        }
        this.addrToName = new HashMap<Long, Demangler.Symbol>();
        boolean is32 = !Platform.is64Bits();
        for (String name : symbs) {
            if (name == null) continue;
            long addr = JNI.findSymbolInLibrary(this.getHandle(), name);
            if (addr == 0L && name.startsWith("_")) {
                String n2 = name.substring(1);
                addr = JNI.findSymbolInLibrary(this.getHandle(), n2);
                if (addr == 0L) {
                    n2 = "_" + name;
                    addr = JNI.findSymbolInLibrary(this.getHandle(), n2);
                }
                if (addr != 0L) {
                    name = n2;
                }
            }
            if (addr == 0L) {
                if (!BridJ.verbose) continue;
                BridJ.warning("Symbol '" + name + "' not found.");
                continue;
            }
            Demangler.Symbol sym = new Demangler.Symbol(name, this);
            sym.setAddress(addr);
            this.addrToName.put(addr, sym);
            this.nameToSym.put(name, sym);
        }
        if (BridJ.debug) {
            BridJ.info("Found " + this.nameToSym.size() + " symbols in '" + this.path + "' :");
            for (Demangler.Symbol sym : this.nameToSym.values()) {
                BridJ.info("DEBUG(BridJ): library=\"" + this.path + "\", symbol=\"" + sym.getSymbol() + "\", address=" + Long.toHexString(sym.getAddress()) + ", demangled=\"" + sym.getParsedRef() + "\"");
            }
        }
    }

    public Demangler.MemberRef parseSymbol(String symbol) throws Demangler.DemanglingException {
        if ("__cxa_pure_virtual".equals(symbol)) {
            return null;
        }
        Demangler demangler = Platform.isWindows() ? new VC9Demangler(this, symbol) : new GCC4Demangler(this, symbol);
        return demangler.parseSymbol();
    }

    public static interface SymbolAccepter {
        public boolean accept(Demangler.Symbol var1);
    }
}

