package inputs;

import gamestates.Gamestates;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public Mouse(GamePanel gamePanel){
        this.gamePanel=gamePanel;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestates.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestates.state){
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (Gamestates.state){
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch (Gamestates.state){
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
