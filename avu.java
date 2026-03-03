/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class avu {
    public static String a(String string, boolean bl2) {
        if (bl2 || ave.A().t.n) {
            return string;
        }
        return a.a(string);
    }

    public static List<eu> a(eu eu2, int n2, avn avn2, boolean bl2, boolean bl3) {
        int n3 = 0;
        fa \u26032 = new fa("");
        ArrayList<eu> \u26033 = Lists.newArrayList();
        ArrayList<eu> \u26034 = Lists.newArrayList(eu2);
        for (\u2603 = 0; \u2603 < \u26034.size(); ++\u2603) {
            String \u26037;
            eu eu3 = (eu)\u26034.get(\u2603);
            String \u26035 = eu3.e();
            boolean \u26036 = false;
            if (\u26035.contains("\n")) {
                int n4 = \u26035.indexOf(10);
                \u26037 = \u26035.substring(n4 + 1);
                \u26035 = \u26035.substring(0, n4 + 1);
                fa \u26038 = new fa(\u26037);
                \u26038.a(eu3.b().m());
                \u26034.add(\u2603 + 1, \u26038);
                \u26036 = true;
            }
            \u26037 = (\u2603 = avu.a(eu3.b().k() + \u26035, bl3)).endsWith("\n") ? \u2603.substring(0, \u2603.length() - 1) : \u2603;
            n6 = avn2.a(\u26037);
            fa \u260310 = new fa(\u26037);
            \u260310.a(eu3.b().m());
            if (n3 + n6 > n2) {
                String string = avn2.a(\u2603, n2 - n3, false);
                String string2 = string3 = string.length() < \u2603.length() ? \u2603.substring(string.length()) : null;
                if (string3 != null && string3.length() > 0) {
                    String string3;
                    int n5 = string.lastIndexOf(" ");
                    if (n5 >= 0 && avn2.a(\u2603.substring(0, n5)) > 0) {
                        string = \u2603.substring(0, n5);
                        if (bl2) {
                            ++n5;
                        }
                        string3 = \u2603.substring(n5);
                    } else if (n3 > 0 && !\u2603.contains(" ")) {
                        string = "";
                        string3 = \u2603;
                    }
                    fa \u26039 = new fa(string3);
                    \u26039.a(eu3.b().m());
                    \u26034.add(\u2603 + 1, \u26039);
                }
                \u2603 = string;
                int n6 = avn2.a(\u2603);
                \u260310 = new fa(\u2603);
                \u260310.a(eu3.b().m());
                \u26036 = true;
            }
            if (n3 + n6 <= n2) {
                n3 += n6;
                \u26032.a(\u260310);
            } else {
                \u26036 = true;
            }
            if (!\u26036) continue;
            \u26033.add(\u26032);
            n3 = 0;
            \u26032 = new fa("");
        }
        \u26033.add(\u26032);
        return \u26033;
    }
}

