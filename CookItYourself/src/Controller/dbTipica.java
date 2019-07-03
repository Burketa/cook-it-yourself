/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import Model.Tipica;

public class dbTipica {
    //Conexão com o banco
    private Connection conexao;
    
    public dbTipica() throws SQLException {       
        this.conexao = Conexao.getConexao();
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
}
