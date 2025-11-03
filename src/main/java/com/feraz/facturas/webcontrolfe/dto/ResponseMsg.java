/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

/**
 *
 * @author Ing. JAMH
 */
public class ResponseMsg {
    private String msg;
    private boolean isGood;

    public ResponseMsg() {
    }

    public ResponseMsg(String msg, boolean isGood) {
        this.msg = msg;
        this.isGood = isGood;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIsGood() {
        return isGood;
    }

    public void setIsGood(boolean isGood) {
        this.isGood = isGood;
    }
    
}
