/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.feraz.facturas.webcontrolfe.dto;

import mx.bigdata.sat.cfdi.v32.schema.Comprobante;

/**
 *
 * @author Administrador
 */
public class PolizasInfo {
    
    private String numero;
    private String tipoPoliza;
    private String fecha;
    private int infTipo;
    private String msgErr;
    private Comprobante comprobante;
    private Long numeroFe;
    private String uuid;
    private String folio;

    public PolizasInfo() {
    }

    public PolizasInfo(String numero, String tipoPoliza, String fecha, int infTipo, String msgErr) {
        this.numero = numero;
        this.tipoPoliza = tipoPoliza;
        this.fecha = fecha;
        this.infTipo = infTipo;
        this.msgErr = msgErr;
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

    public int getInfTipo() {
        return infTipo;
    }

    public void setInfTipo(int infTipo) {
        this.infTipo = infTipo;
    }

    public String getMsgErr() {
        return msgErr;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }

    public Long getNumeroFe() {
        return numeroFe;
    }

    public void setNumeroFe(Long numeroFe) {
        this.numeroFe = numeroFe;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
    
    
}
