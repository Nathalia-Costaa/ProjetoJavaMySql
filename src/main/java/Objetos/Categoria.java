package Objetos;

public class Categoria {
    private int idCategoria;
    private String nome_categoria;

    public Categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public void mostraInfo(){
        System.out.println("ID Premio: " + getIdCategoria());
        System.out.println("Nome da Categoria do Filme: " + getNome_categoria());
        System.out.println("\n------------------");
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
