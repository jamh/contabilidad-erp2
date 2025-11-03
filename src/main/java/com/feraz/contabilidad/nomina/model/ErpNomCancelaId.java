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


@Embeddable

public class ErpNomCancelaId implements java.io.Serializable{
    
     @Column(name = "ID")
     private int id;
     
      public ErpNomCancelaId(){
         
        
     
     }
     public ErpNomCancelaId(int id){
         
         this.id = id;
     
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
     
     
    
}
