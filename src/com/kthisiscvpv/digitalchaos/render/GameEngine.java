package com.kthisiscvpv.digitalchaos.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.kthisiscvpv.digitalchaos.misc.RenderEngine;
import com.kthisiscvpv.digitalchaos.panel.ScreenFrame;

public class GameEngine extends RenderEngine implements Runnable {

    private ScreenFrame frame;
    private Thread instance;

    private char[][] map = new char[][] { { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#' }, { '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', ' ', '#' }, { '#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#' }, { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' }, };

    private double boxSideLength;

    private double playerX;
    private double playerY;

    private double playerXVel;
    private double playerYVel;

    private long shootDelay;
    private long lastShot;

    private boolean leftKey = false;
    private boolean rightKey = false;

    private boolean upKey = false;
    private boolean downKey = false;

    public GameEngine(ScreenFrame frame) {
        this.frame = frame;
        this.boxSideLength = frame.getHeight() / 10;

        this.playerX = (map[0].length / 2) + 0.5;
        this.playerY = (map.length / 2) + 0.5;

        this.playerXVel = 0.05;
        this.playerYVel = 0.05;

        this.frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        upKey = false;
                        break;
                    case KeyEvent.VK_S:
                        downKey = false;
                        break;
                    case KeyEvent.VK_A:
                        leftKey = false;
                        break;
                    case KeyEvent.VK_D:
                        rightKey = false;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        upKey = true;
                        break;
                    case KeyEvent.VK_S:
                        downKey = true;
                        break;
                    case KeyEvent.VK_A:
                        leftKey = true;
                        break;
                    case KeyEvent.VK_D:
                        rightKey = true;
                        break;
                    default:
                        break;
                }
            }
        });

        this.frame.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO: 
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        
        this.instance = new Thread(this);
        this.instance.start();
    }

    public Thread getInstance() {
        return this.instance;
    }

    public double getPlayerX() {
        return this.playerX;
    }

    public double getPlayerY() {
        return this.playerY;
    }

    public long getShootDelay() {
        return this.shootDelay;
    }

    public void setShootDelay(long shootDelay) {
        this.shootDelay = shootDelay;
    }

    public long getLastShot() {
        return this.lastShot;
    }

    public void setLastShot(long lastShot) {
        this.lastShot = lastShot;
    }

    public void render(Graphics graphics) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int floorX = (int) Math.floor(this.playerX);
        int floorY = (int) Math.floor(this.playerY);

        double overflowX = this.playerX - floorX;
        double overflowY = this.playerY - floorY;

        int relativityX = (int) (boxSideLength * overflowX);
        int relativityY = (int) (boxSideLength * overflowY);

        int mapStartX = (int) ((size.width / 2) - relativityX - (this.boxSideLength * floorX));
        int mapStartY = (int) ((size.height / 2) - relativityY - (this.boxSideLength * floorY));
        int roundedLength = (int) this.boxSideLength;

        // System.out.println("Box length is considered to be " + boxSideLength + "px.");
        // System.out.println("Player is at " + this.playerX + ", " + this.playerY + ".");
        // System.out.println("Player is overflowing " + overflowX + ", " + overflowY + ".");
        // System.out.println("Player relativity is " + relativityX + ", " + relativityY + ".");
        // System.out.println("Map starts at " + mapStartX + ", " + mapStartY + ".");

        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                graphics.setColor(Color.BLACK);
                if (this.map[y][x] == '#') {
                    graphics.fillRect(mapStartX + (roundedLength * x), mapStartY + (roundedLength * y), roundedLength, roundedLength);
                }

                // graphics.setColor(Color.GRAY);
                // graphics.drawRect(mapStartX + (boxSideLength * x), mapStartY + (boxSideLength * y), boxSideLength, boxSideLength);
            }
        }

        int centerX = size.width / 2;
        int centerY = size.height / 2;

        graphics.setColor(Color.RED);
        graphics.fillOval(centerX - 25, centerY - 25, 50, 50);
    }

    public boolean isValidMovement(double x, double y, int bounds, char[][] map) {
        double movementUnit = (((double) bounds) / this.boxSideLength);

        int xA = (int) Math.floor(x - movementUnit);
        int yA = (int) Math.floor(y - movementUnit);

        if (yA > -1 && map.length > yA && xA > -1 && map[yA].length > xA && this.map[yA][xA] == '#') {
            return false;
        }

        int xB = (int) Math.floor(x + movementUnit);
        int yB = (int) Math.floor(y - movementUnit);

        if (yB > -1 && map.length > yB && xB > -1 && map[yB].length > xB && this.map[yB][xB] == '#') {
            return false;
        }

        int xC = (int) Math.floor(x - movementUnit);
        int yC = (int) Math.floor(y + movementUnit);

        if (yC > -1 && map.length > yC && xC > -1 && map[yC].length > xC && this.map[yC][xC] == '#') {
            return false;
        }

        int xD = (int) Math.floor(x + movementUnit);
        int yD = (int) Math.floor(y + movementUnit);

        return !(yD > -1 && map.length > yD && xD > -1 && map[yD].length > xD && this.map[yD][xD] == '#');
    }

    @Override
    public void run() {
        while (true) {
            if (this.rightKey) {
                double newX = this.playerX + this.playerXVel;
                if (this.isValidMovement(newX, this.playerY, 25, this.map)) {
                    this.playerX = newX;
                }
            }

            if (this.leftKey) {
                double newX = this.playerX - this.playerXVel;
                if (this.isValidMovement(newX, this.playerY, 25, this.map)) {
                    this.playerX = newX;
                }
            }

            if (this.upKey) {
                double newY = this.playerY - this.playerYVel;
                if (this.isValidMovement(this.playerX, newY, 25, this.map)) {
                    this.playerY = newY;
                }
            }

            if (this.downKey) {
                double newY = this.playerY + this.playerYVel;
                if (this.isValidMovement(this.playerX, newY, 25, this.map)) {
                    this.playerY = newY;
                }
            }

            this.frame.getPanel().repaint();

            try {
                Thread.sleep(20L);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
