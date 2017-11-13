package com.linhnt.speedshoot.main;

import com.linhnt.speedshoot.input.KeyboardListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Main {
    private static KeyboardListener keyboardListener = new KeyboardListener();

    private static void setupKeyListener(JFrame window){
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyboardListener.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyboardListener.keyReleased(e);
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
            canvas.setupKeyListener(keyboardListener);

            window.add(canvas);
            window.setVisible(true);

            canvas.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
