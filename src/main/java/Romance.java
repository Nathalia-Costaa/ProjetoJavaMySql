public class Romance extends Filme {
    @Override
    public void mostraInfo() {
        System.out.println("ID Filme: " + getIdFilme() );
        System.out.println("Nome do Filme: " + getTitulo());

        if (getAtor() != null) {
            System.out.println("Nome do Ator: " + getAtor().getNome());
            System.out.println("Data de nascimento do ator: " + getAtor().getData_nascimento_ator());
        }

        if (getPremio() != null){
            System.out.println("Premio do Ator: " + getPremio().getTipo());
        }

        if (getCategoria() != null){
            System.out.println("Categoria do Filme: " + getCategoria().getNome_categoria());
        }
    }
}
