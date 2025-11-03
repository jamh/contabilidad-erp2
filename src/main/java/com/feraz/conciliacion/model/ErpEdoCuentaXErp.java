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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author JAMH
 */

@Entity
@Table(name = "ERP_EDO_CUENTAXERP")
public class ErpEdoCuentaXErp implements java.io.Serializable{
    
          @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "idEdoCuenta", column = @Column(name = "ID_EDO_CUENTA", nullable = false)),
        @AttributeOverride(name = "idErp", column = @Column(name = "ID_ERP", nullable = false)),
         @AttributeOverride(name = "tDocErp", column = @Column(name = "T_DOC_ERP", nullable = false, length = 10))

    })

    //@JsonProperty("ID")
    private ErpEdoCuentaXErpId id;
        
     @Column(name = "TIPO_CONCILIACION")
     private String tipoConciliacion;
     
     @Column(name = "IMPORTE_PAGO_ERP")
     private BigDecimal importePagoErp;
     
     @Column(name = "BANCO_ERP")
     private String bancoErp;
    
     
     
     public ErpEdoCuentaXErp(){
     
     }
     
     
     public ErpEdoCuentaXErp(ErpEdoCuentaXErpId id){
     
         this.id = id;
     }

    public ErpEdoCuentaXErpId getId() {
        return id;
    }

    public void setId(ErpEdoCuentaXErpId id) {
        this.id = id;
    }

    public String getTipoConciliacion() {
        return tipoConciliacion;
    }

    public void setTipoConciliacion(String tipoConciliacion) {
        this.tipoConciliacion = tipoConciliacion;
    }

    public BigDecimal getImportePagoErp() {
        return importePagoErp;
    }

    public void setImportePagoErp(BigDecimal importePagoErp) {
        this.importePagoErp = importePagoErp;
    }

    public String getBancoErp() {
        return bancoErp;
    }

    public void setBancoErp(String bancoErp) {
        this.bancoErp = bancoErp;
    }
     
     
     
}
