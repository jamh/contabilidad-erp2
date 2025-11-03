package com.feraz.cxp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Armando
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ErpCxpOrdenCompraLogId implements java.io.Serializable {
    
    @JsonProperty
    @Column(name = "COMPANIA")
    private String compania;
    
    @JsonProperty
    @Column(name = "ID")
    private Integer id;
    
    @JsonProperty
    @Column(name = "FOLIO")
    private BigDecimal folio;
    
    @JsonProperty
    @Column(name = "UUID")
    private String uuid;
    
    public ErpCxpOrdenCompraLogId(){
        
    }
    
    public ErpCxpOrdenCompraLogId(String compania, Integer id, BigDecimal folio, String uuid){
        this.compania = compania;
        this.id = id;
        this.folio = folio;
        this.uuid = uuid;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFolio() {
        return folio;
    }

    public void setFolio(BigDecimal folio) {
        this.folio = folio;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    
    
}
