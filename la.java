/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class la {
    private static final Logger a = LogManager.getLogger();
    private final le b;
    private Set<lh> c = Sets.newHashSet();
    private nm<lh> d = new nm();
    private int e;

    public la(le le2) {
        this.b = le2;
        this.e = le2.r().ap().d();
    }

    public void a(pk pk22) {
        pk pk22;
        if (pk22 instanceof lf) {
            this.a(pk22, 512, 2);
            lf lf2 = (lf)pk22;
            for (lh lh2 : this.c) {
                if (lh2.a == lf2) continue;
                lh2.b(lf2);
            }
        } else if (pk22 instanceof ur) {
            this.a(pk22, 64, 5, true);
        } else if (pk22 instanceof wq) {
            this.a(pk22, 64, 20, false);
        } else if (pk22 instanceof ww) {
            this.a(pk22, 64, 10, false);
        } else if (pk22 instanceof ws) {
            this.a(pk22, 64, 10, false);
        } else if (pk22 instanceof wx) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof xa) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof wr) {
            this.a(pk22, 64, 4, true);
        } else if (pk22 instanceof wz) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof xc) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof xb) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof wt) {
            this.a(pk22, 64, 10, true);
        } else if (pk22 instanceof uz) {
            this.a(pk22, 64, 20, true);
        } else if (pk22 instanceof va) {
            this.a(pk22, 80, 3, true);
        } else if (pk22 instanceof ux) {
            this.a(pk22, 80, 3, true);
        } else if (pk22 instanceof tx) {
            this.a(pk22, 64, 3, true);
        } else if (pk22 instanceof uk) {
            this.a(pk22, 80, 3, false);
        } else if (pk22 instanceof tk) {
            this.a(pk22, 80, 3, false);
        } else if (pk22 instanceof ug) {
            this.a(pk22, 160, 3, true);
        } else if (pk22 instanceof pi) {
            this.a(pk22, 80, 3, true);
        } else if (pk22 instanceof vj) {
            this.a(pk22, 160, 10, true);
        } else if (pk22 instanceof uy) {
            this.a(pk22, 160, 20, true);
        } else if (pk22 instanceof un) {
            this.a(pk22, 160, Integer.MAX_VALUE, false);
        } else if (pk22 instanceof um) {
            this.a(pk22, 160, 3, true);
        } else if (pk22 instanceof pp) {
            this.a(pk22, 160, 20, true);
        } else if (pk22 instanceof uf) {
            this.a(pk22, 256, Integer.MAX_VALUE, false);
        }
    }

    public void a(pk pk2, int n2, int n3) {
        this.a(pk2, n2, n3, false);
    }

    public void a(pk pk22, int n22, final int n3, boolean bl2) {
        if (n22 > this.e) {
            int n22 = this.e;
        }
        try {
            pk pk22;
            if (this.d.b(pk22.F())) {
                throw new IllegalStateException("Entity is already tracked!");
            }
            lh \u26032 = new lh(pk22, n22, n3, bl2);
            this.c.add(\u26032);
            this.d.a(pk22.F(), \u26032);
            \u26032.b(this.b.j);
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Adding entity to track");
            c \u26033 = b2.a("Entity To Track");
            \u26033.a("Tracking range", n22 + " blocks");
            \u26033.a("Update interval", new Callable<String>(){

                public String a() throws Exception {
                    String string = "Once per " + n3 + " ticks";
                    if (n3 == Integer.MAX_VALUE) {
                        string = "Maximum (" + string + ")";
                    }
                    return string;
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            pk22.a(\u26033);
            c \u26034 = b2.a("Entity That Is Already Tracked");
            this.d.a((int)pk22.F()).a.a(\u26034);
            try {
                throw new e(b2);
            }
            catch (e \u26035) {
                a.error("\"Silently\" catching entity tracking error.", (Throwable)\u26035);
            }
        }
    }

    public void b(pk pk2) {
        Object object;
        if (pk2 instanceof lf) {
            object = (lf)pk2;
            for (lh lh2 : this.c) {
                lh2.a((lf)object);
            }
        }
        if ((object = this.d.d(pk2.F())) != null) {
            this.c.remove(object);
            ((lh)object).a();
        }
    }

    public void a() {
        ArrayList<lf> arrayList = Lists.newArrayList();
        for (lh lh2 : this.c) {
            lh2.a(this.b.j);
            if (!lh2.n || !(lh2.a instanceof lf)) continue;
            arrayList.add((lf)lh2.a);
        }
        for (int i2 = 0; i2 < arrayList.size(); ++i2) {
            lf lf2 = (lf)arrayList.get(i2);
            for (lh lh2 : this.c) {
                if (lh2.a == lf2) continue;
                lh2.b(lf2);
            }
        }
    }

    public void a(lf lf2) {
        for (lh lh2 : this.c) {
            if (lh2.a == lf2) {
                lh2.b(this.b.j);
                continue;
            }
            lh2.b(lf2);
        }
    }

    public void a(pk pk2, ff ff2) {
        lh lh2 = this.d.a(pk2.F());
        if (lh2 != null) {
            lh2.a(ff2);
        }
    }

    public void b(pk pk2, ff ff2) {
        lh lh2 = this.d.a(pk2.F());
        if (lh2 != null) {
            lh2.b(ff2);
        }
    }

    public void b(lf lf2) {
        for (lh lh2 : this.c) {
            lh2.d(lf2);
        }
    }

    public void a(lf lf2, amy amy2) {
        for (lh lh2 : this.c) {
            if (lh2.a == lf2 || lh2.a.ae != amy2.a || lh2.a.ag != amy2.b) continue;
            lh2.b(lf2);
        }
    }
}

