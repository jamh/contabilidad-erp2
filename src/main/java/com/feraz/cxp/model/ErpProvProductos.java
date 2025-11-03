/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.cxp.model;

/**
 *
 * @author 55555
 */

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ERP_PROV_PRODUCTOS")
public class ErpProvProductos implements java.io.Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "proveedor", column = @Column(name = "PROVEEDOR", nullable = false, length = 12)),
        @AttributeOverride(name = "idProducto", column = @Column(name = "ID_PRODUCTO", nullable = false))

    })
     
     private ErpProvProductosId id;
    
     
      @Column(name = "NOMBRE")
      private String nombre;
      
      @Column(name = "CODIGO")
      private String codigo;
      
      @Column(name = "UNIDAD_MEDIDA")
      private String unidadMedida;
      
      @Column(name = "PRECIO_SIN_IVA")
      private BigDecimal precioSinIva;
      
      @Column(name = "CODIGO_PROVEEDOR")
      private String codigoProveedor;
      
      
      
      
      public ErpProvProductos(){}
      
      public ErpProvProductos(ErpProvProductosId id){
          
          this.id = id;
      
      
      }

    public ErpProvProductosId getId() {
        return id;
    }

    public void setId(ErpProvProductosId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(BigDecimal precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }
      
      
    
}
