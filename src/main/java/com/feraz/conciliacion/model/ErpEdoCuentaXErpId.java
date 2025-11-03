/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.conciliacion.model;

/**
 *
 * @author vavi
 */
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpEdoCuentaXErpId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_EDO_CUENTA")
    private Integer idEdoCuenta;
    
    @Column(name = "ID_ERP")
    private Long idErp;
    
    @Column(name = "T_DOC_ERP")
    private String tDocErp;
    
    
    
    
    public ErpEdoCuentaXErpId(){
    
    }
    
    
    public ErpEdoCuentaXErpId(String compania, Integer idEdoCuenta, Long idErp, String tDocErp){
        
        this.compania = compania;
        this.idEdoCuenta= idEdoCuenta;
        this.idErp = idErp;
        this.tDocErp = tDocErp; 
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdEdoCuenta() {
        return idEdoCuenta;
    }

    public void setIdEdoCuenta(Integer idEdoCuenta) {
        this.idEdoCuenta = idEdoCuenta;
    }

    public Long getIdErp() {
        return idErp;
    }

    public void setIdErp(Long idErp) {
        this.idErp = idErp;
    }

    public String gettDocErp() {
        return tDocErp;
    }

    public void settDocErp(String tDocErp) {
        this.tDocErp = tDocErp;
    }
    
    
    
}
