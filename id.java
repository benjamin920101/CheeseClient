/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class id
implements ff<ic> {
    private String a;
    private cj b;

    public id() {
    }

    public id(String string) {
        this(string, null);
    }

    public id(String string, cj cj2) {
        this.a = string;
        this.b = cj2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(Short.MAX_VALUE);
        boolean bl2 = em2.readBoolean();
        if (bl2) {
            this.b = em2.c();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(StringUtils.substring(this.a, 0, Short.MAX_VALUE));
        boolean bl2 = this.b != null;
        em2.writeBoolean(bl2);
        if (bl2) {
            em2.a(this.b);
        }
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public String a() {
        return this.a;
    }

    public cj b() {
        return this.b;
    }
}

