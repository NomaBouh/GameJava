package gamestates;

import entites.EnemyManager;
import entites.Player;
import levels.*;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false; //le jeu démarre en mode play et pas sur le menu pause

    private int xLvOffset;
    private int lesftBorder = (int) (0.2*Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8*Game.GAME_WIDTH);
    private int lvTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTileOffset = lvTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvOffset = maxTileOffset * Game.TILES_SIZE;

    private BufferedImage bckGameImg;

    public Playing(Game game) {
        super(game);
        initClasses();

        bckGameImg = LoadSave.GetSpriteAtlas(LoadSave.GAME_BCK_IMG);
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(200,200, (int) (64*Game.SCALE), (int) (40*Game.SCALE), this);
        player.loadLvData(levelManager.getCurrentLevel().getLvData());
        pauseOverlay = new PauseOverlay(this);
    }

    public void windowFocusLost(){
        player.ressetDirBooleans();
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public void update() {
        if(!paused){
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLvData(),player);
            checkBorderDist();
        }else{
            pauseOverlay.update();
        }
    }

    private void checkBorderDist() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvOffset;

        if (diff>rightBorder)
            xLvOffset += diff - rightBorder;
        else if (diff < lesftBorder)
            xLvOffset += diff - lesftBorder;

        if(xLvOffset > maxLvOffset)
            xLvOffset = maxLvOffset;
        else if (xLvOffset < 0)
            xLvOffset = 0;
    }

    public void resetAll(){
        //resetLvl
    }

    public void checkEnemyHit(Rectangle2D.Float atkHitbox){
        enemyManager.checkHit(atkHitbox);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bckGameImg,0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT,null);
        levelManager.draw(g, xLvOffset);
        player.render(g, xLvOffset);
        enemyManager.draw(g, xLvOffset);
        if (paused) {
            g.setColor(new Color(0,0,0,50));
            g.fillRect(0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT);
            pauseOverlay.draw(g);

        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
        pauseOverlay.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(paused)
            pauseOverlay.mouseMoved(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch ((e.getKeyCode())) {
            case KeyEvent.VK_Q:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;//en appuyant sur le bouton escape du clavier, on inverse la valeur du boolean pause et ainsi on active/desactive l'écran de pause
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch ((e.getKeyCode())) {

            case KeyEvent.VK_Q:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }

    }

    public void mouseDragged(MouseEvent e){
        if(paused)
            pauseOverlay.mouseDragged(e);
    }

    public void unpauseGame(){
        paused = false;
    }
}
