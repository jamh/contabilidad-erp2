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
public class CajaChicaDTO {
    
     @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("ID")
    public String id;
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("CTO_CTO")
    public String ctoCto;
    
    @JsonProperty("ID_TIPO_NEGOCIO")
    public String idTipoNegocio;
    
    @JsonProperty("ID_PAIS_CXP")
    public String idPaisCxp;

    @JsonProperty("IMPORTE_RESTANTE")
    public String importeRestante;
    
    @JsonProperty("NOMBRE_CTO")
    public String nombreCto;
    
    @JsonProperty("NOMBRE_NEGOCIO")
    public String nombreNegocio;
    
    @JsonProperty("NOMBRE_PAIS")
    public String nombrePais;

    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("NOM_BANCO")
    public String nomBanco;
    
    @JsonProperty("ESTATUS")
    public String estatus;
    
    @JsonProperty("NOM_ESTATUS")
    public String nomEstatus;

    
    
}

