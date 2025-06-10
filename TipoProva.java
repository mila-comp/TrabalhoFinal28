public class TipoProva implements Comparable<TipoProva> {
    private int numero;
    private String descricao;

    public TipoProva(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    @Override
    public int compareTo(TipoProva outro) {
        return Integer.compare(this.numero, outro.numero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}