package com.painttower.game;

public class Rectangulo extends Forma {
    private float ancho;
    private float largo;

    public Rectangulo(float x, float y, float largo, float ancho, Pintor pintor, Cuerpo cuerpo) {
        super(x, y, pintor, cuerpo);
        this.ancho = ancho;
        this.largo = largo;
    }

    @Override
    public void pintar(float x,float y) {
        getPintor().pintar(x,y,getRota());
    }

    @Override
    public float getLado() {
        return ancho;
    }
}
