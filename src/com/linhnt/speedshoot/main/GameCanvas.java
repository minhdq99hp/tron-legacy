package com.linhnt.speedshoot.main;

import com.linhnt.speedshoot.background.Background;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.bonus.Bonus;
import com.linhnt.speedshoot.bonus.BonusManager;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.player.Player1;
import com.linhnt.speedshoot.player.Player2;
import com.linhnt.speedshoot.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class GameCanvas extends JPanel{
    private BufferedImage backBuffer;
    private Graphics2D backBufferGraphics2D;
    private BufferedImage cache;
    private Graphics2D cacheGraphics2D;
    private KeyboardListener keyboardListener;

//    private Vector2D mouseClickPoint;

//    BullEyes bullEyes;
//    private MouseAdapter mouseAdapter = new MouseAdapter() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            super.mouseClicked(e);
//            mouseClickPoint.set(e.getX(), e.getY());
//        }
//    };

    GameCanvas()throws Exception{
        setupWindow();

        setupBackground();

        setupBonusManager();
    }

    private void setupBonusManager() throws Exception {
        GameObjectPool.createAndAdd(BonusManager.class);

        Bonus bonus = GameObjectPool.createAndAdd(Bonus.class);
        bonus.getPosition().set(100, 100);
    }

    private void setupBackground() throws Exception {
        GameObjectPool.createAndAdd(Background.class);
    }

    public void setupPlayer() throws Exception {

        Player1 player_1 = GameObjectPool.createAndAddAnddAddToPhysics(Player1.class);
        player_1.setupKeyListener(this.keyboardListener);


        Player2 player_2 = GameObjectPool.createAndAddAnddAddToPhysics(Player2.class);
        player_2.setupKeyListener(this.keyboardListener);
    }

    void setupKeyListener(KeyboardListener e) {
        this.keyboardListener = e;
    }

    private void setupWindow() {
        //window
        this.setSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));
        this.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));

        //backBuffer
        backBuffer = new BufferedImage(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBuffer.getGraphics();
        //backBuffer
        cache = new BufferedImage(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        cacheGraphics2D = (Graphics2D) backBuffer.getGraphics();

        //create empty cursor
//        this.setCursor(this.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
    }

    private void renderToBackBuffer() {
//        fill empty background
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, getWidth(), getHeight());

        GameObjectPool.renderAll(backBufferGraphics2D, this);

        this.getGraphics().drawImage(backBuffer, 0, 0, null);

        cacheGraphics2D.drawImage(backBuffer, 0, 0, null);
    }

    public void run(){
        long loopTime = 17;
        long lastTimeUpdate = System.currentTimeMillis();
        while(true) {
            long currentTime = System.currentTimeMillis();
            long milisecDelay = currentTime - lastTimeUpdate;
            if(milisecDelay >= loopTime) {
                lastTimeUpdate = currentTime;

                GameObjectPool.runAll(milisecDelay, null);

                GameObjectPool.runActionAll(milisecDelay);

                renderToBackBuffer();

//                bullEyes.setImage(cache);
            }
        }
    }
}
