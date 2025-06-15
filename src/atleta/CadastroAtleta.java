package src.atleta;

import src.Cadastravel;

import java.util.*;

public class CadastroAtleta implements Cadastravel {
    private List<Atleta> atletas;
    private Scanner scanner;

    public CadastroAtleta() {
        atletas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("----------------- Cadastro de Atleta -----------------");

        System.out.print("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        boolean existe = atletas.stream()
                .anyMatch(a -> a.getCodigo() == codigo);

        if (existe) {
            System.out.println("Erro: Já existe um atleta com este código.");
            return;
        }

        Atleta novo = new Atleta(codigo, nome, telefone);
        atletas.add(novo);
        atletas.sort(Comparator.comparingInt(Atleta::getCodigo));

        System.out.println("Atleta cadastrado com sucesso.");
    }

    public Atleta buscarAtletaPorCodigo(int codigo) {
        return atletas.stream()
                .filter(a -> a.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }


    public void listarAtletas(String detalhado) {
        if (atletas.isEmpty()) {
            System.out.println("Não há atletas cadastrados.");
            return;
        }

        System.out.println("----------------- Atletas Cadastrados -----------------");
        for (Atleta atleta : atletas) {
            if (detalhado.equalsIgnoreCase("yes")) {
                System.out.println(atleta); // Usa o toString() completo
            } else {
                System.out.println("Código: " + atleta.getCodigo() + ", Nome: " + atleta.getNome());
            }
        }
    }
}
