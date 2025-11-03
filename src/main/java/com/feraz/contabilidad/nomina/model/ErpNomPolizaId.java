/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.nomina.model;

/**
 *
 * @author Feraz3
 */



import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ing. JAMH
 */

@Embeddable
public class ErpNomPolizaId implements java.io.Serializable{
    
     @Column(name = "ID")
     private int id;
     
     public ErpNomPolizaId(){
     }
     
     public ErpNomPolizaId(int id){
         
         this.id = id;
     }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
     
     
    
}
