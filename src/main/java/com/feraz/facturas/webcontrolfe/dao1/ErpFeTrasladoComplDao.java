/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.facturas.webcontrolfe.dao1;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoCompl;
import com.feraz.facturas.webcontrolfe.model.ErpFeTrasladoComplId;
import java.util.List;

/**
 * @author Armando
 */
public interface ErpFeTrasladoComplDao {
    public ErpFeTrasladoComplId save(ErpFeTrasladoCompl erpFeTrasladoCompl);
    public List<ErpFeTrasladoCompl> findErpFeTrasladoCompl(ErpFeTrasladoCompl erpFeTrasladoCompl);
    public boolean delete(ErpFeTrasladoCompl erpFeTrasladoCompl);
    public boolean update(ErpFeTrasladoCompl erpFeTrasladoCompl);
    public int getMaxIdErpFeTrasladoCompl(ErpFeTrasladoComplId id);
    
    
}
