package Controller;

import Model.Receita;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class FiltragemController {

    @FXML
    private JFXComboBox<?> cbmTipica;

    @FXML
    private JFXComboBox<?> cbmAddIngrediente;

    @FXML
    private JFXComboBox<?> cmbDelIngrediente;

    @FXML
    private JFXListView<Receita> listRec;

    @FXML
    private Label nomeReceita;

    @FXML
    private ListView<?> listIngrRec;

    @FXML
    private ListView<?> listRecPrep;

    @FXML
    private JFXButton btnEmail;

    @FXML
    private JFXButton btnWhats;

    @FXML
    private JFXButton btnEmail1;
    
    @FXML
    public void initialize() throws SQLException {
        dbReceita db = new dbReceita();
        System.out.println(FXCollections.observableArrayList(db.recuperaReceitaByCategoria(utils.Utils.primeiraSelecao)));
        listRec.setItems(FXCollections.observableArrayList(db.recuperaReceitaByCategoria(utils.Utils.primeiraSelecao)));
    }

}
