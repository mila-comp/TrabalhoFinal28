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


    public void salvarLocaisEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Local local : locais) {
                writer.write(local.getCodigo() + ";" + local.getNome() + ";" + local.getCidade());
                writer.newLine();
            }
            System.out.println("Locais salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar locais: " + e.getMessage());
        }
    }

    public void carregarLocaisDeArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    String cidade = partes[2];
                    Local local = new Local(codigo, nome, cidade);
                    locais.add(local);
                }
            }
            System.out.println("Locais carregados com sucesso de " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar locais: " + e.getMessage());
        }
    }


    public void salvarPercursosEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Percurso percurso : percursos) {
                writer.write(percurso.getCodigo() + ";" + percurso.getNome() + ";" +
                        percurso.getDistancia() + ";" + percurso.getCidade());
                writer.newLine();
            }
            System.out.println("Percursos salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar percursos: " + e.getMessage());
        }
    }

    public void carregarPercursosDeArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double distancia = Double.parseDouble(partes[2]);
                    String cidade = partes[3];
                    Percurso percurso = new Percurso(codigo, nome, distancia, cidade);
                    percursos.add(percurso);
                }
            }
            System.out.println("Percursos carregados com sucesso de " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar percursos: " + e.getMessage());
        }
    }


    public void salvarTiposProvaEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (TipoProva tipoProva : tiposProva) {
                writer.write(tipoProva.getNumero() + ";" + tipoProva.getDescricao());
                writer.newLine();
            }
            System.out.println("Tipos de Prova salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tipos de prova: " + e.getMessage());
        }
    }

    public void carregarTiposProvaDeArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int numero = Integer.parseInt(partes[0]);
                    String descricao = partes[1];
                    TipoProva tipoProva = new TipoProva(numero, descricao);
                    tiposProva.add(tipoProva);
                }
            }
            System.out.println("Tipos de Prova carregados com sucesso de " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar tipos de prova: " + e.getMessage());
        }
    }


    public void salvarProvasEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Prova prova : todasProvas) {
                writer.write(prova.getCodigo() + ";" +
                        prova.getTipoProva().getNumero() + ";" +
                        prova.getPercurso().getCodigo() + ";" +
                        prova.getLocal().getCodigo() + ";" +
                        prova.getAtleta().getCodigo() + ";" +
                        prova.getBonus() + ";" +
                        prova.getSituacao() + ";" +
                        (prova.getTempoFinal() == null ? "NULL" : prova.getTempoFinal()));
                writer.newLine();
            }
            System.out.println("Provas salvas com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar provas: " + e.getMessage());
        }
    }

    public void carregarProvasDeArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 8) {
                    int codigo = Integer.parseInt(partes[0]);
                    int tipoProvaNumero = Integer.parseInt(partes[1]);
                    int percursoCodigo = Integer.parseInt(partes[2]);
                    int localCodigo = Integer.parseInt(partes[3]);
                    int atletaCodigo = Integer.parseInt(partes[4]);
                    double bonus = Double.parseDouble(partes[5]);


                    TipoProva tipoProva = buscarTipoProvaPorNumero(tipoProvaNumero);
                    Percurso percurso = buscarPercursoPorCodigo(percursoCodigo);
                    Local local = buscarLocalPorCodigo(localCodigo);
                    Atleta atleta = buscarAtletaPorCodigo(atletaCodigo);

                    if (tipoProva != null && percurso != null && local != null && atleta != null) {
                        Prova prova = new Prova(codigo, tipoProva, percurso, local, atleta, bonus);


                        SituacaoProva situacao = SituacaoProva.valueOf(partes[6]);
                        prova.setSituacao(situacao);


                        if (!partes[7].equals("NULL")) {
                            double tempoFinal = Double.parseDouble(partes[7]);
                            prova.setTempoFinal(tempoFinal);
                        }

                        todasProvas.add(prova);


                        if (situacao == SituacaoProva.INSCRITA) {
                            provasInscritas.add(prova);
                        }
                    }
                }
            }
            System.out.println("Provas carregadas com sucesso de " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar provas: " + e.getMessage());
        }
    }


    public void salvarTodosDados() {
        boolean sucesso = true;

        try {
            salvarAtletasEmArquivo("atletas.txt");
            salvarLocaisEmArquivo("locais.txt");
            salvarPercursosEmArquivo("percursos.txt");
            salvarTiposProvaEmArquivo("tipos_prova.txt");
            salvarProvasEmArquivo("provas.txt");
        } catch (Exception e) {
            sucesso = false;
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }

        if (sucesso) {
            System.out.println("Todos os dados foram salvos com sucesso.");
        }
    }


    public void carregarTodosDados() {
        boolean sucesso = true;

        try {
            carregarAtletasDeArquivo("atletas.txt");
            carregarLocaisDeArquivo("locais.txt");
            carregarPercursosDeArquivo("percursos.txt");
            carregarTiposProvaDeArquivo("tipos_prova.txt");

            carregarProvasDeArquivo("provas.txt");
        } catch (Exception e) {
            sucesso = false;
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        if (sucesso) {
            System.out.println("Todos os dados foram carregados com sucesso.");
        }
    }


    private TipoProva buscarTipoProvaPorNumero(int numero) {
        return tiposProva.stream()
                .filter(tp -> tp.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    private Percurso buscarPercursoPorCodigo(int codigo) {
        return percursos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    private Local buscarLocalPorCodigo(int codigo) {
        return locais.stream()
                .filter(l -> l.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    private Atleta buscarAtletaPorCodigo(int codigo) {
        return atletas.stream()
                .filter(a -> a.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
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