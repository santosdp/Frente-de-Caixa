package model;
import java.sql.*;

/**
 *
 * @author Daniel
 */
public class Model implements ProdutoDAO{
    private Connection connection;
    private static Model instancia;
    
    public Model() {
        // Estabelece a conexão com o banco de dados
        String url = "jdbc:postgresql://localhost:5432/FrenteCaixa";
        String username = "postgres";
        String password = "36564663";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Padrão Singleton
    public static Model getInstancia() {
        if (instancia == null) {
            instancia = new Model();
        }
        return instancia;
    }

    @Override
    public void criarProduto(Produto produto) {
        String create = "INSERT INTO produtos (codigo, nome, quantidade, preco) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setString(1, produto.getCodigo());
            statement.setString(2, produto.getNome());
            statement.setInt(3, produto.getQuantidade());
            statement.setDouble(4, produto.getPreco());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produto procurarProduto(String codigo){
        String read = "SELECT * FROM produtos WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(read);
            statement.setString(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()){
                String codigoProduto = resultSet.getString("codigo");
                String nomeProduto = resultSet.getString("nome");
                int quantidadeProduto = resultSet.getInt("quantidade");
                double precoProduto = resultSet.getDouble("preco");
                return new Produto(codigoProduto, nomeProduto, quantidadeProduto, precoProduto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void atualizarProduto(Produto produto) {
        String update = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ? WHERE codigo = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setDouble(3, produto.getPreco());
            statement.setString(4, produto.getCodigo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluirProduto(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

