/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Model.Produto;

import java.util.Arrays;
import java.util.List;

/**
 * @author burca
 */

public class BDMercado {
    public static List<Produto> produtos = Arrays.asList(
            new Produto(0, "Arroz", 1.0f),
            new Produto(1, "Feijao", 2.0f),
            new Produto(2, "Manteiga", 3.0f),
            new Produto(3, "Ovo", 4.0f),
            new Produto(4, "Carne", 5.0f)
    );
}
