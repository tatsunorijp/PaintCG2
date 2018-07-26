package Modelo;

import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.CYAN;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public class Circunferencia extends Forma{
    public Coordenada c1;
    public double raio;
    Boolean selecionado = false;

    public Circunferencia(Coordenada c1, double raio) {
        this.c1 = c1;
        this.raio = raio;
    }

    @Override
    public void desenhar(GraphicsContext gc) {
        if(selecionado){
            gc.setStroke(CYAN);
        }else {
            gc.setStroke(super.p);
        }
        gc.strokeOval(c1.x, c1.y, raio, raio);
    }

    @Override
    public void imprimeCoordenadas() {
        System.out.println("Cricunferencia:");
        System.out.println("Centro:" + " x: "+c1.x + " y: "+c1.y+" Raio:" + raio);
    }




    @Override
    public void transladar(double x, double y) {
        this.c1.x = x;
        this.c1.y = y;
    }

    @Override
    public void selecionar() {
        selecionado = true;
    }

    @Override
    public void descelecionar() {
        selecionado = false;
    }
}
