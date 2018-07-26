package Modelo;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public class Coordenada {
    public double x, y;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double calculaDistancia(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

    }
}
