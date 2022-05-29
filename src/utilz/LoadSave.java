package utilz;


import entites.Debilus;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constantsa.EnemyConstants.*;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String LEVEL_ATLAS = "outside_sprites.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String LEVEL_ONE_DATA = "level_one_data.png";//mettre le nom du ficjiers sprites joueurs une fois fini
    public static final String MENU_BUTTONS = "menuButtons.png";//fichier image des bouttons
    public static final String MENU_BACKGROUND = "menu_background.png";//fichier image des bouttons
    public static final String LEVEL_ONE_CAMLOCK = "level_CamLockTest.png";
    public static final String MENU_BCK_IMG = "backGroundMenuOpen.png"; // img de fond du menu open
    public static final String GAME_BCK_IMG = "fontGame.png";//image en arrière plan du jeu

    public static final String ENNEMY_SPRITE = "ennemy_sprite.png";//Ennemy sprite

    public static final String HEAL = "health_power_bar.png";

    public static final String PAUSE_BCK = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";

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

    public static ArrayList<Debilus> GetDebilus(){
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_CAMLOCK);
        ArrayList<Debilus> list = new ArrayList<>();
        for(int j = 0; j<img.getHeight();j++)
            for (int i=0;i<img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getGreen();
                if(value == ENEMY_0)
                    list.add(new Debilus(i*Game.TILES_SIZE, j*Game.TILES_SIZE));
            }
        return list;
    }

    public static int[][] GetLevelData(){
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_CAMLOCK);
        int[][] lvData = new int[img.getHeight()][img.getWidth()];
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
