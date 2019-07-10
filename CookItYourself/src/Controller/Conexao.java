/*
 * Cook It Yourself
 * Projeto de Ofina de Integração
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection getConexao() throws SQLException {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Conectou ao banco");//Public Key Retrieval is not allowed
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCook?autoReconnect=true&useSSL=false", "root", "cookit");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }      
    }
    
    public static void main(String[] args) throws SQLException {
        Conexao.getConexao();
    }
}