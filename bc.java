/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class bc
extends i {
    @Override
    public String c() {
        return "scoreboard";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.scoreboard.usage";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (this.b(m2, stringArray)) {
            return;
        }
        if (stringArray.length < 1) {
            throw new cf("commands.scoreboard.usage", new Object[0]);
        }
        if (stringArray[0].equalsIgnoreCase("objectives")) {
            if (stringArray.length == 1) {
                throw new cf("commands.scoreboard.objectives.usage", new Object[0]);
            }
            if (stringArray[1].equalsIgnoreCase("list")) {
                this.d(m2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("add")) {
                if (stringArray.length < 4) throw new cf("commands.scoreboard.objectives.add.usage", new Object[0]);
                this.b(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("remove")) {
                if (stringArray.length != 3) throw new cf("commands.scoreboard.objectives.remove.usage", new Object[0]);
                this.h(m2, stringArray[2]);
                return;
            } else {
                if (!stringArray[1].equalsIgnoreCase("setdisplay")) throw new cf("commands.scoreboard.objectives.usage", new Object[0]);
                if (stringArray.length != 3 && stringArray.length != 4) throw new cf("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                this.j(m2, stringArray, 2);
            }
            return;
        } else if (stringArray[0].equalsIgnoreCase("players")) {
            if (stringArray.length == 1) {
                throw new cf("commands.scoreboard.players.usage", new Object[0]);
            }
            if (stringArray[1].equalsIgnoreCase("list")) {
                if (stringArray.length > 3) throw new cf("commands.scoreboard.players.list.usage", new Object[0]);
                this.k(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("add")) {
                if (stringArray.length < 5) throw new cf("commands.scoreboard.players.add.usage", new Object[0]);
                this.l(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("remove")) {
                if (stringArray.length < 5) throw new cf("commands.scoreboard.players.remove.usage", new Object[0]);
                this.l(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("set")) {
                if (stringArray.length < 5) throw new cf("commands.scoreboard.players.set.usage", new Object[0]);
                this.l(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("reset")) {
                if (stringArray.length != 3 && stringArray.length != 4) throw new cf("commands.scoreboard.players.reset.usage", new Object[0]);
                this.m(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("enable")) {
                if (stringArray.length != 4) throw new cf("commands.scoreboard.players.enable.usage", new Object[0]);
                this.n(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("test")) {
                if (stringArray.length != 5 && stringArray.length != 6) throw new cf("commands.scoreboard.players.test.usage", new Object[0]);
                this.o(m2, stringArray, 2);
                return;
            } else {
                if (!stringArray[1].equalsIgnoreCase("operation")) throw new cf("commands.scoreboard.players.usage", new Object[0]);
                if (stringArray.length != 7) throw new cf("commands.scoreboard.players.operation.usage", new Object[0]);
                this.p(m2, stringArray, 2);
            }
            return;
        } else {
            if (!stringArray[0].equalsIgnoreCase("teams")) throw new cf("commands.scoreboard.usage", new Object[0]);
            if (stringArray.length == 1) {
                throw new cf("commands.scoreboard.teams.usage", new Object[0]);
            }
            if (stringArray[1].equalsIgnoreCase("list")) {
                if (stringArray.length > 3) throw new cf("commands.scoreboard.teams.list.usage", new Object[0]);
                this.f(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("add")) {
                if (stringArray.length < 3) throw new cf("commands.scoreboard.teams.add.usage", new Object[0]);
                this.c(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("remove")) {
                if (stringArray.length != 3) throw new cf("commands.scoreboard.teams.remove.usage", new Object[0]);
                this.e(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("empty")) {
                if (stringArray.length != 3) throw new cf("commands.scoreboard.teams.empty.usage", new Object[0]);
                this.i(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("join")) {
                if (stringArray.length < 4 && (stringArray.length != 3 || !(m2 instanceof wn))) throw new cf("commands.scoreboard.teams.join.usage", new Object[0]);
                this.g(m2, stringArray, 2);
                return;
            } else if (stringArray[1].equalsIgnoreCase("leave")) {
                if (stringArray.length < 3 && !(m2 instanceof wn)) throw new cf("commands.scoreboard.teams.leave.usage", new Object[0]);
                this.h(m2, stringArray, 2);
                return;
            } else {
                if (!stringArray[1].equalsIgnoreCase("option")) throw new cf("commands.scoreboard.teams.usage", new Object[0]);
                if (stringArray.length != 4 && stringArray.length != 5) throw new cf("commands.scoreboard.teams.option.usage", new Object[0]);
                this.d(m2, stringArray, 2);
            }
        }
    }

    private boolean b(m m22, String[] stringArray) throws bz {
        m m22;
        int n2 = -1;
        for (\u2603 = 0; \u2603 < stringArray.length; ++\u2603) {
            if (!this.b(stringArray, \u2603) || !"*".equals(stringArray[\u2603])) continue;
            if (n2 < 0) {
                n2 = \u2603;
                continue;
            }
            throw new bz("commands.scoreboard.noMultiWildcard", new Object[0]);
        }
        if (n2 < 0) {
            return false;
        }
        ArrayList<String> \u26032 = Lists.newArrayList(this.d().d());
        String \u26033 = stringArray[n2];
        ArrayList<String> \u26034 = Lists.newArrayList();
        Iterator \u26035 = \u26032.iterator();
        while (\u26035.hasNext()) {
            stringArray[n2] = \u2603 = (String)\u26035.next();
            try {
                this.a(m22, stringArray);
                \u26034.add(\u2603);
            }
            catch (bz bz2) {
                fb fb2 = new fb(bz2.getMessage(), bz2.a());
                fb2.b().a(a.m);
                m22.a(fb2);
            }
        }
        stringArray[n2] = \u26033;
        m22.a(n.a.c, \u26034.size());
        if (\u26034.size() == 0) {
            throw new cf("commands.scoreboard.allMatchesFailed", new Object[0]);
        }
        return true;
    }

    protected auo d() {
        return MinecraftServer.N().a(0).Z();
    }

    protected auk a(String string, boolean bl2) throws bz {
        auo auo2 = this.d();
        auk \u26032 = auo2.b(string);
        if (\u26032 == null) {
            throw new bz("commands.scoreboard.objectiveNotFound", string);
        }
        if (bl2 && \u26032.c().b()) {
            throw new bz("commands.scoreboard.objectiveReadOnly", string);
        }
        return \u26032;
    }

    protected aul e(String string) throws bz {
        auo auo2 = this.d();
        aul \u26032 = auo2.d(string);
        if (\u26032 == null) {
            throw new bz("commands.scoreboard.teamNotFound", string);
        }
        return \u26032;
    }

    protected void b(m m2, String[] stringArray, int n2) throws bz {
        String string = stringArray[n2++];
        \u2603 = stringArray[n2++];
        auo \u26032 = this.d();
        auu \u26033 = auu.a.get(\u2603);
        if (\u26033 == null) {
            throw new cf("commands.scoreboard.objectives.add.wrongType", \u2603);
        }
        if (\u26032.b(string) != null) {
            throw new bz("commands.scoreboard.objectives.add.alreadyExists", string);
        }
        if (string.length() > 16) {
            throw new cc("commands.scoreboard.objectives.add.tooLong", string, 16);
        }
        if (string.length() == 0) {
            throw new cf("commands.scoreboard.objectives.add.usage", new Object[0]);
        }
        if (stringArray.length > n2) {
            \u2603 = bc.a(m2, stringArray, n2).c();
            if (\u2603.length() > 32) {
                throw new cc("commands.scoreboard.objectives.add.displayTooLong", \u2603, 32);
            }
            if (\u2603.length() > 0) {
                \u26032.a(string, \u26033).a(\u2603);
            } else {
                \u26032.a(string, \u26033);
            }
        } else {
            \u26032.a(string, \u26033);
        }
        bc.a(m2, (k)this, "commands.scoreboard.objectives.add.success", string);
    }

    protected void c(m m2, String[] stringArray, int n2) throws bz {
        String string = stringArray[n2++];
        auo \u26032 = this.d();
        if (\u26032.d(string) != null) {
            throw new bz("commands.scoreboard.teams.add.alreadyExists", string);
        }
        if (string.length() > 16) {
            throw new cc("commands.scoreboard.teams.add.tooLong", string, 16);
        }
        if (string.length() == 0) {
            throw new cf("commands.scoreboard.teams.add.usage", new Object[0]);
        }
        if (stringArray.length > n2) {
            \u2603 = bc.a(m2, stringArray, n2).c();
            if (\u2603.length() > 32) {
                throw new cc("commands.scoreboard.teams.add.displayTooLong", \u2603, 32);
            }
            if (\u2603.length() > 0) {
                \u26032.e(string).a(\u2603);
            } else {
                \u26032.e(string);
            }
        } else {
            \u26032.e(string);
        }
        bc.a(m2, (k)this, "commands.scoreboard.teams.add.success", string);
    }

    protected void d(m m22, String[] stringArray, int n2) throws bz {
        m m22;
        String string;
        if ((\u2603 = this.e(stringArray[n2++])) == null) {
            return;
        }
        if (!((string = stringArray[n2++].toLowerCase()).equalsIgnoreCase("color") || string.equalsIgnoreCase("friendlyfire") || string.equalsIgnoreCase("seeFriendlyInvisibles") || string.equalsIgnoreCase("nametagVisibility") || string.equalsIgnoreCase("deathMessageVisibility"))) {
            throw new cf("commands.scoreboard.teams.option.usage", new Object[0]);
        }
        if (stringArray.length == 4) {
            if (string.equalsIgnoreCase("color")) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(a.a(true, false)));
            }
            if (string.equalsIgnoreCase("friendlyfire") || string.equalsIgnoreCase("seeFriendlyInvisibles")) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(Arrays.asList("true", "false")));
            }
            if (string.equalsIgnoreCase("nametagVisibility") || string.equalsIgnoreCase("deathMessageVisibility")) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(auq.a.a()));
            }
            throw new cf("commands.scoreboard.teams.option.usage", new Object[0]);
        }
        String string2 = stringArray[n2];
        if (string.equalsIgnoreCase("color")) {
            a a2 = a.b(string2);
            if (a2 == null || a2.c()) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(a.a(true, false)));
            }
            \u2603.a(a2);
            \u2603.b(a2.toString());
            \u2603.c(a.v.toString());
        } else if (string.equalsIgnoreCase("friendlyfire")) {
            if (!string2.equalsIgnoreCase("true") && !string2.equalsIgnoreCase("false")) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(Arrays.asList("true", "false")));
            }
            \u2603.a(string2.equalsIgnoreCase("true"));
        } else if (string.equalsIgnoreCase("seeFriendlyInvisibles")) {
            if (!string2.equalsIgnoreCase("true") && !string2.equalsIgnoreCase("false")) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(Arrays.asList("true", "false")));
            }
            \u2603.b(string2.equalsIgnoreCase("true"));
        } else if (string.equalsIgnoreCase("nametagVisibility")) {
            auq.a a3 = auq.a.a(string2);
            if (a3 == null) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(auq.a.a()));
            }
            \u2603.a(a3);
        } else if (string.equalsIgnoreCase("deathMessageVisibility")) {
            auq.a a4 = auq.a.a(string2);
            if (a4 == null) {
                throw new cf("commands.scoreboard.teams.option.noValue", string, bc.a(auq.a.a()));
            }
            \u2603.b(a4);
        }
        bc.a(m22, (k)this, "commands.scoreboard.teams.option.success", string, \u2603.b(), string2);
    }

    protected void e(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        aul \u26032 = this.e(stringArray[n2]);
        if (\u26032 == null) {
            return;
        }
        auo2.d(\u26032);
        bc.a(m2, (k)this, "commands.scoreboard.teams.remove.success", \u26032.b());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void f(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        if (stringArray.length > n2) {
            aul aul2 = this.e(stringArray[n2]);
            if (aul2 == null) {
                return;
            }
            Collection<String> \u26032 = aul2.d();
            m2.a(n.a.e, \u26032.size());
            if (\u26032.size() <= 0) throw new bz("commands.scoreboard.teams.list.player.empty", aul2.b());
            fb \u26033 = new fb("commands.scoreboard.teams.list.player.count", \u26032.size(), aul2.b());
            \u26033.b().a(a.c);
            m2.a(\u26033);
            m2.a(new fa(bc.a(\u26032.toArray())));
            return;
        } else {
            Collection<aul> \u26034 = auo2.g();
            m2.a(n.a.e, \u26034.size());
            if (\u26034.size() <= 0) throw new bz("commands.scoreboard.teams.list.empty", new Object[0]);
            fb \u26035 = new fb("commands.scoreboard.teams.list.count", \u26034.size());
            \u26035.b().a(a.c);
            m2.a(\u26035);
            for (aul aul3 : \u26034) {
                m2.a(new fb("commands.scoreboard.teams.list.entry", aul3.b(), aul3.c(), aul3.d().size()));
            }
        }
    }

    protected void g(m m22, String[] stringArray, int n22) throws bz {
        auo auo2 = this.d();
        String \u26032 = stringArray[n22++];
        HashSet<Object> \u26033 = Sets.newHashSet();
        HashSet<Object> \u26034 = Sets.newHashSet();
        if (m22 instanceof wn && n22 == stringArray.length) {
            String string = bc.b(m22).e_();
            if (auo2.a(string, \u26032)) {
                \u26033.add(string);
            } else {
                \u26034.add(string);
            }
        } else {
            int n22;
            while (n22 < stringArray.length) {
                Object object;
                if ((\u2603 = stringArray[n22++]).startsWith("@")) {
                    object = bc.c(m22, \u2603);
                    Iterator \u26035 = object.iterator();
                    while (\u26035.hasNext()) {
                        pk pk2 = (pk)\u26035.next();
                        String \u26036 = bc.e(m22, pk2.aK().toString());
                        if (auo2.a(\u26036, \u26032)) {
                            \u26033.add(\u26036);
                            continue;
                        }
                        \u26034.add(\u26036);
                    }
                    continue;
                }
                object = bc.e(m22, \u2603);
                if (auo2.a((String)object, \u26032)) {
                    \u26033.add(object);
                    continue;
                }
                \u26034.add(object);
            }
        }
        if (!\u26033.isEmpty()) {
            m m22;
            m22.a(n.a.c, \u26033.size());
            bc.a(m22, (k)this, "commands.scoreboard.teams.join.success", \u26033.size(), \u26032, bc.a(\u26033.toArray(new String[\u26033.size()])));
        }
        if (!\u26034.isEmpty()) {
            throw new bz("commands.scoreboard.teams.join.failure", \u26034.size(), \u26032, bc.a(\u26034.toArray(new String[\u26034.size()])));
        }
    }

    protected void h(m m22, String[] stringArray, int n22) throws bz {
        auo auo2 = this.d();
        HashSet<Object> \u26032 = Sets.newHashSet();
        HashSet<Object> \u26033 = Sets.newHashSet();
        if (m22 instanceof wn && n22 == stringArray.length) {
            String string = bc.b(m22).e_();
            if (auo2.f(string)) {
                \u26032.add(string);
            } else {
                \u26033.add(string);
            }
        } else {
            int n22;
            while (n22 < stringArray.length) {
                Object object;
                if ((\u2603 = stringArray[n22++]).startsWith("@")) {
                    object = bc.c(m22, \u2603);
                    Iterator \u26034 = object.iterator();
                    while (\u26034.hasNext()) {
                        pk pk2 = (pk)\u26034.next();
                        String \u26035 = bc.e(m22, pk2.aK().toString());
                        if (auo2.f(\u26035)) {
                            \u26032.add(\u26035);
                            continue;
                        }
                        \u26033.add(\u26035);
                    }
                    continue;
                }
                object = bc.e(m22, \u2603);
                if (auo2.f((String)object)) {
                    \u26032.add(object);
                    continue;
                }
                \u26033.add(object);
            }
        }
        if (!\u26032.isEmpty()) {
            m m22;
            m22.a(n.a.c, \u26032.size());
            bc.a(m22, (k)this, "commands.scoreboard.teams.leave.success", \u26032.size(), bc.a(\u26032.toArray(new String[\u26032.size()])));
        }
        if (!\u26033.isEmpty()) {
            throw new bz("commands.scoreboard.teams.leave.failure", \u26033.size(), bc.a(\u26033.toArray(new String[\u26033.size()])));
        }
    }

    protected void i(m m22, String[] stringArray, int n2) throws bz {
        m m22;
        auo auo2 = this.d();
        aul \u26032 = this.e(stringArray[n2]);
        if (\u26032 == null) {
            return;
        }
        ArrayList<String> \u26033 = Lists.newArrayList(\u26032.d());
        m22.a(n.a.c, \u26033.size());
        if (\u26033.isEmpty()) {
            throw new bz("commands.scoreboard.teams.empty.alreadyEmpty", \u26032.b());
        }
        for (String string : \u26033) {
            auo2.a(string, \u26032);
        }
        bc.a(m22, (k)this, "commands.scoreboard.teams.empty.success", \u26033.size(), \u26032.b());
    }

    protected void h(m m2, String string) throws bz {
        auo auo2 = this.d();
        auk \u26032 = this.a(string, false);
        auo2.k(\u26032);
        bc.a(m2, (k)this, "commands.scoreboard.objectives.remove.success", string);
    }

    protected void d(m m2) throws bz {
        auo auo2 = this.d();
        Collection<auk> \u26032 = auo2.c();
        if (\u26032.size() > 0) {
            fb fb2 = new fb("commands.scoreboard.objectives.list.count", \u26032.size());
            fb2.b().a(a.c);
            m2.a(fb2);
            for (auk auk2 : \u26032) {
                m2.a(new fb("commands.scoreboard.objectives.list.entry", auk2.b(), auk2.d(), auk2.c().a()));
            }
        } else {
            throw new bz("commands.scoreboard.objectives.list.empty", new Object[0]);
        }
    }

    protected void j(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        String \u26032 = stringArray[n2++];
        int \u26033 = auo.i(\u26032);
        auk \u26034 = null;
        if (stringArray.length == 4) {
            \u26034 = this.a(stringArray[n2], false);
        }
        if (\u26033 < 0) {
            throw new bz("commands.scoreboard.objectives.setdisplay.invalidSlot", \u26032);
        }
        auo2.a(\u26033, \u26034);
        if (\u26034 != null) {
            bc.a(m2, (k)this, "commands.scoreboard.objectives.setdisplay.successSet", auo.b(\u26033), \u26034.b());
        } else {
            bc.a(m2, (k)this, "commands.scoreboard.objectives.setdisplay.successCleared", auo.b(\u26033));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void k(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        if (stringArray.length > n2) {
            String string = bc.e(m2, stringArray[n2]);
            Map<auk, aum> \u26032 = auo2.c(string);
            m2.a(n.a.e, \u26032.size());
            if (\u26032.size() <= 0) throw new bz("commands.scoreboard.players.list.player.empty", string);
            fb \u26033 = new fb("commands.scoreboard.players.list.player.count", \u26032.size(), string);
            \u26033.b().a(a.c);
            m2.a(\u26033);
            for (aum aum2 : \u26032.values()) {
                m2.a(new fb("commands.scoreboard.players.list.player.entry", aum2.c(), aum2.d().d(), aum2.d().b()));
            }
            return;
        } else {
            Collection<String> \u26034 = auo2.d();
            m2.a(n.a.e, \u26034.size());
            if (\u26034.size() <= 0) throw new bz("commands.scoreboard.players.list.empty", new Object[0]);
            fb \u26035 = new fb("commands.scoreboard.players.list.count", \u26034.size());
            \u26035.b().a(a.c);
            m2.a(\u26035);
            m2.a(new fa(bc.a(\u26034.toArray())));
        }
    }

    protected void l(m m2, String[] stringArray, int n2) throws bz {
        Object object;
        String string = stringArray[n2 - 1];
        int \u26032 = n2;
        if ((\u2603 = bc.e(m2, stringArray[n2++])).length() > 40) {
            throw new cc("commands.scoreboard.players.name.tooLong", \u2603, 40);
        }
        auk \u26033 = this.a(stringArray[n2++], true);
        int n3 = \u2603 = string.equalsIgnoreCase("set") ? bc.a(stringArray[n2++]) : bc.a(stringArray[n2++], 0);
        if (stringArray.length > n2) {
            object = bc.b(m2, stringArray[\u26032]);
            try {
                \u2603 = ed.a(bc.a(stringArray, n2));
                dn dn2 = new dn();
                ((pk)object).e(dn2);
                if (!dy.a((eb)\u2603, dn2, true)) {
                    throw new bz("commands.scoreboard.players.set.tagMismatch", \u2603);
                }
            }
            catch (ec ec2) {
                throw new bz("commands.scoreboard.players.set.tagError", ec2.getMessage());
            }
        }
        object = this.d();
        \u2603 = ((auo)object).c(\u2603, \u26033);
        if (string.equalsIgnoreCase("set")) {
            ((aum)\u2603).c(\u2603);
        } else if (string.equalsIgnoreCase("add")) {
            ((aum)\u2603).a(\u2603);
        } else {
            ((aum)\u2603).b(\u2603);
        }
        bc.a(m2, (k)this, "commands.scoreboard.players.set.success", \u26033.b(), \u2603, ((aum)\u2603).c());
    }

    protected void m(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        String \u26032 = bc.e(m2, stringArray[n2++]);
        if (stringArray.length > n2) {
            auk auk2 = this.a(stringArray[n2++], false);
            auo2.d(\u26032, auk2);
            bc.a(m2, (k)this, "commands.scoreboard.players.resetscore.success", auk2.b(), \u26032);
        } else {
            auo2.d(\u26032, null);
            bc.a(m2, (k)this, "commands.scoreboard.players.reset.success", \u26032);
        }
    }

    protected void n(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        if ((\u2603 = bc.d(m2, stringArray[n2++])).length() > 40) {
            throw new cc("commands.scoreboard.players.name.tooLong", \u2603, 40);
        }
        auk \u26032 = this.a(stringArray[n2], false);
        if (\u26032.c() != auu.c) {
            throw new bz("commands.scoreboard.players.enable.noTrigger", \u26032.b());
        }
        aum \u26033 = auo2.c(\u2603, \u26032);
        \u26033.a(false);
        bc.a(m2, (k)this, "commands.scoreboard.players.enable.success", \u26032.b(), \u2603);
    }

    protected void o(m m2, String[] stringArray, int n2) throws bz {
        auo auo2 = this.d();
        if ((\u2603 = bc.e(m2, stringArray[n2++])).length() > 40) {
            throw new cc("commands.scoreboard.players.name.tooLong", \u2603, 40);
        }
        if (!auo2.b(\u2603, \u2603 = this.a(stringArray[n2++], false))) {
            throw new bz("commands.scoreboard.players.test.notFound", \u2603.b(), \u2603);
        }
        int \u26032 = stringArray[n2].equals("*") ? Integer.MIN_VALUE : bc.a(stringArray[n2]);
        int \u26033 = ++n2 >= stringArray.length || stringArray[n2].equals("*") ? Integer.MAX_VALUE : bc.a(stringArray[n2], \u26032);
        aum \u26034 = auo2.c(\u2603, \u2603);
        if (\u26034.c() < \u26032 || \u26034.c() > \u26033) {
            throw new bz("commands.scoreboard.players.test.failed", \u26034.c(), \u26032, \u26033);
        }
        bc.a(m2, (k)this, "commands.scoreboard.players.test.success", \u26034.c(), \u26032, \u26033);
    }

    protected void p(m m22, String[] stringArray, int n2) throws bz {
        m m22;
        auo auo2 = this.d();
        String \u26032 = bc.e(m22, stringArray[n2++]);
        auk \u26033 = this.a(stringArray[n2++], true);
        String \u26034 = stringArray[n2++];
        String \u26035 = bc.e(m22, stringArray[n2++]);
        auk \u26036 = this.a(stringArray[n2], false);
        if (\u26032.length() > 40) {
            throw new cc("commands.scoreboard.players.name.tooLong", \u26032, 40);
        }
        if (\u26035.length() > 40) {
            throw new cc("commands.scoreboard.players.name.tooLong", \u26035, 40);
        }
        aum \u26037 = auo2.c(\u26032, \u26033);
        if (!auo2.b(\u26035, \u26036)) {
            throw new bz("commands.scoreboard.players.operation.notFound", \u26036.b(), \u26035);
        }
        aum \u26038 = auo2.c(\u26035, \u26036);
        if (\u26034.equals("+=")) {
            \u26037.c(\u26037.c() + \u26038.c());
        } else if (\u26034.equals("-=")) {
            \u26037.c(\u26037.c() - \u26038.c());
        } else if (\u26034.equals("*=")) {
            \u26037.c(\u26037.c() * \u26038.c());
        } else if (\u26034.equals("/=")) {
            if (\u26038.c() != 0) {
                \u26037.c(\u26037.c() / \u26038.c());
            }
        } else if (\u26034.equals("%=")) {
            if (\u26038.c() != 0) {
                \u26037.c(\u26037.c() % \u26038.c());
            }
        } else if (\u26034.equals("=")) {
            \u26037.c(\u26038.c());
        } else if (\u26034.equals("<")) {
            \u26037.c(Math.min(\u26037.c(), \u26038.c()));
        } else if (\u26034.equals(">")) {
            \u26037.c(Math.max(\u26037.c(), \u26038.c()));
        } else if (\u26034.equals("><")) {
            int n3 = \u26037.c();
            \u26037.c(\u26038.c());
            \u26038.c(n3);
        } else {
            throw new bz("commands.scoreboard.players.operation.invalidOperation", \u26034);
        }
        bc.a(m22, (k)this, "commands.scoreboard.players.operation.success", new Object[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray2, cj cj2) {
        String[] stringArray2;
        if (stringArray2.length == 1) {
            return bc.a(stringArray2, "objectives", "players", "teams");
        }
        if (stringArray2[0].equalsIgnoreCase("objectives")) {
            if (stringArray2.length == 2) {
                return bc.a(stringArray2, "list", "add", "remove", "setdisplay");
            }
            if (stringArray2[1].equalsIgnoreCase("add")) {
                if (stringArray2.length == 4) {
                    Set<String> set = auu.a.keySet();
                    return bc.a(stringArray2, set);
                }
            } else if (stringArray2[1].equalsIgnoreCase("remove")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, this.a(false));
                }
            } else if (stringArray2[1].equalsIgnoreCase("setdisplay")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, auo.h());
                }
                if (stringArray2.length == 4) {
                    return bc.a(stringArray2, this.a(false));
                }
            }
        } else if (stringArray2[0].equalsIgnoreCase("players")) {
            if (stringArray2.length == 2) {
                return bc.a(stringArray2, "set", "add", "remove", "reset", "list", "enable", "test", "operation");
            }
            if (stringArray2[1].equalsIgnoreCase("set") || stringArray2[1].equalsIgnoreCase("add") || stringArray2[1].equalsIgnoreCase("remove") || stringArray2[1].equalsIgnoreCase("reset")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, MinecraftServer.N().K());
                }
                if (stringArray2.length == 4) {
                    return bc.a(stringArray2, this.a(true));
                }
            } else if (stringArray2[1].equalsIgnoreCase("enable")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, MinecraftServer.N().K());
                }
                if (stringArray2.length == 4) {
                    return bc.a(stringArray2, this.e());
                }
            } else if (stringArray2[1].equalsIgnoreCase("list") || stringArray2[1].equalsIgnoreCase("test")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, this.d().d());
                }
                if (stringArray2.length == 4 && stringArray2[1].equalsIgnoreCase("test")) {
                    return bc.a(stringArray2, this.a(false));
                }
            } else if (stringArray2[1].equalsIgnoreCase("operation")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, this.d().d());
                }
                if (stringArray2.length == 4) {
                    return bc.a(stringArray2, this.a(true));
                }
                if (stringArray2.length == 5) {
                    return bc.a(stringArray2, "+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><");
                }
                if (stringArray2.length == 6) {
                    return bc.a(stringArray2, MinecraftServer.N().K());
                }
                if (stringArray2.length == 7) {
                    return bc.a(stringArray2, this.a(false));
                }
            }
        } else if (stringArray2[0].equalsIgnoreCase("teams")) {
            if (stringArray2.length == 2) {
                return bc.a(stringArray2, "add", "remove", "join", "leave", "empty", "list", "option");
            }
            if (stringArray2[1].equalsIgnoreCase("join")) {
                if (stringArray2.length == 3) {
                    return bc.a(stringArray2, this.d().f());
                }
                if (stringArray2.length >= 4) {
                    return bc.a(stringArray2, MinecraftServer.N().K());
                }
            } else {
                if (stringArray2[1].equalsIgnoreCase("leave")) {
                    return bc.a(stringArray2, MinecraftServer.N().K());
                }
                if (stringArray2[1].equalsIgnoreCase("empty") || stringArray2[1].equalsIgnoreCase("list") || stringArray2[1].equalsIgnoreCase("remove")) {
                    if (stringArray2.length == 3) {
                        return bc.a(stringArray2, this.d().f());
                    }
                } else if (stringArray2[1].equalsIgnoreCase("option")) {
                    if (stringArray2.length == 3) {
                        return bc.a(stringArray2, this.d().f());
                    }
                    if (stringArray2.length == 4) {
                        return bc.a(stringArray2, "color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility");
                    }
                    if (stringArray2.length == 5) {
                        if (stringArray2[3].equalsIgnoreCase("color")) {
                            return bc.a(stringArray2, a.a(true, false));
                        }
                        if (stringArray2[3].equalsIgnoreCase("nametagVisibility") || stringArray2[3].equalsIgnoreCase("deathMessageVisibility")) {
                            return bc.a(stringArray2, auq.a.a());
                        }
                        if (stringArray2[3].equalsIgnoreCase("friendlyfire") || stringArray2[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                            return bc.a(stringArray2, "true", "false");
                        }
                    }
                }
            }
        }
        return null;
    }

    protected List<String> a(boolean bl2) {
        Collection<auk> collection = this.d().c();
        ArrayList<String> \u26032 = Lists.newArrayList();
        for (auk auk2 : collection) {
            if (bl2 && auk2.c().b()) continue;
            \u26032.add(auk2.b());
        }
        return \u26032;
    }

    protected List<String> e() {
        Collection<auk> collection = this.d().c();
        ArrayList<String> \u26032 = Lists.newArrayList();
        for (auk auk2 : collection) {
            if (auk2.c() != auu.c) continue;
            \u26032.add(auk2.b());
        }
        return \u26032;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        if (stringArray[0].equalsIgnoreCase("players")) {
            if (stringArray.length > 1 && stringArray[1].equalsIgnoreCase("operation")) {
                return n2 == 2 || n2 == 5;
            }
            return n2 == 2;
        }
        if (stringArray[0].equalsIgnoreCase("teams")) {
            return n2 == 2;
        }
        return false;
    }
}

