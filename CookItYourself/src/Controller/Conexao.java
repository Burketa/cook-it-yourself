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
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conectando ao banco de dados.");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCook", "root", "2247");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }      
    }
    
    public static void main(String[] args) {
        Conexao.getConexao();
    }
}
