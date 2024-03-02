
package Controlador;
import Modelo.Conexion;
import java.sql.ResultSet;

public class Movies {
    
    
    int id;
    String titulo, director;
    String year, duracion;
    String genero;

    public Movies() {
    }

    public Movies(int id, String titulo, String director, String year, String duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.year = year;
        this.duracion = duracion;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    
    
    
    public boolean agregarPelicula(){
        
        Conexion prueba = new Conexion();
            prueba.conectar();
            boolean resultado = false;
            
        try{
            resultado = prueba.ejecutaSQL("insert into movie values (null,'"+this.titulo + "','"
                    +this.director +"','"+this.year + "','"+this.duracion+ "', '"+this.genero+"')");
            //System.out.println("insert into usuarios values (null,'"+this.rut + "','"
            //        +this.nombre +"','"+this.apellido + "','"+this.pass+ "')");
        }catch(Exception e){
            System.out.println("Error en la busqueda "+e);
        }
        prueba.desconectar();
        return resultado;
    }
    
    
    public boolean eliminarPelicula(){
        Conexion conect = new Conexion();
        conect.conectar();
        boolean resultado = false;
        System.out.println(resultado);
        try{
            resultado = conect.ejecutaSQL("delete from movie where titulo = '"+this.titulo + "'");
           System.out.println(resultado);
        }catch(Exception e){
            System.out.println("Error en la busqueda "+e);
        }
        conect.desconectar();
        return resultado;
    }
    
    public boolean actualizarPelicula(){
        Conexion conect = new Conexion();
        conect.conectar();
        boolean resultado = false;
        
        try{
            
        resultado = conect.ejecutaSQL("update movie set titulo = '"+this.titulo + "', director = '"+this.director + "', year = '"+this.year + "', genero = '"+this.genero + "' where titulo = '"+this.titulo + "'");
            System.out.println(resultado);
        }catch(Exception e){
            System.out.println("Error en la busqueda "+e);
        }
        conect.desconectar();
        return resultado;
    }
    
    public void enviarMail(){
        
        Conexion conect = new Conexion();
        conect.conectar();
        boolean resultado = false;
        
        
        
        
    }
    
    public Object[][] llenaTabla(String[] cabeceras){
        Conexion conecta = new Conexion();
        conecta.conectar();
        Object data[][];
        int conteo = 0;
        String sql = "Select * from movie";
        ResultSet resultado;
        try{
            resultado = conecta.registros(sql);
            int i =0;
            while(resultado.next()){
                conteo++;
            }
            data = new String[conteo][cabeceras.length];
            String[] columna = new String[cabeceras.length];
            resultado = conecta.registros(sql);
            while(resultado.next()){
                for (int j = 0; j < columna.length; j++) {
                    columna[j] = resultado.getString(cabeceras[j]);
                    data[i][j] = columna[j];
                }i++;
            }
            //System.out.println(Arrays.deepToString(data));
        }catch(Exception e){
            System.out.println("Error en la busqueda "+e);
            data = new String[0][0];
        }
        conecta.desconectar();
        return data;
    } 
    
    public Object[][] llenaTablaFiltrada(String[] cabeceras, String genero, String year1, String year2){
        int ano1 = Integer.parseInt(year1);
        int ano2 = Integer.parseInt(year2);
        Conexion conecta = new Conexion();
        conecta.conectar();
        Object data[][];
        int conteo = 0;
        String sql = "SELECT * FROM movie WHERE CAST(year AS SIGNED) BETWEEN '" + ano1 + "' AND '" + ano2 + "' AND genero = '" + genero + "'";
        System.out.println(sql);
        ResultSet resultado;
        try{
            resultado = conecta.registros(sql);
            int i =0;
            while(resultado.next()){
                conteo++;
            }
            data = new String[conteo][cabeceras.length];
            String[] columna = new String[cabeceras.length];
            resultado = conecta.registros(sql);
            while(resultado.next()){
                for (int j = 0; j < columna.length; j++) {
                    columna[j] = resultado.getString(cabeceras[j]);
                    data[i][j] = columna[j];
                }i++;
            }
            //System.out.println(Arrays.deepToString(data));
        }catch(Exception e){
            System.out.println("Error en la busqueda "+e);
            data = new String[0][0];
        }
        conecta.desconectar();
        return data;
    } 
    }
    
    
  

