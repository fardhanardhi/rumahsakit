/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author panjiad
 */
public class ManajerKoneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_rumahsakit?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        if(ManajerKoneksi.koneksi==null){
            ManajerKoneksi.buatKoneksi();
        }
        
        return ManajerKoneksi.koneksi;
    }
    
    public static void buatKoneksi(){
        ManajerKoneksi.cekDriver();
        
        try {
            ManajerKoneksi.koneksi = DriverManager.getConnection(ManajerKoneksi.URL, ManajerKoneksi.USER, ManajerKoneksi.PASSWORD);
            System.out.println("Buat koneksi OK");
        } catch (SQLException ex) {
            System.out.println("Buat Koneksi Gagal, erorrnya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public static void cekDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Cek driver OK!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Cek driver gagal! Errornya:");
            System.out.println(ex.getMessage());
            
            //perintah untuk nutup program sendiri
            System.exit(1);
        }
    }
}
