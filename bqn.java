/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.opengl.GL11;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.VideoParams;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class bqn
implements bqj.b,
bqk.e,
bql.a,
bqm {
    private static final Logger b = LogManager.getLogger();
    public static final Marker a = MarkerManager.getMarker("STREAM");
    private final bqj c;
    private final bqk d;
    private String e;
    private final ave f;
    private final eu g = new fa("Twitch");
    private final Map<String, ChatUserInfo> h = Maps.newHashMap();
    private bfw i;
    private boolean j;
    private int k = 30;
    private long l = 0L;
    private boolean m = false;
    private boolean n;
    private boolean o;
    private boolean p;
    private bqm.a q = bqm.a.a;
    private static boolean r;

    public bqn(ave ave2, final Property property) {
        this.f = ave2;
        this.c = new bqj();
        this.d = new bqk();
        this.c.a(this);
        this.d.a(this);
        this.c.a("nmt37qblda36pvonovdkbopzfzw3wlq");
        this.d.a("nmt37qblda36pvonovdkbopzfzw3wlq");
        this.g.b().a(a.f);
        if (property != null && !Strings.isNullOrEmpty(property.getValue()) && bqs.l) {
            Thread thread = new Thread("Twitch authenticator"){

                @Override
                public void run() {
                    try {
                        URL uRL = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(property.getValue(), "UTF-8"));
                        String \u26032 = nj.a(uRL);
                        JsonObject \u26033 = ni.l(new JsonParser().parse(\u26032), "Response");
                        JsonObject \u26034 = ni.s(\u26033, "token");
                        if (ni.i(\u26034, "valid")) {
                            String string = ni.h(\u26034, "user_name");
                            b.debug(a, "Authenticated with twitch; username is {}", string);
                            AuthToken \u26035 = new AuthToken();
                            \u26035.data = property.getValue();
                            bqn.this.c.a(string, \u26035);
                            bqn.this.d.c(string);
                            bqn.this.d.a(\u26035);
                            Runtime.getRuntime().addShutdownHook(new Thread("Twitch shutdown hook"){

                                @Override
                                public void run() {
                                    bqn.this.f();
                                }
                            });
                            bqn.this.c.C();
                            bqn.this.d.n();
                        } else {
                            bqn.this.q = bqm.a.b;
                            b.error(a, "Given twitch access token is invalid");
                        }
                    }
                    catch (IOException iOException) {
                        bqn.this.q = bqm.a.a;
                        b.error(a, "Could not authenticate with twitch", (Throwable)iOException);
                    }
                }
            };
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void f() {
        b.debug(a, "Shutdown streaming");
        this.c.E();
        this.d.p();
    }

    @Override
    public void g() {
        int n2 = this.f.t.S;
        boolean \u26032 = this.e != null && this.d.d(this.e);
        boolean bl2 = \u2603 = this.d.h() == bqk.c.c && (this.e == null || this.d.e(this.e) == bqk.a.e);
        if (n2 == 2) {
            if (\u26032) {
                b.debug(a, "Disconnecting from twitch chat per user options");
                this.d.l(this.e);
            }
        } else if (n2 == 1) {
            if (\u2603 && this.c.q()) {
                b.debug(a, "Connecting to twitch chat per user options");
                this.F();
            }
        } else if (n2 == 0) {
            if (\u26032 && !this.k()) {
                b.debug(a, "Disconnecting from twitch chat as user is no longer streaming");
                this.d.l(this.e);
            } else if (\u2603 && this.k()) {
                b.debug(a, "Connecting to twitch chat as user is streaming");
                this.F();
            }
        }
        this.c.K();
        this.d.q();
    }

    protected void F() {
        bqk.c c2 = this.d.h();
        this.e = \u2603 = this.c.l().name;
        if (c2 != bqk.c.c) {
            b.warn("Invalid twitch chat state {}", new Object[]{c2});
        } else if (this.d.e(this.e) == bqk.a.e) {
            this.d.j(\u2603);
        } else {
            b.warn("Invalid twitch chat state {}", new Object[]{c2});
        }
    }

    @Override
    public void h() {
        if (!this.c.m() || this.c.p()) {
            return;
        }
        long l2 = System.nanoTime();
        \u2603 = l2 - this.l;
        boolean bl2 = \u2603 = \u2603 >= (\u2603 = (long)(1000000000 / this.k));
        if (\u2603) {
            FrameBuffer frameBuffer = this.c.Q();
            bfw \u26032 = this.f.b();
            this.i.a(true);
            bfl.n(5889);
            bfl.E();
            bfl.D();
            bfl.a(0.0, this.i.c, this.i.d, 0.0, 1000.0, 3000.0);
            bfl.n(5888);
            bfl.E();
            bfl.D();
            bfl.b(0.0f, 0.0f, -2000.0f);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.b(0, 0, this.i.c, this.i.d);
            bfl.w();
            bfl.c();
            bfl.k();
            float \u26033 = this.i.c;
            float \u26034 = this.i.d;
            float \u26035 = (float)\u26032.c / (float)\u26032.a;
            float \u26036 = (float)\u26032.d / (float)\u26032.b;
            \u26032.c();
            GL11.glTexParameterf(3553, 10241, 9729.0f);
            GL11.glTexParameterf(3553, 10240, 9729.0f);
            bfx \u26037 = bfx.a();
            bfd \u26038 = \u26037.c();
            \u26038.a(7, bms.g);
            \u26038.b(0.0, (double)\u26034, 0.0).a(0.0, \u26036).d();
            \u26038.b((double)\u26033, (double)\u26034, 0.0).a(\u26035, \u26036).d();
            \u26038.b((double)\u26033, 0.0, 0.0).a(\u26035, 0.0).d();
            \u26038.b(0.0, 0.0, 0.0).a(0.0, 0.0).d();
            \u26037.b();
            \u26032.d();
            bfl.F();
            bfl.n(5889);
            bfl.F();
            bfl.n(5888);
            this.c.a(frameBuffer);
            this.i.e();
            this.c.b(frameBuffer);
            this.l = l2;
        }
    }

    @Override
    public boolean i() {
        return this.c.q();
    }

    @Override
    public boolean j() {
        return this.c.n();
    }

    @Override
    public boolean k() {
        return this.c.m();
    }

    @Override
    public void a(bqh bqh2, long l2) {
        if (!this.k() || !this.j) {
            return;
        }
        \u2603 = this.c.x();
        if (!this.c.a(bqh2.c(), \u2603 + l2, bqh2.a(), bqh2.b())) {
            b.warn(a, "Couldn't send stream metadata action at {}: {}", \u2603 + l2, bqh2);
        } else {
            b.debug(a, "Sent stream metadata action at {}: {}", \u2603 + l2, bqh2);
        }
    }

    @Override
    public void a(bqh bqh2, long l2, long l3) {
        if (!this.k() || !this.j) {
            return;
        }
        \u2603 = this.c.x();
        String string = bqh2.a();
        \u2603 = bqh2.b();
        long \u26032 = this.c.b(bqh2.c(), \u2603 + l2, string, \u2603);
        if (\u26032 < 0L) {
            b.warn(a, "Could not send stream metadata sequence from {} to {}: {}", \u2603 + l2, \u2603 + l3, bqh2);
        } else if (this.c.a(bqh2.c(), \u2603 + l3, \u26032, string, \u2603)) {
            b.debug(a, "Sent stream metadata sequence from {} to {}: {}", \u2603 + l2, \u2603 + l3, bqh2);
        } else {
            b.warn(a, "Half-sent stream metadata sequence from {} to {}: {}", \u2603 + l2, \u2603 + l3, bqh2);
        }
    }

    @Override
    public boolean l() {
        return this.c.p();
    }

    @Override
    public void m() {
        if (this.c.G()) {
            b.debug(a, "Requested commercial from Twitch");
        } else {
            b.warn(a, "Could not request commercial from Twitch");
        }
    }

    @Override
    public void n() {
        this.c.I();
        this.o = true;
        this.p();
    }

    @Override
    public void o() {
        this.c.J();
        this.o = false;
        this.p();
    }

    @Override
    public void p() {
        if (this.k()) {
            float f2 = this.f.t.M;
            boolean \u26032 = this.o || f2 <= 0.0f;
            this.c.b(\u26032 ? 0.0f : f2);
            this.c.a(this.D() ? 0.0f : this.f.t.L);
        }
    }

    @Override
    public void q() {
        avh avh2 = this.f.t;
        VideoParams \u26032 = this.c.a(bqn.b(avh2.N), bqn.a(avh2.O), bqn.c(avh2.K), (float)this.f.d / (float)this.f.e);
        switch (avh2.P) {
            case 2: {
                \u26032.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
                break;
            }
            case 1: {
                \u26032.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
                break;
            }
            case 0: {
                \u26032.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
            }
        }
        if (this.i == null) {
            this.i = new bfw(\u26032.outputWidth, \u26032.outputHeight, false);
        } else {
            this.i.a(\u26032.outputWidth, \u26032.outputHeight);
        }
        if (avh2.R != null && avh2.R.length() > 0) {
            for (IngestServer ingestServer : this.s()) {
                if (!ingestServer.serverUrl.equals(avh2.R)) continue;
                this.c.a(ingestServer);
                break;
            }
        }
        this.k = \u26032.targetFps;
        this.j = avh2.Q;
        this.c.a(\u26032);
        b.info(a, "Streaming at {}/{} at {} kbps to {}", \u26032.outputWidth, \u26032.outputHeight, \u26032.maxKbps, this.c.s().serverUrl);
        this.c.a(null, "Minecraft", null);
    }

    @Override
    public void r() {
        if (this.c.H()) {
            b.info(a, "Stopped streaming to Twitch");
        } else {
            b.warn(a, "Could not stop streaming to Twitch");
        }
    }

    @Override
    public void a(ErrorCode errorCode, AuthToken authToken) {
    }

    @Override
    public void a(ErrorCode errorCode) {
        if (ErrorCode.succeeded(errorCode)) {
            b.debug(a, "Login attempt successful");
            this.n = true;
        } else {
            b.warn(a, "Login attempt unsuccessful: {} (error code {})", ErrorCode.getString(errorCode), errorCode.getValue());
            this.n = false;
        }
    }

    @Override
    public void a(ErrorCode errorCode, GameInfo[] gameInfoArray) {
    }

    @Override
    public void a(bqj.a a2) {
        b.debug(a, "Broadcast state changed to {}", new Object[]{a2});
        if (a2 == bqj.a.b) {
            this.c.a(bqj.a.d);
        }
    }

    @Override
    public void a() {
        b.info(a, "Logged out of twitch");
    }

    @Override
    public void a(StreamInfo streamInfo) {
        b.debug(a, "Stream info updated; {} viewers on stream ID {}", streamInfo.viewers, streamInfo.streamId);
    }

    @Override
    public void a(IngestList ingestList) {
    }

    @Override
    public void b(ErrorCode errorCode) {
        b.warn(a, "Issue submitting frame: {} (Error code {})", ErrorCode.getString(errorCode), errorCode.getValue());
        this.f.q.d().a(new fa("Issue streaming frame: " + (Object)((Object)errorCode) + " (" + ErrorCode.getString(errorCode) + ")"), 2);
    }

    @Override
    public void b() {
        this.p();
        b.info(a, "Broadcast to Twitch has started");
    }

    @Override
    public void c() {
        b.info(a, "Broadcast to Twitch has stopped");
    }

    @Override
    public void c(ErrorCode errorCode2) {
        if (errorCode2 == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
            fb fb2 = new fb("stream.unavailable.soundflower.chat.link", new Object[0]);
            fb2.b().a(new et(et.a.a, "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"));
            fb2.b().d(true);
            \u2603 = new fb("stream.unavailable.soundflower.chat", fb2);
            \u2603.b().a(a.e);
            this.f.q.d().a(\u2603);
        } else {
            ErrorCode errorCode2;
            fb \u26032 = new fb("stream.unavailable.unknown.chat", ErrorCode.getString(errorCode2));
            \u26032.b().a(a.e);
            this.f.q.d().a(\u26032);
        }
    }

    @Override
    public void a(bql bql2, bql.b b2) {
        b.debug(a, "Ingest test state changed to {}", new Object[]{b2});
        if (b2 == bql.b.f) {
            this.m = true;
        }
    }

    public static int a(float f2) {
        return ns.d(10.0f + f2 * 50.0f);
    }

    public static int b(float f2) {
        return ns.d(230.0f + f2 * 3270.0f);
    }

    public static float c(float f2) {
        return 0.1f + f2 * 0.1f;
    }

    @Override
    public IngestServer[] s() {
        return this.c.t().getServers();
    }

    @Override
    public void u() {
        bql bql2 = this.c.M();
        if (bql2 != null) {
            bql2.a(this);
        }
    }

    @Override
    public bql v() {
        return this.c.w();
    }

    @Override
    public boolean w() {
        return this.c.o();
    }

    @Override
    public int x() {
        return this.k() ? this.c.j().viewers : 0;
    }

    @Override
    public void d(ErrorCode errorCode) {
        if (ErrorCode.failed(errorCode)) {
            b.error(a, "Chat failed to initialize");
        }
    }

    @Override
    public void e(ErrorCode errorCode) {
        if (ErrorCode.failed(errorCode)) {
            b.error(a, "Chat failed to shutdown");
        }
    }

    @Override
    public void a(bqk.c c2) {
    }

    @Override
    public void a(String string, ChatRawMessage[] chatRawMessageArray) {
        for (ChatRawMessage chatRawMessage : chatRawMessageArray) {
            this.a(chatRawMessage.userName, chatRawMessage);
            if (!this.a(chatRawMessage.modes, chatRawMessage.subscriptions, this.f.t.T)) continue;
            fa fa2 = new fa(chatRawMessage.userName);
            fb \u26032 = new fb("chat.stream." + (chatRawMessage.action ? "emote" : "text"), this.g, fa2, a.a(chatRawMessage.message));
            if (chatRawMessage.action) {
                \u26032.b().b(true);
            }
            \u2603 = new fa("");
            \u2603.a(new fb("stream.userinfo.chatTooltip", new Object[0]));
            for (eu eu2 : bab.a(chatRawMessage.modes, chatRawMessage.subscriptions, null)) {
                \u2603.a("\n");
                \u2603.a(eu2);
            }
            fa2.b().a(new ew(ew.a.a, \u2603));
            fa2.b().a(new et(et.a.d, chatRawMessage.userName));
            this.f.q.d().a(\u26032);
        }
    }

    @Override
    public void a(String string, ChatTokenizedMessage[] chatTokenizedMessageArray) {
    }

    private void a(String string, ChatRawMessage chatRawMessage) {
        ChatUserInfo chatUserInfo = this.h.get(string);
        if (chatUserInfo == null) {
            chatUserInfo = new ChatUserInfo();
            chatUserInfo.displayName = string;
            this.h.put(string, chatUserInfo);
        }
        chatUserInfo.subscriptions = chatRawMessage.subscriptions;
        chatUserInfo.modes = chatRawMessage.modes;
        chatUserInfo.nameColorARGB = chatRawMessage.nameColorARGB;
    }

    private boolean a(Set<ChatUserMode> set, Set<ChatUserSubscription> set2, int n2) {
        if (set.contains((Object)ChatUserMode.TTV_CHAT_USERMODE_BANNED)) {
            return false;
        }
        if (set.contains((Object)ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR)) {
            return true;
        }
        if (set.contains((Object)ChatUserMode.TTV_CHAT_USERMODE_MODERATOR)) {
            return true;
        }
        if (set.contains((Object)ChatUserMode.TTV_CHAT_USERMODE_STAFF)) {
            return true;
        }
        if (n2 == 0) {
            return true;
        }
        if (n2 == 1) {
            return set2.contains((Object)ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER);
        }
        return false;
    }

    @Override
    public void a(String string, ChatUserInfo[] chatUserInfoArray, ChatUserInfo[] chatUserInfoArray2, ChatUserInfo[] chatUserInfoArray32) {
        ChatUserInfo[] chatUserInfoArray32;
        for (ChatUserInfo \u26032 : chatUserInfoArray2) {
            this.h.remove(\u26032.displayName);
        }
        for (ChatUserInfo \u26032 : chatUserInfoArray32) {
            this.h.put(\u26032.displayName, \u26032);
        }
        for (ChatUserInfo \u26032 : chatUserInfoArray) {
            this.h.put(\u26032.displayName, \u26032);
        }
    }

    @Override
    public void a(String string) {
        b.debug(a, "Chat connected");
    }

    @Override
    public void b(String string) {
        b.debug(a, "Chat disconnected");
        this.h.clear();
    }

    @Override
    public void a(String string, String string2) {
    }

    @Override
    public void d() {
    }

    @Override
    public void e() {
    }

    @Override
    public void c(String string) {
    }

    @Override
    public void d(String string) {
    }

    @Override
    public boolean y() {
        return this.e != null && this.e.equals(this.c.l().name);
    }

    @Override
    public String z() {
        return this.e;
    }

    @Override
    public ChatUserInfo e(String string) {
        return this.h.get(string);
    }

    @Override
    public void f(String string) {
        this.d.a(this.e, string);
    }

    @Override
    public boolean A() {
        return r && this.c.b();
    }

    @Override
    public ErrorCode B() {
        if (!r) {
            return ErrorCode.TTV_EC_OS_TOO_OLD;
        }
        return this.c.A();
    }

    @Override
    public boolean C() {
        return this.n;
    }

    @Override
    public void a(boolean bl2) {
        this.p = bl2;
        this.p();
    }

    @Override
    public boolean D() {
        boolean bl2 = this.f.t.U == 1;
        return this.o || this.f.t.L <= 0.0f || bl2 != this.p;
    }

    @Override
    public bqm.a E() {
        return this.q;
    }

    static {
        try {
            if (g.a() == g.a.c) {
                System.loadLibrary("avutil-ttv-51");
                System.loadLibrary("swresample-ttv-0");
                System.loadLibrary("libmp3lame-ttv");
                if (System.getProperty("os.arch").contains("64")) {
                    System.loadLibrary("libmfxsw64");
                } else {
                    System.loadLibrary("libmfxsw32");
                }
            }
            r = true;
        }
        catch (Throwable throwable) {
            r = false;
        }
    }
}

