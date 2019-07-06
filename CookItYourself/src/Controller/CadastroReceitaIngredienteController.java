package Controller;

import Model.Categoria;
import Model.Ingrediente;
import Model.Medida;
import Model.Receita;
import Model.Tipica;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Utils;

public class CadastroReceitaIngredienteController {

    private Ingrediente ingredienteSelecionado;

    //region FXML Ingredientes
    @FXML
    private TextField pesquisaIngrediente;
    @FXML
    private TextField idIngrediente;
    @FXML
    private TextField nomeIngrediente;
    @FXML
    private TextField precoIngrediente;
    @FXML
    private TextField estoqueIngrediente;
    @FXML
    private ComboBox<Medida> medidaIngrediente;
    @FXML
    private TableView<Ingrediente> tabelaIngrediente;
    @FXML
    private TableColumn<Ingrediente, Integer> idIngredienteColuna;
    @FXML
    private TableColumn<Ingrediente, String> nomeIngredienteColuna;
    @FXML
    private TableColumn<Ingrediente, Float> precoIngredienteColuna;
    @FXML
    private TableColumn<Ingrediente, Integer> estoqueIngredienteColuna;
    @FXML
    private TableColumn<Ingrediente, String> medidaIngredienteColuna;
    //endregion

    //region FXML Receitas
    @FXML
    private TextField idReceita;
    @FXML
    private TextField nomeReceita;
    @FXML
    private TextArea modoPreparoReceita;
    @FXML
    private TextField tempoPreparoReceita;
    @FXML
    private TextField rendimentoReceita;
    @FXML
    private ComboBox<Categoria> categoriaReceita;
    @FXML
    private ComboBox<Tipica> tipicaReceita;
    @FXML
    private ComboBox<Ingrediente> ingredienteReceita;
    @FXML
    private TextField quantidadeReceita;
    @FXML
    private TableView<Receita> tabelaReceita;
    @FXML
    private TableColumn<Receita, Integer> idReceitaColuna;
    @FXML
    private TableColumn<Receita, String> nomeReceitaColuna;
    @FXML
    private TableColumn<Receita, String> preparoReceitaColuna;
    @FXML
    private TableColumn<Receita, String> tempoReceitaColuna;
    @FXML
    private TableColumn<Receita, Integer> rendimentoReceitaColuna;
    //endregion

    @FXML
    public void initialize() throws SQLException {
        recuperarIngrediente(false);
        recuperarReceita(false);
        configurarComboBoxes();
    }

