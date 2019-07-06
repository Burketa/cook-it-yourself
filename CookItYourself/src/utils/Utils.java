/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Burca
 */
public class Utils {

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean ingredienteCamposPreenchidos(String nome, String preco, String estoque)
    {
        if((!nome.equals("")) && (!preco.equals("")) && (!estoque.equals("")))
            return true;
        else
            return false;
    }
}
