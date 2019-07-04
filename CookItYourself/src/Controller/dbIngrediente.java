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

public class dbIngrediente {
    
    //Conexão com o banco
    private Connection conexao;
    
    public dbIngrediente() throws SQLException {       
        this.conexao = Conexao.getConexao();
    }
    
    // INSERT - Adiciona um novo registro
    public void adicionaIngrediente(Ingrediente i) throws SQLException {
        
        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO ingrediente (nomeIngrediente, precoIngrediente, estoqueIngrediente)" + "VALUES(?, ?, ?)";   
        
        PreparedStatement stmt;
        
        // stmt recebe o comando SQL
        stmt = this.conexao.prepareStatement(sql);
        
        // Seta os valores p/ o stmt, substituindo os "?"
        stmt.setString(1, i.getNome());
        stmt.setFloat(2, i.getPreco());
        stmt.setInt(3, i.getEstoque());
        
        // O stmt executa o comando SQL no BD, e fecha a conexão
        stmt.execute();
        stmt.close();
        
    }
    
    /* SELECT - Retorna uma lista com o resultado da consulta
    public List<Ingrediente> getLista(String nomeIngrediente) throws SQLException{
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM ingrediente WHERE nome like ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, nomeIngrediente);
        
        // Recebe o resultado da consulta SQL
        ResultSet rs = stmt.executeQuery();
        
        List<Cliente> lista = new ArrayList<>();
        
        // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista
        while(rs.next()) {
            //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
            Cliente c = new Cliente();
            
            // "c" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
            c.setId(Integer.valueOf(rs.getString("id_cliente")));
            c.setNome(rs.getString("nome"));
            c.setDataNasc(rs.getString("data_nasc"));
            c.setSexo(rs.getString("sexo"));
            c.setCpf(rs.getString("cpf"));
            c.setEndereco(rs.getString("endereco"));
            c.setFone(rs.getString("fone"));
            
            // Adiciona o registro na lista
            lista.add(c);            
        }
        
        // Fecha a conexão com o BD
        rs.close();
        stmt.close();
        
        // Retorna a lista de registros, gerados pela consulta
        return lista;          
    }*/
       
    // UPDATE - Atualiza um registro
    public void alteraIngrediente(Ingrediente i) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE ingrediente set nomeIngrediente = ?, precoIngrediente = ?, estoqueIngrediente = ?" + "WHERE idIngrediente = ?";
        
        // stmt recebe o comando SQL
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        // Seta os valores p/ o stmt, substituindo os "?"
        stmt.setString(1, i.getNome());
        stmt.setFloat(2, i.getPreco());
        stmt.setInt(3, i.getEstoque());       
        
        // O stmt executa o comando SQL no BD, e fecha a conexão
        stmt.execute();
        stmt.close();
    }
    
    // DELETE - Apaga registros
    public void removeIngrediente(int idIngrediente) throws SQLException {
        
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM ingrediente WHERE idIngrediente = ?";
        
        // stmt recebe o comando SQL
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        stmt.setInt(1, idIngrediente);
        
        // Executa o codigo SQL, e fecha
        stmt.execute();
        stmt.close(); 
    }   
}
