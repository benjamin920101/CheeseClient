/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.Collection;

public class gu
implements ff<fj> {
    private int a;
    private byte b;
    private atf[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;

    public gu() {
    }

    public gu(int n2, byte by, Collection<atf> collection, byte[] byArray, int n3, int n4, int n5, int n6) {
        this.a = n2;
        this.b = by;
        this.c = collection.toArray(new atf[collection.size()]);
        this.d = n3;
        this.e = n4;
        this.f = n5;
        this.g = n6;
        this.h = new byte[n5 * n6];
        for (\u2603 = 0; \u2603 < n5; ++\u2603) {
            for (\u2603 = 0; \u2603 < n6; ++\u2603) {
                this.h[\u2603 + \u2603 * n5] = byArray[n3 + \u2603 + (n4 + \u2603) * 128];
            }
        }
    }

    @Override
    public void a(em em22) throws IOException {
        em em22;
        this.a = em22.e();
        this.b = em22.readByte();
        this.c = new atf[em22.e()];
        for (int i2 = 0; i2 < this.c.length; ++i2) {
            short s2 = em22.readByte();
            this.c[i2] = new atf((byte)(s2 >> 4 & 0xF), em22.readByte(), em22.readByte(), (byte)(s2 & 0xF));
        }
        this.f = em22.readUnsignedByte();
        if (this.f > 0) {
            this.g = em22.readUnsignedByte();
            this.d = em22.readUnsignedByte();
            this.e = em22.readUnsignedByte();
            this.h = em22.a();
        }
    }

    @Override
    public void b(em em22) throws IOException {
        em em22;
        em22.b(this.a);
        em22.writeByte(this.b);
        em22.b(this.c.length);
        for (atf atf2 : this.c) {
            em22.writeByte((atf2.a() & 0xF) << 4 | atf2.d() & 0xF);
            em22.writeByte(atf2.b());
            em22.writeByte(atf2.c());
        }
        em22.writeByte(this.f);
        if (this.f > 0) {
            em22.writeByte(this.g);
            em22.writeByte(this.d);
            em22.writeByte(this.e);
            em22.a(this.h);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    @Override
    public void a(atg atg2) {
        int n2;
        atg2.e = this.b;
        atg2.h.clear();
        for (n2 = 0; n2 < this.c.length; ++n2) {
            atf atf2 = this.c[n2];
            atg2.h.put("icon-" + n2, atf2);
        }
        for (n2 = 0; n2 < this.f; ++n2) {
            for (\u2603 = 0; \u2603 < this.g; ++\u2603) {
                atg2.f[this.d + n2 + (this.e + \u2603) * 128] = this.h[n2 + \u2603 * this.f];
            }
        }
    }
}

