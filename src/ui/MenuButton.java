package ui;

import gamestates.Gamestates;

import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, rowIndex;
    private Gamestates state;
    private BufferedImage[] imgs;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestates state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];//le nombre dépendra du nombre de button
    }
}
