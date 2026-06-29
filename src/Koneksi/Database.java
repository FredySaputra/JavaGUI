//NIM       : 2311500140
//Nama      : Fredy Dwi Saputra
//No. Absen : 7
package Koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author Mahasiswa
 */
public class Database {
    static Connection conn;
    
    public static Connection KoneksiDB(){
        if(conn==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/db_akademik_2311500140","root","");
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil!","Pesan",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Koneksi Tidak Berhasil","Pesan",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Error"+e.getMessage());
            }
        }
        return conn;
    }
}
