/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamDriverUtils {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamDriverUtils.class);

    private WebcamDriverUtils() {
    }

    protected static WebcamDriver findDriver(List<String> names, List<Class<?>> classes) {
        for (String name : names) {
            LOG.info("Searching driver {}", (Object)name);
            Class<?> clazz = null;
            for (Class<?> c2 : classes) {
                if (!c2.getCanonicalName().equals(name)) continue;
                clazz = c2;
                break;
            }
            if (clazz == null) {
                try {
                    clazz = Class.forName(name);
                }
                catch (ClassNotFoundException e2) {
                    LOG.trace("Class not found {}, fall thru", (Object)name);
                }
            }
            if (clazz == null) {
                LOG.debug("Driver {} not found", (Object)name);
                continue;
            }
            LOG.info("Webcam driver {} has been found", (Object)name);
            try {
                return (WebcamDriver)clazz.newInstance();
            }
            catch (InstantiationException e3) {
                throw new RuntimeException(e3);
            }
            catch (IllegalAccessException e4) {
                throw new RuntimeException(e4);
            }
        }
        return null;
    }

    protected static Class<?>[] getClasses(String pkgname, boolean flat) {
        ArrayList<File> dirs = new ArrayList<File>();
        ArrayList classes = new ArrayList();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = pkgname.replace('.', '/');
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
        }
        catch (IOException e2) {
            throw new RuntimeException("Cannot read path " + path, e2);
        }
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        for (File directory : dirs) {
            try {
                classes.addAll(WebcamDriverUtils.findClasses(directory, pkgname, flat));
            }
            catch (ClassNotFoundException e3) {
                throw new RuntimeException("Class not found", e3);
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class<?>> findClasses(File dir, String pkgname, boolean flat) throws ClassNotFoundException {
        File[] files;
        ArrayList classes = new ArrayList();
        if (!dir.exists()) {
            return classes;
        }
        for (File file : files = dir.listFiles()) {
            if (file.isDirectory() && !flat) {
                classes.addAll(WebcamDriverUtils.findClasses(file, pkgname + "." + file.getName(), flat));
                continue;
            }
            if (!file.getName().endsWith(".class")) continue;
            classes.add(Class.forName(pkgname + '.' + file.getName().substring(0, file.getName().length() - 6)));
        }
        return classes;
    }
}

