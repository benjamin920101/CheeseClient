/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gv
implements ff<fj> {
    protected int a;
    protected byte b;
    protected byte c;
    protected byte d;
    protected byte e;
    protected byte f;
    protected boolean g;
    protected boolean h;

    public gv() {
    }

    public gv(int n2) {
        this.a = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public String toString() {
        return "Entity_" + super.toString();
    }

    public pk a(adm adm2) {
        return adm2.a(this.a);
    }

    public byte a() {
        return this.b;
    }

    public byte b() {
        return this.c;
    }

    public byte c() {
        return this.d;
    }

    public byte d() {
        return this.e;
    }

    public byte e() {
        return this.f;
    }

    public boolean f() {
        return this.h;
    }

    public boolean g() {
        return this.g;
    }

    public static class c
    extends gv {
        public c() {
            this.h = true;
        }

        public c(int n2, byte by, byte by2, boolean bl2) {
            super(n2);
            this.e = by;
            this.f = by2;
            this.h = true;
            this.g = bl2;
        }

        @Override
        public void a(em em2) throws IOException {
            super.a(em2);
            this.e = em2.readByte();
            this.f = em2.readByte();
            this.g = em2.readBoolean();
        }

        @Override
        public void b(em em2) throws IOException {
            super.b(em2);
            em2.writeByte(this.e);
            em2.writeByte(this.f);
            em2.writeBoolean(this.g);
        }
    }

    public static class a
    extends gv {
        public a() {
        }

        public a(int n2, byte by, byte by2, byte by3, boolean bl2) {
            super(n2);
            this.b = by;
            this.c = by2;
            this.d = by3;
            this.g = bl2;
        }

        @Override
        public void a(em em2) throws IOException {
            super.a(em2);
            this.b = em2.readByte();
            this.c = em2.readByte();
            this.d = em2.readByte();
            this.g = em2.readBoolean();
        }

        @Override
        public void b(em em2) throws IOException {
            super.b(em2);
            em2.writeByte(this.b);
            em2.writeByte(this.c);
            em2.writeByte(this.d);
            em2.writeBoolean(this.g);
        }
    }

    public static class b
    extends gv {
        public b() {
            this.h = true;
        }

        public b(int n2, byte by, byte by2, byte by3, byte by4, byte by5, boolean bl2) {
            super(n2);
            this.b = by;
            this.c = by2;
            this.d = by3;
            this.e = by4;
            this.f = by5;
            this.g = bl2;
            this.h = true;
        }

        @Override
        public void a(em em2) throws IOException {
            super.a(em2);
            this.b = em2.readByte();
            this.c = em2.readByte();
            this.d = em2.readByte();
            this.e = em2.readByte();
            this.f = em2.readByte();
            this.g = em2.readBoolean();
        }

        @Override
        public void b(em em2) throws IOException {
            super.b(em2);
            em2.writeByte(this.b);
            em2.writeByte(this.c);
            em2.writeByte(this.d);
            em2.writeByte(this.e);
            em2.writeByte(this.f);
            em2.writeBoolean(this.g);
        }
    }
}

