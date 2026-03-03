/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bx
extends i {
    @Override
    public String c() {
        return "worldborder";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.worldborder.usage";
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        String[] stringArray2;
        if (stringArray2.length < 1) {
            throw new cf("commands.worldborder.usage", new Object[0]);
        }
        ams ams2 = this.d();
        if (stringArray2[0].equals("set")) {
            if (stringArray2.length != 2 && stringArray2.length != 3) {
                throw new cf("commands.worldborder.set.usage", new Object[0]);
            }
            double d2 = ams2.j();
            \u2603 = bx.a(stringArray2[1], 1.0, 6.0E7);
            long l2 = \u2603 = stringArray2.length > 2 ? bx.a(stringArray2[2], 0L, 9223372036854775L) * 1000L : 0L;
            if (\u2603 > 0L) {
                ams2.a(d2, \u2603, \u2603);
                if (d2 > \u2603) {
                    bx.a(m2, (k)this, "commands.worldborder.setSlowly.shrink.success", String.format("%.1f", \u2603), String.format("%.1f", d2), Long.toString(\u2603 / 1000L));
                } else {
                    bx.a(m2, (k)this, "commands.worldborder.setSlowly.grow.success", String.format("%.1f", \u2603), String.format("%.1f", d2), Long.toString(\u2603 / 1000L));
                }
            } else {
                ams2.a(\u2603);
                bx.a(m2, (k)this, "commands.worldborder.set.success", String.format("%.1f", \u2603), String.format("%.1f", d2));
            }
        } else if (stringArray2[0].equals("add")) {
            if (stringArray2.length != 2 && stringArray2.length != 3) {
                throw new cf("commands.worldborder.add.usage", new Object[0]);
            }
            double d3 = ams2.h();
            \u2603 = d3 + bx.a(stringArray2[1], -d3, 6.0E7 - d3);
            long \u26032 = ams2.i() + (stringArray2.length > 2 ? bx.a(stringArray2[2], 0L, 9223372036854775L) * 1000L : 0L);
            if (\u26032 > 0L) {
                ams2.a(d3, \u2603, \u26032);
                if (d3 > \u2603) {
                    bx.a(m2, (k)this, "commands.worldborder.setSlowly.shrink.success", String.format("%.1f", \u2603), String.format("%.1f", d3), Long.toString(\u26032 / 1000L));
                } else {
                    bx.a(m2, (k)this, "commands.worldborder.setSlowly.grow.success", String.format("%.1f", \u2603), String.format("%.1f", d3), Long.toString(\u26032 / 1000L));
                }
            } else {
                ams2.a(\u2603);
                bx.a(m2, (k)this, "commands.worldborder.set.success", String.format("%.1f", \u2603), String.format("%.1f", d3));
            }
        } else if (stringArray2[0].equals("center")) {
            if (stringArray2.length != 3) {
                throw new cf("commands.worldborder.center.usage", new Object[0]);
            }
            cj \u26033 = m2.c();
            \u2603 = bx.b((double)\u26033.n() + 0.5, stringArray2[1], true);
            \u2603 = bx.b((double)\u26033.p() + 0.5, stringArray2[2], true);
            ams2.c(\u2603, \u2603);
            bx.a(m2, (k)this, "commands.worldborder.center.success", \u2603, \u2603);
        } else if (stringArray2[0].equals("damage")) {
            if (stringArray2.length < 2) {
                throw new cf("commands.worldborder.damage.usage", new Object[0]);
            }
            if (stringArray2[1].equals("buffer")) {
                if (stringArray2.length != 3) {
                    throw new cf("commands.worldborder.damage.buffer.usage", new Object[0]);
                }
                \u2603 = bx.a(stringArray2[2], 0.0);
                \u2603 = ams2.m();
                ams2.b(\u2603);
                bx.a(m2, (k)this, "commands.worldborder.damage.buffer.success", String.format("%.1f", \u2603), String.format("%.1f", \u2603));
            } else if (stringArray2[1].equals("amount")) {
                if (stringArray2.length != 3) {
                    throw new cf("commands.worldborder.damage.amount.usage", new Object[0]);
                }
                \u2603 = bx.a(stringArray2[2], 0.0);
                \u2603 = ams2.n();
                ams2.c(\u2603);
                bx.a(m2, (k)this, "commands.worldborder.damage.amount.success", String.format("%.2f", \u2603), String.format("%.2f", \u2603));
            }
        } else if (stringArray2[0].equals("warning")) {
            if (stringArray2.length < 2) {
                throw new cf("commands.worldborder.warning.usage", new Object[0]);
            }
            int \u26034 = bx.a(stringArray2[2], 0);
            if (stringArray2[1].equals("time")) {
                if (stringArray2.length != 3) {
                    throw new cf("commands.worldborder.warning.time.usage", new Object[0]);
                }
                int n2 = ams2.p();
                ams2.b(\u26034);
                bx.a(m2, (k)this, "commands.worldborder.warning.time.success", \u26034, n2);
            } else if (stringArray2[1].equals("distance")) {
                if (stringArray2.length != 3) {
                    throw new cf("commands.worldborder.warning.distance.usage", new Object[0]);
                }
                int n3 = ams2.q();
                ams2.c(\u26034);
                bx.a(m2, (k)this, "commands.worldborder.warning.distance.success", \u26034, n3);
            }
        } else if (stringArray2[0].equals("get")) {
            double d4 = ams2.h();
            m2.a(n.a.e, ns.c(d4 + 0.5));
            m2.a(new fb("commands.worldborder.get.success", String.format("%.0f", d4)));
        } else {
            throw new cf("commands.worldborder.usage", new Object[0]);
        }
    }

    protected ams d() {
        return MinecraftServer.N().d[0].af();
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bx.a(stringArray, "set", "center", "damage", "warning", "add", "get");
        }
        if (stringArray.length == 2 && stringArray[0].equals("damage")) {
            return bx.a(stringArray, "buffer", "amount");
        }
        if (stringArray.length >= 2 && stringArray.length <= 3 && stringArray[0].equals("center")) {
            return bx.b(stringArray, 1, cj2);
        }
        if (stringArray.length == 2 && stringArray[0].equals("warning")) {
            return bx.a(stringArray, "time", "distance");
        }
        return null;
    }
}

