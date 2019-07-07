package Model;

import java.util.ArrayList;

public class Receita {
    private int id;
    private String nome;
    private String preparo;
    private String tempo;
    private int rendimento;
    private Categoria categoria;
    private Tipica tipica;
    private int categoriaId;
    private int tipicaId;
    private String categoriaString;
    private String tipicaString;
    
   public Receita(int idReceita, String nomeReceita, String preparoReceita, String tempoReceita, int rendimentoReceita, int idCategoria, int idTipica){ 
       this.id = idReceita;
       this.nome = nomeReceita;
       this.preparo = preparoReceita;
       this.tempo = tempoReceita;
       this.rendimento = rendimentoReceita;
       this.categoriaId = idCategoria;
       this.tipicaId = idTipica;
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
   
   public Categoria getCategoria() {
        return categoria;
   }
   
   public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        categoriaId = categoria.getId();
        categoriaString = categoria.getNome();
   }
   
   public Tipica getTipica() {
        return tipica;
   }
   
   public void setTipica(Tipica tipica) {
        this.tipica = tipica;
        tipicaId = tipica.getId();
        tipicaString = tipica.getNome();
   }
   
   public String getCategoriaString() {
        return categoriaString;
   }
   
   public void setCategoriaString(String categoriaString) {
        this.categoriaString = categoriaString;
   }
   public int getCategoriaId() {
        return categoriaId;
   }
   
   public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
   }
   
   public String getTipicaString() {
        return tipicaString;
   }
   
   public void setTipicaString(String tipicaString) {
        this.tipicaString = tipicaString;
   }
   
   public int getTipicaId() {
        return tipicaId;
   }
   
   public void setTipicaId(int tipicaId) {
        this.tipicaId = tipicaId;
   }
   
   @Override
   public String toString()
   {
       return getNome();
   }
}

