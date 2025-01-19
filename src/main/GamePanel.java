package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {


        importImage();
        loadAnimations();

        MouseInputs mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        setPreferredSize(new Dimension(1200, 800));
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }

    private void setAnimation() {
        if (moving){
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case RIGHT -> xDelta += 5;
                case UP -> yDelta -= 5;
                case DOWN -> yDelta += 5;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);

    }




    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }


    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }
}
