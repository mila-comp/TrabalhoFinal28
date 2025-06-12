import java.util.*;

public class CadastroProva implements Cadastravel {
    private List<Prova> provas;
    private Queue<Prova> provasInscritas;
    private Scanner scanner;
    private CadastroAtleta cadastroAtleta;
    private CadastroPercurso cadastroPercurso;
    private CadastroLocal cadastroLocal;
    private CadastroTipoProva cadastroTipoProva;

    public CadastroProva(CadastroAtleta cadastroAtleta, CadastroPercurso cadastroPercurso,
                        CadastroLocal cadastroLocal, CadastroTipoProva cadastroTipoProva) {
        this.provas = new ArrayList<>();
        this.provasInscritas = new LinkedList<>();
        this.scanner = new Scanner(System.in);
        this.cadastroAtleta = cadastroAtleta;
        this.cadastroPercurso = cadastroPercurso;
        this.cadastroLocal = cadastroLocal;
        this.cadastroTipoProva = cadastroTipoProva;
    }

    @Override
    public void cadastrar() {
        System.out.println("----------------- Cadastro de Prova -----------------");

        System.out.print("Código da Prova: ");
        int codigo = Integer.parseInt(scanner.nextLine());


        boolean existe = provas.stream()
                .anyMatch(p -> p.getCodigo() == codigo);

        if (existe) {
            System.out.println("Erro: Já existe uma prova com este código.");
            return;
        }

        if (cadastroAtleta.getAtletas().isEmpty()) {
            System.out.println("Erro: Não há atletas cadastrados. Cadastre um atleta antes de criar uma prova.");
            return;
        }

        if (cadastroPercurso.getPercursos().isEmpty()) {
            System.out.println("Erro: Não há percursos cadastrados. Cadastre um percurso antes de criar uma prova.");
            return;
        }

        if (cadastroLocal.getLocais().isEmpty()) {
            System.out.println("Erro: Não há locais cadastrados. Cadastre um local antes de criar uma prova.");
            return;
        }

        if (cadastroTipoProva.getTiposProva().isEmpty()) {
            System.out.println("Erro: Não há tipos de prova cadastrados. Cadastre um tipo de prova antes de criar uma prova.");
            return;
        }


        System.out.println("Tipos de Prova disponíveis:");
        cadastroTipoProva.listarTiposProva();
        System.out.print("Digite o número do Tipo de Prova: ");
        int numeroTipoProva = Integer.parseInt(scanner.nextLine());
        TipoProva tipoProva = cadastroTipoProva.buscarTipoProvaPorNumero(numeroTipoProva);
        if (tipoProva == null) {
            System.out.println("Erro: Tipo de Prova não encontrado.");
            return;
        }


        System.out.println("Percursos disponíveis:");
        cadastroPercurso.listarPercursos();
        System.out.print("Digite o código do Percurso: ");
        int codigoPercurso = Integer.parseInt(scanner.nextLine());
        Percurso percurso = cadastroPercurso.buscarPercursoPorCodigo(codigoPercurso);
        if (percurso == null) {
            System.out.println("Erro: Percurso não encontrado.");
            return;
        }


        System.out.println("Locais disponíveis:");
        cadastroLocal.listarLocais();
        System.out.print("Digite o código do Local: ");
        int codigoLocal = Integer.parseInt(scanner.nextLine());
        Local local = cadastroLocal.buscarLocalPorCodigo(codigoLocal);
        if (local == null) {
            System.out.println("Erro: Local não encontrado.");
            return;
        }


        System.out.println("Atletas disponíveis:");
        cadastroAtleta.listarAtletas("no");
        System.out.print("Digite o código do Atleta: ");
        int codigoAtleta = Integer.parseInt(scanner.nextLine());
        Atleta atleta = cadastroAtleta.buscarAtletaPorCodigo(codigoAtleta);
        if (atleta == null) {
            System.out.println("Erro: Atleta não encontrado.");
            return;
        }

        System.out.print("Digite o bônus de tempo (em minutos): ");
        double bonus = Double.parseDouble(scanner.nextLine());

        Prova novaProva = new Prova(codigo, tipoProva, percurso, local, atleta, bonus);
        provas.add(novaProva);
        provas.sort(Comparator.comparingInt(Prova::getCodigo));
        provasInscritas.add(novaProva);

        System.out.println("\nProva cadastrada com sucesso:");
        System.out.println("Código: " + novaProva.getCodigo());
        System.out.println("Tipo de Prova: " + novaProva.getTipoProva().getDescricao());
        System.out.println("Percurso: " + novaProva.getPercurso().getNome() + " (" + novaProva.getPercurso().getDistancia() + " km)");
        System.out.println("Local: " + novaProva.getLocal().getNome() + " - " + novaProva.getLocal().getCidade());
        System.out.println("Atleta: " + novaProva.getAtleta().getNome());
        System.out.println("Bônus: " + novaProva.getBonus() + " minutos");
        System.out.println("Tempo Máximo: " + novaProva.getTempoMaximo() + " minutos");
        System.out.println("Situação: " + novaProva.getSituacao());
    }

