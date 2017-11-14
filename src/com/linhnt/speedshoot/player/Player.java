package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.Animation;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Player extends GameObject {
    private KeyboardListener keyboardListener;

    private Animation normalAnimation;
    private Animation turboAnimation;

    private int blood = Settings.INIT_BLOOD;
    private int point = Settings.INIT_POINT;

    private float speed = Settings.NORMAL_SPEED;

    public Player(){
        this.getScale().set(0.4f, 0.4f);
        this.matchSpeedToVelocity();
    }

    public void setupKeyListener(KeyboardListener e){
        this.keyboardListener = e;
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

    }

    protected void matchSpeedToVelocity(){
        this.getVelocity().multiplyThis(speed / this.getVelocity().getLength());
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setNormalAnimationFromUrl(String url){
        try {
            this.normalAnimation = new Animation(ImageUtils.readAllInForder(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTurboAnimation(String url){
        try {
            this.turboAnimation = new Animation(ImageUtils.readAllInForder(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;

        this.matchSpeedToVelocity();
    }
}
