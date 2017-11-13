package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.main.Settings;

public class Player1 extends Player {
    private KeyboardListener keyboardListener;

    public Player1(){
        this.getVelocity().set(0, -1);
    }

    @Override
    public void setupKeyListener(KeyboardListener e) {
        this.keyboardListener = e;
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
//        super.run(milisecDelay, parent);

        if(keyboardListener.isLeftPress()){
            this.getVelocity().rotateThis(Settings.DELTA_ANGLE);
        }
        if(keyboardListener.isRightPress()){
            this.getVelocity().rotateThis(-Settings.DELTA_ANGLE);
        }
        if(keyboardListener.isSpeedUpPress()){
            this.setSpeed(Settings.TURBO_SPEED);
        }
        else{
            this.setSpeed(Settings.NORMAL_SPEED);
        }
    }
}