    public void listarProvas() {
        if (provas.isEmpty()) {
            System.out.println("Não há provas cadastradas.");
            return;
        }

        System.out.println("----------------- Provas Cadastradas -----------------");
        for (Prova prova : provas) {
            System.out.println("\nCódigo: " + prova.getCodigo());
            System.out.println("Tipo de Prova: " + prova.getTipoProva().getDescricao());
            System.out.println("Percurso: " + prova.getPercurso().getNome() + " (" + prova.getPercurso().getDistancia() + " km)");
            System.out.println("Local: " + prova.getLocal().getNome() + " - " + prova.getLocal().getCidade());
            System.out.println("Atleta: " + prova.getAtleta().getNome());
            System.out.println("Bônus: " + prova.getBonus() + " minutos");
            System.out.println("Tempo Máximo: " + prova.getTempoMaximo() + " minutos");
            System.out.println("Situação: " + prova.getSituacao());
            if (prova.getSituacao() == SituacaoProva.FINALIZADA && prova.getTempoFinal() != null) {
                System.out.println("Tempo Final: " + prova.getTempoFinal() + " minutos");
                System.out.println("Pace Médio: " + prova.calcularPaceMedio() + " min/km");
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public void alterarSituacaoProva() {
        if (provas.isEmpty()) {
            System.out.println("Não há provas cadastradas.");
            return;
        }

        System.out.println("----------------- Alterar Situação de Prova -----------------");
        System.out.print("Digite o código da Prova: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Prova prova = buscarProvaPorCodigo(codigo);
        if (prova == null) {
            System.out.println("Erro: Prova não encontrada.");
            return;
        }

        if (prova.getSituacao() == SituacaoProva.FINALIZADA) {
            System.out.println("Erro: Não é possível alterar a situação de uma prova finalizada.");
            return;
        }

        System.out.println("\nDados da Prova selecionada:");
        System.out.println("Código: " + prova.getCodigo());
        System.out.println("Tipo: " + prova.getTipoProva().getDescricao());
        System.out.println("Atleta: " + prova.getAtleta().getNome());
        System.out.println("Situação atual: " + prova.getSituacao());

        System.out.println("\nNovas situações possíveis:");
        System.out.println("1 - INSCRITA");
        System.out.println("2 - EM_ANDAMENTO");
        System.out.println("3 - CANCELADA");
        System.out.print("Digite o número da nova situação: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        SituacaoProva novaSituacao;
        switch (opcao) {
            case 1 -> novaSituacao = SituacaoProva.INSCRITA;
            case 2 -> novaSituacao = SituacaoProva.EM_ANDAMENTO;
            case 3 -> novaSituacao = SituacaoProva.CANCELADA;
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        prova.setSituacao(novaSituacao);
        System.out.println("Situação da prova alterada com sucesso para: " + novaSituacao);
    }

    public void registrarTempoFinal() {
        if (provas.isEmpty()) {
            System.out.println("Não há provas cadastradas.");
            return;
        }

        System.out.println("----------------- Registrar Tempo Final -----------------");
        System.out.print("Digite o código da Prova: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        Prova prova = buscarProvaPorCodigo(codigo);
        if (prova == null) {
            System.out.println("Erro: Prova não encontrada.");
            return;
        }

        if (prova.getSituacao() != SituacaoProva.EM_ANDAMENTO) {
            System.out.println("Erro: Só é possível registrar tempo final para provas em andamento.");
            return;
        }

        System.out.println("\nDados da Prova selecionada:");
        System.out.println("Código: " + prova.getCodigo());
        System.out.println("Tipo: " + prova.getTipoProva().getDescricao());
        System.out.println("Atleta: " + prova.getAtleta().getNome());
        System.out.println("Tempo Máximo: " + prova.getTempoMaximo() + " minutos");

        System.out.print("Digite o tempo final (em minutos): ");
        double tempoFinal = Double.parseDouble(scanner.nextLine());

        prova.setTempoFinal(tempoFinal);
        prova.setSituacao(SituacaoProva.FINALIZADA);

        double paceMedio = prova.calcularPaceMedio();

        System.out.println("\nTempo final registrado com sucesso:");
        System.out.println("Tempo Final: " + tempoFinal + " minutos");
        System.out.println("Pace Médio: " + paceMedio + " min/km");
        System.out.println("Situação atualizada para: FINALIZADA");
    }

    public void organizarProvas() {
        if (provasInscritas.isEmpty()) {
            System.out.println("Não há provas inscritas na fila para organizar.");
            return;
        }

        System.out.println("----------------- Organizando Provas -----------------");
        System.out.println("Provas na fila para organização: " + provasInscritas.size());

        int iniciadas = 0;
        int canceladas = 0;

        List<Prova> provasProcessadas = new ArrayList<>();


        Set<Atleta> atletasEmUso = new HashSet<>();
        Set<Percurso> percursosEmUso = new HashSet<>();


        for (Prova prova : provas) {
            if (prova.getSituacao() == SituacaoProva.EM_ANDAMENTO) {
                atletasEmUso.add(prova.getAtleta());
                percursosEmUso.add(prova.getPercurso());
            }
        }

        while (!provasInscritas.isEmpty()) {
            Prova prova = provasInscritas.poll();
            provasProcessadas.add(prova);


            boolean atletaDisponivel = !atletasEmUso.contains(prova.getAtleta());
            boolean percursoDisponivel = !percursosEmUso.contains(prova.getPercurso());

            if (atletaDisponivel && percursoDisponivel && prova.getSituacao() == SituacaoProva.INSCRITA) {
                prova.setSituacao(SituacaoProva.EM_ANDAMENTO);
                atletasEmUso.add(prova.getAtleta());
                percursosEmUso.add(prova.getPercurso());
                iniciadas++;
                System.out.println("Prova " + prova.getCodigo() + " iniciada (EM_ANDAMENTO).");
            } else if (prova.getSituacao() == SituacaoProva.INSCRITA) {
                prova.setSituacao(SituacaoProva.CANCELADA);
                canceladas++;
                System.out.println("Prova " + prova.getCodigo() + " cancelada (CANCELADA). Motivo: " +
                                  (!atletaDisponivel ? "Atleta indisponível" : "Percurso indisponível"));
            }
        }

        System.out.println("\nOrganização de provas concluída:");
        System.out.println("Total de provas processadas: " + provasProcessadas.size());
        System.out.println("Provas iniciadas: " + iniciadas);
        System.out.println("Provas canceladas: " + canceladas);
    }

    public Prova buscarProvaPorCodigo(int codigo) {
        return provas.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public Queue<Prova> getProvasInscritas() {
        return provasInscritas;
    }

    public void listarAtletas() {
        cadastroAtleta.listarAtletas("yes");
    }
}
