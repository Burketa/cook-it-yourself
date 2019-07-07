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
        String sql = "INSERT INTO receita (nomeReceita, preparoReceita, tempoReceita, rendimentoReceita, idCategoria, idTipica, categoriaReceita, tipicaReceita)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        // preparedStatement recebe o comando SQL
        preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setString(1, receita.getNome());
        preparedStatement.setString(2, receita.getPreparo());
        preparedStatement.setString(3, receita.getTempo());
        preparedStatement.setInt(4, receita.getRendimento());
        preparedStatement.setInt(5, receita.getCategoriaId());
        preparedStatement.setInt(6, receita.getTipicaId());
        preparedStatement.setString(7, receita.getCategoriaString());
        preparedStatement.setString(8, receita.getTipicaString());

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
            receita.setId(Integer.valueOf(resultSet.getString("idReceita")));
            receita.setNome(resultSet.getString("nomeReceita"));
            receita.setPreparo(resultSet.getString("preparoReceita"));
            receita.setCategoriaId(Integer.parseInt(resultSet.getString("idCategoria")));
            receita.setTipicaId(Integer.parseInt(resultSet.getString("idTipica")));
            receita.setCategoriaString(resultSet.getString("categoriaReceita"));
            receita.setTipicaString(resultSet.getString("tipicaReceita"));
            receita.setRendimento(Integer.parseInt(resultSet.getString("rendimentoReceita")));
            receita.setTempo(resultSet.getString("tempoReceita"));

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
        String sql = "UPDATE receita set nomeReceita = ?, preparoReceita = ?, tempoReceita = ?, rendimentoReceita = ?, idCategoria = ?, idTipica = ?, categoriaReceita = ?, tipicaReceita = ?"
                + " WHERE idReceita = ?";
        // preparedStatement recebe o comando SQL
        PreparedStatement preparedStatement = this.conexao.prepareStatement(sql);

        // Seta os valores p/ o preparedStatement, substituindo os "?"
        preparedStatement.setString(1, receita.getNome());
        preparedStatement.setString(2, receita.getPreparo());
        preparedStatement.setString(3, receita.getTempo());
        preparedStatement.setInt(4, receita.getRendimento());
        preparedStatement.setInt(5, receita.getCategoriaId());
        preparedStatement.setInt(6, receita.getTipicaId());
        preparedStatement.setString(7, receita.getCategoriaString());
        preparedStatement.setString(8, receita.getTipicaString());
        preparedStatement.setInt(9, receita.getId());

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
