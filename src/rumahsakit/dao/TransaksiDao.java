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
import rumahsakit.entity.Transaksi;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class TransaksiDao extends AbstractMK implements ICrudTransaksi{
    
    public TransaksiDao(){
        super();
    }
    
    @Override
    public void insert(Transaksi transaksi){
        String tanggal=transaksi.getTanggal();
        Integer total=transaksi.getTotal();
        Integer id_pasien=transaksi.getId_pasien();
        
        String sql = "INSERT INTO tb_transaksi(tanggal, total, id_pasien)"+"VALUES ('"+tanggal+"','"+total+"','"+id_pasien+"');";
        
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
        String query = "DELETE FROM tb_transaksi WHERE id_transaksi = ?";
        
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
    public void update(Transaksi transaksi){
        String query = "UPDATE tb_transaksi SET id_pasien = ?, tanggal_transaksi = ?, total_transaksi = ? WHERE id_transaksi = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setInt(1, transaksi.getId_pasien());
            ps.setString(2, transaksi.getTanggal());
            ps.setInt(3, transaksi.getTotal());
            ps.setInt(4, transaksi.getId());
            ps.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<Transaksi> ambilSemuaData(){
        ArrayList<Transaksi> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    @Override
    public ArrayList<Transaksi> selectWhere(String where){
        String sql = "SELECT * FROM tb_transaksi";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Transaksi> listTransaksi = new ArrayList<>();
            
            while(hasil.next()){
                Transaksi t= new Transaksi();
                t.setId(hasil.getInt("id_transaksi"));
                t.setId_pasien(hasil.getInt("id_pasien"));
                t.setTanggal(hasil.getString("tanggal_transaksi"));
                t.setTotal(hasil.getInt("total_transaksi"));
                
                listTransaksi.add(t);
            }
            return listTransaksi;
        }
        catch(Exception ex){
            System.out.println("Eksekusi SQL gagal! Errornya : ");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
