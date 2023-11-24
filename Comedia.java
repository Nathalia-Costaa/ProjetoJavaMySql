public class Comedia extends Filme implements Cinema{


    public Comedia(int idFilme, String titulo) {
        super(idFilme, titulo);
    }

    public void fazerRir(){

        System.out.println("Fazendo rir");
    }


    @Override
    public void passarCinema() {

        System.out.println("Filme passando no cinema");

    }

    @Override
    public void mostraInfo() {

        System.out.println("ID Filme: " + getIdFilme() );
        System.out.println("Nome do Filme: " + getTitulo());

    }

    @Override
    public void addFilme() {


    }


}
