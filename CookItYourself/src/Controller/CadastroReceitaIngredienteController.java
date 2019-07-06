package Controller;

import Model.Ingrediente;
import utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
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
    private TableView<Ingrediente> tabelaIngrediente;
    @FXML
    private TableColumn nomeIngredienteColuna;
    @FXML
    private TableColumn precoIngredienteColuna;
    @FXML
    private TableColumn estoqueIngredienteColuna;
    @FXML
    private ListView listView;

    @FXML
    private void botaoCadastrarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("Click Salvar");
        cadastrarIngrediente();
        recuperarIngrediente();
    }

    @FXML
    private void botaoRecuperarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Buscar");
        recuperarIngrediente();
    }

    @FXML
    private void botaoAlterarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("Click Alterar");
        alterarIngrediente();
        recuperarIngrediente();
    }

    @FXML
    private void botaoDeletarIngrediente(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("click Excluir");
        deletarIngrediente();
        recuperarIngrediente();
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

    public void cadastrarIngrediente() throws SQLException {

        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        String nomeIngredienteString = nomeIngrediente.getText();
        String precoIngredienteString = precoIngrediente.getText();
        String estoqueIngredienteString = estoqueIngrediente.getText();

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

    public void recuperarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();
        ObservableList<Ingrediente> observableListIngrediente = FXCollections.observableArrayList(db.pesquisaIngrediente());
        System.out.println(observableListIngrediente);
        //tabelaIngrediente.setItems(observableListIngrediente);
        listView.getItems().setAll(db.pesquisaIngrediente());

        /* nomeIngredienteColuna.setcell(
         new PropertyValueFactory<>("nome"));
         precoIngredienteColuna.setCellValueFactory(
         new PropertyValueFactory<>("idade"));
         estoqueIngredienteColuna.setCellValueFactory(
         new PropertyValueFactory<>("endereco"));
        
         */
        //System.out.println(db.pesquisaIngrediente());
    }

    public void alterarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        String nomeIngredienteString = nomeIngrediente.getText();
        String precoIngredienteString = precoIngrediente.getText();
        String estoqueIngredienteString = estoqueIngrediente.getText();
        //ingrediente.setid = selecionado na tabela de pesquisa

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

            db.alteraIngrediente(ingrediente);
            System.out.println("Ingrediente Alterado: " + ingrediente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void deletarIngrediente() throws SQLException {

        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        //nome.setcell //ingrediente.setid = selecionado na tabela de pesquisa
        db.removeIngrediente(ingrediente);
    }
}
