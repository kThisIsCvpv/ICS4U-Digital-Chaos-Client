package com.kthisiscvpv.digitalchaos.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kthisiscvpv.digitalchaos.misc.GameState;
import com.kthisiscvpv.digitalchaos.misc.Pixel;
import com.kthisiscvpv.digitalchaos.misc.RenderEngine;
import com.kthisiscvpv.digitalchaos.panel.ScreenFrame;
import com.kthisiscvpv.digitalchaos.util.CButton;

public class MainMenuEngine extends RenderEngine implements Runnable {

    private ScreenFrame frame;

    private List<Pixel> pixels = new ArrayList<Pixel>();
    private Thread instance;

    private Image titleImage;

    private CButton startButton;
    private CButton highscoreButton;
    private CButton optionsButton;
    private CButton creditsButton;
    private CButton exitButton;

    public MainMenuEngine(ScreenFrame frame) {
        this.frame = frame;
        this.titleImage = Toolkit.getDefaultToolkit().getImage("title_screen.png");

        Dimension size = this.frame.getSize();
        Font defaultFont = new Font("Arial", Font.BOLD, 20);

        // int buttonWidth = 300;
        // int buttonHeight = 75;
        // int buttonSpacer = 20;

        int buttonWidth = size.width / 6;
        int buttonHeight = size.height / 15;
        int buttonSpacer = size.height / 30;

        this.startButton = new CButton("Start Game", defaultFont, size.width / 2, size.height / 2, buttonWidth, buttonHeight, this.frame);
        this.startButton.addRunnable(new Runnable() {
            public void run() {
                frame.clearFrame();
                frame.getInstance().setState(GameState.IN_GAME);
                frame.getInstance().setRenderEngine(new GameEngine(frame));
            }
        });

        this.highscoreButton = new CButton("High Scores", defaultFont, size.width / 2, size.height / 2 + (1 * buttonHeight) + (1 * buttonSpacer), buttonWidth, buttonHeight, this.frame);
        this.highscoreButton.addRunnable(new Runnable() {
            public void run() {
                System.out.println("You have clicked on the High Scores button!");
            }
        });

        this.optionsButton = new CButton("Options", defaultFont, size.width / 2, size.height / 2 + (2 * buttonHeight) + (2 * buttonSpacer), buttonWidth, buttonHeight, this.frame);
        this.optionsButton.addRunnable(new Runnable() {
            public void run() {
                System.out.println("You have clicked on the Options button!");
            }
        });

        this.creditsButton = new CButton("Credits", defaultFont, size.width / 2, size.height / 2 + (3 * buttonHeight) + (3 * buttonSpacer), buttonWidth, buttonHeight, this.frame);
        this.creditsButton.addRunnable(new Runnable() {
            public void run() {
                System.out.println("You have clicked on the Credits button!");
            }
        });

        this.exitButton = new CButton("Exit", defaultFont, size.width / 2, size.height / 2 + (4 * buttonHeight) + (4 * buttonSpacer), buttonWidth, buttonHeight, this.frame);
        this.exitButton.addRunnable(new Runnable() {
            public void run() {
                System.exit(0);
            }
        });

        this.instance = new Thread(this);
        this.instance.start();
    }

    public void render(Graphics graphics) {
        Dimension size = this.frame.getSize();

        graphics.setColor(Color.GRAY);

        List<Pixel> clonedPixels = new ArrayList<Pixel>(this.pixels);
        for (Pixel pixel : clonedPixels) {
            for (Pixel nearby : this.getNearbyPixels(pixel, clonedPixels, 100)) {
                graphics.drawLine(pixel.getX(), pixel.getY(), nearby.getX(), nearby.getY());
            }
        }

        graphics.setColor(Color.BLACK);

        int circleSize = 8;
        for (Pixel pixel : clonedPixels) {
            graphics.fillOval(pixel.getX() - (circleSize / 2), pixel.getY() - (circleSize / 2), circleSize, circleSize);
        }

        for (CButton button : new CButton[] { this.startButton, this.highscoreButton, this.optionsButton, this.creditsButton, this.exitButton }) {
            button.draw(graphics);
        }

        int quarterHeight = size.height / 4;
        int halfWidth = size.width / 2;
        graphics.drawImage(this.titleImage, halfWidth - (this.titleImage.getWidth(null) / 2), quarterHeight - (this.titleImage.getHeight(null) / 2), null);
    }

    public Thread getInstance() {
        return this.instance;
    }

    @Override
    public void run() {
        final Random rand = new Random();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int pixelAmount = 500;
        final int randVel = 15;

        for (int i = 0; i < pixelAmount; i++) {
            int startX = rand.nextInt(dim.width);
            int startY = rand.nextInt(dim.height);

            int velX = rand.nextInt(randVel) - (randVel / 2);
            int velY = rand.nextInt(randVel) - (randVel / 2);

            velX = velX == 0 ? 1 : velX;
            velY = velY == 0 ? 1 : velY;

            this.pixels.add(new Pixel(startX, startY, velX, velY));
        }

        while (true) {
            int iterations = pixelAmount - this.pixels.size();
            for (int i = 0; i < iterations; i++) {
                int startX = rand.nextInt(dim.width);
                int startY = rand.nextInt(dim.height);

                int velX = rand.nextInt(randVel) - (randVel / 2);
                int velY = rand.nextInt(randVel) - (randVel / 2);

                velX = velX == 0 ? 1 : velX;
                velY = velY == 0 ? 1 : velY;

                if (rand.nextBoolean()) {
                    if (rand.nextBoolean()) {
                        this.pixels.add(new Pixel(startX, 0, velX, Math.abs(velY)));
                    } else {
                        this.pixels.add(new Pixel(startX, dim.height, velX, -Math.abs(velY)));
                    }
                } else {
                    if (rand.nextBoolean()) {
                        this.pixels.add(new Pixel(0, startY, -Math.abs(velX), velY));
                    } else {
                        this.pixels.add(new Pixel(dim.width, startY, -Math.abs(velX), velY));
                    }
                }
            }

            List<Pixel> toRemove = new ArrayList<Pixel>();
            for (Pixel pixel : this.pixels) {
                pixel.tick();

                if (pixel.getX() < -10 || pixel.getX() > (dim.width + 10)) {
                    toRemove.add(pixel);
                } else if (pixel.getY() < -10 || pixel.getY() > (dim.height + 10)) {
                    toRemove.add(pixel);
                }
            }

            for (Pixel pixel : toRemove) {
                this.pixels.remove(pixel);
            }

            this.frame.getPanel().repaint();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Pixel> getNearbyPixels(Pixel pixel, List<Pixel> list, int range) {
        List<Pixel> nearby = new ArrayList<Pixel>();
        for (Pixel pix : list) {
            if (pix != pixel) {
                if (Math.abs(pix.getX() - pixel.getX()) <= range && Math.abs(pix.getY() - pixel.getY()) <= range) {
                    nearby.add(pix);
                }
            }
        }

        return nearby;
    }
}
