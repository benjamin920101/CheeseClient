/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import jaco.mp3.player.a;
import jaco.mp3.player.c;
import jaco.mp3.player.t;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public final class R
extends a {
    private SourceDataLine a = null;
    private AudioFormat b = null;
    private byte[] c = new byte[4096];

    @Override
    protected final void b() {
        if (this.a != null) {
            this.a.close();
        }
    }

    @Override
    protected final void b(short[] objectArray, int n2, int n3) {
        if (this.a == null) {
            R r2 = this;
            Throwable throwable = null;
            try {
                R r3 = r2;
                Object object = r3;
                object = r3;
                if (r3.b == null) {
                    c c2 = ((a)object).e();
                    ((R)object).b = new AudioFormat(c2.a(), 16, c2.b(), true, false);
                }
                object = ((R)object).b;
                object = new DataLine.Info(SourceDataLine.class, (AudioFormat)object);
                if ((object = AudioSystem.getLine((Line.Info)object)) instanceof SourceDataLine) {
                    r2.a = (SourceDataLine)object;
                    r2.a.open(r2.b);
                    r2.a.start();
                }
            }
            catch (RuntimeException runtimeException) {
                RuntimeException runtimeException2 = runtimeException;
                throwable = runtimeException;
            }
            catch (LinkageError linkageError) {
                LinkageError linkageError2 = linkageError;
                throwable = linkageError;
            }
            catch (LineUnavailableException lineUnavailableException) {
                LineUnavailableException lineUnavailableException2 = lineUnavailableException;
                throwable = lineUnavailableException;
            }
            if (r2.a == null) {
                throw new t("cannot obtain source audio line", throwable);
            }
        }
        objectArray = this.c((short[])objectArray, n2, n3);
        this.a.write((byte[])objectArray, 0, n3 << 1);
    }

    private byte[] c(short[] sArray, int n2, int n3) {
        int n4 = n3 << 1;
        if (((R)object).c.length < n4) {
            ((R)object).c = new byte[n4 + 1024];
        }
        Object object = ((R)object).c;
        n4 = 0;
        while (n3-- > 0) {
            short s2 = sArray[n2++];
            object[n4++] = (byte)s2;
            object[n4++] = (byte)(s2 >>> 8);
        }
        return object;
    }

    @Override
    protected final void d() {
        if (this.a != null) {
            this.a.drain();
        }
    }
}

