/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamViewer
extends JFrame
implements Runnable,
WebcamListener,
WindowListener,
Thread.UncaughtExceptionHandler,
ItemListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(WebcamViewer.class);
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private WebcamPicker picker = null;

    public WebcamViewer() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        this.setTitle("Webcam Capture Viewer");
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.picker = new WebcamPicker();
        this.picker.addItemListener(this);
        this.webcam = this.picker.getSelectedWebcam();
        if (this.webcam == null) {
            LOG.error("No webcams found");
            System.exit(1);
        }
        this.webcam.setViewSize(WebcamResolution.VGA.getSize());
        this.webcam.addWebcamListener(this);
        this.panel = new WebcamPanel(this.webcam, false);
        this.panel.setFPSDisplayed(true);
        this.add((Component)this.picker, "North");
        this.add((Component)this.panel, "Center");
        this.pack();
        this.setVisible(true);
        Thread t2 = new Thread(){

            @Override
            public void run() {
                WebcamViewer.this.panel.start();
            }
        };
        t2.setName("webcam-viewer-starter");
        t2.setDaemon(true);
        t2.setUncaughtExceptionHandler(this);
        t2.start();
    }

    @Override
    public void webcamOpen(WebcamEvent we2) {
        LOG.info("Webcam open");
    }

    @Override
    public void webcamClosed(WebcamEvent we2) {
        LOG.info("Webcam closed");
    }

    @Override
    public void webcamDisposed(WebcamEvent we2) {
        LOG.info("Webcam disposed");
    }

    @Override
    public void webcamImageObtained(WebcamEvent we2) {
    }

    @Override
    public void windowActivated(WindowEvent e2) {
    }

    @Override
    public void windowClosed(WindowEvent e2) {
        this.webcam.close();
    }

    @Override
    public void windowClosing(WindowEvent e2) {
    }

    @Override
    public void windowOpened(WindowEvent e2) {
    }

    @Override
    public void windowDeactivated(WindowEvent e2) {
    }

    @Override
    public void windowDeiconified(WindowEvent e2) {
        LOG.info("Webcam viewer resumed");
        this.panel.resume();
    }

    @Override
    public void windowIconified(WindowEvent e2) {
        LOG.info("Webcam viewer paused");
        this.panel.pause();
    }

    @Override
    public void uncaughtException(Thread t2, Throwable e2) {
        e2.printStackTrace();
        LOG.error(String.format("Exception in thread %s", t2.getName()), e2);
    }

    @Override
    public void itemStateChanged(ItemEvent e2) {
        if (e2.getItem() == this.webcam) {
            return;
        }
        if (this.webcam == null) {
            return;
        }
        final WebcamPanel tmp = this.panel;
        this.remove(this.panel);
        this.webcam.removeWebcamListener(this);
        this.webcam = (Webcam)e2.getItem();
        this.webcam.setViewSize(WebcamResolution.VGA.getSize());
        this.webcam.addWebcamListener(this);
        System.out.println("selected " + this.webcam.getName());
        this.panel = new WebcamPanel(this.webcam, false);
        this.add((Component)this.panel, "Center");
        Thread t2 = new Thread(){

            @Override
            public void run() {
                tmp.stop();
                WebcamViewer.this.panel.start();
            }
        };
        t2.setDaemon(true);
        t2.setUncaughtExceptionHandler(this);
        t2.start();
    }
}

