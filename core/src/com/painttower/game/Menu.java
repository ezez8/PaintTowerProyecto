package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends Escena {
    Main game;
    Texture texture;
    SpriteBatch spriteBatch;
    Sprite sprite;

    public Menu(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        texture = new Texture("press.png");
        spriteBatch = new SpriteBatch();
        sprite = new Sprite(texture);
        sprite.setScale(2);
        sprite.setPosition(Gdx.graphics.getWidth()/2-sprite.getWidth()/2,Gdx.graphics.getHeight()/2-sprite.getHeight()/2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pintar();

        if(Gdx.input.isTouched()) {
            game.setScreen(new Partida(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        texture.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void pintar() {
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }
}
