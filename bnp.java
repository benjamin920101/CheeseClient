/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class bnp {
    private static final ExecutorService a = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    private final bmj b;
    private final File c;
    private final MinecraftSessionService d;
    private final LoadingCache<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> e;

    public bnp(bmj bmj2, File file, MinecraftSessionService minecraftSessionService) {
        this.b = bmj2;
        this.c = file;
        this.d = minecraftSessionService;
        this.e = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new CacheLoader<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>>(){

            public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> a(GameProfile gameProfile) throws Exception {
                return ave.A().aa().getTextures(gameProfile, false);
            }

            @Override
            public /* synthetic */ Object load(Object object) throws Exception {
                return this.a((GameProfile)object);
            }
        });
    }

    public jy a(MinecraftProfileTexture minecraftProfileTexture, MinecraftProfileTexture.Type type) {
        return this.a(minecraftProfileTexture, type, null);
    }

    public jy a(final MinecraftProfileTexture minecraftProfileTexture, final MinecraftProfileTexture.Type type, final a a2) {
        final jy jy2 = new jy("skins/" + minecraftProfileTexture.getHash());
        bmk \u26032 = this.b.b(jy2);
        if (\u26032 != null) {
            if (a2 != null) {
                a2.a(type, jy2, minecraftProfileTexture);
            }
        } else {
            File file = new File(this.c, minecraftProfileTexture.getHash().length() > 2 ? minecraftProfileTexture.getHash().substring(0, 2) : "xx");
            \u2603 = new File(file, minecraftProfileTexture.getHash());
            final bfs \u26033 = type == MinecraftProfileTexture.Type.SKIN ? new bfs() : null;
            bma \u26034 = new bma(\u2603, minecraftProfileTexture.getUrl(), bmz.a(), new bfm(){

                @Override
                public BufferedImage a(BufferedImage bufferedImage) {
                    if (\u26033 != null) {
                        bufferedImage = \u26033.a(bufferedImage);
                    }
                    return bufferedImage;
                }

                @Override
                public void a() {
                    if (\u26033 != null) {
                        \u26033.a();
                    }
                    if (a2 != null) {
                        a2.a(type, jy2, minecraftProfileTexture);
                    }
                }
            });
            this.b.a(jy2, \u26034);
        }
        return jy2;
    }

    public void a(final GameProfile gameProfile, final a a2, final boolean bl2) {
        a.submit(new Runnable(){

            @Override
            public void run() {
                final HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture> hashMap = Maps.newHashMap();
                try {
                    hashMap.putAll(bnp.this.d.getTextures(gameProfile, bl2));
                }
                catch (InsecureTextureException insecureTextureException) {
                    // empty catch block
                }
                if (hashMap.isEmpty() && gameProfile.getId().equals(ave.A().L().e().getId())) {
                    gameProfile.getProperties().clear();
                    gameProfile.getProperties().putAll(ave.A().N());
                    hashMap.putAll(bnp.this.d.getTextures(gameProfile, false));
                }
                ave.A().a(new Runnable(){

                    @Override
                    public void run() {
                        if (hashMap.containsKey((Object)MinecraftProfileTexture.Type.SKIN)) {
                            bnp.this.a((MinecraftProfileTexture)hashMap.get((Object)MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN, a2);
                        }
                        if (hashMap.containsKey((Object)MinecraftProfileTexture.Type.CAPE)) {
                            bnp.this.a((MinecraftProfileTexture)hashMap.get((Object)MinecraftProfileTexture.Type.CAPE), MinecraftProfileTexture.Type.CAPE, a2);
                        }
                    }
                });
            }
        });
    }

    public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> a(GameProfile gameProfile) {
        return this.e.getUnchecked(gameProfile);
    }

    public static interface a {
        public void a(MinecraftProfileTexture.Type var1, jy var2, MinecraftProfileTexture var3);
    }
}

