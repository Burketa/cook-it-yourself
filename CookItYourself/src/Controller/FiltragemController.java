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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class FiltragemController {

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
        listaReceitas.setItems(FXCollections.observableArrayList(db.recuperaReceitaByCategoria(utils.Utils.primeiraSelecao)));
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
    }

    public void configurarComboBoxes() throws SQLException {
        dbIngrediente dbIngrediente = new dbIngrediente();
        dbTipica dbTipica = new dbTipica();
        
        ObservableList <Tipica> listTipica = FXCollections.observableArrayList(dbTipica.recuperarTipicas());
        listTipica.add(0, null);
        tipicaComboBox.setItems(FXCollections.observableArrayList(listTipica));
        
        ObservableList <Ingrediente> listIngrediente = FXCollections.observableArrayList(dbIngrediente.recuperaIngredientes(""));
        listIngrediente.add(0, null);
        incluirIngredienteComboBox.setItems(listIngrediente);
        excluirIngredienteComboBox.setItems(listIngrediente);
    }
}
