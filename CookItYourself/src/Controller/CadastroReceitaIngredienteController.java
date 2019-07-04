/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ingrediente;
import Model.Receita;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nick_
 */
public class CadastroReceitaIngredienteController implements Initializable {

    @FXML
    private TextField txtBuscarReceita;
    @FXML
    private TableView<?> tblExibeReceita;
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
    private ListView<?> lstListaIngrediente;
    @FXML
    private ComboBox<?> cmbMedida;
    @FXML
    private Button btnAdicionarIngrediente;
    @FXML
    private Button btnExcluirIngrediente;
    @FXML
    private ComboBox<?> cmbTipica;
    @FXML
    private ComboBox<?> cmbCategoria;
    @FXML
    private Button btnCadastraNovaReceita;
    @FXML
    private Button btnSalvarReceita;
    @FXML
    private Button btnAlterarReceita;
    @FXML
    private Button btnExcluirReceita;
    @FXML
    private TextField txtBuscarIngrediente;
    @FXML
    private Button btnBuscarIngrediente;
    @FXML
    private TextField txtNomeIngrediente;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtEstoque;
    @FXML
    private Button btnCadastrarNovoIngrediente;
    @FXML
    private Button btnAlterarIngrediente;
    @FXML
    private Button btnSalvarIngrediente;

    
    private List<Receita> listReceita;
    private ObservableList<Receita> observableListReceita;
    
    private List<Ingrediente> listIngrediente;
    private ObservableList<Ingrediente> observableListIngrediente;
    
    //Atributos para manipulação de Banco de Dados
    //Conexão com o banco
    private Connection conexao;
  
    this.conexao = Conexao.getConexao();
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
