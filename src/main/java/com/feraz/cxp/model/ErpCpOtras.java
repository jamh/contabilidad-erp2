/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

import java.io.Serializable;
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
 * @author Administrador
 */
@Entity
@Table(name = "ERP_CP_OTRAS")
public class ErpCpOtras implements Serializable {

    @EmbeddedId
    @AttributeOverrides({      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false, length = 12))
    })
      private  ErpCpOtrasId id;
     

    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Column(name = "ID_BENEFICIARIO")
    private String idBeneficiario;

    @Column(name = "BENEFICIARIO")
    private String beneficiario;
    
    @Column (name = "TIPO_GASTO")
    private String tipoGasto;

    @Column(name = "MONEDA")
    private String moneda;

    @Column(name = "IMPORTE")
    private Double importe;

    @Column(name = "OTROS_IMPUESTOS")
    private Double otrosImpuestos;

    @Column(name = "IVA")
    private Double iva;

    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "TIPO_CAMPO")
    private Double tipoCampo;

    @Column(name = "CONCEPTO_CXP")
    private String conceptoCxp;

    @Column(name = "CTO_CXP")
    private String ctoCxp;

    @Column(name = "FECHA_VENC_CXP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencCxp;
    
    @Column(name = "RFC")
    private String rfc;
    
    @Column(name = "TIPO_CAMBIO")
    private String tipoCambio;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "ESTATUS_CXP")
    private String estatusCxp;
    
    @Column(name = "PAGO_ACUMULADO_CXP")
    private BigDecimal pagoAcumuladoCXP;
    
    @Column(name = "FECHA_PAGO_CXP")
    private Date fechaPagoCxp;
    
    @Column(name = "FOLIO_PAGOS")
    private Integer folioPagos;
    
    @Column(name = "FACTURABLE")
    private String facturable;
    
    
    @Column(name = "PAGO_CIE")
    private String pagoCie;

    
    @Column(name = "REFERENCIA_CIE")
    private String refenciaCie;

    
    @Column(name = "CONCEPTO_CIE")
    private String conceptoCie;
    
    @Column(name = "ARCHIVO")
    private String archivo;
    
    @Column(name = "RUTA")
    private String ruta;
    
    @Column(name = "ID_PROVEEDOR")
    private String idProveedor;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "ID_TIPO_GASTO")
    private Integer idTipoGasto;
    
    @Column(name = "FECHA_CONTAB_PROV")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaContabProv;
    
    @Column(name = "ID_TIPO_NEGOCIO")
    private String idTipoNegocio;
    
    @Column(name = "ID_PAIS_CXP")
    private String idPaisCxp;
    
    @Column(name = "MONTO_REST_PROVISION")
    private Double montoRestProvision;
    
    @Column(name = "RETENCION_RENTA")
    private Double retencionRenta;
    
    @Column(name = "RETENCION_IVA")
    private Double retencionIva;
    
    @Column(name = "ID_SERVICIO")
    private Integer idServicio;
    
    @Column(name = "IMPORTE_NOTA")
    private BigDecimal importeNota;
    
    @Column(name = "FOLIO_GASTO")
    private String folioGasto;
    
      @Column(name = "USUARIO_CANCELA")
    private String usuarioCancela;
    
    @Column(name = "DESCRIPCION_CANCELA")
    private String descripcionCancela;
    
    @Column(name = "FECHA_CANCELA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCancela;
    
    @Column(name = "VIATICOS")
    private String viaticos;
    
    @Column(name = "TIPO_FACTURA")
    private String tipoFactura;

    @Column(name = "FECHA_CASH_FLOW")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCashFlow;
    
    @Column(name = "TIPO_PAGO")
    private String tipoPago;
    
    @Column(name = "TOTAL_A_PAGAR")
    private BigDecimal totalAPagar;
    
    @Column(name = "REFERENCIA_PAGO")
    private String referenciaPago;


    public ErpCpOtras() {
    }

    public ErpCpOtrasId getId() {
        return id;
    }

    public void setId(ErpCpOtrasId id) {
        this.id = id;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(String idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getOtrosImpuestos() {
        return otrosImpuestos;
    }

    public void setOtrosImpuestos(Double otrosImpuestos) {
        this.otrosImpuestos = otrosImpuestos;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(Double tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public String getConceptoCxp() {
        return conceptoCxp;
    }

    public void setConceptoCxp(String conceptoCxp) {
        this.conceptoCxp = conceptoCxp;
    }

    public String getCtoCxp() {
        return ctoCxp;
    }

    public void setCtoCxp(String ctoCxp) {
        this.ctoCxp = ctoCxp;
    }

    public Date getFechaVencCxp() {
        return fechaVencCxp;
    }

    public void setFechaVencCxp(Date fechaVencCxp) {
        this.fechaVencCxp = fechaVencCxp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFolioPagos() {
        return folioPagos;
    }

    public void setFolioPagos(Integer folioPagos) {
        this.folioPagos = folioPagos;
    }

    public String getEstatusCxp() {
        return estatusCxp;
    }

    public void setEstatusCxp(String estatusCxp) {
        this.estatusCxp = estatusCxp;
    }

    public BigDecimal getPagoAcumuladoCXP() {
        return pagoAcumuladoCXP;
    }

    public void setPagoAcumuladoCXP(BigDecimal pagoAcumuladoCXP) {
        this.pagoAcumuladoCXP = pagoAcumuladoCXP;
    }

    public String getFacturable() {
        return facturable;
    }

    public void setFacturable(String facturable) {
        this.facturable = facturable;
    }

    public String getPagoCie() {
        return pagoCie;
    }

    public void setPagoCie(String pagoCie) {
        this.pagoCie = pagoCie;
    }

    public String getRefenciaCie() {
        return refenciaCie;
    }

    public void setRefenciaCie(String refenciaCie) {
        this.refenciaCie = refenciaCie;
    }

    public String getConceptoCie() {
        return conceptoCie;
    }

    public void setConceptoCie(String conceptoCie) {
        this.conceptoCie = conceptoCie;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Date getFechaPagoCxp() {
        return fechaPagoCxp;
    }

    public void setFechaPagoCxp(Date fechaPagoCxp) {
        this.fechaPagoCxp = fechaPagoCxp;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(Integer idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public Date getFechaContabProv() {
        return fechaContabProv;
    }

    public void setFechaContabProv(Date fechaContabProv) {
        this.fechaContabProv = fechaContabProv;
    }

    public String getIdTipoNegocio() {
        return idTipoNegocio;
    }

    public void setIdTipoNegocio(String idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }

    public String getIdPaisCxp() {
        return idPaisCxp;
    }

    public void setIdPaisCxp(String idPaisCxp) {
        this.idPaisCxp = idPaisCxp;
    }

    public Double getMontoRestProvision() {
        return montoRestProvision;
    }

    public void setMontoRestProvision(Double montoRestProvision) {
        this.montoRestProvision = montoRestProvision;
    }

    public Double getRetencionRenta() {
        return retencionRenta;
    }

    public void setRetencionRenta(Double retencionRenta) {
        this.retencionRenta = retencionRenta;
    }

    public Double getRetencionIva() {
        return retencionIva;
    }

    public void setRetencionIva(Double retencionIva) {
        this.retencionIva = retencionIva;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public BigDecimal getImporteNota() {
        return importeNota;
    }

    public void setImporteNota(BigDecimal importeNota) {
        this.importeNota = importeNota;
    }

    public String getFolioGasto() {
        return folioGasto;
    }

    public void setFolioGasto(String folioGasto) {
        this.folioGasto = folioGasto;
    }

    public String getViaticos() {
        return viaticos;
    }

    public void setViaticos(String viaticos) {
        this.viaticos = viaticos;
    }

    public String getUsuarioCancela() {
        return usuarioCancela;
    }

    public void setUsuarioCancela(String usuarioCancela) {
        this.usuarioCancela = usuarioCancela;
    }

    public String getDescripcionCancela() {
        return descripcionCancela;
    }

    public void setDescripcionCancela(String descripcionCancela) {
        this.descripcionCancela = descripcionCancela;
    }

    public Date getFechaCancela() {
        return fechaCancela;
    }

    public void setFechaCancela(Date fechaCancela) {
        this.fechaCancela = fechaCancela;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public Date getFechaCashFlow() {
        return fechaCashFlow;
    }

    public void setFechaCashFlow(Date fechaCashFlow) {
        this.fechaCashFlow = fechaCashFlow;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    
    
    

}
