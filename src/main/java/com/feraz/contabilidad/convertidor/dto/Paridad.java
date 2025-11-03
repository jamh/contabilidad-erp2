
package com.feraz.contabilidad.convertidor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Ing. JAMH
 */
public class Paridad {
    
    @JsonProperty("FECHA")
    private String FECHA;
    
    @JsonProperty("FACTOR")
    private double FACTOR;

    public Paridad() {
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public double getFACTOR() {
        return FACTOR;
    }

    public void setFACTOR(double FACTOR) {
        this.FACTOR = FACTOR;
    }



}
