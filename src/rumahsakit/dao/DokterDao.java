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
public class DokterDao extends AbstractMK implements ICrudDokter{
    
    public DokterDao(){
        super();
    }
    
    @Override
    public void insert(Dokter dokter){
        String nama=dokter.getNama();
        String alamat=dokter.getAlamat();
        Integer umur=dokter.getUmur();
        Integer gaji=dokter.getGaji();
        
        String sql = "INSERT INTO tb_dokter (nama_dokter, umur_dokter, alamat_dokter, gaji)"+"VALUES ('"+nama+"','"+umur+"','"+alamat+"','"+gaji+"');";
        
        try{
            Statement s=this.koneksi.createStatement();
            s.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int id){
        String query = "DELETE * FROM tb_dokter WHERE id_dokter = '"+id+"'";
        
        try{
            Statement ps = this.koneksi.createStatement();
            ps.executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void update(Dokter dokter){
        String query = "UPDATE tb_dokter SET nama_dokter = ?, umur_dokter = ?, alamat_dokter = ?, gaji = ? WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, dokter.getNama());
            ps.setInt(2, dokter.getUmur());
            ps.setString(3, dokter.getAlamat());
            ps.setInt(4, dokter.getGaji());
            ps.setInt(5, dokter.getId());
            ps.executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Dokter> ambilSemuaData(){
        ArrayList<Dokter> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    public ArrayList<Dokter> selectWhere(String where){
        String sql = "SELECT * FROM tb_dokter";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Dokter> listDokter = new ArrayList<>();
            
            while(hasil.next()){
                Dokter d= new Dokter();
                d.setId(hasil.getInt("id_dokter"));
                d.setNama(hasil.getString("nama_dokter"));
                d.setUmur(hasil.getInt("umur_dokter"));
                d.setAlamat(hasil.getString("alamat_dokter"));
                d.setGaji(hasil.getInt("gaji"));
                
                listDokter.add(d);
            }
            return listDokter;
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}


