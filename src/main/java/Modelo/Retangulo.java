package Modelo;

import javafx.scene.canvas.GraphicsContext;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.CYAN;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public class Retangulo extends Forma {
    public Coordenada c1;
    public Coordenada c2;
    Boolean selecionado = false;

    public Retangulo(Coordenada c1, Coordenada c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void desenhar(GraphicsContext gc) {
        if(selecionado){
            gc.setStroke(CYAN);
        }else{
            gc.setStroke(super.p);
        }

        gc.strokeLine(c1.x, c1.y, c2.x, c1.y);
        gc.strokeLine(c1.x, c1.y, c1.x, c2.y);
        gc.strokeLine(c2.x, c2.y, c2.x, c1.y);
        gc.strokeLine(c2.x, c2.y, c1.x, c2.y);

    }

    @Override
    public void imprimeCoordenadas() {
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
