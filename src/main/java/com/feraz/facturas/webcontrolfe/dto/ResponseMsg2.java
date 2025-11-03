/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. JAMH
 */
public class ResponseMsg2 {
    private String msg;
    private boolean isGood;
    private List<String> list = new ArrayList<String>();

    public ResponseMsg2() {
    }

    public ResponseMsg2(String msg, boolean isGood) {
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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

   
    
}
