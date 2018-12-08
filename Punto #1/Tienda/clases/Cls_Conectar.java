//Conexción a la base de datos
package clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Rodriguez
 */
public class Cls_Conectar {
   
    private static final String LIB = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    
    private Connection conn;

    public Cls_Conectar() {
        conn = null;
    }
    
    public Connection getConnection(){
        
        try {
            Class.forName(LIB);
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en la conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        return conn;
    }
    
    public void desconectar (){
        try {
            conn.close();
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al cerrar la conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
