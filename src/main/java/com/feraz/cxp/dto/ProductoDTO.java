/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.dto;

/**
 *
 * @author 55555
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {
    
      
    @JsonProperty("COMPANIA")
    public String compania;
    
    @JsonProperty("PROVEEDOR")
    public String proveedor;
    
    @JsonProperty("ID_PRODUCTO")
    public String idProductor;
    
    @JsonProperty("NOMBRE")
    public String nombre;
    
    @JsonProperty("CODIGO")
    public String codigo;
    
    @JsonProperty("UNIDAD_MEDIDA")
    public String unidadMedida;
    
    @JsonProperty("PRECIO_SIN_IVA")
    public String precioSinIva;
    
    @JsonProperty("CODIGO_PROVEEDOR")
    public String codigoProveedor;
    
    

    
}
