/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dto;

/**
 *
 * @author Feraz3
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Ing. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetControlPolizaDTO {
    
      
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
    
    @JsonProperty("FECHA")
    public String fecha;
    
    @JsonProperty("NUMERO")
    public String numero;
    
    @JsonProperty("EXISTE_CUENTA")
    public String existeCuenta;
    
    @JsonProperty("SEC")
    public String sec;
    
    @JsonProperty("ID")
    public String id;
    
     @JsonProperty("id")
    public String id2;
    
    @JsonProperty("CC")
    public String cc;
    
     @JsonProperty("C_COSTOS")
    public String cCostos;
    
    @JsonProperty("CT")
    public String ct;
    
     @JsonProperty("CUENTA")
    public String cuenta;
     @JsonProperty("CUENTA_ALIAS")
    public String cuentaAlias;
     
     @JsonProperty("NOMCUENTA")
    public String nomCuenta;
     
     @JsonProperty("DESCRIPCION")
    public String descripcion;
     
     @JsonProperty("REFERENCIA")
    public String referencia;
     
      @JsonProperty("REFERENCIA2")
    public String referencia2;
      
      
      @JsonProperty("CHEQUE")
    public String cheque;
      
       @JsonProperty("RFC")
    public String rfc;
       
       @JsonProperty("CARGOS")
    public String cargos;
       
        @JsonProperty("ABONOS")
    public String abonos;
        
    @JsonProperty("CARGOS_BASE")
    public String cargosBase;
       
    @JsonProperty("ABONOS_BASE")
    public String abonosBase;
        
         @JsonProperty("FECHA_DOCUMENTO")
    public String fechaDocumento;
         
         @JsonProperty("INDICADOR")
    public String indicador;
         
         @JsonProperty("FECHA_CAP")
    public String fechaCap;
         
          @JsonProperty("ESTATUS")
    public String estatus;
          
           @JsonProperty("HORA")
    public String hora;
     
      @JsonProperty("CTO_TRABAJO")
    public String ctoTrabajo;
      
//       @JsonProperty("id")
//    public String id3;
         
     @JsonProperty("msgErr")
    public String msgErr;
     
     @JsonProperty("NUM_DIOT")
     public String numDiot;
     
      @JsonProperty("ID_TRANSACCION")
     public String idTransaccion;
     
   
    
}
