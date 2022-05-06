package ui;

import main.Game;
import utilz.Constantsa.*;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constantsa.UI.PauseButtons.*;
import static utilz.Constantsa.UI.UrmButton.*;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;

    public PauseOverlay(){

        loadBck();
        creatSoundButton();
        creatUrmButton();
    }

    private void creatUrmButton() {
        int menuX = (int)(313*Game.SCALE);
        int replayX = (int)(387*Game.SCALE);
        int unpauseX = (int)(461*Game.SCALE);
        int bY = (int)(325*Game.SCALE);


        menuB = new UrmButton(menuX,bY,URM_SIZE,URM_SIZE,2);
        replayB = new UrmButton(replayX,bY,URM_SIZE,URM_SIZE,1);
        unpauseB = new UrmButton(unpauseX,bY,URM_SIZE,URM_SIZE,0);


    }

    private void creatSoundButton() {
        int soundX = (int)(450*Game.SCALE);
        int musicY = (int)(140 * Game.SCALE);
        int sfxY = (int)(186*Game.SCALE);
        musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
        sfxButton = new SoundButton(soundX,sfxY,SOUND_SIZE,SOUND_SIZE);
    }

    private void loadBck() {
        background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BCK);
        bgW = (int)(background.getWidth()* Game.SCALE);
        bgH = (int)(background.getHeight()* Game.SCALE);
        bgX = Game.GAME_WIDTH/2-bgW/2;
        bgY = (int) (25 * Game.SCALE);

    }

    public void update(){

        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpauseB.update();

    }

    public void draw(Graphics g){
        //bck
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        //buttons
        //Sounds
        musicButton.draw(g);
        sfxButton.draw(g);

        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

    }

    public void mouseDragged(MouseEvent e){

    }

    public void mouseMoved(MouseEvent e){
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        if(isIn(e,musicButton))
            musicButton.setMouseOver(true);
        else if(isIn(e,sfxButton))
            sfxButton.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e){
        if(isIn(e,musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());

            }
        }else if(isIn(e,sfxButton)){
            if ((sfxButton.isMousePressed()))
                sfxButton.setMuted(!sfxButton.isMuted());
        }

        musicButton.resetBools();
        sfxButton.resetBools();
    }

    public void mousePressed(MouseEvent e){
        if(isIn(e,musicButton))
            musicButton.setMousePressed(true);
        else if(isIn(e,sfxButton))
            sfxButton.setMousePressed(true);

    }

    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());

    }
}
