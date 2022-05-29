package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvData){
        if (!IsSolid(x,y,lvData))
            if(!IsSolid(x+width, y+ height, lvData))
                if (!IsSolid(x+width, y, lvData))
                    if(!IsSolid(x, y+height, lvData))
                        return true;
        return false;
    }

    private static boolean IsSolid(float x,float y, int[][] lvData) {
        int maxWitdth = lvData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWitdth)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return IsTileSolid((int)xIndex,(int)yIndex,lvData);
    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvData){
        int value = lvData[(int) yTile][(int) xTile];

        if (value >= 48 || value < 0 || value !=11 ) // 11 ici correspond à la case de vide dans la sheet de sprite monde
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        if(xSpeed>0){
            //Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        }else{
            //Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        if (airSpeed>0){
            //Falling
            int tileYPOs = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPOs + yOffset -1;
        }else{
            //Jumping
            return currentTile * Game.TILES_SIZE;

        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvData){
        //check pixel  below bottom left and bottom right
        if(!IsSolid(hitbox.x,hitbox.y+ hitbox.height+1,lvData))
            if(!IsSolid(hitbox.x + hitbox.width, hitbox.y+ hitbox.height +1, lvData))
                return false;
        return true;
    }

    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvData){
        return IsSolid(hitbox.x+xSpeed ,hitbox.y+ hitbox.height+1,lvData);
    }

    public static boolean RoadToOnePiece(int xStart, int xEnd, int y, int[][] lvData){
        for (int i = 0; i<xEnd-xStart; i++) {
            if (IsSolid(xStart + i, y, lvData))
                return false;
            if (!IsSolid(xStart + i, y+1, lvData))
                return false;
        }
        return true;
    }

    public static boolean noObstacle(int[][] lvData, Rectangle2D.Float hitBox, Rectangle2D.Float hitBox1, int tileY) {
        int XTile = (int)(hitBox.x/Game.TILES_SIZE);
        int XTile1 = (int)(hitBox1.x/Game.TILES_SIZE);

        if(XTile > XTile1)
            return RoadToOnePiece(XTile1,XTile,tileY,lvData);
        else
            return RoadToOnePiece(XTile,XTile1,tileY,lvData);
    }
}
