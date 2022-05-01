package ui;

import gamestates.Gamestates;
import utilz.LoadSave;
import static utilz.Constantsa.UI.Buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH /2;
    private Gamestates state;
    private BufferedImage[] imgs;
    private boolean mouseOver,mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestates state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGTH);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];//le nombre dépendra du nombre de button
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for(int i =0;i< imgs.length;i++)
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT, rowIndex*B_HEIGTH_DEFAULT,B_WIDTH_DEFAULT,B_HEIGTH_DEFAULT);
    }

    public void draw(Graphics g){
        g.drawImage(imgs[index],xPos - xOffsetCenter,yPos,B_WIDTH,B_HEIGTH,null);
    }

    public void update(){
        index = 0;
        if(mouseOver)
            index = 2;
        if(mousePressed)
            index = 1;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGamestate(){
        Gamestates.state = state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
