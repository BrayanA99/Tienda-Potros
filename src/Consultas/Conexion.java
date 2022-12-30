
package Consultas;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author User
 */
public class Conexion {
    public static final String URL = "jdbc:mysql://localhost:3306/Potro1";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static Connection getConection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
