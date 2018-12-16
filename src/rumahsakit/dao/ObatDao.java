/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ObatDao extends AbstractMK{
    
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
    
    public void delete(int id){
        String query = "DELETE FROM obat WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(Obat obat){
        String query = "UPDATE obat SET nama = ?, jenis = ?, stok = ?, harga = ? WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, obat.getNama());
            ps.setString(2, obat.getJenis());
            ps.setInt(3, obat.getStok());
            ps.setInt(4, obat.getHarga());
            ps.setInt(5, obat.getId());
            ps.executeUpdate();
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
