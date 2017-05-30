package com.kthisiscvpv.digitalchaos.panel;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.kthisiscvpv.digitalchaos.DigitalChaos;

@SuppressWarnings("serial")
public class ScreenFrame extends JFrame {

    private DigitalChaos instance;
    private ScreenPanel panel;

    public ScreenFrame(DigitalChaos instance) {
        this.instance = instance;

        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);

        this.panel = new ScreenPanel(this);
        this.setSize(this.panel.getSize());
        this.panel.setLocation(0, 0);
        this.add(this.panel);
    }

    public DigitalChaos getInstance() {
        return this.instance;
    }
    
    public ScreenPanel getPanel(){
        return this.panel;
    }

    public void clearFrame() {
        for (Component component : this.getContentPane().getComponents().clone()) {
            if (component != this.panel) {
                this.getContentPane().remove(component);
            }
        }

        for(KeyListener kl : this.getKeyListeners().clone()) {
            this.removeKeyListener(kl);
        }
        
        for(MouseListener ml : this.getMouseListeners().clone()) {
            this.removeMouseListener(ml);
        }

        this.panel.clearPanel();
        this.repaint();
    }
}
