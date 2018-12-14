/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import rumahsakit.entity.Dokter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author panjiad
 */
public class DokterDao extends AbstractMK {
    
    public void insert(String nama, int umur, String alamat, int gaji) throws SQLException {
        String sql = "INSERT INTO dokter(nama, umur, alamat, gaji) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+nama+"', '"+umur+"', '"+alamat+"', '"+gaji+"')");
        }
        catch(SQLException ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(int id){
        String query = "DELETE FROM dokter WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(Dokter dokter){
        String query = "UPDATE dokter SET nama = ?, umur = ?, alamat = ?, gaji = ? WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, dokter.getNama());
            ps.setInt(2, dokter.getUmur());
            ps.setString(3, dokter.getAlamat());
            ps.setInt(4, dokter.getGaji());
            ps.setInt(5, dokter.getId());
            ps.executeUpdate();
        }
        catch(SQLException ex){
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
        catch(SQLException ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}


