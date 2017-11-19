package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.group.Group;
import com.linhnt.speedshoot.bases.physic.Physic;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;
import com.linhnt.speedshoot.main.Settings;

import java.util.LinkedList;

public class Tail extends GameObject {
    public float length = 10;
    private LinkedList<Particle> particles = new LinkedList<>();
    private Player player;
    private Group particleGroup;

    public Group getParticleGroup(){ return particleGroup; }
    public Tail(){
        try {
            particleGroup = GameObjectPool.createAndAddAnddAddToPhysics(Group.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setupParticleDeque(){
        for(int i=0; i< 10; i++){
            grow();

        }
    }

    public void grow(){
        try {
            Particle tmpParticle = GameObjectPool.create(Particle.class);
            particleGroup.addChild(tmpParticle);

            tmpParticle.getPosition().set(player.getPosition());
            particles.addFirst(tmpParticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void degrow(){
        if(particles.size() > 5) particleGroup.removeChild(particles.removeLast());
    }

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

        particles.getFirst().getPosition().set(player.getPosition());

        if(checkParticle()){
            particles.addFirst(particles.removeLast());
        }

        collide();

        if(Math.floor(length) < particles.size()) degrow();
        else if(Math.floor(length) > particles.size()) grow();

        if(particles.size() <= 5) player.setSpeed(Settings.NORMAL_SPEED);

    }
    private void collide(){
        for(int i=5; i<particles.size(); i++){
            Player player = Physic.bodyContains(particles.get(i).getPosition().x, particles.get(i).getPosition().y, Player.class);

            if(player != null){
                player.setBlood(0);
                player.getVelocity().set(0, 0);
                this.player.getVelocity().set(0, 0);
            }
        }
    }
    private boolean checkParticle(){
        Particle f = particles.get(0);
        Particle n = particles.get(1);
        return Math.sqrt(Math.pow(f.getPosition().x - n.getPosition().x, 2) + Math.pow(f.getPosition().y - n.getPosition().y, 2)) > 15;

    }
}
