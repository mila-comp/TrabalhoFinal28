import java.util.*;

public class CadastroTipoProva implements Cadastravel {
    private List<TipoProva> tiposProva;
    private Scanner scanner;

    public CadastroTipoProva() {
        tiposProva = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("----------------- Cadastro de Tipo de Prova -----------------");

        System.out.print("Número: ");
        int numero = Integer.parseInt(scanner.nextLine());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();


        boolean existe = tiposProva.stream()
                .anyMatch(tp -> tp.getNumero() == numero);

        if (existe) {
            System.out.println("Erro: Já existe um tipo de prova com este número.");
            return;
        }

        TipoProva novo = new TipoProva(numero, descricao);
        tiposProva.add(novo);
        tiposProva.sort(Comparator.comparingInt(TipoProva::getNumero));

        System.out.println("Tipo de Prova cadastrado com sucesso.");
    }

    public void listarTiposProva() {
        if (tiposProva.isEmpty()) {
            System.out.println("Não há tipos de prova cadastrados.");
            return;
        }

        System.out.println("----------------- Tipos de Prova Cadastrados -----------------");
        tiposProva.forEach(tp -> System.out.println("Número: " + tp.getNumero() + ", Descrição: " + tp.getDescricao()));
    }

    public TipoProva buscarTipoProvaPorNumero(int numero) {
        return tiposProva.stream()
                .filter(tp -> tp.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public List<TipoProva> getTiposProva() {
        return tiposProva;
    }
}
