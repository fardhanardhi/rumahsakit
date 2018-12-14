/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import rumahsakit.entity.Obat;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class ObatDao {
    private final Connection koneksi;
    
    public ObatDao(){
        this.koneksi = ManajerKoneksi.getKoneksi();    
    }
    
    public void insert(String nama, String jenis, int stok, int harga) throws SQLException{
        String sql = "INSERT INTO obat(nama, jenis, stok, harga) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+nama+"', '"+jenis+"', '"+stok+"', '"+harga+"')");
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Obat> ambilSemuaData(){
        ArrayList<Obat> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    private ArrayList<Obat> selectWhere(String where){
        String sql = "SELECT * FROM obat";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Obat> listObat = new ArrayList<>();
            
            while(hasil.next()){
                Obat o= new Obat();
                o.setNama(hasil.getString("nama"));
                o.setJenis(hasil.getString("jenis"));
                o.setStok(hasil.getInt("stok"));
                o.setHarga(hasil.getInt("harga"));
                
                listObat.add(o);
            }
            return listObat;
        }
        catch(Exception ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
