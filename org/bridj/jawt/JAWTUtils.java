/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.jawt;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import org.bridj.JNI;
import org.bridj.Pointer;
import org.bridj.jawt.JAWT;
import org.bridj.jawt.JAWT_DrawingSurface;
import org.bridj.jawt.JAWT_DrawingSurfaceInfo;
import org.bridj.jawt.JawtLibrary;

public class JAWTUtils {
    public static JawtLibrary.JNIEnv getJNIEnv() {
        return new JawtLibrary.JNIEnv(JNI.getEnv());
    }

    public static JAWT getJAWT(JawtLibrary.JNIEnv env) {
        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("No native peers in headless mode.");
        }
        JAWT awt2 = new JAWT().version(65540);
        Pointer<JAWT> pAwt = Pointer.pointerTo(awt2);
        if (!JawtLibrary.JAWT_GetAWT(env, pAwt)) {
            throw new RuntimeException("Failed to get JAWT pointer !");
        }
        return pAwt.get();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void withLockedSurface(JawtLibrary.JNIEnv env, JAWT awt2, Component component, LockedComponentRunnable runnable) {
        if (component.isLightweight()) {
            throw new IllegalArgumentException("Lightweight components do not have native peers.");
        }
        if (!component.isDisplayable()) {
            throw new IllegalArgumentException("Component that are not displayable do not have native peers.");
        }
        Pointer<?> componentPointer = JNI.getGlobalPointer(component);
        Pointer<JAWT_DrawingSurface> pSurface = awt2.GetDrawingSurface().get().invoke(env, componentPointer).as(JAWT_DrawingSurface.class);
        if (pSurface == null) {
            throw new RuntimeException("Cannot get drawing surface from " + component);
        }
        JAWT_DrawingSurface surface = pSurface.get();
        try {
            int lock = surface.Lock().get().invoke(pSurface);
            if ((lock & 1) != 0) {
                throw new RuntimeException("Cannot lock drawing surface of " + component);
            }
            try {
                Pointer<JAWT_DrawingSurface.GetDrawingSurfaceInfo_callback> cb2 = surface.GetDrawingSurfaceInfo().as(JAWT_DrawingSurface.GetDrawingSurfaceInfo_callback.class);
                Pointer<JAWT_DrawingSurfaceInfo> pInfo = cb2.get().invoke(pSurface);
                if (pInfo != null) {
                    pInfo = pInfo.as(JAWT_DrawingSurfaceInfo.class);
                }
                Pointer platformInfo = pInfo.get().platformInfo();
                long peer = platformInfo.getSizeT();
                runnable.run(component, peer);
            }
            finally {
                surface.Unlock().get().invoke(pSurface);
            }
        }
        finally {
            awt2.FreeDrawingSurface().get().invoke(pSurface);
        }
    }

    public static long getNativePeerHandle(Component component) {
        try {
            JawtLibrary.JNIEnv env = JAWTUtils.getJNIEnv();
            JAWT awt2 = JAWTUtils.getJAWT(env);
            final long[] ret = new long[1];
            JAWTUtils.withLockedSurface(env, awt2, component, new LockedComponentRunnable(){

                public void run(Component component, long peer) {
                    ret[0] = peer;
                }
            });
            return ret[0];
        }
        catch (Throwable ex2) {
            ex2.printStackTrace();
            return 0L;
        }
    }

    public static interface LockedComponentRunnable {
        public void run(Component var1, long var2);
    }
}

