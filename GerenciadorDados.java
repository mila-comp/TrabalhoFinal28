import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class GerenciadorDados {
    private final TreeSet<Percurso> percursos = new TreeSet<>();
    private final TreeSet<Local> locais = new TreeSet<>();
    private final TreeSet<Atleta> atletas = new TreeSet<>();
    private final TreeSet<TipoProva> tiposProva = new TreeSet<>();
    private final TreeSet<Prova> todasProvas = new TreeSet<>();
    private final Queue<Prova> provasInscritas = new LinkedList<>();

    public void salvarAtletasEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Atleta atleta : atletas) {
                writer.write(atleta.getCodigo() + ";" + atleta.getNome() + ";" + atleta.getTelefone());
                writer.newLine();
            }
            System.out.println("Atletas salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar atletas: " + e.getMessage());
        }
    }

    public void carregarAtletasDeArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String telefone = partes[2];
                    Atleta atleta = new Atleta(codigo, nome, telefone);
                    atletas.add(atleta);
                }
            }
            System.out.println("Atletas carregados com sucesso de " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar atletas: " + e.getMessage());
        }
    }
    public TreeSet<Percurso> getPercursos() {
        return percursos;
    }
    public TreeSet<Local> getLocais() {
        return locais;
    }
    public TreeSet<Atleta> getAtletas() {
        return atletas;
    }
    public TreeSet<TipoProva> getTiposProva() {
        return tiposProva;
    }
    public TreeSet<Prova> getTodasProvas() {
        return todasProvas;
    }
    public Queue<Prova> getProvasInscritas() {
        return provasInscritas;
    }

}
