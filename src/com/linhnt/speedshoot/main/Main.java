package com.linhnt.speedshoot.main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Main {
    public static void setupKeyListener(JFrame window){
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setTitle(Settings.GAME_TITLE);
        window.setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);

        setupKeyListener(window);

        try {
            GameCanvas canvas = new GameCanvas();
            window.add(canvas);
            window.setVisible(true);



            canvas.run();//
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
