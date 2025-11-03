/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author vavi
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpNotasExtId implements java.io.Serializable{
    
    @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID_NOTA")
    private int idNota;
    
    @Column (name = "ID_FACTURA")
    private int idFactura;
    
    public ErpNotasExtId(){
    
    }
    
    public ErpNotasExtId(String compania, int idNota, int idFactura){
        
        this.compania = compania;
        this.idNota = idNota;
        this.idFactura = idFactura;
    
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
    
    
}
