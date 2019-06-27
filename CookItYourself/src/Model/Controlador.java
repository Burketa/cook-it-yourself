/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Controlador {
    private ArrayList<Receita> receitasCandidatas;
    private ArrayList<Ingrediente> ingredientesIncluidos;
    private ArrayList<Ingrediente> ingredientesRestritos;
    private ArrayList<Integer> preferenciasEscolhidas;
    
    public ArrayList<Receita> incluirIngrediente(ArrayList<Ingrediente> ingredientes)
    {
        return new ArrayList<Receita>();
    }
    
    public ArrayList<Receita> restringirIngrediente(ArrayList<Ingrediente> ingredientes)
    {
        return new ArrayList<Receita>();
    }
    
    public ArrayList<Receita> filtrar(int categoria, int cozinhaTipica,ArrayList<Receita> receitasCandidatas)
    {
        return new ArrayList<Receita>();
    }
    
    public boolean compartilharEmail()
    {
        return false;
    }
    
    public boolean compartilharWhatsapp()
    {
        return false;
    }
}
