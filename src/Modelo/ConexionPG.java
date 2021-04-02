package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bryan
 */
public class ConexionPG {

    private String usuarioPG = "postgres";
    private String passPG = "1234";
    String cadenaConexion = "jdbc:postgresql://localhost:5432/      ";

    private Connection con;

    public ConexionPG() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(cadenaConexion, usuarioPG, passPG);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet query(String sql) {
        try {
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public SQLException noQuery(String nsql) {
        try {
            Statement st;
            st = con.createStatement();
            st.execute(nsql);

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
    }

    public SQLException noqueryActualizar(String nsql) {
        try {
            Statement st;
            st = con.createStatement();
            int i = st.executeUpdate(nsql);
            System.out.println(i);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
