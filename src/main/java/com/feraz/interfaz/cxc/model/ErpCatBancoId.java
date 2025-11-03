/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.interfaz.cxc.model;

/**
 *
 * @author vavi
 */

   import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpCatBancoId implements java.io.Serializable{
    
     @Column (name = "COMPANIA")
    private String compania;

     
      @Column (name = "BANCO")
    private String banco;
      
      
      public ErpCatBancoId(){
      
      }
      
      public ErpCatBancoId(String compania, String banco){
          
          this.compania = compania;
          this.banco = banco;
      
      }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
      
      
    
}
