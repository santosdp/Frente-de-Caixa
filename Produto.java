package Model;

public class Produto{
    private int id, quantidade;
    private String nome;
    private float preco;

    public Produto(int id, String nome, int quantidade, float preco){
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setNome(int nome){
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

    public void setPreco(int preco){
        this.preco = preco;
    }
    public float getPreco(){
        return this.preco;
    }
}