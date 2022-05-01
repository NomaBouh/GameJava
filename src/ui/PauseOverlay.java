package ui;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constantsa.UI.PauseButtons.SOUND_SIZE;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;

    public PauseOverlay(){

        loadBck();
        creatSoundButton();
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
        bgY = (int) (50 * Game.SCALE);
    }

    public void update(){

    }

    public void draw(Graphics g){
        //bck
        g.drawImage(background, bgX, bgY, bgW, bgW, null);

        //buttons
        //Sounds
        musicButton.draw(g);
        sfxButton.draw(g);

    }

    public void mouseDragged(MouseEvent e){

    }

    public void mouseMoved(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    public void mousePressed(MouseEvent e){

    }
}
