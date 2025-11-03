/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.cfdi.dao;

import com.feraz.cfdi.model.ErpSatLeerCfdi;
import com.feraz.cfdi.model.ErpSatLeerCfdiId;
import java.util.List;

/**
 *
 * @author Feraz3
 */
public interface ErpSatLeerCfdiDao {
    
    public ErpSatLeerCfdiId save(ErpSatLeerCfdi erpSatLeerCfdi);  
    public Integer findErpSatLeerCfdi(List<String> lista);
    public boolean delete (ErpSatLeerCfdi deleteErpSatLeerCfdi);
    public boolean update (ErpSatLeerCfdi updateErpSatLeerCfdi);
    public int getMaxId(ErpSatLeerCfdiId id);
    
}
