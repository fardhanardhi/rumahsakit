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
public class TransaksiDao extends AbstractMK{
    
    public void insert(String tanggal, int total, int id_pasien) throws SQLException{
        String sql = "INSERT INTO transaksi(tanggal, total, id_pasien) VALUES";
        
        try{
            Statement st = this.koneksi.createStatement();
            st.executeUpdate(sql + "('"+tanggal+"', '"+total+"', '"+id_pasien+"')");
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(int id){
        String query = "DELETE FROM penyakit WHERE id = ?";
        
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
    
    public void update(Transaksi transaksi){
        String query = "UPDATE pasien SET nama = ?, umur = ? WHERE id = ?";
        
        try{
            PreparedStatement ps = this.koneksi.prepareStatement(query);
            ps.setString(1, transaksi.getTanggal());
            ps.setInt(2, transaksi.getTotal());
            ps.setInt(3, transaksi.getId());
            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Select Error! error : ");
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Transaksi> ambilSemuaData(){
        ArrayList<Transaksi> semuanya=this.selectWhere(null);
        return semuanya;
    }
    
    private ArrayList<Transaksi> selectWhere(String where){
        String sql = "SELECT * FROM transaksi";
        if(where!=null){
            sql+=(" "+where);
        }
        try{
            Statement s = this.koneksi.createStatement();
            ResultSet hasil = s.executeQuery(sql);
            ArrayList<Transaksi> listTransaksi = new ArrayList<>();
            
            while(hasil.next()){
                Transaksi t= new Transaksi();
                t.setTanggal(hasil.getString("tanggal"));
                t.setTotal(hasil.getInt("total"));
                
                listTransaksi.add(t);
            }
            return listTransaksi;
        }
        catch(Exception ex){
            System.out.println("Select Error! error :");
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
