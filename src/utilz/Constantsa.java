package utilz;

import main.Game;

public class Constantsa {

    public static class EnemyConstants{
        public static final int ENEMY_0 = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int ENEMY_0_WIDTH_DEFAULT = 72;//taille de la case sur les sprites
        public static final int ENEMY_0_HEIGHT_DEFAULT = 32;//taille de la case sur les sprites

        public static final int ENEMY_0_WIDTH = (int)(ENEMY_0_WIDTH_DEFAULT*Game.SCALE);
        public static final int ENEMY_0_HEIGHT = (int)(ENEMY_0_HEIGHT_DEFAULT*Game.SCALE);

        public static final int DEBILUS_DRAWOFFSET_X = (int)(24*Game.SCALE);//différence entre le début du sprit et de la hitbox
        public static final int DEBILUS_DRAWOFFSET_Y = (int)(9*Game.SCALE);//same que dessue

        public static int GetSpriteAmount(int enemy_type, int enemy_state){
            switch(enemy_type){
                case ENEMY_0:
                    switch (enemy_state){
                        case(IDLE):
                            return 9;
                        case(RUNNING):
                            return 6;
                        case(ATTACK):
                            return 7;
                        case(HIT):
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }
            return 0;
        }

        public static int GetMaxlP(int enemy_type){
            switch (enemy_type){
                case ENEMY_0:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type){
            switch (enemy_type){
                case ENEMY_0:
                    return 15;
                default:
                    return 0;
            }
        }
    }

    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGTH_DEFAULT = 56;
            public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT* Game.SCALE);
            public static final int B_HEIGTH = (int)(B_HEIGTH_DEFAULT* Game.SCALE);
        }

        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        public static class UrmButton{
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE*Game.SCALE);
        }

        public static class VolumeButton{
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int DEAD = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;


        public static int GetSpriteAmount(int player_action){

            switch (player_action){
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                case DEAD:
                    return 4;
                case JUMP:
                case ATTACK_1:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }
    }
}
