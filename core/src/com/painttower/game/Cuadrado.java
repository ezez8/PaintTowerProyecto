package com.painttower.game;

public class Cuadrado extends Forma {
    private float lado;

    public Cuadrado(float x, float y, float lado, Pintor pintor, Cuerpo cuerpo) {
        super(x, y, pintor, cuerpo);
        this.lado = lado;
    }

    @Override
    public void pintar(float x,float y) {
        getPintor().pintar(x,y,getRota());
    }

    @Override
    public float getLado() {
        return lado;
    }
}
