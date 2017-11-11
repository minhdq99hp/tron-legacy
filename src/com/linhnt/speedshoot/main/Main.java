package com.linhnt.speedshoot.main;

import javax.swing.*;
/**
 * Created by minhdq99hp on 11/11/2017.
 */
public class Main {
//    public static void setupKeyListener(JFrame window){
//        window.addKeyListener(new KeyboardListener());
//    }

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setTitle(Settings.GAME_TITLE);
        window.setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);

//        setupKeyListener(window);

        try {
            GameCanvas canvas = new GameCanvas();
            canvas.addKeyListener(new KeyboardListener());

            window.add(canvas);
            window.setVisible(true);

            canvas.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
