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
import rumahsakit.entity.Obat;

/**
 *
 * @author panjiad
 */
public class ObatDao extends AbstractMK implements ICrudObat{
    
    public ObatDao(){
        super();
    }
    
    @Override
    public void insert(Obat obat){
        String nama=obat.getNama();
        String jenis=obat.getJenis();
        Integer stok=obat.getStok();
        Integer harga=obat.getHarga();
        
        String sql = "INSERT INTO tb_obat(nama_obat, jenis_obat, stok, harga)"+"VALUES ('"+nama+"','"+jenis+"','"+stok+"','"+harga+"');";
        
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
        String query = "DELETE * FROM tb_obat WHERE id_obat = '"+id+"'";
        
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
    public void update(Obat obat){
        String query = "UPDATE tb_obat SET nama_obat = ?, jenis_obat = ?, stok = ?, harga = ? WHERE id_obat = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, obat.getNama());
            ps.setString(2, obat.getJenis());
            ps.setInt(3, obat.getStok());
            ps.setInt(4, obat.getHarga());
            ps.setInt(5, obat.getId());
            ps.executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Obat> ambilSemuaData(){
        ArrayList<Obat> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    @Override
    public ArrayList<Obat> selectWhere(String where){
        String sql = "SELECT * FROM tb_obat";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Obat> listObat = new ArrayList<>();
            
            while(hasil.next()){
                Obat o= new Obat();
                o.setId(hasil.getInt("id_obat"));
                o.setNama(hasil.getString("nama_obat"));
                o.setJenis(hasil.getString("jenis_obat"));
                o.setStok(hasil.getInt("stok"));
                o.setHarga(hasil.getInt("harga"));
                
                listObat.add(o);
            }
            return listObat;
        }
        catch(SQLException ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
