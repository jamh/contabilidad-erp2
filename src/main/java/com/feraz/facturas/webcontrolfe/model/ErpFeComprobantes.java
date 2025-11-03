/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_FE_COMPROBANTES")
public class ErpFeComprobantes implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "numero", column = @Column(name = "NUMERO", nullable = false))
    })
    private ErpFeComprobantesId id;

    @Column(name = "FOLIO")
    //@JsonProperty("FOLIO")
    private String folio;

    @Column(name = "FOLIO_FISCAL_ORIG")
    //@JsonProperty("FOLIO_FISCAL_ORIG")
    private String folioFiscalOrig;

    @Column(name = "FECHA")
    //@JsonProperty("FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Column(name = "FECHA_FOLIO_FISCAL_ORIG")
    //@JsonProperty("FECHA_FOLIO_FISCAL_ORIG")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFolioFiscalOrig;

    @Column(name = "SERIE")
    //@JsonProperty("SERIE")
    private String serie;

    @Column(name = "SERIE_FOLIO_FISCAL_ORIG")
    //@JsonProperty("SERIE_FOLIO_FISCAL_ORIG")
    private String serieFolioFiscalOrig;

    @Column(name = "SUBTOTAL")
    //@JsonProperty("SUBTOTAL")
    private BigDecimal subTotal;

    @Column(name = "TOTAL")
    //@JsonProperty("TOTAL")
    private BigDecimal total;

    @Column(name = "TIPO_CAMBIO")
    //@JsonProperty("TIPO_CAMBIO")
    private String tipoCambio;

    @Column(name = "TIPO_DE_COMPROBANTE")
    //@JsonProperty("TIPO_DE_COMPROBANTE")
    private String tipoDeComprobante;

    @Column(name = "CONDICIONES_DE_PAGO")
    //@JsonProperty("CONDICIONES_DE_PAGO")
    private String condicionesDePago;

    @Column(name = "DESCUENTO")
    //@JsonProperty("DESCUENTO")
    private BigDecimal descuento;

    @Column(name = "FORMA_DE_PAGO")
    //@JsonProperty("FORMA_DE_PAGO")
    private String formaDePago;

    @Column(name = "METODO_DE_PAGO")
    //@JsonProperty("METODO_DE_PAGO")
    private String metodoDePago;

    @Column(name = "MONEDA")
    //@JsonProperty("MONEDA")
    private String moneda;

    @Column(name = "MONTO_FOLIO_FISCAL_ORIG")
    //@JsonProperty("MONTO_FOLIO_FISCAL_ORIG")
    private BigDecimal montoFolioFiscalOrig;

    @Column(name = "LUGAR_EXPEDICION")
    //@JsonProperty("LUGAR_EXPEDICION")
    private String lugarExpedicion;

    @Column(name = "MOTIVO_DESCUENTO")
    //@JsonProperty("MOTIVO_DESCUENTO")
    private String motivoDescuento;

    @Column(name = "NO_CERTIFICADO")
    //@JsonProperty("NO_CERTIFICADO")
    private String noCertificado;

    @Column(name = "NUM_CTA_PAGO")
    //@JsonProperty("NUM_CTA_PAGO")
    private String numCtaPago;

    @Column(name = "SELLO")
    //@JsonProperty("SELLO")
    private String sello;

    @Column(name = "VERSION")
    //@JsonProperty("VERSION")
    private String version;

    @Column(name = "CERTIFICADO")
    //@JsonProperty("CERTIFICADO")
    private String certificado;

    @Column(name = "IDCONCGASTO")
    private String idConCGastos;

    @Column(name = "DIR_XML")
    //@JsonProperty("CERTIFICADO")
    private String dirXML;

    @Column(name = "ORIGEN")
    private String origen;

    @Column(name = "XML")
    private String xml;

    @Column(name = "RFC")
    private String rfc;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "NO_CERT_SAT")
    private String noCertSat;

    @Column(name = "SELLO_CFD")
    private String selloCfd;

    @Column(name = "SELLO_SAT")
    private String selloSat;

    @Column(name = "VERSION_COMPLE")
    private String versionComple;

    @Column(name = "FECHA_TIMBRADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTimbrado;

    @Column(name = "PDF")
    private String pdf;

    @Column(name = "DIR_PDF")
    private String dirPdf;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "TIPO_CARGA")
    private String tipoCarga;

    @Column(name = "CONCEPTO_CXP")
    private String conceptoCxp;

    @Column(name = "CTO_CXP")
    private String ctoCxp;

    @Column(name = "FECHA_VENC_CXP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencCxp;

    @Column(name = "ESTATUSV")
    private String estatusV;

    @Column(name = "ESTATUS_CXP")
    private String estatusCxp;

    @Column(name = "FOLIO_PAGOS")
    private Integer folioPagos;

    @Column(name = "SALDOS_CXP")
    private BigDecimal saldosCxp;

    @Column(name = "MONEDA_PAGO")
    private String monedaPago;

    @Column(name = "PAGO_CIE", nullable = true)
    private Integer pagoCie;

    @Column(name = "REFERENCIA_CIE")
    private String referenciaCie;

    @Column(name = "REEMBOLSO_CLIEPROV")
    private String reembolsoClieprov;

    @Column(name = "CONCEPTO_CIE")
    private String conceptoCie;

    @Column(name = "TOTAL_LETRA")
    private String totalLetra;

    @Column(name = "DESCRIPCION_CANCELACION")
    private String descripcionCancelacion;

    @Column(name = "TOTAL_A_PAGAR")
    private BigDecimal totalAPagar;

    @Column(name = "TIPO_PAGO")
    private String tipoPago;

    @Column(name = "PAGO_ACUMULADO_CXP")
    private BigDecimal pagoAcumuladoCxp;

    @Column(name = "USO_CFDI")
    private String usoCCFDI;

    @Column(name = "ID_PROYECTO")
    private String idProyecto;

    @Column(name = "ID_TIPO_GASTO")
    private String idTipoGasto;

    @Column(name = "DEDUCIBLE")
    private String deducible;

    @Column(name = "IMPORTE_DEDUCIBLE")
    private BigDecimal importeDeducible;

    @Column(name = "FECHA_CANCELACION")
    private Date fechaCancelacion;

    @Column(name = "USUARIO_CANCELO")
    private String usuarioCancelo;
    
    @Column(name = "FECHA_CONTAB_PROV")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaContabProv;
    
    @Column(name = "ID_PAIS_CXP")
    private String idPaisCXP;
    
    @Column(name = "ID_TIPO_NEGOCIO")
    private String idTipoNegocio;
    
    @Column(name = "FECHA_CASH_FLOW")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCashFlow;

    public ErpFeComprobantes() {
    }

    public ErpFeComprobantes(ErpFeComprobantesId id) {

        this.id = id;

    }

    public ErpFeComprobantesId getId() {
        return id;
    }

    public String getFolio() {
        return folio;
    }

    public String getFolioFiscalOrig() {
        return folioFiscalOrig;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getFechaFolioFiscalOrig() {
        return fechaFolioFiscalOrig;
    }

    public String getSerie() {
        return serie;
    }

    public String getSerieFolioFiscalOrig() {
        return serieFolioFiscalOrig;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public String getCondicionesDePago() {
        return condicionesDePago;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public String getMoneda() {
        return moneda;
    }

    public BigDecimal getMontoFolioFiscalOrig() {
        return montoFolioFiscalOrig;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public String getMotivoDescuento() {
        return motivoDescuento;
    }

    public String getNoCertificado() {
        return noCertificado;
    }

    public String getNumCtaPago() {
        return numCtaPago;
    }

    public String getSello() {
        return sello;
    }

    public String getVersion() {
        return version;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setId(ErpFeComprobantesId id) {
        this.id = id;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFolioFiscalOrig(String folioFiscalOrig) {
        this.folioFiscalOrig = folioFiscalOrig;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechaFolioFiscalOrig(Date fechaFolioFiscalOrig) {
        this.fechaFolioFiscalOrig = fechaFolioFiscalOrig;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setSerieFolioFiscalOrig(String serieFolioFiscalOrig) {
        this.serieFolioFiscalOrig = serieFolioFiscalOrig;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public void setCondicionesDePago(String condicionesDePago) {
        this.condicionesDePago = condicionesDePago;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setMontoFolioFiscalOrig(BigDecimal montoFolioFiscalOrig) {
        this.montoFolioFiscalOrig = montoFolioFiscalOrig;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public void setMotivoDescuento(String motivoDescuento) {
        this.motivoDescuento = motivoDescuento;
    }

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    public void setNumCtaPago(String numCtaPago) {
        this.numCtaPago = numCtaPago;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getIdConCGastos() {
        return idConCGastos;
    }

    public String getOrigen() {
        return origen;
    }

    public void setIdConCGastos(String idConCGastos) {
        this.idConCGastos = idConCGastos;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public void setDirXML(String dirXML) {
        this.dirXML = dirXML;
    }

    public String getDirXML() {
        return dirXML;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNoCertSat() {
        return noCertSat;
    }

    public String getSelloCfd() {
        return selloCfd;
    }

    public String getSelloSat() {
        return selloSat;
    }

    public String getVersionComple() {
        return versionComple;
    }

    public Date getFechaTimbrado() {
        return fechaTimbrado;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setNoCertSat(String noCertSat) {
        this.noCertSat = noCertSat;
    }

    public void setSelloCfd(String selloCfd) {
        this.selloCfd = selloCfd;
    }

    public void setSelloSat(String selloSat) {
        this.selloSat = selloSat;
    }

    public void setVersionComple(String versionComple) {
        this.versionComple = versionComple;
    }

    public void setFechaTimbrado(Date fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    public String getPdf() {
        return pdf;
    }

    public String getDirPdf() {
        return dirPdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public void setDirPdf(String dirPdf) {
        this.dirPdf = dirPdf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
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

    public String getEstatusV() {
        return estatusV;
    }

    public void setEstatusV(String estatusV) {
        this.estatusV = estatusV;
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

    public BigDecimal getSaldosCxp() {
        return saldosCxp;
    }

    public void setSaldosCxp(BigDecimal saldosCxp) {
        this.saldosCxp = saldosCxp;
    }

    public String getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(String monedaPago) {
        this.monedaPago = monedaPago;
    }

    public Integer getPagoCie() {
        return pagoCie;
    }

    public void setPagoCie(Integer pagoCie) {

        this.pagoCie = pagoCie;
    }

    public String getReferenciaCie() {
        return referenciaCie;
    }

    public void setReferenciaCie(String referenciaCie) {
        this.referenciaCie = referenciaCie;
    }

    public String getReembolsoClieprov() {
        return reembolsoClieprov;
    }

    public void setReembolsoClieprov(String reembolsoClieprov) {
        this.reembolsoClieprov = reembolsoClieprov;
    }

    public String getConceptoCie() {
        return conceptoCie;
    }

    public void setConceptoCie(String conceptoCie) {
        this.conceptoCie = conceptoCie;
    }

    public String getTotalLetra() {
        return totalLetra;
    }

    public void setTotalLetra(String totalLetra) {
        this.totalLetra = totalLetra;
    }

    public String getDescripcionCancelacion() {
        return descripcionCancelacion;
    }

    public void setDescripcionCancelacion(String descripcionCancelacion) {
        this.descripcionCancelacion = descripcionCancelacion;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getPagoAcumuladoCxp() {
        return pagoAcumuladoCxp;
    }

    public void setPagoAcumuladoCxp(BigDecimal pagoAcumuladoCxp) {
        this.pagoAcumuladoCxp = pagoAcumuladoCxp;
    }

    public String getUsoCCFDI() {
        return usoCCFDI;
    }

    public void setUsoCCFDI(String usoCCFDI) {
        this.usoCCFDI = usoCCFDI;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(String idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public String getDeducible() {
        return deducible;
    }

    public void setDeducible(String deducible) {
        this.deducible = deducible;
    }

    public BigDecimal getImporteDeducible() {
        return importeDeducible;
    }

    public void setImporteDeducible(BigDecimal importeDeducible) {
        this.importeDeducible = importeDeducible;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getUsuarioCancelo() {
        return usuarioCancelo;
    }

    public void setUsuarioCancelo(String usuarioCancelo) {
        this.usuarioCancelo = usuarioCancelo;
    }

    public Date getFechaContabProv() {
        return fechaContabProv;
    }

    public void setFechaContabProv(Date fechaContabProv) {
        this.fechaContabProv = fechaContabProv;
    }

    public String getIdPaisCXP() {
        return idPaisCXP;
    }

    public void setIdPaisCXP(String idPaisCXP) {
        this.idPaisCXP = idPaisCXP;
    }

    public String getIdTipoNegocio() {
        return idTipoNegocio;
    }

    public void setIdTipoNegocio(String idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }

    public Date getFechaCashFlow() {
        return fechaCashFlow;
    }

    public void setFechaCashFlow(Date fechaCashFlow) {
        this.fechaCashFlow = fechaCashFlow;
    }

    
    
}
