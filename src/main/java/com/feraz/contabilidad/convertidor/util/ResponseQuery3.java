/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.convertidor.util;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public class ResponseQuery3 implements Serializable{
    
    private boolean success;
    private List data;
    private List dataErr;
    private String msg;
    private BigDecimal total;
    private String numero;
    private String tipoPoliza;
    private String fecha;
    private String compania;
    private String numeroFe;

    public ResponseQuery3(boolean success, List data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public ResponseQuery3() {
    }   
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List getDataErr() {
        return dataErr;
    }

    public void setDataErr(List dataErr) {
        this.dataErr = dataErr;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(String numeroFe) {
        this.numeroFe = numeroFe;
    }
    
    
    
}
