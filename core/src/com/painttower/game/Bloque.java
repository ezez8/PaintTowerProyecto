package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;

import java.util.ArrayList;

public class Bloque {
    private ArrayList<Forma> formas;
    DistanceJointDef distanceJointDef;
    Joint joint;
    private float x;
    private  float y;

    public Bloque(float x, float y, ArrayList<Forma> formas) {
        this.x = x;
        this.y = y;
        this.formas = formas;
    }

    public void agregar(float x,float y){
        float lado = Gdx.graphics.getWidth()/9;
        formas.add(new Cuadrado(x,y,lado,new Pintor(new Texture("grisclaro.png"),new SpriteBatch(),new Sprite(new Texture("grisclaro.png"),(int)(x+1),(int)(y+1),(int)(lado-2),(int)(lado-2))),null));
    }

    public void vaciar(World world){
        delete(world);
        formas.clear();
    }

    public void llenarTodo(World world){
        float i;
        float j;
        float lado = Gdx.graphics.getWidth()/9;
        for(i = 0;i<3; i++){
            for(j = 0;j<5; j++){
                formas.add(new Cuadrado(x+j*lado,y+i*lado,lado,new Pintor(new Texture("gris.png"),new SpriteBatch(),new Sprite(new Texture("gris.png"),(int)(x+j*lado+1),(int)(y+i*lado+1),(int)(lado-2),(int)(lado-2))),null));
            }
        }
    }

    public void crearDistanceJoint(World world){
        for (Forma f1:formas){
            for (Forma f2:formas){
                if(f1 != f2) {
                    distanceJointDef = new DistanceJointDef();
                    distanceJointDef.length = (float) Math.sqrt(Math.pow((double)(f1.getCuerpo().getX()-f2.getCuerpo().getX()),2)+Math.pow((double)(f1.getCuerpo().getY()-f2.getCuerpo().getY()),2));
                    distanceJointDef.collideConnected = true;
                    distanceJointDef.bodyA = f1.getCuerpo().getBody();
                    distanceJointDef.bodyB = f2.getCuerpo().getBody();
                    world.createJoint(distanceJointDef);
                }
            }
        }
    }

    public boolean verificarToque(float x,float y){
        for(Forma f:formas){
            if((x >= f.getX())&&(x <= f.getX()+f.getLado())&&(y >= f.getY())&&(y <= f.getY()+f.getLado()))
                return true;
        }
        return false;
    }

    public float getXForma(float x){
        for(Forma f:formas){
            if((x >= f.getX()) && (x <= f.getX() + f.getLado()))
                return f.getX();
        }
        return 0;
    }
    public float getYForma(float y) {
        for (Forma f : formas) {
            if ((y >= f.getY()) && (y <= f.getY() + f.getLado()))
                return f.getY();
        }
        return 0;
    }

    public void pintar(){
        for(Forma f:formas)
            f.getPintor().pintar(f.getX(),f.getY(),f.getRota());
    }

    public void delete(World world){
        for(Forma f:formas)
            f.delete(world);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public ArrayList<Forma> getFormas() {
        return formas;
    }

    public void setCuerposFormas(BodyDef.BodyType bodyType, World world){
        for(Forma f:formas){
            f.setCuerpo(bodyType,world);
        }
    }

    public void actualizar(){
        for(Forma f:formas){
            f.actualizar();
        }
    }

    public void setTex(Texture tex){
        for (Forma f:formas)
            f.setTex(tex);
    }
}
