package com.linhnt.speedshoot.bonus;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.animation.SingleImageRenderer;
import com.linhnt.speedshoot.utils.ImageUtils;

import java.io.IOException;

public class Bonus extends GameObject {
    public Bonus(){
        try {
            this.setRenderer(new SingleImageRenderer(ImageUtils.read("assets/images/bonus.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);
    }
}
