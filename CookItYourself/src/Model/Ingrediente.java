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
public class Ingrediente {
    private int idIngrediente;
    private String nomeIngrediente;
    private float precoIngrediente;
    private int estoqueIngrediente;
    
   public Ingrediente(int idIngrediente, String nomeIngrediente, float precoIngrediente, int estoqueIngrediente){ 
       this.idIngrediente = idIngrediente;
       this.nomeIngrediente = nomeIngrediente;
       this.precoIngrediente = precoIngrediente;
       this.estoqueIngrediente = estoqueIngrediente;
   }
   
   public Ingrediente(){
   }
   
   public int getIdIngrediente() {
        return idIngrediente;
   }
   
   public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
   }
   
   public String getNomeIngrediente() {
        return nomeIngrediente;
   }
   
   public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
   }
   
   public float getPrecoIngrediente() {
        return precoIngrediente;
   }
   
   public void setPrecoIngrediente(float precoIngrediente) {
        this.precoIngrediente = precoIngrediente;
   }
   
   public int getEstoqueIngrediente() {
        return estoqueIngrediente;
   }
   
}
