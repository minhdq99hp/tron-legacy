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
            case KeyEvent.VK_UP: {
                upPress = true;
                break;
            }
            case KeyEvent.VK_DOWN: {
                downPress = true;
                break;
            }
            case KeyEvent.VK_LEFT: {
                leftPress = true;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                rightPress = true;
                break;
            }
            case KeyEvent.VK_SLASH: {
                shootPress = true;
                break;
            }
            case KeyEvent.VK_SPACE: {
                jumpPress = true;
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: {
                upPress = false;
                break;
            }
            case KeyEvent.VK_DOWN: {
                downPress = false;
                break;
            }
            case KeyEvent.VK_LEFT: {
                leftPress = false;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                rightPress = false;
                break;
            }
            case KeyEvent.VK_SLASH: {
                shootPress = false;
                break;
            }
            case KeyEvent.VK_SPACE: {
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
