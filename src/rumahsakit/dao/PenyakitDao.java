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
import rumahsakit.entity.Penyakit;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class PenyakitDao {
    private final Connection koneksi;
    
    public PenyakitDao(){
        this.koneksi = ManajerKoneksi.getKoneksi();    
    }
    
    public void insert(String nama, String status, int id_obat) throws SQLException{
        String sql = "INSERT INTO penyakit(nama, status, id_obat) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+nama+"', '"+status+"', '"+id_obat+"')");
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Penyakit> ambilSemuaData(){
        ArrayList<Penyakit> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    private ArrayList<Penyakit> selectWhere(String where){
        String sql = "SELECT * FROM penyakit";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Penyakit> listPenyakit = new ArrayList<>();
            
            while(hasil.next()){
                Penyakit pe= new Penyakit();
                pe.setNama(hasil.getString("nama"));
                pe.setStatus(hasil.getString("status"));
                
                listPenyakit.add(pe);
            }
            return listPenyakit;
        }
        catch(Exception ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
