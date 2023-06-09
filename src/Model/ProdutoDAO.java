package model;
/**
 *
 * @author Daniel
 */
public interface ProdutoDAO{
    void criarProduto(Produto produto);
    Produto procurarProduto(String codigo);
    void atualizarProduto(Produto produto);
    void excluirProduto(String codigo);
}

