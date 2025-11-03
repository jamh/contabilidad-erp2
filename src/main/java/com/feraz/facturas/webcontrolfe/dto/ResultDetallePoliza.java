/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

import com.feraz.polizas3.model.DetPolizas;

/**
 *
 * @author Administrador
 */
public class ResultDetallePoliza {
    private boolean isValid;
    private String msgErr;
    private DetPolizas detalle;

    public ResultDetallePoliza() {
    }

    public ResultDetallePoliza(boolean isValid, String msgErr) {
        this.isValid = isValid;
        this.msgErr = msgErr;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getMsgErr() {
        return msgErr;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }

    public DetPolizas getDetalle() {
        return detalle;
    }

    public void setDetalle(DetPolizas detalle) {
        this.detalle = detalle;
    }
    
    
}
