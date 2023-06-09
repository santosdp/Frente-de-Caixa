package model;

/**
 *
 * @author Daniel
 */

public class Produto{
    private String codigo;
    private String nome;
    private int quantidade;
    private double preco;
    
    public Produto(String codigo, String nome, int quantidade, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
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
