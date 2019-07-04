import Controller.dbIngrediente;
import Model.Ingrediente;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private void botaoNovoIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Novo");
        dbIngrediente bd = new dbIngrediente();
        Ingrediente ing = new Ingrediente(10, "ml", 1, "Água", 5);
        bd.adicionaIngrediente(ing);
    }
    
    @FXML
    private void botaoSalvarIngrediente(javafx.event.ActionEvent event) {
        System.out.println("click Salvar");
    }
    
    @FXML
    private void botaoAlterarIngrediente(javafx.event.ActionEvent event) {
        System.out.println("click Alterar");
    }
    
    @FXML
    private void botaoExcluirIngrediente(javafx.event.ActionEvent event) {
        System.out.println("click Excluir");
    }
    
    @FXML
    private void botaoBuscarIngrediente(javafx.event.ActionEvent event) {
        System.out.println("click Buscar");
    }
}
