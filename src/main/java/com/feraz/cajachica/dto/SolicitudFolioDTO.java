/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cajachica.dto;

/**
 *
 * @author LENOVO
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudFolioDTO {
        
        @JsonProperty("COMPANIA_FOLIO")
    public String compania;
        
        @JsonProperty("ID_SOLICITUD_FOLIO")
    public String idSolicitud;
    
        @JsonProperty("USUARIO_SOLICITA_FOLIO")
    public String usuarioSolicito;
    
        
        @JsonProperty("USUARIO_AUTORIZA_FOLIO")
    public String usuarioAutorizo;
    
        
        @JsonProperty("USUARIO_SOLICITO_NOM_FOLIO")
    public String usuarioSolicitoNom;
    
        @JsonProperty("FECHA_DE_SOLICITUD_FOLIO")
    public String fechaSol;
    
        @JsonProperty("FECHA_DE_AUTORIZACION_FOLIO")
    public String fechaAutorizacion;
    
        @JsonProperty("MONEDA_FOLIO")
    public String moneda;
    
        @JsonProperty("IMPORTE_REQUERIDO_FOLIO")
    public String importeRequerido;
    
        @JsonProperty("TIPO_FOLIO")
    public String tipoFolio;
    
        @JsonProperty("TIPO_FOLIO_NOM")
    public String tipoFolioNom;
    
        @JsonProperty("BANCO_FOLIO")
    public String bancoFolio;
    
        @JsonProperty("BANCO_FOLIO_NOM")
    public String bancoFolioNom;
    
        @JsonProperty("CASA_DE_CAMBIO_FOLIO")
    public String casaDeCambio;
        
        @JsonProperty("CASA_DE_CAMBIO_FOLIO_NOM")
    public String casaDeCambioNom;
        
         @JsonProperty("MONEDA_DE_PAGO_FOLIO")
    public String monedaDePago;
         
          @JsonProperty("IMPORTE_DE_PAGO_FOLIO")
    public String importeDePago;
           @JsonProperty("SOLICITAR_A_CAJA_FOLIO")
    public String solicitarACaja;
            @JsonProperty("NOM_SOLICITAR_A_CAJA_FOLIO")
    public String nomSolicituarACaja;
            
            @JsonProperty("NUMERO_DE_OPERACION_FOLIO")
    public String numeroDeOperacionFolio;
            
            @JsonProperty("FECHA_DE_RETIRO_FOLIO")
    public String fechaDeRetiroFolio;
            
            @JsonProperty("FOLIO_PAGOS")
    public String folioPagos;
            
            

}
