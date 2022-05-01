package ui;

import utilz.LoadSave;
import static utilz.Constantsa.UI.PauseButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SoundButton extends PauseButton{

    private BufferedImage[][] soundImgs;

    public SoundButton(int x, int y, int width, int heigth) {
        super(x, y, width, heigth);

        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j=0; j<soundImgs.length;j++)
            for (int i = 0; i<soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i*SOUND_SIZE_DEFAULT,j*SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT);
    }

    public void update(){

    }

    public void draw(Graphics g){
        g.drawImage(soundImgs[0][0],x,y,width,heigth,null);
    }
}
