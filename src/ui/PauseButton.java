package ui;

import java.awt.*;

public class PauseButton {

    protected int x,y,width,heigth;
    protected Rectangle bounds;

    public PauseButton(int x, int y, int width, int heigth){
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        creatBounds();
    }

    private void creatBounds() {
        bounds = new Rectangle(x,y,width,heigth);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
