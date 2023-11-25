import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FilmeDAO filmeDAO = new FilmeDAO();
        Scanner input = new Scanner(System.in);
        Filme filme;

        boolean flag = true;

        System.out.println("");

        while(flag) {
            System.out.println("Lembre de inserir as informacoes com letras minusculas!");
            System.out.println("Escolha sua opcao");
            System.out.println("1 - adicionar Filme");
            System.out.println("2 - mostrar informacoes dos filmes");
            System.out.println("3 - Atualizar informacoes de um filme");
            System.out.println("4 - Deletar filme");

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
                    filme = generateFilm(input);
                    if (filme == null) {
                        break;
                    }

                    filmeDAO.insertFilme(filme);

                    break;

                case 2:
                    for (Filme filme1 : filmeDAO.selectFilme()) {
                        System.out.println("---------------");

                        if (filme1 != null) {
                            filme1.mostraInfo();
                        } else {
                            System.out.println("Nenhum filme adicionado ainda.");
                        }

                        if (filme1 instanceof Cinema) {
                            ((Cinema) filme1).passarCinema();
                        } else {
                            System.out.println("Nao esta passando no cinema");
                        }
                    }

                    System.out.println("---------------");

                    break;

                case 3:
                    System.out.println("Digite o id do filme a ser atualizado: ");
                    String idStr = input.nextLine();
                    int id;
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

                    filmeDAO.updateFilme(id, filme);

                    break;

                case 4:
                    System.out.println("Digite o id do filme a ser atualizado: ");
                    idStr = input.nextLine();
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um numero valido!");
                        break;
                    }

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
        System.out.println("Digite a categoria do filme");
        String nome_categoriaCategoria = input.nextLine();
        System.out.println("Digite o nome do ator: ");
        String nomeAtor = input.nextLine();
        System.out.println("Digite a data de nascimento do ator: ");
        String data_nascimento_ator = input.nextLine();
        System.out.println("Digite os premios que esse ator possui");
        String tipoPremio = input.nextLine();

        Filme filme;
        if (Objects.equals(nome_categoriaCategoria, "comedia")) {
            filme = new Comedia();
        } else if (Objects.equals(nome_categoriaCategoria, "romance")) {
            filme = new Romance();
        } else {
            System.out.println("Categoria invalida!");
            return null;
        }

        Ator ator = new Ator(nomeAtor, data_nascimento_ator);
        Categoria categoria = new Categoria(nome_categoriaCategoria);
        Premio premio = new Premio (tipoPremio);

        filme.setTitulo(titulo);
        filme.setAtor(ator);
        filme.setCategoria(categoria);
        filme.setPremio(premio);

        return filme;
    }
}
