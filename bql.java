/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.AudioParams;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.PixelFormat;
import tv.twitch.broadcast.RTMPState;
import tv.twitch.broadcast.StartFlags;
import tv.twitch.broadcast.StatType;
import tv.twitch.broadcast.Stream;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.UserInfo;
import tv.twitch.broadcast.VideoParams;

public class bql {
    protected a a = null;
    protected Stream b = null;
    protected IngestList c = null;
    protected b d = bql$b.a;
    protected long e = 8000L;
    protected long f = 2000L;
    protected long g = 0L;
    protected RTMPState h = RTMPState.Invalid;
    protected VideoParams i = null;
    protected AudioParams j = null;
    protected long k = 0L;
    protected List<FrameBuffer> l = null;
    protected boolean m = false;
    protected IStreamCallbacks n = null;
    protected IStatCallbacks o = null;
    protected IngestServer p = null;
    protected boolean q = false;
    protected boolean r = false;
    protected int s = -1;
    protected int t = 0;
    protected long u = 0L;
    protected float v = 0.0f;
    protected float w = 0.0f;
    protected boolean x = false;
    protected boolean y = false;
    protected boolean z = false;
    protected IStreamCallbacks A = new IStreamCallbacks(){

        @Override
        public void requestAuthTokenCallback(ErrorCode errorCode, AuthToken authToken) {
        }

        @Override
        public void loginCallback(ErrorCode errorCode, ChannelInfo channelInfo) {
        }

        @Override
        public void getIngestServersCallback(ErrorCode errorCode, IngestList ingestList) {
        }

        @Override
        public void getUserInfoCallback(ErrorCode errorCode, UserInfo userInfo) {
        }

        @Override
        public void getStreamInfoCallback(ErrorCode errorCode, StreamInfo streamInfo) {
        }

        @Override
        public void getArchivingStateCallback(ErrorCode errorCode, ArchivingState archivingState) {
        }

        @Override
        public void runCommercialCallback(ErrorCode errorCode) {
        }

        @Override
        public void setStreamInfoCallback(ErrorCode errorCode) {
        }

        @Override
        public void getGameNameListCallback(ErrorCode errorCode, GameInfoList gameInfoList) {
        }

        @Override
        public void bufferUnlockCallback(long l2) {
        }

        @Override
        public void startCallback(ErrorCode errorCode) {
            bql.this.y = false;
            if (ErrorCode.succeeded(errorCode)) {
                bql.this.x = true;
                bql.this.k = System.currentTimeMillis();
                bql.this.a(bql$b.c);
            } else {
                bql.this.m = false;
                bql.this.a(bql$b.e);
            }
        }

        @Override
        public void stopCallback(ErrorCode errorCode) {
            if (ErrorCode.failed(errorCode)) {
                System.out.println("IngestTester.stopCallback failed to stop - " + bql.this.p.serverName + ": " + errorCode.toString());
            }
            bql.this.z = false;
            bql.this.x = false;
            bql.this.a(bql$b.e);
            bql.this.p = null;
            if (bql.this.q) {
                bql.this.a(bql$b.g);
            }
        }

        @Override
        public void sendActionMetaDataCallback(ErrorCode errorCode) {
        }

        @Override
        public void sendStartSpanMetaDataCallback(ErrorCode errorCode) {
        }

        @Override
        public void sendEndSpanMetaDataCallback(ErrorCode errorCode) {
        }
    };
    protected IStatCallbacks B = new IStatCallbacks(){

        @Override
        public void statCallback(StatType statType, long l2) {
            switch (statType) {
                case TTV_ST_RTMPSTATE: {
                    bql.this.h = RTMPState.lookupValue((int)l2);
                    break;
                }
                case TTV_ST_RTMPDATASENT: {
                    bql.this.g = l2;
                }
            }
        }
    };

    public void a(a a2) {
        this.a = a2;
    }

    public IngestServer c() {
        return this.p;
    }

    public int d() {
        return this.s;
    }

    public boolean f() {
        return this.d == bql$b.f || this.d == bql$b.h || this.d == bql$b.i;
    }

    public float i() {
        return this.w;
    }

    public bql(Stream stream, IngestList ingestList) {
        this.b = stream;
        this.c = ingestList;
    }

