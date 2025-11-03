/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.contabilidad.convertidor.util;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante;

/**
 *
 * @author Ing. JAMH
 */
public class ResponseComprobante {
    private Comprobante comprobante;
    private Long numero;

    public ResponseComprobante() {
    }

    
    
    public ResponseComprobante(Comprobante comprobante, Long numero) {
        this.comprobante = comprobante;
        this.numero = numero;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
    
    
}
