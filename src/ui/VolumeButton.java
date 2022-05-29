package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.LoadSave;
import static utilz.Constantsa.UI.VolumeButton.*;

public class VolumeButton extends PauseButton{
    private BufferedImage[] imgs;
    private BufferedImage slider;
    private int index = 0;
    private boolean mouseOver,mousePressed;
    private int buttonX, minX, maxX;

    public VolumeButton(int x, int y, int width, int heigth) {
        super(x+width/2, y,VOLUME_WIDTH, heigth);
        bounds.x -= VOLUME_WIDTH/2;
        buttonX = x+width/2;
        this.x = x;
        this.width = width;
        minX = x + VOLUME_WIDTH/2;
        maxX = x + width - VOLUME_WIDTH/2;
        loadImg();
    }
    private void loadImg(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i = 0; i< imgs.length; i++)
            imgs[i] = temp.getSubimage(i*VOLUME_DEFAULT_WIDTH,0,VOLUME_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);

        slider = temp.getSubimage(3*VOLUME_DEFAULT_WIDTH,0,SLIDER_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
    }

    public void update(){
        index = 0;
        if(mouseOver)
            index=1;
        if(mousePressed)
            index = 2;
    }

    public void draw(Graphics g){

        g.drawImage(slider, x, y, width, heigth, null);
        g.drawImage(imgs[index],buttonX - VOLUME_WIDTH/2,y,VOLUME_WIDTH,heigth,null);
    }

    public void changeX(int x){
        if(x<minX)
            buttonX=minX;
        else if(x>maxX)
            buttonX=maxX;
        else
            buttonX = x- VOLUME_WIDTH/2;

        bounds.x = buttonX;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
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
}
