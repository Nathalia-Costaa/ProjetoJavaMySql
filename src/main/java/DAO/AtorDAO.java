package DAO;

import Objetos.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AtorDAO extends ConnectionDAO {

    //DAO - Data Access Object
    private boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public void insertAtor(Ator ator) {
        connectToDB();

        String sql = "INSERT INTO ator (nome_ator, data_nascimento_ator, idPremio) values(?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ator.nome);
            pst.setString(2, ator.dataNascimentoAtor);
            pst.setInt(3, ator.premio.getIdPremio());
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

    //UPDATE
    public boolean updateAtor(int id, Ator ator) {
        connectToDB();
        String sql = "UPDATE ator SET nome_ator=?, data_nascimento_ator=?, idPremio=? where idAtor=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ator.nome);
            pst.setString(2, ator.dataNascimentoAtor);
            pst.setInt(3, ator.premio.getIdPremio());
            pst.setInt(4, id);
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
        return sucesso;
    }

    //DELETE
    public boolean deleteAtor(int id) {
        connectToDB();
        String sql = "DELETE FROM ator where idAtor=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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
        return sucesso;
    }

    //SELECT
    public ArrayList<Ator> selectAtor() {
        ArrayList<Ator> atores = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM ator";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Ator ator = new Ator(
                        rs.getString("nome_ator"),
                        rs.getString("data_nascimento_ator")
                );

                PremioDAO premioDAO = new PremioDAO();
                Premio premio = premioDAO.selectPremioPorId(rs.getInt("idPremio"));
                ator.premio = premio;
                ator.idAtor = rs.getInt("idAtor");

                atores.add(ator);
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

        return atores;
    }

    public Ator selectAtorPorId(int id) {
        connectToDB();
        String sql = "Select * FROM ator where idAtor=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            rs.next();
            Ator ator = new Ator(
                    rs.getString("nome_ator"),
                    rs.getString("data_nascimento_ator")
            );
            ator.idAtor = rs.getInt("idAtor");

            PremioDAO premioDAO = new PremioDAO();
            ator.premio = premioDAO.selectPremioPorId(rs.getInt("idPremio"));

            return ator;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return null;
    }
}
