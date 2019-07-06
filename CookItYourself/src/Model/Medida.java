/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Burca
 */
public class Medida {
    private int id;
    private String tipo;
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    public void setTipo(String nome)
    {
        this.tipo = nome;
    }
    
    @Override
    public String toString() {
        return getTipo();
    }
}
