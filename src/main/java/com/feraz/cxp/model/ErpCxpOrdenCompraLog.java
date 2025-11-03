package com.feraz.cxp.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Armando
 */

@Entity
@Table(name = "ERP_CXP_ORDEN_COMPRA_LOG")
public class ErpCxpOrdenCompraLog implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
        @AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false)),
        @AttributeOverride(name = "uuid", column = @Column(name = "UUID", nullable = false, length = 200))
    })
    
    private ErpCxpOrdenCompraLogId id;
    
    @Column(name = "CALENDARIO")
    private Integer calendario;
    
    @Column(name = "PERIODO")
    private Integer periodo;
    
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    
    @Column(name = "RFC")
    private String rfc;
    
    @Column(name = "IMPORTE_FACTURA")
    private BigDecimal importeFactura;
    
    
    @Column(name = "ESTATUS")
    private String estatus;
    
    public ErpCxpOrdenCompraLog(){
        
    }
    
    public ErpCxpOrdenCompraLog(ErpCxpOrdenCompraLogId id){
        this.id = id;
    }

    public ErpCxpOrdenCompraLogId getId() {
        return id;
    }

    public void setId(ErpCxpOrdenCompraLogId id) {
        this.id = id;
    }

    public Integer getCalendario() {
        return calendario;
    }

    public void setCalendario(Integer calendario) {
        this.calendario = calendario;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public BigDecimal getImporteFactura() {
        return importeFactura;
    }

    public void setImporteFactura(BigDecimal importeFactura) {
        this.importeFactura = importeFactura;
    }



    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