    public void j() {
        if (this.d != bql$b.a) {
            return;
        }
        this.s = 0;
        this.q = false;
        this.r = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.o = this.b.getStatCallbacks();
        this.b.setStatCallbacks(this.B);
        this.n = this.b.getStreamCallbacks();
        this.b.setStreamCallbacks(this.A);
        this.i = new VideoParams();
        this.i.targetFps = 60;
        this.i.maxKbps = 3500;
        this.i.outputWidth = 1280;
        this.i.outputHeight = 720;
        this.i.pixelFormat = PixelFormat.TTV_PF_BGRA;
        this.i.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
        this.i.disableAdaptiveBitrate = true;
        this.i.verticalFlip = false;
        this.b.getDefaultParams(this.i);
        this.j = new AudioParams();
        this.j.audioEnabled = false;
        this.j.enableMicCapture = false;
        this.j.enablePlaybackCapture = false;
        this.j.enablePassthroughAudio = false;
        this.l = Lists.newArrayList();
        int n2 = 3;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            FrameBuffer frameBuffer = this.b.allocateFrameBuffer(this.i.outputWidth * this.i.outputHeight * 4);
            if (!frameBuffer.getIsValid()) {
                this.p();
                this.a(bql$b.i);
                return;
            }
            this.l.add(frameBuffer);
            this.b.randomizeFrameBuffer(frameBuffer);
        }
        this.a(bql$b.b);
        this.k = System.currentTimeMillis();
    }

    public void k() {
        if (this.f() || this.d == bql$b.a) {
            return;
        }
        if (this.y || this.z) {
            return;
        }
        switch (this.d) {
            case b: 
            case e: {
                if (this.p != null) {
                    if (this.r || !this.m) {
                        this.p.bitrateKbps = 0.0f;
                    }
                    this.b(this.p);
                    break;
                }
                this.k = 0L;
                this.r = false;
                this.m = true;
                if (this.d != bql$b.b) {
                    ++this.s;
                }
                if (this.s < this.c.getServers().length) {
                    this.p = this.c.getServers()[this.s];
                    this.a(this.p);
                    break;
                }
                this.a(bql$b.f);
                break;
            }
            case c: 
            case d: {
                this.c(this.p);
                break;
            }
            case g: {
                this.a(bql$b.h);
                break;
            }
        }
        this.o();
        if (this.d == bql$b.h || this.d == bql$b.f) {
            this.p();
        }
    }

    public void m() {
        if (this.f() || this.q) {
            return;
        }
        this.q = true;
        if (this.p != null) {
            this.p.bitrateKbps = 0.0f;
        }
    }

    protected boolean a(IngestServer ingestServer) {
        this.m = true;
        this.g = 0L;
        this.h = RTMPState.Idle;
        this.p = ingestServer;
        this.y = true;
        this.a(bql$b.c);
        ErrorCode errorCode = this.b.start(this.i, this.j, ingestServer, StartFlags.TTV_Start_BandwidthTest, true);
        if (ErrorCode.failed(errorCode)) {
            this.y = false;
            this.m = false;
            this.a(bql$b.e);
            return false;
        }
        this.u = this.g;
        ingestServer.bitrateKbps = 0.0f;
        this.t = 0;
        return true;
    }

    protected void b(IngestServer ingestServer) {
        if (this.y) {
            this.r = true;
        } else if (this.x) {
            this.z = true;
            ErrorCode errorCode = this.b.stop(true);
            if (ErrorCode.failed(errorCode)) {
                this.A.stopCallback(ErrorCode.TTV_EC_SUCCESS);
                System.out.println("Stop failed: " + errorCode.toString());
            }
            this.b.pollStats();
        } else {
            this.A.stopCallback(ErrorCode.TTV_EC_SUCCESS);
        }
    }

    protected long n() {
        return System.currentTimeMillis() - this.k;
    }

    protected void o() {
        float f2 = this.n();
        switch (this.d) {
            case b: 
            case c: 
            case a: 
            case f: 
            case h: 
            case i: {
                this.w = 0.0f;
                break;
            }
            case e: {
                this.w = 1.0f;
                break;
            }
            default: {
                this.w = f2 / (float)this.e;
            }
        }
        switch (this.d) {
            case f: 
            case h: 
            case i: {
                this.v = 1.0f;
                break;
            }
            default: {
                this.v = (float)this.s / (float)this.c.getServers().length;
                this.v += this.w / (float)this.c.getServers().length;
            }
        }
    }

    protected boolean c(IngestServer ingestServer) {
        if (this.r || this.q || this.n() >= this.e) {
            this.a(bql$b.e);
            return true;
        }
        if (this.y || this.z) {
            return true;
        }
        ErrorCode errorCode = this.b.submitVideoFrame(this.l.get(this.t));
        if (ErrorCode.failed(errorCode)) {
            this.m = false;
            this.a(bql$b.e);
            return false;
        }
        this.t = (this.t + 1) % this.l.size();
        this.b.pollStats();
        if (this.h == RTMPState.SendVideo) {
            this.a(bql$b.d);
            long l2 = this.n();
            if (l2 > 0L && this.g > this.u) {
                ingestServer.bitrateKbps = (float)(this.g * 8L) / (float)this.n();
                this.u = this.g;
            }
        }
        return true;
    }

    protected void p() {
        this.p = null;
        if (this.l != null) {
            for (int i2 = 0; i2 < this.l.size(); ++i2) {
                this.l.get(i2).free();
            }
            this.l = null;
        }
        if (this.b.getStatCallbacks() == this.B) {
            this.b.setStatCallbacks(this.o);
            this.o = null;
        }
        if (this.b.getStreamCallbacks() == this.A) {
            this.b.setStreamCallbacks(this.n);
            this.n = null;
        }
    }

    protected void a(b b2) {
        if (b2 == this.d) {
            return;
        }
        this.d = b2;
        if (this.a != null) {
            this.a.a(this, b2);
        }
    }

    public static interface a {
        public void a(bql var1, b var2);
    }

    public static enum b {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i;

    }
}

