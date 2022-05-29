package entites;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constantsa.EnemyConstants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] enemy0Arr;
    private ArrayList<Debilus> debilus = new ArrayList<>();

    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        addEnemy();
    }

    private void addEnemy() {
        debilus = LoadSave.GetDebilus();
    }

    public void update(int[][] lvData,Player player){
        for(Debilus c : debilus)
            if (c.isActive())
                c.update(lvData,player);
    }

    public void draw(Graphics g, int xLvOffset){
        drawDebilus(g, xLvOffset);
        //System.out.println("size debilus : " + debilus.size());// affiche le nombre d'énemi sur la map

    }

    private void drawDebilus(Graphics g, int xLvOffset) {
        for(Debilus c : debilus)
            if (c.isActive()){
                g.drawImage(enemy0Arr[c.getEnnemyState()][c.getAnimationIndex()], (int) c.getHitBox().x - xLvOffset + c.flipX() - DEBILUS_DRAWOFFSET_X, (int) c.getHitBox().y - DEBILUS_DRAWOFFSET_Y,ENEMY_0_WIDTH*c.flipW(),ENEMY_0_HEIGHT, null);
                c.drawHitbox(g, xLvOffset);//affiche la hitbox des enemy
                c.drawAtkHitbox(g,xLvOffset);//atkHitbox enemy
            }
    }

    public void  checkHit(Rectangle2D.Float atkHitbox){
        for(Debilus c : debilus)
            if (c.isActive()) {
                if (atkHitbox.intersects(c.getHitBox())) {
                    c.hurt(10);
                    return;

                }
            }
    }

    private void loadEnemyImgs() {
        enemy0Arr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.ENNEMY_SPRITE); //charger sprite enemy
        for (int j = 0; j<enemy0Arr.length; j++)
            for (int i=0; i<enemy0Arr[j].length; i++)
                enemy0Arr[j][i] = temp.getSubimage(i*ENEMY_0_WIDTH_DEFAULT, j*ENEMY_0_HEIGHT_DEFAULT, ENEMY_0_WIDTH_DEFAULT,ENEMY_0_HEIGHT_DEFAULT);
    }
}
