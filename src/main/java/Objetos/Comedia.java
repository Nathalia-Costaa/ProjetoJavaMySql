package Objetos;

import Interface.Cinema;

public class Comedia extends Filme implements Cinema {
    @Override
    public void passarCinema() {
        System.out.println("Esta passando no cinema");
    }

    @Override
    public void mostraInfo() {
        System.out.println("Informacoes do filme:");
        System.out.println("ID Filme: " + getIdFilme() );
        System.out.println("Nome do Filme: " + getTitulo());

        if (getCategoria() != null){
            System.out.println("Categoria do Filme: " + getCategoria().getNome_categoria());
        }

        System.out.println("\nInformacoes dos atores:");
        for (Ator ator : getAtores()) {
            ator.mostraInfo();
        }
    }
}
