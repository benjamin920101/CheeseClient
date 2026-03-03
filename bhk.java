/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
import java.util.UUID;

public class bhk
extends bhd<alo> {
    private static final jy d = new jy("textures/entity/skeleton/skeleton.png");
    private static final jy e = new jy("textures/entity/skeleton/wither_skeleton.png");
    private static final jy f = new jy("textures/entity/zombie/zombie.png");
    private static final jy g = new jy("textures/entity/creeper/creeper.png");
    public static bhk c;
    private final bbz h = new bbz(0, 0, 64, 32);
    private final bbz i = new bbi();

    @Override
    public void a(alo alo2, double d2, double d3, double d4, float f2, int n2) {
        cq cq2 = cq.a(alo2.u() & 7);
        this.a((float)d2, (float)d3, (float)d4, cq2, (float)(alo2.d() * 360) / 16.0f, alo2.c(), alo2.b(), n2);
    }

    @Override
    public void a(bhc bhc2) {
        super.a(bhc2);
        c = this;
    }

    public void a(float f22, float f3, float f4, cq cq2, float f52, int n2, GameProfile gameProfile2, int n3) {
        float f22;
        bbz bbz2 = this.h;
        if (n3 >= 0) {
            this.a(a[n3]);
            bfl.n(5890);
            bfl.E();
            bfl.a(4.0f, 2.0f, 1.0f);
            bfl.b(0.0625f, 0.0625f, 0.0625f);
            bfl.n(5888);
        } else {
            switch (n2) {
                default: {
                    this.a(d);
                    break;
                }
                case 1: {
                    this.a(e);
                    break;
                }
                case 2: {
                    this.a(f);
                    bbz2 = this.i;
                    break;
                }
                case 3: {
                    bbz2 = this.i;
                    jy \u26034 = bmz.a();
                    if (gameProfile2 != null) {
                        ave ave2 = ave.A();
                        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> \u26032 = ave2.ab().a(gameProfile2);
                        if (\u26032.containsKey((Object)MinecraftProfileTexture.Type.SKIN)) {
                            \u26034 = ave2.ab().a(\u26032.get((Object)MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
                        } else {
                            GameProfile gameProfile2;
                            UUID \u26033 = wn.a(gameProfile2);
                            \u26034 = bmz.a(\u26033);
                        }
                    }
                    this.a(\u26034);
                    break;
                }
                case 4: {
                    this.a(g);
                }
            }
        }
        bfl.E();
        bfl.p();
        if (cq2 != cq.b) {
            switch (cq2) {
                case c: {
                    bfl.b(f22 + 0.5f, f3 + 0.25f, f4 + 0.74f);
                    break;
                }
                case d: {
                    bfl.b(f22 + 0.5f, f3 + 0.25f, f4 + 0.26f);
                    float f52 = 180.0f;
                    break;
                }
                case e: {
                    bfl.b(f22 + 0.74f, f3 + 0.25f, f4 + 0.5f);
                    f52 = 270.0f;
                    break;
                }
                default: {
                    bfl.b(f22 + 0.26f, f3 + 0.25f, f4 + 0.5f);
                    f52 = 90.0f;
                    break;
                }
            }
        } else {
            bfl.b(f22 + 0.5f, f3, f4 + 0.5f);
        }
        float f6 = 0.0625f;
        bfl.B();
        bfl.a(-1.0f, -1.0f, 1.0f);
        bfl.d();
        ((bbo)bbz2).a(null, 0.0f, 0.0f, 0.0f, f52, 0.0f, f6);
        bfl.F();
        if (n3 >= 0) {
            bfl.n(5890);
            bfl.F();
            bfl.n(5888);
        }
    }
}

