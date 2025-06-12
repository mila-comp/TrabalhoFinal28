import java.util.Scanner;

public class Application {
    public static void main(String args[]) {
        CadastroLocal cadastroLocal = new CadastroLocal();
        CadastroPercurso cadastroPercurso = new CadastroPercurso();
        CadastroAtleta cadastroAtleta = new CadastroAtleta();
        CadastroTipoProva cadastroTipoProva = new CadastroTipoProva();
        CadastroProva cadastroProva = new CadastroProva(cadastroAtleta, cadastroPercurso, cadastroLocal, cadastroTipoProva);
        GerenciadorDados gerenciadorDados = new GerenciadorDados();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bem vindo(a) ao site da empresa KSPORTS!!");
        System.out.println("Somos especializados no planejamento de eventos esportivos, como corridas e maratonas");
        System.out.println("Aqui, nesse site, você vai conseguir gerenciar e cadastrar suas corridas! De uma olhada no seguinte menu:");

        int opcao;
        do {
            System.out.println("\n---------------------- MENU ----------------------\n");
            System.out.println("1-  Cadastrar novo percurso");
            System.out.println("2-  Cadastrar novo local");
            System.out.println("3-  Cadastrar novo atleta");
            System.out.println("4-  Cadastrar novo tipo de prova");
            System.out.println("5-  Cadastrar nova prova");
            System.out.println("6-  Consultar todas as provas");
            System.out.println("7-  Alterar a situação de uma prova");
            System.out.println("8-  Organizar provas");
            System.out.println("9-  Registrar tempo final da prova");
            System.out.println("10- Carregar dados iniciais");
            System.out.println("11- Salvar dados");
            System.out.println("12- Carregar dados");
            System.out.println("13- Listar percursos");
            System.out.println("14- Listar locais");
            System.out.println("15- Listar atletas");
            System.out.println("16- Listar tipos de prova");
            System.out.println("0-  Finalizar sistema");

            System.out.print("\nDigite a opção desejada: ");
            opcao = teclado.nextInt();
            teclado.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> cadastroPercurso.cadastrar();
                case 2 -> cadastroLocal.cadastrar();
                case 3 -> cadastroAtleta.cadastrar();
                case 4 -> cadastroTipoProva.cadastrar();
                case 5 -> cadastroProva.cadastrar();
                case 6 -> cadastroProva.listarProvas();
                case 7 -> cadastroProva.alterarSituacaoProva();
                case 8 -> cadastroProva.organizarProvas();
                case 9 -> cadastroProva.registrarTempoFinal();
                case 10 -> {
                    System.out.println("Carregando dados iniciais...");
                    // Criar alguns dados iniciais para teste
                    criarDadosIniciais(cadastroAtleta, cadastroLocal, cadastroPercurso, cadastroTipoProva, cadastroProva);
                    System.out.println("Dados iniciais carregados com sucesso!");
                }
                case 11 -> {
                    System.out.println("Salvando dados...");
                    // Transferir dados dos cadastros para o gerenciador
                    transferirDadosParaGerenciador(gerenciadorDados, cadastroAtleta, cadastroLocal,
                                                  cadastroPercurso, cadastroTipoProva, cadastroProva);
                    gerenciadorDados.salvarTodosDados();
                }
                case 12 -> {
                    System.out.println("Carregando dados de arquivos...");
                    gerenciadorDados.carregarTodosDados();
                    // Transferir dados do gerenciador para os cadastros
                    transferirDadosParaCadastros(gerenciadorDados, cadastroAtleta, cadastroLocal,
                                                cadastroPercurso, cadastroTipoProva, cadastroProva);
                    System.out.println("Dados carregados com sucesso!");
                }
                case 13 -> cadastroPercurso.listarPercursos();
                case 14 -> cadastroLocal.listarLocais();
                case 15 -> cadastroAtleta.listarAtletas("yes");
                case 16 -> cadastroTipoProva.listarTiposProva();
                case 0 -> System.out.println("Finalizando o sistema. Até a próxima!");
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para criar dados iniciais para teste
    private static void criarDadosIniciais(CadastroAtleta cadastroAtleta, CadastroLocal cadastroLocal,
                                         CadastroPercurso cadastroPercurso, CadastroTipoProva cadastroTipoProva,
                                         CadastroProva cadastroProva) {
        // Criar atletas
        Atleta atleta1 = new Atleta(101, "João Silva", "123456789");
        Atleta atleta2 = new Atleta(102, "Maria Oliveira", "987654321");
        Atleta atleta3 = new Atleta(103, "Pedro Santos", "555123456");

        cadastroAtleta.getAtletas().add(atleta1);
        cadastroAtleta.getAtletas().add(atleta2);
        cadastroAtleta.getAtletas().add(atleta3);


        Local local1 = new Local(201, "Parque Central", "São Paulo");
        Local local2 = new Local(202, "Praia de Copacabana", "Rio de Janeiro");
        Local local3 = new Local(203, "Parque Ibirapuera", "São Paulo");

        cadastroLocal.getLocais().add(local1);
        cadastroLocal.getLocais().add(local2);
        cadastroLocal.getLocais().add(local3);


        Percurso percurso1 = new Percurso(301, "Volta do Parque", 5.0, "São Paulo");
        Percurso percurso2 = new Percurso(302, "Orla da Praia", 10.0, "Rio de Janeiro");
        Percurso percurso3 = new Percurso(303, "Circuito Ibirapuera", 21.0, "São Paulo");

        cadastroPercurso.getPercursos().add(percurso1);
        cadastroPercurso.getPercursos().add(percurso2);
        cadastroPercurso.getPercursos().add(percurso3);


        TipoProva tipoProva1 = new TipoProva(401, "5K");
        TipoProva tipoProva2 = new TipoProva(402, "10K");
        TipoProva tipoProva3 = new TipoProva(403, "21K");
        TipoProva tipoProva4 = new TipoProva(404, "Maratona");

        cadastroTipoProva.getTiposProva().add(tipoProva1);
        cadastroTipoProva.getTiposProva().add(tipoProva2);
        cadastroTipoProva.getTiposProva().add(tipoProva3);
        cadastroTipoProva.getTiposProva().add(tipoProva4);


        Prova prova1 = new Prova(501, tipoProva1, percurso1, local1, atleta1, 0.5);
        Prova prova2 = new Prova(502, tipoProva2, percurso2, local2, atleta2, 1.0);
        Prova prova3 = new Prova(503, tipoProva3, percurso3, local3, atleta3, 2.0);

        cadastroProva.getProvas().add(prova1);
        cadastroProva.getProvas().add(prova2);
        cadastroProva.getProvas().add(prova3);

        cadastroProva.getProvasInscritas().add(prova1);
        cadastroProva.getProvasInscritas().add(prova2);
        cadastroProva.getProvasInscritas().add(prova3);

        System.out.println("Dados iniciais criados:");
        System.out.println("- 3 Atletas");
        System.out.println("- 3 Locais");
        System.out.println("- 3 Percursos");
        System.out.println("- 4 Tipos de Prova");
        System.out.println("- 3 Provas (todas na situação INSCRITA)");
    }

    private static void transferirDadosParaGerenciador(GerenciadorDados gerenciador,
                                                     CadastroAtleta cadastroAtleta,
                                                     CadastroLocal cadastroLocal,
                                                     CadastroPercurso cadastroPercurso,
                                                     CadastroTipoProva cadastroTipoProva,
                                                     CadastroProva cadastroProva) {

        gerenciador.getAtletas().clear();
        gerenciador.getLocais().clear();
        gerenciador.getPercursos().clear();
        gerenciador.getTiposProva().clear();
        gerenciador.getTodasProvas().clear();
        gerenciador.getProvasInscritas().clear();

        for (Atleta atleta : cadastroAtleta.getAtletas()) {
            gerenciador.getAtletas().add(atleta);
        }

        for (Local local : cadastroLocal.getLocais()) {
            gerenciador.getLocais().add(local);
        }


        for (Percurso percurso : cadastroPercurso.getPercursos()) {
            gerenciador.getPercursos().add(percurso);
        }


        for (TipoProva tipoProva : cadastroTipoProva.getTiposProva()) {
            gerenciador.getTiposProva().add(tipoProva);
        }


        for (Prova prova : cadastroProva.getProvas()) {
            gerenciador.getTodasProvas().add(prova);


            if (prova.getSituacao() == SituacaoProva.INSCRITA) {
                gerenciador.getProvasInscritas().add(prova);
            }
        }
    }


    private static void transferirDadosParaCadastros(GerenciadorDados gerenciador,
                                                    CadastroAtleta cadastroAtleta,
                                                    CadastroLocal cadastroLocal,
                                                    CadastroPercurso cadastroPercurso,
                                                    CadastroTipoProva cadastroTipoProva,
                                                    CadastroProva cadastroProva) {

        cadastroAtleta.getAtletas().clear();
        cadastroLocal.getLocais().clear();
        cadastroPercurso.getPercursos().clear();
        cadastroTipoProva.getTiposProva().clear();
        cadastroProva.getProvas().clear();
        cadastroProva.getProvasInscritas().clear();


        for (Atleta atleta : gerenciador.getAtletas()) {
            cadastroAtleta.getAtletas().add(atleta);
        }


        for (Local local : gerenciador.getLocais()) {
            cadastroLocal.getLocais().add(local);
        }


        for (Percurso percurso : gerenciador.getPercursos()) {
            cadastroPercurso.getPercursos().add(percurso);
        }


        for (TipoProva tipoProva : gerenciador.getTiposProva()) {
            cadastroTipoProva.getTiposProva().add(tipoProva);
        }

        for (Prova prova : gerenciador.getTodasProvas()) {
            cadastroProva.getProvas().add(prova);


            if (prova.getSituacao() == SituacaoProva.INSCRITA) {
                cadastroProva.getProvasInscritas().add(prova);
            }
        }
    }
}
