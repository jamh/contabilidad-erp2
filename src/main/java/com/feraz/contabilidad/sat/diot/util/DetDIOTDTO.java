/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.diot.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class DetDIOTDTO {
    



    @JsonProperty("ID")
    public String id;
    @JsonProperty("COMPANIA")
    public String compania;
    @JsonProperty("CONCEPTO")
    public String concepto;
    @JsonProperty("NOMBRE")
    public String nombre;
    @JsonProperty("TIPO_POLIZA")
    public String tipoPoliza;
    @JsonProperty("FECHA")
    public String fecha;

    @JsonProperty("NUMERO")
    public String numero;
    @JsonProperty("SEC")
    public String sec;
    @JsonProperty("CUENTA")
    public String cuenta;
    @JsonProperty("IMPORTE")
    public String importe;
    
    @JsonProperty("CARGOS")
    public String cargos;
    
     @JsonProperty("ABONOS")
    public String abonos;
   

}

    

