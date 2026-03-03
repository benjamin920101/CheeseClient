/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class auc
implements Runnable {
    private static final auc a = new auc();
    private List<aud> b = Collections.synchronizedList(Lists.newArrayList());
    private volatile long c;
    private volatile long d;
    private volatile boolean e;

    private auc() {
        Thread thread = new Thread((Runnable)this, "File IO Thread");
        thread.setPriority(1);
        thread.start();
    }

    public static auc a() {
        return a;
    }

    @Override
    public void run() {
        while (true) {
            this.c();
        }
    }

    private void c() {
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            aud aud2 = this.b.get(i2);
            boolean \u26032 = aud2.c();
            if (!\u26032) {
                this.b.remove(i2--);
                ++this.d;
            }
            try {
                Thread.sleep(this.e ? 0L : 10L);
                continue;
            }
            catch (InterruptedException \u26033) {
                \u26033.printStackTrace();
            }
        }
        if (this.b.isEmpty()) {
            try {
                Thread.sleep(25L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public void a(aud aud2) {
        if (this.b.contains(aud2)) {
            return;
        }
        ++this.c;
        this.b.add(aud2);
    }

    public void b() throws InterruptedException {
        this.e = true;
        while (this.c != this.d) {
            Thread.sleep(10L);
        }
        this.e = false;
    }
}

