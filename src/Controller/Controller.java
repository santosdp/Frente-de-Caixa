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
        view.addCriarListener(new AdicionarListener());
        view.addAtualizarListener(new AtualizarListener());
        view.addExcluirListener(new ExcluirListener());
        
    }
    
    class AddFrenteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String codigoCarrinho = view.getprodutoFrente();
            String quantidadePre = view.getquantidadeFrente();
            
            Produto produtoEstoque = produtoDAO.procurarProduto(codigoCarrinho);
            if(produtoEstoque == null){
                view.MostraMensagem("Produto não existe!");
                return;
            }
            
            if(codigoCarrinho.length() != 5){
                view.MostraMensagem("Código de Produto inválido!");
                return;
            }
            if(quantidadePre.length() > 5){
                view.MostraMensagem("Quantidade de Produto inválido!");
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
            int quantidade = view.getLinhasTabela();
            if(quantidade > 0){
                view.removerTabela();
                view.limparCampos();
                view.setTotal("0.00");
            }
            else{
                view.MostraMensagem("Nenhum produto no carrinho.");
            }
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
            precototal += produto.getPreco() * produto.getQuantidade();
        }
        double precoarredondado = (Math.round(precototal *100.0)/100.0);
        view.setTotal(Double.toString(precoarredondado));
    }
    
    private class AdicionarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigoAdicionar = view.getcodigoCreate();
            String nomeAdicionar = view.getnomeCreate();
            String quantidadeAdicionar = view.getquantidadeCreate();
            String precoAdicionar = view.getprecoCreate();
            
            Produto produtoEstoque = produtoDAO.procurarProduto(codigoAdicionar);
            if(!(produtoEstoque == null)){
                view.MostraMensagem("Código de produto já cadastrado no sistema!");
                return;
            }
            
            if(codigoAdicionar.length() != 5){
                view.MostraMensagem("Código de Produto inválido!");
                return;
            }
            if(nomeAdicionar.length() < 1 && nomeAdicionar.equals(" ")){
                view.MostraMensagem("Nome de Produto inválido!");
                return;
            }
            if(quantidadeAdicionar.length() > 5){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            if(precoAdicionar.length() < 1 && precoAdicionar.equals(" ") && precoAdicionar.equals("0")){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            
            
            int quantidadeCarrinho;
            try{
                quantidadeCarrinho = Integer.parseInt(quantidadeAdicionar);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            if(quantidadeCarrinho < 1){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            double precoCarrinho;
            try{
                precoCarrinho = (Math.round(Double.parseDouble(precoAdicionar) *100.0)/100.0);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            if(precoCarrinho <= 0){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            

            Produto produto = new Produto(codigoAdicionar, nomeAdicionar, quantidadeCarrinho, precoCarrinho);

            produtoDAO.criarProduto(produto);
            view.MostraMensagem("Produto adicionado com sucesso.");
            view.limparCampos();
        }
    }
    
    private class AtualizarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigoAtualizar = view.getcodigoUpdate();
            String nomeAtualizar = view.getnomeUpdate();
            String quantidadeAtualizar = view.getquantidadeUpdate();
            String precoAtualizar = view.getprecoUpdate();
            
            Produto produtoEstoque = produtoDAO.procurarProduto(codigoAtualizar);
            if(produtoEstoque == null){
                view.MostraMensagem("Produto não existe!");
                return;
            }
            if(codigoAtualizar.length() != 5){
                view.MostraMensagem("Código de Produto inválido!");
                return;
            }
            if(nomeAtualizar.length() < 1 && nomeAtualizar.equals(" ")){
                view.MostraMensagem("Nome de Produto inválido!");
                return;
            }
            if(quantidadeAtualizar.length() > 5){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            if(precoAtualizar.length() < 1 && precoAtualizar.equals(" ") && precoAtualizar.equals("0")){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            
            int quantidadeCarrinho;
            try{
                quantidadeCarrinho = Integer.parseInt(quantidadeAtualizar);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            if(quantidadeCarrinho < 1){
                view.MostraMensagem("Quantidade de Produto inválido!");
                return;
            }
            double precoCarrinho;
            try{
                precoCarrinho = (Math.round(Double.parseDouble(precoAtualizar) *100.0)/100.0);
            } catch(NumberFormatException ex){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            if(precoCarrinho <= 0){
                view.MostraMensagem("Preço de Produto inválido!");
                return;
            }
            

            Produto produto = new Produto(codigoAtualizar, nomeAtualizar, quantidadeCarrinho, precoCarrinho);

            produtoDAO.atualizarProduto(produto);
            view.MostraMensagem("Produto atualizado com sucesso.");
            view.limparCampos();
        }
    }
    
    private class ExcluirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = view.getcodigoDelete();
            Produto produtoEstoque = produtoDAO.procurarProduto(codigo);
            if(produtoEstoque == null){
                view.MostraMensagem("Código de produto não encontrado no sistema, corrija e tente novamente.");
            }
            produtoDAO.excluirProduto(codigo);
            view.MostraMensagem("Produto excluído com sucesso.");
            view.limparCampos();
        }
    }
    
    
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = Model.getInstancia();
        View view = new View();
        Controller controller = new Controller(view, produtoDAO);
    }
}
