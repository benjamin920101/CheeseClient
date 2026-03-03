/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class eb {
    public static final String[] a = new String[]{"END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"};

    abstract void a(DataOutput var1) throws IOException;

    abstract void a(DataInput var1, int var2, dw var3) throws IOException;

    public abstract String toString();

    public abstract byte a();

    protected eb() {
    }

    protected static eb a(byte by) {
        switch (by) {
            case 0: {
                return new dq();
            }
            case 1: {
                return new dm();
            }
            case 2: {
                return new dz();
            }
            case 3: {
                return new dt();
            }
            case 4: {
                return new dv();
            }
            case 5: {
                return new dr();
            }
            case 6: {
                return new dp();
            }
            case 7: {
                return new dl();
            }
            case 11: {
                return new ds();
            }
            case 8: {
                return new ea();
            }
            case 9: {
                return new du();
            }
            case 10: {
                return new dn();
            }
        }
        return null;
    }

    public abstract eb b();

    public boolean c_() {
        return false;
    }

    public boolean equals(Object object) {
        if (!(object instanceof eb)) {
            return false;
        }
        eb eb2 = (eb)object;
        return this.a() == eb2.a();
    }

    public int hashCode() {
        return this.a();
    }

    protected String a_() {
        return this.toString();
    }

    public static abstract class a
    extends eb {
        protected a() {
        }

        public abstract long c();

        public abstract int d();

        public abstract short e();

        public abstract byte f();

        public abstract double g();

        public abstract float h();
    }
}

