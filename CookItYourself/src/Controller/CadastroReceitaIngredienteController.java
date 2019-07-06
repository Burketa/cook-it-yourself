package Controller;

import Model.Ingrediente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Utils;

public class CadastroReceitaIngredienteController {

    private Ingrediente ingredienteSelecionado;

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
    private TextField idIngrediente;
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
    private TextField pesquisaIngrediente;

    @FXML
    public void initialize() throws SQLException {
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoCadastrarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Salvar");
        cadastrarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoRecuperarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("click Buscar");
        recuperarIngrediente(true);
    }

    @FXML
    private void botaoAlterarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Alterar");
        alterarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoDeletarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("click Excluir");
        deletarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoFiltro(ActionEvent event) throws IOException {
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

    public void recuperarIngrediente(boolean isBusca) throws SQLException {
        dbIngrediente db = new dbIngrediente();
        List<Ingrediente> listIngrediente = new ArrayList<>();
        if (isBusca) {
            listIngrediente = db.pesquisaIngrediente(pesquisaIngrediente.getText());
        } else {
            listIngrediente = db.pesquisaIngrediente("");
        }
        if (listIngrediente.size() > 0) {
            listView.getItems().setAll(listIngrediente);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado");
        }
    }

    public void alterarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();
        Ingrediente ingrediente = new Ingrediente();

        String idIngredienteString = idIngrediente.getText();
        String nomeIngredienteString = nomeIngrediente.getText();
        String precoIngredienteString = precoIngrediente.getText();
        String estoqueIngredienteString = estoqueIngrediente.getText();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngredienteString, precoIngredienteString, estoqueIngredienteString);

        if (camposPreenchidos) {
            ingrediente.setId(Integer.parseInt(idIngredienteString));

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
            System.out.println("Ingrediente Alterado");
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void deletarIngrediente() throws SQLException {

        dbIngrediente db = new dbIngrediente();
        String idIngredienteString = idIngrediente.getText();
        if (!idIngredienteString.equals("")) {
            int idIngredienteSelecionado = Integer.parseInt(idIngredienteString);
            db.removeIngrediente(idIngredienteSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ingrediente.");
        }
    }

    @FXML
    public void clickIngrediente(MouseEvent event) throws IOException {

        selecionarIngrediente();
    }

    private void selecionarIngrediente() {
        Ingrediente ingrediente = (Ingrediente) listView.getSelectionModel().getSelectedItem();

        ingredienteSelecionado = ingrediente;

        idIngrediente.setText(String.valueOf(ingrediente.getId()));
        nomeIngrediente.setText(ingrediente.getNome());
        precoIngrediente.setText(String.valueOf(ingrediente.getPreco()));
        estoqueIngrediente.setText(String.valueOf(ingrediente.getEstoque()));

    }

}
