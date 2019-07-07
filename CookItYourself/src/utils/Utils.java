/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Model.Medida;

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

    public static boolean ingredienteCamposPreenchidos(String nome, String preco, String estoque, Medida medida) {
        if ((!nome.equals("")) && (!preco.equals("")) && (!estoque.equals("")) && (medida != null)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean receitaCamposPreenchidos(String nome, String modo, String tempo, String rendimento) {
        if ((!nome.equals("")) && (!modo.equals("")) && (!tempo.equals("")) && (!rendimento.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public static int medidaStringToId(String string) {
        switch (string) {
            case "ml":
                return 1;
            case "l":
                return 2;
            case "g":
                return 3;
            case "kg":
                return 4;
            case "colher(es) (café)":
                return 5;
            case "colher(es) de chá":
                return 6;
            case "colher(es) de sobremesa":
                return 7;
            case "colher(es) de sopa":
                return 8;
            case "xícara(s) de café":
                return 9;
            case "xícara(s) de chá":
                return 10;
            case "unidade":
                return 11;
            default:
                return 1;
        }
    }

    public static String medidaIdToString(int id) {
        switch (id) {
            case 1:
                return "ml";
            case 2:
                return "l";
            case 3:
                return "g";
            case 4:
                return "kg";
            case 5:
                return "colher(es) (café)";
            case 6:
                return "colher(es) de chá";
            case 7:
                return "colher(es) de sobremesa";
            case 8:
                return "colher(es) de sopa";
            case 9:
                return "xícara(s) de café";
            case 10:
                return "xícara(s) de chá";
            case 11:
                return "unidade";
            default:
                return "ml";
        }
    }
}
