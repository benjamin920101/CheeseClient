/*
 * Decompiled with CFR 0.152.
 */
public class bqe
extends bqh {
    public bqe(mq mq2) {
        super("achievement");
        this.a("achievement_id", mq2.e);
        this.a("achievement_name", mq2.e().c());
        this.a("achievement_description", mq2.f());
        this.a("Achievement '" + mq2.e().c() + "' obtained!");
    }
}

