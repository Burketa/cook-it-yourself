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
public class Receita {
    private int idReceita;
    private String nomeReceita;
    private String preparoReceita;
    private String tempoReceita;
    private int rendimentoReceita;
    private int idCategoria;
    private int idTipica;
   
    private ArrayList<Ingrediente> ingredientes;
    
   public Receita(int idReceita, String nomeReceita, String preparoReceita, String tempoReceita, int rendimentoReceita, int idCategoria, int idTipica){ 
       this.idReceita = idReceita;
       this.nomeReceita = nomeReceita;
       this.preparoReceita = preparoReceita;
       this.tempoReceita = tempoReceita;
       this.rendimentoReceita = rendimentoReceita;
       this.idCategoria = idCategoria;
       this.idTipica = idTipica;
   }
   
   public Receita(){
   }
   
   public int getIdReceita() {
        return idReceita;
   }
   
   public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
   }
   
   public String getNomeReceita() {
        return nomeReceita;
   }
   
   public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
   }
   
   public String getPreparoReceita() {
        return preparoReceita;
   }
   
   public void setPreparoReceita(String preparoReceita) {
        this.preparoReceita = preparoReceita;
   }
   
   public String getTempoReceita() {
        return tempoReceita;
   }
   
   public void setTempoReceita(String tempoReceita) {
        this.tempoReceita = tempoReceita;
   }
   
   public int getRendimentoReceita() {
        return rendimentoReceita;
   }
   
   public void setRendimentoReceita(int rendimentoReceita) {
        this.rendimentoReceita = rendimentoReceita;
   }
   
   public int getIdCategoria() {
        return idCategoria;
   }
   
   public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
   }
   
   public int getIdTipica() {
        return idTipica;
   }
   
   public void setIdTipica(int idTipica) {
        this.idTipica = idTipica;
   }
   
}

