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
public class Produto {
    private int id;
    private String nome;
    private float preco;

    public Produto(int id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    
    public int isEmEstoque()
    {
        return 0;
    }
    
    public float checarPreco()
    {
        return 0;
    }
}
