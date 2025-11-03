/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.util;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CajaDetDTO {
    
     @JsonProperty("COMPANIA")
    public String compania;
     
     @JsonProperty("ID_CAJA")
    public String idCaja;
     
     @JsonProperty("SEC")
    public String sec;
    
     @JsonProperty("USUARIO_CAJA")
    public String usuarioCaja;
     
     @JsonProperty("NOMBRE_USUARIO_CAJA")
    public String nombreUsuarioCaja;
     
     @JsonProperty("IMPORTE_DET")
    public String importeDet;
     
     @JsonProperty("MONEDA")
    public String moneda;
     
     @JsonProperty("CTO_CTO")
    public String ctocto;
     
     @JsonProperty("NOM_CTO_CTO_DET")
    public String nomCtoCtoDet;
     
     @JsonProperty("ID_TIPO_NEGOCIO")
    public String idTipoNegocio;
     
    @JsonProperty("NOM_NEGOCIO_DET")
    public String nomNegocioDet;
     
    @JsonProperty("ID_PAIS_CXP")
    public String idPaisCxp;
    
    @JsonProperty("NOM_PAIS_DET")
    public String nomPaisDet;
    
    @JsonProperty("FECHA")
    public String fecha;

    @JsonProperty("USUARIO_CAP")
    public String usuarioCap;
      
    @JsonProperty("ESTATUS")
    public String estatus;
    
    @JsonProperty("NOM_ESTATUS")
    public String nomEstatus;

    
}
