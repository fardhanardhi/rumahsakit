/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.dao;

import java.sql.Connection;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public abstract class AbstractMK {
    protected final Connection koneksi;
    
    public  AbstractMK(){
        this.koneksi = ManajerKoneksi.getKoneksi();
    }
}
