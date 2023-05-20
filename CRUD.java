import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public class ProdutoPostgre implements ProdutoDAO {
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
        public void createProduto(Produto produto){
            String create = "insert into produtos (nome, quantidade) values (?,?)";
            try{
                PreparedStatement statement = connection.prepareStatement(create);
                statement.setString(1, produto.getNome());
                statement.setString(2, produto.getQuantidade());
                statement.executeUpdate();
                statement.close();
            }
            catch (Exception e) {
                System.out.printl(e)
            }
        }
        @Override
        public List<Produto> findTodosProdutos() {
            List<Produto> produtos = new ArrayList<>();
            String read = "select * from produtos order by nome";
            try {
                PreparedStatement statement = connection.prepareStatement(read);
                ResultSet resultset = statement.executeQuery();

                while(resultset.next()){
                    Produto produto = new Produto();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setQuantidade(resultset.getInt("quantidade"));

                    produtos.add(produto);
                }
                
                resultset.close();
                statement.close();

            } catch (Exception e) {
                System.out.printl(e);
                return null;
            }
        }


