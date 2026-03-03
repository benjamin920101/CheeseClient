/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.ErrorCode;
import tv.twitch.MessageLevel;
import tv.twitch.StandardCoreAPI;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.AudioDeviceType;
import tv.twitch.broadcast.AudioParams;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.DesktopStreamAPI;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.PixelFormat;
import tv.twitch.broadcast.StartFlags;
import tv.twitch.broadcast.StatType;
import tv.twitch.broadcast.Stream;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.StreamInfoForSetting;
import tv.twitch.broadcast.UserInfo;
import tv.twitch.broadcast.VideoParams;
import static b.a;

public class bqj {
    private static final Logger D = LogManager.getLogger();
    protected final int a = 30;
    protected final int b = 3;
    private static final np<String> E = new np<String>(String.class, 50);
    private String F = null;
    protected b c = null;
    protected String d = "";
    protected String e = "";
    protected String f = "";
    protected boolean g = true;
    protected Core h = null;
    protected Stream i = null;
    protected List<FrameBuffer> j = Lists.newArrayList();
    protected List<FrameBuffer> k = Lists.newArrayList();
    protected boolean l = false;
    protected boolean m = false;
    protected boolean n = false;
    protected a o = bqj$a.a;
    protected String p = null;
    protected VideoParams q = null;
    protected AudioParams r = null;
    protected IngestList s = new IngestList(new IngestServer[0]);
    protected IngestServer t = null;
    protected AuthToken u = new AuthToken();
    protected ChannelInfo v = new ChannelInfo();
    protected UserInfo w = new UserInfo();
    protected StreamInfo x = new StreamInfo();
    protected ArchivingState y = new ArchivingState();
    protected long z = 0L;
    protected bql A = null;
    private ErrorCode G;
    protected IStreamCallbacks B = new IStreamCallbacks(){

        @Override
        public void requestAuthTokenCallback(ErrorCode errorCode2, AuthToken authToken) {
            if (ErrorCode.succeeded(errorCode2)) {
                bqj.this.u = authToken;
                bqj.this.a(bqj$a.d);
            } else {
                bqj.this.u.data = "";
                bqj.this.a(bqj$a.b);
                String string = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("RequestAuthTokenDoneCallback got failure: %s", string));
            }
            try {
                if (bqj.this.c != null) {
                    ErrorCode errorCode2;
                    bqj.this.c.a(errorCode2, authToken);
                }
            }
            catch (Exception exception) {
                bqj.this.d(exception.toString());
            }
        }

