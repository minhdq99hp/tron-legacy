package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.bases.physic.PhysicBody;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Particle extends GameObject implements PhysicBody{
    public Particle(){
        try {
            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/tail1- v1.png")));
            this.getScale().set(0.5f, 0.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(Vector2D point) {
        return false;
    }
}
