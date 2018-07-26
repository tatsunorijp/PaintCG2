package Modelo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public class Tela extends Canvas {

    public GraphicsContext gc;
    public List<Forma> formas = new ArrayList<>();

    public Tela() {
        gc = getGraphicsContext2D();
    }

    public void desenhar(Paint p) {
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (Forma forma : formas) {
            forma.desenhar(gc);
        }
    }

    public void coordenadas(){
        for(Forma forma: formas){
            forma.imprimeCoordenadas();
        }
    }

}
