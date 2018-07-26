package Modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tatsunori on 05/07/2018.
 */
public abstract class Forma {
    public Paint p;
    public ArrayList<Coordenada> coordenadas = new ArrayList<>();

    public void redimencionar(double sx, double sy){}
    public void rotacionar(double angulo){}
    public void zoomExtends(){}

    public abstract void desenhar(GraphicsContext gc);
    public abstract void imprimeCoordenadas();
    public abstract void transladar(double x, double y);
    public abstract void selecionar();
    public abstract void descelecionar();

    public StringProperty nameProperty() {
        return new SimpleStringProperty(getClass().getSimpleName());
    }

}
