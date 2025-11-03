/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.model;

/**
 *
 * @author 5555
 */
public class ExtJSFormResult {
    private boolean success;
    private String message;
 
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
 
    public String toString(){
        if(message==null){
        return "{success:"+this.success+"}";
        }else{
            return "{success:"+this.success+" ,'msg':'"+message+"' }";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
