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
    
    public PasienDao(){
        super();
    }
    
    @Override
    public void insert(Pasien pasien) {
        String nama=pasien.getNama();
        String alamat=pasien.getAlamat();
        Integer umur=pasien.getUmur();
        Integer id_dokter=pasien.getId_dokter();
        Integer id_penyakit=pasien.getId_penyakit();
    
        String sql = "INSERT INTO tb_pasien(nama_pasien, umur_pasien, alamat_pasien, id_dokter, id_penyakit)"+"VALUES ('"+nama+"','"+umur+"','"+alamat+"','"+id_dokter+"','"+id_penyakit+"');";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql);
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int id){
        String query = "DELETE * FROM tb_pasien WHERE id_pasien = '"+id+"'";
        
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
    public void update(Pasien pasien){
        String query = "UPDATE tb_pasien SET nama_pasien = ?, umur_pasien = ?, alamat_pasien = ?, id_dokter = ?, id_penyakit = ? WHERE id_pasien = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, pasien.getNama());
            ps.setInt(2, pasien.getUmur());
            ps.setString(3, pasien.getAlamat());
            ps.setInt(4, pasien.getId_dokter());
            ps.setInt(5, pasien.getId_penyakit());
            ps.setInt(6, pasien.getId());
            ps.executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
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
        String sql = "SELECT * FROM tb_pasien";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Pasien> listPasien = new ArrayList<>();
            
            while(hasil.next()){
                Pasien p= new Pasien();
                p.setId(hasil.getInt("id_pasien"));
                p.setNama(hasil.getString("nama_pasien"));
                p.setUmur(hasil.getInt("umur_pasien"));
                p.setAlamat(hasil.getString("alamat_pasien"));
                p.setId_dokter(hasil.getInt("id_dokter"));
                p.setId_penyakit(hasil.getInt("id_penyakit"));
                
                listPasien.add(p);
            }
            return listPasien;
        }
        catch(SQLException ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
