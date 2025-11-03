/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.portal.dto;

/**
 *
 * @author Ing. JAMH
 */
public class ValidaDTO {
    
    private boolean valid;
    private String msg;
    private int pos;
    private String compania;
    private String ordenAklara;
    private String rfcAklaraProveedor;
    private String rfcAklaraPlantel;
    private String idCorreo;
    private String estatus;
 
    
    public ValidaDTO(boolean valid, String msg) {
        this.valid = valid;
        this.msg = msg;
    }

    public ValidaDTO(boolean valid, String msg, int pos) {
        this.valid = valid;
        this.msg = msg;
        this.pos = pos;
    }

    
    public ValidaDTO() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getOrdenAklara() {
        return ordenAklara;
    }

    public void setOrdenAklara(String ordenAklara) {
        this.ordenAklara = ordenAklara;
    }

    public String getRfcAklaraProveedor() {
        return rfcAklaraProveedor;
    }

    public void setRfcAklaraProveedor(String rfcAklaraProveedor) {
        this.rfcAklaraProveedor = rfcAklaraProveedor;
    }

    public String getRfcAklaraPlantel() {
        return rfcAklaraPlantel;
    }

    public void setRfcAklaraPlantel(String rfcAklaraPlantel) {
        this.rfcAklaraPlantel = rfcAklaraPlantel;
    }

    public String getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(String idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

  
    
    
    @Override
    public String toString() {
        return "ValidaDTO{" + "valid=" + valid + ", msg=" + msg + '}';
    }
    
    
}
