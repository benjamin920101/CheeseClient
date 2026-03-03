/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class bd
extends j
implements h {
    public bd() {
        this.a(new br());
        this.a(new ah());
        this.a(new ag());
        this.a(new y());
        this.a(new am());
        this.a(new bt());
        this.a(new bv());
        this.a(new ae());
        this.a(new bn());
        this.a(new aj());
        this.a(new ax());
        this.a(new bk());
        this.a(new z());
        this.a(new ab());
        this.a(new au());
        this.a(new aa());
        this.a(new bi());
        this.a(new ak());
        this.a(new x());
        this.a(new ap());
        this.a(new bb());
        this.a(new bh());
        this.a(new bf());
        this.a(new ai());
        this.a(new t());
        this.a(new bq());
        this.a(new bj());
        this.a(new av());
        this.a(new bc());
        this.a(new ad());
        this.a(new bu());
        this.a(new p());
        this.a(new bm());
        this.a(new be());
        this.a(new af());
        this.a(new u());
        this.a(new v());
        this.a(new s());
        this.a(new bp());
        this.a(new aq());
        this.a(new bx());
        this.a(new bs());
        this.a(new ac());
        if (MinecraftServer.N().ae()) {
            this.a(new ar());
            this.a(new w());
            this.a(new bl());
            this.a(new ay());
            this.a(new az());
            this.a(new ba());
            this.a(new q());
            this.a(new as());
            this.a(new r());
            this.a(new an());
            this.a(new at());
            this.a(new al());
            this.a(new ao());
            this.a(new bw());
            this.a(new bg());
        } else {
            this.a(new aw());
        }
        i.a(this);
    }

    @Override
    public void a(m m22, k k2, int n2, String string, Object ... objectArray) {
        boolean bl2 = true;
        MinecraftServer \u26032 = MinecraftServer.N();
        if (!m22.u_()) {
            bl2 = false;
        }
        fb \u26033 = new fb("chat.type.admin", m22.e_(), new fb(string, objectArray));
        \u26033.b().a(a.h);
        \u26033.b().b(true);
        if (bl2) {
            for (wn wn2 : \u26032.ap().v()) {
                boolean bl3;
                if (wn2 == m22 || !\u26032.ap().h(wn2.cd()) || !k2.a(m22)) continue;
                boolean bl32 = m22 instanceof MinecraftServer && MinecraftServer.N().r();
                boolean bl4 = bl3 = m22 instanceof mj && MinecraftServer.N().q();
                if (!bl32 && !bl3 && (m22 instanceof mj || m22 instanceof MinecraftServer)) continue;
                wn2.a(\u26033);
            }
        }
        if (m22 != \u26032 && \u26032.d[0].Q().b("logAdminCommands")) {
            \u26032.a(\u26033);
        }
        boolean \u26034 = \u26032.d[0].Q().b("sendCommandFeedback");
        if (m22 instanceof adc) {
            \u26034 = ((adc)m22).m();
        }
        if ((n2 & 1) != 1 && \u26034 || m22 instanceof MinecraftServer) {
            m22.a(new fb(string, objectArray));
        }
    }
}

