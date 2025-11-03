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
public class VallidaCfidDTO {
   public String rfcEmisor;
   public String rfcReceptor;
   public String uuid;
   public String total;

    public VallidaCfidDTO(String rfcEmisor, String rfcReceptor, String uuid, String total) {
        this.rfcEmisor = rfcEmisor;
        this.rfcReceptor = rfcReceptor;
        this.uuid = uuid;
        this.total = total;
    }

    public VallidaCfidDTO() {
    }
   
   
   

    public String getRfcEmisor() {
        return rfcEmisor;
    }

    public void setRfcEmisor(String rfcEmisor) {
        this.rfcEmisor = rfcEmisor;
    }

    public String getRfcReceptor() {
        return rfcReceptor;
    }

    public void setRfcReceptor(String rfcReceptor) {
        this.rfcReceptor = rfcReceptor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VallidaCfidDTO{" + "rfcEmisor=" + rfcEmisor + ", rfcReceptor=" + rfcReceptor + ", uuid=" + uuid + ", total=" + total + '}';
    }
   
   
   
}
