package com.kthisiscvpv.digitalchaos.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel {

    private ScreenFrame frame;
    private boolean clean;

    public ScreenPanel(ScreenFrame frame) {
        this.frame = frame;
        this.clean = false;

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size);
        this.setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.clean) {
            this.clean = false;
            return;
        }

        switch (this.frame.getInstance().getGameState()) {
            default:
                this.frame.getInstance().getRenderEngine().render(graphics);
        }
    }

    public void clearPanel() {
        this.clean = true;
        this.repaint();
    }

    public ScreenFrame getFrame() {
        return this.frame;
    }
}
