/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

public class Ingrediente extends Produto {

    private float quantidade;
    private String medidaPadrao;

    public Ingrediente() {
        super();
    }

    //Construtor com chamada para a classe pai
    public Ingrediente(float quantidade, String medidaPadrao, int id, String nome, float preco) {
        super(id, nome, preco);
        this.quantidade = quantidade;
        this.medidaPadrao = medidaPadrao;
    }

    public Ingrediente(float quantidade, String medidaPadrao, Produto produto) {
        super(produto.getId(), produto.getNome(), produto.getPreco());
        this.quantidade = quantidade;
        this.medidaPadrao = medidaPadrao;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedidaPadrao() {
        return medidaPadrao;
    }

    public void setMedidaPadrao(String medidaPadrao) {
        this.medidaPadrao = medidaPadrao;
    }
    
    public void salvarIngrediente() {

    }

    public void alterarIngrediente() {

    }

    public void excluirIngrediente() {

    }

    @Override
    public String toString() {
        return getNome() + " - " + getQuantidade() + "" + getMedidaPadrao();
    }
}
