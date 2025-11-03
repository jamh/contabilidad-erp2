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
public class FoliosSolFondosDTO {
    
    @JsonProperty("FOLIO_PAGOS")
    public String folioPagos;
    
    @JsonProperty("COMPANIA_FOLIO_SOL")
    public String compania;
    
    @JsonProperty("ID_SOLICITUD_FOLIO_SOL")
    public String idSolicitud;
    
    @JsonProperty("USUARIO_SOLICITA_FOLIO_SOL")
    public String usuarioSolicito;
    
    @JsonProperty("USUARIO_AUTORIZA_FOLIO_SOL")
    public String usuarioAutorizo;
    
    @JsonProperty("USUARIO_SOLICITO_NOM_FOLIO_SOL")
    public String usuarioSolicitoNom;
    
    @JsonProperty("FECHA_DE_SOLICITUD_FOLIO_SOL")
    public String fechaSolicito;
    
    @JsonProperty("FECHA_DE_AUT_FOLIO_SOL")
    public String fechaDeAut;
    
    @JsonProperty("MONEDA_FOLIO_SOL")
    public String moneda;
    
    @JsonProperty("IMPORTE_REQUERIDO_FOLIO_SOL")
    public String importeRequerido;
    
    @JsonProperty("TIPO_FOLIO_SOL")
    public String tipoFolio;
    
    @JsonProperty("TIPO_FOLIO_NOM_SOL")
    public String tipoFolioNom;

    @JsonProperty("BANCO_FOLIO_SOL")
    public String banco;
    
    @JsonProperty("BANCO_FOLIO_NOM_SOL")
    public String bancoNom;
    
    @JsonProperty("CASA_DE_CAMBIO_FOLIO_SOL")
    public String casaDeCambio;
    
    @JsonProperty("CASA_DE_CAMBIO_FOLIO_NOM_SOL")
    public String casaDeCambioNom;

    @JsonProperty("MONEDA_DE_PAGO_FOLIO_SOL")
    public String monedaDePago;
       
     @JsonProperty("IMPORTE_DE_PAGO_FOLIO_SOL")
    public String importeDePago;
     
     @JsonProperty("SOLICITAR_A_CAJA_FOLIO_SOL")
    public String solicitarACaja;
     
     @JsonProperty("NOM_SOLICITAR_A_CAJA_FOLIO_SOL")
    public String solicitarACajaNom;
     
      @JsonProperty("NUMERO_DE_OPERACION_FOLIO_SOL")
    public String numeroOperacion;
      
       @JsonProperty("FECHA_DE_RETIRO_FOLIO_SOL")
    public String fechaRetiro;
 
}
