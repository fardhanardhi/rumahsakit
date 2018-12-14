/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.util.ArrayList;
import rumahsakit.entity.Obat;

/**
 *
 * @author adan
 */
public interface ICrudObat {
    public abstract void insert(String nama, String jenis, int stok, int harga);
    public abstract void delete(int id);
    public abstract void update(Obat obat);
    public abstract ArrayList<Obat> ambilSemuaData();
    public abstract ArrayList<Obat> selectWhere(String where);
}
