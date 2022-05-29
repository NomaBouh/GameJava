package entites;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constantsa.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constantsa.Direction.*;

public abstract class Enemy extends Entity{

    protected int indexAnimation, ennemyState, ennemyType;
    protected int animationTick, animationSpeed = 20;
    protected boolean update0 = true;
    protected boolean inAir;
    protected float fallSpeed;
    protected float gravity = 0.04f* Game.SCALE;
    protected float walkSpeed = 0.5f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float atkDist = Game.TILES_SIZE;
    protected int maxLP;
    protected int currentLP;
    protected boolean active = true;
    protected boolean atkCheck;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.ennemyType = enemyType;
        initHitbox(x,y,width,height);
        maxLP = GetMaxlP(enemyType);
        currentLP = maxLP;
    }

    protected void update0Check(int[][] lvData){
        if (!IsEntityOnFloor(hitBox, lvData))
            inAir = true;
        update0 = false;
    }

    protected void updateInAir(int[][] lvData){
            if (CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvData)) {
                hitBox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
                tileY = (int)(hitBox.y/Game.TILES_SIZE);
            }
    }

    protected void updateAnimation(){
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            indexAnimation++;
            if (indexAnimation >= GetSpriteAmount(ennemyType,ennemyState)) {
                indexAnimation = 0;


                switch (ennemyState) {
                    case ATTACK, HIT -> ennemyState = IDLE;
                    case DEAD -> active = false;
                }
            }
        }
    }

    protected void update0Move(int[][] lvData){
        float xSpeed = 0;

        if(walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        if (CanMoveHere(hitBox.x+xSpeed,hitBox.y, hitBox.width, hitBox.height, lvData))
            if(IsFloor(hitBox,xSpeed,lvData)){
                hitBox.x += xSpeed;
                return;
            }
        changeWalkDir();
    }

    protected void wazaaah(Player player){ //retourn vers joueurs
        if (player.hitBox.x > hitBox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    protected boolean getThisManBOYSSS(int[][] lvData, Player player){ //pour voir le joueur
        int playerTileY = (int)(player.getHitBox().y / Game.TILES_SIZE);
        if (playerTileY == tileY)
            if (isPlayerInRange(player)){
                if(noObstacle(lvData,hitBox,player.hitBox,tileY))
                    return true;
            }
        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return  absValue <= atkDist * 5;
    }

    protected boolean yourComingToBrasil(Player player){
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return  absValue <= atkDist;
    }


    protected  void checkHitEnemy(Player player, Rectangle2D.Float atkHitBox){
        if(atkHitBox.intersects(player.hitBox))
            player.lPChange(-GetEnemyDmg(ennemyType));
        atkCheck = true;
    }
    public void hurt(int amount){
        currentLP -= amount;
        if (currentLP <=0)
            newState(DEAD);
        else
            newState(HIT);
    }

    protected void newState(int ennemyState){
        this.ennemyState = ennemyState;
        animationTick = 0;
        indexAnimation = 0;
    }

    private void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    public int getAnimationIndex(){
        return indexAnimation;
    }
    public int getEnnemyState(){
        return ennemyState;
    }

    public boolean isActive() {
        return active;
    }
}
