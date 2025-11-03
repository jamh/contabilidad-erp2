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

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtrasDTO {
    

    @JsonProperty("COMPANIA_OTR_ARC")
    public String compania;
    
    @JsonProperty("SEC_OTR_ARC")
    public String sec;
    
    @JsonProperty("NOMBRE_OTR_ARC")
    public String nombre;
    
    @JsonProperty("DESCRIPCION_OTR_ARC")
    public String descripcion;
    
    @JsonProperty("ID_OTR_ARC")
    public String id;
    
    @JsonProperty("PATH_OTR_ARC")
    public String path;
    
    @JsonProperty("URL_OTR_ARC")
    public String url;
    
    @JsonProperty("TIPO")
    public String tipo;

}

    