        @Override
        public void loginCallback(ErrorCode errorCode2, ChannelInfo channelInfo) {
            if (ErrorCode.succeeded(errorCode2)) {
                bqj.this.v = channelInfo;
                bqj.this.a(bqj$a.f);
                bqj.this.m = true;
            } else {
                bqj.this.a(bqj$a.b);
                bqj.this.m = false;
                String string = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("LoginCallback got failure: %s", string));
            }
            try {
                if (bqj.this.c != null) {
                    ErrorCode errorCode2;
                    bqj.this.c.a(errorCode2);
                }
            }
            catch (Exception exception) {
                bqj.this.d(exception.toString());
            }
        }

        @Override
        public void getIngestServersCallback(ErrorCode errorCode2, IngestList ingestList) {
            if (ErrorCode.succeeded(errorCode2)) {
                bqj.this.s = ingestList;
                bqj.this.t = bqj.this.s.getDefaultServer();
                bqj.this.a(bqj$a.h);
                try {
                    if (bqj.this.c != null) {
                        bqj.this.c.a(ingestList);
                    }
                }
                catch (Exception exception) {
                    bqj.this.d(exception.toString());
                }
            } else {
                ErrorCode errorCode2;
                String \u26032 = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("IngestListCallback got failure: %s", \u26032));
                bqj.this.a(bqj$a.e);
            }
        }

        @Override
        public void getUserInfoCallback(ErrorCode errorCode, UserInfo userInfo) {
            bqj.this.w = userInfo;
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.d(String.format("UserInfoDoneCallback got failure: %s", string));
            }
        }

        @Override
        public void getStreamInfoCallback(ErrorCode errorCode2, StreamInfo streamInfo) {
            if (ErrorCode.succeeded(errorCode2)) {
                bqj.this.x = streamInfo;
                try {
                    if (bqj.this.c != null) {
                        bqj.this.c.a(streamInfo);
                    }
                }
                catch (Exception exception) {
                    bqj.this.d(exception.toString());
                }
            } else {
                ErrorCode errorCode2;
                String \u26032 = ErrorCode.getString(errorCode2);
                bqj.this.e(String.format("StreamInfoDoneCallback got failure: %s", \u26032));
            }
        }

        @Override
        public void getArchivingStateCallback(ErrorCode errorCode, ArchivingState archivingState) {
            bqj.this.y = archivingState;
            if (ErrorCode.failed(errorCode)) {
                // empty if block
            }
        }

        @Override
        public void runCommercialCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.e(String.format("RunCommercialCallback got failure: %s", string));
            }
        }

        @Override
        public void setStreamInfoCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.e(String.format("SetStreamInfoCallback got failure: %s", string));
            }
        }

        @Override
        public void getGameNameListCallback(ErrorCode errorCode2, GameInfoList gameInfoList) {
            if (ErrorCode.failed(errorCode2)) {
                String string = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("GameNameListCallback got failure: %s", string));
            }
            try {
                if (bqj.this.c != null) {
                    ErrorCode errorCode2;
                    bqj.this.c.a(errorCode2, gameInfoList == null ? new GameInfo[]{} : gameInfoList.list);
                }
            }
            catch (Exception exception) {
                bqj.this.d(exception.toString());
            }
        }

        @Override
        public void bufferUnlockCallback(long l2) {
            FrameBuffer frameBuffer = FrameBuffer.lookupBuffer(l2);
            bqj.this.k.add(frameBuffer);
        }

        @Override
        public void startCallback(ErrorCode errorCode2) {
            if (ErrorCode.succeeded(errorCode2)) {
                try {
                    if (bqj.this.c != null) {
                        bqj.this.c.b();
                    }
                }
                catch (Exception exception) {
                    bqj.this.d(exception.toString());
                }
                bqj.this.a(bqj$a.k);
            } else {
                bqj.this.q = null;
                bqj.this.r = null;
                bqj.this.a(bqj$a.i);
                try {
                    if (bqj.this.c != null) {
                        ErrorCode errorCode2;
                        bqj.this.c.c(errorCode2);
                    }
                }
                catch (Exception exception) {
                    bqj.this.d(exception.toString());
                }
                String string = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("startCallback got failure: %s", string));
            }
        }

        @Override
        public void stopCallback(ErrorCode errorCode2) {
            if (ErrorCode.succeeded(errorCode2)) {
                bqj.this.q = null;
                bqj.this.r = null;
                bqj.this.P();
                try {
                    if (bqj.this.c != null) {
                        bqj.this.c.c();
                    }
                }
                catch (Exception exception) {
                    bqj.this.d(exception.toString());
                }
                if (bqj.this.m) {
                    bqj.this.a(bqj$a.i);
                } else {
                    bqj.this.a(bqj$a.b);
                }
            } else {
                ErrorCode errorCode2;
                bqj.this.a(bqj$a.i);
                String \u26032 = ErrorCode.getString(errorCode2);
                bqj.this.d(String.format("stopCallback got failure: %s", \u26032));
            }
        }

        @Override
        public void sendActionMetaDataCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.d(String.format("sendActionMetaDataCallback got failure: %s", string));
            }
        }

        @Override
        public void sendStartSpanMetaDataCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.d(String.format("sendStartSpanMetaDataCallback got failure: %s", string));
            }
        }

        @Override
        public void sendEndSpanMetaDataCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                String string = ErrorCode.getString(errorCode);
                bqj.this.d(String.format("sendEndSpanMetaDataCallback got failure: %s", string));
            }
        }
    };
    protected IStatCallbacks C = new IStatCallbacks(){

        @Override
        public void statCallback(StatType statType, long l2) {
        }
    };

    public void a(b b2) {
        this.c = b2;
    }

    public boolean b() {
        return this.l;
    }

    public void a(String string) {
        this.d = string;
    }

    public StreamInfo j() {
        return this.x;
    }

    public ChannelInfo l() {
        return this.v;
    }

    public boolean m() {
        return this.o == bqj$a.k || this.o == bqj$a.m;
    }

    public boolean n() {
        return this.o == bqj$a.i;
    }

    public boolean o() {
        return this.o == bqj$a.n;
    }

    public boolean p() {
        return this.o == bqj$a.m;
    }

    public boolean q() {
        return this.m;
    }

    public IngestServer s() {
        return this.t;
    }

    public void a(IngestServer ingestServer) {
        this.t = ingestServer;
    }

    public IngestList t() {
        return this.s;
    }

    public void a(float f2) {
        this.i.setVolume(AudioDeviceType.TTV_RECORDER_DEVICE, f2);
    }

    public void b(float f2) {
        this.i.setVolume(AudioDeviceType.TTV_PLAYBACK_DEVICE, f2);
    }

    public bql w() {
        return this.A;
    }

    public long x() {
        return this.i.getStreamTime();
    }

    protected boolean y() {
        return true;
    }

    public ErrorCode A() {
        return this.G;
    }

    public bqj() {
        this.h = Core.getInstance();
        if (Core.getInstance() == null) {
            this.h = new Core(new StandardCoreAPI());
        }
        this.i = new Stream(new DesktopStreamAPI());
    }

    protected PixelFormat B() {
        return PixelFormat.TTV_PF_RGBA;
    }

    public boolean C() {
        if (this.l) {
            return false;
        }
        this.i.setStreamCallbacks(this.B);
        ErrorCode errorCode = this.h.initialize(this.d, System.getProperty("java.library.path"));
        if (!this.a(errorCode)) {
            this.i.setStreamCallbacks(null);
            this.G = errorCode;
            return false;
        }
        errorCode = this.h.setTraceLevel(MessageLevel.TTV_ML_ERROR);
        if (!this.a(errorCode)) {
            this.i.setStreamCallbacks(null);
            this.h.shutdown();
            this.G = errorCode;
            return false;
        }
        if (ErrorCode.succeeded(errorCode)) {
            this.l = true;
            this.a(bqj$a.b);
            return true;
        }
        this.G = errorCode;
        this.h.shutdown();
        return false;
    }

    public boolean D() {
        if (!this.l) {
            return true;
        }
        if (this.o()) {
            return false;
        }
        this.n = true;
        this.F();
        this.i.setStreamCallbacks(null);
        this.i.setStatCallbacks(null);
        ErrorCode errorCode = this.h.shutdown();
        this.a(errorCode);
        this.l = false;
        this.n = false;
        this.a(bqj$a.a);
        return true;
    }

    public void E() {
        if (this.o != bqj$a.a) {
            if (this.A != null) {
                this.A.m();
            }
            while (this.A != null) {
                try {
                    Thread.sleep(200L);
                }
                catch (Exception exception) {
                    this.d(exception.toString());
                }
                this.K();
            }
            this.D();
        }
    }

    public boolean a(String string, AuthToken authToken) {
        if (this.o()) {
            return false;
        }
        this.F();
        if (string == null || string.isEmpty()) {
            this.d("Username must be valid");
            return false;
        }
        if (authToken == null || authToken.data == null || authToken.data.isEmpty()) {
            this.d("Auth token must be valid");
            return false;
        }
        this.p = string;
        this.u = authToken;
        if (this.b()) {
            this.a(bqj$a.d);
        }
        return true;
    }

    public boolean F() {
        if (this.o()) {
            return false;
        }
        if (this.m()) {
            this.i.stop(false);
        }
        this.p = "";
        this.u = new AuthToken();
        if (!this.m) {
            return false;
        }
        this.m = false;
        if (!this.n) {
            try {
                if (this.c != null) {
                    this.c.a();
                }
            }
            catch (Exception exception) {
                this.d(exception.toString());
            }
        }
        this.a(bqj$a.b);
        return true;
    }

    public boolean a(String string, String string2, String string3) {
        if (!this.m) {
            return false;
        }
        if (string == null || string.equals("")) {
            string = this.p;
        }
        if (string2 == null) {
            string2 = "";
        }
        if (string3 == null) {
            string3 = "";
        }
        StreamInfoForSetting streamInfoForSetting = new StreamInfoForSetting();
        streamInfoForSetting.streamTitle = string3;
        streamInfoForSetting.gameName = string2;
        ErrorCode \u26032 = this.i.setStreamInfo(this.u, string, streamInfoForSetting);
        this.a(\u26032);
        return ErrorCode.succeeded(\u26032);
    }

    public boolean G() {
        if (!this.m()) {
            return false;
        }
        ErrorCode errorCode = this.i.runCommercial(this.u);
        this.a(errorCode);
        return ErrorCode.succeeded(errorCode);
    }

    public VideoParams a(int n2, int n3, float f2, float f3) {
        int[] nArray = this.i.getMaxResolution(n2, n3, f2, f3);
        VideoParams \u26032 = new VideoParams();
        \u26032.maxKbps = n2;
        \u26032.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
        \u26032.pixelFormat = this.B();
        \u26032.targetFps = n3;
        \u26032.outputWidth = nArray[0];
        \u26032.outputHeight = nArray[1];
        \u26032.disableAdaptiveBitrate = false;
        \u26032.verticalFlip = false;
        return \u26032;
    }

    public boolean a(VideoParams videoParams) {
        if (videoParams == null || !this.n()) {
            return false;
        }
        this.q = videoParams.clone();
        this.r = new AudioParams();
        this.r.enableMicCapture = this.r.audioEnabled = this.g && this.y();
        this.r.enablePlaybackCapture = this.r.audioEnabled;
        this.r.enablePassthroughAudio = false;
        if (!this.O()) {
            this.q = null;
            this.r = null;
            return false;
        }
        ErrorCode errorCode = this.i.start(videoParams, this.r, this.t, StartFlags.None, true);
        if (ErrorCode.failed(errorCode)) {
            this.P();
            String string = ErrorCode.getString(errorCode);
            this.d(String.format("Error while starting to broadcast: %s", string));
            this.q = null;
            this.r = null;
            return false;
        }
        this.a(bqj$a.j);
        return true;
    }

    public boolean H() {
        if (!this.m()) {
            return false;
        }
        ErrorCode errorCode = this.i.stop(true);
        if (ErrorCode.failed(errorCode)) {
            String string = ErrorCode.getString(errorCode);
            this.d(String.format("Error while stopping the broadcast: %s", string));
            return false;
        }
        this.a(bqj$a.l);
        return ErrorCode.succeeded(errorCode);
    }

    public boolean I() {
        if (!this.m()) {
            return false;
        }
        ErrorCode errorCode = this.i.pauseVideo();
        if (ErrorCode.failed(errorCode)) {
            this.H();
            String string = ErrorCode.getString(errorCode);
            this.d(String.format("Error pausing stream: %s\n", string));
        } else {
            this.a(bqj$a.m);
        }
        return ErrorCode.succeeded(errorCode);
    }

    public boolean J() {
        if (!this.p()) {
            return false;
        }
        this.a(bqj$a.k);
        return true;
    }

    public boolean a(String string, long l2, String string2, String string3) {
        ErrorCode errorCode = this.i.sendActionMetaData(this.u, string, l2, string2, string3);
        if (ErrorCode.failed(errorCode)) {
            String string4 = ErrorCode.getString(errorCode);
            this.d(String.format("Error while sending meta data: %s\n", string4));
            return false;
        }
        return true;
    }

    public long b(String string, long l2, String string2, String string3) {
        long l3 = this.i.sendStartSpanMetaData(this.u, string, l2, string2, string3);
        if (l3 == -1L) {
            this.d(String.format("Error in SendStartSpanMetaData\n", new Object[0]));
        }
        return l3;
    }

    public boolean a(String string, long l2, long l3, String string2, String string3) {
        if (l3 == -1L) {
            this.d(String.format("Invalid sequence id: %d\n", l3));
            return false;
        }
        ErrorCode errorCode = this.i.sendEndSpanMetaData(this.u, string, l2, l3, string2, string3);
        if (ErrorCode.failed(errorCode)) {
            String string4 = ErrorCode.getString(errorCode);
            this.d(String.format("Error in SendStopSpanMetaData: %s\n", string4));
            return false;
        }
        return true;
    }

    protected void a(a a2) {
        if (a2 == this.o) {
            return;
        }
        this.o = a2;
        try {
            if (this.c != null) {
                this.c.a(a2);
            }
        }
        catch (Exception exception) {
            this.d(exception.toString());
        }
    }

    public void K() {
        if (this.i == null || !this.l) {
            return;
        }
        ErrorCode errorCode = this.i.pollTasks();
        this.a(errorCode);
        if (this.o()) {
            this.A.k();
            if (this.A.f()) {
                this.A = null;
                this.a(bqj$a.i);
            }
        }
        switch (this.o) {
            case d: {
                this.a(bqj$a.e);
                errorCode = this.i.login(this.u);
                if (!ErrorCode.failed(errorCode)) break;
                String string = ErrorCode.getString(errorCode);
                this.d(String.format("Error in TTV_Login: %s\n", string));
                break;
            }
            case f: {
                this.a(bqj$a.g);
                errorCode = this.i.getIngestServers(this.u);
                if (!ErrorCode.failed(errorCode)) break;
                this.a(bqj$a.f);
                String \u26032 = ErrorCode.getString(errorCode);
                this.d(String.format("Error in TTV_GetIngestServers: %s\n", \u26032));
                break;
            }
            case h: {
                String \u26033;
                this.a(bqj$a.i);
                errorCode = this.i.getUserInfo(this.u);
                if (ErrorCode.failed(errorCode)) {
                    \u26033 = ErrorCode.getString(errorCode);
                    this.d(String.format("Error in TTV_GetUserInfo: %s\n", \u26033));
                }
                this.L();
                errorCode = this.i.getArchivingState(this.u);
                if (!ErrorCode.failed(errorCode)) break;
                \u26033 = ErrorCode.getString(errorCode);
                this.d(String.format("Error in TTV_GetArchivingState: %s\n", \u26033));
                break;
            }
            case j: 
            case l: {
                break;
            }
            case g: 
            case c: 
            case b: 
            case a: 
            case n: {
                break;
            }
            case m: 
            case k: {
                this.L();
                break;
            }
        }
    }

    protected void L() {
        long l2 = System.nanoTime();
        \u2603 = (l2 - this.z) / 1000000000L;
        if (\u2603 < 30L) {
            return;
        }
        this.z = l2;
        ErrorCode \u26032 = this.i.getStreamInfo(this.u, this.p);
        if (ErrorCode.failed(\u26032)) {
            String string = ErrorCode.getString(\u26032);
            this.d(String.format("Error in TTV_GetStreamInfo: %s", string));
        }
    }

    public bql M() {
        if (!this.n() || this.s == null) {
            return null;
        }
        if (this.o()) {
            return null;
        }
        this.A = new bql(this.i, this.s);
        this.A.j();
        this.a(bqj$a.n);
        return this.A;
    }

    protected boolean O() {
        for (int i2 = 0; i2 < 3; ++i2) {
            FrameBuffer frameBuffer = this.i.allocateFrameBuffer(this.q.outputWidth * this.q.outputHeight * 4);
            if (!frameBuffer.getIsValid()) {
                this.d(String.format("Error while allocating frame buffer", new Object[0]));
                return false;
            }
            this.j.add(frameBuffer);
            this.k.add(frameBuffer);
        }
        return true;
    }

    protected void P() {
        for (int i2 = 0; i2 < this.j.size(); ++i2) {
            FrameBuffer frameBuffer = this.j.get(i2);
            frameBuffer.free();
        }
        this.k.clear();
        this.j.clear();
    }

    public FrameBuffer Q() {
        if (this.k.size() == 0) {
            this.d(String.format("Out of free buffers, this should never happen", new Object[0]));
            return null;
        }
        FrameBuffer frameBuffer = this.k.get(this.k.size() - 1);
        this.k.remove(this.k.size() - 1);
        return frameBuffer;
    }

    public void a(FrameBuffer frameBuffer) {
        try {
            this.i.captureFrameBuffer_ReadPixels(frameBuffer);
        }
        catch (Throwable throwable) {
            b b2 = a(throwable, "Trying to submit a frame to Twitch");
            c \u26032 = b2.a("Broadcast State");
            \u26032.a("Last reported errors", Arrays.toString(E.c()));
            \u26032.a("Buffer", frameBuffer);
            \u26032.a("Free buffer count", this.k.size());
            \u26032.a("Capture buffer count", this.j.size());
            throw new e(b2);
        }
    }

    public ErrorCode b(FrameBuffer frameBuffer) {
        if (this.p()) {
            this.J();
        } else if (!this.m()) {
            return ErrorCode.TTV_EC_STREAM_NOT_STARTED;
        }
        ErrorCode errorCode = this.i.submitVideoFrame(frameBuffer);
        if (errorCode != ErrorCode.TTV_EC_SUCCESS) {
            String string = ErrorCode.getString(errorCode);
            if (ErrorCode.succeeded(errorCode)) {
                this.e(String.format("Warning in SubmitTexturePointer: %s\n", string));
            } else {
                this.d(String.format("Error in SubmitTexturePointer: %s\n", string));
                this.H();
            }
            if (this.c != null) {
                this.c.b(errorCode);
            }
        }
        return errorCode;
    }

    protected boolean a(ErrorCode errorCode) {
        if (ErrorCode.failed(errorCode)) {
            this.d(ErrorCode.getString(errorCode));
            return false;
        }
        return true;
    }

    protected void d(String string) {
        this.F = string;
        E.a("<Error> " + string);
        D.error(bqn.a, "[Broadcast controller] {}", string);
    }

    protected void e(String string) {
        E.a("<Warning> " + string);
        D.warn(bqn.a, "[Broadcast controller] {}", string);
    }

    public static interface b {
        public void a(ErrorCode var1, AuthToken var2);

        public void a(ErrorCode var1);

        public void a(ErrorCode var1, GameInfo[] var2);

        public void a(a var1);

        public void a();

        public void a(StreamInfo var1);

        public void a(IngestList var1);

        public void b(ErrorCode var1);

        public void b();

        public void c();

        public void c(ErrorCode var1);
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        j,
        k,
        l,
        m,
        n;

    }
}

