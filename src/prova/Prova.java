package src.prova;

import src.local.Local;
import src.percurso.Percurso;
import src.atleta.Atleta;
import src.atleta.Pessoa;
import src.tipo_prova.TipoProva;

public class Prova implements Comparable<Prova> {
    private int codigo;
    private TipoProva tipoProva;
    private Percurso percurso;
    private Local local;
    private Atleta atleta;
    private double bonus;
    private SituacaoProva situacao;
    private double tempoFinal;
    private double tempoMaximo;

    public Prova(int codigo, TipoProva tipoProva, Percurso percurso, Local local, Pessoa atleta, double bonus) {
        this.codigo = codigo;
        this.tipoProva = tipoProva;
        this.percurso = percurso;
        this.local = local;
        this.atleta = (Atleta) atleta;
        this.bonus = bonus;
        this.situacao = SituacaoProva.INSCRITA;
        calcularTempoMaximo();
    }

    private void calcularTempoMaximo() {
        double distancia = percurso.getDistancia();
        switch (tipoProva.getDescricao()) {
            case "5K" -> this.tempoMaximo = (distancia * 5) + bonus;
            case "10K" -> this.tempoMaximo = (distancia * 10) + bonus;
            case "21K" -> this.tempoMaximo = (distancia * 21) + bonus;
            case "Maratona" -> this.tempoMaximo = (distancia * 41) + bonus;
        }
    }

    public double calcularPaceMedio() {
        return tempoFinal / percurso.getDistancia();
    }

    @Override
    public int compareTo(Prova outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoProva getTipoProva() {
        return tipoProva;
    }

    public void setTipoProva(TipoProva tipoProva) {
        this.tipoProva = tipoProva;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public SituacaoProva getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoProva situacao) {
        this.situacao = situacao;
    }

    public Double getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(Double tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public Double getTempoMaximo() {
        return tempoMaximo;
    }

    public void setTempoMaximo(Double tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }
}