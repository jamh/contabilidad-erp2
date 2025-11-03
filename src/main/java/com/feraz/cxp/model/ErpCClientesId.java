
package com.feraz.cxp.model;

/**
 *
 * @author Feraz3
 */
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ErpCClientesId implements java.io.Serializable{
    
     @Column(name = "COMPANIA")
    private String compania;
    
    @Column(name = "ID_CLIENTE")
    private String idCliente;
    
    @Column(name = "ORIGEN")
    private String origen;
    
    public ErpCClientesId(){
    
    }
    
    public ErpCClientesId(String compania, String idCliente, String origen){
        
        this.compania = compania;
        this.idCliente = idCliente;
        this.origen = origen;
        
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    
    
}
