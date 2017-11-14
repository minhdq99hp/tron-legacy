package com.linhnt.speedshoot.main;

import com.linhnt.speedshoot.input.KeyboardListener;

import javax.swing.*;

/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Main {
    private static KeyboardListener keyboardListener = KeyboardListener.instance;

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setTitle(Settings.GAME_TITLE);
        window.setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);

        try {
            GameCanvas canvas = new GameCanvas();
            canvas.setupKeyListener(keyboardListener);
            canvas.setupPlayer();

            window.add(canvas);
            window.setVisible(true);

            canvas.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
