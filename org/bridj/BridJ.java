/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bridj.BridJRuntime;
import org.bridj.CLong;
import org.bridj.CRuntime;
import org.bridj.CallbackNativeImplementer;
import org.bridj.HeadersReconstructor;
import org.bridj.IntValuedEnum;
import org.bridj.NativeEntities;
import org.bridj.NativeLibrary;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.PlatformSupport;
import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.StructIO;
import org.bridj.TimeT;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.demangling.Demangler;
import org.bridj.util.ASMUtils;
import org.bridj.util.AnnotationUtils;
import org.bridj.util.StringUtils;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BridJ {
    static final Map<AnnotatedElement, NativeLibrary> librariesByClass = new HashMap<AnnotatedElement, NativeLibrary>();
    static final Map<String, File> librariesFilesByName = new HashMap<String, File>();
    static final Map<File, NativeLibrary> librariesByFile = new HashMap<File, NativeLibrary>();
    private static NativeEntities orphanEntities = new NativeEntities();
    static final Map<Class<?>, BridJRuntime> classRuntimes = new HashMap();
    static final Map<Long, NativeObject> strongNativeObjects = new HashMap<Long, NativeObject>();
    static final Map<Long, NativeObject> weakNativeObjects = new WeakHashMap<Long, NativeObject>();
    static ThreadLocal<Stack<CastingType>> currentlyCastingNativeObject = new ThreadLocal<Stack<CastingType>>(){

        @Override
        protected Stack<CastingType> initialValue() {
            Stack<CastingType> s2 = new Stack<CastingType>();
            s2.push(CastingType.None);
            return s2;
        }
    };
    private static WeakHashMap<Long, NativeObject> knownNativeObjects = new WeakHashMap();
    private static Map<Class<? extends BridJRuntime>, BridJRuntime> runtimes = new HashMap<Class<? extends BridJRuntime>, BridJRuntime>();
    static Map<Type, BridJRuntime.TypeInfo<?>> typeInfos = new HashMap();
    public static final boolean debug;
    public static final boolean debugNeverFree;
    public static final boolean debugPointers;
    public static final boolean veryVerbose;
    public static final boolean verbose;
    public static final boolean quiet;
    public static final boolean logCalls;
    public static final boolean protectedMode;
    public static final boolean enableDestructors;
    public static final boolean alignDoubles;
    static volatile int minLogLevelValue;
    static Logger logger;
    static Map<String, NativeLibrary> libHandles;
    static volatile List<String> paths;
    static List<String> additionalPaths;
    static Map<String, String> libraryActualNames;
    static Map<String, List<String>> libraryAliases;
    private static final Pattern numPat;
    static Map<String, File> nativeLibraryFiles;
    static Boolean directModeEnabled;

    public static long sizeOf(Type type) {
        Class c2 = Utils.getClass(type);
        if (c2.isPrimitive()) {
            return StructIO.primTypeLength(c2);
        }
        if (Pointer.class.isAssignableFrom(c2)) {
            return Pointer.SIZE;
        }
        if (c2 == CLong.class) {
            return CLong.SIZE;
        }
        if (c2 == TimeT.class) {
            return TimeT.SIZE;
        }
        if (c2 == SizeT.class) {
            return SizeT.SIZE;
        }
        if (c2 == Integer.class || c2 == Float.class) {
            return 4L;
        }
        if (c2 == Character.class || c2 == Short.class) {
            return 2L;
        }
        if (c2 == Long.class || c2 == Double.class) {
            return 8L;
        }
        if (c2 == Boolean.class || c2 == Byte.class) {
            return 1L;
        }
        if (NativeObject.class.isAssignableFrom(c2)) {
            return BridJ.getRuntime(c2).getTypeInfo(type).sizeOf();
        }
        if (IntValuedEnum.class.isAssignableFrom(c2)) {
            return 4L;
        }
        throw new RuntimeException("Unable to compute size of type " + Utils.toString(type));
    }

    static synchronized void registerNativeObject(NativeObject ob2) {
        weakNativeObjects.put(Pointer.getAddress(ob2, null), ob2);
    }

    static synchronized NativeObject getNativeObject(long peer) {
        NativeObject ob2 = weakNativeObjects.get(peer);
        if (ob2 == null) {
            ob2 = strongNativeObjects.get(peer);
        }
        return ob2;
    }

    static synchronized void unregisterNativeObject(NativeObject ob2) {
        long peer = Pointer.getAddress(ob2, null);
        weakNativeObjects.remove(peer);
        strongNativeObjects.remove(peer);
    }

    public static synchronized <T extends NativeObject> T protectFromGC(T ob2) {
        long peer = Pointer.getAddress(ob2, null);
        weakNativeObjects.remove(peer);
        strongNativeObjects.put(peer, ob2);
        return ob2;
    }

    public static synchronized <T extends NativeObject> T unprotectFromGC(T ob2) {
        long peer = Pointer.getAddress(ob2, null);
        if (strongNativeObjects.remove(peer) != null) {
            weakNativeObjects.put(peer, ob2);
        }
        return ob2;
    }

    public static void delete(NativeObject nativeObject) {
        BridJ.unregisterNativeObject(nativeObject);
        Pointer.pointerTo(nativeObject, null).release();
    }

    public static synchronized void register() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length < 2) {
            throw new RuntimeException("No useful stack trace : cannot register with register(), please use register(Class) instead.");
        }
        String name = stackTrace[1].getClassName();
        try {
            Class<?> type = Class.forName(name);
            BridJ.register(type);
        }
        catch (Exception ex2) {
            throw new RuntimeException("Failed to register class " + name, ex2);
        }
    }

    public static <T> Class<? extends T> subclassWithSynchronizedNativeMethods(Class<T> original) throws IOException {
        CallbackNativeImplementer classDefiner = BridJ.getRuntimeByRuntimeClass(CRuntime.class).getCallbackNativeImplementer();
        return ASMUtils.createSubclassWithSynchronizedNativeMethodsAndNoStaticFields(original, classDefiner);
    }

    @Deprecated
    public static boolean isCastingNativeObjectInCurrentThread() {
        return currentlyCastingNativeObject.get().peek() != CastingType.None;
    }

    @Deprecated
    public static boolean isCastingNativeObjectReturnTypeInCurrentThread() {
        return currentlyCastingNativeObject.get().peek() == CastingType.CastingNativeObjectReturnType;
    }

    public static synchronized <O extends NativeObject> void setJavaObjectFromNativePeer(long peer, O object) {
        if (object == null) {
            knownNativeObjects.remove(peer);
        } else {
            knownNativeObjects.put(peer, object);
        }
    }

    public static synchronized Object getJavaObjectFromNativePeer(long peer) {
        return knownNativeObjects.get(peer);
    }

    private static <O extends NativeObject> O createNativeObjectFromPointer(Pointer<? super O> pointer, Type type, CastingType castingType) {
        Stack<CastingType> s2 = currentlyCastingNativeObject.get();
        s2.push(castingType);
        try {
            BridJRuntime.TypeInfo typeInfo = BridJ.getTypeInfo(type);
            Object instance = typeInfo.cast(pointer);
            if (debug) {
                BridJ.info("Created native object from pointer " + pointer);
            }
            Object t2 = instance;
            return (O)t2;
        }
        catch (Exception ex2) {
            throw new RuntimeException("Failed to cast pointer to native object of type " + Utils.getClass(type).getName(), ex2);
        }
        finally {
            s2.pop();
        }
    }

    public static <O extends NativeObject> void copyNativeObjectToAddress(O value, Type type, Pointer<O> ptr) {
        BridJ.getTypeInfo(type).copyNativeObjectToAddress(value, ptr);
    }

    public static <O extends NativeObject> O createNativeObjectFromPointer(Pointer<? super O> pointer, Type type) {
        return BridJ.createNativeObjectFromPointer(pointer, type, CastingType.CastingNativeObject);
    }

    public static <O extends NativeObject> O createNativeObjectFromReturnValuePointer(Pointer<? super O> pointer, Type type) {
        return BridJ.createNativeObjectFromPointer(pointer, type, CastingType.CastingNativeObjectReturnType);
    }

    public static synchronized <R extends BridJRuntime> R getRuntimeByRuntimeClass(Class<R> runtimeClass) {
        BridJRuntime r2 = runtimes.get(runtimeClass);
        if (r2 == null) {
            try {
                r2 = (BridJRuntime)runtimeClass.newInstance();
                runtimes.put(runtimeClass, r2);
            }
            catch (Exception e2) {
                throw new RuntimeException("Failed to instantiate runtime " + runtimeClass.getName(), e2);
            }
        }
        return (R)r2;
    }

    public static Class<? extends BridJRuntime> getRuntimeClass(Class<?> type) {
        Runtime runtimeAnn = AnnotationUtils.getInheritableAnnotation(Runtime.class, type, new Annotation[0]);
        Class runtimeClass = null;
        runtimeClass = runtimeAnn != null ? runtimeAnn.value() : CRuntime.class;
        return runtimeClass;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BridJRuntime getRuntime(Class<?> type) {
        Map<Class<?>, BridJRuntime> map = classRuntimes;
        synchronized (map) {
            BridJRuntime runtime = classRuntimes.get(type);
            if (runtime == null) {
                Class<? extends BridJRuntime> runtimeClass = BridJ.getRuntimeClass(type);
                runtime = BridJ.getRuntimeByRuntimeClass(runtimeClass);
                classRuntimes.put(type, runtime);
                if (veryVerbose) {
                    BridJ.info("Runtime for " + type.getName() + " : " + runtimeClass.getName());
                }
            }
            return runtime;
        }
    }

    public static BridJRuntime register(Class<?> type) {
        BridJRuntime runtime = BridJ.getRuntime(type);
        if (runtime == null) {
            for (Class<?> child : type.getClasses()) {
                BridJ.register(child);
            }
        } else {
            runtime.register(type);
        }
        return runtime;
    }

    public static void unregister(Class<?> type) {
        BridJRuntime runtime = BridJ.getRuntime(type);
        if (runtime == null) {
            for (Class<?> child : type.getClasses()) {
                BridJ.register(child);
            }
        } else {
            runtime.unregister(type);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static <T extends NativeObject> BridJRuntime.TypeInfo<T> getTypeInfo(Type t2) {
        Map<Type, BridJRuntime.TypeInfo<?>> map = typeInfos;
        synchronized (map) {
            BridJRuntime.TypeInfo<Object> info = typeInfos.get(t2);
            if (info == null) {
                info = BridJ.getRuntime(Utils.getClass(t2)).getTypeInfo(t2);
                typeInfos.put(t2, info);
            }
            return info;
        }
    }

    static void checkOptions() {
        HashSet<String> props = new HashSet<String>();
        HashSet<String> envs = new HashSet<String>();
        for (Switch s2 : Switch.values()) {
            props.add(s2.propertyName);
            envs.add(s2.envName);
        }
        boolean hasUnknown = false;
        for (String n2 : System.getenv().keySet()) {
            if (!n2.startsWith("BRIDJ_") || envs.contains(n2) || n2.endsWith("_LIBRARY")) continue;
            BridJ.error("Unknown environment variable : " + n2 + "=\"" + System.getenv(n2) + "\"");
            hasUnknown = true;
        }
        Enumeration<?> e2 = System.getProperties().propertyNames();
        while (e2.hasMoreElements()) {
            String n3 = (String)e2.nextElement();
            if (!n3.startsWith("bridj.") || props.contains(n3) || n3.endsWith(".library")) continue;
            BridJ.error("Unknown property : " + n3 + "=\"" + System.getProperty(n3) + "\"");
            hasUnknown = true;
        }
        if (hasUnknown) {
            StringBuilder b2 = new StringBuilder();
            b2.append("Available options (ENVIRONMENT_VAR_NAME / javaPropertyName) :\n");
            for (Switch s3 : Switch.values()) {
                b2.append(s3.getFullDescription() + "\n");
            }
            BridJ.error(b2.toString());
        }
    }

    public static void setMinLogLevel(Level level) {
        minLogLevelValue = level.intValue();
    }

    static boolean shouldLog(Level level) {
        return !quiet && (verbose || level.intValue() >= minLogLevelValue);
    }

    static synchronized Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(BridJ.class.getName());
        }
        return logger;
    }

    public static boolean info(String message) {
        return BridJ.info(message, null);
    }

    public static boolean info(String message, Throwable ex2) {
        return BridJ.log(Level.INFO, message, ex2);
    }

    public static boolean debug(String message) {
        if (!debug) {
            return true;
        }
        return BridJ.info(message, null);
    }

    public static boolean error(String message) {
        return BridJ.error(message, null);
    }

    public static boolean error(String message, Throwable ex2) {
        return BridJ.log(Level.INFO, message, ex2);
    }

    public static boolean warning(String message) {
        return BridJ.warning(message, null);
    }

    public static boolean warning(String message, Throwable ex2) {
        return BridJ.log(Level.INFO, message, ex2);
    }

    private static boolean log(Level level, String message, Throwable ex2) {
        if (!BridJ.shouldLog(level)) {
            return true;
        }
        BridJ.getLogger().log(level, message, ex2);
        return true;
    }

    static void logCall(Method m2) {
        BridJ.info("Calling method " + m2);
    }

    public static synchronized NativeEntities getNativeEntities(AnnotatedElement type) throws IOException {
        NativeLibrary lib = BridJ.getNativeLibrary(type);
        if (lib != null) {
            return lib.getNativeEntities();
        }
        return BridJ.getOrphanEntities();
    }

    public static synchronized NativeLibrary getNativeLibrary(AnnotatedElement type) throws IOException {
        Library libAnn;
        NativeLibrary lib = librariesByClass.get(type);
        if (lib == null && (libAnn = BridJ.getLibrary(type)) != null) {
            for (String dependency : libAnn.dependencies()) {
                NativeLibrary depLib;
                if (verbose) {
                    BridJ.info("Trying to load dependency '" + dependency + "' of '" + libAnn.value() + "'");
                }
                if ((depLib = BridJ.getNativeLibrary(dependency)) != null) continue;
                throw new RuntimeException("Failed to load dependency '" + dependency + "' of library '" + libAnn.value() + "'");
            }
            lib = BridJ.getNativeLibrary(libAnn.value());
            if (lib != null) {
                librariesByClass.put(type, lib);
            }
        }
        return lib;
    }

    public static synchronized void releaseAll() {
        strongNativeObjects.clear();
        weakNativeObjects.clear();
        System.gc();
        for (NativeLibrary lib : librariesByFile.values()) {
            lib.release();
        }
        librariesByFile.clear();
        librariesByClass.clear();
        BridJ.getOrphanEntities().release();
        System.gc();
    }

    public static synchronized void releaseLibrary(String name) {
        File file = librariesFilesByName.remove(name);
        if (file != null) {
            BridJ.releaseLibrary(file);
        }
    }

    public static synchronized void releaseLibrary(File library) {
        NativeLibrary lib = librariesByFile.remove(library);
        if (lib != null) {
            lib.release();
        }
    }

    public static synchronized void addLibraryPath(String path) {
        additionalPaths.add(path);
        paths = null;
    }

    private static void addPathsFromEnv(List<String> out, String name) {
        String env = System.getenv(name);
        if (verbose) {
            BridJ.info("Environment var " + name + " = " + env);
        }
        BridJ.addPaths(out, env);
    }

    private static void addPathsFromProperty(List<String> out, String name) {
        String env = System.getProperty(name);
        if (verbose) {
            BridJ.info("Property " + name + " = " + env);
        }
        BridJ.addPaths(out, env);
    }

    private static void addPaths(List<String> out, String env) {
        if (env == null) {
            return;
        }
        String[] paths = env.split(File.pathSeparator);
        if (paths.length == 0) {
            return;
        }
        if (paths.length == 1) {
            out.add(paths[0]);
            return;
        }
        out.addAll(Arrays.asList(paths));
    }

    static synchronized List<String> getNativeLibraryPaths() {
        if (paths == null) {
            paths = new ArrayList<String>();
            paths.addAll(additionalPaths);
            paths.add(null);
            paths.add(".");
            BridJ.addPathsFromEnv(paths, "LD_LIBRARY_PATH");
            BridJ.addPathsFromEnv(paths, "DYLD_LIBRARY_PATH");
            BridJ.addPathsFromEnv(paths, "PATH");
            BridJ.addPathsFromProperty(paths, "java.library.path");
            BridJ.addPathsFromProperty(paths, "gnu.classpath.boot.library.path");
            File javaHome = new File(System.getProperty("java.home"));
            paths.add(new File(javaHome, "bin").toString());
            if (Platform.isMacOSX()) {
                paths.add(new File(javaHome, "../Libraries").toString());
            }
            if (Platform.isUnix()) {
                String bits;
                String string = bits = Platform.is64Bits() ? "64" : "32";
                if (Platform.isLinux()) {
                    String abi2 = Platform.isArm() ? "gnueabi" : "gnu";
                    String multiArch = Platform.getMachine() + "-linux-" + abi2;
                    paths.add("/lib/" + multiArch);
                    paths.add("/usr/lib/" + multiArch);
                    paths.add("/usr/lib" + bits);
                    paths.add("/lib" + bits);
                } else if (Platform.isSolaris()) {
                    paths.add("/usr/lib/" + bits);
                    paths.add("/lib/" + bits);
                }
                paths.add("/usr/lib");
                paths.add("/lib");
                paths.add("/usr/local/lib");
            }
        }
        return paths;
    }

    public static synchronized void setNativeLibraryActualName(String name, String actualName) {
        libraryActualNames.put(name, actualName);
    }

    public static synchronized void addNativeLibraryAlias(String name, String alias) {
        List<String> list = libraryAliases.get(name);
        if (list == null) {
            list = new ArrayList<String>();
            libraryAliases.put(name, list);
        }
        if (!list.contains(alias)) {
            list.add(alias);
        }
    }

    static String[] getPossibleFileNames(String name) {
        if (Platform.isWindows()) {
            return new String[]{name + ".dll", name + ".drv"};
        }
        String jniName = "lib" + name + ".jnilib";
        if (Platform.isMacOSX()) {
            return new String[]{"lib" + name + ".dylib", jniName};
        }
        return new String[]{"lib" + name + ".so", name + ".so", jniName};
    }

    static double parseVersion(String s2) {
        Matcher m2 = numPat.matcher(s2);
        double res = 0.0;
        double f2 = 1.0;
        while (m2.find()) {
            res += (double)Integer.parseInt(m2.group(1)) * f2;
            f2 /= 1000.0;
        }
        return res;
    }

    static File findFileWithGreaterVersion(File dir, String[] files, String baseFileName) {
        Pattern versionPattern = Pattern.compile(Pattern.quote(baseFileName) + "((:?\\.\\d+)+)");
        double maxVersion = 0.0;
        String maxVersionFile = null;
        for (String fileName : files) {
            Matcher m2 = versionPattern.matcher(fileName);
            if (!m2.matches()) continue;
            double version = BridJ.parseVersion(m2.group(1));
            if (maxVersionFile != null && !(version > maxVersion)) continue;
            maxVersionFile = fileName;
            maxVersion = version;
        }
        if (maxVersionFile == null) {
            return null;
        }
        return new File(dir, maxVersionFile);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static File getNativeLibraryFile(String libraryName) {
        if (libraryName == null) {
            return null;
        }
        try {
            Map<String, File> map = nativeLibraryFiles;
            synchronized (map) {
                File nativeLibraryFile = nativeLibraryFiles.get(libraryName);
                if (nativeLibraryFile == null) {
                    nativeLibraryFile = BridJ.findNativeLibraryFile(libraryName);
                    nativeLibraryFiles.put(libraryName, nativeLibraryFile);
                }
                return nativeLibraryFile;
            }
        }
        catch (Throwable th2) {
            BridJ.warning("Library not found : " + libraryName);
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void setNativeLibraryFile(String libraryName, File nativeLibraryFile) {
        if (libraryName == null) {
            return;
        }
        Map<String, File> map = nativeLibraryFiles;
        synchronized (map) {
            nativeLibraryFiles.put(libraryName, nativeLibraryFile);
        }
    }

    static File findNativeLibraryFile(String libraryName) {
        Iterator i$;
        String actualName = libraryActualNames.get(libraryName);
        List<String> aliases = libraryAliases.get(libraryName);
        ArrayList<String> possibleNames = new ArrayList<String>();
        if (aliases != null) {
            possibleNames.addAll(aliases);
        }
        possibleNames.add(actualName == null ? libraryName : actualName);
        if (Platform.isWindows()) {
            if (libraryName.equals("c")) {
                possibleNames.add("msvcrt");
            } else if (libraryName.equals("m")) {
                possibleNames.add("msvcrt");
            }
        }
        List<String> paths = BridJ.getNativeLibraryPaths();
        if (debug) {
            BridJ.info("Looking for library '" + libraryName + "' " + (actualName != null ? "('" + actualName + "') " : "") + "in paths " + paths, null);
        }
        if ((i$ = possibleNames.iterator()).hasNext()) {
            File f2;
            String name = (String)i$.next();
            String env = System.getenv("BRIDJ_" + name.toUpperCase() + "_LIBRARY");
            if (env == null) {
                env = System.getProperty("bridj." + name + ".library");
            }
            if (env != null && (f2 = new File(env)).exists()) {
                try {
                    return f2.getCanonicalFile();
                }
                catch (IOException ex2) {
                    BridJ.error(null, ex2);
                }
            }
            for (String path : paths) {
                File pathFile = path == null ? null : new File(path);
                File f3 = new File(name);
                if (!f3.isFile() && pathFile != null) {
                    String[] files;
                    String possibleFileName;
                    String[] possibleFileNames;
                    String[] arr$ = possibleFileNames = BridJ.getPossibleFileNames(name);
                    int len$ = arr$.length;
                    for (int i$2 = 0; i$2 < len$ && !(f3 = new File(pathFile, possibleFileName = arr$[i$2])).isFile(); ++i$2) {
                    }
                    if (!f3.isFile() && Platform.isLinux() && (files = pathFile.list()) != null) {
                        for (String possibleFileName2 : possibleFileNames) {
                            File ff2 = BridJ.findFileWithGreaterVersion(pathFile, files, possibleFileName2);
                            if (ff2 == null || !(f3 = ff2).isFile()) continue;
                            if (!verbose) break;
                            BridJ.info("File '" + possibleFileName2 + "' was not found, used versioned file '" + f3 + "' instead.");
                            break;
                        }
                    }
                }
                if (!f3.isFile()) continue;
                try {
                    return f3.getCanonicalFile();
                }
                catch (IOException ex3) {
                    BridJ.error(null, ex3);
                }
            }
            if (Platform.isMacOSX()) {
                for (String s2 : new String[]{"/System/Library/Frameworks", "/System/Library/Frameworks/ApplicationServices.framework/Frameworks", new File(System.getProperty("user.home"), "Library/Frameworks").toString()}) {
                    try {
                        File f4 = new File(new File(s2, name + ".framework"), name);
                        if (!f4.isFile()) continue;
                        return f4.getCanonicalFile();
                    }
                    catch (IOException ex4) {
                        ex4.printStackTrace();
                        return null;
                    }
                }
            }
            try {
                f2 = Platform.isAndroid() ? new File("lib" + name + ".so") : Platform.extractEmbeddedLibraryResource(name);
                if (f2 == null || !f2.isFile()) {
                    throw new FileNotFoundException(StringUtils.implode(possibleNames, (Object)", "));
                }
                return f2;
            }
            catch (IOException ex5) {
                throw new RuntimeException(ex5);
            }
        }
        return null;
    }

    public static boolean isDirectModeEnabled() {
        if (directModeEnabled == null) {
            directModeEnabled = Switch.Direct.enabled && !logCalls && !protectedMode;
            if (veryVerbose) {
                BridJ.info("directModeEnabled = " + directModeEnabled);
            }
        }
        return directModeEnabled;
    }

    static void setDirectModeEnabled(boolean v2) {
        directModeEnabled = v2;
    }

    public static synchronized NativeLibrary getNativeLibrary(String name) throws IOException {
        if (name == null) {
            return null;
        }
        NativeLibrary l2 = libHandles.get(name);
        if (l2 != null) {
            return l2;
        }
        File f2 = BridJ.getNativeLibraryFile(name);
        return BridJ.getNativeLibrary(name, f2);
    }

    public static NativeLibrary getNativeLibrary(String name, File f2) throws IOException {
        NativeLibrary ll2 = NativeLibrary.load(f2 == null ? name : f2.toString());
        if (ll2 == null && (ll2 = PlatformSupport.getInstance().loadNativeLibrary(name)) == null && "c".equals(name)) {
            ll2 = new NativeLibrary(null, 0L, 0L);
        }
        if (ll2 == null) {
            if (f2 != null && f2.exists()) {
                throw new RuntimeException("Library '" + name + "' was not loaded successfully from file '" + f2 + "'");
            }
            throw new FileNotFoundException("Library '" + name + "' was not found in path '" + BridJ.getNativeLibraryPaths() + "'" + (f2 != null && f2.exists() ? " (failed to load " + f2 + ")" : ""));
        }
        if (verbose) {
            BridJ.info("Loaded library '" + name + "' from '" + f2 + "'", null);
        }
        libHandles.put(name, ll2);
        return ll2;
    }

    public static String getNativeLibraryName(AnnotatedElement m2) {
        Library lib = BridJ.getLibrary(m2);
        return lib == null ? null : lib.value();
    }

    static Library getLibrary(AnnotatedElement m2) {
        return AnnotationUtils.getInheritableAnnotation(Library.class, m2, new Annotation[0]);
    }

    public static Demangler.Symbol getSymbolByAddress(long peer) {
        for (NativeLibrary lib : libHandles.values()) {
            Demangler.Symbol symbol = lib.getSymbol(peer);
            if (symbol == null) continue;
            return symbol;
        }
        return null;
    }

    public static void setOrphanEntities(NativeEntities orphanEntities) {
        BridJ.orphanEntities = orphanEntities;
    }

    public static NativeEntities getOrphanEntities() {
        return orphanEntities;
    }

    static void initialize(NativeObject instance) {
        BridJRuntime.TypeInfo<NativeObject> typeInfo;
        instance.typeInfo = typeInfo = BridJ.getTypeInfo(instance.getClass());
        typeInfo.initialize(instance);
    }

    static void initialize(NativeObject instance, Pointer peer) {
        BridJRuntime.TypeInfo<NativeObject> typeInfo;
        instance.typeInfo = typeInfo = BridJ.getTypeInfo(instance.getClass());
        typeInfo.initialize(instance, peer);
    }

    static void initialize(NativeObject instance, int constructorId, Object[] args) {
        BridJRuntime.TypeInfo<NativeObject> typeInfo;
        instance.typeInfo = typeInfo = BridJ.getTypeInfo(instance.getClass());
        typeInfo.initialize(instance, constructorId, args);
    }

    static <T extends NativeObject> T clone(T instance) throws CloneNotSupportedException {
        return instance.typeInfo.clone(instance);
    }

    public static <T extends NativeObject> T readFromNative(T instance) {
        instance.typeInfo.readFromNative(instance);
        return instance;
    }

    public static <T extends NativeObject> T writeToNative(T instance) {
        instance.typeInfo.writeToNative(instance);
        return instance;
    }

    public static String describe(NativeObject instance) {
        return instance.typeInfo.describe(instance);
    }

    public static String describe(Type nativeObjectType) {
        BridJRuntime.TypeInfo typeInfo = BridJ.getTypeInfo(nativeObjectType);
        return typeInfo == null ? Utils.toString(nativeObjectType) : typeInfo.describe();
    }

    public static void main(String[] args) {
        ArrayList<NativeLibrary> libraries = new ArrayList<NativeLibrary>();
        try {
            File outputDir = new File(".");
            int nArgs = args.length;
            for (int iArg = 0; iArg < nArgs; ++iArg) {
                String arg2 = args[iArg];
                if (arg2.equals("-d")) {
                    outputDir = new File(args[++iArg]);
                    continue;
                }
                try {
                    NativeLibrary lib = BridJ.getNativeLibrary(arg2);
                    libraries.add(lib);
                    PrintWriter sout = new PrintWriter(new File(outputDir, new File(arg2).getName() + ".symbols.txt"));
                    for (Demangler.Symbol sym : lib.getSymbols()) {
                        sout.print(sym.getSymbol());
                        sout.print(" // ");
                        try {
                            Demangler.MemberRef mr2 = sym.getParsedRef();
                            sout.print(" // " + mr2);
                        }
                        catch (Throwable th2) {
                            sout.print("?");
                        }
                        sout.println();
                    }
                    sout.close();
                    continue;
                }
                catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
            PrintWriter out = new PrintWriter(new File(outputDir, "out.h"));
            HeadersReconstructor.reconstructHeaders(libraries, out);
            out.close();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            System.exit(1);
        }
    }

    static {
        BridJ.checkOptions();
        debug = Switch.Debug.enabled;
        debugNeverFree = Switch.DebugNeverFree.enabled;
        debugPointers = Switch.DebugPointers.enabled;
        veryVerbose = Switch.VeryVerbose.enabled;
        verbose = debug || veryVerbose || Switch.Verbose.enabled;
        quiet = Switch.Quiet.enabled;
        logCalls = Switch.LogCalls.enabled;
        protectedMode = Switch.Protected.enabled;
        enableDestructors = Switch.Destructors.enabled;
        alignDoubles = Switch.AlignDouble.enabled;
        minLogLevelValue = (verbose ? Level.WARNING : Level.INFO).intValue();
        libHandles = new HashMap<String, NativeLibrary>();
        additionalPaths = new ArrayList<String>();
        libraryActualNames = new HashMap<String, String>();
        libraryAliases = new HashMap<String, List<String>>();
        numPat = Pattern.compile("\\b(\\d+)\\b");
        nativeLibraryFiles = new HashMap<String, File>();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum Switch {
        Debug("bridj.debug", "BRIDJ_DEBUG", false, "Debug mode (implies high verbosity)"),
        DebugNeverFree("bridj.debug.neverFree", "BRIDJ_DEBUG_NEVER_FREE", false, "Never free allocated pointers (deprecated)"),
        DebugPointers("bridj.debug.pointers", "BRIDJ_DEBUG_POINTERS", false, "Trace pointer allocations & deallocations (to debug memory issues)"),
        VeryVerbose("bridj.veryVerbose", "BRIDJ_VERY_VERBOSE", false, "Highly verbose mode"),
        Verbose("bridj.verbose", "BRIDJ_VERBOSE", false, "Verbose mode"),
        Quiet("bridj.quiet", "BRIDJ_QUIET", false, "Quiet mode"),
        AlignDouble("bridj.alignDouble", "BRIDJ_ALIGN_DOUBLE", false, "Align doubles on 8 bytes boundaries even on Linux 32 bits (see -malign-double GCC option)."),
        LogCalls("bridj.logCalls", "BRIDJ_LOG_CALLS", false, "Log each native call performed (or call from native to Java callback)"),
        Protected("bridj.protected", "BRIDJ_PROTECTED", false, "Protect all native calls (including memory accesses) against native crashes."),
        Destructors("bridj.destructors", "BRIDJ_DESTRUCTORS", true, "Enable destructors (in languages that support them, such as C++)"),
        Direct("bridj.direct", "BRIDJ_DIRECT", true, "Direct mode (uses optimized assembler glue when possible to speed up calls)"),
        StructsByValue("bridj.structsByValue", "BRIDJ_STRUCT_BY_VALUE", false, "Enable experimental support for structs-by-value arguments and return values for C/C++ functions and methods.");

        public final boolean enabled;
        public final boolean enabledByDefault;
        public final String propertyName;
        public final String envName;
        public final String description;

        private Switch(String propertyName, String envName, boolean enabledByDefault, String description) {
            this.enabled = enabledByDefault ? !"false".equals(System.getProperty(propertyName)) && !"0".equals(System.getenv(envName)) : "true".equals(System.getProperty(propertyName)) || "1".equals(System.getenv(envName));
            this.enabledByDefault = enabledByDefault;
            this.propertyName = propertyName;
            this.envName = envName;
            this.description = description;
        }

        public String getFullDescription() {
            return this.envName + " / " + this.propertyName + " (" + (this.enabledByDefault ? "enabled" : "disabled") + " by default) :\n\t" + this.description.replaceAll("\n", "\n\t");
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum CastingType {
        None,
        CastingNativeObject,
        CastingNativeObjectReturnType;

    }
}

