package Controller;

import Model.Ingrediente;
import Model.Receita;
import Model.ReceitaIngrediente;
import Model.Tipica;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
//import utils.MandarWhatsapp;

public class FiltragemController {

    private List<Receita> listaReceitasInicial;

    private Receita receitaSelecionada;

    @FXML
    private JFXComboBox<Tipica> tipicaComboBox;

    @FXML
    private JFXComboBox<Ingrediente> incluirIngredienteComboBox;

    @FXML
    private JFXComboBox<Ingrediente> excluirIngredienteComboBox;

    @FXML
    private JFXListView<Receita> listaReceitas;

    @FXML
    private Label nomeReceita;

    @FXML
    private ListView<ReceitaIngrediente> listaIngredientes;

    @FXML
    private TextArea modoPreparo;

    @FXML
    private JFXButton buttonEmail;

    @FXML
    private JFXButton buttonWhats;

    @FXML
    public void initialize() throws SQLException {
        dbReceita db = new dbReceita();
        System.out.println(FXCollections.observableArrayList(db.recuperaReceitaByCategoria(utils.Utils.primeiraSelecao)));

        listaReceitasInicial = db.recuperaReceitaByCategoria(utils.Utils.primeiraSelecao);
        listaReceitas.setItems(FXCollections.observableArrayList(listaReceitasInicial));

        configurarComboBoxes();
    }

    @FXML
    public void clickReceita(MouseEvent event) throws IOException, SQLException {
        selecionarReceita();
    }

    private void selecionarReceita() throws SQLException {
        dbReceitaIngrediente db = new dbReceitaIngrediente();

        Receita receita = listaReceitas.getSelectionModel().getSelectedItem();
        List<ReceitaIngrediente> ingredienteList = db.getReceitaIngredientes(receita.getId());

        nomeReceita.setText(receita.getNome());
        listaIngredientes.setItems(FXCollections.observableArrayList(ingredienteList));
        modoPreparo.setText(receita.getPreparo());

        receitaSelecionada = receita;
    }

    public void configurarComboBoxes() throws SQLException {
        dbIngrediente dbIngrediente = new dbIngrediente();
        dbTipica dbTipica = new dbTipica();

        ObservableList<Tipica> listTipica = FXCollections.observableArrayList(dbTipica.recuperarTipicas());
        listTipica.add(0, null);
        tipicaComboBox.setItems(FXCollections.observableArrayList(listTipica));

        ObservableList<Ingrediente> listIngrediente = FXCollections.observableArrayList(dbIngrediente.recuperaIngredientes(""));
        listIngrediente.add(0, null);
        incluirIngredienteComboBox.setItems(listIngrediente);
        excluirIngredienteComboBox.setItems(listIngrediente);
    }

    public void enviarWhatsapp(ActionEvent event) {
        //MandarWhatsapp.enviar(receitaSelecionada.getPreparo());
    }

    public void botaoFitlrar(ActionEvent event) throws SQLException {
        selecionarTipica();
        selecionarIngredienteAdicionado();
    }

    //TODO:Acabar
    public void selecionarTipica() {
        List<Receita> listaFiltroTipica = filtrarTipica(listaReceitasInicial);

        listaReceitas.setItems(FXCollections.observableArrayList(listaFiltroTipica));
    }

    public List<Receita> filtrarTipica(List<Receita> list) {
        List<Receita> listaFiltroTipica = new ArrayList<>();

        if (tipicaComboBox.getSelectionModel().getSelectedItem() != null) {
            for (Receita receita : list) {
                if (receita.getTipicaId() == tipicaComboBox.getSelectionModel().getSelectedItem().getId()) {
                    listaFiltroTipica.add(receita);
                }
            }
        } else {
            listaFiltroTipica = new ArrayList<>(listaReceitasInicial);
        }

        return listaFiltroTipica;
    }

    public void selecionarIngredienteAdicionado() throws SQLException {
        List<Receita> listaFiltroIngredienteSelecionado = filtrarIngredienteAdicionado(listaReceitasInicial);

        listaReceitas.setItems(FXCollections.observableArrayList(listaFiltroIngredienteSelecionado));
    }

    public List<Receita> filtrarIngredienteAdicionado(List<Receita> list) throws SQLException {
        List<Receita> listaFiltroIngredienteSelecionado = new ArrayList<>();

        dbReceitaIngrediente dbReceitaIngrediente = new dbReceitaIngrediente();

        if (incluirIngredienteComboBox.getSelectionModel().getSelectedItem() != null) {
            for (Receita receita : list) {
                List<ReceitaIngrediente> listReceitaIngrediente = dbReceitaIngrediente.getReceitaIngredientes(receita.getId());
                for (ReceitaIngrediente receitaIngrediente : listReceitaIngrediente) {
                    if (receitaIngrediente.getIdIngrediente() == incluirIngredienteComboBox.getSelectionModel().getSelectedItem().getId()) {
                        listaFiltroIngredienteSelecionado.add(receita);
                    }
                }
            }
        } else {
            listaFiltroIngredienteSelecionado = new ArrayList<>(listaReceitasInicial);
        }

        return listaFiltroIngredienteSelecionado;
    }
    
    public void selecionarIngredienteRestringido() throws SQLException {
        List<Receita> listaFiltroIngredienteSelecionado = filtrarIngredienteRestringido(listaReceitasInicial);

        listaReceitas.setItems(FXCollections.observableArrayList(listaFiltroIngredienteSelecionado));
    }

    public List<Receita> filtrarIngredienteRestringido(List<Receita> list) throws SQLException {
        List<Receita> listaFiltroIngredienteSelecionado = new ArrayList<>(listaReceitasInicial);

        dbReceitaIngrediente dbReceitaIngrediente = new dbReceitaIngrediente();

        if (excluirIngredienteComboBox.getSelectionModel().getSelectedItem() != null) {
            for (Receita receita : list) {
                List<ReceitaIngrediente> listReceitaIngrediente = dbReceitaIngrediente.getReceitaIngredientes(receita.getId());
                for (ReceitaIngrediente receitaIngrediente : listReceitaIngrediente) {
                    if (receitaIngrediente.getIdIngrediente() == excluirIngredienteComboBox.getSelectionModel().getSelectedItem().getId()) {
                        listaFiltroIngredienteSelecionado.remove(receita);
                        break;
                    }
                }
            }
        } else {
            listaFiltroIngredienteSelecionado = new ArrayList<>(listaReceitasInicial);
        }

        return listaFiltroIngredienteSelecionado;
    }
}
