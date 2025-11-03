/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author vavi
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiciosCXPDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("SEC")
    public String sec;
    
    @JsonProperty("CANTIDAD")
    public String cantidad;
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("CLAVE_UNIDAD")
    public String claveUnidad;
    
    @JsonProperty("CLAVE_PROD_SERV")
    public String claveProdServ;
    
    @JsonProperty("ID_PRODUCTXSERVICIO")
    public String idProductxServicio;
    
    @JsonProperty("NOM_PRODUCTXSERVICIO")
    public String nomProductxServicio;
    
    
    
    
}
