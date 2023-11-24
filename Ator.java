public class Ator {

    private int idAtor;
    private String nome;

    private String data_nascimento_ator;

    public int getIdAtor() {
        return idAtor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento_ator() {
        return data_nascimento_ator;
    }

    public void setData_nascimento_ator(String data_nascimento_ator) {
        this.data_nascimento_ator = data_nascimento_ator;
    }

    public void mostraInfo(){

        System.out.println("ID Ator: " + getIdAtor());
        System.out.println("Nome do Ator: " + getNome());
        System.out.println("Data de nascimento do Ator: " + getData_nascimento_ator());
        System.out.println("\n------------------");

    }



}
