/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import Model.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Categoria;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class dbCategoria {

    //Conexão com o banco

    private Connection conexao;

    public dbCategoria() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    //SELECT - Retorna uma lista com o resultado da consulta
    public List<Categoria> recuperarCategorias() throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM categoria";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Categoria> lista = new ArrayList<>();

        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while (resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Categoria categoria = new Categoria();

            categoria.setId(Integer.parseInt(resultSet.getString("idCategoria")));
            categoria.setNome(resultSet.getString("nomeCategoria"));

            // Adiciona o registro na lista
            lista.add(categoria);
        }

        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();

        // Retorna a lista de registros, gerados pela consulta
        return lista;
    }
}
