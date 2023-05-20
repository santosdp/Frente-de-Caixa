import java.util.Scanner;

public class Main {

    static public ProdutoDAO getAcessDB(){
        return new ProdutoPostgre();
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do produto: ");
        String nome = scan.nextLine();
        System.out.println("Digite a quantidade do produto: ");
        String quantidade = scan.nextLine();

        ProdutoDAO acessarDB = Main.getAcessDB();
        Produto novoProduto = acessarDB.create(nome, quantidade);
    }
}