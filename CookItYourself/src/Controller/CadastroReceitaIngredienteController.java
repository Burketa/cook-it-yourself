package Controller;

import Model.Categoria;
import Model.Ingrediente;
import Model.Medida;
import Model.Receita;
import Model.ReceitaIngrediente;
import Model.Tipica;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    // <editor-fold desc="FXML Ingrediente">
    @FXML
    private TextField ingredientePesquisaTextField;
    @FXML
    private TextField ingredienteIdTextField;
    @FXML
    private TextField ingredienteNomeTextField;
    @FXML
    private TextField ingredientePrecoTextField;
    @FXML
    private TextField ingredienteEstoqueTextField;
    @FXML
    private ComboBox<Medida> ingredienteMedidaComboBox;
    @FXML
    private TableView<Ingrediente> ingredienteTableView;
    @FXML
    private TableColumn<Ingrediente, Integer> ingredienteIdColuna;
    @FXML
    private TableColumn<Ingrediente, String> ingredienteNomeColuna;
    @FXML
    private TableColumn<Ingrediente, Float> ingredientePrecoColuna;
    @FXML
    private TableColumn<Ingrediente, Integer> ingredienteEstoqueColuna;
    @FXML
    private TableColumn<Ingrediente, String> ingredienteMedidaColuna;
    // </editor-fold>

    // <editor-fold desc="FXML Receita">
    @FXML
    private TextField receitaPesquisaTextField;
    @FXML
    private TextField receitaIdTextField;
    @FXML
    private TextField receitaNomeTextField;
    @FXML
    private TextArea receitaPreparoTextField;
    @FXML
    private TextField receitaTempoTextField;
    @FXML
    private TextField receitaRendimentoTextField;
    @FXML
    private ComboBox<Categoria> receitaCategoriaComboBox;
    @FXML
    private ComboBox<Tipica> receitaTipicaComboBox;
    @FXML
    private ComboBox<Ingrediente> receitaIngredienteComboBox;
    @FXML
    private TextField receitaQuantidadeTextField;
    @FXML
    private TableView<Receita> receitaTableView;
    @FXML
    private TableColumn<Receita, Integer> receitaIdColuna;
    @FXML
    private TableColumn<Receita, String> receitaNomeColuna;
    @FXML
    private TableColumn<Receita, String> receitaCategoriaColuna;
    @FXML
    private TableColumn<Receita, String> receitaTipicaColuna;
    @FXML
    private TableColumn<Receita, String> receitaPreparoColuna;
    @FXML
    private TableColumn<Receita, String> receitaTempoColuna;
    @FXML
    private TableColumn<Receita, Integer> receitaRendimentoColuna;
    @FXML
    private ListView<ReceitaIngrediente> receitaIngredientesListView;
    // </editor-fold>

    // <editor-fold desc="FXML ReceitaIngrediente">
    @FXML
    private Button buttonAdicionarIngredienteReceita;
    // </editor-fold>

    @FXML
    public void initialize() throws SQLException {
        recuperarIngrediente(false);
        recuperarReceita(false);
        configurarComboBoxes();
    }

    @FXML
    private void botaoFiltro(ActionEvent event) throws IOException {
        System.out.println("Click Iniciar Filtro");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Primeiro Filtro");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    // <editor-fold desc="Ingrediente">
    @FXML
    private void botaoCadastrarIngrediente(ActionEvent event) throws SQLException {
        System.out.println("Click Novo Ingrediente");
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
        System.out.println("click Deletar Ingrediente");
        deletarIngrediente();
        recuperarIngrediente(false);
    }

    public void cadastrarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();

        String nomeIngrediente = ingredienteNomeTextField.getText();
        String precoIngrediente = ingredientePrecoTextField.getText();
        String estoqueIngrediente = ingredienteEstoqueTextField.getText();
        Medida medidaIngrediente = ingredienteMedidaComboBox.getSelectionModel().getSelectedItem();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngrediente, precoIngrediente, estoqueIngrediente, medidaIngrediente);

        if (camposPreenchidos) {
            Ingrediente ingrediente = configurarIngrediente("", nomeIngrediente, precoIngrediente, estoqueIngrediente, medidaIngrediente);
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
            listIngrediente = db.recuperaIngredientes(ingredientePesquisaTextField.getText());
        } else {
            listIngrediente = db.recuperaIngredientes("");
        }
        if (listIngrediente.size() > 0) {
            ingredienteTableView.getItems().setAll(listIngrediente);

            ingredienteIdColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            ingredienteNomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ingredientePrecoColuna.setCellValueFactory(new PropertyValueFactory<>("preco"));
            ingredienteEstoqueColuna.setCellValueFactory(new PropertyValueFactory<>("estoque"));
            ingredienteMedidaColuna.setCellValueFactory(new PropertyValueFactory<>("medidaString"));
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado");
        }
    }

    public void alterarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();

        String idIngrediente = ingredienteIdTextField.getText();
        String nomeIngrediente = ingredienteNomeTextField.getText();
        String precoIngrediente = ingredientePrecoTextField.getText();
        String estoqueIngrediente = ingredienteEstoqueTextField.getText();
        Medida medidaIngrediente = ingredienteMedidaComboBox.getSelectionModel().getSelectedItem();

        boolean camposPreenchidos = Utils.ingredienteCamposPreenchidos(nomeIngrediente, precoIngrediente, estoqueIngrediente, medidaIngrediente);

        if (camposPreenchidos) {
            Ingrediente ingrediente = configurarIngrediente(idIngrediente, nomeIngrediente, precoIngrediente, estoqueIngrediente, medidaIngrediente);
            db.alteraIngrediente(ingrediente);
            System.out.println("Ingrediente Alterado: " + ingrediente);
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void deletarIngrediente() throws SQLException {
        dbIngrediente db = new dbIngrediente();

        String idIngrediente = ingredienteIdTextField.getText();

        if (!idIngrediente.equals("")) {
            int idIngredienteSelecionado = Integer.parseInt(idIngrediente);
            db.removeIngrediente(idIngredienteSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ingrediente.");
        }
    }

    private Ingrediente configurarIngrediente(String id, String nome, String preco, String estoque, Medida medida) {
        Ingrediente ingrediente = new Ingrediente();

        if (!id.equals("")) {
            ingrediente.setId(Integer.parseInt(id));
        }

        ingrediente.setNome(nome);

        if (Utils.isFloat(preco)) {
            ingrediente.setPreco(Float.parseFloat(preco));
        } else {
            JOptionPane.showMessageDialog(null, "Preço não é valido (numero real ou inteiro).");
        }

        if (Utils.isInteger(estoque)) {
            ingrediente.setEstoque(Integer.parseInt(estoque));
        } else {
            JOptionPane.showMessageDialog(null, "Estoque não é numero inteiro.");
        }

        if (ingredienteMedidaComboBox != null) {
            ingrediente.setMedida(medida);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma medida");
        }

        return ingrediente;
    }

    @FXML
    public void clickIngrediente(MouseEvent event) throws IOException {
        selecionarIngrediente();
    }

    private void selecionarIngrediente() {
        Ingrediente ingrediente = ingredienteTableView.getSelectionModel().getSelectedItem();

        ingredienteIdTextField.setText(String.valueOf(ingrediente.getId()));
        ingredienteNomeTextField.setText(ingrediente.getNome());
        ingredientePrecoTextField.setText(String.valueOf(ingrediente.getPreco()));
        ingredienteEstoqueTextField.setText(String.valueOf(ingrediente.getEstoque()));
        ingredienteMedidaComboBox.getSelectionModel().select(ingrediente.getMedidaId() - 1);
    }
    // </editor-fold>

    // <editor-fold desc="Receita">
    @FXML
    private void botaoCadastrarReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Nova Receita");
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

    //TODO:Melhorar
    @FXML
    private void botaoAdicionarIngredienteReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Adicionar Ingrediente Receita");
        //deletarReceita();
        //recuperarReceita(false);
        //if (ingredienteReceita.getSelectionModel().getSelectedItem() != null) {
        adicionarIngredienteReceita();
        if ((Receita) receitaTableView.getSelectionModel().getSelectedItem() != null) {
            recuperarIngredienteReceita(receitaTableView.getSelectionModel().getSelectedItem());
        }
        //} else {
        //    JOptionPane.showMessageDialog(null, "Selecione um ingrediente.");
        //}
    }

    @FXML
    private void botaoDeletarIngredienteReceita(ActionEvent event) throws SQLException {
        System.out.println("Click Deletar Ingrediente Receita");
        ReceitaIngrediente receitaIngrediente = receitaIngredientesListView.getSelectionModel().getSelectedItem();

        if (receitaIngrediente != null) {
            deletarIngredienteReceita();
            recuperarIngredienteReceita(receitaTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void cadastrarReceita() throws SQLException {
        dbReceita db = new dbReceita();

        String nome = receitaNomeTextField.getText();
        String modo = receitaPreparoTextField.getText();
        String tempo = receitaTempoTextField.getText();
        String rendimento = receitaRendimentoTextField.getText();
        Categoria categoria = receitaCategoriaComboBox.getSelectionModel().getSelectedItem();
        Tipica tipica = receitaTipicaComboBox.getSelectionModel().getSelectedItem();

        boolean camposPreenchidos = Utils.receitaCamposPreenchidos(nome, modo, tempo,
                rendimento, categoria, tipica);

        if (camposPreenchidos) {
            Receita receita = configurarReceita("", nome, modo, tempo,
                    rendimento, categoria, tipica);
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
            listReceita = db.recuperaReceita(receitaPesquisaTextField.getText());
        } else {
            listReceita = db.recuperaReceita("");
        }
        
        if (listReceita.size() > 0) {
            receitaTableView.getItems().setAll(listReceita);
            receitaIdColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
            receitaNomeColuna.setCellValueFactory(new PropertyValueFactory<>("nome"));
            receitaCategoriaColuna.setCellValueFactory(new PropertyValueFactory<>("categoriaString"));
            receitaTipicaColuna.setCellValueFactory(new PropertyValueFactory<>("tipicaString"));
            receitaPreparoColuna.setCellValueFactory(new PropertyValueFactory<>("preparo"));
            receitaTempoColuna.setCellValueFactory(new PropertyValueFactory<>("tempo"));
            receitaRendimentoColuna.setCellValueFactory(new PropertyValueFactory<>("rendimento"));
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado");
        }
    }

    public void alterarReceita() throws SQLException {
        dbReceita db = new dbReceita();

        String id = receitaIdTextField.getText();
        String nome = receitaNomeTextField.getText();
        String preparo = receitaPreparoTextField.getText();
        String tempo = receitaTempoTextField.getText();
        String rendimento = receitaRendimentoTextField.getText();
        Categoria categoria = receitaCategoriaComboBox.getSelectionModel().getSelectedItem();
        Tipica tipica = receitaTipicaComboBox.getSelectionModel().getSelectedItem();

        boolean camposPreenchidos = Utils.receitaCamposPreenchidos(nome, preparo,
                tempo, rendimento, categoria, tipica);

        if (camposPreenchidos) {
            Receita receita = configurarReceita("", nome, preparo, tempo,
                    rendimento, categoria, tipica);
            db.alteraReceita(receita);
            System.out.println("Receita Alterada: " + receita);
        } else {
            JOptionPane.showMessageDialog(null, "Campos não preenchidos.");
        }
    }

    public void deletarReceita() throws SQLException {

        dbReceita db = new dbReceita();
        String id = receitaIdTextField.getText();
        if (!id.equals("")) {
            int idReceitaSelecionado = Integer.parseInt(id);
            db.removeReceita(idReceitaSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma receita.");
        }
    }

    public void adicionarIngredienteReceita() throws SQLException {
        dbReceitaIngrediente db = new dbReceitaIngrediente();

        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();
        Ingrediente ingredienteObj = receitaIngredienteComboBox.getSelectionModel().getSelectedItem();
        Receita receitaObj = receitaTableView.getSelectionModel().getSelectedItem();

        receitaIngrediente.setIdIngrediente(ingredienteObj.getId());
        receitaIngrediente.setIdReceita(receitaObj.getId());
        receitaIngrediente.setIngrediente(ingredienteObj);
        receitaIngrediente.setReceita(receitaObj);
        receitaIngrediente.setQuantidade(Float.parseFloat(receitaQuantidadeTextField.getText()));

        db.adicionaIngredienteReceita(receitaIngrediente);
    }

    public void recuperarIngredienteReceita(Receita receita) throws SQLException {
        dbReceitaIngrediente db = new dbReceitaIngrediente();

        List<ReceitaIngrediente> ingredienteList = db.getReceitaIngredientes(receita.getId());

        receitaIngredientesListView.setItems(FXCollections.observableArrayList(ingredienteList));

    }

    public void deletarIngredienteReceita() throws SQLException {
        dbReceitaIngrediente db = new dbReceitaIngrediente();

        int receitaId = receitaTableView.getSelectionModel().getSelectedItem().getId();
        int ingredienteId = receitaIngredientesListView.getSelectionModel().getSelectedItem().getIdIngrediente();

        db.removeIngredienteReceita(receitaId, ingredienteId);
    }

    //TODO: Validacao dos campos
    public Receita configurarReceita(String id, String nome, String preparo, String tempo, String rendimento, Categoria categoria, Tipica tipica) {
        Receita receita = new Receita();

        if (!id.equals("")) {
            receita.setId(Integer.parseInt(id));
        }
        receita.setNome(nome);
        receita.setPreparo(preparo);
        receita.setTempo(tempo);
        receita.setRendimento(Integer.parseInt(rendimento));
        receita.setCategoria(categoria);
        receita.setTipica(tipica);

        return receita;
    }

    @FXML
    public void clickReceita(MouseEvent event) throws IOException, SQLException {
        selecionarReceita();
    }

    private void selecionarReceita() throws SQLException {
        Receita receita = receitaTableView.getSelectionModel().getSelectedItem();

        receitaIdTextField.setText(String.valueOf(receita.getId()));
        receitaNomeTextField.setText(receita.getNome());
        receitaPreparoTextField.setText(String.valueOf(receita.getPreparo()));
        receitaTempoTextField.setText(String.valueOf(receita.getTempo()));
        receitaRendimentoTextField.setText(String.valueOf(receita.getRendimento()));
        receitaCategoriaComboBox.getSelectionModel().select(receita.getCategoriaId() - 1);
        receitaTipicaComboBox.getSelectionModel().select(receita.getTipicaId() - 1);

        recuperarIngredienteReceita(receita);
        //listaIngredientesReceita.getSelectionModel().select(0);
    }
    // </editor-fold>

    // <editor-fold desc="ReceitaIngrediente">
    @FXML
    public void clickReceitaIngrediente(MouseEvent event) throws IOException, SQLException {
        System.out.println("Click ReceitaIngrediente");
        //selecionarReceitaIngrediente();
    }

    //TODO: Arrumar
    private void selecionarReceitaIngrediente() throws SQLException {
        ReceitaIngrediente receitaIngrediente = receitaIngredientesListView.getSelectionModel().getSelectedItem();

        System.out.println("\nId Ingrediente: " + (receitaIngrediente.getIdIngrediente()));
        System.out.println("\nSelected on list Index: " + receitaIngredientesListView.getSelectionModel().getSelectedIndex());

        receitaQuantidadeTextField.setText(String.valueOf(receitaIngrediente.getQuantidade()));
        receitaIngredienteComboBox.getSelectionModel().select(receitaIngrediente.getIdIngrediente());
    }

    // </editor-fold>
    // <editor-fold desc="Metodos">
    public void configurarComboBoxes() throws SQLException {
        dbIngrediente dbIngrediente = new dbIngrediente();
        dbTipica dbTipica = new dbTipica();
        dbCategoria dbCategoria = new dbCategoria();
        dbMedida dbMedida = new dbMedida();

        receitaCategoriaComboBox.setItems(FXCollections.observableArrayList(dbCategoria.recuperarCategorias()));
        receitaTipicaComboBox.setItems(FXCollections.observableArrayList(dbTipica.recuperarTipicas()));
        receitaIngredienteComboBox.setItems(FXCollections.observableArrayList(dbIngrediente.recuperaIngredientes("")));
        ingredienteMedidaComboBox.setItems(FXCollections.observableArrayList(dbMedida.recuperarMedidas("")));
    }
    // </editor-fold>
}
