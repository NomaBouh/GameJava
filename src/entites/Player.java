package entites;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static utilz.Constantsa.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animation;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false,attacking = false;
    private boolean left,up, right, down, jump;
    private float playerSpeed = 1.0f * Game.SCALE;
    private int[][] lvData;
    private float xDrawOffset = 21* Game.SCALE; //ici le 21 est du à la distace entre le coin du sprite et de sa hit box.
    // il fait prendre en compte que lorsque l'on changera avec notre design a nous il faudra le changer
    // ==> veillez à uniformisez la taille des sprites et leur forme (pour ne pas avoir a le refaire pour chaque mobs
    private float yDrawOffset = 12*Game.SCALE; //de même que pour au dessus. penser a change et uniformiseer

    //Gravity and Jump
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.5f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    //Statue Player
    private BufferedImage healthBarImg;

    private int healBarW = (int)(192*Game.SCALE);
    private int healBarH = (int)(58*Game.SCALE);
    private int healBarX = (int)(10*Game.SCALE);
    private int healBarY = (int)(10*Game.SCALE);

    private int lPBarW = (int)(150*Game.SCALE);
    private int lpBarH = (int)(4*Game.SCALE);
    private int lpBarX = (int)(34*Game.SCALE);
    private int lpBarY = (int)(14*Game.SCALE);

    private int maxLP = 100;
    private int currentLP = maxLP;
    private int lPW = currentLP;

    //Attack hitbox
    private Rectangle2D.Float atkhitbox;

    //vue revers player
    private int flipX = 0;
    private int flipW = 1;

    private boolean atkChek;

    private Playing playing;
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x,y,(int)(20*Game.SCALE),(int)(20*Game.SCALE));
        initAtkHitbox();
    }

    private void initAtkHitbox() {
        atkhitbox = new Rectangle2D.Float(x,y,(int)(20*Game.SCALE),(int)(20*Game.SCALE));
    }

    public void update(){
        updateLP();
        updateAtkHitbox();
        updatePos();
        if (attacking)
            chechAtk();
        updateAnimationTick();
        setAnimation();
    }

    private void chechAtk() {
        if (atkChek || aniIndex!=1)
            return;
        atkChek = true;
        playing.checkEnemyHit(atkhitbox);
    }

    private void updateAtkHitbox() {
        if(right){
            atkhitbox.x = hitBox.x + hitBox.width + (int)(Game.SCALE*8);
        }else if(left){
            atkhitbox.x = hitBox.x - hitBox.width - (int)(Game.SCALE*8);
        }
        atkhitbox.y = hitBox.y + (Game.SCALE*5);
    }

    private void updateLP() {
        lPW = (int) ((currentLP/(float)maxLP)*lPBarW);
    }

    public void render(Graphics g, int lvlOffset){

        g.drawImage(animation[playerAction][aniIndex],(int)(hitBox.x -xDrawOffset) - lvlOffset + flipX,(int)(hitBox.y - yDrawOffset), width*flipW, height, null);
        drawHitbox(g,lvlOffset);// a retiré pour rendre la hit box invisible
        drawAtkHitbox(g,lvlOffset);//affichage de la hitbox atk player
        drawUI(g);
    }

    private void drawAtkHitbox(Graphics g, int lvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int)atkhitbox.x - lvlOffset,(int)atkhitbox.y,(int)atkhitbox.width,(int)atkhitbox.height);
    }

    private void drawUI(Graphics g) {
        g.drawImage(healthBarImg,healBarX,healBarY,healBarW,healBarH,null);
        g.setColor(Color.yellow);
        g.fillRect(healBarX + lpBarX, healBarY + lpBarY, lPW,lpBarH);
    }


    private void setAnimation() {
        int startAni = playerAction;

        if(moving)
            playerAction=RUNNING;
        else
            playerAction=IDLE;

        if(inAir){
            if(airSpeed <0)
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        if(attacking)
            playerAction = ATTACK_1;

        if(startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;
        if (jump)
            jump();
        if (!inAir)
            if ((!left && !right) || (left && right))
                return;

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right){
            xSpeed += playerSpeed;
            flipX = 0;
            flipW = 1;
        }
        if(!inAir)
            if (!IsEntityOnFloor(hitBox, lvData))
                inAir = true;

        if(inAir){
            if(CanMoveHere(hitBox.x, hitBox.y+airSpeed, hitBox.width, hitBox.height, lvData)){
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }else{
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if(airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        }else
            updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if(inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitBox.x+xSpeed, hitBox.y, hitBox.width, hitBox.height, lvData)){
            hitBox.x += xSpeed;
        }else{
            hitBox.x = GetEntityXPosNextToWall(hitBox,xSpeed);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
                atkChek = false;
            }
        }
    }

    public void lPChange(int value){
        currentLP += value;

        if (currentLP <= 0){
            currentLP = 0;
            //gameOver();
        }else if(currentLP >= maxLP)
            currentLP = maxLP;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animation = new BufferedImage[9][6];
            for(int j =0;j<animation.length;j++)
                for(int i =0; i<animation[j].length;i++)
                    animation[j][i] = img.getSubimage(i*64,j*40,64,40);

            healthBarImg = LoadSave.GetSpriteAtlas(LoadSave.HEAL);
    }

    public void loadLvData(int[][] lvData){
        this.lvData =lvData;
        if(!IsEntityOnFloor(hitBox,lvData))
            inAir = true;
    }

    public void ressetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking=attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    public void setJump(boolean jump){
        this.jump = jump;
    }
}
