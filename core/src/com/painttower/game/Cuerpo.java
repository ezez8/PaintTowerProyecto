package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Cuerpo {
    private Body body;
    private Fixture fixture;
    private BodyDef bodyDef;
    private float x;
    private float y;
    private float rota;

    public Cuerpo(float x, float y, float largo, float ancho, BodyDef.BodyType bodyType, World world) {
        this.x = x;
        this.y = y;

        bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = bodyType;
        body = world.createBody(bodyDef);
        rota = body.getAngle();

        PolygonShape bodySha = new PolygonShape();
        bodySha.setAsBox(largo,ancho);
        fixture = body.createFixture(bodySha,1);
        bodySha.dispose();
    }

    public void setBodyDef(float largo,float ancho,BodyDef.BodyType bodyType,World world){
        delete(world);

        bodyDef = new BodyDef();
        bodyDef.position.set(x,y);
        bodyDef.type = bodyType;
        body = world.createBody(bodyDef);

        PolygonShape bodySha = new PolygonShape();
        bodySha.setAsBox(largo,ancho);
        fixture = body.createFixture(bodySha,1);
        bodySha.dispose();
    }

    public void delete(World world){
        if(body != null) {
            body.destroyFixture(fixture);
            world.destroyBody(body);
        }
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public float getRota() {
        return body.getAngle();
    }

    public Body getBody() {
        return body;
    }
}
