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
    public void adicionaReceita(Receita r) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO receita (nomeReceita, preparoReceita, tempoReceita, rendimentoReceita, idCategoria, idTipica)"
                + "VALUES(?, ?, ?, ?, ?, ?)";       
        PreparedStatement stmt;
        // stmt recebe o comando SQL
        stmt = this.conexao.prepareStatement(sql);
        
        // Seta os valores p/ o stmt, substituindo os "?"
        stmt.setString(1, r.getNomeReceita());
        stmt.setString(2, r.getPreparoReceita());
        stmt.setString(3, r.getTempoReceita());

        stmt.setString(4, String.valueOf(r.getRendimentoReceita()));
        stmt.setString(5, String.valueOf(r.getIdCategoria()));
        stmt.setString(6, String.valueOf(r.getIdTipica()));

        stmt.setInt(4, r.getRendimentoReceita());
        stmt.setInt(5, r.getIdCategoria());
        stmt.setInt(6, r.getIdTipica());
      
        
        // O stmt executa o comando SQL no BD, e fecha a conexão
        stmt.execute();
        stmt.close();
        
    }
    
    /* SELECT - Retorna uma lista com o resultado da consulta
    public List<Receita> getLista(String nomeReceita) throws SQLException{
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM cliente WHERE nome like ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        
        // Recebe o resultado da consulta SQL
        ResultSet rs = stmt.executeQuery();
        
        List<Receita> lista = new ArrayList<>();
        
        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while(rs.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Receita r = new Receita();
            
            // "c" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
            r.setId(Integer.valueOf(rs.getString("id_cliente")));
            r.setNome(rs.getString("nome"));
            r.setDataNasc(rs.getString("data_nasc"));
            r.setSexo(rs.getString("sexo"));
            r.setCpf(rs.getString("cpf"));
            r.setEndereco(rs.getString("endereco"));
            r.setFone(rs.getString("fone"));
            
            // Adiciona o registro na lista
            lista.add(r);            
        }
        
        // Fecha a conexão com o BD
        rs.close();
        stmt.close();
        
        // Retorna a lista de registros, gerados pela consulta
        return lista;          
    }*/
       
    // UPDATE - Altera uma receita no banco
    public void alteraReceita(Receita r) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE receita set nomeReceita = ?, preparoReceita = ?, tempoReceita = ?, rendimentoReceita = ?, idCategoria = ?, idTipica = ?"
                + "WHERE idReceita = ?";
        // stmt recebe o comando SQL
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        // Seta os valores p/ o stmt, substituindo os "?"
        stmt.setString(7, r.getNomeReceita());
        stmt.setString(1, r.getPreparoReceita());
        stmt.setString(2, r.getTempoReceita());
        stmt.setInt(3, r.getRendimentoReceita());
        stmt.setInt(4, r.getIdCategoria());
        stmt.setInt(5, r.getIdTipica());        
        
        // O stmt executa o comando SQL no BD, e fecha a conexão
        stmt.execute();
        stmt.close();
    }
    
    // DELETE - Apaga registros
    public void removeReceita(int idReceita) throws SQLException {
        
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM Receita WHERE idReceita = ?";
        
        // stmt recebe o comando SQL
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        stmt.setInt(1, idReceita);
        
        // Executa o codigo SQL, e fecha
        stmt.execute();
        stmt.close();
    }   
}
