package com.feraz.contabilidad.sat.electronica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransaccionDTO {
    
     @JsonProperty("CUENTA")
    public String cuenta;
     
      @JsonProperty("CARGOS")
    public String cargos;
      
       @JsonProperty("ABONOS")
    public String abonos;
       
        @JsonProperty("DESCRIPCION")
    public String descripcion;

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

    @JsonProperty("RFC")
    public String rfc;

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
    public String fecha;

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
    
    @JsonProperty("MET_PAGO_AUX")
    public String metPagoAux;

}
