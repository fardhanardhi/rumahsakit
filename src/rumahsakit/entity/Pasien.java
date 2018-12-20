/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.entity;

/**
 *
 * @author panjiad
 */
public class Pasien {
    
    private Integer id;
    private String nama;
    private Integer umur;
    private String alamat;
    private Integer id_dokter;
    private Integer id_penyakit;

    public Integer getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(Integer id_dokter) {
        this.id_dokter = id_dokter;
    }

    public Integer getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(Integer id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
