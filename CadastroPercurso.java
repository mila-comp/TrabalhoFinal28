import java.util.*;

public class CadastroPercurso implements Cadastravel {
    private List<Percurso> percursos;
    private Scanner scanner;

    public CadastroPercurso() {
        percursos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("----------------- Cadastro de Percurso -----------------");

        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Distância (km): ");
        double distancia = Double.parseDouble(scanner.nextLine());

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        // Verifica duplicidade por código
        boolean existe = percursos.stream()
                .anyMatch(p -> p.getCodigo() == codigo);

        if (existe) {
            System.out.println("Erro: Já existe um percurso com este código.");
            return;
        }

        Percurso novo = new Percurso(codigo, nome, distancia, cidade);
        percursos.add(novo);
        percursos.sort(Comparator.comparingInt(Percurso::getCodigo));

        System.out.println("Percurso cadastrado com sucesso.");
    }

    public void listarPercursos() {
        percursos.forEach(System.out::println);
    }

    //talvez não seja necessário
    public Percurso buscarPercursoPorCodigo(int codigo) {
        return percursos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Percurso> getPercursos() {
        return percursos;
    }
}
