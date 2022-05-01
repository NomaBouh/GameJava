package gamestates;

import entites.Player;
import levels.*;
import main.Game;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{
    private Player player;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = true;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200,200, (int) (64*Game.SCALE), (int) (40*Game.SCALE));
        player.loadLvData(levelManager.getCurrentLevel().getLvData());
        pauseOverlay = new PauseOverlay();
    }

    public void windowFocusLost(){
        player.ressetDirBooleans();
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();

        pauseOverlay.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);

        pauseOverlay.draw(g);
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
                Gamestates.state = Gamestates.MENU;
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
}
