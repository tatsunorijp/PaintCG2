package Controle;

import Modelo.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by Tatsunori on 05/07/2018.
 */
public class inicioControle implements Initializable{
    @FXML
    JFXColorPicker cpColorPicker;
    @FXML
    Tela cCanvas;
    @FXML
    TableView tvObjetos;
    @FXML
    TableColumn tcObjetos;
    @FXML
    Text mouseX;
    @FXML
    Text mouseY;
    @FXML
    JFXTextField tfAux;
    @FXML
    JFXButton btEscala;

    Forma formaEmContrucao = null;
    Forma selecionada = null;
    ObservableList<Forma> list;
    GraphicsContext gc;

    int forma = 0;
    int clicks = 0;
    int operacao = 0;
    double x, y;


    public void btHelpFunc(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/help.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void btRetasFunc(){
        forma = 1;
    }
    public  void btTriangulosFunc(){
        forma = 2;
    }
    public void btRetangulosFunc(){
        forma = 3;
    }
    public void btCircunferenciasFunc(){
        forma = 4;
    }
    public void btTransladarFunc(){
        cCanvas.setOnMouseClicked(i -> {
            selecionada.transladar(x, y);
            selecionada.selecionar();
            cCanvas.desenhar(cpColorPicker.getValue());
            operacao = 0;
        });
    }
    public void btEscalaFunc(){
        if(selecionada instanceof Linha) {
            Coordenada c1 = new Coordenada(((Linha) selecionada).c1.x, ((Linha) selecionada).c1.y);
            ((Linha) selecionada).c1.x = ((Linha) selecionada).c1.x * Double.valueOf(tfAux.getText());
            ((Linha) selecionada).c1.y = ((Linha) selecionada).c1.y * Double.valueOf(tfAux.getText());
            ((Linha) selecionada).c2.x = ((Linha) selecionada).c2.x * Double.valueOf(tfAux.getText());
            ((Linha) selecionada).c2.y = ((Linha) selecionada).c2.y * Double.valueOf(tfAux.getText());
            selecionada.transladar(c1.x, c1.y);
        }

        if(selecionada instanceof Triangulo) {
            Coordenada c1 = new Coordenada(((Triangulo) selecionada).c1.x, ((Triangulo) selecionada).c1.y);
            ((Triangulo) selecionada).c1.x = ((Triangulo) selecionada).c1.x * Double.valueOf(tfAux.getText());
            ((Triangulo) selecionada).c1.y = ((Triangulo) selecionada).c1.y * Double.valueOf(tfAux.getText());
            ((Triangulo) selecionada).c2.x = ((Triangulo) selecionada).c2.x * Double.valueOf(tfAux.getText());
            ((Triangulo) selecionada).c2.y = ((Triangulo) selecionada).c2.y * Double.valueOf(tfAux.getText());
            ((Triangulo) selecionada).c3.x = ((Triangulo) selecionada).c3.x * Double.valueOf(tfAux.getText());
            ((Triangulo) selecionada).c3.y = ((Triangulo) selecionada).c3.y * Double.valueOf(tfAux.getText());
            selecionada.transladar(c1.x, c1.y);
        }

        if(selecionada instanceof Retangulo) {
            Coordenada c1 = new Coordenada(((Retangulo) selecionada).c1.x, ((Retangulo) selecionada).c1.y);
            ((Retangulo) selecionada).c1.x = ((Retangulo) selecionada).c1.x * Double.valueOf(tfAux.getText());
            ((Retangulo) selecionada).c1.y = ((Retangulo) selecionada).c1.y * Double.valueOf(tfAux.getText());
            ((Retangulo) selecionada).c2.x = ((Retangulo) selecionada).c2.x * Double.valueOf(tfAux.getText());
            ((Retangulo) selecionada).c2.y = ((Retangulo) selecionada).c2.y * Double.valueOf(tfAux.getText());
            selecionada.transladar(c1.x, c1.y);
        }

        if(selecionada instanceof Circunferencia) {
            Coordenada c1 = new Coordenada(((Circunferencia) selecionada).c1.x, ((Circunferencia) selecionada).c1.y);
            ((Circunferencia) selecionada).raio = ((Circunferencia) selecionada).raio * Double.valueOf(tfAux.getText());
            selecionada.transladar(c1.x, c1.y);
        }
        cCanvas.desenhar(cpColorPicker.getValue());
    }
    public void btClearFunc(){
        cCanvas.formas = new ArrayList<>();
        cCanvas.desenhar(cpColorPicker.getValue());
        atualizaObservableList();
    }
    public void atualizaObservableList(){
        list = FXCollections.observableList(cCanvas.formas);
        tcObjetos.setCellValueFactory( new PropertyValueFactory<>("name"));
        tvObjetos.setItems(list);
    }
    public void initialize(URL location, ResourceBundle resources) {
        cpColorPicker.setValue(Color.DARKGREEN);
        gc = cCanvas.getGraphicsContext2D();
        cCanvas.setOnMouseMoved(e -> {
            if(forma == 1){
                if(clicks == 0){
                    cCanvas.setOnMouseClicked(i ->{
                        Linha linha = new Linha(null, null);
                        linha.c1 = new Coordenada(x, y);
                        linha.p = cpColorPicker.getValue();
                        formaEmContrucao = linha;
                        cCanvas.formas.add(linha);
                        clicks++;
                    });
                }else if(clicks == 1){
                    Linha linha = (Linha) formaEmContrucao;
                    linha.c2 = new Coordenada(x, y);
                    cCanvas.setOnMouseClicked(i ->{
                        clicks = 0;
                        forma = 0;
                        formaEmContrucao = null;
                        atualizaObservableList();
                    });
                }
            }

            if(forma == 2){
                if(clicks == 0){
                    cCanvas.setOnMouseClicked(i ->{
                        Triangulo triangulo= new Triangulo(null, null, null);
                        triangulo.c1 = new Coordenada(x, y);
                        triangulo.c2 = new Coordenada(x, y);
                        triangulo.c3 = new Coordenada(x, y);
                        triangulo.p = cpColorPicker.getValue();
                        formaEmContrucao = triangulo;
                        cCanvas.formas.add(triangulo);
                        clicks++;
                    });
                }else if(clicks == 1){
                    Triangulo triangulo = (Triangulo) formaEmContrucao;
                    triangulo.c2 = new Coordenada(x, y);
                    triangulo.c3 = new Coordenada(x, y);
                    cCanvas.setOnMouseClicked(i -> {
                        clicks++;
                    });
                }else{
                    Triangulo triangulo = (Triangulo) formaEmContrucao;
                    triangulo.c3 = new Coordenada(x, y);
                    cCanvas.setOnMouseClicked(i ->{
                        clicks = 0;
                        forma = 0;
                        formaEmContrucao = null;
                        atualizaObservableList();
                    });
                }
            }

            if(forma == 3){
                if(clicks == 0){
                    cCanvas.setOnMouseClicked(i ->{
                        Retangulo retangulo = new Retangulo(null, null);
                        retangulo.c1 = new Coordenada(x, y);
                        retangulo.p = cpColorPicker.getValue();
                        formaEmContrucao = retangulo;
                        cCanvas.formas.add(retangulo);
                        clicks++;
                    });
                }else if(clicks == 1){
                    Retangulo retangulo = (Retangulo) formaEmContrucao;
                    retangulo.c2 = new Coordenada(x, y);
                    cCanvas.setOnMouseClicked(i ->{
                        clicks = 0;
                        forma = 0;
                        formaEmContrucao = null;
                        atualizaObservableList();
                    });
                }
            }

            if(forma == 4){
                if(clicks == 0){
                    cCanvas.setOnMouseClicked(i ->{
                        Circunferencia circinferencia = new Circunferencia(null, 0);
                        circinferencia.c1 = new Coordenada(x, y);
                        circinferencia.raio = 0;
                        circinferencia.p = cpColorPicker.getValue();
                        formaEmContrucao = circinferencia;
                        cCanvas.formas.add(circinferencia);
                        clicks++;
                    });
                }else if(clicks == 1){
                    Circunferencia circunferencia = (Circunferencia) formaEmContrucao;
                    Coordenada x2 = new Coordenada(x, y);
                    circunferencia.raio = Math.sqrt( Math.pow(x - circunferencia.c1.x, 2) + Math.pow(y - circunferencia.c1.y, 2));
                    cCanvas.setOnMouseClicked(i ->{
                        clicks = 0;
                        forma = 0;
                        formaEmContrucao = null;
                        atualizaObservableList();
                    });
                }
            }
/*
            else if(operacao == 3){
                cCanvas.setOnMouseClicked(i -> {
                    selecionada.transladar(x, y);
                    selecionada.selecionar();
                    cCanvas.desenhar(cpColorPicker.getValue());
                    operacao = 0;
                });

            }*/

            x = e.getX();
            y = e.getY();
            mouseX.setText(String.valueOf(e.getX()));
            mouseY.setText(String.valueOf(e.getY()));
            if(tvObjetos.getSelectionModel().getSelectedItem()!= null){
                selecionada = (Forma)tvObjetos.getSelectionModel().getSelectedItem();
                selecionada.selecionar();
            }
            cCanvas.desenhar(cpColorPicker.getValue());
            if(tvObjetos.getSelectionModel().getSelectedItem() != null){
                selecionada.descelecionar();
            }
        });

    }
}
