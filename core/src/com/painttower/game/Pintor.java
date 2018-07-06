package com.painttower.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Pintor {
    private Texture tex;
    private Batch batch;
    private Sprite sprite;

    public Pintor(Texture tex, Batch batch, Sprite sprite) {
        this.tex = tex;
        this.batch = batch;
        this.sprite = sprite;
    }

    public void pintar(float x, float y,float rota){
        sprite.setTexture(tex);
        sprite.setPosition(x+1,y+1);
        sprite.setRotation(rota);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void setPosicion(float x,float y){
        sprite.setPosition(x,y);
    }

    public void setTex(Texture tex) {
        this.tex.dispose();
        this.tex = tex;
    }

    public void delete(){
        tex.dispose();
        batch.dispose();
    }
}
