package com.kthisiscvpv.digitalchaos.misc;

public class Pixel {

    private int x;
    private int y;

    private double xVelocity;
    private double yVelocity;

    public Pixel(int startX, int startY, double xVel, double yVel) {
        this.x = startX;
        this.y = startY;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void tick() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }
}
