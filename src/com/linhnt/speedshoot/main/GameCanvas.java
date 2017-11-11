package com.linhnt.speedshoot.main;

import com.linhnt.speedshoot.background.Background;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.bases.group.Group;
import com.linhnt.speedshoot.bases.listeners.GameMouseListener;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.player.BullEyes;
import com.linhnt.speedshoot.player.Player;
import com.linhnt.speedshoot.utils.ImageUtils;
import com.linhnt.speedshoot.vehicule.VehiculeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class GameCanvas extends JPanel{
    private BufferedImage backBuffer;
    private Graphics2D backBufferGraphics2D;
    private BufferedImage cache;
    private Graphics2D cacheGraphics2D;

//    private Vector2D mouseClickPoint;

//    BullEyes bullEyes;
//    private MouseAdapter mouseAdapter = new MouseAdapter() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            super.mouseClicked(e);
//            mouseClickPoint.set(e.getX(), e.getY());
//        }
//    };

    public GameCanvas()throws Exception{
        setupWindow();

        setupBackground();

        setupPlayer();

//        setupGameObject();
//        setupListener();
    }

    private void setupBackground() throws Exception {
        //add anh landscape vao game
//        Group backgroundGroup = GameObjectPool.createAndAdd(Group.class);
//        Background background = GameObjectPool.create(Background.class);
//        backgroundGroup.addChild(background);
//        //TODO: có thể add thêm nhà cửa cây cối vào backgroundGroup

        GameObjectPool.createAndAdd(Background.class);
    }

    private void setupPlayer() throws Exception {
//        bullEyes = new BullEyes();
//        GameObjectPool.add(bullEyes);
        Player player_1 = GameObjectPool.createAndAddAnddAddToPhysics(Player.class);
        player_1.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/player1.png")));
        player_1.getPosition().set(Settings.GAME_WIDTH / 2, 100);

        Player player_2 = GameObjectPool.createAndAddAnddAddToPhysics(Player.class);
        player_2.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/player2.png")));
        player_2.getPosition().set(Settings.GAME_WIDTH / 2, 500);
    }

//    private void setupGameObject() throws Exception {
//        GameObjectPool.createAndAdd(VehiculeManager.class);
//    }

//    private void setupListener() {
//        mouseClickPoint = new Vector2D();
//
//        this.addMouseListener(GameMouseListener.instance);
//        this.addMouseMotionListener(GameMouseListener.instance);
//        GameMouseListener.registerToInstance(this.mouseAdapter);
//    }

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
