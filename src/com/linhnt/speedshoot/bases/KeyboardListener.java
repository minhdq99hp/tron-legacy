package com.linhnt.speedshoot.bases;


import com.linhnt.speedshoot.main.Settings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by LT on 14/07/2017.
 */
public class KeyboardListener implements KeyListener {
    private boolean upPress;
    private boolean downPress;
    private boolean leftPress;
    private boolean rightPress;
    private boolean shootPress;
    private boolean jumpPress;

    public static final KeyboardListener instance = new KeyboardListener();

    private KeyboardListener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case Settings.KEY_UP: {
                upPress = true;
                break;
            }
            case Settings.KEY_DOWN: {
                downPress = true;
                break;
            }
            case Settings.KEY_LEFT: {
                leftPress = true;
                break;
            }
            case Settings.KEY_RIGHT: {
                rightPress = true;
                break;
            }
            case Settings.KEY_SHOOT: {
                shootPress = true;
                break;
            }
            case Settings.KEY_JUMP: {
                jumpPress = true;
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case Settings.KEY_UP: {
                upPress = false;
                break;
            }
            case Settings.KEY_DOWN: {
                downPress = false;
                break;
            }
            case Settings.KEY_LEFT: {
                leftPress = false;
                break;
            }
            case Settings.KEY_RIGHT: {
                rightPress = false;
                break;
            }
            case Settings.KEY_SHOOT: {
                shootPress = false;
                break;
            }
            case Settings.KEY_JUMP: {
                jumpPress = false;
                break;
            }
        }
    }

    public boolean isUpPress() {
        return upPress;
    }

    public boolean isDownPress() {
        return downPress;
    }

    public boolean isLeftPress() {
        return leftPress;
    }

    public boolean isRightPress() {
        return rightPress;
    }

    public boolean isShootPress() {
        return shootPress;
    }

    public boolean isJumpPress() {
        return jumpPress;
    }
}
