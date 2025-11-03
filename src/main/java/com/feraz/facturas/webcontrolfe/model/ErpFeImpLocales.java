/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author vavi
 */

@Entity
@Table(name = "ERP_FE_IMP_LOCALES")
public class ErpFeImpLocales implements java.io.Serializable{
    
      @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })    
    private ErpFeImpLocalesId id;
    
     @Column (name = "TOTAL_RETENCIONES")
    private BigDecimal totalRetenciones;
     
     
     @Column (name = "TOTAL_TRASLADOS")
    private BigDecimal totalTraslados;
    
     
     @Column (name = "VERSION")
    private String version;
     
     
     
     public ErpFeImpLocales(){
     
     }
     
     public ErpFeImpLocales(ErpFeImpLocalesId id){
     
              this.id = id;
     
     }

    public ErpFeImpLocalesId getId() {
        return id;
    }

    public void setId(ErpFeImpLocalesId id) {
        this.id = id;
    }

    public BigDecimal getTotalRetenciones() {
        return totalRetenciones;
    }

    public void setTotalRetenciones(BigDecimal totalRetenciones) {
        this.totalRetenciones = totalRetenciones;
    }

    public BigDecimal getTotalTraslados() {
        return totalTraslados;
    }

    public void setTotalTraslados(BigDecimal totalTraslados) {
        this.totalTraslados = totalTraslados;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
     
     
     
     
}
