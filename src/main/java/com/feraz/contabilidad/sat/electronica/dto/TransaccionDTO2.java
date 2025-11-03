package com.feraz.contabilidad.sat.electronica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;

/**
 *
 * @author Ing. JAMH
 */
public class TransaccionDTO2 {

    @JsonProperty("COMPANIA")
    @Column(name = "COMPANIA")
    private String compania;

    @JsonProperty("TIPO_POLIZA")
    @Column(name = "TIPO_POLIZA")
    private String tipoPoliza;

    @JsonProperty("FECHA")
    @Column(name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @JsonProperty("NUMERO")
    @Column(name = "NUMERO")
    private String numero;

    @JsonProperty("SEC")
    @Column(name = "SEC")
    private BigDecimal sec;

    @JsonProperty("C_COSTOS")
    @Column(name = "C_COSTOS")
    private String cCostos;

    @JsonProperty("DESCRIPCION")
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @JsonProperty("REFERENCIA")
    @Column(name = "REFERENCIA")
    private String referencia;

    @JsonProperty("CARGOS_BASE")
    @Column(name = "CARGOS_BASE")
    private BigDecimal cargosBase;

    @JsonProperty("ABONOS_BASE")
    @Column(name = "ABONOS_BASE")
    private BigDecimal abonosBase;

    @JsonProperty("CARGOS")
    @Column(name = "CARGOS")
    private BigDecimal cargos;

    @JsonProperty("ABONOS")
    @Column(name = "ABONOS")
    private BigDecimal abonos;

    @JsonProperty("RFC")
    @Column(name = "RFC")
    private String rfc;

    @JsonProperty("INDICADOR")
    @Column(name = "INDICADOR")
    private String indicador;

    @JsonProperty("FECHA_CAP")
    @Column(name = "FECHA_CAP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCap;

    @JsonProperty("CHEQUE")
    @Column(name = "CHEQUE")
    private String cheque;

    @JsonProperty("ESTATUS")
    @Column(name = "ESTATUS")
    private String estatus;

    @JsonProperty("HORA")
    @Column(name = "HORA")
    private String hora;

    @JsonProperty("CTO_TRABAJO")
    @Column(name = "CTO_TRABAJO")
    private String ctoTrabajo;

    @JsonProperty("REFERENCIA2")
    @Column(name = "REFERENCIA2")
    private String referencia2;

    @JsonProperty("FECHA_DOCUMENTO")
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDocumento;
    @JsonProperty("CUENTA")
    @Column(name = "CUENTA")
    private String cuenta;

    @JsonProperty("TC_PAGO")
    @Column(name = "TC_PAGO")
    private String tcPago;

    @JsonProperty("TC_PROV")
    @Column(name = "TC_PROV")
    private String tcProv;

    @JsonProperty("DIVISA")
    @Column(name = "DIVISA")
    private String divisa;

    @JsonProperty("NUM_CTA")
    public String numCta;

    @JsonProperty("DES_CTA_NOM")
    public String desCta;

    @JsonProperty("CONCEPTO")
    public String concepto;

    @JsonProperty("DEBE")
    public String debe;

    @JsonProperty("HABER")
    public String haber;

    @JsonProperty("SEC_POL")
    public String SEC;

    @JsonProperty("TIPO")
    public String tipo;

    @JsonProperty("UUID_CFDI")
    public String uuidCfdi;

   

    @JsonProperty("CFD_CBB_SERIE")
    public String cfdCbbSerie;

    @JsonProperty("CFD_CBB_NUMFOL")
    public String cfdCbbNumFol;

    @JsonProperty("NUM_FACT_EXT")
    public String numFactExt;

    @JsonProperty("TAX_ID")
    public String taxId;

    @JsonProperty("NUM")
    public String num;

    @JsonProperty("BAN_EMIS_NAL")
    public String banEmisNal;

    @JsonProperty("BAN_EMIS_EXT")
    public String banEmisExt;

    @JsonProperty("FECHA")
    public String fechaTr;

    @JsonProperty("BENEF")
    public String benef;

    @JsonProperty("CTA_ORI")
    public String ctaOri;

    @JsonProperty("BANCO_ORI_NAL")
    public String bancoOriNal;

    @JsonProperty("BANCO_ORI_EXT")
    public String bancoOriExt;

    @JsonProperty("CTA_DEST")
    public String ctaDest;

    @JsonProperty("BANCO_DEST_NAL")
    public String bancoDestNal;

    @JsonProperty("BANCO_DEST_EXT")
    public String bancoDestExt;

    @JsonProperty("MET_PAGO_POL")
    public String metPagoPol;

    @JsonProperty("MONTO_TOTAL")
    public String montoTotal;

    @JsonProperty("MONEDA")
    public String moneda;

    @JsonProperty("TIP_CAMB")
    public String tipCamb;

}
