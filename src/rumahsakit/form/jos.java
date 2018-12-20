/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rumahsakit.form;

import java.sql.Connection;
import rumahsakit.lib.ManajerKoneksi;

/**
 *
 * @author panjiad
 */
public class jos {
    private final Connection koneksi;
    
    public jos(){
    this.koneksi=ManajerKoneksi.getKoneksi();
}
}
