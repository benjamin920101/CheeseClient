/*
 * Decompiled with CFR 0.152.
 */
public class bqf
extends bqh {
    public bqf(pr pr2, pr pr3) {
        super("player_combat");
        this.a("player", pr2.e_());
        if (pr3 != null) {
            this.a("primary_opponent", pr3.e_());
        }
        if (pr3 != null) {
            this.a("Combat between " + pr2.e_() + " and " + pr3.e_());
        } else {
            this.a("Combat between " + pr2.e_() + " and others");
        }
    }
}

