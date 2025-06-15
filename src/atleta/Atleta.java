package src.atleta;

public class Atleta extends Pessoa implements Comparable<Atleta> {

    public Atleta(int codigo, String nome, String telefone) {
        super(codigo, nome, telefone);
    }

    @Override
    public int compareTo(Atleta outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }

    public String toString() {
        return "Atleta: \n" +
                " codigo: " + codigo +
                "\n  nome: " + nome +
                "\n   telefone: " + telefone;
    }
}