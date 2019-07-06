package Controller;

import Model.Ingrediente;
import utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CadastroReceitaIngredienteController {

    @FXML
    private TextField txtBuscarReceita;

    @FXML
    private Button btnBuscarReceita;

    @FXML
    private TextField txtNomeReceita;

    @FXML
    private TextArea txtModoPreparo;

    @FXML
    private TextField txtTempoPreparo;

    @FXML
    private TextField txtRendimento;

    @FXML
    private ComboBox<?> cmbIngrediente;

    @FXML
    private ComboBox<?> cmbMedida;

    @FXML
    private TextField nomeIngrediente;

    @FXML
    private TextField precoIngrediente;

    @FXML
    private TextField estoqueIngrediente;

    @FXML
    private void botaoSalvarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("Click Salvar");

        String nomeIngredienteString = nomeIngrediente.getText();
        String precoIngredienteString = precoIngrediente.getText();
        String estoqueIngredienteString = estoqueIngrediente.getText();

        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngredienteString, precoIngredienteString, estoqueIngredienteString);

        if (camposPreenchidos) {
            ingrediente.setNome(nomeIngredienteString);
            
            if (Utils.isFloat(precoIngredienteString)) {
                ingrediente.setPreco(Float.parseFloat(precoIngredienteString));
            } else {
                JOptionPane.showMessageDialog(null, "Preço não é valido (numero real ou inteiro).");
            }
            
            if (Utils.isInteger(estoqueIngredienteString)) {
                ingrediente.setEstoque(Integer.parseInt(estoqueIngredienteString));
            } else {
                JOptionPane.showMessageDialog(null, "Estoque não é numero inteiro.");
            }

            db.adicionaIngrediente(ingrediente);
            System.out.println("Ingrediente Adicionado: " + ingrediente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    @FXML
    private void botaoAlterarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Alterar");
        System.out.println("NOME = " + nomeIngrediente.getText());
        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setNome(nomeIngrediente.getText());
        ingrediente.setPreco(Float.parseFloat(precoIngrediente.getText()));
        ingrediente.setEstoque(Integer.parseInt(estoqueIngrediente.getText()));
        //ingrediente.setid = selecionado na tabela de pesquisa

        db.alteraIngrediente(ingrediente);
    }

    @FXML
    private void botaoExcluirIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Excluir");

        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        //ingrediente.setid = selecionado na tabela de pesquisa
        db.removeIngrediente(ingrediente.getId());
    }

    @FXML
    private void botaoBuscarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Buscar");
        dbIngrediente db = new dbIngrediente();

        db.pesquisaIngrediente();
    }

    @FXML
    private void botaoFiltro(javafx.event.ActionEvent event) throws IOException {
        System.out.println("click Iniciar Filtro");
        Parent root = FXMLLoader.load(getClass().getResource("/View/PrimeiroFiltro.fxml"));
        Stage stage = new Stage();
        stage.setTitle("PrimeiroFiltro");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }
}
