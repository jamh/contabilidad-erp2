/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author Ing . JAMH 
 */
public class VallidaRespDTO {
  
    private boolean valida;
    
    private String msg;

    public VallidaRespDTO() {
    }

    
    
    
    public VallidaRespDTO(boolean valida, String msg) {
        this.valida = valida;
        this.msg = msg;
    }

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "VallidaRespDTO{" + "valida=" + valida + ", msg=" + msg + '}';
    }
    
    
    
    
    
}
