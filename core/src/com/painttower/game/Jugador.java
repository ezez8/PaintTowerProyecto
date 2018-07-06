package com.painttower.game;

import com.badlogic.gdx.Gdx;

public class Jugador {
    public boolean tocar(){
        return Gdx.input.isTouched();
    }

    public float x(){
        return Gdx.input.getX();
    }

    public float y(){
        return Gdx.graphics.getHeight()-Gdx.input.getY();
    }
}
