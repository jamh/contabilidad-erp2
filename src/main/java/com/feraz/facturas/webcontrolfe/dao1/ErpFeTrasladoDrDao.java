/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;

import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoDrId;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoDr;
import java.util.List;

/**
 *
 * @author FERAZ-14
 */
public interface ErpFeTrasladoDrDao {
    
    public ErpFeTrasladoDrId save(ErpFeTrasladoDr erpFeTrasladoDr);
    public List<ErpFeTrasladoDr> findErpFeTrasladoDr(ErpFeTrasladoDr erpFeTrasladoDr);
    public boolean delete(ErpFeTrasladoDr erpFeTrasladoDr);
    public boolean update(ErpFeTrasladoDr erpFeTrasladoDr);
    public int getMaxIdfindErpFeTrasladoDr(ErpFeTrasladoDrId id);
    
}
