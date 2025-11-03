/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.avisos.dto;

/**
 *
 * @author Feraz3
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Feraz3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvisosResponseDTO {
    
    @JsonProperty("MENSAJE")
    private String mensaje;
    
    @JsonProperty("COLOR_FONDO")
    private String colorFondo;
    
    @JsonProperty("TIEMPO_VISIBLE")
    private int tiempoVisible;
    
    @JsonProperty("TIPO")
    private String tipo;
    
     @JsonProperty("TITULO")
    private String titulo;

   

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    
}
