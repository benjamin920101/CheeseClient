/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ayx {
    private static final ayx a = new ayx();
    private Random b = new Random();
    private String[] c = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale ".split(" ");

    private ayx() {
    }

    public static ayx a() {
        return a;
    }

    public String b() {
        int n2 = this.b.nextInt(2) + 3;
        String \u26032 = "";
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            if (\u2603 > 0) {
                \u26032 = \u26032 + " ";
            }
            \u26032 = \u26032 + this.c[this.b.nextInt(this.c.length)];
        }
        return \u26032;
    }

    public void a(long l2) {
        this.b.setSeed(l2);
    }
}

