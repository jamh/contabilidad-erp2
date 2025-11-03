/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.model;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class PolizasId implements java.io.Serializable {
    @JsonProperty("COMPANIA")
    @Column (name = "COMPANIA")
    private String compania;
    @JsonProperty("TIPO_POLIZA")
    @Column (name = "TIPO_POLIZA")
    private String tipoPoliza;
    @JsonProperty("FECHA")
    @Column (name = "FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @JsonProperty("NUMERO")
    @Column (name = "NUMERO")
    private String numero;
    
    public PolizasId(){
    
    }
    
    public PolizasId(String compania, String tipoPoliza, Date fecha, String numero){
        
        this.compania = compania;
        this.tipoPoliza = tipoPoliza;
        this.fecha = fecha;
        this.numero = numero;
    
    }

    public String getCompania() {
        return compania;
    }

    public String getTipoPoliza() {
        return tipoPoliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public void setTipoPoliza(String tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    
}
