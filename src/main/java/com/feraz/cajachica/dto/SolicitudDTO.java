/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author vavi
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudDTO {
    
    
    @JsonProperty("COMPANIA")
    public String compania;
      
    @JsonProperty("ID_SOLICITUD")
    public String idSolicitud;
    
    @JsonProperty("USUARIO_SOLICITA")
    public String usuarioSolicita;
    
    @JsonProperty("USUARIO_AUTORIZA")
    public String usuarioAutoriza;
    
    @JsonProperty("FECHA_DE_SOLICITUD")
    public String fechaDeSolicitud;
    
    @JsonProperty("FECHA_DE_AUTORIZACION")
    public String fechaDeAutorizacion;
    
    @JsonProperty("MONEDA")
    public String moneda;
    
    @JsonProperty("IMPORTE_REQUERIDO")
    public String importeRequerido;
    
    @JsonProperty("TIPO")
    public String tipo;
    
    @JsonProperty("BANCO")
    public String banco;
    
    @JsonProperty("CASA_DE_CAMBIO")
    public String casaDeCambio;
    
    @JsonProperty("MONEDA_DE_PAGO")
    public String monedaDePago;
    
    @JsonProperty("IMPORTE_DE_PAGO")
    public String importeDePago;
    
    @JsonProperty("SOLICITAR_A_CAJA")
    public String solicitarACaja;
    
    @JsonProperty("NOM_SOLICITAR_A_CAJA")
    public String nomSolicitarACaja;
    
    
    @JsonProperty("DESCRIPCION")
    public String descripcion;
    
    @JsonProperty("ID_AREA")
    public String idArea;
    
    @JsonProperty("NOM_AREA")
    public String nomArea;
    
    @JsonProperty("AUTORIZA_AREA")
    public String autorizaArea;
    
    @JsonProperty("NOM_AUTORIZA_AREA")
    public String nomAutorizaArea;
    
    @JsonProperty("NOM_SOLICITO")
    public String nomSolicito;
    
    @JsonProperty("NOM_AUT_AREA")
    public String nomAutArea;
    
    @JsonProperty("MOT_RECHAZO")
    public String motRechazo;
    
    
    

}
