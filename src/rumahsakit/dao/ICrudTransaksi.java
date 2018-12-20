/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.util.ArrayList;
import rumahsakit.entity.Transaksi;

/**
 *
 * @author adan
 */
public interface ICrudTransaksi {
    public abstract void insert(Transaksi transaksi);
    public abstract void delete(int id);
    public abstract void update(Transaksi transaksi);
    public abstract ArrayList<Transaksi> ambilSemuaData();
    public abstract ArrayList<Transaksi> selectWhere(String where);
}
