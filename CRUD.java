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
            String sql = "INSERT INTO Produtos (nome, email) VALUES (?, ?)";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, produto.getNome());
                statement.setString(2, produto.getEmail());
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Produto buscarProduto(int id) {
            String sql = "SELECT * FROM Produtos WHERE id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultSet.getInt("id"));
                    produto.setNome(resultSet.getString("nome"));
                    produto.setEmail(resultSet.getString("email"));

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
        String sql = "SELECT * FROM Produtos";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setEmail(resultSet.getString("email"));
                
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
        String sql = "UPDATE Produtos SET nome = ?, email = ? WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getEmail());
            statement.setInt(3, produto.getId());
            statement.executeUpdate();
            statement.close

}
