package view;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class View{
    private JFrame frame;
    private JTextField codigoCreateField , nomeCreateField, quantidadeCreateField, precoCreateField;
    private JTextField codigoUpdateField, nomeUpdateField, quantidadeUpdateField, precoUpdateField;
    private JTextField codigoDeleteField;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JButton criarButton, atualizarButton, excluirButton;
    private JTextField produtoFrenteField, quantidadeFrenteField, totalFrenteField;
    private JComboBox pagamentoFrenteField;
    private JButton cancelarFrenteButton, confirmarFrenteButton, removerFrenteButton, addFrenteButton;
    
    public View(){
        frame = new JFrame("Frente de Caixa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crie um JTabbedPane para as abas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Crie os painéis para cada aba
        JPanel painel1 = new JPanel();
        JPanel painel2 = new JPanel();
        JPanel painel3 = new JPanel();
        JPanel painel4 = new JPanel();

        // Adicione os painéis ao JTabbedPane com rótulos para cada aba
        tabbedPane.addTab("Home", painel2);
        tabbedPane.addTab("Adicionar", painel1);
        tabbedPane.addTab("Atualizar", painel3);
        tabbedPane.addTab("Excluir", painel4);
        
        // Adicione o JTabbedPane ao conteúdo da janela
        frame.getContentPane().add(tabbedPane);
        
        // Definição de layout das
        painel1.setLayout(null);
        painel2.setLayout(null);
        painel3.setLayout(null);
        painel4.setLayout(null);
        
        
        // Painel "Adicionar" de entrada de dados
        JLabel descricaoCreate1 = new JLabel("Bem-vindo à sessão de Adicionar Produto");
        descricaoCreate1.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel descricaoCreate2 = new JLabel("Adicione as informações referentes ao produto corretamente");
        
        JLabel codigoCreateLabel = new JLabel ("Código:");
        codigoCreateField = new JTextField (5);
        JLabel nomeCreateLabel = new JLabel ("Nome:");
        nomeCreateField = new JTextField (30);
        JLabel quantidadeCreateLabel = new JLabel ("Quantidade:");
        quantidadeCreateField = new JTextField (5);
        JLabel precoCreateLabel = new JLabel ("Preço (R$):");
        precoCreateField = new JTextField (6);
        criarButton = new JButton ("Adicionar");
        
        // Adicionando os companentes à este Painel
        painel1.add(descricaoCreate1);
        painel1.add(codigoCreateLabel);
        painel1.add(descricaoCreate2);
        painel1.add(codigoCreateField);
        painel1.add(nomeCreateLabel);
        painel1.add(nomeCreateField);
        painel1.add(quantidadeCreateLabel);
        painel1.add(quantidadeCreateField);
        painel1.add(precoCreateLabel);
        painel1.add(precoCreateField);
        painel1.add(criarButton);
        
        // Definição da posições dos elementos no Painel Adicionar
        descricaoCreate1.setBounds (120, 35, 400, 35);
        codigoCreateLabel.setBounds (150, 144, 50, 25);
        descricaoCreate2.setBounds (130, 85, 355, 40);
        codigoCreateField.setBounds (195, 145, 235, 25);
        nomeCreateLabel.setBounds (156, 176, 40, 20);
        nomeCreateField.setBounds (195, 175, 235, 25);
        quantidadeCreateLabel.setBounds (125, 205, 70, 20);
        quantidadeCreateField.setBounds (195, 205, 235, 25);
        precoCreateLabel.setBounds (130, 235, 75, 20);
        precoCreateField.setBounds (195, 235, 235, 25);
        criarButton.setBounds (260, 265, 100, 25);

        // Adição de cor de letras e fundo de painel
        painel1.setBackground(new Color(70,130,180));
        descricaoCreate1.setForeground(new Color(255, 255, 255));
        descricaoCreate2.setForeground(new Color(255, 255, 255));
        codigoCreateLabel.setForeground(new Color(255, 255, 255));
        nomeCreateLabel.setForeground(new Color(255, 255, 255));
        quantidadeCreateLabel.setForeground(new Color(255, 255, 255));
        precoCreateLabel.setForeground(new Color(255, 255, 255));
        
        // Adição de cor de fundo
        codigoCreateField.setBackground(new Color(176,196,222));
        nomeCreateField.setBackground(new Color(176,196,222));
        quantidadeCreateField.setBackground(new Color(176,196,222));
        precoCreateField.setBackground(new Color(176,196,222));
        criarButton.setBackground(new Color(173,216,230));

        // Adição de Bordas
        codigoCreateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        nomeCreateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        quantidadeCreateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        precoCreateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        criarButton.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        
        
        // Elementos do Painel "Home"
        String[] pagamentoFrenteFieldItems = {"Dinheiro", "Pix", "Cartão de Débito", "Cartão de Crédito"};
        JLabel produtoFrenteLabel = new JLabel ("CÓDIGO DO PRODUTO");
        JLabel quantidadeFrenteLabel = new JLabel ("QUANTIDADE");
        JLabel pagamentoFrenteLabel = new JLabel ("PAGAMENTO");
        produtoFrenteField = new JTextField (5);
        quantidadeFrenteField = new JTextField (5);
        quantidadeFrenteField.setText("1");
        pagamentoFrenteField = new JComboBox (pagamentoFrenteFieldItems);
        cancelarFrenteButton = new JButton ("X CANCELAR");
        confirmarFrenteButton = new JButton ("CONFIRMAR");
        removerFrenteButton = new JButton("REMOVER");
        addFrenteButton = new JButton ("+");
        tableModel = new DefaultTableModel();
        tabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);
        tableModel.addColumn("CÓDIGO");
        tableModel.addColumn("QTD");
        tableModel.addColumn("PRODUTO");
        tableModel.addColumn("VL. UNID");
        tableModel.addColumn("VL TOTAL");
        
        JPanel totalFrente = new JPanel();
        JLabel descricaototalFrente = new JLabel("  TOTAL:");
        JLabel moedaFrente = new JLabel("R$ ");
        totalFrenteField = new JTextField(6);
        totalFrenteField.setText("0.00");
        
        //Propriedade
        totalFrenteField.setEnabled(false);
        
        //
        totalFrente.setLayout(new BorderLayout());
        totalFrente.add(descricaototalFrente, BorderLayout.WEST);
        totalFrente.add(moedaFrente, BorderLayout.EAST);
        totalFrente.add(totalFrenteField, BorderLayout.EAST);
        
        // Adicionando componentes
        painel2.add(produtoFrenteLabel);
        painel2.add(quantidadeFrenteLabel);
        painel2.add(quantidadeFrenteField);
        painel2.add(pagamentoFrenteField);
        painel2.add(pagamentoFrenteLabel);
        painel2.add(cancelarFrenteButton);
        painel2.add(confirmarFrenteButton);
        painel2.add(produtoFrenteField);
        painel2.add(addFrenteButton);
        painel2.add(removerFrenteButton);
        painel2.add(scrollPane);
        painel2.add(totalFrente);
        
        // Posicionando
        produtoFrenteLabel.setBounds (130, 10, 200, 25);
        produtoFrenteField.setBounds (130, 35, 575, 35);
        quantidadeFrenteLabel.setBounds (15, 10, 100, 25);
        quantidadeFrenteField.setBounds (15, 35, 100, 35);
        pagamentoFrenteField.setBounds (15, 435, 150, 30);
        pagamentoFrenteLabel.setBounds (15, 410, 200, 25);
        cancelarFrenteButton.setBounds (300, 480, 140, 35);
        confirmarFrenteButton.setBounds (620, 480, 140, 35);
        removerFrenteButton.setBounds (460, 480, 140, 35);
        addFrenteButton.setBounds (720, 35, 41, 35);
        scrollPane.setBounds (300, 85, 460, 325);
        totalFrente.setBounds(300, 425, 460, 40);
        moedaFrente.setBounds(350, 0, 30, 40);
        
        // Definindo tamanho das celulas da tabela
        TableColumnModel columnModel = tabela.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(10);
        columnModel.getColumn(2).setPreferredWidth(175);
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(4).setPreferredWidth(55);
        
        tabela.setRowHeight(30);
        
        //
        painel2.setBackground(new Color(230,230,230));
        confirmarFrenteButton.setBackground(new Color(49,148,109));
        cancelarFrenteButton.setBackground(new Color(247,247,247));
        removerFrenteButton.setBackground(new Color(230,94,94));
        addFrenteButton.setBackground(new Color(247,247,247));
        produtoFrenteField.setBackground(new Color(253,252,224));
        tabela.setBackground(new Color(247,247,247));
        totalFrente.setBackground(new Color(226,46,0));
        totalFrenteField.setBackground(new Color(226,46,0));
        pagamentoFrenteField.setBackground(new Color(247,247,247));
        
        
        //
        confirmarFrenteButton.setBorder(BorderFactory.createLineBorder(new Color(160,160,160), 2));
        cancelarFrenteButton.setBorder(BorderFactory.createLineBorder(new Color(160,160,160), 2));
        removerFrenteButton.setBorder(BorderFactory.createLineBorder(new Color(160,160,160), 2));
        quantidadeFrenteField.setBorder(BorderFactory.createLineBorder(new Color(160,160,160)));
        produtoFrenteField.setBorder(BorderFactory.createLineBorder(new Color(240,73,10), 2));
        addFrenteButton.setBorder(BorderFactory.createLineBorder(new Color(160,160,160), 2));
        totalFrenteField.setBorder(BorderFactory.createLineBorder(new Color(226,46,0)));
        scrollPane.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 5, 5));
        
        JTableHeader tabelaHeader = tabela.getTableHeader();
        
        //
        produtoFrenteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        quantidadeFrenteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pagamentoFrenteLabel.setFont(new Font("Arial", Font.BOLD, 18));
        confirmarFrenteButton.setFont(new Font("Arial", Font.BOLD, 14));
        removerFrenteButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelarFrenteButton.setFont(new Font("Arial", Font.BOLD, 14));
        produtoFrenteField.setFont(new Font("Arial", Font.BOLD, 14));
        quantidadeFrenteField.setFont(new Font("Arial", Font.BOLD, 14));
        tabelaHeader.setFont(new Font("Arial", Font.BOLD, 14));
        descricaototalFrente.setFont(new Font("Arial", Font.BOLD, 20));
        moedaFrente.setFont(new Font("Arial", Font.BOLD, 16));
        totalFrenteField.setFont(new Font("Arial", Font.BOLD, 16));
        
        //
        produtoFrenteField.setForeground(new Color(142,161,176));
        confirmarFrenteButton.setForeground(Color.WHITE);
        removerFrenteButton.setForeground(Color.WHITE);
        descricaototalFrente.setForeground(new Color(247,247,247));
        moedaFrente.setForeground(new Color(247,247,247));
        quantidadeFrenteField.setForeground(new Color(0,0,0));
        produtoFrenteField.setForeground(new Color(0,0,0));
        produtoFrenteField.setDisabledTextColor(new Color(0,0,0));
        totalFrenteField.setDisabledTextColor(new Color(247,247,247));
        
        
        // Elementos do Painel "Atualizar"
        JLabel descricaoUpdate1 = new JLabel("Bem-vindo à sessão de Atualizar Produto");
        JLabel descricaoUpdate2 = new JLabel("Adicione as informações referentes ao produto corretamente");
        descricaoUpdate1.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel codigoUpdateLabel = new JLabel ("Código:");
        codigoUpdateField = new JTextField (5);
        JLabel nomeUpdateLabel = new JLabel ("Nome:");
        nomeUpdateField = new JTextField (30);
        JLabel quantidadeUpdateLabel = new JLabel ("Quantidade:");
        quantidadeUpdateField = new JTextField (5);
        JLabel precoUpdateLabel = new JLabel ("Preço (R$):");
        precoUpdateField = new JTextField (6);
        atualizarButton = new JButton ("Atualizar");
        
        // Adição dos elementos no Painel "Atualizar"
        painel3.add(descricaoUpdate1);
        painel3.add(codigoUpdateLabel);
        painel3.add(descricaoUpdate2);
        painel3.add(codigoUpdateField);
        painel3.add(nomeUpdateLabel);
        painel3.add(nomeUpdateField);
        painel3.add(quantidadeUpdateLabel);
        painel3.add(quantidadeUpdateField);
        painel3.add(precoUpdateLabel);
        painel3.add(precoUpdateField);
        painel3.add(atualizarButton);
        
        // Posicionamento dos elementos no Painel "Atualizar"
        descricaoUpdate1.setBounds (120, 35, 400, 35);
        codigoUpdateLabel.setBounds (150, 144, 50, 25);
        descricaoUpdate2.setBounds (130, 85, 355, 40);
        codigoUpdateField.setBounds (195, 145, 235, 25);
        nomeUpdateLabel.setBounds (156, 176, 40, 20);
        nomeUpdateField.setBounds (195, 175, 235, 25);
        quantidadeUpdateLabel.setBounds (125, 205, 70, 20);
        quantidadeUpdateField.setBounds (195, 205, 235, 25);
        precoUpdateLabel.setBounds (130, 235, 75, 20);;
        precoUpdateField.setBounds (195, 235, 235, 25);
        atualizarButton.setBounds (260, 265, 100, 25);
        
        // Adição de cor de fundo
        painel3.setBackground(new Color(70,130,180));
        descricaoUpdate1.setForeground(new Color(255, 255, 255));
        descricaoUpdate2.setForeground(new Color(255, 255, 255));
        codigoUpdateLabel.setForeground(new Color(255, 255, 255));
        nomeUpdateLabel.setForeground(new Color(255, 255, 255));
        quantidadeUpdateLabel.setForeground(new Color(255, 255, 255));
        precoUpdateLabel.setForeground(new Color(255, 255, 255));
        
        // Adição de cor de letras
        codigoUpdateField.setBackground(new Color(176,196,222));
        nomeUpdateField.setBackground(new Color(176,196,222));
        quantidadeUpdateField.setBackground(new Color(176,196,222));
        precoUpdateField.setBackground(new Color(176,196,222));
        atualizarButton.setBackground(new Color(173,216,230));
        
        // Adição de bordas
        codigoUpdateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        nomeUpdateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        quantidadeUpdateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        precoUpdateField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        atualizarButton.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        
        
        // Elementos do Painel "Deletar"
        JLabel descricaoDelete1 = new JLabel("Bem-vindo à sessão de Excluir Produto");
        descricaoDelete1.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel descricaoDelete2 = new JLabel("Digite o código do produto que deseja remover do estoque.");
        codigoDeleteField = new JTextField(5);
        JLabel codigoDeleteLabel = new JLabel("Código:");
        excluirButton = new JButton("Procurar");
        
        // Adicionando os elemetnos no Painel "Deletar"
        painel4.add(descricaoDelete1);
        painel4.add(descricaoDelete2);
        painel4.add(codigoDeleteLabel);
        painel4.add(codigoDeleteField);
        painel4.add(excluirButton);
        
        // Posicionamento dos elementos no Painel "Deletar"
        descricaoDelete1.setBounds (120, 35, 400, 35);
        descricaoDelete2.setBounds (150, 65, 355, 40);
        codigoDeleteLabel.setBounds (180, 110, 50, 25);
        codigoDeleteField.setBounds (225, 110, 75, 25);
        excluirButton.setBounds(305, 110, 60, 25);
        
        // Adição de cor de fundo e de letras
        painel4.setBackground(new Color(70,130,180));
        descricaoDelete1.setForeground(new Color(255, 255, 255));
        descricaoDelete2.setForeground(new Color(255, 255, 255));
        codigoDeleteLabel.setForeground(new Color(255, 255, 255));
        codigoDeleteField.setBackground(new Color(176,196,222));
        
        //  Adição de Bordas
        codigoDeleteField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        excluirButton.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        
        
        // Defina o tamanho da janela e torne-a visível
        frame.setSize(new Dimension(800, 600));
        frame.setVisible(true);
        frame.setResizable(false);
        
    }  
    // Metodos para acessar os dados da interface
    // Dados de "Create"
    public String getcodigoCreate(){
        return codigoCreateField.getText();
    }
    public String getnomeCreate(){
        return nomeCreateField.getText();
    }
    public String getquantidadeCreate(){
        return quantidadeCreateField.getText();
    }
    public String getprecoCreate(){
        return precoCreateField.getText();
    }
    // Dados de "Home"
    public String getprodutoFrente(){
        return produtoFrenteField.getText();
    }
    public String getquantidadeFrente(){
        return quantidadeFrenteField.getText();
    }
    // Dados de "Update"
    public String getcodigoUpdate(){
        return codigoUpdateField.getText();
    }
    public String getnomeUpdate(){
        return nomeUpdateField.getText();
    }
    public String getquantidadeUpdate(){
        return quantidadeUpdateField.getText();
    }
    public String getprecoUpdate(){
        return precoUpdateField.getText();
    }
    // Dados de "Delete"
    public String getcodigoDelete(){
        return codigoDeleteField.getText();
    }
    // Métodos para interagir com a tabela de produtos procurados
    public int getLinhasTabela(){
        return tableModel.getRowCount();
    }
    public void adicionarTabela(Produto produto){
        tableModel.setRowCount(0);
        Object[] row = { produto.getCodigo(), produto.getQuantidade(), produto.getNome(), (Math.round(produto.getPreco() *100.0)/100.0) , (Math.round((produto.getQuantidade())*(produto.getPreco()) *100.0)/100.0)};
        tableModel.addRow(row);
    }
    public void removerUltimoTabela(){
        int coluna = tableModel.getRowCount();
        tableModel.removeRow(coluna-1);
    }
    public void removerTabela(){
        int coluna = tableModel.getRowCount();
        for(int i = 1; i < coluna; i++){
            tableModel.removeRow(i);
        }
    }
    public void setTotal(String preco){
        totalFrenteField.setText(preco);
    }
    public String getTotal(){
        return totalFrenteField.getText();
    }
    public List<Produto> listaTabela(){
        List<Produto> produtos = new ArrayList<>();
        int linhas = tableModel.getRowCount();
        for(int i = 0; i < linhas; i++){
            int codigo = (int)(tableModel.getValueAt(i, 0));
            int quantidade = (int)(tableModel.getValueAt(i, 1));
            String nome = (tableModel.getValueAt(i, 2)).toString();
            double preco = (double)(tableModel.getValueAt(i, 3));
            Produto produto = new Produto(codigo, nome, quantidade, preco);
            produtos.add(produto);
        }
        return produtos;
    }
    // Adição de eventos para serem usados no Controller
    // Função de exibição de mensagem de alerta
    public void MostraMensagem(String text){
        JOptionPane.showMessageDialog(frame, text);
    }
    // Função de limpar campos de texto
    public void limparCampos(){
        codigoCreateField.setText("");
        nomeCreateField.setText("");
        quantidadeCreateField.setText("");
        precoCreateField.setText("");
        codigoUpdateField.setText("");
        nomeUpdateField.setText("");
        quantidadeUpdateField.setText("");
        precoUpdateField.setText("");
        codigoDeleteField.setText("");
        produtoFrenteField.setText("");
        quantidadeFrenteField.setText("1");
    }
    // Evento de criar produto ao apertar o botão
    public void addCriarListener(ActionListener listener){
        criarButton.addActionListener(listener);
    }
    // Evento de procurar produto ao apertar o botão
    public void addFrenteListener(ActionListener listener){
        addFrenteButton.addActionListener(listener);
    }
    public void confirmarFrenteListener(ActionListener listener){
        confirmarFrenteButton.addActionListener(listener);
    }
    public void removerFrenteListener(ActionListener listener){
        removerFrenteButton.addActionListener(listener);
    }
    public void cancelarFrenteListener(ActionListener listener){
        cancelarFrenteButton.addActionListener(listener);
    }
    // Evento de atualizar produto ao apertar o botão
    public void addAtualizarListener(ActionListener listener){
        atualizarButton.addActionListener(listener);
    }
    // Evento de excluir produto ao apertar o botão
    public void addExcluirListener(ActionListener listener){
        excluirButton.addActionListener(listener);
    }
    
}

