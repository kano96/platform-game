package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GamePanel() {


        importImage();

        MouseInputs mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(1200, 800));
    }

    public void changeXDelta(int xDelta) {
        this.xDelta += xDelta;
    }

    public void changeYDelta(int yDelta) {
        this.yDelta += yDelta;

    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);

    }

}
