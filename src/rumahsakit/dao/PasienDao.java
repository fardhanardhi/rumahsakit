/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import rumahsakit.entity.Pasien;

/**
 *
 * @author panjiad
 */
public class PasienDao extends AbstractMK implements ICrudPasien {
    
    @Override
    public void insert(String nama, int umur, String alamat, int id_dokter, int id_penyakit) {
        String sql = "INSERT INTO pasien(nama, umur, alamat, id_dokter, id_penyakit) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+nama+"', '"+umur+"', '"+alamat+"', '"+id_dokter+"', '"+id_penyakit+"')");
        }
        catch(SQLException ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int id){
        String query = "DELETE FROM pasien WHERE id = ?";
        
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
    
    @Override
    public void update(Pasien pasien){
        String query = "UPDATE pasien SET nama = ?, umur = ?, alamat = ? WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, pasien.getNama());
            ps.setInt(2, pasien.getUmur());
            ps.setString(3, pasien.getAlamat());
            ps.setInt(4, pasien.getId());
            ps.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Pasien> ambilSemuaData(){
        ArrayList<Pasien> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    @Override
    public ArrayList<Pasien> selectWhere(String where){
        String sql = "SELECT * FROM pasien";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Pasien> listPasien = new ArrayList<>();
            
            while(hasil.next()){
                Pasien p= new Pasien();
                p.setNama(hasil.getString("nama"));
                p.setUmur(hasil.getInt("umur"));
                p.setAlamat(hasil.getString("alamat"));
                
                listPasien.add(p);
            }
            return listPasien;
        }
        catch(SQLException ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
