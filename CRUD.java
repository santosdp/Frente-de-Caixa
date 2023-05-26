package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public class ProdutoDAO implements ProdutoDAO {
        private Connection connection;

        public ProdutoDAO() {
            // Estabelece a conexão com o banco de dados
            String url = "jdbc:postgresql://localhost:5432/Loja";
            String username = "postgres";
            String password = "12345678";

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void adicionarProduto(Produto produto) {
            String create = "INSERT INTO Produtos (id, nome, quantidade, preco) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(create);
                statement.setInt(1, produto.getId());
                statement.setString(2, produto.getNome());
                statement.setInt(3, produto.getQuantidade());
                statement.setFloat(4, produto.getPreco());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Produto buscarProduto(int id) {
            String read = "SELECT * FROM Produtos WHERE id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(read);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setNome(resultSet.getString("nome"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    produto.setPreco(resultSet.getFloat("preco"));

                    return produto;
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

    @Override
    public List<Produto> buscarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String readAll = "SELECT * FROM Produtos";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAll);
            
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setPreco(resultSet.getFloat("preco"));
                
                produtos.add(produto);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produtos;
    }

    @Override
    public void atualizarProduto(Produto produto) {
        String update = "UPDATE Produtos SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setFloat(3, produto.getPreco());
            statement.setInt(4, produto.getId());
            statement.executeUpdate();
            statement.close

}
