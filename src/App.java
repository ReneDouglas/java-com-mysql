import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import banco_de_dados.FuncionarioDAO;
import entidades.Funcionario;

public class App {
    public static void main(String[] args) throws Exception {

        menuInicial();

    }

    public static void menuInicial() throws ParseException, IOException{

        Scanner ler = new Scanner(System.in);
        Integer opcao = 0;

        System.out.println("======== CRUD FUNCIONÁRIOS ========");
        System.out.println("[1] - Cadastrar Funcionário");
        System.out.println("[2] - Listar Funcionários");
        System.out.println("[3] - Alterar Funcionário");
        System.out.println("[4] - Deletar Funcionário");
        System.out.printf("Digite a opção: ");
        opcao = ler.nextInt();
        switch (opcao) {
            case 1:
                limparConsole();
                cadastrar();
                break;
            case 2:
                limparConsole();
                listarFuncionarios();
                break;
            default:
                break;
        }

    }

    public static void cadastrar() throws ParseException, IOException{

        Funcionario funcionario = new Funcionario();
        Scanner ler = new Scanner(System.in);

        System.out.println("======== CADASTRAR FUNCIONÁRIO ========");
        System.out.printf("Digite o nome do funcionário: ");

        funcionario.setNome(ler.nextLine());

        System.out.printf("Digite a data de nascimento do funcionário (dd/mm/aaaa): ");
        String dataNasc = ler.next();
        Date dataNascFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dataNasc);
        funcionario.setDataNascimento(dataNascFormatada);

        System.out.printf("Digite o salário do funcionário (0000.00): ");

        funcionario.setSalario(new BigDecimal(ler.next()));
        // BigDecimal é recomendado quando se quer trabalhar com R$

        funcionario.setDataRegistro(new Date(System.currentTimeMillis()));
        funcionario.setAtivo(true);

        FuncionarioDAO funcDAO = new FuncionarioDAO();
        funcDAO.inserir(funcionario);

        limparConsole();
        menuInicial();

    }

    public static void listarFuncionarios() throws ParseException, IOException{

        FuncionarioDAO funcDAO = new FuncionarioDAO();
        ArrayList<Funcionario> listaFuncionarios = funcDAO.listarTodos();
        

        System.out.println("===== LISTA DE FUNCIONÁRIOS =====");
        
        // para cada Funcionario {f} na lista {listaFuncionarios} faça
        for (Funcionario f : listaFuncionarios) {
            System.out.println(
                "> ID: " + f.getId() + 
                "\tNOME: " + f.getNome() + 
                "\tDATA DE NASC.: " + new SimpleDateFormat("dd/MM/yyyy").format(f.getDataNascimento()) +
                "\tSALÁRIO: " + f.getSalario() +
                "\tREGISTRO: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(f.getDataRegistro()) +
                "\tATIVO: " + (f.getAtivo() ? "Sim" : "Não"));

        }

        Scanner ler = new Scanner(System.in);

        System.out.printf("Digite uma tecla para retornar ao menu inicial: ");
        ler.next();
        limparConsole();
        menuInicial();

    }

    public static void limparConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
