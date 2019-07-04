/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
//import utils.BDMercado;

/**
 *
 * @author Burca
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button11;
    @FXML
    private ListView listView;
    
    //Primeira etapa de filtragem
    @FXML
    private void buttonEntrada(ActionEvent event) {
        System.out.println("Selecionado: Entrada");
        label.setText("Entrada");
    }
    
    @FXML
    private void buttonPratoPrincipal(ActionEvent event) {
        System.out.println("Selecionado: Prato Principal");
        label.setText("Prato Principal");
    }
    
    @FXML
    private void buttonSobremesa(ActionEvent event) {
        System.out.println("Selecionado: Sobremesa");
        label.setText("Sobremesa");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //BDMercado bdmercado = new BDMercado();
        //listView.getItems().addAll(bdmercado.produtos);
    }
}
