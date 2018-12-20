/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.util.ArrayList;
import rumahsakit.entity.Dokter;

/**
 *
 * @author adan
 */
public interface ICrudDokter {
    public abstract void insert(Dokter dokter);
    public abstract void delete(int id);
    public abstract void update(Dokter dokter);
    public abstract ArrayList<Dokter> ambilSemuaData();
    public abstract ArrayList<Dokter> selectWhere(String where);
}
