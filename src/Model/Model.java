package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Model implements ProdutoDAO{
    private Connection connection;

    public Model() {
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
    public void criarProduto(Produto produto) {
        String create = "INSERT INTO Produtos (codigo, nome, quantidade, preco) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setInt(1, produto.getCodigo());
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
    public List<Produto> procurarProduto(){
        List<Produto> produtos = new ArrayList<>();
        String readAll = "SELECT * FROM Produtos";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAll);

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setCodigo(resultSet.getInt("codigo"));
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
        String update = "UPDATE Produtos SET nome = ?, quantidade = ?, preco = ? WHERE codigo = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setFloat(3, produto.getPreco());
            statement.setInt(4, produto.getCodigo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluirProduto(int codigo) {
        String sql = "DELETE FROM usuarios WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

