package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Player1 extends Player {
    private KeyboardListener keyboardListener;

    public Player1(){
        this.getVelocity().set(0, -1);
        this.matchSpeedToVelocity();
        this.getPosition().set(Settings.GAME_WIDTH / 2, 500);

        try {
            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/player1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setupKeyListener(KeyboardListener e) {
        this.keyboardListener = e;
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

        if(keyboardListener.isLeftPress()){
            this.getVelocity().rotateThis(Settings.DELTA_ANGLE);
        }
        if(keyboardListener.isRightPress()){
            this.getVelocity().rotateThis(-Settings.DELTA_ANGLE);
        }
        if(keyboardListener.isSpeedUpPress()){
            this.setSpeed(Settings.TURBO_SPEED);
            this.matchSpeedToVelocity();
        }
        else{
            this.setSpeed(Settings.NORMAL_SPEED);
            this.matchSpeedToVelocity();
        }
    }
}
