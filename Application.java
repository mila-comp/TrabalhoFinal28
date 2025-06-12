import java.util.Scanner;

public class Application {
    public static void main(String args[]){
        CadastroLocal cadastrosLocal = new CadastroLocal();
        CadastroPercurso cadastrosPercurso = new CadastroPercurso();
        CadastroAtleta cadastrosAtleta = new CadastroAtleta();
        Scanner teclado = new Scanner(System.in);

        Pessoa atleta1 = new Atleta(201, "João Silva", "123456789");

        System.out.println("Bem vindo(a) ao site da empresa KSPORTS!!");
        System.out.println("Somos especializados no planejamento de eventos esportivos, como corridas e maratonas");
        System.out.println("Aqui, nesse site, você vai conseguir gerenciar e cadastrar suas corridas! De uma olhada no seguinte menu:");

        int ans;
        do{
            System.out.println("\n---------------------- MENU ----------------------\n");
            System.out.println("1-  Incluir percurso\n2-  Mostrar percursos");
            System.out.println("3-  Incluir local\n4-  Mostrar locais");
            System.out.println("5-  Incluir atleta\n6-  Mostrar atletas");
            System.out.println("11- Sair do programa");

            System.out.println("\nDigite a opção desejada:");
            ans =teclado.nextInt();

            switch(ans){
                case 1 -> cadastrosPercurso.cadastrar();
                case 2 -> cadastrosPercurso.listarPercursos(); //ver
                case 3 -> cadastrosLocal.cadastrar();
                case 4 -> cadastrosLocal.listarLocais();
                case 5 -> cadastrosAtleta.cadastrar();
                case 6 -> cadastrosAtleta.listarAtletas("yes");
                case 11 -> System.out.println("Até a próxima!!");
                default -> System.out.println("\nOpção inválida.");
            }
        }while(ans != 11);

    }
}
