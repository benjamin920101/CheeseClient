/*
 * Decompiled with CFR 0.152.
 */
public class bqg
extends bqh {
    public bqg(pr pr2, pr pr3) {
        super("player_death");
        if (pr2 != null) {
            this.a("player", pr2.e_());
        }
        if (pr3 != null) {
            this.a("killer", pr3.e_());
        }
    }
}

