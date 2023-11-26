package Objetos;

import java.util.ArrayList;

public abstract class Filme {
    private int idFilme;
    private String titulo;
    private ArrayList<Ator> atores;
    private Categoria categoria;

    public abstract void mostraInfo();

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Ator> getAtores() {
        return atores;
    }

    public void setAtores(ArrayList<Ator> atores) {
        this.atores = atores;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
