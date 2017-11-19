package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.animation.Animation;
import com.linhnt.speedshoot.bases.group.Group;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.bases.physic.PhysicBody;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.bonus.Bonus;
import com.linhnt.speedshoot.input.KeyboardListener;
import com.linhnt.speedshoot.main.Settings;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;
import java.util.Random;

public class Player extends GameObject implements PhysicBody{
    private KeyboardListener keyboardListener;

    private Animation normalAnimation;
    private Animation turboAnimation;

    private int blood = Settings.INIT_BLOOD;
    private int point = Settings.INIT_POINT;

    private float speed = Settings.NORMAL_SPEED;

    private Tail tail;

    private boolean isColliding = false;

    public Player(){
        setupTail();

        this.getScale().set(0.4f, 0.4f);
        this.matchSpeedToVelocity();


    }
    public Tail getTail(){ return tail;}
    public void collide(){
        // Bonus
        Bonus bonus = Physic.bodyContains(this.position.x, this.position.y, Bonus.class);

        if(bonus != null){
            Random r = new Random();

            bonus.getPosition().set(r.nextInt(Settings.GAME_WIDTH), r.nextInt(Settings.GAME_HEIGHT));

            this.point += 10;

            this.tail.grow();
            this.tail.length += 1;

        }

        // Player
        Player player = Physic.bodyContains(this.position.x, this.position.y, Player.class);

        if(player != null) {
            if(!isColliding) {
                isColliding = true;
                this.blood -= 10;
            }
        }
        else isColliding = false;


    }
    private void setupTail(){
        try {
            this.tail = GameObjectPool.createAndAddAnddAddToPhysics(Tail.class);
            this.tail.setPlayer(this);
            this.tail.setupParticleDeque();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

        collide();

        checkOutSide();

        checkBlood();
    }
    private void checkOutSide(){
        if(position.x > Settings.GAME_WIDTH) position.x = 0;
        if(position.x < 0) position.x = Settings.GAME_WIDTH;
        if(position.y > Settings.GAME_HEIGHT) position.y = 0;
        if(position.y < 0) position.y = Settings.GAME_HEIGHT;
    }

    private void checkBlood(){
        if(this.blood <= 0){
            this.getTail().getParticleGroup().setActive(false);
            this.setActive(false);
        }

    }
    public void matchSpeedToVelocity(){
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

    @Override
    public boolean contains(Vector2D point) {
        return this.renderer.contains(point.minus(position));
    }
}
