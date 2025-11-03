/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.token.util;

import java.io.Serializable;

/**
 *
 * @author 55555
 */
public class ResponseQueryToken implements Serializable {
    
    private String idToken;
    private String msg;
    private boolean success;
    
     public ResponseQueryToken(String idToken) {
        this.idToken = idToken;
    }

    public ResponseQueryToken() {
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    
    
    
     
     
    
}
