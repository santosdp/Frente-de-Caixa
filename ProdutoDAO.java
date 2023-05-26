package Model;
import java.util.List;

public interface ProdutoDAO {
    void adicionarProduto(Produto produto);

    Produto buscarProduto(int id);

    List<Produto> buscarTodosProdutos();

    void atualizarProduto(Produto produto);

    void deletarProduto(int id);
}
