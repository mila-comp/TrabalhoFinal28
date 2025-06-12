public class Percurso implements Comparable<Percurso> {
    private int codigo;
    private String nome;
    private double distancia;
    private String cidade;

    public Percurso(int codigo, String nome, double distancia, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.distancia = distancia;
        this.cidade = cidade;
    }

    @Override
    public int compareTo(Percurso outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String toString() {
        return "Percurso \n" +
                " codigo: " + codigo +
                "\n  nome: " + nome +
                "\n  distância: " + distancia +
                "\n   cidade: " + cidade;
    }
}