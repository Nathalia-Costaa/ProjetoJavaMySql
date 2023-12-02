package DAO;

import Objetos.Ator;
import Objetos.Filme;
import Objetos.RelacaoAtorFilme;

import java.sql.SQLException;
import java.util.ArrayList;

public class RelacaoAtorFilmeDAO extends ConnectionDAO {

    //DAO - Data Access Object
    private boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public void insertRelacao(RelacaoAtorFilme relacaoAtorFilme) {
        connectToDB();

        String sql = "INSERT INTO relacaoatorfilme (idAtor, idFilme) values(?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, relacaoAtorFilme.getAtor().idAtor);
            pst.setInt(2, relacaoAtorFilme.getFilme().idFilme);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

    //SELECT
    public ArrayList<RelacaoAtorFilme> selectRelacao() {
        ArrayList<RelacaoAtorFilme> relacoes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM relacaoatorfilme";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                FilmeDAO filmeDAO = new FilmeDAO();
                AtorDAO atorDAO = new AtorDAO();
                Ator ator = atorDAO.selectAtorPorId(rs.getInt("idAtor"));
                Filme filme = filmeDAO.selectFilmPorId(rs.getInt("idFilme"));
                RelacaoAtorFilme relacaoAtorFilme = new RelacaoAtorFilme(ator, filme);

                relacoes.add(relacaoAtorFilme);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return relacoes;
    }

    //DELETE
    public void deleteRelacao(int idFilme) {
        connectToDB();
        String sql = "DELETE FROM relacaoatorfilme where idFilme=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFilme);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }
}
