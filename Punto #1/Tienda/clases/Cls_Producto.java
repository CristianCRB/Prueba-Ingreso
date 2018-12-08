package clases;

import clases.Cls_Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Cristian Rodriguez
 */
public class Cls_Producto {
    private final String Insert = "INSERT INTO productos (pro_nombre, pro_cantidad, pro_costo, pro_precio) VALUES (?,?,?,?)";
    private final String Select = "SELECT pro_id, pro_nombre, pro_cantidad, pro_costo, pro_precio, pro_costo*pro_cantidad as Total_Costo, pro_cantidad*pro_precio as Total_Precio,(pro_precio-pro_costo)*pro_cantidad as Total_Ganancias FROM productos";
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Cls_Conectar conn;

    public Cls_Producto() {
        PS = null;
        conn = new Cls_Conectar();
    }
    
    private DefaultTableModel titulos(){
        DT = new DefaultTableModel();
        DT.addColumn("Id");
        DT.addColumn("Nombre");
        DT.addColumn("Cantidad");
        DT.addColumn("Costo/Und");
        DT.addColumn("Precio/Und");
        DT.addColumn("Total Costo");
        DT.addColumn("Total Precio");
        DT.addColumn("Total Ganancias");
        
        return DT;
    }
    
    public int guardar(String nom, int cant, float costo, float precio){
        
        int devuelve = 0;
        try {
            PS = conn.getConnection().prepareStatement(Insert);
            PS.setString(1, nom);
            PS.setInt(2, cant);
            PS.setFloat(3, costo);
            PS.setFloat(4, precio);
            devuelve = PS.executeUpdate();
            
            if (devuelve > 0) {
                JOptionPane.showMessageDialog(null, "El registro se ha guardado");
            }
        } 
        catch (SQLException e) {
            System.err.println("Error al guardar datos " +e.getMessage());
        }
        //Finalizar recursos y liberar
        finally{
            PS = null;
            conn.desconectar();
        }
        return devuelve;
    }
    
    public DefaultTableModel getDatos(){
        try {
            titulos();
            PS = conn.getConnection().prepareStatement(Select);
            RS = PS.executeQuery();
            Object[] fila = new Object[8];
            while(RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getFloat(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getFloat(6);
                fila[6] = RS.getFloat(7);
                fila[7] = RS.getFloat(8);
                
                DT.addRow(fila);
            }
        } 
        catch (SQLException e) {
            System.out.println("Error en los datos" +e.getMessage());
        }
        
        finally{
            PS = null;
            RS = null;
            conn.desconectar();
        }
        
        return DT;
    }
}
