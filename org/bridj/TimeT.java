/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.util.Date;
import java.util.List;
import org.bridj.AbstractIntegral;
import org.bridj.BridJ;
import org.bridj.Platform;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Struct;

public final class TimeT
extends AbstractIntegral {
    public static final int SIZE = Platform.TIME_T_SIZE;

    public TimeT(long value) {
        super(value);
    }

    public Date toDate() {
        return new Date(this.value);
    }

    public static TimeT valueOf(long value) {
        return new TimeT(value);
    }

    public static TimeT valueOf(Date value) {
        return TimeT.valueOf(value.getTime());
    }

    public String toString() {
        return "TimeT(value = " + this.value + ", time = " + this.toDate() + ")";
    }

    static {
        BridJ.register();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class timeval_customizer
    extends StructIO.DefaultCustomizer {
        @Override
        public void beforeLayout(StructIO io2, List<StructIO.AggregatedFieldDesc> aggregatedFields) {
            StructIO.AggregatedFieldDesc secondsField = aggregatedFields.get(0);
            secondsField.byteLength = Platform.isWindows() || !Platform.is64Bits() ? 4L : 8L;
            secondsField.alignment = secondsField.byteLength;
        }
    }

    @Struct(customizer=timeval_customizer.class)
    public static class timeval
    extends StructObject {
        public long getTime() {
            return this.seconds() * 1000L + (long)this.milliseconds();
        }

        @Field(value=0)
        public long seconds() {
            return this.io.getCLongField(this, 0);
        }

        @Field(value=0)
        public timeval seconds(long seconds) {
            this.io.setCLongField(this, 0, seconds);
            return this;
        }

        public final long seconds_$eq(long seconds) {
            this.seconds(seconds);
            return seconds;
        }

        @Field(value=1)
        public int milliseconds() {
            return this.io.getIntField(this, 1);
        }

        @Field(value=1)
        public timeval milliseconds(int milliseconds) {
            this.io.setIntField(this, 1, milliseconds);
            return this;
        }

        public final int milliseconds_$eq(int milliseconds) {
            this.milliseconds(milliseconds);
            return milliseconds;
        }
    }
}

