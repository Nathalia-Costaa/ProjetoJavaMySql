import DAO.AtorDAO;
import DAO.RelacaoAtorFilmeDAO;
import DAO.FilmeDAO;
import DAO.PremioDAO;
import Interface.Cinema;
import Objetos.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FilmeDAO filmeDAO = new FilmeDAO();
        AtorDAO atorDAO = new AtorDAO();
        PremioDAO premioDAO = new PremioDAO();
        RelacaoAtorFilmeDAO relacaoDAO = new RelacaoAtorFilmeDAO();
        Scanner input = new Scanner(System.in);
        Filme filme;

        boolean flag = true;

        System.out.println("");

        while(flag) {
            System.out.println("\nEscolha sua opcao e lembre-se de inserir as informacoes com letras minusculas!");
            System.out.println("1 - cadastrar Ator");
            System.out.println("2 - mostrar informacoes dos atores");
            System.out.println("3 - adicionar Filme");
            System.out.println("4 - mostrar informacoes dos filmes");
            System.out.println("5 - Atualizar informacoes de um filme");
            System.out.println("6 - Deletar filme");

            String opStr = input.nextLine();
            int op;
            try {
                op = Integer.parseInt(opStr);
            } catch (NumberFormatException e) {
                System.out.println("Insira um numero valido!");
                continue;
            }

            switch(op){
                case 1:
                    System.out.println("Digite o nome do ator: ");
                    String nome = input.nextLine();
                    System.out.println("Digite a data de nascimento do ator: ");
                    String nasc = input.nextLine();
                    System.out.println("Digite o premio do ator: ");
                    String nomePremio = input.nextLine();

                    Ator ator = new Ator(nome, nasc);
                    Premio premio = new Premio(nomePremio);
                    int id = premioDAO.insertPremio(premio);
                    premio.setIdPremio(id);
                    ator.premio = premio;
                    atorDAO.insertAtor(ator);

                    break;

                case 2:
                    for (Ator ator1 : atorDAO.selectAtor()) {
                        System.out.println("---------------");
                        ator1.mostraInfo();
                    }
                    System.out.println("---------------");

                    break;

                case 3:
                    filme = generateFilm(input);
                    if (filme == null) {
                        break;
                    }

                    id = filmeDAO.insertFilme(filme);

                    for (Ator ator1 : filme.atores) {
                        filme.idFilme = id;
                        RelacaoAtorFilme relacaoAtorFilme = new RelacaoAtorFilme(ator1, filme);
                        relacaoDAO.insertRelacao(relacaoAtorFilme);
                    }

                    break;

                case 4:
                    ArrayList<Filme> filmes = filmeDAO.selectFilme();
                    ArrayList<Ator> atores = atorDAO.selectAtor();
                    ArrayList<RelacaoAtorFilme> relacoes = relacaoDAO.selectRelacao();

                    // Relaciona os filmes com o respectivo ator com base nas relacoes
                    for (Filme filme1 : filmes) {
                        ArrayList<Ator> atores1 = new ArrayList<>();
                        for (RelacaoAtorFilme relacao : relacoes) {
                            if (filme1.idFilme == relacao.getFilme().idFilme) {
                                for (Ator ator1 : atores) {
                                    if (ator1.idAtor == relacao.getAtor().idAtor) {
                                        atores1.add((ator1));
                                        break;
                                    }
                                }
                            }
                        }
                        filme1.atores = atores1;
                    }

                    for (Filme filme1 : filmes) {
                        System.out.println("---------------");

                        if (filme1 != null) {
                            filme1.mostraInfo();
                        } else {
                            System.out.println("Nenhum filme adicionado ainda.");
                            break;
                        }

                        System.out.println("");
                        if (filme1 instanceof Cinema) {
                            ((Cinema) filme1).passarCinema();
                        } else {
                            System.out.println("Nao esta passando no cinema");
                        }
                    }

                    System.out.println("---------------");

                    break;

                case 5:
                    System.out.println("Digite o id do filme a ser atualizado: ");
                    String idStr = input.nextLine();
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um numero valido!");
                        break;
                    }

                    filme = generateFilm(input);
                    if (filme == null) {
                        break;
                    }

                    // Refaz as relações
                    relacaoDAO.deleteRelacao(id);
                    for (Ator ator1 : filme.atores) {
                        filme.idFilme = id;
                        RelacaoAtorFilme relacaoAtorFilme = new RelacaoAtorFilme(ator1, filme);
                        relacaoDAO.insertRelacao(relacaoAtorFilme);
                    }
                    filmeDAO.updateFilme(id, filme);

                    break;

                case 6:
                    System.out.println("Digite o id do filme a ser deletado: ");
                    idStr = input.nextLine();
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um numero valido!");
                        break;
                    }

                    relacaoDAO.deleteRelacao(id);
                    filmeDAO.deleteFilme(id);
                    break;

                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static Filme generateFilm(Scanner input) {
        System.out.println("Digite o titulo do filme: ");
        String titulo = input.nextLine();
        System.out.println("Digite a categoria do filme (romance ou comedia)");
        String nomeCategoriaCategoria = input.nextLine();
        System.out.println("Digite os ids dos atores ja cadastrados separados por virgula: ");
        String idAtores = input.nextLine().replace(" ", "");

        Filme filme;
        if (Objects.equals(nomeCategoriaCategoria, "comedia")) {
            filme = new Comedia();
        } else if (Objects.equals(nomeCategoriaCategoria, "romance")) {
            filme = new Romance();
        } else {
            System.out.println("Categoria invalida!");
            return null;
        }

        ArrayList<Ator> atores = new ArrayList<>();
        for (String idAtor : idAtores.split(",")) {
            int id;
            try {
                id = Integer.parseInt(idAtor);
            } catch (NumberFormatException e) {
                System.out.println("Insira um id valido!");
                return null;
            }

            Ator ator = new Ator();
            ator.idAtor = id;

            atores.add(ator);
        }

        Categoria categoria = new Categoria(nomeCategoriaCategoria);

        filme.titulo = titulo;
        filme.atores = atores;
        filme.categoria = categoria;

        return filme;
    }
}
