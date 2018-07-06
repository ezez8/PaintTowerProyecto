package com.painttower.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Partida extends Escena {
    Main game;

    private Jugador jugador;
    private World world;
    private OrthographicCamera camara;
    private Box2DDebugRenderer renderer;

    private Bloque tablero;
    private ArrayList<Bloque> moviles;
    private Bloque estatico;
    private Forma rectangulo;

    private Contador contador;

    boolean b1;

    public Partida(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        jugador = new Jugador();
        world = new World(new Vector2(0,5),true);
        camara = new OrthographicCamera(Gdx.graphics.getWidth()/100f,Gdx.graphics.getHeight()/100f);
        camara.translate(Gdx.graphics.getWidth()/2/100f,Gdx.graphics.getHeight()/2/100f);
        renderer = new Box2DDebugRenderer();

        tablero = new Bloque(2* Gdx.graphics.getWidth()/9f,Gdx.graphics.getHeight()/32f,new ArrayList<Forma>());
        moviles = new ArrayList<Bloque>();
        estatico = new Bloque(tablero.getX(),tablero.getY()*4,new ArrayList<Forma>());
        rectangulo = new Rectangulo(2* Gdx.graphics.getWidth()/9,Gdx.graphics.getHeight()-Gdx.graphics.getWidth()/18,Gdx.graphics.getWidth()/18,5*Gdx.graphics.getWidth()/9,new Pintor(new Texture("17.png"),new SpriteBatch(),new Sprite(new Texture("17.png"),5*Gdx.graphics.getWidth()/9,Gdx.graphics.getWidth()/18)),new Cuerpo( 2* (Gdx.graphics.getWidth()/9+5*Gdx.graphics.getWidth()/36)/100f,(Gdx.graphics.getHeight()-Gdx.graphics.getWidth()/36)/100f,5*Gdx.graphics.getWidth()/18/100f,Gdx.graphics.getWidth()/36/100f, BodyDef.BodyType.StaticBody,world));

        tablero.llenarTodo(world);

        contador = new Contador((int)(Math.random()*10)+1);

        b1 = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta,6,2);
        camara.update();
        renderer.render(world,camara.combined);

        if(jugador.tocar()){
            if(tablero.verificarToque(jugador.x(),jugador.y())&&!estatico.verificarToque(jugador.x(),jugador.y())) {
                estatico.agregar(tablero.getXForma(jugador.x()), tablero.getYForma(jugador.y()));
                contador.decre();
                b1 = true;
            }
            if(!tablero.verificarToque(jugador.x(),jugador.y())){
                estatico.vaciar(world);
                contador.reiniciar();
            }

        }
        else{
            if(b1 == true) {
                if(contador.getCont()==0) {
                    estatico.setCuerposFormas(BodyDef.BodyType.DynamicBody, world);
                    estatico.crearDistanceJoint(world);
                    moviles.add(estatico);
                    estatico = new Bloque(tablero.getX(), tablero.getY() * 4, new ArrayList<Forma>());
                    contador.random((int)(Math.random()*10)+1);
                    b1 = false;
                }
                else{
                    estatico.vaciar(world);
                    contador.reiniciar();
                }
            }
        }


        if(contador.getCont()==0) {
            estatico.setTex(new Texture(((int)(Math.random()*8)+11)+".png"));
        }
        else
            estatico.setTex(new Texture("grisclaro.png"));

        if(gameOver()){
            game.setScreen(new GameOver(game));
        }

       // desplazarcamara();

        actualizar();
        pintar();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        tablero.delete(world);
        estatico.delete(world);
        rectangulo.delete(world);

        moviles.get(0).pintar();

        for (Bloque b:moviles)
            b.delete(world);

        world.dispose();
        renderer.dispose();
    }

    @Override
    public void pintar() {
        tablero.pintar();
        estatico.pintar();
        for(Bloque b:moviles)
            b.pintar();
        rectangulo.pintar(rectangulo.getX(),rectangulo.getY());
        contador.pintar();
    }

    public void actualizar(){
        for (Bloque b:moviles){
            b.actualizar();
        }
    }

    public void desplazarcamara(){
        for (Bloque b:moviles){
            for (Forma f:b.getFormas()){
                if(f.getY() < Gdx.graphics.getHeight()/2);
                    camara.translate(camara.viewportWidth,camara.viewportHeight-100f);
            }
        }
    }

    public boolean gameOver(){
        for (Bloque b:moviles){
            for (Forma f:b.getFormas()){
                if(f.getY() > 2*Gdx.graphics.getHeight())
                    return true;
            }
        }
        return false;
    }

}
