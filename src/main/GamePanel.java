package main;

import inputs.KeyBoard;
import inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Mouse mouseInputs;
    private Game game;
    public GamePanel(Game game){
        mouseInputs = new Mouse(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyBoard(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size : "+GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}
