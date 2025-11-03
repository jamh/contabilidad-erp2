/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cxp.model;

/**
 *
 * @author LENOVO
 */

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ErpDetProrrateoXNegocioId implements java.io.Serializable{
    
      @Column (name = "COMPANIA")
    private String compania;
    
    @Column (name = "NUMERO")
    private Integer numero;
    
    @Column (name = "SEC")
    private Integer sec;
    
    @Column (name = "CTO_CTO")
    private String ctoCto;
    
    @Column (name = "TIPO")
    private String tipo;
    
    @Column (name = "TIPO_NEGOCIO")
    private String tipoNegocio;
    
        @Column (name = "ID_FAMILIA")
    private Integer idFamilia;
    
    public ErpDetProrrateoXNegocioId(){
    
    }
    
    public ErpDetProrrateoXNegocioId(String compania, Integer numero,Integer sec,String ctoCto, String tipo, String tipoNegocio, Integer idFamilia){
        
        this.compania = compania;
        this.numero = numero;
        this.sec = sec;
        this.ctoCto = ctoCto;
        this.tipo = tipo;
        this.tipoNegocio = tipoNegocio;
        this.idFamilia = idFamilia;
                
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }

    public String getCtoCto() {
        return ctoCto;
    }

    public void setCtoCto(String ctoCto) {
        this.ctoCto = ctoCto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }
    
    
    
    
    
}
