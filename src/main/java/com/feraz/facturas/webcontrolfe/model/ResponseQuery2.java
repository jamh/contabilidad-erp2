/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public class ResponseQuery2 implements Serializable{
    
    private boolean success;
    private List data;
    private List dataErr;
    private String msg;
    private BigDecimal total;

    public ResponseQuery2(boolean success, List data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public ResponseQuery2() {
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
    
    
    
}
