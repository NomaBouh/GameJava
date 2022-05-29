package entites;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x,y;
    protected int width,height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

    }

    protected void drawHitbox(Graphics g, int xLvOffset){
        //pour débug la hitbox
        g.setColor(Color.green);
        g.drawRect((int) hitBox.x-xLvOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitbox(float x, float y, int width, int height){
        hitBox = new Rectangle2D.Float( x,  y, width, height);
    }

 /*   protected void updateHitbox(){
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }*/
    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }
}
