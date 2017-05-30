package com.kthisiscvpv.digitalchaos.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class CButton implements MouseListener {

    private String name;
    private Font font;

    private int startX;
    private int startY;

    private int width;
    private int height;

    private JFrame frame;

    private List<Runnable> onClick = new ArrayList<Runnable>();

    public CButton(String name, int startX, int startY, int width, int height, JFrame frame) {
        this(name, new Font("Arial", Font.BOLD, 20), startX, startY, width, height, frame);
    }

    public CButton(String name, Font font, int startX, int startY, int width, int height, JFrame frame) {
        this.name = name;
        this.font = font;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;

        this.frame = frame;
        this.frame.addMouseListener(this);
    }

    public String getName() {
        return this.name;
    }

    public Font getFont() {
        return this.font;
    }

    public int getX() {
        return this.startX;
    }

    public int getY() {
        return this.startY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean inBounds(int x, int y) {
        int halfX = this.width / 2;
        int halfY = this.height / 2;
        return x >= (this.startX - halfX) && x <= (this.startX + halfX) && y >= (this.startY - halfY) && y <= (this.startY + halfY);
    }

    public void draw(Graphics graphics) {
        Point mouse = MouseInfo.getPointerInfo().getLocation();        
        this.draw(graphics, this.inBounds(mouse.x, mouse.y));
    }

    public void draw(Graphics graphics, boolean hover) {
        graphics.setColor(hover ? Color.GRAY : Color.WHITE);
        graphics.fillRect(this.startX - (this.width / 2), this.startY - (this.height / 2), this.width, this.height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(this.startX - (this.width / 2), this.startY - (this.height / 2), this.width, this.height);

        graphics.setFont(this.font);
        int textWidth = graphics.getFontMetrics().stringWidth(this.name);
        graphics.drawString(this.name, this.startX - (textWidth / 2), (int) (this.startY + (font.getSize() * 0.35)));
    }

    public void addRunnable(Runnable runnable) {
        if (!this.onClick.contains(runnable)) {
            this.onClick.add(runnable);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1 && inBounds(e.getX(), e.getY())) {
            for (Runnable runnable : this.onClick) {
                new Thread(runnable).start();
            }
        }
    }
}
