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
import Model.Receita;

public class dbReceita {

    //Conexão com o banco
    private Connection conexao;

    public dbReceita() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    // INSERT - Adiciona uma receita no banco
    public void adicionaReceita(Receita receita) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO receita (nomeReceita, preparoReceita, tempoReceita, rendimentoReceita, idCategoria, idTipica)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        // preparedStatement recebe o comando SQL
        preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setString(1, receita.getNomeReceita());
        preparedStatement.setString(2, receita.getPreparoReceita());
        preparedStatement.setString(3, receita.getTempoReceita());
        preparedStatement.setInt(4, receita.getRendimentoReceita());
        //preparedStatement.setInt(5, receita.getIdCategoria());
        //preparedStatement.setInt(6, receita.getIdTipica());
        //TODO: Colocar certo as coisas
        preparedStatement.setInt(5, 1);
        preparedStatement.setInt(6, 1);

        // O preparedStatement executa o comando SQL no BD, e fecha a conexão
        preparedStatement.execute();
        preparedStatement.close();

    }

    //SELECT - Retorna uma lista com o resultado da consulta
    public List<Receita> recuperaReceita(String string) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "";
        PreparedStatement preparedStatement = null;
        if (string.equals("")) {
            sql = "SELECT * FROM receita";
            preparedStatement = this.conexao.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM receita WHERE nomeReceita = ?";
            preparedStatement = this.conexao.prepareStatement(sql);
            preparedStatement.setString(1, string);
        }

        // Recebe o resultado da consulta SQL
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Receita> lista = new ArrayList<>();

        // Enquanto existir receitas, pega os valores do ReultSet e vai adicionando na lista
        while (resultSet.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de receitas p/ a lista
            Receita receita = new Receita();

            // "receita" -> Receita nova - .setNome recebe o campo do banco de String "nome"...
            receita.setIdReceita(Integer.valueOf(resultSet.getString("idReceita")));
            receita.setNomeReceita(resultSet.getString("nomeReceita"));
            receita.setPreparoReceita(resultSet.getString("preparoReceita"));
            receita.setIdCategoria(Integer.parseInt(resultSet.getString("idCategoria")));
            receita.setIdTipica(Integer.parseInt(resultSet.getString("idTipica")));
            receita.setRendimentoReceita(Integer.parseInt(resultSet.getString("rendimentoReceita")));
            receita.setTempoReceita(resultSet.getString("tempoReceita"));

            // Adiciona o registro na lista
            lista.add(receita);
        }

        // Fecha a conexão com o BD
        resultSet.close();
        preparedStatement.close();

        // Retorna a lista de registros, gerados pela consulta
        return lista;
    }

    // UPDATE - Altera uma receita no banco
    public void alteraReceita(Receita receita) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        //String sql = "UPDATE receita set nomeReceita = ?, preparoReceita = ?, tempoReceita = ?, rendimentoReceita = ?, idCategoria = ?, idTipica = ?"
        //+ " WHERE idReceita = ?";
        String sql = "UPDATE receita set nomeReceita = ?, preparoReceita = ?, tempoReceita = ?, rendimentoReceita = ?"
                + " WHERE idReceita = ?";
        // preparedStatement recebe o comando SQL
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setString(1, receita.getNomeReceita());
        preparedStatement.setString(2, receita.getPreparoReceita());
        preparedStatement.setString(3, receita.getTempoReceita());
        preparedStatement.setInt(4, receita.getRendimentoReceita());
       // preparedStatement.setInt(5, receita.getIdCategoria());
        // preparedStatement.setInt(6, receita.getIdTipica());
        preparedStatement.setInt(5, receita.getIdReceita());

        // O preparedStatement executa o comando SQL no BD, e fecha a conexão
        preparedStatement.execute();
        preparedStatement.close();
    }

    // DELETE - Apaga registros
    public void removeReceita(int idReceita) throws SQLException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM Receita WHERE idReceita = ?";

        // stmt recebe o comando SQL
        PreparedStatement prepareStatement = this.conexao.prepareStatement(sql);

        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        prepareStatement.setInt(1, idReceita);

        // Executa o codigo SQL, e fecha
        prepareStatement.execute();
        prepareStatement.close();
    }
}
