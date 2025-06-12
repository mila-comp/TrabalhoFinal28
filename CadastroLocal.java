import java.util.*;

public class CadastroLocal implements Cadastravel {
    private List<Local> locais;
    private Scanner scanner;

    public CadastroLocal() {
        locais = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("----------------- Cadastro de Local -----------------");

        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        // Verifica duplicidade por nome
        boolean existe = locais.stream()
                .anyMatch(l -> l.getNome().equalsIgnoreCase(nome));

        if (existe) {
            System.out.println("Erro: Já existe um local com este nome.");
            return;
        }

        Local novo = new Local(codigo, nome, cidade);
        locais.add(novo);
        locais.sort(Comparator.comparing(Local::getNome));

        System.out.println("Local cadastrado com sucesso.");
    }

    public void listarLocais() {
        locais.forEach(System.out::println);
    }

    //talvez não seja necessário
    public Local buscarLocalPorCodigo(int codigo) {
        return locais.stream()
                .filter(l -> l.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Local> getLocais() {
        return locais;
    }
}
