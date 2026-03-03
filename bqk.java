/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.ErrorCode;
import tv.twitch.StandardCoreAPI;
import tv.twitch.chat.Chat;
import tv.twitch.chat.ChatBadgeData;
import tv.twitch.chat.ChatChannelInfo;
import tv.twitch.chat.ChatEmoticonData;
import tv.twitch.chat.ChatEvent;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizationOption;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.IChatAPIListener;
import tv.twitch.chat.IChatChannelListener;
import tv.twitch.chat.StandardChatAPI;

public class bqk {
    private static final Logger q = LogManager.getLogger();
    protected e a = null;
    protected String b = "";
    protected String c = "";
    protected String d = "";
    protected Core e = null;
    protected Chat f = null;
    protected c g = bqk$c.a;
    protected AuthToken h = new AuthToken();
    protected HashMap<String, b> i = new HashMap();
    protected int j = 128;
    protected d k = bqk$d.a;
    protected d l = bqk$d.a;
    protected ChatEmoticonData m = null;
    protected int n = 500;
    protected int o = 2000;
    protected IChatAPIListener p = new IChatAPIListener(){

        @Override
        public void chatInitializationCallback(ErrorCode errorCode) {
            if (ErrorCode.succeeded(errorCode)) {
                bqk.this.f.setMessageFlushInterval(bqk.this.n);
                bqk.this.f.setUserChangeEventInterval(bqk.this.o);
                bqk.this.r();
                bqk.this.a(bqk$c.c);
            } else {
                bqk.this.a(bqk$c.a);
            }
            try {
                if (bqk.this.a != null) {
                    bqk.this.a.d(errorCode);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        @Override
        public void chatShutdownCallback(ErrorCode errorCode2) {
            ErrorCode errorCode2;
            if (ErrorCode.succeeded(errorCode2)) {
                \u2603 = bqk.this.e.shutdown();
                if (ErrorCode.failed(\u2603)) {
                    String string = ErrorCode.getString(\u2603);
                    bqk.this.n(String.format("Error shutting down the Twitch sdk: %s", string));
                }
                bqk.this.a(bqk$c.a);
            } else {
                bqk.this.a(bqk$c.c);
                bqk.this.n(String.format("Error shutting down Twith chat: %s", new Object[]{errorCode2}));
            }
            try {
                if (bqk.this.a != null) {
                    bqk.this.a.e(errorCode2);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        @Override
        public void chatEmoticonDataDownloadCallback(ErrorCode errorCode) {
            if (ErrorCode.succeeded(errorCode)) {
                bqk.this.s();
            }
        }
    };

    public void a(e e2) {
        this.a = e2;
    }

    public void a(AuthToken authToken) {
        this.h = authToken;
    }

    public void a(String string) {
        this.c = string;
    }

    public void c(String string) {
        this.b = string;
    }

    public c h() {
        return this.g;
    }

    public boolean d(String string) {
        if (!this.i.containsKey(string)) {
            return false;
        }
        b b2 = this.i.get(string);
        return b2.a() == bqk$a.c;
    }

    public a e(String string) {
        if (!this.i.containsKey(string)) {
            return bqk$a.e;
        }
        b b2 = this.i.get(string);
        return b2.a();
    }

    public bqk() {
        this.e = Core.getInstance();
        if (this.e == null) {
            this.e = new Core(new StandardCoreAPI());
        }
        this.f = new Chat(new StandardChatAPI());
    }

    public boolean n() {
        if (this.g != bqk$c.a) {
            return false;
        }
        this.a(bqk$c.b);
        ErrorCode \u26032 = this.e.initialize(this.c, null);
        if (ErrorCode.failed(\u26032)) {
            this.a(bqk$c.a);
            String string = ErrorCode.getString(\u26032);
            this.n(String.format("Error initializing Twitch sdk: %s", string));
            return false;
        }
        this.l = this.k;
        HashSet<ChatTokenizationOption> hashSet = new HashSet<ChatTokenizationOption>();
        switch (this.k) {
            case a: {
                hashSet.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_NONE);
                break;
            }
            case b: {
                hashSet.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_URLS);
                break;
            }
            case c: {
                hashSet.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_TEXTURES);
            }
        }
        \u26032 = this.f.initialize(hashSet, this.p);
        if (ErrorCode.failed(\u26032)) {
            this.e.shutdown();
            this.a(bqk$c.a);
            String string = ErrorCode.getString(\u26032);
            this.n(String.format("Error initializing Twitch chat: %s", string));
            return false;
        }
        this.a(bqk$c.c);
        return true;
    }

    public boolean j(String string) {
        return this.a(string, false);
    }

    protected boolean a(String string, boolean bl2) {
        if (this.g != bqk$c.c) {
            return false;
        }
        if (this.i.containsKey(string)) {
            this.n("Already in channel: " + string);
            return false;
        }
        if (string == null || string.equals("")) {
            return false;
        }
        b b2 = new b(string);
        this.i.put(string, b2);
        boolean \u26032 = b2.a(bl2);
        if (!\u26032) {
            this.i.remove(string);
        }
        return \u26032;
    }

    public boolean l(String string) {
        if (this.g != bqk$c.c) {
            return false;
        }
        if (!this.i.containsKey(string)) {
            this.n("Not in channel: " + string);
            return false;
        }
        b b2 = this.i.get(string);
        return b2.g();
    }

    public boolean o() {
        if (this.g != bqk$c.c) {
            return false;
        }
        ErrorCode errorCode = this.f.shutdown();
        if (ErrorCode.failed(errorCode)) {
            String string = ErrorCode.getString(errorCode);
            this.n(String.format("Error shutting down chat: %s", string));
            return false;
        }
        this.t();
        this.a(bqk$c.d);
        return true;
    }

    public void p() {
        if (this.h() != bqk$c.a) {
            this.o();
            if (this.h() == bqk$c.d) {
                while (this.h() != bqk$c.a) {
                    try {
                        Thread.sleep(200L);
                        this.q();
                    }
                    catch (InterruptedException interruptedException) {}
                }
            }
        }
    }

    public void q() {
        if (this.g == bqk$c.a) {
            return;
        }
        ErrorCode errorCode = this.f.flushEvents();
        if (ErrorCode.failed(errorCode)) {
            String string = ErrorCode.getString(errorCode);
            this.n(String.format("Error flushing chat events: %s", string));
        }
    }

    public boolean a(String string, String string2) {
        if (this.g != bqk$c.c) {
            return false;
        }
        if (!this.i.containsKey(string)) {
            this.n("Not in channel: " + string);
            return false;
        }
        b b2 = this.i.get(string);
        return b2.b(string2);
    }

    protected void a(c c2) {
        if (c2 == this.g) {
            return;
        }
        this.g = c2;
        try {
            if (this.a != null) {
                this.a.a(c2);
            }
        }
        catch (Exception exception) {
            this.n(exception.toString());
        }
    }

    protected void r() {
        ErrorCode errorCode;
        if (this.l == bqk$d.a) {
            return;
        }
        if (this.m == null && ErrorCode.failed(errorCode = this.f.downloadEmoticonData())) {
            String string = ErrorCode.getString(errorCode);
            this.n(String.format("Error trying to download emoticon data: %s", string));
        }
    }

    protected void s() {
        if (this.m != null) {
            return;
        }
        this.m = new ChatEmoticonData();
        ErrorCode errorCode = this.f.getEmoticonData(this.m);
        if (ErrorCode.succeeded(errorCode)) {
            try {
                if (this.a != null) {
                    this.a.d();
                }
            }
            catch (Exception exception) {
                this.n(exception.toString());
            }
        } else {
            this.n("Error preparing emoticon data: " + ErrorCode.getString(errorCode));
        }
    }

    protected void t() {
        if (this.m == null) {
            return;
        }
        ErrorCode errorCode = this.f.clearEmoticonData();
        if (ErrorCode.succeeded(errorCode)) {
            this.m = null;
            try {
                if (this.a != null) {
                    this.a.e();
                }
            }
            catch (Exception exception) {
                this.n(exception.toString());
            }
        } else {
            this.n("Error clearing emoticon data: " + ErrorCode.getString(errorCode));
        }
    }

    protected void n(String string) {
        q.error(bqn.a, "[Chat controller] {}", string);
    }

    public class b
    implements IChatChannelListener {
        protected String a = null;
        protected boolean b = false;
        protected a c = bqk$a.a;
        protected List<ChatUserInfo> d = Lists.newArrayList();
        protected LinkedList<ChatRawMessage> e = new LinkedList();
        protected LinkedList<ChatTokenizedMessage> f = new LinkedList();
        protected ChatBadgeData g = null;

        public b(String string) {
            this.a = string;
        }

        public a a() {
            return this.c;
        }

        public boolean a(boolean bl2) {
            this.b = bl2;
            ErrorCode errorCode = ErrorCode.TTV_EC_SUCCESS;
            errorCode = bl2 ? bqk.this.f.connectAnonymous(this.a, this) : bqk.this.f.connect(this.a, bqk.this.b, bqk.this.h.data, this);
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqk.this.n(String.format("Error connecting: %s", string));
                this.d(this.a);
                return false;
            }
            this.a(bqk$a.b);
            this.h();
            return true;
        }

        public boolean g() {
            switch (this.c) {
                case c: 
                case b: {
                    ErrorCode errorCode = bqk.this.f.disconnect(this.a);
                    if (ErrorCode.failed(errorCode)) {
                        String string = ErrorCode.getString(errorCode);
                        bqk.this.n(String.format("Error disconnecting: %s", string));
                        return false;
                    }
                    this.a(bqk$a.d);
                    return true;
                }
            }
            return false;
        }

        protected void a(a a2) {
            if (a2 == this.c) {
                return;
            }
            this.c = a2;
        }

        public void a(String string2) {
            if (bqk.this.l == bqk$d.a) {
                this.e.clear();
                this.f.clear();
            } else {
                Object object;
                ListIterator listIterator;
                if (this.e.size() > 0) {
                    listIterator = this.e.listIterator();
                    while (listIterator.hasNext()) {
                        object = (ChatRawMessage)listIterator.next();
                        if (!((ChatRawMessage)object).userName.equals(string2)) continue;
                        listIterator.remove();
                    }
                }
                if (this.f.size() > 0) {
                    listIterator = this.f.listIterator();
                    while (listIterator.hasNext()) {
                        object = (ChatTokenizedMessage)listIterator.next();
                        if (!((ChatTokenizedMessage)object).displayName.equals(string2)) continue;
                        listIterator.remove();
                    }
                }
            }
            try {
                if (bqk.this.a != null) {
                    String string2;
                    bqk.this.a.a(this.a, string2);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        public boolean b(String string) {
            if (this.c != bqk$a.c) {
                return false;
            }
            ErrorCode errorCode = bqk.this.f.sendMessage(this.a, string);
            if (ErrorCode.failed(errorCode)) {
                String string2 = ErrorCode.getString(errorCode);
                bqk.this.n(String.format("Error sending chat message: %s", string2));
                return false;
            }
            return true;
        }

        protected void h() {
            ErrorCode errorCode;
            if (bqk.this.l == bqk$d.a) {
                return;
            }
            if (this.g == null && ErrorCode.failed(errorCode = bqk.this.f.downloadBadgeData(this.a))) {
                String string = ErrorCode.getString(errorCode);
                bqk.this.n(String.format("Error trying to download badge data: %s", string));
            }
        }

        protected void i() {
            if (this.g != null) {
                return;
            }
            this.g = new ChatBadgeData();
            ErrorCode errorCode = bqk.this.f.getBadgeData(this.a, this.g);
            if (ErrorCode.succeeded(errorCode)) {
                try {
                    if (bqk.this.a != null) {
                        bqk.this.a.c(this.a);
                    }
                }
                catch (Exception exception) {
                    bqk.this.n(exception.toString());
                }
            } else {
                bqk.this.n("Error preparing badge data: " + ErrorCode.getString(errorCode));
            }
        }

        protected void j() {
            if (this.g == null) {
                return;
            }
            ErrorCode errorCode = bqk.this.f.clearBadgeData(this.a);
            if (ErrorCode.succeeded(errorCode)) {
                this.g = null;
                try {
                    if (bqk.this.a != null) {
                        bqk.this.a.d(this.a);
                    }
                }
                catch (Exception exception) {
                    bqk.this.n(exception.toString());
                }
            } else {
                bqk.this.n("Error releasing badge data: " + ErrorCode.getString(errorCode));
            }
        }

        protected void c(String string) {
            try {
                if (bqk.this.a != null) {
                    bqk.this.a.a(string);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        protected void d(String string) {
            try {
                if (bqk.this.a != null) {
                    bqk.this.a.b(string);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        private void k() {
            if (this.c != bqk$a.e) {
                this.a(bqk$a.e);
                this.d(this.a);
                this.j();
            }
        }

        @Override
        public void chatStatusCallback(String string, ErrorCode errorCode) {
            if (ErrorCode.succeeded(errorCode)) {
                return;
            }
            bqk.this.i.remove(string);
            this.k();
        }

        @Override
        public void chatChannelMembershipCallback(String string, ChatEvent chatEvent, ChatChannelInfo chatChannelInfo) {
            switch (chatEvent) {
                case TTV_CHAT_JOINED_CHANNEL: {
                    this.a(bqk$a.c);
                    this.c(string);
                    break;
                }
                case TTV_CHAT_LEFT_CHANNEL: {
                    this.k();
                    break;
                }
            }
        }

        @Override
        public void chatChannelUserChangeCallback(String string, ChatUserInfo[] chatUserInfoArray4, ChatUserInfo[] chatUserInfoArray2, ChatUserInfo[] chatUserInfoArray3) {
            int n2;
            for (n2 = 0; n2 < chatUserInfoArray2.length; ++n2) {
                \u2603 = this.d.indexOf(chatUserInfoArray2[n2]);
                if (\u2603 < 0) continue;
                this.d.remove(\u2603);
            }
            for (n2 = 0; n2 < chatUserInfoArray3.length; ++n2) {
                \u2603 = this.d.indexOf(chatUserInfoArray3[n2]);
                if (\u2603 >= 0) {
                    this.d.remove(\u2603);
                }
                this.d.add(chatUserInfoArray3[n2]);
            }
            for (n2 = 0; n2 < chatUserInfoArray4.length; ++n2) {
                this.d.add(chatUserInfoArray4[n2]);
            }
            try {
                if (bqk.this.a != null) {
                    ChatUserInfo[] chatUserInfoArray4;
                    bqk.this.a.a(this.a, chatUserInfoArray4, chatUserInfoArray2, chatUserInfoArray3);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
        }

        @Override
        public void chatChannelRawMessageCallback(String string, ChatRawMessage[] chatRawMessageArray2) {
            for (int i2 = 0; i2 < chatRawMessageArray2.length; ++i2) {
                this.e.addLast(chatRawMessageArray2[i2]);
            }
            try {
                if (bqk.this.a != null) {
                    ChatRawMessage[] chatRawMessageArray2;
                    bqk.this.a.a(this.a, chatRawMessageArray2);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
            while (this.e.size() > bqk.this.j) {
                this.e.removeFirst();
            }
        }

        @Override
        public void chatChannelTokenizedMessageCallback(String string, ChatTokenizedMessage[] chatTokenizedMessageArray2) {
            for (int i2 = 0; i2 < chatTokenizedMessageArray2.length; ++i2) {
                this.f.addLast(chatTokenizedMessageArray2[i2]);
            }
            try {
                if (bqk.this.a != null) {
                    ChatTokenizedMessage[] chatTokenizedMessageArray2;
                    bqk.this.a.a(this.a, chatTokenizedMessageArray2);
                }
            }
            catch (Exception exception) {
                bqk.this.n(exception.toString());
            }
            while (this.f.size() > bqk.this.j) {
                this.f.removeFirst();
            }
        }

        @Override
        public void chatClearCallback(String string, String string2) {
            this.a(string2);
        }

        @Override
        public void chatBadgeDataDownloadCallback(String string, ErrorCode errorCode) {
            if (ErrorCode.succeeded(errorCode)) {
                this.i();
            }
        }
    }

    public static interface e {
        public void d(ErrorCode var1);

        public void e(ErrorCode var1);

        public void d();

        public void e();

        public void a(c var1);

        public void a(String var1, ChatTokenizedMessage[] var2);

        public void a(String var1, ChatRawMessage[] var2);

        public void a(String var1, ChatUserInfo[] var2, ChatUserInfo[] var3, ChatUserInfo[] var4);

        public void a(String var1);

        public void b(String var1);

        public void a(String var1, String var2);

        public void c(String var1);

        public void d(String var1);
    }

    public static enum d {
        a,
        b,
        c;

    }

    public static enum a {
        a,
        b,
        c,
        d,
        e;

    }

    public static enum c {
        a,
        b,
        c,
        d;

    }
}

