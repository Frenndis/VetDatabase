
package Modelo;
import Vista.Administrar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Conexion {
    
    String bd = "moviesdb";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String pass = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, pass);
            System.out.println("CONEXIÓN CON LA BD " + bd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR EN LA CONEXION A LA BD " + ex);
        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo desconectar ");
        }
    }

    public static void main(String[] args) {
        Administrar admin = new Administrar();
        admin.setVisible(true);
            Conexion prueba = new Conexion();
            prueba.conectar();
    }
    public boolean ejecutaSQL(String cadenaSQL){
        try{
            //System.out.println(cadenaSQL);
            PreparedStatement pstm = cx.prepareStatement(cadenaSQL);
            int filasAfectadas = pstm.executeUpdate();
            return filasAfectadas > 0;                
        }catch(Exception e){
            System.out.println("Error: "+e);
            return false;
        }
    }
    
    public ResultSet registros(String cadenaSQL){
        try{
            PreparedStatement pstm = cx.prepareStatement(cadenaSQL);
            ResultSet respuesta = pstm.executeQuery();
            return respuesta;
        }catch(Exception e){
            System.out.println("Error: "+e);
            return null;
        }
        
    }
}
