/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.bridj.BridJ;
import org.bridj.NativeLibrary;
import org.bridj.util.ProcessUtils;
import org.bridj.util.StringUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Platform {
    static final String osName = System.getProperty("os.name", "");
    private static boolean inited;
    static final String BridJLibraryName = "bridj";
    public static final int POINTER_SIZE;
    public static final int WCHAR_T_SIZE;
    public static final int SIZE_T_SIZE;
    public static final int TIME_T_SIZE;
    public static final int CLONG_SIZE;
    static final ClassLoader systemClassLoader;
    static final List<String> embeddedLibraryResourceRoots;
    static Set<File> temporaryExtractedLibraryCanonicalFiles;
    private static final String arch;
    private static boolean is64Bits;
    private static File extractedLibrariesTempDir;
    private static List<NativeLibrary> nativeLibraries;
    public static boolean useUnicodeVersionOfWindowsAPIs;
    static final long DELETE_OLD_BINARIES_AFTER_MILLIS = 86400000L;
    static final int maxTempFileAttempts = 20;

    public static ClassLoader getClassLoader() {
        return Platform.getClassLoader(BridJ.class);
    }

    public static ClassLoader getClassLoader(Class<?> cl2) {
        ClassLoader loader;
        ClassLoader classLoader = loader = cl2 == null ? null : cl2.getClassLoader();
        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }
        return loader == null ? systemClassLoader : loader;
    }

    public static InputStream getResourceAsStream(String path) {
        URL url = Platform.getResource(path);
        try {
            return url != null ? url.openStream() : null;
        }
        catch (IOException ex2) {
            if (BridJ.verbose) {
                BridJ.warning("Failed to get resource '" + path + "'", ex2);
            }
            return null;
        }
    }

    public static URL getResource(String path) {
        ClassLoader[] cls;
        URL in2;
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if ((in2 = BridJ.class.getResource(path)) != null) {
            return in2;
        }
        for (ClassLoader cl2 : cls = new ClassLoader[]{BridJ.class.getClassLoader(), Thread.currentThread().getContextClassLoader(), systemClassLoader}) {
            if (cl2 == null || (in2 = cl2.getResource(path)) == null) continue;
            return in2;
        }
        return null;
    }

    public static synchronized void addEmbeddedLibraryResourceRoot(String root) {
        embeddedLibraryResourceRoots.add(0, root);
    }

    static void addTemporaryExtractedLibraryFileToDeleteOnExit(File file) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        temporaryExtractedLibraryCanonicalFiles.add(canonicalFile);
        canonicalFile.deleteOnExit();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void addNativeLibrary(NativeLibrary library) {
        List<NativeLibrary> list = nativeLibraries;
        synchronized (list) {
            nativeLibraries.add(library);
        }
    }

    private static void shutdown() {
        Platform.deleteTemporaryExtractedLibraryFiles();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void releaseNativeLibraries() {
        List<NativeLibrary> list = nativeLibraries;
        synchronized (list) {
            int iLibrary = nativeLibraries.size();
            while (iLibrary-- != 0) {
                NativeLibrary lib = nativeLibraries.get(iLibrary);
                try {
                    lib.release();
                }
                catch (Throwable th2) {
                    BridJ.error("Failed to release library '" + lib.path + "' : " + th2, th2);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void deleteTemporaryExtractedLibraryFiles() {
        Set<File> set = temporaryExtractedLibraryCanonicalFiles;
        synchronized (set) {
            temporaryExtractedLibraryCanonicalFiles.add(extractedLibrariesTempDir);
            ArrayList<File> filesToDeleteAfterExit = new ArrayList<File>();
            for (File tempFile : temporaryExtractedLibraryCanonicalFiles) {
                if (tempFile.delete()) {
                    if (!BridJ.verbose) continue;
                    BridJ.info("Deleted temporary library file '" + tempFile + "'");
                    continue;
                }
                filesToDeleteAfterExit.add(tempFile);
            }
            if (!filesToDeleteAfterExit.isEmpty()) {
                if (BridJ.verbose) {
                    BridJ.info("Attempting to delete " + filesToDeleteAfterExit.size() + " files after JVM exit : " + StringUtils.implode(filesToDeleteAfterExit, (Object)", "));
                }
                try {
                    ProcessUtils.startJavaProcess(DeleteFiles.class, filesToDeleteAfterExit);
                }
                catch (Throwable ex2) {
                    BridJ.error("Failed to launch process to delete files after JVM exit : " + ex2, ex2);
                }
            }
        }
    }

    static ClassLoader createClassLoader() {
        ArrayList<URL> urls = new ArrayList<URL>();
        for (String propName : new String[]{"java.class.path", "sun.boot.class.path"}) {
            String prop = System.getProperty(propName);
            if (prop == null) continue;
            for (String path : prop.split(File.pathSeparator)) {
                URL url;
                if ((path = path.trim()).length() == 0) continue;
                try {
                    url = new URL(path);
                }
                catch (MalformedURLException ex2) {
                    try {
                        url = new File(path).toURI().toURL();
                    }
                    catch (MalformedURLException ex22) {
                        url = null;
                    }
                }
                if (url == null) continue;
                urls.add(url);
            }
        }
        return new URLClassLoader(urls.toArray(new URL[urls.size()]));
    }

    static String getenvOrProperty(String envName, String javaName, String defaultValue) {
        String value = System.getenv(envName);
        if (value == null) {
            value = System.getProperty(javaName);
        }
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static synchronized void initLibrary() {
        if (inited) {
            return;
        }
        inited = true;
        try {
            boolean loaded = false;
            String forceLibFile = Platform.getenvOrProperty("BRIDJ_LIBRARY", "bridj.library", null);
            String lib = null;
            if (forceLibFile != null) {
                try {
                    lib = forceLibFile;
                    System.load(lib);
                    loaded = true;
                }
                catch (Throwable ex2) {
                    BridJ.error("Failed to load forced library " + forceLibFile, ex2);
                }
            }
            if (!loaded) {
                if (!Platform.isAndroid()) {
                    try {
                        File libFile = Platform.extractEmbeddedLibraryResource(BridJLibraryName);
                        if (libFile == null) {
                            throw new FileNotFoundException("Failed to extract embedded library 'bridj' (could be a classloader issue, or missing binary in resource path " + StringUtils.implode(embeddedLibraryResourceRoots, (Object)", ") + ")");
                        }
                        if (BridJ.veryVerbose) {
                            BridJ.info("Loading library " + libFile);
                        }
                        lib = libFile.toString();
                        System.load(lib);
                        BridJ.setNativeLibraryFile(BridJLibraryName, libFile);
                        loaded = true;
                    }
                    catch (IOException ex3) {
                        BridJ.error("Failed to load 'bridj'", ex3);
                    }
                }
                if (!loaded) {
                    System.loadLibrary(BridJLibraryName);
                }
            }
            if (BridJ.veryVerbose) {
                BridJ.info("Loaded library " + lib);
            }
            Platform.init();
            if (BridJ.logCalls) {
                BridJ.info("Calls logs enabled");
            }
        }
        catch (Throwable ex4) {
            throw new RuntimeException("Failed to initialize " + BridJ.class.getSimpleName(), ex4);
        }
    }

    private static native void init();

    public static boolean isLinux() {
        return Platform.isUnix() && osName.toLowerCase().contains("linux");
    }

    public static boolean isMacOSX() {
        return Platform.isUnix() && (osName.startsWith("Mac") || osName.startsWith("Darwin"));
    }

    public static boolean isSolaris() {
        return Platform.isUnix() && (osName.startsWith("SunOS") || osName.startsWith("Solaris"));
    }

    public static boolean isBSD() {
        return Platform.isUnix() && (osName.contains("BSD") || Platform.isMacOSX());
    }

    public static boolean isUnix() {
        return File.separatorChar == '/';
    }

    public static boolean isWindows() {
        return File.separatorChar == '\\';
    }

    public static boolean isWindows7() {
        return osName.equals("Windows 7");
    }

    private static String getArch() {
        return arch;
    }

    public static String getMachine() {
        String arch = Platform.getArch();
        if (arch.equals("amd64") || arch.equals("x86_64")) {
            if (Platform.is64Bits()) {
                return "x86_64";
            }
            return "i386";
        }
        return arch;
    }

    public static boolean isAndroid() {
        return "dalvik".equalsIgnoreCase(System.getProperty("java.vm.name")) && Platform.isLinux();
    }

    public static boolean isArm() {
        String arch = Platform.getArch();
        return "arm".equals(arch);
    }

    public static boolean isSparc() {
        String arch = Platform.getArch();
        return "sparc".equals(arch) || "sparcv9".equals(arch);
    }

    public static boolean is64Bits() {
        return is64Bits;
    }

    public static boolean isAmd64Arch() {
        String arch = Platform.getArch();
        return arch.equals("x86_64");
    }

    static synchronized Collection<String> getEmbeddedLibraryResource(String name) {
        ArrayList<String> ret = new ArrayList<String>();
        for (String root : embeddedLibraryResourceRoots) {
            if (root == null) continue;
            if (Platform.isWindows()) {
                ret.add(root + (Platform.is64Bits() ? "win64/" : "win32/") + name + ".dll");
                continue;
            }
            if (Platform.isMacOSX()) {
                String suff = "/lib" + name + ".dylib";
                if (Platform.isArm()) {
                    ret.add(root + "iphoneos_arm32_arm" + suff);
                    continue;
                }
                String pref = root + "darwin_";
                String univ = pref + "universal" + suff;
                if (Platform.isAmd64Arch()) {
                    ret.add(univ);
                    ret.add(pref + "x64" + suff);
                    continue;
                }
                ret.add(univ);
                continue;
            }
            String path = null;
            if (Platform.isAndroid()) {
                assert (root.equals("lib/"));
                path = root + "armeabi/";
            } else if (Platform.isLinux()) {
                path = root + (Platform.isArm() ? "linux_arm32_arm/" : (Platform.is64Bits() ? "linux_x64/" : "linux_x86/"));
            } else if (Platform.isSolaris()) {
                path = Platform.isSparc() ? root + (Platform.is64Bits() ? "sunos_sparc64/" : "sunos_sparc/") : root + (Platform.is64Bits() ? "sunos_x64/" : "sunos_x86/");
            }
            if (path == null) continue;
            ret.add(path + "lib" + name + ".so");
            ret.add(path + name + ".so");
        }
        if (ret.isEmpty()) {
            throw new RuntimeException("Platform not supported ! (os.name='" + osName + "', os.arch='" + System.getProperty("os.arch") + "')");
        }
        if (BridJ.veryVerbose) {
            BridJ.info("Embedded paths for library " + name + " : " + ret);
        }
        return ret;
    }

    static void tryDeleteFilesInSameDirectory(final File legitFile, final Pattern fileNamePattern, long atLeastOlderThanMillis) {
        final long maxModifiedDateForDeletion = System.currentTimeMillis() - atLeastOlderThanMillis;
        new Thread(new Runnable(){

            public void run() {
                File dir = legitFile.getParentFile();
                String legitFileName = legitFile.getName();
                try {
                    for (String name : dir.list()) {
                        File file;
                        if (name.equals(legitFileName) || !fileNamePattern.matcher(name).matches() || (file = new File(dir, name)).lastModified() > maxModifiedDateForDeletion || !file.delete() || !BridJ.verbose) continue;
                        BridJ.info("Deleted old binary file '" + file + "'");
                    }
                }
                catch (SecurityException ex2) {
                    BridJ.warning("Failed to delete files matching '" + fileNamePattern + "' in directory '" + dir + "'", ex2);
                }
                catch (Throwable ex3) {
                    BridJ.error("Unexpected error : " + ex3, ex3);
                }
            }
        }).start();
    }

    static File extractEmbeddedLibraryResource(String name) throws IOException {
        String firstLibraryResource = null;
        for (String libraryResource : Platform.getEmbeddedLibraryResource(name)) {
            int len;
            if (firstLibraryResource == null) {
                firstLibraryResource = libraryResource;
            }
            int i2 = libraryResource.lastIndexOf(46);
            byte[] b2 = new byte[8196];
            InputStream in2 = Platform.getResourceAsStream(libraryResource);
            if (in2 == null) {
                File f2 = new File(libraryResource);
                if (!f2.exists()) {
                    f2 = new File(f2.getName());
                }
                if (!f2.exists()) continue;
                return f2.getCanonicalFile();
            }
            String fileName = new File(libraryResource).getName();
            File libFile = new File(extractedLibrariesTempDir, fileName);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(libFile));
            while ((len = in2.read(b2)) > 0) {
                ((OutputStream)out).write(b2, 0, len);
            }
            ((OutputStream)out).close();
            in2.close();
            Platform.addTemporaryExtractedLibraryFileToDeleteOnExit(libFile);
            Platform.addTemporaryExtractedLibraryFileToDeleteOnExit(libFile.getParentFile());
            return libFile;
        }
        return null;
    }

    static File createTempDir(String prefix) throws IOException {
        for (int i2 = 0; i2 < 20; ++i2) {
            File dir = File.createTempFile(prefix, "");
            if (!dir.delete() || !dir.mkdirs()) continue;
            return dir;
        }
        throw new RuntimeException("Failed to create temp dir with prefix '" + prefix + "' despite " + 20 + " attempts!");
    }

    public static final void open(URL url) throws NoSuchMethodException {
        if (url.getProtocol().equals("file")) {
            Platform.open(new File(url.getFile()));
        } else if (Platform.isMacOSX()) {
            Platform.execArgs("open", url.toString());
        } else if (Platform.isWindows()) {
            Platform.execArgs("rundll32", "url.dll,FileProtocolHandler", url.toString());
        } else if (Platform.isUnix() && Platform.hasUnixCommand("gnome-open")) {
            Platform.execArgs("gnome-open", url.toString());
        } else if (Platform.isUnix() && Platform.hasUnixCommand("konqueror")) {
            Platform.execArgs("konqueror", url.toString());
        } else if (Platform.isUnix() && Platform.hasUnixCommand("mozilla")) {
            Platform.execArgs("mozilla", url.toString());
        } else {
            throw new NoSuchMethodException("Cannot open urls on this platform");
        }
    }

    public static final void open(File file) throws NoSuchMethodException {
        if (Platform.isMacOSX()) {
            Platform.execArgs("open", file.getAbsolutePath());
        } else if (Platform.isWindows()) {
            if (file.isDirectory()) {
                Platform.execArgs("explorer", file.getAbsolutePath());
            } else {
                Platform.execArgs("start", file.getAbsolutePath());
            }
        } else if (Platform.isUnix() && Platform.hasUnixCommand("gnome-open")) {
            Platform.execArgs("gnome-open", file.toString());
        } else if (Platform.isUnix() && Platform.hasUnixCommand("konqueror")) {
            Platform.execArgs("konqueror", file.toString());
        } else if (Platform.isSolaris() && file.isDirectory()) {
            Platform.execArgs("/usr/dt/bin/dtfile", "-folder", file.getAbsolutePath());
        } else {
            throw new NoSuchMethodException("Cannot open files on this platform");
        }
    }

    public static final void show(File file) throws NoSuchMethodException, IOException {
        if (Platform.isWindows()) {
            Platform.exec("explorer /e,/select,\"" + file.getCanonicalPath() + "\"");
        } else {
            Platform.open(file.getAbsoluteFile().getParentFile());
        }
    }

    static final void execArgs(String ... cmd) throws NoSuchMethodException {
        try {
            Runtime.getRuntime().exec(cmd);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            throw new NoSuchMethodException(ex2.toString());
        }
    }

    static final void exec(String cmd) throws NoSuchMethodException {
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            throw new NoSuchMethodException(ex2.toString());
        }
    }

    static final boolean hasUnixCommand(String name) {
        try {
            Process p2 = Runtime.getRuntime().exec(new String[]{"which", name});
            return p2.waitFor() == 0;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        }
    }

    static native int sizeOf_size_t();

    static native int sizeOf_time_t();

    static native int sizeOf_wchar_t();

    static native int sizeOf_ptrdiff_t();

    static native int sizeOf_long();

    static native int getMaxDirectMappingArgCount();

    static {
        embeddedLibraryResourceRoots = new ArrayList<String>();
        temporaryExtractedLibraryCanonicalFiles = Collections.synchronizedSet(new LinkedHashSet());
        arch = System.getProperty("os.arch");
        String dataModel = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        is64Bits = "32".equals(dataModel) ? false : ("64".equals(dataModel) ? true : arch.contains("64") || arch.equalsIgnoreCase("sparcv9"));
        systemClassLoader = Platform.createClassLoader();
        Platform.addEmbeddedLibraryResourceRoot("lib/");
        if (!Platform.isAndroid()) {
            Platform.addEmbeddedLibraryResourceRoot("org/bridj/lib/");
            if (!"v0_6_2".equals("")) {
                Platform.addEmbeddedLibraryResourceRoot("org/bridj/v0_6_2/lib/");
            }
        }
        try {
            extractedLibrariesTempDir = Platform.createTempDir("BridJExtractedLibraries");
            Platform.initLibrary();
        }
        catch (Throwable th2) {
            th2.printStackTrace();
        }
        POINTER_SIZE = Platform.sizeOf_ptrdiff_t();
        WCHAR_T_SIZE = Platform.sizeOf_wchar_t();
        SIZE_T_SIZE = Platform.sizeOf_size_t();
        TIME_T_SIZE = Platform.sizeOf_time_t();
        CLONG_SIZE = Platform.sizeOf_long();
        is64Bits = POINTER_SIZE == 8;
        Runtime.getRuntime().addShutdownHook(new Thread(){

            public void run() {
                Platform.shutdown();
            }
        });
        nativeLibraries = new ArrayList<NativeLibrary>();
        useUnicodeVersionOfWindowsAPIs = !"false".equals(System.getProperty("bridj.useUnicodeVersionOfWindowsAPIs")) && !"0".equals(System.getenv("BRIDJ_USE_UNICODE_VERSION_OF_WINDOWS_APIS"));
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class DeleteFiles {
        static final long TRY_DELETE_EVERY_MILLIS = 50L;
        static final long FAIL_AFTER_MILLIS = 10000L;

        static boolean delete(List<File> files) {
            Iterator<File> it2 = files.iterator();
            while (it2.hasNext()) {
                File file = it2.next();
                if (!file.delete()) continue;
                it2.remove();
            }
            return files.isEmpty();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public static void main(String[] args) {
            try {
                LinkedList<File> files = new LinkedList<File>();
                for (String arg2 : args) {
                    files.add(new File(arg2));
                }
                long start = System.currentTimeMillis();
                while (!DeleteFiles.delete(files)) {
                    long elapsed = System.currentTimeMillis() - start;
                    if (elapsed > 10000L) {
                        System.err.println("Failed to delete the following files : " + StringUtils.implode(files));
                        System.exit(1);
                    }
                    Thread.sleep(50L);
                }
            }
            catch (Throwable th2) {
                th2.printStackTrace();
            }
            finally {
                System.exit(0);
            }
        }
    }
}

