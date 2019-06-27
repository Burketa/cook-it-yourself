/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Aluno
 */
public class Ingrediente extends Produto {
    private float quantidade;
    private String medidaPadrao;

    //Construtor com chamada para a classe pai
    public Ingrediente(float quantidade, String medidaPadrao, int id, String nome, float preco) {
        super(id, nome, preco);
        this.quantidade = quantidade;
        this.medidaPadrao = medidaPadrao;
    }
    
    public void salvarIngrediente()
    {
        
    }

    public void excluirIngrediente()
    {
        
    }
    
    public void alterarIngrediente()
    {
        
    }
}
