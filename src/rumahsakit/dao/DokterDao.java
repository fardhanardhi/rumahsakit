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
import rumahsakit.entity.Dokter;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class DokterDao {
    private final Connection koneksi;
    
    public DokterDao(){
        this.koneksi = ManajerKoneksi.getKoneksi();    
    }
    
    public void insert(String nama, int umur, String alamat, int gaji) throws SQLException{
        String sql = "INSERT INTO dokter(nama, umur, alamat, gaji) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+nama+"', '"+umur+"', '"+alamat+"', '"+gaji+"')");
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Dokter> ambilSemuaData(){
        ArrayList<Dokter> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    private ArrayList<Dokter> selectWhere(String where){
        String sql = "SELECT * FROM dokter";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Dokter> listDokter = new ArrayList<>();
            
            while(hasil.next()){
                Dokter d= new Dokter();
                d.setNama(hasil.getString("nama"));
                d.setUmur(hasil.getInt("umur"));
                d.setAlamat(hasil.getString("alamat"));
                d.setGaji(hasil.getInt("gaji"));
                
                listDokter.add(d);
            }
            return listDokter;
        }
        catch(Exception ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}


