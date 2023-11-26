package DAO;

import Objetos.Categoria;
import Objetos.Comedia;
import Objetos.Filme;
import Objetos.Romance;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class FilmeDAO extends ConnectionDAO {

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public int insertFilme(Filme filme) {
        connectToDB();

        String sql = "INSERT INTO filme (titulo, idCategoria) values(?, ?)";
        try {
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, filme.getTitulo());
            if (Objects.equals(filme.getCategoria().getNome_categoria(), "romance")) {
                pst.setString(2, "1");
            } else {
                pst.setString(2, "2");
            }

            pst.execute();
            rs = pst.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            return generatedKey;
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

        return 0;
    }

    //UPDATE
    public boolean updateFilme(int id, Filme filme) {
        connectToDB();
        String sql = "UPDATE filme SET titulo=?, idCategoria=? where idFilme=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, filme.getTitulo());
            if (Objects.equals(filme.getCategoria().getNome_categoria(), "romance")) {
                pst.setString(2, "1");
            } else {
                pst.setString(2, "2");
            }
            pst.setInt(3,id);
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
    public boolean deleteFilme(int id) {
        connectToDB();
        String sql = "DELETE FROM filme where idFilme=?";
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
    public ArrayList<Filme> selectFilme() {
        ArrayList<Filme> filmes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM filme";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Filme filme;
                Categoria categoria;

                if (Objects.equals(rs.getString("idCategoria"), "1")) {
                    filme = new Romance();
                    categoria = new Categoria("romance");
                } else {
                    filme = new Comedia();
                    categoria = new Categoria("comedia");
                }

                filme.setIdFilme(rs.getInt("idFilme"));
                filme.setTitulo(rs.getString("titulo"));

                filme.setCategoria(categoria);

                filmes.add(filme);
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
        return filmes;
    }

    public Filme selectFilmPorId(int id) {
        connectToDB();
        String sql = "Select * FROM filme where idFilme=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            rs.next();
            Filme filme;
            Categoria categoria;

            if (Objects.equals(rs.getString("idCategoria"), "1")) {
                filme = new Romance();
                categoria = new Categoria("romance");
            } else {
                filme = new Comedia();
                categoria = new Categoria("comedia");
            }

            filme.setIdFilme(rs.getInt("idFilme"));
            filme.setTitulo(rs.getString("titulo"));

            filme.setCategoria(categoria);

            return filme;
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
