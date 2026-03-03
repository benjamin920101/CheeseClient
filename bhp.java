/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bhp
implements Runnable {
    private static final Logger a = LogManager.getLogger();
    private final bho b;
    private final bfg c;

    public bhp(bho bho2) {
        this(bho2, null);
    }

    public bhp(bho bho2, bfg bfg2) {
        this.b = bho2;
        this.c = bfg2;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.a(this.b.d());
            }
        }
        catch (InterruptedException interruptedException) {
            a.debug("Stopping due to interrupt");
            return;
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Batching chunks");
            ave.A().a(ave.A().b(b2));
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void a(final bhn bhn22) throws InterruptedException {
        bhn22.f().lock();
        try {
            if (bhn22.a() != bhn.a.a) {
                if (!bhn22.h()) {
                    a.warn("Chunk render task was " + (Object)((Object)bhn22.a()) + " when I expected it to be pending; ignoring task");
                }
                return;
            }
            bhn22.a(bhn.a.b);
        }
        finally {
            bhn22.f().unlock();
        }
        pk pk2 = ave.A().ac();
        if (pk2 == null) {
            bhn22.e();
            return;
        }
        bhn22.a(this.b());
        float \u26032 = (float)pk2.s;
        float \u26033 = (float)pk2.t + pk2.aS();
        float \u26034 = (float)pk2.u;
        bhn.b \u26035 = bhn22.g();
        if (\u26035 == bhn.b.a) {
            bhn22.b().b(\u26032, \u26033, \u26034, bhn22);
        } else if (\u26035 == bhn.b.b) {
            bhn22.b().a(\u26032, \u26033, \u26034, bhn22);
        }
        bhn22.f().lock();
        try {
            if (bhn22.a() != bhn.a.b) {
                if (!bhn22.h()) {
                    a.warn("Chunk render task was " + (Object)((Object)bhn22.a()) + " when I expected it to be compiling; aborting task");
                }
                this.b(bhn22);
                return;
            }
            bhn22.a(bhn.a.c);
        }
        finally {
            bhn22.f().unlock();
        }
        final bhq \u26036 = bhn22.c();
        ArrayList<ListenableFuture<Object>> \u26037 = Lists.newArrayList();
        if (\u26035 == bhn.b.a) {
            for (adf adf2 : adf.values()) {
                if (!\u26036.d(adf2)) continue;
                \u26037.add(this.b.a(adf2, bhn22.d().a(adf2), bhn22.b(), \u26036));
            }
        } else if (\u26035 == bhn.b.b) {
            \u26037.add(this.b.a(adf.d, bhn22.d().a(adf.d), bhn22.b(), \u26036));
        }
        final ListenableFuture listenableFuture = Futures.allAsList(\u26037);
        bhn22.a(new Runnable(){

            @Override
            public void run() {
                listenableFuture.cancel(false);
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<List<Object>>(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void a(List<Object> list) {
                bhp.this.b(bhn22);
                bhn22.f().lock();
                try {
                    if (bhn22.a() != bhn.a.c) {
                        if (!bhn22.h()) {
                            a.warn("Chunk render task was " + (Object)((Object)bhn22.a()) + " when I expected it to be uploading; aborting task");
                        }
                        return;
                    }
                    bhn22.a(bhn.a.d);
                }
                finally {
                    bhn22.f().unlock();
                }
                bhn22.b().a(\u26036);
            }

            @Override
            public void onFailure(Throwable throwable) {
                bhp.this.b(bhn22);
                if (!(throwable instanceof CancellationException) && !(throwable instanceof InterruptedException)) {
                    ave.A().a(b.a(throwable, "Rendering chunk"));
                }
            }

            @Override
            public /* synthetic */ void onSuccess(Object object) {
                this.a((List)object);
            }
        });
    }

    private bfg b() throws InterruptedException {
        return this.c != null ? this.c : this.b.c();
    }

    private void b(bhn bhn2) {
        if (this.c == null) {
            this.b.a(bhn2.d());
        }
    }
}

