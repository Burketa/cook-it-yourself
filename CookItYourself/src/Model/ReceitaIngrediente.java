package Model;

public class ReceitaIngrediente {
    private Receita receita;
    private Ingrediente ingrediente;
    private int idReceita;
    private int idIngrediente;
    private float quantidade;
   
    
   public ReceitaIngrediente(int idReceita, int idIngrediente, float quantidade){ 
       this.idReceita = idReceita;
       this.idIngrediente = idIngrediente;
       this.quantidade = quantidade;
   }
   
   public ReceitaIngrediente(){
   }
   
   public Receita getReceita() {
        return receita;
   }
   
   public void setReceita(Receita receita) {
        this.receita = receita;
        idReceita = receita.getId();
   }
   
   public Ingrediente getIngrediente() {
        return ingrediente;
   }
   
   public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
        idIngrediente = ingrediente.getId();
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
   
   @Override
   public String toString()
   {
       return "" + getIdIngrediente() + " - " + getQuantidade();//ingrediente.getNome() + " - " + getQuantidade() + "" + ingrediente.getMedidaString();
   }
}