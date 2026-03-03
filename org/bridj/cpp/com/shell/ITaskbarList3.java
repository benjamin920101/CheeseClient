/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com.shell;

import java.util.Collections;
import java.util.Iterator;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.shell.ITaskbarList2;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@IID(value="EA1AFB91-9E28-4B86-90E9-9E9F8A5EEFAF")
@CLSID(value="56FDF344-FD6D-11d0-958A-006097C9A090")
public class ITaskbarList3
extends ITaskbarList2 {
    @Virtual(value=0)
    public native int SetProgressValue(Pointer<Integer> var1, long var2, long var4);

    @Virtual(value=1)
    public native int SetProgressState(Pointer<Integer> var1, ValuedEnum<TbpFlag> var2);

    @Virtual(value=2)
    public native void RegisterTab(Pointer<Integer> var1, Pointer<Integer> var2);

    @Virtual(value=3)
    public native void UnregisterTab(Pointer<Integer> var1);

    @Virtual(value=4)
    public native void SetTabOrder(Pointer<Integer> var1, Pointer<Integer> var2);

    @Virtual(value=5)
    public native void SetTabActive(Pointer<Integer> var1, Pointer<Integer> var2, int var3);

    @Virtual(value=6)
    public native void ThumbBarAddButtons(Pointer<Integer> var1, int var2, Pointer<THUMBBUTTON> var3);

    @Virtual(value=7)
    public native void ThumbBarUpdateButtons(Pointer<Integer> var1, int var2, Pointer<THUMBBUTTON> var3);

    @Virtual(value=8)
    public native void ThumbBarSetImageList(Pointer<Integer> var1, Pointer<Integer> var2);

    @Virtual(value=9)
    public native void SetOverlayIcon(Pointer<Integer> var1, Pointer<?> var2, Pointer<Character> var3);

    @Virtual(value=10)
    public native void SetThumbnailTooltip(Pointer<Integer> var1, Pointer<Character> var2);

    @Virtual(value=11)
    public native void SetThumbnailClip(Pointer<Integer> var1, Pointer<RECT> var2);

    public class RECT {
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum TbpFlag implements IntValuedEnum<TbpFlag>
    {
        TBPF_NOPROGRESS(0),
        TBPF_INDETERMINATE(1),
        TBPF_NORMAL(2),
        TBPF_ERROR(4),
        TBPF_PAUSED(8);

        public final int value;

        private TbpFlag(int value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<TbpFlag> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<TbpFlag> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])TbpFlag.values());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class THUMBBUTTON
    extends StructObject {
        @Field(value=0)
        public native ValuedEnum<THUMBBUTTONMASK> dwMask();

        public native void dwMask(ValuedEnum<THUMBBUTTONMASK> var1);

        @Field(value=1)
        public native int iId();

        public native void iId(int var1);

        @Field(value=2)
        public native int iBitmap();

        public native void iBitmap(int var1);

        @Field(value=3)
        public native Pointer<?> hIcon();

        public native void hIcon(Pointer<?> var1);

        @Field(value=4)
        @Array(value={260L})
        public native Pointer<Character> szTip();

        public native void szTip(Pointer<Character> var1);

        @Field(value=5)
        public native ValuedEnum<THUMBBUTTONFLAGS> dwFlags();

        public native void dwFlags(ValuedEnum<THUMBBUTTONFLAGS> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum THUMBBUTTONFLAGS implements IntValuedEnum<THUMBBUTTONFLAGS>
    {
        THBF_ENABLED(0),
        THBF_DISABLED(1),
        THBF_DISMISSONCLICK(2),
        THBF_NOBACKGROUND(4),
        THBF_HIDDEN(8),
        THBF_NONINTERACTIVE(16);

        final int value;

        private THUMBBUTTONFLAGS(int value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<THUMBBUTTONFLAGS> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<THUMBBUTTONFLAGS> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])THUMBBUTTONFLAGS.values());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum THUMBBUTTONMASK implements IntValuedEnum<THUMBBUTTONMASK>
    {
        THB_BITMAP(1),
        THB_ICON(2),
        THB_TOOLTIP(4),
        THB_FLAGS(8);

        public final int value;

        private THUMBBUTTONMASK(int value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<THUMBBUTTONMASK> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<THUMBBUTTONMASK> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])THUMBBUTTONMASK.values());
        }
    }
}

