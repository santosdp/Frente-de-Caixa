package Controller;
import model.*;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Controller {
    private View view;
    private ProdutoDAO produtoDAO;
    
    public Controller(View view, ProdutoDAO produtoDAO){
        this.view = view;
        this.produtoDAO = produtoDAO;
        
        view.addFrenteListener(new AddFrenteListener());
        view.confirmarFrenteListener(new ConfirmarFrenteListener());
        view.removerFrenteListener(new RemoverFrenteListener());
        view.cancelarFrenteListener(new CancelarFrenteListener());
        
        atualizaPreco();
    }
    
    class AddFrenteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String codigoPre = view.getprodutoFrente();
            String quantidadePre = view.getquantidadeFrente();
            
            if(codigoPre.length() != 5){
                view.MostraMensagem("Código de Produto inválido!");
                return;
            }
            if(quantidadePre.length() > 5){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            
            int codigoCarrinho;
            try{
                codigoCarrinho = Integer.parseInt(codigoPre);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Código de Produto inválido!");
                return;
            }
            
            int quantidadeCarrinho;
            try{
                quantidadeCarrinho = Integer.parseInt(quantidadePre);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            if(quantidadeCarrinho < 1){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            
            Produto produtoEstoque = produtoDAO.procurarProduto(codigoCarrinho);
            if(produtoEstoque.getQuantidade() < quantidadeCarrinho){
                view.MostraMensagem("Quantidade em estoque insuficiente.");
                return;
            }
            Produto produtoCarrinho = new Produto(codigoCarrinho, produtoEstoque.getNome(), quantidadeCarrinho, produtoEstoque.getPreco());
            view.adicionarTabela(produtoCarrinho);
            view.limparCampos();
            atualizaPreco();
        }
    }
    
    class ConfirmarFrenteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(view.getLinhasTabela() < 1){
                view.MostraMensagem("Nenhum produto no carrinho.");
                return;
            }
            List<Produto> produtos = view.listaTabela();
            for(Produto produto : produtos){
                Produto produtoEstoque = produtoDAO.procurarProduto(produto.getCodigo());
                int novaquantidade = produtoEstoque.getQuantidade() - produto.getQuantidade();
                if(novaquantidade < 0){
                    view.MostraMensagem("Quantidade em estoque insuficiente.");
                    return;
                }
                Produto produtoAtualizado = new Produto(produto.getCodigo(), produto.getNome(), novaquantidade, produto.getPreco());
                produtoDAO.atualizarProduto(produtoAtualizado);
            }
            view.removerTabela();
            atualizaPreco();
            view.MostraMensagem("Compra Finalizada!");
        }
    }
    
    class RemoverFrenteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int quantidade = view.getLinhasTabela();
            if(quantidade > 0){
                view.removerUltimoTabela();
                atualizaPreco();
            }
            else{
                view.MostraMensagem("Nenhum produto para remover.");
            }
        }
    }
    
    class CancelarFrenteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.removerTabela();
            view.limparCampos();
            view.setTotal("0.00");
        }
    }
    
    public void atualizaPreco(){
        List<Produto> produtos = view.listaTabela();
        double precototal = 0;
        int quantidadeProdutos = view.getLinhasTabela();
        if(quantidadeProdutos < 1){
            view.setTotal("0.00");
        }
        for(Produto produto : produtos){
            precototal += produto.getPreco();
        }
    }
    
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new Model();
        View view = new View();
        Controller controller = new Controller(view, produtoDAO);
    }
}
