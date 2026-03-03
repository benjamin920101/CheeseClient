/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class WebcamPickerCellRenderer
extends JLabel
implements ListCellRenderer {
    private static final long serialVersionUID = 1L;
    private static final ImageIcon ICON = new ImageIcon(WebcamPickerCellRenderer.class.getResource("/com/github/sarxos/webcam/icons/camera-icon.png"));

    public WebcamPickerCellRenderer() {
        this.setOpaque(true);
        this.setHorizontalAlignment(2);
        this.setVerticalAlignment(0);
        this.setIcon(ICON);
    }

    public Component getListCellRendererComponent(JList list, Object value, int i2, boolean selected, boolean focused) {
        Webcam webcam = (Webcam)value;
        if (selected) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        this.setText(webcam.getName());
        this.setFont(list.getFont());
        return this;
    }
}

