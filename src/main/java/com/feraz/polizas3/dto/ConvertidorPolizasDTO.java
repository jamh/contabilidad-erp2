package com.feraz.polizas3.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author ING. JAMH
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertidorPolizasDTO {
    //{"CUENTA":"","NOMCUENTA":"","RFC":null,"IMPORTE":550,"TIPO_IMPORTE":"A","NOMIMPORTE":"ISR"}

    @JsonProperty("CUENTA")
    public String cuenta;
    
    @JsonProperty("CUENTA_ALIAS")
    public String cuentaAlias;
        
    @JsonProperty("NOMCUENTA")
    public String nomcuenta;
    
    @JsonProperty("RFC")
    public String rfc;
    
    @JsonProperty("IMPORTE")
    public String importe;

    @JsonProperty("TIPO_IMPORTE")
    public String tipoImporte;
    
    @JsonProperty("NOMIMPORTE")
    public String nomImporte;
    
     @JsonProperty("CARGOS")
    public String cargos;
       
    @JsonProperty("ABONOS")
    public String abonos;
    

}
