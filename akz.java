/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;

public class akz
extends akw {
    private final adc a = new adc(){

        @Override
        public cj c() {
            return akz.this.c;
        }

        @Override
        public aui d() {
            return new aui((double)akz.this.c.n() + 0.5, (double)akz.this.c.o() + 0.5, (double)akz.this.c.p() + 0.5);
        }

        @Override
        public adm e() {
            return akz.this.z();
        }

        @Override
        public void a(String string) {
            super.a(string);
            akz.this.p_();
        }

        @Override
        public void h() {
            akz.this.z().h(akz.this.c);
        }

        @Override
        public int i() {
            return 0;
        }

        @Override
        public void a(ByteBuf byteBuf) {
            byteBuf.writeInt(akz.this.c.n());
            byteBuf.writeInt(akz.this.c.o());
            byteBuf.writeInt(akz.this.c.p());
        }

        @Override
        public pk f() {
            return null;
        }
    };

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        this.a.a(dn2);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a.b(dn2);
    }

    @Override
    public ff y_() {
        dn dn2 = new dn();
        this.b(dn2);
        return new ft(this.c, 2, dn2);
    }

    @Override
    public boolean F() {
        return true;
    }

    public adc b() {
        return this.a;
    }

    public n c() {
        return this.a.n();
    }
}

