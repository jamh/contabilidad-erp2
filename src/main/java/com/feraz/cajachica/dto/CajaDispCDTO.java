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
public class CajaDispCDTO {
    
    @JsonProperty("COMPANIA_DEU_VIA")
    public String compania;
    
    @JsonProperty("COMISION_DEU_VIA")
    public String comision;
    
    @JsonProperty("F_INI_COMISION_DEU_VIA")
    public String fIniComision;
    
    @JsonProperty("F_FIN_COMISION_DEU_VIA")
    public String fFinComision;
    
    @JsonProperty("T_MONEDA_DEU_VIA")
    public String moneda;
    
    @JsonProperty("ESTATUS_DEU_VIA")
    public String estatus;
    
    @JsonProperty("IMPORTE_DEU_VIA")
    public String importe;
    
    @JsonProperty("EMPLEADO_DEU_VIA")
    public String empleado;
    
    @JsonProperty("NOM_EMPLEADO_DEU_VIA")
    public String nomEmpleado;
    
    @JsonProperty("DEPARTAMENTO_DEU_VIA")
    public String departamento;
    
    @JsonProperty("NOM_DEPARTAMENTO_DEU_VIA")
    public String nomDepartamento;
    
    @JsonProperty("TOT_COMP_CAJA_VIA")
    public String totComp;
    
    @JsonProperty("ID_CAJA_DEU_VIA")
    public String idCaja;

    @JsonProperty("SEC_CAJA_VIA")
    public String secCaja;

    
}
