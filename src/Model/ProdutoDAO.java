package model;
import java.util.List;
/**
 *
 * @author Daniel
 */
public interface ProdutoDAO{
    
    void criarProduto(Produto produto);
    List<Produto> procurarProduto();
    void atualizarProduto(Produto produto);
    void excluirProduto(int codigo);
}

