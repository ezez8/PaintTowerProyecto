package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Contador {
    private int ini;
    private int cont;
    private Pintor pintor;

    public Contador(int ini) {
        this.ini = ini;
        cont = ini;
        pintor = new Pintor(new Texture(ini+".png"),new SpriteBatch(),new Sprite(new Texture(ini+".png")));
    }

    public void decre(){
        cont--;
    }

    public void random(int ini){
        this.ini = ini;
        cont = ini;
        pintor.delete();
        pintor = new Pintor(new Texture(ini+".png"),new SpriteBatch(),new Sprite(new Texture(ini+".png")));
    }

    public void reiniciar(){
        cont = ini;
    }

    public void pintar(){
        pintor.pintar(0,0,0);
    }

    public int getCont() {
        return cont;
    }

}
