/*
 * Decompiled with CFR 0.152.
 */
public class fh {
    public static <T extends ep> void a(final ff<T> ff2, final T t2, od od2) throws ki {
        if (!od2.aJ()) {
            od2.a(new Runnable(){

                @Override
                public void run() {
                    ff2.a(t2);
                }
            });
            throw ki.a;
        }
    }
}

