package model;

/**
 *
 * @author Daniel
 */

public class Produto{
    private int codigo, quantidade;
    private String nome;
    private double preco;
    
    public Produto(int codigo, String nome, int quantidade, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public int getCodigo(){
        return this.codigo;
    }

    public void setNome(String nome){
        this.nome = nome.toUpperCase();
    }
    public String getNome(){
        return this.nome;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public int getQuantidade(){
        return this.quantidade;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }
    public double getPreco(){
        return this.preco;
    }
}
