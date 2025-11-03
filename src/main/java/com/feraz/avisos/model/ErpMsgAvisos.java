/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.model;

/**
 *
 * @author Ing. David Ortiz García
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.AttributeOverride;


import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ERP_MSG_AVISOS")
public class ErpMsgAvisos implements java.io.Serializable{
    
     @EmbeddedId
    @AttributeOverrides({
      
        @AttributeOverride(name = "compania", column = @Column(name = "COMPANIA", nullable = false, length = 10)),
        @AttributeOverride(name = "usuario", column = @Column(name = "USUARIO", nullable = false, length = 50)),
        @AttributeOverride(name = "sec", column = @Column(name = "SEC", nullable = false))
    })
    
     @JsonProperty("ID2")
    private ErpMsgAvisosId id;
    
    @JsonProperty("GPO_USUARIO")
    @Column(name = "GPO_USUARIO")
    private String gpUsuario;
    
    @JsonProperty("MENSAJE")
    @Column(name = "MENSAJE")
    private String mensaje;
    
    @JsonProperty("FECHA_INI")
    @Column(name = "FECHA_INI")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIni;
    
    @JsonProperty("FECHA_FIN")
    @Column(name = "FECHA_FIN")
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
     @JsonProperty("ESTATUS")
    @Column(name = "ESTATUS")
    private String estatus;
    
     
     @JsonProperty("COLOR_FONDO")
    @Column(name = "COLOR_FONDO")
    private String colorFondo;
     
     @JsonProperty("TIEMPO_VISIBLE")
    @Column(name = "TIEMPO_VISIBLE")
    private int tiempoVisible;
     
     @JsonProperty("TIPO")
     @Column(name = "TIPO")
     private String tipo;
     
     @JsonProperty ("TITULO")
     @Column(name = "TITULO")
     private String titulo;
     
     @JsonProperty("NUM_VISTAS")
     @Column(name = "NUM_VISTAS")
     private int numVistas;
     
     @JsonProperty ("TIPO_CONTROL")
     @Column(name = "TIPO_CONTROL")
     private String tipoControl;
     
     @JsonProperty ("ORDEN")
     @Column(name = "ORDEN")
     private String orden;
     
     public ErpMsgAvisos (){
     
     }
     
     public ErpMsgAvisos(ErpMsgAvisosId id){
        this.id = id;
     }

    public ErpMsgAvisosId getId() {
        return id;
    }

    public void setId(ErpMsgAvisosId id) {
        this.id = id;
    }

    public String getGpUsuario() {
        return gpUsuario;
    }

    public void setGpUsuario(String gpUsuario) {
        this.gpUsuario = gpUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getTiempoVisible() {
        return tiempoVisible;
    }

    public void setTiempoVisible(int tiempoVisible) {
        this.tiempoVisible = tiempoVisible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumVistas() {
        return numVistas;
    }

    public void setNumVistas(int numVistas) {
        this.numVistas = numVistas;
    }

    public String getTipoControl() {
        return tipoControl;
    }

    public void setTipoControl(String tipoControl) {
        this.tipoControl = tipoControl;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
     
     
    
}
