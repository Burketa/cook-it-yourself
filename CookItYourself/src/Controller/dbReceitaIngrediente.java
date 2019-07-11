package Controller;

import Model.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.ReceitaIngrediente;
import utils.Utils;

public class dbReceitaIngrediente {

    //Conexão com o banco
    private Connection conexao;

    public dbReceitaIngrediente() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    // CREATE - Adiciona um registro
    public void adicionaIngredienteReceita(ReceitaIngrediente receitaIngrediente) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO receitaingrediente (idReceita, idIngrediente, quantidade)"
                + "VALUES(?, ?, ?)";

        PreparedStatement preparedStatement;

        // preparedStatement recebe o comando SQL
        preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setInt(1, receitaIngrediente.getIdReceita());
        preparedStatement.setInt(2, receitaIngrediente.getIdIngrediente());
        preparedStatement.setFloat(3, receitaIngrediente.getQuantidade());

        System.out.println("Adicionado: " + receitaIngrediente);

        // O preparedStatement executa o comando SQL no BD, e fecha a conexão
        preparedStatement.execute();
        preparedStatement.close();
    }

    //SELECT - Retorna uma lista com o resultado da consulta
    public List<ReceitaIngrediente> getReceitaIngredientes(int idReceita) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM receitaingrediente WHERE idReceita = ?";
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);
        preparedStatement.setInt(1, idReceita);

        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ReceitaIngrediente> lista = new ArrayList<>();

        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while (resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

            // "receitaIngrediente" -> ReceitaIngrediente nova - .setIdReceita recebe o id...
            receitaIngrediente.setIdReceita(resultSet.getInt("idReceita"));
            receitaIngrediente.setIdIngrediente(resultSet.getInt("idIngrediente"));
            receitaIngrediente.setQuantidade(resultSet.getFloat("quantidade"));

            //
            dbIngrediente dbIngrediente = new dbIngrediente();
            Ingrediente ingrediente = dbIngrediente.recuperaIngredienteById(receitaIngrediente.getIdIngrediente());
            
            receitaIngrediente.setIngrediente(ingrediente);
            //

            // Adiciona o registro na lista
            lista.add(receitaIngrediente);
        }

        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();

        // Retorna a lista de registros, gerados pela consulta
        return lista;
    }

    // UPDATE - Atualiza registros
    public void alteraReceitaIngrediente(ReceitaIngrediente ri) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE ingrediente set nomeIngrediente = ?, precoIngrediente = ?, estoqueIngrediente = ?"
                + "WHERE idIngrediente = ?";

        // stmt recebe o comando SQL
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o stmt, substituindo os "?"
        stmt.setFloat(1, ri.getQuantidade());

        // O stmt executa o comando SQL no BD, e fecha a conexão
        stmt.execute();
        stmt.close();
    }

    // DELETE - Apaga registros
    public void removeIngredienteReceita(int receitaId, int ingredienteId) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM receitaingrediente WHERE idIngrediente = ? AND idReceita = ?";

        // stmt recebe o comando SQL
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        preparedStatement.setInt(1, ingredienteId);
        preparedStatement.setInt(2, receitaId);

        // Executa o codigo SQL, e fecha
        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<Ingrediente> recuperaIngredientesById(int id) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM ingrediente WHERE idIngrediente = ?";
        PreparedStatement preparedStatement = null;
        preparedStatement = this.conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);

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
}
