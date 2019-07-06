/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import Model.Medida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbMedida {
    //Conexão com o banco
    private Connection conexao;
    
    public dbMedida() throws SQLException {       
        this.conexao = Conexao.getConexao();
    }
    
    //SELECT - Retorna uma lista com o resultado da consulta
    public List<Medida> recuperarMedidas(String nomeReceita) throws SQLException{
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM medida";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);
        
        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Medida> lista = new ArrayList<>();
        
        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while(resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Medida medida = new Medida();
            
            // "medida" -> Medida nova - .setNome recebe o campo do banco de String "nome"...
            medida.setId(Integer.valueOf(resultSet.getString("idMedida")));
            medida.setTipo(resultSet.getString("tipoMedida"));
            
            // Adiciona o registro na lista
            lista.add(medida);            
        }
        
        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();
        
        // Retorna a lista de registros, gerados pela consulta
        return lista;          
    }
}
