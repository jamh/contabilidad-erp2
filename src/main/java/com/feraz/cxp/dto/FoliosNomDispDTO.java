/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.dto;

/**
 *
 * @author FERAZ-14
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoliosNomDispDTO {
    
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("COMPANIA_NOM")
    public String companiaNom;
    
    @JsonProperty("TIPO_PAGO")
    public String tipoPago;
    
    @JsonProperty("NOM_TIPO_PAGO")
    public String nomTipoPago;
    
    @JsonProperty("GPO_PAGO")
    public String gpoPago;
    
    @JsonProperty("TIPO_NOM")
    public String tipoNom;
    
    @JsonProperty("PROCESO_ESPECIAL")
    public String procesoEspecial;

    @JsonProperty("NOMBRE_PRO_ESP_NOM")
    public String nombreProEspNom;
    
    @JsonProperty("FOLIO")
    public String folio;
    
    @JsonProperty("NUM_EMPLEADOS")
    public String numEmpleados;
    
    @JsonProperty("TOT_PAGO")
    public String totPago;
    
    @JsonProperty("AUTORIZADO")
    public String autorizado;
    
    @JsonProperty("AUTORIZA_CXP")
    public String autorizaCxp;
  
    @JsonProperty("ESTATUS")
    public String estatus;
    
    @JsonProperty("URL_ARCHIVO")
    public String urlArchivo;
     
    @JsonProperty("RUTA")
    public String ruta;
    
    @JsonProperty("NOMBRE_ARCHIVO")
    public String nombreArchivo;
    @JsonProperty("CALENDARIO")
    public String calendario;
    @JsonProperty("PERIODO")
    public String periodo;

        
    
}
