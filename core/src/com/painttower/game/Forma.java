package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Forma {
    private float x;
    private float y;
    private float rota;
    private Pintor pintor;
    private Cuerpo cuerpo;

    public Forma(float x, float y, Pintor pintor, Cuerpo cuerpo) {
        this.x = x;
        this.y = y;
        this.pintor = pintor;
        pintor.setPosicion(x,y);
        this.cuerpo = cuerpo;
    }

    public abstract void pintar(float x,float y);

    public void delete(World world){
        pintor.delete();
        if(cuerpo != null)
            cuerpo.delete(world);
    }

    public void setCuerpo(BodyDef.BodyType bodyType, World world){
        cuerpo = new Cuerpo(x/100f+getLado()/2/100f , y/100f+getLado()/2/100f,getLado()/2/100f,getLado()/2/100f,bodyType,world);
    }

    public void actualizar(){
        if (cuerpo != null) {
            x = cuerpo.getX()*100f-getLado()/2f;
            y = cuerpo.getY()*100f-getLado()/2f;
            rota = (float) Math.toDegrees((double) cuerpo.getRota());
        }
    }

    public void setTex(Texture tex){
        pintor.setTex(tex);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRota() {
        return rota;
    }

    public Cuerpo getCuerpo() {
        return cuerpo;
    }

    public Pintor getPintor() {
        return pintor;
    }

    public abstract float getLado();
}
