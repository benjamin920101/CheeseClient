/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bpq {
    private static final AtomicInteger a = new AtomicInteger(0);
    private static final Logger b = LogManager.getLogger();

    public static class b
    extends Thread {
        private final c a;
        private final InetAddress b;
        private final MulticastSocket c;

        public b(c c2) throws IOException {
            super("LanServerDetector #" + a.incrementAndGet());
            this.a = c2;
            this.setDaemon(true);
            this.c = new MulticastSocket(4445);
            this.b = InetAddress.getByName("224.0.2.60");
            this.c.setSoTimeout(5000);
            this.c.joinGroup(this.b);
        }

        @Override
        public void run() {
            byte[] byArray = new byte[1024];
            while (!this.isInterrupted()) {
                DatagramPacket datagramPacket = new DatagramPacket(byArray, byArray.length);
                try {
                    this.c.receive(datagramPacket);
                }
                catch (SocketTimeoutException \u26032) {
                    continue;
                }
                catch (IOException \u26033) {
                    b.error("Couldn't ping server", (Throwable)\u26033);
                    break;
                }
                String \u26034 = new String(datagramPacket.getData(), datagramPacket.getOffset(), datagramPacket.getLength());
                b.debug(datagramPacket.getAddress() + ": " + \u26034);
                this.a.a(\u26034, datagramPacket.getAddress());
            }
            try {
                this.c.leaveGroup(this.b);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            this.c.close();
        }
    }

    public static class a {
        private String a;
        private String b;
        private long c;

        public a(String string, String string2) {
            this.a = string;
            this.b = string2;
            this.c = ave.J();
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public void c() {
            this.c = ave.J();
        }
    }

    public static class c {
        private List<a> b = Lists.newArrayList();
        boolean a;

        public synchronized boolean a() {
            return this.a;
        }

        public synchronized void b() {
            this.a = false;
        }

        public synchronized List<a> c() {
            return Collections.unmodifiableList(this.b);
        }

        public synchronized void a(String string, InetAddress inetAddress) {
            String string2 = bpr.a(string);
            \u2603 = bpr.b(string);
            if (\u2603 == null) {
                return;
            }
            \u2603 = inetAddress.getHostAddress() + ":" + \u2603;
            boolean \u26032 = false;
            for (a a2 : this.b) {
                if (!a2.b().equals(\u2603)) continue;
                a2.c();
                \u26032 = true;
                break;
            }
            if (!\u26032) {
                this.b.add(new a(string2, \u2603));
                this.a = true;
            }
        }
    }
}

