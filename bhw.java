/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;

public class bhw {
    private static final int a = (int)Math.pow(16.0, 0.0);
    private static final int b = (int)Math.pow(16.0, 1.0);
    private static final int c = (int)Math.pow(16.0, 2.0);
    private final BitSet d = new BitSet(4096);
    private static final int[] e = new int[1352];
    private int f = 4096;

    public void a(cj cj2) {
        this.d.set(bhw.c(cj2), true);
        --this.f;
    }

    private static int c(cj cj2) {
        return bhw.a(cj2.n() & 0xF, cj2.o() & 0xF, cj2.p() & 0xF);
    }

    private static int a(int n2, int n3, int n4) {
        return n2 << 0 | n3 << 8 | n4 << 4;
    }

    public bhx a() {
        bhx bhx2 = new bhx();
        if (4096 - this.f < 256) {
            bhx2.a(true);
        } else if (this.f == 0) {
            bhx2.a(false);
        } else {
            for (int n2 : e) {
                if (this.d.get(n2)) continue;
                bhx2.a(this.a(n2));
            }
        }
        return bhx2;
    }

    public Set<cq> b(cj cj2) {
        return this.a(bhw.c(cj2));
    }

    private Set<cq> a(int n2) {
        EnumSet<cq> enumSet = EnumSet.noneOf(cq.class);
        LinkedList<Integer> \u26032 = Lists.newLinkedList();
        \u26032.add(nl.a(n2));
        this.d.set(n2, true);
        while (!\u26032.isEmpty()) {
            int n3 = (Integer)\u26032.poll();
            this.a(n3, enumSet);
            for (cq cq2 : cq.values()) {
                int n4 = this.a(n3, cq2);
                if (n4 < 0 || this.d.get(n4)) continue;
                this.d.set(n4, true);
                \u26032.add(nl.a(n4));
            }
        }
        return enumSet;
    }

    private void a(int n2, Set<cq> set) {
        int n3 = n2 >> 0 & 0xF;
        if (n3 == 0) {
            set.add(cq.e);
        } else if (n3 == 15) {
            set.add(cq.f);
        }
        \u2603 = n2 >> 8 & 0xF;
        if (\u2603 == 0) {
            set.add(cq.a);
        } else if (\u2603 == 15) {
            set.add(cq.b);
        }
        \u2603 = n2 >> 4 & 0xF;
        if (\u2603 == 0) {
            set.add(cq.c);
        } else if (\u2603 == 15) {
            set.add(cq.d);
        }
    }

    private int a(int n2, cq cq2) {
        switch (cq2) {
            case a: {
                if ((n2 >> 8 & 0xF) == 0) {
                    return -1;
                }
                return n2 - c;
            }
            case b: {
                if ((n2 >> 8 & 0xF) == 15) {
                    return -1;
                }
                return n2 + c;
            }
            case c: {
                if ((n2 >> 4 & 0xF) == 0) {
                    return -1;
                }
                return n2 - b;
            }
            case d: {
                if ((n2 >> 4 & 0xF) == 15) {
                    return -1;
                }
                return n2 + b;
            }
            case e: {
                if ((n2 >> 0 & 0xF) == 0) {
                    return -1;
                }
                return n2 - a;
            }
            case f: {
                if ((n2 >> 0 & 0xF) == 15) {
                    return -1;
                }
                return n2 + a;
            }
        }
        return -1;
    }

    static {
        boolean bl2 = false;
        int \u26032 = 15;
        int \u26033 = 0;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    if (i2 != 0 && i2 != 15 && \u2603 != 0 && \u2603 != 15 && \u2603 != 0 && \u2603 != 15) continue;
                    bhw.e[\u26033++] = bhw.a(i2, \u2603, \u2603);
                }
            }
        }
    }
}

