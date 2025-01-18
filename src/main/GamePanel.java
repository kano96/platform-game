package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    final private MouseInputs mouseInputs = new MouseInputs(this);
    private float xDelta = 100, yDelta = 100;
    private float xDir = 0.8f, yDir = 0.8f;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(125, 13, 12);
    private Random random;

    public GamePanel(){
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        random = new Random();
    }

    public void changeXDelta(int xDelta){
        this.xDelta += xDelta;
    }

    public void changeYDelta(int yDelta){
        this.yDelta += yDelta;

    }

    public void setRectPosition(int x, int y){
        this.xDelta = x;
        this.yDelta = y;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);



    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 400 || xDelta < 0){
            xDir *= -1;
            color = getRndColor();
        }

        yDelta += yDir;
        if(yDelta > 400 || yDelta < 0){
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);

        return new Color(r, b, g);
    }


}