    //region Ingrediente
    @FXML
    private void botaoCadastrarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Salvar Ingrediente");
        cadastrarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoRecuperarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Buscar Ingrediente");
        recuperarIngrediente(true);
    }

    @FXML
    private void botaoAlterarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Alterar Ingrediente");
        alterarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoDeletarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("click Excluir Ingrediente");
        deletarIngrediente();
        recuperarIngrediente(false);
    }

    @FXML
    private void botaoFiltro(ActionEvent event) throws IOException {
        System.out.println("Click Iniciar Filtro");
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
        //String medidaIngredienteString = medidaIngrediente.getText();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngredienteString, precoIngredienteString, estoqueIngredienteString, "a");

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
            listIngrediente = db.recuperaIngredientes(pesquisaIngrediente.getText());
        } else {
            listIngrediente = db.recuperaIngredientes("");
        }
        if (listIngrediente.size() > 0) {
            tabelaIngrediente.getItems().setAll(listIngrediente);
            idIngredienteColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeIngredienteColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            precoIngredienteColuna.setCellValueFactory(new PropertyValueFactory<>("preco"));
            estoqueIngredienteColuna.setCellValueFactory(new PropertyValueFactory<>("estoque"));
            medidaIngredienteColuna.setCellValueFactory(new PropertyValueFactory<>("medidaPadrao"));
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
        //String medidaIngredienteString = medidaIngrediente.getText();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngredienteString, precoIngredienteString, estoqueIngredienteString, "a");

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
        Ingrediente ingrediente = (Ingrediente) tabelaIngrediente.getSelectionModel().getSelectedItem();

        ingredienteSelecionado = ingrediente;

        idIngrediente.setText(String.valueOf(ingrediente.getId()));
        nomeIngrediente.setText(ingrediente.getNome());
        precoIngrediente.setText(String.valueOf(ingrediente.getPreco()));
        estoqueIngrediente.setText(String.valueOf(ingrediente.getEstoque()));

    }
    //endregion

    //Region Receita
    @FXML
    private void botaoCadastrarReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Salvar Receita");
        cadastrarReceita();
        recuperarReceita(false);
    }

    @FXML
    private void botaoRecuperarReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Buscar Receita");
        recuperarReceita(true);
    }

    @FXML
    private void botaoAlterarReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Alterar Receita");
        alterarReceita();
        recuperarReceita(false);
    }

    @FXML
    private void botaoDeletarReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Excluir Receita");
        deletarReceita();
        recuperarReceita(false);
    }

    public void cadastrarReceita() throws SQLException {

        dbReceita db = new dbReceita();
        Receita receita = new Receita();

        String idReceitaString = idReceita.getText();
        String nomeReceitaString = nomeReceita.getText();
        String modoPreparoReceitaString = modoPreparoReceita.getText();
        String tempoPreparoReceitaString = tempoPreparoReceita.getText();
        String rendimentoReceitaString = rendimentoReceita.getText();

        boolean camposPreenchidos = Utils.receitaCamposPreenchidos(nomeReceitaString, modoPreparoReceitaString, tempoPreparoReceitaString, rendimentoReceitaString);

        if (camposPreenchidos) {
            receita.setNome(nomeReceitaString);
            receita.setPreparo(modoPreparoReceitaString);
            receita.setTempo(tempoPreparoReceitaString);
            receita.setRendimento(Integer.parseInt(rendimentoReceitaString));

            db.adicionaReceita(receita);
            System.out.println("Receita Adicionada: " + receita.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void recuperarReceita(boolean isBusca) throws SQLException {
        dbReceita db = new dbReceita();
        List<Receita> listReceita = new ArrayList<>();
        if (isBusca) {
            listReceita = db.recuperaReceita(pesquisaIngrediente.getText());
        } else {
            listReceita = db.recuperaReceita("");
        }
        if (listReceita.size() > 0) {
            tabelaReceita.getItems().setAll(listReceita);
            idReceitaColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeReceitaColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            preparoReceitaColuna.setCellValueFactory(new PropertyValueFactory<>("preparo"));
            tempoReceitaColuna.setCellValueFactory(new PropertyValueFactory<>("tempo"));
            rendimentoReceitaColuna.setCellValueFactory(new PropertyValueFactory<>("rendimento"));
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado");
        }
    }

    public void alterarReceita() throws SQLException {
        dbReceita db = new dbReceita();
        Receita receita = new Receita();

        String idReceitaString = idReceita.getText();
        String nomeReceitaString = nomeReceita.getText();
        String modoPreparoReceitaString = modoPreparoReceita.getText();
        String tempoPreparoReceitaString = tempoPreparoReceita.getText();
        String rendimentoReceitaString = rendimentoReceita.getText();

        boolean camposPreenchidos = Utils.receitaCamposPreenchidos(nomeReceitaString, modoPreparoReceitaString, tempoPreparoReceitaString, rendimentoReceitaString);

        if (camposPreenchidos) {
            receita.setId(Integer.parseInt(idReceitaString));
            receita.setNome(nomeReceitaString);
            receita.setPreparo(modoPreparoReceitaString);
            receita.setTempo(tempoPreparoReceitaString);
            receita.setRendimento(Integer.parseInt(rendimentoReceitaString));

            db.alteraReceita(receita);
            System.out.println("Receita Alterada");
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void deletarReceita() throws SQLException {

        dbReceita db = new dbReceita();
        String idReceitaString = idReceita.getText();
        if (!idReceitaString.equals("")) {
            int idReceitaSelecionado = Integer.parseInt(idReceitaString);
            db.removeReceita(idReceitaSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma receita.");
        }
    }

    @FXML
    public void clickReceita(MouseEvent event) throws IOException {
        selecionarReceita();
    }

    private void selecionarReceita() {
        Receita receita = (Receita) tabelaReceita.getSelectionModel().getSelectedItem();

        //ingredienteSelecionado = ingrediente;
        idReceita.setText(String.valueOf(receita.getId()));
        nomeReceita.setText(receita.getNome());
        modoPreparoReceita.setText(String.valueOf(receita.getPreparo()));
        tempoPreparoReceita.setText(String.valueOf(receita.getTempo()));
        rendimentoReceita.setText(String.valueOf(receita.getRendimento()));
        //categoriaReceita.setValue(null);
    }

    //endregion
    public void configurarComboBoxes() throws SQLException {
        dbIngrediente dbIngrediente = new dbIngrediente();
        dbTipica dbTipica = new dbTipica();
        dbCategoria dbCategoria = new dbCategoria();
        dbMedida dbMedida = new dbMedida();

        categoriaReceita.setItems(FXCollections.observableArrayList(dbCategoria.recuperarCategorias()));
        tipicaReceita.setItems(FXCollections.observableArrayList(dbTipica.recuperarTipicas()));
        ingredienteReceita.setItems(FXCollections.observableArrayList(dbIngrediente.recuperaIngredientes("")));
        medidaIngrediente.setItems(FXCollections.observableArrayList(dbMedida.recuperarMedidas("")));
        //medidaIngredienteReceita.setItems(FXCollections.observableArrayList(db));
    }
}
