/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import Model.Tipica;
import Model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import Model.Tipica;

public class dbTipica {
    //Conexão com o banco
    private Connection conexao;
    
    public dbTipica() throws SQLException {       
        this.conexao = Conexao.getConexao();
    }
    
    //SELECT - Retorna uma lista com o resultado da consulta
    public List<Tipica> recuperarTipicas() throws SQLException{
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM tipica";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);
        
        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Tipica> lista = new ArrayList<>();
        
        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while(resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Tipica tipica = new Tipica();
            
            tipica.setId(Integer.parseInt(resultSet.getString("idTipica")));
            tipica.setNome(resultSet.getString("nomeTipica"));
            
            // Adiciona o registro na lista
            lista.add(tipica);            
        }
        
        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();
        
        // Retorna a lista de registros, gerados pela consulta
        return lista;          
    }
}
