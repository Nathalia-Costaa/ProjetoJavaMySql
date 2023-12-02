package Objetos;

public class Ator {
    public int idAtor;
    public String nome;
    public String dataNascimentoAtor;
    public Premio premio;

    public Ator(String nome, String dataNascimentoAtor) {
        this.nome = nome;
        this.dataNascimentoAtor = dataNascimentoAtor;
    }

    public Ator() {

    }

    public void mostraInfo(){
        System.out.println("ID Ator: " + idAtor);
        System.out.println("Nome do Ator: " + nome);
        System.out.println("Data de nascimento do Ator: " + dataNascimentoAtor);
        System.out.println("Premio do Ator: " + premio.getTipo());
    }
}
