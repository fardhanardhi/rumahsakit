/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.util.ArrayList;
import rumahsakit.entity.Penyakit;

/**
 *
 * @author adan
 */
public interface ICrudPenyakit {
    public abstract void insert(String nama, String status, int id_obat);
    public abstract void delete(int id);
    public abstract void update(Penyakit penyakit);
    public abstract ArrayList<Penyakit> ambilSemuaData();
    public abstract ArrayList<Penyakit> selectWhere(String where);
}
