package Objetos;

import java.util.ArrayList;

public abstract class Filme {
    public int idFilme;
    public String titulo;
    public ArrayList<Ator> atores;
    public Categoria categoria;

    public abstract void mostraInfo();
}
