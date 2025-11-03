/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.ordencompra.model;

/**
 *
 * @author vavi
 */
    import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpOrdenXFacturaId implements java.io.Serializable{
    
      @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "ID_ORDEN")
    private Integer idOrden;
    
    @Column (name = "NUMERO_FE")
    private Integer numeroFe;
    
    @Column (name = "ID_ORDEN_DET")
    private Integer idOrdenDet;
    
    public ErpOrdenXFacturaId(){
    
    }
    
    public ErpOrdenXFacturaId(String compania, Integer idOrden, Integer numeroFe, Integer idOrdenDet){
        
        this.compania = compania;
        this.idOrden = idOrden;
        this.numeroFe = numeroFe;
        this.idOrdenDet = idOrdenDet;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(Integer numeroFe) {
        this.numeroFe = numeroFe;
    }

    public Integer getIdOrdenDet() {
        return idOrdenDet;
    }

    public void setIdOrdenDet(Integer idOrdenDet) {
        this.idOrdenDet = idOrdenDet;
    }
    
    
    
    
}
