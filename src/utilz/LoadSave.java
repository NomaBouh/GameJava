package utilz;


import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String LEVEL_ATLAS = "outside_sprites.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String LEVEL_ONE_DATA = "level_one_data.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String MENU_BUTTONS = "menuButtons.png";//fichier image des bouttons
    public static final String MENU_BACKGROUND = "menu_background.png";//fichier image des bouttons
    public static final String LEVEL_LEFROID = "Sprit niveau leFroid.png";//spritelv EVANN
    public static final String LEVEL_LEFROID_PLAY = "Niveau_LEFROID.png";//Lvl EVANN

    public static final String PAUSE_BCK = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";

    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName); //mettre le nom du ficjiers sprites joueurs une fois fini

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] GetLevelData(){
        int[][] lvData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j<img.getHeight();j++)
            for (int i=0;i<img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvData[j][i] = color.getRed();
            }
        return lvData;
    }
}
