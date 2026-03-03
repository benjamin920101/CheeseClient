/*
 * Decompiled with CFR 0.152.
 */
import tv.twitch.broadcast.IngestServer;

public class azy
extends axu {
    private final axu a;
    private String f;
    private a g;

    public azy(axu axu2) {
        this.a = axu2;
    }

    @Override
    public void b() {
        this.f = bnq.a("options.stream.ingest.title", new Object[0]);
        this.g = new a(this.j);
        if (!this.j.Y().w()) {
            this.j.Y().u();
        }
        this.n.add(new avs(1, this.l / 2 - 155, this.m - 24 - 6, 150, 20, bnq.a("gui.done", new Object[0])));
        this.n.add(new avs(2, this.l / 2 + 5, this.m - 24 - 6, 150, 20, bnq.a("options.stream.ingest.reset", new Object[0])));
    }

    @Override
    public void k() {
        super.k();
        this.g.p();
    }

    @Override
    public void m() {
        if (this.j.Y().w()) {
            this.j.Y().v().m();
        }
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 1) {
            this.j.a(this.a);
        } else {
            this.j.t.R = "";
            this.j.t.b();
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.g.a(n2, n3, f2);
        this.a(this.q, this.f, this.l / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    class a
    extends awi {
        public a(ave ave2) {
            super(ave2, azy.this.l, azy.this.m, 32, azy.this.m - 35, (int)((double)ave2.k.a * 3.5));
            this.b(false);
        }

        @Override
        protected int b() {
            return this.a.Y().s().length;
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            this.a.t.R = this.a.Y().s()[n2].serverUrl;
            this.a.t.b();
        }

        @Override
        protected boolean a(int n2) {
            return this.a.Y().s()[n2].serverUrl.equals(this.a.t.R);
        }

        @Override
        protected void a() {
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            IngestServer ingestServer = this.a.Y().s()[n2];
            String \u26032 = ingestServer.serverUrl.replaceAll("\\{stream_key\\}", "");
            String \u26033 = (int)ingestServer.bitrateKbps + " kbps";
            String \u26034 = null;
            bql \u26035 = this.a.Y().v();
            if (\u26035 != null) {
                if (ingestServer == \u26035.c()) {
                    \u26032 = (Object)((Object)a.k) + \u26032;
                    \u26033 = (int)(\u26035.i() * 100.0f) + "%";
                } else if (n2 < \u26035.d()) {
                    if (ingestServer.bitrateKbps == 0.0f) {
                        \u26033 = (Object)((Object)a.m) + "Down!";
                    }
                } else {
                    \u26033 = (Object)((Object)a.q) + "1234" + (Object)((Object)a.v) + " kbps";
                }
            } else if (ingestServer.bitrateKbps == 0.0f) {
                \u26033 = (Object)((Object)a.m) + "Down!";
            }
            n3 -= 15;
            if (this.a(n2)) {
                \u26034 = (Object)((Object)a.j) + "(Preferred)";
            } else if (ingestServer.defaultServer) {
                \u26034 = (Object)((Object)a.k) + "(Default)";
            }
            azy.this.c(azy.this.q, ingestServer.serverName, n3 + 2, n4 + 5, 0xFFFFFF);
            azy.this.c(azy.this.q, \u26032, n3 + 2, n4 + ((azy)azy.this).q.a + 5 + 3, 0x303030);
            azy.this.c(azy.this.q, \u26033, this.d() - 5 - azy.this.q.a(\u26033), n4 + 5, 0x808080);
            if (\u26034 != null) {
                azy.this.c(azy.this.q, \u26034, this.d() - 5 - azy.this.q.a(\u26034), n4 + 5 + 3 + ((azy)azy.this).q.a, 0x808080);
            }
        }

        @Override
        protected int d() {
            return super.d() + 15;
        }
    }
}

