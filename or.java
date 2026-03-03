/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class or {
    private final Map<String, Object> a = Maps.newHashMap();
    private final Map<String, Object> b = Maps.newHashMap();
    private final String c = UUID.randomUUID().toString();
    private final URL d;
    private final os e;
    private final Timer f = new Timer("Snooper Timer", true);
    private final Object g = new Object();
    private final long h;
    private boolean i;
    private int j;

    public or(String string, os os2, long l2) {
        try {
            this.d = new URL("http://snoop.minecraft.net/" + string + "?version=" + 2);
        }
        catch (MalformedURLException malformedURLException) {
            throw new IllegalArgumentException();
        }
        this.e = os2;
        this.h = l2;
    }

    public void a() {
        if (this.i) {
            return;
        }
        this.i = true;
        this.h();
        this.f.schedule(new TimerTask(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                HashMap<String, Object> hashMap;
                if (!or.this.e.ad()) {
                    return;
                }
                Object object = or.this.g;
                synchronized (object) {
                    hashMap = Maps.newHashMap(or.this.b);
                    if (or.this.j == 0) {
                        hashMap.putAll(or.this.a);
                    }
                    hashMap.put("snooper_count", or.this.j++);
                    hashMap.put("snooper_token", or.this.c);
                }
                nj.a(or.this.d, hashMap, true);
            }
        }, 0L, 900000L);
    }

    private void h() {
        this.i();
        this.a("snooper_token", this.c);
        this.b("snooper_token", this.c);
        this.b("os_name", System.getProperty("os.name"));
        this.b("os_version", System.getProperty("os.version"));
        this.b("os_architecture", System.getProperty("os.arch"));
        this.b("java_version", System.getProperty("java.version"));
        this.a("version", "1.8.8");
        this.e.b(this);
    }

    private void i() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> \u26032 = runtimeMXBean.getInputArguments();
        int \u26033 = 0;
        for (String string : \u26032) {
            if (!string.startsWith("-X")) continue;
            this.a("jvm_arg[" + \u26033++ + "]", string);
        }
        this.a("jvm_args", \u26033);
    }

    public void b() {
        this.b("memory_total", Runtime.getRuntime().totalMemory());
        this.b("memory_max", Runtime.getRuntime().maxMemory());
        this.b("memory_free", Runtime.getRuntime().freeMemory());
        this.b("cpu_cores", Runtime.getRuntime().availableProcessors());
        this.e.a(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(String string, Object object) {
        Object object2 = this.g;
        synchronized (object2) {
            this.b.put(string, object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(String string, Object object) {
        Object object2 = this.g;
        synchronized (object2) {
            this.a.put(string, object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = Maps.newLinkedHashMap();
        Object object = this.g;
        synchronized (object) {
            this.b();
            for (Map.Entry<String, Object> entry : this.a.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue().toString());
            }
            for (Map.Entry<String, Object> entry : this.b.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return linkedHashMap;
    }

    public boolean d() {
        return this.i;
    }

    public void e() {
        this.f.cancel();
    }

    public String f() {
        return this.c;
    }

    public long g() {
        return this.h;
    }
}

