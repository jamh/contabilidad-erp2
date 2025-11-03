
package com.feraz.cxp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ing. JAMH
 */
@Embeddable
public class ErpCpOtrasId implements Serializable {

    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "ID")
    private int id;

    public ErpCpOtrasId(String compania, int id) {
        this.compania = compania;
        this.id = id;
    }

    public ErpCpOtrasId() {
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
