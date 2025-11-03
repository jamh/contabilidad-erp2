/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.contabilidad.sat.electronica.dao;


import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtas;
import com.feraz.contabilidad.sat.electronica.model.ErpSatCatalogoCtasId;
import java.util.List;
/**
 *
 * @author Feraz3
 */
public interface ErpSatCatalogoCtasDao {
    
     public List<ErpSatCatalogoCtas> FindErpSatCatalogoCtas(String compania,String pid);
    
}
