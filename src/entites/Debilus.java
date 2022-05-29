package entites;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constantsa.Direction.LEFT;
import static utilz.Constantsa.Direction.RIGHT;
import static utilz.Constantsa.EnemyConstants.*;
import static utilz.HelpMethods.*;

public class Debilus extends Enemy{

    //Attack hitbox
    private Rectangle2D.Float atkhitbox;
    private int atkHitboxOffset;

    public Debilus(float x, float y) {
        super(x, y, ENEMY_0_WIDTH, ENEMY_0_HEIGHT, ENEMY_0);
        initHitbox(x,y,(int)(22* Game.SCALE),(int)(19*Game.SCALE));
        initAtkHitbox();

    }

    private void initAtkHitbox() {
        atkhitbox = new Rectangle2D.Float(x,y,(int)(82*Game.SCALE),(int)(19*Game.SCALE));
        atkHitboxOffset = (int)(Game.SCALE*30);
    }

    public void update(int[][] lvData, Player player){
        updateMove(lvData, player);
        updateAnimation();
        updateAtkHitbox();
    }

    private void updateAtkHitbox() {
        atkhitbox.x = hitBox.x - atkHitboxOffset;
        atkhitbox.y = hitBox.y;
    }

    private void updateMove(int[][] lvData, Player player){
        if(update0) {
            update0Check(lvData);
        }
        if (inAir)
            updateInAir(lvData);
        else{
            switch (ennemyState){
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (getThisManBOYSSS(lvData, player))
                        wazaaah(player);
                    if (yourComingToBrasil(player))//si joueur dans la zone de dmg attk de enemies
                        newState(ATTACK);

                    update0Move(lvData);
                    break;
                case ATTACK:
                    if (indexAnimation == 0)
                        atkCheck = false;

                    if(indexAnimation == 3 & !atkCheck)
                        checkHitEnemy(player,atkhitbox);
                    break;
                case HIT:

                    break;
            }
        }


    }

    public void drawAtkHitbox(Graphics g, int lvOffset){
        g.setColor(Color.red);
        g.drawRect((int)(atkhitbox.x - lvOffset),(int)atkhitbox.y,(int)atkhitbox.width,(int)atkhitbox.height);
    }

    public int flipX(){
        if (walkDir == RIGHT)
            return width;
        return 0;
    }

    public int flipW(){
        if (walkDir == RIGHT)
            return -1;
        return 1;
    }

}
