package Model;

import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Receita {
    private int id;
    private String nome;
    private String preparo;
    private String tempo;
    private int rendimento;
    private int idCategoria;
    private int idTipica;
   
    private ArrayList<Ingrediente> ingredientes;
    
   public Receita(int idReceita, String nomeReceita, String preparoReceita, String tempoReceita, int rendimentoReceita, int idCategoria, int idTipica){ 
       this.id = idReceita;
       this.nome = nomeReceita;
       this.preparo = preparoReceita;
       this.tempo = tempoReceita;
       this.rendimento = rendimentoReceita;
       this.idCategoria = idCategoria;
       this.idTipica = idTipica;
   }
   
   public Receita(){
   }
   
   public int getId() {
        return id;
   }
   
   public void setId(int idReceita) {
        this.id = idReceita;
   }
   
   public String getNome() {
        return nome;
   }
   
   public void setNome(String nomeReceita) {
        this.nome = nomeReceita;
   }
   
   public String getPreparo() {
        return preparo;
   }
   
   public void setPreparo(String preparoReceita) {
        this.preparo = preparoReceita;
   }
   
   public String getTempo() {
        return tempo;
   }
   
   public void setTempo(String tempoReceita) {
        this.tempo = tempoReceita;
   }
   
   public int getRendimento() {
        return rendimento;
   }
   
   public void setRendimento(int rendimentoReceita) {
        this.rendimento = rendimentoReceita;
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
   
   @Override
   public String toString()
   {
       return getNome();
   }
}

