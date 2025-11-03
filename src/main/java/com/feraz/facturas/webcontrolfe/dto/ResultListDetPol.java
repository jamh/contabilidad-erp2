/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

import com.feraz.polizas3.model.DetPolizas;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ResultListDetPol {
    private List<DetPolizas> lista;
    private String err;
    private boolean valid;

    public ResultListDetPol() {
    }
    
    

    public List<DetPolizas> getLista() {
        return lista;
    }

    public void setLista(List<DetPolizas> lista) {
        this.lista = lista;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
}
