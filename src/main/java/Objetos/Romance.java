package Objetos;

public class Romance extends Filme {
    @Override
    public void mostraInfo() {
        System.out.println("Informacoes do filme:");
        System.out.println("ID Filme: " + idFilme);
        System.out.println("Nome do Filme: " + titulo);

        if (categoria != null){
            System.out.println("Categoria do Filme: " + categoria.getNome_categoria());
        }

        System.out.println("\nInformacoes dos atores:");
        for (Ator ator : atores) {
            ator.mostraInfo();
        }
    }
}
