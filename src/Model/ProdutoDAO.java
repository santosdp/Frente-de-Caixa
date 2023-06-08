package model;
/**
 *
 * @author Daniel
 */
public interface ProdutoDAO{
    void criarProduto(Produto produto);
    Produto procurarProduto(int codigo);
    void atualizarProduto(Produto produto);
    void excluirProduto(int codigo);
}

