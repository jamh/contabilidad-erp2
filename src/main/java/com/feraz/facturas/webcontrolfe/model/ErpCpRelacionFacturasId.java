/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpCpRelacionFacturasId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "NUM_FACTURA")
    private int numFactura;
    
    @Column(name = "NUM_DOCUMENTO")
    private int numDocumento;
    
    @Column(name = "SEC")
    private int sec;
    
   
    public ErpCpRelacionFacturasId(){
    
    }
    
    public ErpCpRelacionFacturasId(String compania, int numFactura, int numDocumento, int sec){
        
        this.compania = compania;
        this.numFactura = numFactura;
        this.numDocumento = numDocumento;
        this.sec = sec;
    
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
    
    
    
}
