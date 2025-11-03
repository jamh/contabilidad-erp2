/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.portal.dto;

import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public class ValidadorGral {
    
    private ValidaDTO ultimoValidador;
    private List<ValidaDTO> pasosCorrectos;
    private Integer errores;
    private Integer aciertos;
    private String compania;

    public ValidadorGral() {
    }

    public ValidadorGral(ValidaDTO ultimoValidador, List<ValidaDTO> pasosCorrectos) {
        this.ultimoValidador = ultimoValidador;
        this.pasosCorrectos = pasosCorrectos;
    }

    public ValidaDTO getUltimoValidador() {
        return ultimoValidador;
    }

    public void setUltimoValidador(ValidaDTO ultimoValidador) {
        this.ultimoValidador = ultimoValidador;
    }

    public List<ValidaDTO> getPasosCorrectos() {
        return pasosCorrectos;
    }

    public void setPasosCorrectos(List<ValidaDTO> pasosCorrectos) {
        this.pasosCorrectos = pasosCorrectos;
    }

    @Override
    public String toString() {
   
        return "ValidadorGral{" + "ultimoValidador=" + ultimoValidador + '}';
    }

    public Integer getErrores() {
        return errores;
    }

    public void setErrores(Integer errores) {
        this.errores = errores;
    }

    public Integer getAciertos() {
        return aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    
    
    
    
    
    
    
}
