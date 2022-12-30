/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class CodigoBarra {

    public static final String URL = "jdbc:mysql://localhost:3306/Potro";
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
    /*
    Este metodo solo recorre toda la base de datos por medio del id y los va poniendo en un archivo PDF
    Como imagen 
    */
    public void CodigosPDF(){
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        con = getConection();    
        try {
            // TODO code application logic here
            ps = con.prepareStatement("Select* FROM Inventario");
            rs = ps.executeQuery();
            
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/User/Desktop/codigos.pdf"));
            doc.open();
            
            Barcode39 code =new Barcode39();
            while(rs.next()){
                code.setCode(rs.getString("Codigo"));
                Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                doc.add(img);
                doc.add(new Paragraph(" "));
            }
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Codigos de barra generados con exito");
    }    
}
