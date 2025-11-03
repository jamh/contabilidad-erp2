/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.dao;

/**
 *
 * @author Feraz3
 */

import java.util.List;
import com.feraz.polizas3.model.PolizasArchivos;
import com.feraz.polizas3.model.PolizasArchivosId;

public interface PolizasArchivosDao {
    
     public PolizasArchivosId save(PolizasArchivos polizasArchivos);
    public List<PolizasArchivos> findArchivosPolizas();
    public boolean delete (PolizasArchivos deleteArchivos);
    public boolean update (PolizasArchivos updateArchivos);
    public int getMaxId(PolizasArchivosId id);
    
}
