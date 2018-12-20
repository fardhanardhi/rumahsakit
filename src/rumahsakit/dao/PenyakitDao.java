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
import rumahsakit.entity.Penyakit;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class PenyakitDao extends AbstractMK implements ICrudPenyakit{
    
    public PenyakitDao(){
        super();
    }
    
    @Override
    public void insert(Penyakit penyakit){
        String nama=penyakit.getNama();
        String status=penyakit.getStatus();
        Integer id_obat=penyakit.getId_obat();
        
        String sql = "INSERT INTO tb_penyakit(nama, status, id_obat)"+"VALUES ('"+nama+"','"+status+"','"+id_obat+"');";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql);
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int id){
        String query = "DELETE * FROM tb_penyakit WHERE id_penyakit = '"+id+"'";
        
        try{
            Statement ps = this.koneksi.createStatement();
            ps.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void update(Penyakit penyakit){
        String query = "UPDATE tb_penyakit SET nama = ?, status = ?, id_obat = ? WHERE id_penyakit = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, penyakit.getNama());
            ps.setString(2, penyakit.getStatus());
            ps.setInt(3, penyakit.getId_obat());
            ps.setInt(4, penyakit.getId());
            ps.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Penyakit> ambilSemuaData(){
        ArrayList<Penyakit> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    @Override
    public ArrayList<Penyakit> selectWhere(String where){
        String sql = "SELECT * FROM tb_penyakit";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Penyakit> listPenyakit = new ArrayList<>();
            
            while(hasil.next()){
                Penyakit pe= new Penyakit();
                pe.setId(hasil.getInt("id_penyakit"));
                pe.setNama(hasil.getString("nama"));
                pe.setStatus(hasil.getString("status"));
                pe.setId_obat(hasil.getInt("id_obat"));
                
                listPenyakit.add(pe);
            }
            return listPenyakit;
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
