/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_CP_RELACION_FACTURAS")
public class ErpCpRelacionFacturas implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "numFactura", column = @Column(name = "NUM_FACTURA", nullable = false )),
        @AttributeOverride(name = "numDocumento", column = @Column(name = "NUM_DOCUMENTO", nullable = false)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false)),
    
        
        
    })

    private ErpCpRelacionFacturasId id;
    
    @Column (name = "IMPORTE")
    private BigDecimal importeFact;
    
    
      public ErpCpRelacionFacturas(){
     
     }
     
     public ErpCpRelacionFacturas(ErpCpRelacionFacturasId id){
         
         this.id = id;
         
     }

    public ErpCpRelacionFacturasId getId() {
        return id;
    }

    public void setId(ErpCpRelacionFacturasId id) {
        this.id = id;
    }

    public BigDecimal getImporteFact() {
        return importeFact;
    }

    public void setImporteFact(BigDecimal importeFact) {
        this.importeFact = importeFact;
    }
     
     
}
