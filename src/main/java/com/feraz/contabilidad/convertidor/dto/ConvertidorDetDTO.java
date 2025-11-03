/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.dto;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author 55555
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertidorDetDTO {
    
     @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("ORIGEN")
    public String origen;
    
    @JsonProperty("IDCONCGASTO")
    public String idConCGastos;
    
    @JsonProperty("NO_CASO")
    public String noCaso;
    
    @JsonProperty("IDCAMPO")
    public String idCampo;
    
    @JsonProperty("SEC")
    public int sec;
    
    @JsonProperty("CUENTA")
    public String cuenta;
    
     @JsonProperty("T_APLICACION")
    public String tAplicacion;
    
    @JsonProperty("IDOPERACION")
    public String idOperacion;
    
     @JsonProperty("PROYECTO")
    public String proyecto;
    
    @JsonProperty("PARTIDA")
    public String partida;
    
     @JsonProperty("IDTPAGO")
    public String idTPago;
     
     @JsonProperty("T_PERSONA")
    public String tPersona;
      
     @JsonProperty("DESCRIPCION")
    public String descripcion;
      
     @JsonProperty("REFERENCIA")
    public String referencia;
     
     @JsonProperty("REFERENCIA2")
    public String referencia2;
     
     @JsonProperty("OPERACIONES")
     public String operaciones;
     
     @JsonProperty("RFC")
     public String rfc;
     
     @JsonProperty("CONCEPTO_DIOT")
     public String conceptoDiot;
     
     @JsonProperty("CC")
     public String c_costos;
     
     @JsonProperty("ORDEN")
     public String orden;
      
       @JsonProperty("id")
    public String id2;
       
//         @JsonProperty("msgErr")
//    public String msgErr;
//     
  
}
