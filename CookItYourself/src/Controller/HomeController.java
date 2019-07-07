package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import utils.Utils;

public class HomeController {

    @FXML
    void clickBotaoEntrada(MouseEvent event) throws IOException {
        System.out.println("Click Entrada");
        Utils.primeiraSelecao = 1;
        carregaProximaTela();
    }

    @FXML
    void clickBotaoPratoPrincipal(MouseEvent event) throws IOException {
        System.out.println("Click Principal");
        Utils.primeiraSelecao = 2;
        carregaProximaTela();
    }

    @FXML
    void clickBotaoSobremesa(MouseEvent event) throws IOException {
        System.out.println("Click Sobremesa");
        Utils.primeiraSelecao = 3;
        carregaProximaTela();
    }

    public void carregaProximaTela() throws IOException {
        System.out.println("Click Iniciar Filtro");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Filtragem.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Primeiro Filtro");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }
}
