package main;

public class Game {

    final private GameWindow gameWindow;
    final private GamePanel gamePanel;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
    }

}
