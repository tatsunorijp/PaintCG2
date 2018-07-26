package Modelo;

import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.CYAN;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public class Triangulo extends Forma{
    public Coordenada c1;
    public Coordenada c2;
    public Coordenada c3;
    Boolean selecionado = false;

    public Triangulo(Coordenada c1, Coordenada c2, Coordenada c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    @Override
    public void desenhar(GraphicsContext gc) {
        if(selecionado){
            gc.setStroke(CYAN);
        }else{
            gc.setStroke(super.p);
        }
        gc.strokeLine(c1.x, c1.y, c2.x, c2.y);
        gc.strokeLine(c2.x, c2.y, c3.x, c3.y);
        gc.strokeLine(c3.x, c3.y, c1.x, c1.y);
    }

    @Override
    public void imprimeCoordenadas() {
        System.out.println("Triangulo:");
        System.out.println("x1: "+c1.x+" y1: "+c1.y+" x2: "+c2.x+" y2: "+c2.y+" x3: "+c3.x+" y3: "+c3.y);
    }

    @Override
    public void transladar(double x, double y) {
        this.c3.x = (x - c1.x) + c3.x;
        this.c3.y = (y - c1.y) + c3.y;
        this.c2.x = (x - c1.x) + c2.x;
        this.c2.y = (y - c1.y) + c2.y;

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
