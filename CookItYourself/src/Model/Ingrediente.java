package Model;

import utils.Utils;

public class Ingrediente extends Produto {

    private Medida medida;
    private String medidaString;
    private int medidaId;

    public Ingrediente() {
        super();
    }

    //Construtor com chamada para a classe pai
    public Ingrediente(int id, String nome, float preco, Medida medida) {
        super(id, nome, preco);
        this.medida = medida;
        medidaString = medida.getTipo();
        medidaId = medida.getId();
    }

    public Ingrediente(Produto produto, Medida medida) {
        super(produto.getId(), produto.getNome(), produto.getPreco());
        this.medida = medida;
        medidaString = medida.getTipo();
        medidaId = medida.getId();
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
        medidaString = medida.getTipo();
    }
    
    public String getMedidaString() {
        return medidaString;
    }

    public void setMedidaString(String medidaString) {
        this.medidaString = medidaString;
    }
    
    public int getMedidaId() {
        return medidaId;
    }

    public void setMedidaId(int medidaId) {
        this.medidaId = medidaId;
    }
    
    @Override
    public String toString() {
        //return super.toString() + " Medida: " + getMedidaString();
        return "" + getId() + " - " + getNome() + " | " + getMedidaString();
    }
}
