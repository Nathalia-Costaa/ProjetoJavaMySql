public abstract class Filme{

    private int idFilme;
    private String titulo;

    public Filme(int idFilme, String titulo) {
        this.idFilme = idFilme;
        this.titulo = titulo;
    }


    public int getIdFilme() {
        return idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public abstract void mostraInfo();

    public abstract void addFilme();



}
