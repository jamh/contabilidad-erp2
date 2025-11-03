/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_POLIZASXFACTURAS")
public class ErpPolizasXFacturas implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10 )),

        @AttributeOverride(name = "numeroPol", column = @Column(name = "NUMERO_POL", nullable = false, length = 10 )),
        @AttributeOverride(name = "fechaPol", column = @Column(name = "FECHA_POL", nullable = false)),
        @AttributeOverride(name = "tipoPol", column = @Column(name = "TIPO_POL", nullable = false, length = 2)),
     //   @AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false, length = 10)),
        @AttributeOverride(name = "idFactura", column = @Column(name = "ID_FACTURA", nullable = false, length = 10)),
        @AttributeOverride(name = "operacion", column = @Column(name = "OPERACION", nullable = false, length = 5)),
    
        
        
    })
     //@JsonProperty("ID2")
    private ErpPolizasXFacturasId id;
     
         @Column(name = "COMPANIA_CFDI")
    private String companiaCfdi;
         
              @Column(name = "NUMERO_FE")
    private long numeroFe;
              
   @Column(name = "UUID")
    private String uuid;
   
       @Column(name = "FOLIO")
    private String folio;
       
           
    @Column(name = "ID_FACTURA")
    private String idFactura;
     
     public ErpPolizasXFacturas(){
     
     }
     
     public ErpPolizasXFacturas(ErpPolizasXFacturasId id){
         
         this.id = id;
         
     }

    public ErpPolizasXFacturasId getId() {
        return id;
    }

    public void setId(ErpPolizasXFacturasId id) {
        this.id = id;
    }

    public String getCompaniaCfdi() {
        return companiaCfdi;
    }

    public void setCompaniaCfdi(String companiaCfdi) {
        this.companiaCfdi = companiaCfdi;
    }

    public long getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(long numeroFe) {
        this.numeroFe = numeroFe;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }


     
    
  
    
}
