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
public class Penyakit {
    
    private Integer id;
    private String nama;
    private String status;
    private Integer id_obat;

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
