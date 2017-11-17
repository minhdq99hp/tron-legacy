package com.linhnt.speedshoot.player;

import com.linhnt.speedshoot.bases.GameObject;
import com.linhnt.speedshoot.bases.Vector2D;
import com.linhnt.speedshoot.bases.pool.GameObjectPool;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.ArrayList;

public class Tail extends GameObject {
    private float length = 3;
    private ArrayDeque<Particle> particleDeque = new ArrayDeque<>();
    private LinkedList<Particle> particles = new LinkedList<>();
    private Player player;
//    private ArrayList<Particle> particleArrayList = new ArrayList<>(5);
    public Tail(){

    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setupParticleDeque(){
        for(int i=0; i< 10; i++){
            try {
                Particle tmpParticle = GameObjectPool.createAndAdd(Particle.class);
                tmpParticle.getPosition().set(player.getPosition());

//                particleDeque.addFirst(tmpParticle);
                particles.addFirst(tmpParticle);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println(particles);
    }
    public float getLength(){ return length;}
    public void setLength(float value){ length = value;}

    @Override
    public void run(long milisecDelay, GameObject parent) {
        super.run(milisecDelay, parent);

        particles.getFirst().getPosition().set(player.getPosition());

        if(checkParticle()){
//            particleDeque.addFirst(particleDeque.removeLast());
            particles.addFirst(particles.removeLast());
        }

    }

    private boolean checkParticle(){
//        Particle f = particleDeque.removeFirst();
//        Particle n = particleDeque.removeFirst();
//        particleDeque.addFirst(n);
//        particleDeque.addFirst(f);
        Particle f = particles.get(0);
        Particle n = particles.get(1);
        return Math.sqrt(Math.pow(f.getPosition().x - n.getPosition().x, 2) + Math.pow(f.getPosition().y - n.getPosition().y, 2)) > 15;

    }
}
