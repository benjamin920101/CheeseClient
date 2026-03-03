/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gm
implements ff<fj> {
    public static final String[] a = new String[]{"tile.bed.notValid"};
    private int b;
    private float c;

    public gm() {
    }

    public gm(int n2, float f2) {
        this.b = n2;
        this.c = f2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.b = em2.readUnsignedByte();
        this.c = em2.readFloat();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.b);
        em2.writeFloat(this.c);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.b;
    }

    public float b() {
        return this.c;
    }
}

