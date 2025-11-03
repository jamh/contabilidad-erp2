
package com.feraz.contabilidad.sat.electronica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransaccionNomPol {
    
     @JsonProperty("CUENTA")
    public String cuenta;
     
      @JsonProperty("CARGOS")
    public String cargos;
      
       @JsonProperty("ABONOS")
    public String abonos;
       
        @JsonProperty("DESCRIPCION")
    public String descripcion;

    @JsonProperty("NUM_CTA")
    public String numCta;

    @JsonProperty("DES_CTA_NOM")
    public String desCta;

    @JsonProperty("CONCEPTO")
    public String concepto;

    @JsonProperty("DEBE")
    public String debe;

    @JsonProperty("HABER")
    public String haber;

    @JsonProperty("SEC_POL")
    public String SEC;


}
