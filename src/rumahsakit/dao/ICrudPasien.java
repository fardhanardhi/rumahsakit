/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.util.ArrayList;
import rumahsakit.entity.Pasien;

/**
 *
 * @author adan
 */
public interface ICrudPasien {
    public abstract void insert(Pasien pasien);
    public abstract void delete(int id);
    public abstract void update(Pasien pasien);
    public abstract ArrayList<Pasien> ambilSemuaData();
    public abstract ArrayList<Pasien> selectWhere(String where);
}
