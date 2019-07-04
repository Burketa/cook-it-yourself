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
public class ReceitaIngrediente {
    private int idReceita;
    private int idIngrediente;
    private float quantidade;
    private int idMedida;
   
    
   public ReceitaIngrediente(int idReceita, int idIngrediente, float quatidade, int idMedida){ 
       this.idReceita = idReceita;
       this.idIngrediente = idIngrediente;
       this.quantidade = quantidade;
       this.idMedida = idMedida;
   }
   
   public ReceitaIngrediente(){
   }
   
   public int getIdReceita() {
        return idReceita;
   }
   
   public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
   }
   
   public int getIdIngrediente() {
        return idIngrediente;
   }
   
   public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
   }
   
   public float getQuantidade() {
        return quantidade;
   }
   
   public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
   }
   
   public String getNomeIngrediente() {
        return Integer.toString(idIngrediente);
   }

   public String getPrecoIngrediente() {
        return Integer.toString(idIngrediente);
   }
   
   public String getEstoqueIngrediente() {
        return Integer.toString(idIngrediente);
   }
}