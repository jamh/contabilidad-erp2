/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author FERAZ-14
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_OTRO_PAGO")
public class ErpFeOtroPago implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false)),
         @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })    
    private ErpFeOtroPagoId id;
    
     @Column (name = "IMPORTE")
     private BigDecimal importe;
     
     @Column (name = "CONCEPTO")
     private String concepto;
     
     @Column (name = "CLAVE")
     private String clave;
     
     @Column (name = "TIPO_OTRO_PAGO")
     private String tipoOtroPago;
     
     public ErpFeOtroPago (){
     
     
     }
     
     public ErpFeOtroPago(ErpFeOtroPagoId id){
         
         
         this.id = id;
     }

    public ErpFeOtroPagoId getId() {
        return id;
    }

    public void setId(ErpFeOtroPagoId id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoOtroPago() {
        return tipoOtroPago;
    }

    public void setTipoOtroPago(String tipoOtroPago) {
        this.tipoOtroPago = tipoOtroPago;
    }
     
     
     
    
}
