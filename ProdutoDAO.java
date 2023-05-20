import java.util.List;

public interface ProdutoDAO {
    void createProduto(Produto produto);

    Produto findProduto(int id);

    List<Produto> findTodosProdutos();

    void updateProduto(Produto produto);

    void deleteProduto(int id);
}