/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Ingrediente;
import utils.Utils;

public class dbIngrediente {

    //Conexão com o banco
    private Connection conexao;

    public dbIngrediente() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    // CREATE - Adiciona um registro
    public void adicionaIngrediente(Ingrediente ingrediente) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO ingrediente (nomeIngrediente, precoIngrediente, estoqueIngrediente, medidaIngrediente)"
                + "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement;

        // preparedStatement recebe o comando SQL
        preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setString(1, ingrediente.getNome());
        preparedStatement.setFloat(2, ingrediente.getPreco());
        preparedStatement.setInt(3, ingrediente.getEstoque());
        preparedStatement.setString(4, ingrediente.getMedidaString());

        // O preparedStatement executa o comando SQL no BD, e fecha a conexão
        preparedStatement.execute();
        preparedStatement.close();

    }

    // SELECT - Retorna uma lista com o resultado da consulta
    public List<Ingrediente> recuperaIngredientes(String string) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "";
        PreparedStatement preparedStatement = null;
        if (string.equals("")) {
            sql = "SELECT * FROM ingrediente";
            preparedStatement = this.conexao.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM ingrediente WHERE nomeIngrediente = ?";
            preparedStatement = this.conexao.prepareStatement(sql);
            preparedStatement.setString(1, string);
        }
        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Ingrediente> lista = new ArrayList<>();

        // Enquanto existirem ingredientes, pega os valores do reultSet e vai adicionando na lista
        while (resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Ingrediente ingrediente = new Ingrediente();

            // "ingrediente" -> Ingrediente novo - .setNome recebe o campo do banco de String "nome"...
            ingrediente.setId(Integer.valueOf(resultSet.getString("idIngrediente")));
            ingrediente.setNome(resultSet.getString("nomeIngrediente"));
            ingrediente.setPreco(resultSet.getFloat("precoIngrediente"));
            ingrediente.setEstoque(resultSet.getInt("estoqueIngrediente"));
            ingrediente.setMedidaId(Utils.medidaStringToId(resultSet.getString("medidaIngrediente")));
            ingrediente.setMedidaString(resultSet.getString("medidaIngrediente"));

            // Adiciona o ingrediente na lista
            lista.add(ingrediente);
        }

        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();

        // Retorna a lista de ingrediente, gerados pela consulta
        return lista;
    }

    // UPDATE - Atualiza registros
    public void alteraIngrediente(Ingrediente ingrediente) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE ingrediente set nomeIngrediente = ?, precoIngrediente = ?, estoqueIngrediente = ?, medidaIngrediente = ? WHERE idIngrediente = ?";
        // stmt recebe o comando SQL
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o stmt, substituindo os "?"
        preparedStatement.setString(1, ingrediente.getNome());
        preparedStatement.setFloat(2, ingrediente.getPreco());
        preparedStatement.setInt(3, ingrediente.getEstoque());
        preparedStatement.setString(4, ingrediente.getMedida().getTipo());
        preparedStatement.setInt(5, ingrediente.getId());

        // O stmt executa o comando SQL no BD, e fecha a conexão
        preparedStatement.execute();
        preparedStatement.close();
    }

    // DELETE - Apaga registros
    public void removeIngrediente(int ingredienteId) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM Ingrediente WHERE idIngrediente = ?";

        // preparedStatement recebe o comando SQL
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Seta o valor do ID p/ a condição de verificação SQL, dentro do preparedStatement
        preparedStatement.setInt(1, ingredienteId);

        // Executa o codigo SQL, e fecha
        preparedStatement.execute();
        preparedStatement.close();

    }
}
