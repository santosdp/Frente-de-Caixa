package view;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class View{
    private JFrame frame;
    private JTextField codigoCreateField , nomeCreateField, quantidadeCreateField, precoCreateField;
    private JTextField codigoReadField;
    private JTextField codigoUpdateField, nomeUpdateField, quantidadeUpdateField, precoUpdateField;
    private JTextField codigoDeleteField;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JButton criarButton, procurarButton, atualizarButton, excluirButton;
    
    public View() {
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
        tabbedPane.addTab("Adicionar", painel1);
        tabbedPane.addTab("Procurar", painel2);
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
        precoCreateLabel.setBounds (155, 235, 40, 20);
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
        
        
        // Elementos do Painel "Procurar"
        JLabel descricaoRead1 = new JLabel("Bem-vindo à sessão de Procurar Produto");
        descricaoRead1.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel descricaoRead2 = new JLabel("Digite o código do produto que deseja pesquisar.");
        codigoReadField = new JTextField(5);
        JLabel codigoReadLabel = new JLabel("Código:");
        procurarButton = new JButton("Procurar");
        tableModel = new DefaultTableModel(new Object[]{ "Código", "Nome", "Quantidade", "Preço"}, 0);
        tabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);
        
        // Adicionando os elemetnos no Painel "Procurar"
        painel2.add(descricaoRead1);
        painel2.add(descricaoRead2);
        painel2.add(codigoReadLabel);
        painel2.add(codigoReadField);
        painel2.add(procurarButton);
        painel2.add(scrollPane);
        
        // Posicionamento dos elementos no Painel "Procurar"
        descricaoRead1.setBounds (120, 35, 400, 35);
        descricaoRead2.setBounds (150, 65, 355, 40);
        codigoReadLabel.setBounds (180, 110, 50, 25);
        codigoReadField.setBounds (225, 110, 75, 25);
        procurarButton.setBounds(305, 110, 60, 25);
        scrollPane.setBounds (150, 150, 300, 200);
        
        // Definição de cor de fundo e de letras
        painel2.setBackground(new Color(70,130,180));
        descricaoRead1.setForeground(new Color(255, 255, 255));
        descricaoRead2.setForeground(new Color(255, 255, 255));
        codigoReadLabel.setForeground(new Color(255, 255, 255));
        codigoReadField.setBackground(new Color(176,196,222));
        scrollPane.getViewport().setBackground(new Color(176,196,222));
        
        // Adição de Bordas
        codigoReadField.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        procurarButton.setBorder(BorderFactory.createLineBorder(new Color(70,130,180)));
        
        
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
        precoUpdateLabel.setBounds (155, 235, 40, 20);
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
        frame.setSize(600, 480);
        frame.setVisible(true);
        
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
    // Dados de "Read"
    public String getcodigoRead(){
        return codigoReadField.getText();
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
    public int getLinhaSelecionada(){
        return tabela.getSelectedRow();
    }
    public void atualizarTabela(List<Produto> produtos){
        tableModel.setRowCount(0);
        for(Produto produto : produtos){
            Object[] row = {produto.getCodigo(), produto.getNome(), produto.getQuantidade(), produto.getPreco()};
            tableModel.addRow(row);
        }
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
        codigoReadField.setText("");
        codigoUpdateField.setText("");
        nomeUpdateField.setText("");
        quantidadeUpdateField.setText("");
        precoUpdateField.setText("");
        codigoDeleteField.setText("");
    }
    // Evento de criar produto ao apertar o botão
    public void addCriarListener(ActionListener listener){
        criarButton.addActionListener(listener);
    }
    // Evento de procurar produto ao apertar o botão
    public void addProcurarListener(ActionListener listener){
        procurarButton.addActionListener(listener);
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

