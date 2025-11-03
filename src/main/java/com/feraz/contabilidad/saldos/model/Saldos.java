/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.saldos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.feraz.cuentas.model.CuentasId;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ing. JAMH
 */
@Entity
@Table(name = "SALDOS")
public class Saldos implements Serializable {


     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "tipo", column = @Column(name = "TIPO", nullable = false, length = 5)),
        @AttributeOverride(name = "calendario", column = @Column(name = "CALENDARIO", nullable = false)),
        @AttributeOverride(name = "periodo", column = @Column(name = "PERIODO", nullable = false)),
        @AttributeOverride(name = "cuenta", column = @Column(name = "CUENTA", nullable = false, length = 50)),
        @AttributeOverride(name = "cCostos", column = @Column(name = "C_COSTOS", nullable = false, length = 10))
    })
    
   
    private SaldosId id;
     
     
    @Column(name = "SALDO_INICIAL")
    private BigDecimal saldoInicial;
    @Column(name = "CARGOS")
    private BigDecimal cargos;
    @Column(name = "ABONOS")
    private BigDecimal abonos;
    @Column(name = "SALDO_FINAL")
    private BigDecimal saldoFinal;

  

    public Saldos() {
    }
    
    public Saldos(SaldosId id) {
        
        this.id = id;
    }

 
    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }


    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public SaldosId getId() {
        return id;
    }

    public void setId(SaldosId id) {
        this.id = id;
    }

}
