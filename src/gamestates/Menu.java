package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements Statemethods{

    private MenuButton[] buttons = new MenuButton[3];

    public Menu(Game game) {
        super(game);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2,(int)(150*Game.SCALE),0,Gamestates.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2,(int)(220*Game.SCALE),1,Gamestates.OPTION);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2,(int)(290*Game.SCALE),2,Gamestates.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Menu",Game.GAME_WIDTH / 2,200);
        for(MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e,mb)){
                mb.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons){
            if (isIn(e, mb)){
                if (mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
        }
        resetButton();
    }

    private void resetButton() {
        for(MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons)
            mb.setMouseOver((false));

        for(MenuButton mb : buttons)
            if(isIn(e,mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestates.state = Gamestates.PLAYING;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
