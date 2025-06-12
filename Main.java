public class Main {

    public static void main(String[] args) {
        // Exemplo de uso das classes Local, Percurso, Atleta e TipoProva

        Local local1 = new Local(1, "Parque Central", "Cidade A");
        Local local2 = new Local(2, "Praça da Liberdade", "Cidade B");

        Percurso percurso1 = new Percurso(101, "Percurso 1", 5.0, "Cidade A");
        Percurso percurso2 = new Percurso(102, "Percurso 2", 10.0, "Cidade B");

        Pessoa atleta1 = new Atleta(201, "João Silva", "123456789");
        Pessoa atleta2 = new Atleta(202, "Maria Oliveira", "987654321");

        TipoProva tipoProva1 = new TipoProva(301, "Corrida de 5km");
        TipoProva tipoProva2 = new TipoProva(302, "Corrida de 10km");

        System.out.println("Local: " + local1.getNome() + ", Cidade: " + local1.getCidade());
        System.out.println("Percurso: " + percurso1.getNome() + ", Distância: " + percurso1.getDistancia() + " km");
        System.out.println("Atleta: " + atleta1.getNome() + ", Telefone: " + atleta1.getTelefone());
        System.out.println("Tipo de Prova: " + tipoProva1.getDescricao());

        Prova prova1 = new Prova(401, tipoProva1, percurso1, local1, atleta1, 0.5);
        System.out.println("Prova criada: " + prova1.getTipoProva().getDescricao() +
                ", Local: " + prova1.getLocal().getNome() +
                ", Atleta: " + prova1.getAtleta().getNome() +
                ", Tempo Máximo: " + prova1.getTempoMaximo() + " minutos");

        GerenciadorDados gerenciador = new GerenciadorDados();

        gerenciador.getAtletas().add((Atleta) atleta1);
        gerenciador.getAtletas().add((Atleta) atleta2);
        gerenciador.salvarAtletasEmArquivo("atletas.txt");
        gerenciador.carregarAtletasDeArquivo("atletas.txt");
    }
}